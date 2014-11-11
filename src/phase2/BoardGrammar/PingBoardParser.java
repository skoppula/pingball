package phase2.BoardGrammar;

// Generated from BoardGrammar/PingBoard.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PingBoardParser extends Parser {
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
	public static final String[] tokenNames = {
		"<INVALID>", "'board'", "COMMENT", "'='", "'name'", "'x'", "'y'", "'orientation'", 
		"'width'", "'height'", "'xVelocity'", "'yVelocity'", "'gravity'", "'friction1'", 
		"'friction2'", "'trigger'", "'action'", "'ball'", "'squareBumper'", "'circleBumper'", 
		"'triangleBumper'", "'rightFlipper'", "'leftFlipper'", "'absorber'", "'fire'", 
		"NAME", "INTEGER", "FLOAT", "WHITESPACE", "NEWLINE"
	};
	public static final int
		RULE_root = 0, RULE_board = 1, RULE_boardInit = 2, RULE_bodyLine = 3, 
		RULE_ball = 4, RULE_squareBumper = 5, RULE_circleBumper = 6, RULE_triangleBumper = 7, 
		RULE_flipper = 8, RULE_absorber = 9, RULE_fire = 10, RULE_whitespace = 11, 
		RULE_newline = 12, RULE_comment = 13, RULE_equals = 14, RULE_name = 15, 
		RULE_gravity = 16, RULE_friction1 = 17, RULE_friction2 = 18, RULE_intX = 19, 
		RULE_intY = 20, RULE_orientation = 21, RULE_width = 22, RULE_height = 23, 
		RULE_floatX = 24, RULE_floatY = 25, RULE_xVelocity = 26, RULE_yVelocity = 27, 
		RULE_trigger = 28, RULE_action = 29;
	public static final String[] ruleNames = {
		"root", "board", "boardInit", "bodyLine", "ball", "squareBumper", "circleBumper", 
		"triangleBumper", "flipper", "absorber", "fire", "whitespace", "newline", 
		"comment", "equals", "name", "gravity", "friction1", "friction2", "intX", 
		"intY", "orientation", "width", "height", "floatX", "floatY", "xVelocity", 
		"yVelocity", "trigger", "action"
	};

	@Override
	public String getGrammarFileName() { return "PingBoard.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PingBoardParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PingBoardParser.EOF, 0); }
		public BoardContext board() {
			return getRuleContext(BoardContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); board();
			setState(61); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardContext extends ParserRuleContext {
		public BodyLineContext bodyLine(int i) {
			return getRuleContext(BodyLineContext.class,i);
		}
		public BoardInitContext boardInit() {
			return getRuleContext(BoardInitContext.class,0);
		}
		public List<BodyLineContext> bodyLine() {
			return getRuleContexts(BodyLineContext.class);
		}
		public BoardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_board; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterBoard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitBoard(this);
		}
	}

	public final BoardContext board() throws RecognitionException {
		BoardContext _localctx = new BoardContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_board);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); boardInit();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << BALLID) | (1L << SQUAREBUMPERID) | (1L << CIRCLEBUMPERID) | (1L << TRIANGLEBUMPERID) | (1L << RIGHTFLIPPERID) | (1L << LEFTFLIPPERID) | (1L << ABSORBERID) | (1L << FIREID) | (1L << WHITESPACE) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(64); bodyLine();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardInitContext extends ParserRuleContext {
		public Friction1Context friction1() {
			return getRuleContext(Friction1Context.class,0);
		}
		public Friction2Context friction2() {
			return getRuleContext(Friction2Context.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode BOARDINITID() { return getToken(PingBoardParser.BOARDINITID, 0); }
		public GravityContext gravity() {
			return getRuleContext(GravityContext.class,0);
		}
		public BoardInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterBoardInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitBoardInit(this);
		}
	}

	public final BoardInitContext boardInit() throws RecognitionException {
		BoardInitContext _localctx = new BoardInitContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_boardInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); match(BOARDINITID);
			setState(71); name();
			setState(73);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(72); gravity();
				}
				break;
			}
			setState(76);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(75); friction1();
				}
				break;
			}
			setState(79);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(78); friction2();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyLineContext extends ParserRuleContext {
		public TriangleBumperContext triangleBumper() {
			return getRuleContext(TriangleBumperContext.class,0);
		}
		public AbsorberContext absorber() {
			return getRuleContext(AbsorberContext.class,0);
		}
		public BallContext ball() {
			return getRuleContext(BallContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public FlipperContext flipper() {
			return getRuleContext(FlipperContext.class,0);
		}
		public SquareBumperContext squareBumper() {
			return getRuleContext(SquareBumperContext.class,0);
		}
		public FireContext fire() {
			return getRuleContext(FireContext.class,0);
		}
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public NewlineContext newline() {
			return getRuleContext(NewlineContext.class,0);
		}
		public CircleBumperContext circleBumper() {
			return getRuleContext(CircleBumperContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public BodyLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bodyLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterBodyLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitBodyLine(this);
		}
	}

	public final BodyLineContext bodyLine() throws RecognitionException {
		BodyLineContext _localctx = new BodyLineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_bodyLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(81); match(WHITESPACE);
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
			switch (_input.LA(1)) {
			case BALLID:
				{
				setState(87); ball();
				}
				break;
			case SQUAREBUMPERID:
				{
				setState(88); squareBumper();
				}
				break;
			case CIRCLEBUMPERID:
				{
				setState(89); circleBumper();
				}
				break;
			case TRIANGLEBUMPERID:
				{
				setState(90); triangleBumper();
				}
				break;
			case RIGHTFLIPPERID:
			case LEFTFLIPPERID:
				{
				setState(91); flipper();
				}
				break;
			case ABSORBERID:
				{
				setState(92); absorber();
				}
				break;
			case FIREID:
				{
				setState(93); fire();
				}
				break;
			case COMMENT:
				{
				setState(94); comment();
				}
				break;
			case NEWLINE:
				{
				setState(95); newline();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BallContext extends ParserRuleContext {
		public YVelocityContext yVelocity() {
			return getRuleContext(YVelocityContext.class,0);
		}
		public FloatYContext floatY() {
			return getRuleContext(FloatYContext.class,0);
		}
		public FloatXContext floatX() {
			return getRuleContext(FloatXContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode BALLID() { return getToken(PingBoardParser.BALLID, 0); }
		public XVelocityContext xVelocity() {
			return getRuleContext(XVelocityContext.class,0);
		}
		public BallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ball; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterBall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitBall(this);
		}
	}

	public final BallContext ball() throws RecognitionException {
		BallContext _localctx = new BallContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ball);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); match(BALLID);
			setState(99); name();
			setState(100); floatX();
			setState(101); floatY();
			setState(102); xVelocity();
			setState(103); yVelocity();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SquareBumperContext extends ParserRuleContext {
		public TerminalNode SQUAREBUMPERID() { return getToken(PingBoardParser.SQUAREBUMPERID, 0); }
		public IntXContext intX() {
			return getRuleContext(IntXContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public IntYContext intY() {
			return getRuleContext(IntYContext.class,0);
		}
		public SquareBumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squareBumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterSquareBumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitSquareBumper(this);
		}
	}

	public final SquareBumperContext squareBumper() throws RecognitionException {
		SquareBumperContext _localctx = new SquareBumperContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_squareBumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); match(SQUAREBUMPERID);
			setState(106); name();
			setState(107); intX();
			setState(108); intY();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CircleBumperContext extends ParserRuleContext {
		public IntXContext intX() {
			return getRuleContext(IntXContext.class,0);
		}
		public TerminalNode CIRCLEBUMPERID() { return getToken(PingBoardParser.CIRCLEBUMPERID, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public IntYContext intY() {
			return getRuleContext(IntYContext.class,0);
		}
		public CircleBumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circleBumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterCircleBumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitCircleBumper(this);
		}
	}

	public final CircleBumperContext circleBumper() throws RecognitionException {
		CircleBumperContext _localctx = new CircleBumperContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_circleBumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(CIRCLEBUMPERID);
			setState(111); name();
			setState(112); intX();
			setState(113); intY();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriangleBumperContext extends ParserRuleContext {
		public OrientationContext orientation() {
			return getRuleContext(OrientationContext.class,0);
		}
		public TerminalNode TRIANGLEBUMPERID() { return getToken(PingBoardParser.TRIANGLEBUMPERID, 0); }
		public IntXContext intX() {
			return getRuleContext(IntXContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public IntYContext intY() {
			return getRuleContext(IntYContext.class,0);
		}
		public TriangleBumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triangleBumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterTriangleBumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitTriangleBumper(this);
		}
	}

	public final TriangleBumperContext triangleBumper() throws RecognitionException {
		TriangleBumperContext _localctx = new TriangleBumperContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_triangleBumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(TRIANGLEBUMPERID);
			setState(116); name();
			setState(117); intX();
			setState(118); intY();
			setState(119); orientation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlipperContext extends ParserRuleContext {
		public TerminalNode LEFTFLIPPERID() { return getToken(PingBoardParser.LEFTFLIPPERID, 0); }
		public OrientationContext orientation() {
			return getRuleContext(OrientationContext.class,0);
		}
		public TerminalNode RIGHTFLIPPERID() { return getToken(PingBoardParser.RIGHTFLIPPERID, 0); }
		public IntXContext intX() {
			return getRuleContext(IntXContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public IntYContext intY() {
			return getRuleContext(IntYContext.class,0);
		}
		public FlipperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flipper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterFlipper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitFlipper(this);
		}
	}

	public final FlipperContext flipper() throws RecognitionException {
		FlipperContext _localctx = new FlipperContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_flipper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_la = _input.LA(1);
			if ( !(_la==RIGHTFLIPPERID || _la==LEFTFLIPPERID) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(122); name();
			setState(123); intX();
			setState(124); intY();
			setState(125); orientation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbsorberContext extends ParserRuleContext {
		public WidthContext width() {
			return getRuleContext(WidthContext.class,0);
		}
		public IntXContext intX() {
			return getRuleContext(IntXContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public IntYContext intY() {
			return getRuleContext(IntYContext.class,0);
		}
		public TerminalNode ABSORBERID() { return getToken(PingBoardParser.ABSORBERID, 0); }
		public HeightContext height() {
			return getRuleContext(HeightContext.class,0);
		}
		public AbsorberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absorber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterAbsorber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitAbsorber(this);
		}
	}

	public final AbsorberContext absorber() throws RecognitionException {
		AbsorberContext _localctx = new AbsorberContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_absorber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); match(ABSORBERID);
			setState(128); name();
			setState(129); intX();
			setState(130); intY();
			setState(131); width();
			setState(132); height();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FireContext extends ParserRuleContext {
		public TriggerContext trigger() {
			return getRuleContext(TriggerContext.class,0);
		}
		public TerminalNode FIREID() { return getToken(PingBoardParser.FIREID, 0); }
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public FireContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fire; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterFire(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitFire(this);
		}
	}

	public final FireContext fire() throws RecognitionException {
		FireContext _localctx = new FireContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134); match(FIREID);
			setState(135); trigger();
			setState(136); action();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhitespaceContext extends ParserRuleContext {
		public TerminalNode WHITESPACE() { return getToken(PingBoardParser.WHITESPACE, 0); }
		public WhitespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whitespace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterWhitespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitWhitespace(this);
		}
	}

	public final WhitespaceContext whitespace() throws RecognitionException {
		WhitespaceContext _localctx = new WhitespaceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_whitespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); match(WHITESPACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewlineContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(PingBoardParser.NEWLINE, 0); }
		public NewlineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterNewline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitNewline(this);
		}
	}

	public final NewlineContext newline() throws RecognitionException {
		NewlineContext _localctx = new NewlineContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_newline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140); match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(PingBoardParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142); match(COMMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualsContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(PingBoardParser.EQUALS, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public EqualsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitEquals(this);
		}
	}

	public final EqualsContext equals() throws RecognitionException {
		EqualsContext _localctx = new EqualsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_equals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(144); match(WHITESPACE);
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(150); match(EQUALS);
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(151); match(WHITESPACE);
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode NAMEID() { return getToken(PingBoardParser.NAMEID, 0); }
		public TerminalNode NAME() { return getToken(PingBoardParser.NAME, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(157); match(WHITESPACE);
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(163); match(NAMEID);
			setState(164); equals();
			setState(165); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GravityContext extends ParserRuleContext {
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode GRAVITYID() { return getToken(PingBoardParser.GRAVITYID, 0); }
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public GravityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gravity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterGravity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitGravity(this);
		}
	}

	public final GravityContext gravity() throws RecognitionException {
		GravityContext _localctx = new GravityContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_gravity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(167); match(WHITESPACE);
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(173); match(GRAVITYID);
			setState(174); equals();
			setState(175); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Friction1Context extends ParserRuleContext {
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FRICTION1ID() { return getToken(PingBoardParser.FRICTION1ID, 0); }
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public Friction1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterFriction1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitFriction1(this);
		}
	}

	public final Friction1Context friction1() throws RecognitionException {
		Friction1Context _localctx = new Friction1Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_friction1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(177); match(WHITESPACE);
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(183); match(FRICTION1ID);
			setState(184); equals();
			setState(185); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Friction2Context extends ParserRuleContext {
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public TerminalNode FRICTION2ID() { return getToken(PingBoardParser.FRICTION2ID, 0); }
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public Friction2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterFriction2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitFriction2(this);
		}
	}

	public final Friction2Context friction2() throws RecognitionException {
		Friction2Context _localctx = new Friction2Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_friction2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(187); match(WHITESPACE);
				}
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(193); match(FRICTION2ID);
			setState(194); equals();
			setState(195); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntXContext extends ParserRuleContext {
		public TerminalNode XID() { return getToken(PingBoardParser.XID, 0); }
		public TerminalNode INTEGER() { return getToken(PingBoardParser.INTEGER, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public IntXContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intX; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterIntX(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitIntX(this);
		}
	}

	public final IntXContext intX() throws RecognitionException {
		IntXContext _localctx = new IntXContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_intX);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(197); match(WHITESPACE);
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(203); match(XID);
			setState(204); equals();
			setState(205); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntYContext extends ParserRuleContext {
		public TerminalNode YID() { return getToken(PingBoardParser.YID, 0); }
		public TerminalNode INTEGER() { return getToken(PingBoardParser.INTEGER, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public IntYContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intY; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterIntY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitIntY(this);
		}
	}

	public final IntYContext intY() throws RecognitionException {
		IntYContext _localctx = new IntYContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_intY);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(207); match(WHITESPACE);
				}
				}
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(213); match(YID);
			setState(214); equals();
			setState(215); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrientationContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(PingBoardParser.INTEGER, 0); }
		public TerminalNode ORIENTATIONID() { return getToken(PingBoardParser.ORIENTATIONID, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public OrientationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orientation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterOrientation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitOrientation(this);
		}
	}

	public final OrientationContext orientation() throws RecognitionException {
		OrientationContext _localctx = new OrientationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_orientation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(217); match(WHITESPACE);
				}
				}
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(223); match(ORIENTATIONID);
			setState(224); equals();
			setState(225); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WidthContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(PingBoardParser.INTEGER, 0); }
		public TerminalNode WIDTHID() { return getToken(PingBoardParser.WIDTHID, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public WidthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_width; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterWidth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitWidth(this);
		}
	}

	public final WidthContext width() throws RecognitionException {
		WidthContext _localctx = new WidthContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_width);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(227); match(WHITESPACE);
				}
				}
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(233); match(WIDTHID);
			setState(234); equals();
			setState(235); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeightContext extends ParserRuleContext {
		public TerminalNode HEIGHTID() { return getToken(PingBoardParser.HEIGHTID, 0); }
		public TerminalNode INTEGER() { return getToken(PingBoardParser.INTEGER, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public HeightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_height; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterHeight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitHeight(this);
		}
	}

	public final HeightContext height() throws RecognitionException {
		HeightContext _localctx = new HeightContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_height);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(237); match(WHITESPACE);
				}
				}
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(243); match(HEIGHTID);
			setState(244); equals();
			setState(245); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatXContext extends ParserRuleContext {
		public TerminalNode XID() { return getToken(PingBoardParser.XID, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public FloatXContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatX; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterFloatX(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitFloatX(this);
		}
	}

	public final FloatXContext floatX() throws RecognitionException {
		FloatXContext _localctx = new FloatXContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_floatX);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(247); match(WHITESPACE);
				}
				}
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(253); match(XID);
			setState(254); equals();
			setState(255); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatYContext extends ParserRuleContext {
		public TerminalNode YID() { return getToken(PingBoardParser.YID, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public FloatYContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatY; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterFloatY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitFloatY(this);
		}
	}

	public final FloatYContext floatY() throws RecognitionException {
		FloatYContext _localctx = new FloatYContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_floatY);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(257); match(WHITESPACE);
				}
				}
				setState(262);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(263); match(YID);
			setState(264); equals();
			setState(265); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XVelocityContext extends ParserRuleContext {
		public TerminalNode XVELOCITYID() { return getToken(PingBoardParser.XVELOCITYID, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public XVelocityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xVelocity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterXVelocity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitXVelocity(this);
		}
	}

	public final XVelocityContext xVelocity() throws RecognitionException {
		XVelocityContext _localctx = new XVelocityContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_xVelocity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(267); match(WHITESPACE);
				}
				}
				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(273); match(XVELOCITYID);
			setState(274); equals();
			setState(275); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class YVelocityContext extends ParserRuleContext {
		public TerminalNode YVELOCITYID() { return getToken(PingBoardParser.YVELOCITYID, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public YVelocityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yVelocity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterYVelocity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitYVelocity(this);
		}
	}

	public final YVelocityContext yVelocity() throws RecognitionException {
		YVelocityContext _localctx = new YVelocityContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_yVelocity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(277); match(WHITESPACE);
				}
				}
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(283); match(YVELOCITYID);
			setState(284); equals();
			setState(285); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriggerContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(PingBoardParser.NAME, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public TerminalNode TRIGGERID() { return getToken(PingBoardParser.TRIGGERID, 0); }
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public TriggerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trigger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterTrigger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitTrigger(this);
		}
	}

	public final TriggerContext trigger() throws RecognitionException {
		TriggerContext _localctx = new TriggerContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_trigger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(287); match(WHITESPACE);
				}
				}
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(293); match(TRIGGERID);
			setState(294); equals();
			setState(295); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public TerminalNode ACTIONID() { return getToken(PingBoardParser.ACTIONID, 0); }
		public TerminalNode NAME() { return getToken(PingBoardParser.NAME, 0); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(PingBoardParser.WHITESPACE, i);
		}
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(PingBoardParser.WHITESPACE); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitAction(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(297); match(WHITESPACE);
				}
				}
				setState(302);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(303); match(ACTIONID);
			setState(304); equals();
			setState(305); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u0136\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\3\3\3\7\3D\n\3\f\3\16\3G\13\3\3\4\3\4\3\4\5\4L\n\4\3\4\5\4O\n\4\3"+
		"\4\5\4R\n\4\3\5\7\5U\n\5\f\5\16\5X\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5c\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3"+
		"\17\3\20\7\20\u0094\n\20\f\20\16\20\u0097\13\20\3\20\3\20\7\20\u009b\n"+
		"\20\f\20\16\20\u009e\13\20\3\21\7\21\u00a1\n\21\f\21\16\21\u00a4\13\21"+
		"\3\21\3\21\3\21\3\21\3\22\7\22\u00ab\n\22\f\22\16\22\u00ae\13\22\3\22"+
		"\3\22\3\22\3\22\3\23\7\23\u00b5\n\23\f\23\16\23\u00b8\13\23\3\23\3\23"+
		"\3\23\3\23\3\24\7\24\u00bf\n\24\f\24\16\24\u00c2\13\24\3\24\3\24\3\24"+
		"\3\24\3\25\7\25\u00c9\n\25\f\25\16\25\u00cc\13\25\3\25\3\25\3\25\3\25"+
		"\3\26\7\26\u00d3\n\26\f\26\16\26\u00d6\13\26\3\26\3\26\3\26\3\26\3\27"+
		"\7\27\u00dd\n\27\f\27\16\27\u00e0\13\27\3\27\3\27\3\27\3\27\3\30\7\30"+
		"\u00e7\n\30\f\30\16\30\u00ea\13\30\3\30\3\30\3\30\3\30\3\31\7\31\u00f1"+
		"\n\31\f\31\16\31\u00f4\13\31\3\31\3\31\3\31\3\31\3\32\7\32\u00fb\n\32"+
		"\f\32\16\32\u00fe\13\32\3\32\3\32\3\32\3\32\3\33\7\33\u0105\n\33\f\33"+
		"\16\33\u0108\13\33\3\33\3\33\3\33\3\33\3\34\7\34\u010f\n\34\f\34\16\34"+
		"\u0112\13\34\3\34\3\34\3\34\3\34\3\35\7\35\u0119\n\35\f\35\16\35\u011c"+
		"\13\35\3\35\3\35\3\35\3\35\3\36\7\36\u0123\n\36\f\36\16\36\u0126\13\36"+
		"\3\36\3\36\3\36\3\36\3\37\7\37\u012d\n\37\f\37\16\37\u0130\13\37\3\37"+
		"\3\37\3\37\3\37\3\37\2\2 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"+
		"(*,.\60\62\64\668:<\2\3\3\2\27\30\u0135\2>\3\2\2\2\4A\3\2\2\2\6H\3\2\2"+
		"\2\bV\3\2\2\2\nd\3\2\2\2\fk\3\2\2\2\16p\3\2\2\2\20u\3\2\2\2\22{\3\2\2"+
		"\2\24\u0081\3\2\2\2\26\u0088\3\2\2\2\30\u008c\3\2\2\2\32\u008e\3\2\2\2"+
		"\34\u0090\3\2\2\2\36\u0095\3\2\2\2 \u00a2\3\2\2\2\"\u00ac\3\2\2\2$\u00b6"+
		"\3\2\2\2&\u00c0\3\2\2\2(\u00ca\3\2\2\2*\u00d4\3\2\2\2,\u00de\3\2\2\2."+
		"\u00e8\3\2\2\2\60\u00f2\3\2\2\2\62\u00fc\3\2\2\2\64\u0106\3\2\2\2\66\u0110"+
		"\3\2\2\28\u011a\3\2\2\2:\u0124\3\2\2\2<\u012e\3\2\2\2>?\5\4\3\2?@\7\2"+
		"\2\3@\3\3\2\2\2AE\5\6\4\2BD\5\b\5\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3"+
		"\2\2\2F\5\3\2\2\2GE\3\2\2\2HI\7\3\2\2IK\5 \21\2JL\5\"\22\2KJ\3\2\2\2K"+
		"L\3\2\2\2LN\3\2\2\2MO\5$\23\2NM\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PR\5&\24\2"+
		"QP\3\2\2\2QR\3\2\2\2R\7\3\2\2\2SU\7\36\2\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2"+
		"\2VW\3\2\2\2Wb\3\2\2\2XV\3\2\2\2Yc\5\n\6\2Zc\5\f\7\2[c\5\16\b\2\\c\5\20"+
		"\t\2]c\5\22\n\2^c\5\24\13\2_c\5\26\f\2`c\5\34\17\2ac\5\32\16\2bY\3\2\2"+
		"\2bZ\3\2\2\2b[\3\2\2\2b\\\3\2\2\2b]\3\2\2\2b^\3\2\2\2b_\3\2\2\2b`\3\2"+
		"\2\2ba\3\2\2\2c\t\3\2\2\2de\7\23\2\2ef\5 \21\2fg\5\62\32\2gh\5\64\33\2"+
		"hi\5\66\34\2ij\58\35\2j\13\3\2\2\2kl\7\24\2\2lm\5 \21\2mn\5(\25\2no\5"+
		"*\26\2o\r\3\2\2\2pq\7\25\2\2qr\5 \21\2rs\5(\25\2st\5*\26\2t\17\3\2\2\2"+
		"uv\7\26\2\2vw\5 \21\2wx\5(\25\2xy\5*\26\2yz\5,\27\2z\21\3\2\2\2{|\t\2"+
		"\2\2|}\5 \21\2}~\5(\25\2~\177\5*\26\2\177\u0080\5,\27\2\u0080\23\3\2\2"+
		"\2\u0081\u0082\7\31\2\2\u0082\u0083\5 \21\2\u0083\u0084\5(\25\2\u0084"+
		"\u0085\5*\26\2\u0085\u0086\5.\30\2\u0086\u0087\5\60\31\2\u0087\25\3\2"+
		"\2\2\u0088\u0089\7\32\2\2\u0089\u008a\5:\36\2\u008a\u008b\5<\37\2\u008b"+
		"\27\3\2\2\2\u008c\u008d\7\36\2\2\u008d\31\3\2\2\2\u008e\u008f\7\37\2\2"+
		"\u008f\33\3\2\2\2\u0090\u0091\7\4\2\2\u0091\35\3\2\2\2\u0092\u0094\7\36"+
		"\2\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009c\7\5"+
		"\2\2\u0099\u009b\7\36\2\2\u009a\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\37\3\2\2\2\u009e\u009c\3\2\2"+
		"\2\u009f\u00a1\7\36\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a2\3\2"+
		"\2\2\u00a5\u00a6\7\6\2\2\u00a6\u00a7\5\36\20\2\u00a7\u00a8\7\33\2\2\u00a8"+
		"!\3\2\2\2\u00a9\u00ab\7\36\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ae\3\2\2\2"+
		"\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac"+
		"\3\2\2\2\u00af\u00b0\7\16\2\2\u00b0\u00b1\5\36\20\2\u00b1\u00b2\7\35\2"+
		"\2\u00b2#\3\2\2\2\u00b3\u00b5\7\36\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8"+
		"\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b9\u00ba\7\17\2\2\u00ba\u00bb\5\36\20\2\u00bb\u00bc"+
		"\7\35\2\2\u00bc%\3\2\2\2\u00bd\u00bf\7\36\2\2\u00be\u00bd\3\2\2\2\u00bf"+
		"\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c3\3\2"+
		"\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c4\7\20\2\2\u00c4\u00c5\5\36\20\2\u00c5"+
		"\u00c6\7\35\2\2\u00c6\'\3\2\2\2\u00c7\u00c9\7\36\2\2\u00c8\u00c7\3\2\2"+
		"\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cd"+
		"\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00ce\7\7\2\2\u00ce\u00cf\5\36\20\2"+
		"\u00cf\u00d0\7\34\2\2\u00d0)\3\2\2\2\u00d1\u00d3\7\36\2\2\u00d2\u00d1"+
		"\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d8\7\b\2\2\u00d8\u00d9\5\36"+
		"\20\2\u00d9\u00da\7\34\2\2\u00da+\3\2\2\2\u00db\u00dd\7\36\2\2\u00dc\u00db"+
		"\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df"+
		"\u00e1\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e2\7\t\2\2\u00e2\u00e3\5\36"+
		"\20\2\u00e3\u00e4\7\34\2\2\u00e4-\3\2\2\2\u00e5\u00e7\7\36\2\2\u00e6\u00e5"+
		"\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\u00eb\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ec\7\n\2\2\u00ec\u00ed\5\36"+
		"\20\2\u00ed\u00ee\7\34\2\2\u00ee/\3\2\2\2\u00ef\u00f1\7\36\2\2\u00f0\u00ef"+
		"\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\u00f5\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f6\7\13\2\2\u00f6\u00f7\5"+
		"\36\20\2\u00f7\u00f8\7\34\2\2\u00f8\61\3\2\2\2\u00f9\u00fb\7\36\2\2\u00fa"+
		"\u00f9\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2"+
		"\2\2\u00fd\u00ff\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0100\7\7\2\2\u0100"+
		"\u0101\5\36\20\2\u0101\u0102\7\35\2\2\u0102\63\3\2\2\2\u0103\u0105\7\36"+
		"\2\2\u0104\u0103\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106"+
		"\u0107\3\2\2\2\u0107\u0109\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u010a\7\b"+
		"\2\2\u010a\u010b\5\36\20\2\u010b\u010c\7\35\2\2\u010c\65\3\2\2\2\u010d"+
		"\u010f\7\36\2\2\u010e\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3"+
		"\2\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113"+
		"\u0114\7\f\2\2\u0114\u0115\5\36\20\2\u0115\u0116\7\35\2\2\u0116\67\3\2"+
		"\2\2\u0117\u0119\7\36\2\2\u0118\u0117\3\2\2\2\u0119\u011c\3\2\2\2\u011a"+
		"\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011d\3\2\2\2\u011c\u011a\3\2"+
		"\2\2\u011d\u011e\7\r\2\2\u011e\u011f\5\36\20\2\u011f\u0120\7\35\2\2\u0120"+
		"9\3\2\2\2\u0121\u0123\7\36\2\2\u0122\u0121\3\2\2\2\u0123\u0126\3\2\2\2"+
		"\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0127\3\2\2\2\u0126\u0124"+
		"\3\2\2\2\u0127\u0128\7\21\2\2\u0128\u0129\5\36\20\2\u0129\u012a\7\33\2"+
		"\2\u012a;\3\2\2\2\u012b\u012d\7\36\2\2\u012c\u012b\3\2\2\2\u012d\u0130"+
		"\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130"+
		"\u012e\3\2\2\2\u0131\u0132\7\22\2\2\u0132\u0133\5\36\20\2\u0133\u0134"+
		"\7\33\2\2\u0134=\3\2\2\2\31EKNQVb\u0095\u009c\u00a2\u00ac\u00b6\u00c0"+
		"\u00ca\u00d4\u00de\u00e8\u00f2\u00fc\u0106\u0110\u011a\u0124\u012e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}