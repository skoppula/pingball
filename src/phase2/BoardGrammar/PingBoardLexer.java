//Generated from PingBoard.g4 by ANTLR 4.4
package phase2.BoardGrammar;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;


@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PingBoardLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INTEGER=1, FLOAT=2, NAME=3, ORIENTATIONVALUE=4, COMMENT=5, WHITESPACE=6, 
		EQUALS=7, NAMEID=8, XID=9, YID=10, ORIENTATIONID=11, WIDTHID=12, HEIGHTID=13, 
		XVELOCITYID=14, YVELOCITYID=15, GRAVITYID=16, FRICTION1ID=17, FRICTION2ID=18, 
		TRIGGERID=19, ACTIONID=20, BOARDINITID=21, BALLID=22, SQUAREBUMPERID=23, 
		CIRCLEBUMPERID=24, TRIANGLEBUMPERID=25, RIGHTFLIPPERID=26, LEFTFLIPPERID=27, 
		ABSORBERID=28, FIREID=29;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'"
	};
	public static final String[] ruleNames = {
		"INTEGER", "FLOAT", "NAME", "ORIENTATIONVALUE", "COMMENT", "WHITESPACE", 
		"EQUALS", "NAMEID", "XID", "YID", "ORIENTATIONID", "WIDTHID", "HEIGHTID", 
		"XVELOCITYID", "YVELOCITYID", "GRAVITYID", "FRICTION1ID", "FRICTION2ID", 
		"TRIGGERID", "ACTIONID", "BOARDINITID", "BALLID", "SQUAREBUMPERID", "CIRCLEBUMPERID", 
		"TRIANGLEBUMPERID", "RIGHTFLIPPERID", "LEFTFLIPPERID", "ABSORBERID", "FIREID"
	};


	public PingBoardLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PingBoard.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\37\u012f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\6\2?\n\2\r"+
		"\2\16\2@\3\3\5\3D\n\3\3\3\7\3G\n\3\f\3\16\3J\13\3\3\3\5\3M\n\3\3\3\6\3"+
		"P\n\3\r\3\16\3Q\3\4\3\4\7\4V\n\4\f\4\16\4Y\13\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5d\n\5\3\6\3\6\7\6h\n\6\f\6\16\6k\13\6\3\7\6\7n\n\7\r"+
		"\7\16\7o\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\3\36\2\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37\3\2\b\3\2\62;\3\2//\3\2\60\60\5\2C\\aac|\6"+
		"\2\62;C\\aac|\4\2\13\13\"\"\u0139\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3>\3\2\2\2\5C\3\2\2\2\7S\3\2\2"+
		"\2\tc\3\2\2\2\13e\3\2\2\2\rm\3\2\2\2\17q\3\2\2\2\21s\3\2\2\2\23x\3\2\2"+
		"\2\25z\3\2\2\2\27|\3\2\2\2\31\u0088\3\2\2\2\33\u008e\3\2\2\2\35\u0095"+
		"\3\2\2\2\37\u009f\3\2\2\2!\u00a9\3\2\2\2#\u00b1\3\2\2\2%\u00bb\3\2\2\2"+
		"\'\u00c5\3\2\2\2)\u00cd\3\2\2\2+\u00d4\3\2\2\2-\u00da\3\2\2\2/\u00df\3"+
		"\2\2\2\61\u00ec\3\2\2\2\63\u00f9\3\2\2\2\65\u0108\3\2\2\2\67\u0115\3\2"+
		"\2\29\u0121\3\2\2\2;\u012a\3\2\2\2=?\t\2\2\2>=\3\2\2\2?@\3\2\2\2@>\3\2"+
		"\2\2@A\3\2\2\2A\4\3\2\2\2BD\t\3\2\2CB\3\2\2\2CD\3\2\2\2DL\3\2\2\2EG\t"+
		"\2\2\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KM\t"+
		"\4\2\2LH\3\2\2\2LM\3\2\2\2MO\3\2\2\2NP\t\2\2\2ON\3\2\2\2PQ\3\2\2\2QO\3"+
		"\2\2\2QR\3\2\2\2R\6\3\2\2\2SW\t\5\2\2TV\t\6\2\2UT\3\2\2\2VY\3\2\2\2WU"+
		"\3\2\2\2WX\3\2\2\2X\b\3\2\2\2YW\3\2\2\2Zd\7\62\2\2[\\\7;\2\2\\d\7\62\2"+
		"\2]^\7\63\2\2^_\7:\2\2_d\7\62\2\2`a\7\64\2\2ab\79\2\2bd\7\62\2\2cZ\3\2"+
		"\2\2c[\3\2\2\2c]\3\2\2\2c`\3\2\2\2d\n\3\2\2\2ei\7%\2\2fh\7\60\2\2gf\3"+
		"\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\f\3\2\2\2ki\3\2\2\2ln\t\7\2\2ml"+
		"\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2p\16\3\2\2\2qr\7?\2\2r\20\3\2\2"+
		"\2st\7p\2\2tu\7c\2\2uv\7o\2\2vw\7g\2\2w\22\3\2\2\2xy\7z\2\2y\24\3\2\2"+
		"\2z{\7{\2\2{\26\3\2\2\2|}\7q\2\2}~\7t\2\2~\177\7k\2\2\177\u0080\7g\2\2"+
		"\u0080\u0081\7p\2\2\u0081\u0082\7v\2\2\u0082\u0083\7c\2\2\u0083\u0084"+
		"\7v\2\2\u0084\u0085\7k\2\2\u0085\u0086\7q\2\2\u0086\u0087\7p\2\2\u0087"+
		"\30\3\2\2\2\u0088\u0089\7y\2\2\u0089\u008a\7k\2\2\u008a\u008b\7f\2\2\u008b"+
		"\u008c\7v\2\2\u008c\u008d\7j\2\2\u008d\32\3\2\2\2\u008e\u008f\7j\2\2\u008f"+
		"\u0090\7g\2\2\u0090\u0091\7k\2\2\u0091\u0092\7i\2\2\u0092\u0093\7j\2\2"+
		"\u0093\u0094\7v\2\2\u0094\34\3\2\2\2\u0095\u0096\7z\2\2\u0096\u0097\7"+
		"X\2\2\u0097\u0098\7g\2\2\u0098\u0099\7n\2\2\u0099\u009a\7q\2\2\u009a\u009b"+
		"\7e\2\2\u009b\u009c\7k\2\2\u009c\u009d\7v\2\2\u009d\u009e\7{\2\2\u009e"+
		"\36\3\2\2\2\u009f\u00a0\7{\2\2\u00a0\u00a1\7X\2\2\u00a1\u00a2\7g\2\2\u00a2"+
		"\u00a3\7n\2\2\u00a3\u00a4\7q\2\2\u00a4\u00a5\7e\2\2\u00a5\u00a6\7k\2\2"+
		"\u00a6\u00a7\7v\2\2\u00a7\u00a8\7{\2\2\u00a8 \3\2\2\2\u00a9\u00aa\7i\2"+
		"\2\u00aa\u00ab\7t\2\2\u00ab\u00ac\7c\2\2\u00ac\u00ad\7x\2\2\u00ad\u00ae"+
		"\7k\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7{\2\2\u00b0\"\3\2\2\2\u00b1\u00b2"+
		"\7h\2\2\u00b2\u00b3\7t\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7e\2\2\u00b5"+
		"\u00b6\7v\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7p\2\2"+
		"\u00b9\u00ba\7\63\2\2\u00ba$\3\2\2\2\u00bb\u00bc\7h\2\2\u00bc\u00bd\7"+
		"t\2\2\u00bd\u00be\7k\2\2\u00be\u00bf\7e\2\2\u00bf\u00c0\7v\2\2\u00c0\u00c1"+
		"\7k\2\2\u00c1\u00c2\7q\2\2\u00c2\u00c3\7p\2\2\u00c3\u00c4\7\64\2\2\u00c4"+
		"&\3\2\2\2\u00c5\u00c6\7v\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7k\2\2\u00c8"+
		"\u00c9\7i\2\2\u00c9\u00ca\7i\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7t\2\2"+
		"\u00cc(\3\2\2\2\u00cd\u00ce\7c\2\2\u00ce\u00cf\7e\2\2\u00cf\u00d0\7v\2"+
		"\2\u00d0\u00d1\7k\2\2\u00d1\u00d2\7q\2\2\u00d2\u00d3\7p\2\2\u00d3*\3\2"+
		"\2\2\u00d4\u00d5\7d\2\2\u00d5\u00d6\7q\2\2\u00d6\u00d7\7c\2\2\u00d7\u00d8"+
		"\7t\2\2\u00d8\u00d9\7f\2\2\u00d9,\3\2\2\2\u00da\u00db\7d\2\2\u00db\u00dc"+
		"\7c\2\2\u00dc\u00dd\7n\2\2\u00dd\u00de\7n\2\2\u00de.\3\2\2\2\u00df\u00e0"+
		"\7u\2\2\u00e0\u00e1\7s\2\2\u00e1\u00e2\7w\2\2\u00e2\u00e3\7c\2\2\u00e3"+
		"\u00e4\7t\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6\7D\2\2\u00e6\u00e7\7w\2\2"+
		"\u00e7\u00e8\7o\2\2\u00e8\u00e9\7r\2\2\u00e9\u00ea\7g\2\2\u00ea\u00eb"+
		"\7t\2\2\u00eb\60\3\2\2\2\u00ec\u00ed\7e\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef"+
		"\7t\2\2\u00ef\u00f0\7e\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7g\2\2\u00f2"+
		"\u00f3\7D\2\2\u00f3\u00f4\7w\2\2\u00f4\u00f5\7o\2\2\u00f5\u00f6\7r\2\2"+
		"\u00f6\u00f7\7g\2\2\u00f7\u00f8\7t\2\2\u00f8\62\3\2\2\2\u00f9\u00fa\7"+
		"v\2\2\u00fa\u00fb\7t\2\2\u00fb\u00fc\7k\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe"+
		"\7p\2\2\u00fe\u00ff\7i\2\2\u00ff\u0100\7n\2\2\u0100\u0101\7g\2\2\u0101"+
		"\u0102\7D\2\2\u0102\u0103\7w\2\2\u0103\u0104\7o\2\2\u0104\u0105\7r\2\2"+
		"\u0105\u0106\7g\2\2\u0106\u0107\7t\2\2\u0107\64\3\2\2\2\u0108\u0109\7"+
		"t\2\2\u0109\u010a\7k\2\2\u010a\u010b\7i\2\2\u010b\u010c\7j\2\2\u010c\u010d"+
		"\7v\2\2\u010d\u010e\7H\2\2\u010e\u010f\7n\2\2\u010f\u0110\7k\2\2\u0110"+
		"\u0111\7r\2\2\u0111\u0112\7r\2\2\u0112\u0113\7g\2\2\u0113\u0114\7t\2\2"+
		"\u0114\66\3\2\2\2\u0115\u0116\7n\2\2\u0116\u0117\7g\2\2\u0117\u0118\7"+
		"h\2\2\u0118\u0119\7v\2\2\u0119\u011a\7H\2\2\u011a\u011b\7n\2\2\u011b\u011c"+
		"\7k\2\2\u011c\u011d\7r\2\2\u011d\u011e\7r\2\2\u011e\u011f\7g\2\2\u011f"+
		"\u0120\7t\2\2\u01208\3\2\2\2\u0121\u0122\7c\2\2\u0122\u0123\7d\2\2\u0123"+
		"\u0124\7u\2\2\u0124\u0125\7q\2\2\u0125\u0126\7t\2\2\u0126\u0127\7d\2\2"+
		"\u0127\u0128\7g\2\2\u0128\u0129\7t\2\2\u0129:\3\2\2\2\u012a\u012b\7h\2"+
		"\2\u012b\u012c\7k\2\2\u012c\u012d\7t\2\2\u012d\u012e\7g\2\2\u012e<\3\2"+
		"\2\2\f\2@CHLQWcio\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}