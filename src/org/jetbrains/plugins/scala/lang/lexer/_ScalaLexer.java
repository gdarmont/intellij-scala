/* The following code was generated by JFlex 1.4.1 on 27.09.06 19:19 */

package org.jetbrains.plugins.scala.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import java.util.*;
import java.lang.reflect.Field;
import org.jetbrains.annotations.NotNull;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 27.09.06 19:19 from the specification file
 * <tt>scala.flex</tt>
 */
public class _ScalaLexer implements FlexLexer, ScalaTokenTypes {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int IN_XML_STATE = 4;
  public static final int IN_STRING_STATE = 3;
  public static final int IN_LINE_COMMENT_STATE = 2;
  public static final int IN_BLOCK_COMMENT_STATE = 1;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\0\1\51\1\0\1\52\1\50\22\0\1\32\1\0\1\46"+
    "\4\0\1\47\1\63\1\64\1\54\1\42\1\45\1\42\1\40\1\53"+
    "\1\34\7\36\2\35\1\0\1\45\1\55\1\0\1\56\2\0\3\37"+
    "\1\43\1\41\1\43\5\44\1\33\16\44\1\57\1\0\1\60\1\0"+
    "\1\44\1\0\1\1\1\2\1\6\1\12\1\7\1\13\1\30\1\10"+
    "\1\17\1\25\1\27\1\11\1\21\1\16\1\14\1\22\1\31\1\5"+
    "\1\3\1\4\1\24\1\26\1\23\1\15\1\20\1\44\1\61\1\45"+
    "\1\62\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\5\0\22\1\1\2\2\3\2\4\1\5\1\4\2\1"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\3\14\2\15"+
    "\1\16\1\17\1\16\1\20\1\21\15\1\1\22\7\1"+
    "\1\23\7\1\1\0\1\3\1\24\1\0\1\3\1\0"+
    "\1\24\1\0\1\1\1\25\1\26\1\0\1\27\4\1"+
    "\1\30\13\1\1\31\1\1\1\32\3\1\1\33\10\1"+
    "\1\34\1\35\1\24\1\0\1\3\4\1\1\36\1\1"+
    "\1\37\1\40\2\1\1\41\2\1\1\42\5\1\1\43"+
    "\13\1\1\44\1\45\1\46\2\1\1\47\1\50\1\1"+
    "\1\51\1\52\4\1\1\53\1\54\3\1\1\55\1\56"+
    "\1\1\1\57\1\60\3\1\1\61\2\1\1\62\5\1"+
    "\1\63\1\64\2\1\1\65\1\1\1\66\1\67\1\70"+
    "\1\71\1\72\1\1\1\73";

  private static int [] zzUnpackAction() {
    int [] result = new int[206];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\65\0\152\0\237\0\324\0\u0109\0\u013e\0\u0173"+
    "\0\u01a8\0\u01dd\0\u0212\0\u0247\0\u027c\0\u02b1\0\u02e6\0\u031b"+
    "\0\u0350\0\u0385\0\u03ba\0\u03ef\0\u0424\0\u0459\0\u048e\0\u04c3"+
    "\0\u04f8\0\u052d\0\u0562\0\u04c3\0\u04c3\0\u0597\0\u05cc\0\u0601"+
    "\0\u0109\0\u0109\0\u0109\0\u0109\0\u04c3\0\u04c3\0\u04c3\0\u0636"+
    "\0\u066b\0\u06a0\0\u04c3\0\u04c3\0\u04c3\0\u06d5\0\u04c3\0\u04c3"+
    "\0\u070a\0\u073f\0\u0774\0\u07a9\0\u07de\0\u0813\0\u0848\0\u087d"+
    "\0\u08b2\0\u08e7\0\u091c\0\u0951\0\u0986\0\u0173\0\u09bb\0\u09f0"+
    "\0\u0a25\0\u0a5a\0\u0a8f\0\u0ac4\0\u0af9\0\u0173\0\u0b2e\0\u0b63"+
    "\0\u0b98\0\u0bcd\0\u0c02\0\u0c37\0\u0c6c\0\u0ca1\0\u04c3\0\u04c3"+
    "\0\u0cd6\0\u0d0b\0\u0d40\0\u0d75\0\u0597\0\u0597\0\u0109\0\u0109"+
    "\0\u06d5\0\u06d5\0\u0daa\0\u0ddf\0\u0e14\0\u0e49\0\u0173\0\u0e7e"+
    "\0\u0eb3\0\u0ee8\0\u0f1d\0\u0f52\0\u0f87\0\u0fbc\0\u0ff1\0\u1026"+
    "\0\u105b\0\u1090\0\u0173\0\u10c5\0\u0173\0\u10fa\0\u112f\0\u1164"+
    "\0\u0173\0\u1199\0\u11ce\0\u1203\0\u1238\0\u126d\0\u12a2\0\u12d7"+
    "\0\u130c\0\u0173\0\u0173\0\u1341\0\u1376\0\u13ab\0\u13e0\0\u1415"+
    "\0\u144a\0\u147f\0\u0173\0\u14b4\0\u0173\0\u0173\0\u14e9\0\u151e"+
    "\0\u0173\0\u1553\0\u1588\0\u0173\0\u15bd\0\u15f2\0\u1627\0\u165c"+
    "\0\u1691\0\u0173\0\u16c6\0\u16fb\0\u1730\0\u1765\0\u179a\0\u17cf"+
    "\0\u1804\0\u1839\0\u186e\0\u18a3\0\u18d8\0\u0173\0\u0173\0\u0173"+
    "\0\u190d\0\u1942\0\u0173\0\u0173\0\u1977\0\u0173\0\u19ac\0\u19e1"+
    "\0\u1a16\0\u1a4b\0\u1a80\0\u0173\0\u0173\0\u1ab5\0\u1aea\0\u1b1f"+
    "\0\u0173\0\u0173\0\u1b54\0\u0173\0\u0173\0\u1b89\0\u1bbe\0\u1bf3"+
    "\0\u0173\0\u1c28\0\u1c5d\0\u0173\0\u1c92\0\u1cc7\0\u1cfc\0\u1d31"+
    "\0\u1d66\0\u0173\0\u0173\0\u1d9b\0\u1dd0\0\u0173\0\u1e05\0\u0173"+
    "\0\u0173\0\u0173\0\u0173\0\u0173\0\u1e3a\0\u0173";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[206];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\2\10\1\16\1\17\1\20\1\10\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\2\10\1\27\3\10\1\30\1\10"+
    "\1\31\2\32\1\10\1\33\1\10\1\6\2\10\1\34"+
    "\1\35\1\36\3\6\1\37\1\6\1\40\1\6\1\41"+
    "\1\42\1\43\1\44\1\45\1\46\50\47\1\50\3\47"+
    "\1\51\60\47\1\52\2\53\12\47\46\54\1\55\2\54"+
    "\1\0\64\54\1\0\3\54\1\56\3\54\1\57\1\60"+
    "\2\54\32\6\1\0\1\6\3\0\1\6\1\0\4\6"+
    "\3\0\13\6\2\0\1\6\1\10\1\61\27\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\31\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\6\10\1\63\14\10\1\64\5\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\4\10\1\65\2\10\1\66\7\10\1\67\11\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\6\10\1\70\22\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\1\71\7\10\1\72\20\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\10\10\1\73\3\10\1\74\14\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\6\10\1\75\4\10\1\76"+
    "\15\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\1\77\12\10\1\100"+
    "\2\10\1\101\12\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\1\10"+
    "\1\102\23\10\1\103\3\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\6\10\1\104\14\10\1\105\5\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\12\10\1\106\5\10\1\107\10\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\16\10\1\110\12\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\1\111\30\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\1\112"+
    "\3\10\1\113\24\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\7\10"+
    "\1\114\21\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\1\115\30\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\76\0\1\116\1\0\1\117\2\120\1\0"+
    "\1\121\15\0\1\117\1\122\1\123\1\122\1\0\1\124"+
    "\1\116\1\0\1\120\30\0\1\116\1\0\1\117\2\120"+
    "\17\0\1\117\3\32\1\0\1\124\1\116\1\0\1\120"+
    "\55\0\3\124\26\0\46\125\1\0\1\126\15\125\32\6"+
    "\1\0\1\6\3\0\1\6\1\0\4\6\3\0\3\6"+
    "\1\127\1\130\6\6\2\0\32\40\1\131\1\40\3\131"+
    "\1\40\1\131\4\40\1\131\1\0\1\131\13\40\2\131"+
    "\51\0\1\47\66\0\1\53\62\0\1\53\13\0\46\131"+
    "\1\0\7\131\1\132\6\131\1\6\2\10\1\133\26\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\3\0\31\62\1\0\5\62\1\0\1\62"+
    "\1\0\2\62\20\0\1\6\1\134\30\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\21\10\1\135\7\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\1\136\16\10\1\137\3\10\1\140\5\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\4\10\1\141\11\10\1\142\12\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\21\10\1\143\7\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\3\10\1\144\24\10\1\145\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\2\10\1\146\1\147\25\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\1\150\30\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\2\10\1\151\26\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\3\10\1\152\25\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\12\10"+
    "\1\153\16\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\10\10\1\154"+
    "\20\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\4\10\1\155\24\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\15\10\1\156\13\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\24\10\1\157\4\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\6\10\1\160\22\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\22\10\1\161\6\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\10\10\1\162\20\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\21\10"+
    "\1\163\7\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\6\10\1\164"+
    "\22\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\3\10\1\165\25\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\5\10\1\166\23\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\13\10\1\167\2\10\1\170\12\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\16\10\1\171\12\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\4\10\1\172\3\10\1\173\20\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\36\0\3\174\3\0\1\175\23\0\2\176"+
    "\3\0\2\176\2\0\2\176\20\0\4\176\1\0\1\176"+
    "\1\0\1\176\30\0\1\116\1\0\1\117\2\120\17\0"+
    "\1\117\1\122\1\123\1\122\1\0\1\124\1\116\1\0"+
    "\1\120\30\0\1\116\2\0\2\120\20\0\3\123\1\0"+
    "\1\124\1\116\1\0\1\120\30\0\1\116\2\0\2\120"+
    "\20\0\3\124\2\0\1\116\1\0\1\120\21\0\1\6"+
    "\3\10\1\177\25\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\10\10"+
    "\1\200\20\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\6\10\1\201"+
    "\22\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\16\10\1\202\12\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\6\10\1\203\22\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\13\10\1\204\15\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\2\10\1\205\26\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\6\10\1\206\22\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\23\10\1\207\5\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\23\10"+
    "\1\210\5\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\6\10\1\211"+
    "\22\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\5\10\1\212\23\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\2\10\1\213\26\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\6\10\1\214\22\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\6\10\1\215\22\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\2\10\1\216\26\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\1\217\30\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\6\10\1\220"+
    "\22\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\4\10\1\221\24\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\10\10\1\222\20\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\10\10\1\223\2\10\1\224\15\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\10\10\1\225\20\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\5\10\1\226\23\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\26\10\1\227\2\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\3\10\1\230\25\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\25\10\1\231\3\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\3\10"+
    "\1\232\4\10\1\233\20\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\14\0\2\120"+
    "\20\0\3\174\4\0\1\120\55\0\3\174\27\0\2\176"+
    "\3\0\2\176\1\0\1\117\2\176\17\0\1\117\4\176"+
    "\1\0\1\176\1\0\1\176\21\0\1\6\4\10\1\234"+
    "\24\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\6\10\1\235\22\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\4\10\1\236\24\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\3\10\1\237\25\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\22\10\1\240\6\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\4\10\1\241\24\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\16\10\1\242\12\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\7\10"+
    "\1\243\21\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\2\10\1\244"+
    "\26\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\15\10\1\245\13\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\6\10\1\246\22\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\10\10\1\247\20\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\5\10\1\250\23\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\4\10\1\251\24\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\16\10\1\252\12\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\4\10"+
    "\1\253\24\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\11\10\1\254"+
    "\17\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\7\10\1\255\21\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\1\256\30\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\6\10\1\257\22\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\1\260\30\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\7\10"+
    "\1\261\21\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\6\10\1\262"+
    "\22\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\1\263\30\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\11\10\1\264\17\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\15\10\1\265\13\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\4\10\1\266\24\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\11\10\1\267\17\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\10\10"+
    "\1\270\20\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\3\10\1\271"+
    "\25\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\16\10\1\272\12\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\5\10\1\273\23\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\3\10\1\274\25\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\27\10\1\275\1\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\5\10\1\276\23\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\3\10\1\277\25\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\5\10"+
    "\1\300\23\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\6\10\1\301"+
    "\22\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\2\10\1\302\26\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\17\10\1\303\11\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\11\10\1\304\17\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\16\10\1\305\12\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\6\10\1\306\22\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0\1\6"+
    "\3\10\1\307\25\10\1\0\1\10\3\62\1\10\1\0"+
    "\1\10\1\6\2\10\3\0\13\6\2\0\1\6\6\10"+
    "\1\310\22\10\1\0\1\10\3\62\1\10\1\0\1\10"+
    "\1\6\2\10\3\0\13\6\2\0\1\6\3\10\1\311"+
    "\25\10\1\0\1\10\3\62\1\10\1\0\1\10\1\6"+
    "\2\10\3\0\13\6\2\0\1\6\2\10\1\312\26\10"+
    "\1\0\1\10\3\62\1\10\1\0\1\10\1\6\2\10"+
    "\3\0\13\6\2\0\1\6\6\10\1\313\22\10\1\0"+
    "\1\10\3\62\1\10\1\0\1\10\1\6\2\10\3\0"+
    "\13\6\2\0\1\6\3\10\1\314\25\10\1\0\1\10"+
    "\3\62\1\10\1\0\1\10\1\6\2\10\3\0\13\6"+
    "\2\0\1\6\6\10\1\315\22\10\1\0\1\10\3\62"+
    "\1\10\1\0\1\10\1\6\2\10\3\0\13\6\2\0"+
    "\1\6\11\10\1\316\17\10\1\0\1\10\3\62\1\10"+
    "\1\0\1\10\1\6\2\10\3\0\13\6\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7791];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  private static java.io.Reader zzReader = null; // Fake

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\5\0\22\1\1\11\3\1\2\11\7\1\3\11\3\1"+
    "\3\11\1\1\2\11\35\1\1\0\2\11\1\0\1\1"+
    "\1\0\1\1\1\0\3\1\1\0\43\1\1\0\121\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[206];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private IElementType process(IElementType type){
        return type;
    }



  public _ScalaLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public _ScalaLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 144) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int initialState){
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = 0;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = buffer.length();
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = zzLexicalState;


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL.charAt(zzCurrentPosL++);
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL.charAt(zzCurrentPosL++);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 33: 
          { return process(kCASE);
          }
        case 60: break;
        case 32: 
          { return process(kTYPE);
          }
        case 61: break;
        case 51: 
          { return process(kEXTENDS);
          }
        case 62: break;
        case 34: 
          { return process(kELSE);
          }
        case 63: break;
        case 37: 
          { return process(kTRAIT);
          }
        case 64: break;
        case 41: 
          { return process(kFALSE);
          }
        case 65: break;
        case 36: 
          { return process(kSUPER);
          }
        case 66: break;
        case 29: 
          { return process(kVAL);
          }
        case 67: break;
        case 48: 
          { return process(kRETURN);
          }
        case 68: break;
        case 2: 
          { return tWHITE_SPACE_IN_LINE;
          }
        case 69: break;
        case 24: 
          { return process(kTRY);
          }
        case 70: break;
        case 40: 
          { return process(kCLASS);
          }
        case 71: break;
        case 4: 
          { return tSTUB;
          }
        case 72: break;
        case 17: 
          { yybegin(IN_XML_STATE);
                                            return process(tENDSCALAEXPR);
          }
        case 73: break;
        case 31: 
          { return process(kTHIS);
          }
        case 74: break;
        case 55: 
          { return process(kABSTRACT);
          }
        case 75: break;
        case 18: 
          { return process(kDO);
          }
        case 76: break;
        case 49: 
          { return process(kOBJECT);
          }
        case 77: break;
        case 15: 
          { yybegin(YYINITIAL);
                                            return process(tSTRING);
          }
        case 78: break;
        case 9: 
          { return process(tRBRACE);
          }
        case 79: break;
        case 3: 
          { return process(tINTEGER);
          }
        case 80: break;
        case 13: 
          { yybegin(YYINITIAL);
                                            return process(tCOMMENT);
          }
        case 81: break;
        case 19: 
          { return process(kIF);
          }
        case 82: break;
        case 50: 
          { return process(kIMPORT);
          }
        case 83: break;
        case 23: 
          { yybegin(IN_XML_STATE);
                                            return process(tOPENXMLTAG);
          }
        case 84: break;
        case 52: 
          { return process(kFINALLY);
          }
        case 85: break;
        case 38: 
          { return process(kTHROW);
          }
        case 86: break;
        case 25: 
          { return process(kDEF);
          }
        case 87: break;
        case 53: 
          { return process(kPACKAGE);
          }
        case 88: break;
        case 43: 
          { return process(kYIELD);
          }
        case 89: break;
        case 10: 
          { return process(tLPARENTHIS);
          }
        case 90: break;
        case 28: 
          { return process(kVAR);
          }
        case 91: break;
        case 42: 
          { return process(kFINAL);
          }
        case 92: break;
        case 14: 
          { return process(tSTRING);
          }
        case 93: break;
        case 57: 
          { return process(kOVERRIDE);
          }
        case 94: break;
        case 56: 
          { return process(kREQUIRES);
          }
        case 95: break;
        case 20: 
          { return process(tFLOAT);
          }
        case 96: break;
        case 35: 
          { return process(kNULL);
          }
        case 97: break;
        case 5: 
          { yybegin(IN_STRING_STATE);
                                            return process(tSTRING);
          }
        case 98: break;
        case 26: 
          { return process(kFOR);
          }
        case 99: break;
        case 58: 
          { return process(kIMPLICIT);
          }
        case 100: break;
        case 45: 
          { return process(kWHITH);
          }
        case 101: break;
        case 11: 
          { return process(tRPARENTHIS);
          }
        case 102: break;
        case 6: 
          { return process(tLBRACKET);
          }
        case 103: break;
        case 47: 
          { return process(kSEALED);
          }
        case 104: break;
        case 27: 
          { return process(kNEW);
          }
        case 105: break;
        case 46: 
          { return process(kWHILE);
          }
        case 106: break;
        case 16: 
          { yybegin(YYINITIAL);
                                            return process(tBEGINSCALAEXPR);
          }
        case 107: break;
        case 21: 
          { yybegin(IN_LINE_COMMENT_STATE);
                                            return process(tCOMMENT);
          }
        case 108: break;
        case 54: 
          { return process(kPRIVATE);
          }
        case 109: break;
        case 59: 
          { return process(kPROTECTED);
          }
        case 110: break;
        case 8: 
          { return process(tLBRACE);
          }
        case 111: break;
        case 1: 
          { return process(tIDENTIFIER);
          }
        case 112: break;
        case 7: 
          { return process(tRBRACKET);
          }
        case 113: break;
        case 12: 
          { return process(tCOMMENT);
          }
        case 114: break;
        case 22: 
          { yybegin(IN_BLOCK_COMMENT_STATE);
                                            return process(tCOMMENT);
          }
        case 115: break;
        case 39: 
          { return process(kCATCH);
          }
        case 116: break;
        case 30: 
          { return process(kTRUE);
          }
        case 117: break;
        case 44: 
          { return process(kMATCH);
          }
        case 118: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
