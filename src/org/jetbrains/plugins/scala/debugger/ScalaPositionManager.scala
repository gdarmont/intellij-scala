package org.jetbrains.plugins.scala
package debugger

import java.util

import com.intellij.debugger.engine.{CompoundPositionManager, DebugProcess, DebugProcessImpl}
import com.intellij.debugger.requests.ClassPrepareRequestor
import com.intellij.debugger.{NoDataException, PositionManager, SourcePosition}
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.impl.DirectoryIndex
import com.intellij.openapi.util.{Ref, TextRange}
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi._
import com.intellij.psi.search.{FilenameIndex, GlobalSearchScope}
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.{Processor, Query}
import com.sun.jdi._
import com.sun.jdi.request.ClassPrepareRequest
import org.jetbrains.annotations.{NotNull, Nullable}
import org.jetbrains.plugins.scala.caches.ScalaShortNamesCacheManager
import org.jetbrains.plugins.scala.debugger.ScalaPositionManager._
import org.jetbrains.plugins.scala.debugger.smartStepInto.FunExpressionTarget
import org.jetbrains.plugins.scala.extensions.{ObjectExt, inReadAction}
import org.jetbrains.plugins.scala.lang.psi.api.expr._
import org.jetbrains.plugins.scala.lang.psi.api.statements.params.ScParameters
import org.jetbrains.plugins.scala.lang.psi.api.statements.{ScFunctionDefinition, ScMacroDefinition}
import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef._
import org.jetbrains.plugins.scala.lang.psi.api.{ScalaFile, ScalaRecursiveElementVisitor}
import org.jetbrains.plugins.scala.lang.psi.impl.ScalaPsiManager
import org.jetbrains.plugins.scala.lang.psi.types.ValueClassType
import org.jetbrains.plugins.scala.lang.psi.{ScalaPsiElement, ScalaPsiUtil}
import org.jetbrains.plugins.scala.lang.refactoring.util.{ScalaNamesUtil, ScalaRefactoringUtil}
import org.jetbrains.plugins.scala.lang.resolve.ResolvableReferenceElement
import org.jetbrains.plugins.scala.util.macroDebug.ScalaMacroDebuggingUtil

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

/**
 * @author ilyas
 */
class ScalaPositionManager(debugProcess: DebugProcess) extends PositionManager {
  def getDebugProcess = debugProcess
  def debugProcessImpl = debugProcess match {
    case impl: DebugProcessImpl => Some(impl)
    case _ => None
  }

  @NotNull def locationsOfLine(refType: ReferenceType, position: SourcePosition): util.List[Location] = {
    try {
      val line: Int = position.getLine + 1
      val locations: util.List[Location] =
        if (getDebugProcess.getVirtualMachineProxy.versionHigher("1.4"))
          refType.locationsOfLine(DebugProcess.JAVA_STRATUM, null, line)
        else refType.locationsOfLine(line)
      if (locations == null || locations.isEmpty) throw NoDataException.INSTANCE
      locations
    }
    catch {
      case e: AbsentInformationException =>
        throw NoDataException.INSTANCE
    }
  }

  private def findReferenceTypeSourceImage(position: SourcePosition): ScalaPsiElement = {
    if (position == null) return null
    @tailrec
    def findSuitableParent(element: PsiElement): PsiElement = {
      element match {
        case null => null
        case elem @ (_: ScForStatement | _: ScTemplateDefinition | FunExpressionTarget(_, _)) => elem
        case expr: ScExpression if isInsideMacro(position) => expr
        case elem => findSuitableParent(elem.getParent)
      }
    }

    val element = nonWhitespaceElement(position)
    findSuitableParent(element).asInstanceOf[ScalaPsiElement]
  }

  def nonWhitespaceElement(position: SourcePosition): PsiElement = {
    if (position == null) return null

    val file = position.getFile
    @tailrec
    def nonWhitespaceInner(element: PsiElement, document: Document): PsiElement = {
      element match {
        case null => null
        case ws: PsiWhiteSpace if document.getLineNumber(element.getTextRange.getEndOffset) == position.getLine =>
          val nextElement = file.findElementAt(element.getTextRange.getEndOffset)
          nonWhitespaceInner(nextElement, document)
        case _ => element
      }
    }
    if (!file.isInstanceOf[ScalaFile]) null
    else {
      val firstElement = file.findElementAt(position.getOffset)
      try {
        val document = PsiDocumentManager.getInstance(file.getProject).getDocument(file)
        nonWhitespaceInner(firstElement, document)
      }
      catch {
        case t: Throwable => firstElement
      }
    }
  }

  private def isInsideMacro(position: SourcePosition): Boolean = {
    val element: PsiElement = nonWhitespaceElement(position)
    var call = PsiTreeUtil.getParentOfType(element, classOf[ScMethodCall])
    while (call != null) {
      call.getEffectiveInvokedExpr match {
        case resRef: ResolvableReferenceElement =>
          if (resRef.resolve().isInstanceOf[ScMacroDefinition]) return true
        case _ =>
      }
      call = PsiTreeUtil.getParentOfType(call, classOf[ScMethodCall])
    }
    false
  }

  private def findEnclosingTypeDefinition(position: SourcePosition): Option[ScTypeDefinition] = {
    if (position == null) return None
    @tailrec
    def notLocalEnclosingTypeDefinition(element: PsiElement): Option[ScTypeDefinition] = {
      PsiTreeUtil.getParentOfType(element, classOf[ScTypeDefinition]) match {
        case null => None
        case td if ScalaPsiUtil.isLocalClass(td) => notLocalEnclosingTypeDefinition(td.getParent)
        case td => Some(td)
      }
    }

    val element = nonWhitespaceElement(position)
    notLocalEnclosingTypeDefinition(element)

  }

  def createPrepareRequest(requestor: ClassPrepareRequestor, position: SourcePosition): ClassPrepareRequest = {
    val qName = new Ref[String](null)
    val waitRequestor = new Ref[ClassPrepareRequestor](null)
    inReadAction {
      val sourceImage: ScalaPsiElement = findReferenceTypeSourceImage(position)
      val insideMacro: Boolean = isInsideMacro(position)

      def isLocalOrUnderDelayedInit(definition: PsiClass): Boolean = {
        val isDelayed = definition match {
          case obj: ScObject =>
            val manager: ScalaPsiManager = ScalaPsiManager.instance(obj.getProject)
            val clazz: PsiClass = manager.getCachedClass(obj.getResolveScope, "scala.DelayedInit")
            clazz != null && manager.cachedDeepIsInheritor(obj, clazz)
          case _ => false
        }
        ScalaPsiUtil.isLocalClass(definition) || isDelayed
      }

      sourceImage match {
        case cl: ScClass if ValueClassType.isValueClass(cl) =>
          //there are no instances of value classes, methods from companion object are used
          qName.set(getSpecificNameForDebugger(cl) + "$")
        case typeDef: ScTypeDefinition if !isLocalOrUnderDelayedInit(typeDef) =>
          val specificName = getSpecificNameForDebugger(typeDef)
          qName.set(if (insideMacro) specificName + "*" else specificName)
        case _ =>
          findEnclosingTypeDefinition(position)
                .foreach(typeDef => qName.set(typeDef.getQualifiedNameForDebugger + "*"))
      }
      // Enclosing type definition is not found
      if (qName.get == null) {
        if (position.getFile.isInstanceOf[ScalaFile]) {
          qName.set(SCRIPT_HOLDER_CLASS_NAME + "*")
        }
      }
      waitRequestor.set(new ScalaPositionManager.MyClassPrepareRequestor(position, requestor))
    }

    if (qName.get == null || waitRequestor.get == null) throw NoDataException.INSTANCE
    getDebugProcess.getRequestsManager.createClassPrepareRequest(waitRequestor.get, qName.get)
  }

  def getSourcePosition(location: Location): SourcePosition = {
    val position =
      for {
        loc <- location.toOption
        psiFile <- getPsiFileByLocation(getDebugProcess.getProject, loc).toOption
        lineNumber = calcLineIndex(location)
        if lineNumber >= 0
      } yield {
        val methodName = location.method().name()
        calcPosition(psiFile, lineNumber, methodName).getOrElse {
          SourcePosition.createFromLine(psiFile, lineNumber)
        }
      }
    position match {
      case Some(p) => p
      case None => throw NoDataException.INSTANCE
    }
  }

  private def calcLineIndex(location: Location): Int = {
    LOG.assertTrue(getDebugProcess != null)
    if (location == null) return -1
    try {
      location.lineNumber - 1
    }
    catch {
      case e: InternalError => -1
    }
  }

  private def calcPosition(file: PsiFile, lineNumber: Int, methodName: String): Option[SourcePosition] = {
    val scFile = file match {
      case sf: ScalaFile if !sf.isCompiled => sf
      case _ => return None
    }
    val exprs = expressionsOnLine(scFile, lineNumber)
    def findDefaultArg = {
      try {
        val paramNumber = methodName.substring(methodName.lastIndexOf("$") + 1).toInt - 1
        val inDefaultParam = exprs.find {
          case e =>
            val scParameters = PsiTreeUtil.getParentOfType(e, classOf[ScParameters])
            if (scParameters != null) {
              val param = scParameters.params(paramNumber)
              param.isDefaultParam && param.isAncestorOf(e)
            }
            else false
        }
        inDefaultParam.map(SourcePosition.createFromElement)
      } catch {
        case e: Exception => None
      }
    }

    if (methodName.contains("$default$")) {
      return findDefaultArg
    }

    if (exprs.size == 1) return Some(SourcePosition.createFromElement(exprs.head))

    val inMethodBody = exprs.find {
      case e =>
        val fun = PsiTreeUtil.getParentOfType(e, classOf[ScFunctionDefinition])
        fun != null && fun.body.exists(PsiTreeUtil.isAncestor(_, e, false))
    }
    inMethodBody.map(SourcePosition.createFromElement)
  }

  private def expressionsOnLine(file: ScalaFile, lineNumber: Int): Seq[ScExpression] = {
    val document = PsiDocumentManager.getInstance(file.getProject).getDocument(file)
    if (lineNumber >= document.getLineCount) throw NoDataException.INSTANCE
    val startLine = document.getLineStartOffset(lineNumber)
    val endLine = document.getLineEndOffset(lineNumber)
    val lineRange = new TextRange(startLine, endLine)
    val commonParent = ScalaRefactoringUtil.commonParent(file, lineRange)
    val exprs = ListBuffer[ScExpression]()
    val collector = new ScalaRecursiveElementVisitor {
      override def visitExpression(expr: ScExpression): Unit = {
        if (lineRange.contains(expr.getTextRange.getStartOffset)) exprs += expr
        else super.visitExpression(expr)
      }
    }
    commonParent.accept(collector)
    exprs.toSeq
  }

  @Nullable private def getPsiFileByLocation(project: Project, location: Location): PsiFile = {
    if (location == null) return null
    val refType = location.declaringType
    if (refType == null) return null
    val originalQName = refType.name.replace('/', '.')
    val searchScope: GlobalSearchScope = getDebugProcess.getSearchScope
    if (originalQName.startsWith(SCRIPT_HOLDER_CLASS_NAME)) {
      try {
        val sourceName = location.sourceName
        val files: Array[PsiFile] = FilenameIndex.getFilesByName(project, sourceName, searchScope)
        if (files.length == 1) return files(0)
      }
      catch {
        case e: AbsentInformationException => return null
      }
    }
    val dollar: Int = originalQName.indexOf('$')
    val qName = if (dollar >= 0) originalQName.substring(0, dollar) else originalQName
    val classes = ScalaShortNamesCacheManager.getInstance(project).getClassesByFQName(qName, searchScope)
    val clazz: PsiClass =
      if (classes.length == 1) classes.head
      else if (classes.length == 2 && ScalaPsiUtil.getCompanionModule(classes.head).contains(classes(1))) classes.head
      else null
    if (clazz != null && clazz.isValid && !ScalaMacroDebuggingUtil.isEnabled) {
      return clazz.getNavigationElement.getContainingFile
    }
    val directoryIndex: DirectoryIndex = DirectoryIndex.getInstance(project)
    val dotIndex = qName.lastIndexOf(".")
    val packageName = if (dotIndex > 0) qName.substring(0, dotIndex) else ""
    val query: Query[VirtualFile] = directoryIndex.getDirectoriesByPackageName(packageName, true)
    val fileNameWithoutExtension = if (dotIndex > 0) qName.substring(dotIndex + 1) else qName
    val fileNames: util.Set[String] = new util.HashSet[String]
    import scala.collection.JavaConversions._
    for (extention <- ScalaLoader.SCALA_EXTENSIONS) {
      fileNames.add(fileNameWithoutExtension + "." + extention)
    }
    val result = new Ref[PsiFile]
    query.forEach(new Processor[VirtualFile] {
      override def process(vDir: VirtualFile): Boolean = {
        var isFound = false
        for {
          fileName <- fileNames
          if !isFound
          vFile <- vDir.findChild(fileName).toOption
        } {
          val psiFile: PsiFile = PsiManager.getInstance(project).findFile(vFile)
          val debugFile: PsiFile = ScalaMacroDebuggingUtil.loadCode(psiFile, force = false)
          if (debugFile != null) {
            result.set(debugFile)
            isFound = true
          }
          else if (psiFile.isInstanceOf[ScalaFile]) {
            result.set(psiFile)
            isFound = true
          }
        }
        !isFound
      }
    })
    result.get
  }

  @NotNull def getAllClasses(position: SourcePosition): util.List[ReferenceType] = {
    val result = inReadAction {
      val sourceImage = findReferenceTypeSourceImage(position)
      sourceImage match {
        case td: ScTemplateDefinition if ScalaPsiUtil.isLocalClass(td) =>
          val qName = findEnclosingTypeDefinition(position).map(typeDef => typeDef.getQualifiedNameForDebugger)
          val javaName = td match {
            case _: ScNewTemplateDefinition => "$anon$"
            case _ => ScalaNamesUtil.toJavaName(td.name)
          }
          qName match {
            case Some(enclName) =>
              filterAllClasses { clazz =>
                val cName = clazz.name()
                cName.startsWith(enclName) && cName.contains(s"$$$javaName") && hasLocations(clazz, position)
              }
            case _ => util.Collections.emptyList[ReferenceType]
          }
        case td: ScTypeDefinition =>
          val qName = getSpecificNameForDebugger(td)
          if (qName != null) getDebugProcess.getVirtualMachineProxy.classesByName(qName)
          else util.Collections.emptyList[ReferenceType]
        case _ =>
          val qName = findEnclosingTypeDefinition(position).map(typeDef => typeDef.getQualifiedNameForDebugger)
          qName match {
            case Some(name) => filterAllClasses(c => c.name().startsWith(name) && hasLocations(c, position))
            case _ => util.Collections.emptyList[ReferenceType]
          }
      }
    }
    if (result == null || result.isEmpty) throw NoDataException.INSTANCE
    result
  }

  private def hasLocations(refType: ReferenceType, position: SourcePosition): Boolean = {
    var hasLocations = false
    try {
      hasLocations = refType.locationsOfLine(position.getLine + 1).size > 0
    } catch {
      case ignore @ (_: AbsentInformationException | _: ClassNotPreparedException | _: ObjectCollectedException) =>
    }
    hasLocations
  }
  private def filterAllClasses(condition: ReferenceType => Boolean): util.List[ReferenceType] = {
    import scala.collection.JavaConverters._
    val allClasses = getDebugProcess.getVirtualMachineProxy.allClasses.asScala
    allClasses.filter(condition).asJava
  }

}

object ScalaPositionManager {
  private val LOG: Logger = Logger.getInstance("#com.intellij.debugger.engine.PositionManagerImpl")
  private val SCRIPT_HOLDER_CLASS_NAME: String = "Main$$anon$1"

  private def getSpecificNameForDebugger(td: ScTypeDefinition): String = {
    val name = td.getQualifiedNameForDebugger

    td match {
      case _: ScObject => s"$name$$"
      case _: ScTrait => s"$name$$class"
      case _ => name
    }
  }

  private class MyClassPrepareRequestor(position: SourcePosition, requestor: ClassPrepareRequestor) extends ClassPrepareRequestor {
   def processClassPrepare(debuggerProcess: DebugProcess, referenceType: ReferenceType) {
      val positionManager: CompoundPositionManager = debuggerProcess.asInstanceOf[DebugProcessImpl].getPositionManager
      if (positionManager.locationsOfLine(referenceType, position).size > 0) {
        requestor.processClassPrepare(debuggerProcess, referenceType)
      }
      else {
        val positionClasses: util.List[ReferenceType] = positionManager.getAllClasses(position)
        if (positionClasses.contains(referenceType)) {
          requestor.processClassPrepare(debuggerProcess, referenceType)
        }
      }
    }
  }
}
