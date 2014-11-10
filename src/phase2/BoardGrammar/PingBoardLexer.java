package phase2.BoardGrammar;

// Generated from BoardGrammar/PingBoard.g4 by ANTLR 4.4
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
		BOARDINITID=1, COMMENT=2, ORIENTATIONVALUE=3, EQUALS=4, NAMEID=5, XID=6, 
		YID=7, ORIENTATIONID=8, WIDTHID=9, HEIGHTID=10, XVELOCITYID=11, YVELOCITYID=12, 
		GRAVITYID=13, FRICTION1ID=14, FRICTION2ID=15, TRIGGERID=16, ACTIONID=17, 
		BALLID=18, SQUAREBUMPERID=19, CIRCLEBUMPERID=20, TRIANGLEBUMPERID=21, 
		RIGHTFLIPPERID=22, LEFTFLIPPERID=23, ABSORBERID=24, FIREID=25, NAME=26, 
		INTEGER=27, FLOAT=28, WHITESPACE=29, NEWLINE=30;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'"
	};
	public static final String[] ruleNames = {
		"BOARDINITID", "COMMENT", "ORIENTATIONVALUE", "EQUALS", "NAMEID", "XID", 
		"YID", "ORIENTATIONID", "WIDTHID", "HEIGHTID", "XVELOCITYID", "YVELOCITYID", 
		"GRAVITYID", "FRICTION1ID", "FRICTION2ID", "TRIGGERID", "ACTIONID", "BALLID", 
		"SQUAREBUMPERID", "CIRCLEBUMPERID", "TRIANGLEBUMPERID", "RIGHTFLIPPERID", 
		"LEFTFLIPPERID", "ABSORBERID", "FIREID", "NAME", "INTEGER", "FLOAT", "WHITESPACE", 
		"NEWLINE"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2 \u0136\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\7\3H\n\3\f\3\16\3K\13\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\5\4V\n\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\7\33"+
		"\u0112\n\33\f\33\16\33\u0115\13\33\3\34\6\34\u0118\n\34\r\34\16\34\u0119"+
		"\3\35\5\35\u011d\n\35\3\35\7\35\u0120\n\35\f\35\16\35\u0123\13\35\3\35"+
		"\5\35\u0126\n\35\3\35\6\35\u0129\n\35\r\35\16\35\u012a\3\36\6\36\u012e"+
		"\n\36\r\36\16\36\u012f\3\37\6\37\u0133\n\37\r\37\16\37\u0134\2\2 \3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= \3"+
		"\2\n\3\2\"\u0080\5\2C\\aac|\6\2\62;C\\aac|\3\2//\3\2\62;\3\2\60\60\4\2"+
		"\13\13\"\"\3\2\f\f\u0141\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3\2\2\2\5E\3\2\2\2\7U\3\2\2"+
		"\2\tW\3\2\2\2\13Y\3\2\2\2\r^\3\2\2\2\17`\3\2\2\2\21b\3\2\2\2\23n\3\2\2"+
		"\2\25t\3\2\2\2\27{\3\2\2\2\31\u0085\3\2\2\2\33\u008f\3\2\2\2\35\u0097"+
		"\3\2\2\2\37\u00a1\3\2\2\2!\u00ab\3\2\2\2#\u00b3\3\2\2\2%\u00ba\3\2\2\2"+
		"\'\u00bf\3\2\2\2)\u00cc\3\2\2\2+\u00d9\3\2\2\2-\u00e8\3\2\2\2/\u00f5\3"+
		"\2\2\2\61\u0101\3\2\2\2\63\u010a\3\2\2\2\65\u010f\3\2\2\2\67\u0117\3\2"+
		"\2\29\u011c\3\2\2\2;\u012d\3\2\2\2=\u0132\3\2\2\2?@\7d\2\2@A\7q\2\2AB"+
		"\7c\2\2BC\7t\2\2CD\7f\2\2D\4\3\2\2\2EI\7%\2\2FH\t\2\2\2GF\3\2\2\2HK\3"+
		"\2\2\2IG\3\2\2\2IJ\3\2\2\2J\6\3\2\2\2KI\3\2\2\2LV\7\62\2\2MN\7;\2\2NV"+
		"\7\62\2\2OP\7\63\2\2PQ\7:\2\2QV\7\62\2\2RS\7\64\2\2ST\79\2\2TV\7\62\2"+
		"\2UL\3\2\2\2UM\3\2\2\2UO\3\2\2\2UR\3\2\2\2V\b\3\2\2\2WX\7?\2\2X\n\3\2"+
		"\2\2YZ\7p\2\2Z[\7c\2\2[\\\7o\2\2\\]\7g\2\2]\f\3\2\2\2^_\7z\2\2_\16\3\2"+
		"\2\2`a\7{\2\2a\20\3\2\2\2bc\7q\2\2cd\7t\2\2de\7k\2\2ef\7g\2\2fg\7p\2\2"+
		"gh\7v\2\2hi\7c\2\2ij\7v\2\2jk\7k\2\2kl\7q\2\2lm\7p\2\2m\22\3\2\2\2no\7"+
		"y\2\2op\7k\2\2pq\7f\2\2qr\7v\2\2rs\7j\2\2s\24\3\2\2\2tu\7j\2\2uv\7g\2"+
		"\2vw\7k\2\2wx\7i\2\2xy\7j\2\2yz\7v\2\2z\26\3\2\2\2{|\7z\2\2|}\7X\2\2}"+
		"~\7g\2\2~\177\7n\2\2\177\u0080\7q\2\2\u0080\u0081\7e\2\2\u0081\u0082\7"+
		"k\2\2\u0082\u0083\7v\2\2\u0083\u0084\7{\2\2\u0084\30\3\2\2\2\u0085\u0086"+
		"\7{\2\2\u0086\u0087\7X\2\2\u0087\u0088\7g\2\2\u0088\u0089\7n\2\2\u0089"+
		"\u008a\7q\2\2\u008a\u008b\7e\2\2\u008b\u008c\7k\2\2\u008c\u008d\7v\2\2"+
		"\u008d\u008e\7{\2\2\u008e\32\3\2\2\2\u008f\u0090\7i\2\2\u0090\u0091\7"+
		"t\2\2\u0091\u0092\7c\2\2\u0092\u0093\7x\2\2\u0093\u0094\7k\2\2\u0094\u0095"+
		"\7v\2\2\u0095\u0096\7{\2\2\u0096\34\3\2\2\2\u0097\u0098\7h\2\2\u0098\u0099"+
		"\7t\2\2\u0099\u009a\7k\2\2\u009a\u009b\7e\2\2\u009b\u009c\7v\2\2\u009c"+
		"\u009d\7k\2\2\u009d\u009e\7q\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7\63\2"+
		"\2\u00a0\36\3\2\2\2\u00a1\u00a2\7h\2\2\u00a2\u00a3\7t\2\2\u00a3\u00a4"+
		"\7k\2\2\u00a4\u00a5\7e\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7k\2\2\u00a7"+
		"\u00a8\7q\2\2\u00a8\u00a9\7p\2\2\u00a9\u00aa\7\64\2\2\u00aa \3\2\2\2\u00ab"+
		"\u00ac\7v\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af\7i\2\2"+
		"\u00af\u00b0\7i\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7t\2\2\u00b2\"\3\2"+
		"\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7e\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7"+
		"\7k\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7p\2\2\u00b9$\3\2\2\2\u00ba\u00bb"+
		"\7d\2\2\u00bb\u00bc\7c\2\2\u00bc\u00bd\7n\2\2\u00bd\u00be\7n\2\2\u00be"+
		"&\3\2\2\2\u00bf\u00c0\7u\2\2\u00c0\u00c1\7s\2\2\u00c1\u00c2\7w\2\2\u00c2"+
		"\u00c3\7c\2\2\u00c3\u00c4\7t\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7D\2\2"+
		"\u00c6\u00c7\7w\2\2\u00c7\u00c8\7o\2\2\u00c8\u00c9\7r\2\2\u00c9\u00ca"+
		"\7g\2\2\u00ca\u00cb\7t\2\2\u00cb(\3\2\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce"+
		"\7k\2\2\u00ce\u00cf\7t\2\2\u00cf\u00d0\7e\2\2\u00d0\u00d1\7n\2\2\u00d1"+
		"\u00d2\7g\2\2\u00d2\u00d3\7D\2\2\u00d3\u00d4\7w\2\2\u00d4\u00d5\7o\2\2"+
		"\u00d5\u00d6\7r\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7t\2\2\u00d8*\3\2\2"+
		"\2\u00d9\u00da\7v\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd"+
		"\7c\2\2\u00dd\u00de\7p\2\2\u00de\u00df\7i\2\2\u00df\u00e0\7n\2\2\u00e0"+
		"\u00e1\7g\2\2\u00e1\u00e2\7D\2\2\u00e2\u00e3\7w\2\2\u00e3\u00e4\7o\2\2"+
		"\u00e4\u00e5\7r\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7\7t\2\2\u00e7,\3\2\2"+
		"\2\u00e8\u00e9\7t\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7i\2\2\u00eb\u00ec"+
		"\7j\2\2\u00ec\u00ed\7v\2\2\u00ed\u00ee\7H\2\2\u00ee\u00ef\7n\2\2\u00ef"+
		"\u00f0\7k\2\2\u00f0\u00f1\7r\2\2\u00f1\u00f2\7r\2\2\u00f2\u00f3\7g\2\2"+
		"\u00f3\u00f4\7t\2\2\u00f4.\3\2\2\2\u00f5\u00f6\7n\2\2\u00f6\u00f7\7g\2"+
		"\2\u00f7\u00f8\7h\2\2\u00f8\u00f9\7v\2\2\u00f9\u00fa\7H\2\2\u00fa\u00fb"+
		"\7n\2\2\u00fb\u00fc\7k\2\2\u00fc\u00fd\7r\2\2\u00fd\u00fe\7r\2\2\u00fe"+
		"\u00ff\7g\2\2\u00ff\u0100\7t\2\2\u0100\60\3\2\2\2\u0101\u0102\7c\2\2\u0102"+
		"\u0103\7d\2\2\u0103\u0104\7u\2\2\u0104\u0105\7q\2\2\u0105\u0106\7t\2\2"+
		"\u0106\u0107\7d\2\2\u0107\u0108\7g\2\2\u0108\u0109\7t\2\2\u0109\62\3\2"+
		"\2\2\u010a\u010b\7h\2\2\u010b\u010c\7k\2\2\u010c\u010d\7t\2\2\u010d\u010e"+
		"\7g\2\2\u010e\64\3\2\2\2\u010f\u0113\t\3\2\2\u0110\u0112\t\4\2\2\u0111"+
		"\u0110\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2"+
		"\2\2\u0114\66\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0118\4\62;\2\u0117\u0116"+
		"\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"8\3\2\2\2\u011b\u011d\t\5\2\2\u011c\u011b\3\2\2\2\u011c\u011d\3\2\2\2"+
		"\u011d\u0125\3\2\2\2\u011e\u0120\t\6\2\2\u011f\u011e\3\2\2\2\u0120\u0123"+
		"\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123"+
		"\u0121\3\2\2\2\u0124\u0126\t\7\2\2\u0125\u0121\3\2\2\2\u0125\u0126\3\2"+
		"\2\2\u0126\u0128\3\2\2\2\u0127\u0129\t\6\2\2\u0128\u0127\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b:\3\2\2\2"+
		"\u012c\u012e\t\b\2\2\u012d\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u012d"+
		"\3\2\2\2\u012f\u0130\3\2\2\2\u0130<\3\2\2\2\u0131\u0133\t\t\2\2\u0132"+
		"\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2"+
		"\2\2\u0135>\3\2\2\2\r\2IU\u0113\u0119\u011c\u0121\u0125\u012a\u012f\u0134"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}