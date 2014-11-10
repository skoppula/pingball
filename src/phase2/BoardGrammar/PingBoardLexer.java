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
		BOARDINITID=1, COMMENT=2, EQUALS=3, NAMEID=4, XID=5, YID=6, ORIENTATIONID=7, 
		WIDTHID=8, HEIGHTID=9, XVELOCITYID=10, YVELOCITYID=11, GRAVITYID=12, FRICTION1ID=13, 
		FRICTION2ID=14, TRIGGERID=15, ACTIONID=16, BALLID=17, SQUAREBUMPERID=18, 
		CIRCLEBUMPERID=19, TRIANGLEBUMPERID=20, RIGHTFLIPPERID=21, LEFTFLIPPERID=22, 
		ABSORBERID=23, FIREID=24, NAME=25, INTEGER=26, FLOAT=27, WHITESPACE=28, 
		NEWLINE=29;
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
		"BOARDINITID", "COMMENT", "EQUALS", "NAMEID", "XID", "YID", "ORIENTATIONID", 
		"WIDTHID", "HEIGHTID", "XVELOCITYID", "YVELOCITYID", "GRAVITYID", "FRICTION1ID", 
		"FRICTION2ID", "TRIGGERID", "ACTIONID", "BALLID", "SQUAREBUMPERID", "CIRCLEBUMPERID", 
		"TRIANGLEBUMPERID", "RIGHTFLIPPERID", "LEFTFLIPPERID", "ABSORBERID", "FIREID", 
		"NAME", "INTEGER", "FLOAT", "WHITESPACE", "NEWLINE"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\37\u0129\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\7\3F\n\3\f\3\16\3I\13\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3"+
		"\32\3\32\7\32\u0105\n\32\f\32\16\32\u0108\13\32\3\33\6\33\u010b\n\33\r"+
		"\33\16\33\u010c\3\34\5\34\u0110\n\34\3\34\7\34\u0113\n\34\f\34\16\34\u0116"+
		"\13\34\3\34\5\34\u0119\n\34\3\34\6\34\u011c\n\34\r\34\16\34\u011d\3\35"+
		"\6\35\u0121\n\35\r\35\16\35\u0122\3\36\6\36\u0126\n\36\r\36\16\36\u0127"+
		"\2\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37\3\2\n\3\2\"\u0080\5\2C\\aac|\6\2\62;C\\aac|\3\2//\3\2\62;\3\2"+
		"\60\60\4\2\13\13\"\"\3\2\f\f\u0131\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5C\3\2\2\2\7J\3\2\2"+
		"\2\tL\3\2\2\2\13Q\3\2\2\2\rS\3\2\2\2\17U\3\2\2\2\21a\3\2\2\2\23g\3\2\2"+
		"\2\25n\3\2\2\2\27x\3\2\2\2\31\u0082\3\2\2\2\33\u008a\3\2\2\2\35\u0094"+
		"\3\2\2\2\37\u009e\3\2\2\2!\u00a6\3\2\2\2#\u00ad\3\2\2\2%\u00b2\3\2\2\2"+
		"\'\u00bf\3\2\2\2)\u00cc\3\2\2\2+\u00db\3\2\2\2-\u00e8\3\2\2\2/\u00f4\3"+
		"\2\2\2\61\u00fd\3\2\2\2\63\u0102\3\2\2\2\65\u010a\3\2\2\2\67\u010f\3\2"+
		"\2\29\u0120\3\2\2\2;\u0125\3\2\2\2=>\7d\2\2>?\7q\2\2?@\7c\2\2@A\7t\2\2"+
		"AB\7f\2\2B\4\3\2\2\2CG\7%\2\2DF\t\2\2\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2\2"+
		"GH\3\2\2\2H\6\3\2\2\2IG\3\2\2\2JK\7?\2\2K\b\3\2\2\2LM\7p\2\2MN\7c\2\2"+
		"NO\7o\2\2OP\7g\2\2P\n\3\2\2\2QR\7z\2\2R\f\3\2\2\2ST\7{\2\2T\16\3\2\2\2"+
		"UV\7q\2\2VW\7t\2\2WX\7k\2\2XY\7g\2\2YZ\7p\2\2Z[\7v\2\2[\\\7c\2\2\\]\7"+
		"v\2\2]^\7k\2\2^_\7q\2\2_`\7p\2\2`\20\3\2\2\2ab\7y\2\2bc\7k\2\2cd\7f\2"+
		"\2de\7v\2\2ef\7j\2\2f\22\3\2\2\2gh\7j\2\2hi\7g\2\2ij\7k\2\2jk\7i\2\2k"+
		"l\7j\2\2lm\7v\2\2m\24\3\2\2\2no\7z\2\2op\7X\2\2pq\7g\2\2qr\7n\2\2rs\7"+
		"q\2\2st\7e\2\2tu\7k\2\2uv\7v\2\2vw\7{\2\2w\26\3\2\2\2xy\7{\2\2yz\7X\2"+
		"\2z{\7g\2\2{|\7n\2\2|}\7q\2\2}~\7e\2\2~\177\7k\2\2\177\u0080\7v\2\2\u0080"+
		"\u0081\7{\2\2\u0081\30\3\2\2\2\u0082\u0083\7i\2\2\u0083\u0084\7t\2\2\u0084"+
		"\u0085\7c\2\2\u0085\u0086\7x\2\2\u0086\u0087\7k\2\2\u0087\u0088\7v\2\2"+
		"\u0088\u0089\7{\2\2\u0089\32\3\2\2\2\u008a\u008b\7h\2\2\u008b\u008c\7"+
		"t\2\2\u008c\u008d\7k\2\2\u008d\u008e\7e\2\2\u008e\u008f\7v\2\2\u008f\u0090"+
		"\7k\2\2\u0090\u0091\7q\2\2\u0091\u0092\7p\2\2\u0092\u0093\7\63\2\2\u0093"+
		"\34\3\2\2\2\u0094\u0095\7h\2\2\u0095\u0096\7t\2\2\u0096\u0097\7k\2\2\u0097"+
		"\u0098\7e\2\2\u0098\u0099\7v\2\2\u0099\u009a\7k\2\2\u009a\u009b\7q\2\2"+
		"\u009b\u009c\7p\2\2\u009c\u009d\7\64\2\2\u009d\36\3\2\2\2\u009e\u009f"+
		"\7v\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7i\2\2\u00a2"+
		"\u00a3\7i\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5\7t\2\2\u00a5 \3\2\2\2\u00a6"+
		"\u00a7\7c\2\2\u00a7\u00a8\7e\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa\7k\2\2"+
		"\u00aa\u00ab\7q\2\2\u00ab\u00ac\7p\2\2\u00ac\"\3\2\2\2\u00ad\u00ae\7d"+
		"\2\2\u00ae\u00af\7c\2\2\u00af\u00b0\7n\2\2\u00b0\u00b1\7n\2\2\u00b1$\3"+
		"\2\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b4\7s\2\2\u00b4\u00b5\7w\2\2\u00b5"+
		"\u00b6\7c\2\2\u00b6\u00b7\7t\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7D\2\2"+
		"\u00b9\u00ba\7w\2\2\u00ba\u00bb\7o\2\2\u00bb\u00bc\7r\2\2\u00bc\u00bd"+
		"\7g\2\2\u00bd\u00be\7t\2\2\u00be&\3\2\2\2\u00bf\u00c0\7e\2\2\u00c0\u00c1"+
		"\7k\2\2\u00c1\u00c2\7t\2\2\u00c2\u00c3\7e\2\2\u00c3\u00c4\7n\2\2\u00c4"+
		"\u00c5\7g\2\2\u00c5\u00c6\7D\2\2\u00c6\u00c7\7w\2\2\u00c7\u00c8\7o\2\2"+
		"\u00c8\u00c9\7r\2\2\u00c9\u00ca\7g\2\2\u00ca\u00cb\7t\2\2\u00cb(\3\2\2"+
		"\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7t\2\2\u00ce\u00cf\7k\2\2\u00cf\u00d0"+
		"\7c\2\2\u00d0\u00d1\7p\2\2\u00d1\u00d2\7i\2\2\u00d2\u00d3\7n\2\2\u00d3"+
		"\u00d4\7g\2\2\u00d4\u00d5\7D\2\2\u00d5\u00d6\7w\2\2\u00d6\u00d7\7o\2\2"+
		"\u00d7\u00d8\7r\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7t\2\2\u00da*\3\2\2"+
		"\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7k\2\2\u00dd\u00de\7i\2\2\u00de\u00df"+
		"\7j\2\2\u00df\u00e0\7v\2\2\u00e0\u00e1\7H\2\2\u00e1\u00e2\7n\2\2\u00e2"+
		"\u00e3\7k\2\2\u00e3\u00e4\7r\2\2\u00e4\u00e5\7r\2\2\u00e5\u00e6\7g\2\2"+
		"\u00e6\u00e7\7t\2\2\u00e7,\3\2\2\2\u00e8\u00e9\7n\2\2\u00e9\u00ea\7g\2"+
		"\2\u00ea\u00eb\7h\2\2\u00eb\u00ec\7v\2\2\u00ec\u00ed\7H\2\2\u00ed\u00ee"+
		"\7n\2\2\u00ee\u00ef\7k\2\2\u00ef\u00f0\7r\2\2\u00f0\u00f1\7r\2\2\u00f1"+
		"\u00f2\7g\2\2\u00f2\u00f3\7t\2\2\u00f3.\3\2\2\2\u00f4\u00f5\7c\2\2\u00f5"+
		"\u00f6\7d\2\2\u00f6\u00f7\7u\2\2\u00f7\u00f8\7q\2\2\u00f8\u00f9\7t\2\2"+
		"\u00f9\u00fa\7d\2\2\u00fa\u00fb\7g\2\2\u00fb\u00fc\7t\2\2\u00fc\60\3\2"+
		"\2\2\u00fd\u00fe\7h\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7t\2\2\u0100\u0101"+
		"\7g\2\2\u0101\62\3\2\2\2\u0102\u0106\t\3\2\2\u0103\u0105\t\4\2\2\u0104"+
		"\u0103\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2"+
		"\2\2\u0107\64\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u010b\4\62;\2\u010a\u0109"+
		"\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\66\3\2\2\2\u010e\u0110\t\5\2\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2"+
		"\2\u0110\u0118\3\2\2\2\u0111\u0113\t\6\2\2\u0112\u0111\3\2\2\2\u0113\u0116"+
		"\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0117\3\2\2\2\u0116"+
		"\u0114\3\2\2\2\u0117\u0119\t\7\2\2\u0118\u0114\3\2\2\2\u0118\u0119\3\2"+
		"\2\2\u0119\u011b\3\2\2\2\u011a\u011c\t\6\2\2\u011b\u011a\3\2\2\2\u011c"+
		"\u011d\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e8\3\2\2\2"+
		"\u011f\u0121\t\b\2\2\u0120\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0120"+
		"\3\2\2\2\u0122\u0123\3\2\2\2\u0123:\3\2\2\2\u0124\u0126\t\t\2\2\u0125"+
		"\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2"+
		"\2\2\u0128<\3\2\2\2\f\2G\u0106\u010c\u010f\u0114\u0118\u011d\u0122\u0127"+
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