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
		RULE_rightFlipper = 8, RULE_leftFlipper = 9, RULE_absorber = 10, RULE_fire = 11, 
		RULE_whitespace = 12, RULE_newline = 13, RULE_comment = 14, RULE_equals = 15, 
		RULE_name = 16, RULE_gravity = 17, RULE_friction1 = 18, RULE_friction2 = 19, 
		RULE_intX = 20, RULE_intY = 21, RULE_orientation = 22, RULE_width = 23, 
		RULE_height = 24, RULE_floatX = 25, RULE_floatY = 26, RULE_xVelocity = 27, 
		RULE_yVelocity = 28, RULE_trigger = 29, RULE_action = 30;
	public static final String[] ruleNames = {
		"root", "board", "boardInit", "bodyLine", "ball", "squareBumper", "circleBumper", 
		"triangleBumper", "rightFlipper", "leftFlipper", "absorber", "fire", "whitespace", 
		"newline", "comment", "equals", "name", "gravity", "friction1", "friction2", 
		"intX", "intY", "orientation", "width", "height", "floatX", "floatY", 
		"xVelocity", "yVelocity", "trigger", "action"
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
			setState(62); board();
			setState(63); match(EOF);
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
			setState(65); boardInit();
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << BALLID) | (1L << SQUAREBUMPERID) | (1L << CIRCLEBUMPERID) | (1L << TRIANGLEBUMPERID) | (1L << RIGHTFLIPPERID) | (1L << LEFTFLIPPERID) | (1L << ABSORBERID) | (1L << FIREID) | (1L << WHITESPACE) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(66); bodyLine();
				}
				}
				setState(71);
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
			setState(72); match(BOARDINITID);
			setState(73); name();
			setState(75);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(74); gravity();
				}
				break;
			}
			setState(78);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(77); friction1();
				}
				break;
			}
			setState(81);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(80); friction2();
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
		public LeftFlipperContext leftFlipper() {
			return getRuleContext(LeftFlipperContext.class,0);
		}
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
		public RightFlipperContext rightFlipper() {
			return getRuleContext(RightFlipperContext.class,0);
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
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(83); match(WHITESPACE);
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			switch (_input.LA(1)) {
			case BALLID:
				{
				setState(89); ball();
				}
				break;
			case SQUAREBUMPERID:
				{
				setState(90); squareBumper();
				}
				break;
			case CIRCLEBUMPERID:
				{
				setState(91); circleBumper();
				}
				break;
			case TRIANGLEBUMPERID:
				{
				setState(92); triangleBumper();
				}
				break;
			case RIGHTFLIPPERID:
				{
				setState(93); rightFlipper();
				}
				break;
			case LEFTFLIPPERID:
				{
				setState(94); leftFlipper();
				}
				break;
			case ABSORBERID:
				{
				setState(95); absorber();
				}
				break;
			case FIREID:
				{
				setState(96); fire();
				}
				break;
			case COMMENT:
				{
				setState(97); comment();
				}
				break;
			case NEWLINE:
				{
				setState(98); newline();
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
			setState(101); match(BALLID);
			setState(102); name();
			setState(103); floatX();
			setState(104); floatY();
			setState(105); xVelocity();
			setState(106); yVelocity();
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
			setState(108); match(SQUAREBUMPERID);
			setState(109); name();
			setState(110); intX();
			setState(111); intY();
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
			setState(113); match(CIRCLEBUMPERID);
			setState(114); name();
			setState(115); intX();
			setState(116); intY();
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
			setState(118); match(TRIANGLEBUMPERID);
			setState(119); name();
			setState(120); intX();
			setState(121); intY();
			setState(122); orientation();
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

	public static class RightFlipperContext extends ParserRuleContext {
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
		public RightFlipperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightFlipper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterRightFlipper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitRightFlipper(this);
		}
	}

	public final RightFlipperContext rightFlipper() throws RecognitionException {
		RightFlipperContext _localctx = new RightFlipperContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rightFlipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); match(RIGHTFLIPPERID);
			setState(125); name();
			setState(126); intX();
			setState(127); intY();
			setState(128); orientation();
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

	public static class LeftFlipperContext extends ParserRuleContext {
		public TerminalNode LEFTFLIPPERID() { return getToken(PingBoardParser.LEFTFLIPPERID, 0); }
		public OrientationContext orientation() {
			return getRuleContext(OrientationContext.class,0);
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
		public LeftFlipperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftFlipper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterLeftFlipper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitLeftFlipper(this);
		}
	}

	public final LeftFlipperContext leftFlipper() throws RecognitionException {
		LeftFlipperContext _localctx = new LeftFlipperContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_leftFlipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(LEFTFLIPPERID);
			setState(131); name();
			setState(132); intX();
			setState(133); intY();
			setState(134); orientation();
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
		enterRule(_localctx, 20, RULE_absorber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); match(ABSORBERID);
			setState(137); name();
			setState(138); intX();
			setState(139); intY();
			setState(140); width();
			setState(141); height();
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
		enterRule(_localctx, 22, RULE_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143); match(FIREID);
			setState(144); trigger();
			setState(145); action();
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
		enterRule(_localctx, 24, RULE_whitespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); match(WHITESPACE);
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
		enterRule(_localctx, 26, RULE_newline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(NEWLINE);
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
		enterRule(_localctx, 28, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151); match(COMMENT);
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
		enterRule(_localctx, 30, RULE_equals);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153); match(EQUALS);
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
		enterRule(_localctx, 32, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(155); match(WHITESPACE);
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(161); match(NAMEID);
			setState(162); equals();
			setState(163); match(NAME);
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
		enterRule(_localctx, 34, RULE_gravity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(165); match(WHITESPACE);
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(171); match(GRAVITYID);
			setState(172); equals();
			setState(173); match(FLOAT);
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
		enterRule(_localctx, 36, RULE_friction1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(175); match(WHITESPACE);
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181); match(FRICTION1ID);
			setState(182); equals();
			setState(183); match(FLOAT);
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
		enterRule(_localctx, 38, RULE_friction2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(185); match(WHITESPACE);
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(191); match(FRICTION2ID);
			setState(192); equals();
			setState(193); match(FLOAT);
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
		enterRule(_localctx, 40, RULE_intX);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(195); match(WHITESPACE);
				}
				}
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(201); match(XID);
			setState(202); equals();
			setState(203); match(INTEGER);
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
		enterRule(_localctx, 42, RULE_intY);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(205); match(WHITESPACE);
				}
				}
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(211); match(YID);
			setState(212); equals();
			setState(213); match(INTEGER);
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
		enterRule(_localctx, 44, RULE_orientation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(215); match(WHITESPACE);
				}
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(221); match(ORIENTATIONID);
			setState(222); equals();
			setState(223); match(INTEGER);
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
		enterRule(_localctx, 46, RULE_width);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(225); match(WHITESPACE);
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(231); match(WIDTHID);
			setState(232); equals();
			setState(233); match(INTEGER);
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
		enterRule(_localctx, 48, RULE_height);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(235); match(WHITESPACE);
				}
				}
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(241); match(HEIGHTID);
			setState(242); equals();
			setState(243); match(INTEGER);
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
		enterRule(_localctx, 50, RULE_floatX);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(245); match(WHITESPACE);
				}
				}
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(251); match(XID);
			setState(252); equals();
			setState(253); match(FLOAT);
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
		enterRule(_localctx, 52, RULE_floatY);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(255); match(WHITESPACE);
				}
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(261); match(YID);
			setState(262); equals();
			setState(263); match(FLOAT);
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
		enterRule(_localctx, 54, RULE_xVelocity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(265); match(WHITESPACE);
				}
				}
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(271); match(XVELOCITYID);
			setState(272); equals();
			setState(273); match(FLOAT);
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
		enterRule(_localctx, 56, RULE_yVelocity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(275); match(WHITESPACE);
				}
				}
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(281); match(YVELOCITYID);
			setState(282); equals();
			setState(283); match(FLOAT);
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
		enterRule(_localctx, 58, RULE_trigger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(285); match(WHITESPACE);
				}
				}
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(291); match(TRIGGERID);
			setState(292); equals();
			setState(293); match(NAME);
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
		enterRule(_localctx, 60, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(295); match(WHITESPACE);
				}
				}
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(301); match(ACTIONID);
			setState(302); equals();
			setState(303); match(NAME);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u0134\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\3\3\3\7\3F\n\3\f\3\16\3I\13\3\3\4\3\4\3\4\5\4N\n\4\3\4\5\4"+
		"Q\n\4\3\4\5\4T\n\4\3\5\7\5W\n\5\f\5\16\5Z\13\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\5\5f\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\7\22\u009f\n\22\f"+
		"\22\16\22\u00a2\13\22\3\22\3\22\3\22\3\22\3\23\7\23\u00a9\n\23\f\23\16"+
		"\23\u00ac\13\23\3\23\3\23\3\23\3\23\3\24\7\24\u00b3\n\24\f\24\16\24\u00b6"+
		"\13\24\3\24\3\24\3\24\3\24\3\25\7\25\u00bd\n\25\f\25\16\25\u00c0\13\25"+
		"\3\25\3\25\3\25\3\25\3\26\7\26\u00c7\n\26\f\26\16\26\u00ca\13\26\3\26"+
		"\3\26\3\26\3\26\3\27\7\27\u00d1\n\27\f\27\16\27\u00d4\13\27\3\27\3\27"+
		"\3\27\3\27\3\30\7\30\u00db\n\30\f\30\16\30\u00de\13\30\3\30\3\30\3\30"+
		"\3\30\3\31\7\31\u00e5\n\31\f\31\16\31\u00e8\13\31\3\31\3\31\3\31\3\31"+
		"\3\32\7\32\u00ef\n\32\f\32\16\32\u00f2\13\32\3\32\3\32\3\32\3\32\3\33"+
		"\7\33\u00f9\n\33\f\33\16\33\u00fc\13\33\3\33\3\33\3\33\3\33\3\34\7\34"+
		"\u0103\n\34\f\34\16\34\u0106\13\34\3\34\3\34\3\34\3\34\3\35\7\35\u010d"+
		"\n\35\f\35\16\35\u0110\13\35\3\35\3\35\3\35\3\35\3\36\7\36\u0117\n\36"+
		"\f\36\16\36\u011a\13\36\3\36\3\36\3\36\3\36\3\37\7\37\u0121\n\37\f\37"+
		"\16\37\u0124\13\37\3\37\3\37\3\37\3\37\3 \7 \u012b\n \f \16 \u012e\13"+
		" \3 \3 \3 \3 \3 \2\2!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,."+
		"\60\62\64\668:<>\2\2\u0131\2@\3\2\2\2\4C\3\2\2\2\6J\3\2\2\2\bX\3\2\2\2"+
		"\ng\3\2\2\2\fn\3\2\2\2\16s\3\2\2\2\20x\3\2\2\2\22~\3\2\2\2\24\u0084\3"+
		"\2\2\2\26\u008a\3\2\2\2\30\u0091\3\2\2\2\32\u0095\3\2\2\2\34\u0097\3\2"+
		"\2\2\36\u0099\3\2\2\2 \u009b\3\2\2\2\"\u00a0\3\2\2\2$\u00aa\3\2\2\2&\u00b4"+
		"\3\2\2\2(\u00be\3\2\2\2*\u00c8\3\2\2\2,\u00d2\3\2\2\2.\u00dc\3\2\2\2\60"+
		"\u00e6\3\2\2\2\62\u00f0\3\2\2\2\64\u00fa\3\2\2\2\66\u0104\3\2\2\28\u010e"+
		"\3\2\2\2:\u0118\3\2\2\2<\u0122\3\2\2\2>\u012c\3\2\2\2@A\5\4\3\2AB\7\2"+
		"\2\3B\3\3\2\2\2CG\5\6\4\2DF\5\b\5\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3"+
		"\2\2\2H\5\3\2\2\2IG\3\2\2\2JK\7\3\2\2KM\5\"\22\2LN\5$\23\2ML\3\2\2\2M"+
		"N\3\2\2\2NP\3\2\2\2OQ\5&\24\2PO\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RT\5(\25\2"+
		"SR\3\2\2\2ST\3\2\2\2T\7\3\2\2\2UW\7\36\2\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2"+
		"\2XY\3\2\2\2Ye\3\2\2\2ZX\3\2\2\2[f\5\n\6\2\\f\5\f\7\2]f\5\16\b\2^f\5\20"+
		"\t\2_f\5\22\n\2`f\5\24\13\2af\5\26\f\2bf\5\30\r\2cf\5\36\20\2df\5\34\17"+
		"\2e[\3\2\2\2e\\\3\2\2\2e]\3\2\2\2e^\3\2\2\2e_\3\2\2\2e`\3\2\2\2ea\3\2"+
		"\2\2eb\3\2\2\2ec\3\2\2\2ed\3\2\2\2f\t\3\2\2\2gh\7\23\2\2hi\5\"\22\2ij"+
		"\5\64\33\2jk\5\66\34\2kl\58\35\2lm\5:\36\2m\13\3\2\2\2no\7\24\2\2op\5"+
		"\"\22\2pq\5*\26\2qr\5,\27\2r\r\3\2\2\2st\7\25\2\2tu\5\"\22\2uv\5*\26\2"+
		"vw\5,\27\2w\17\3\2\2\2xy\7\26\2\2yz\5\"\22\2z{\5*\26\2{|\5,\27\2|}\5."+
		"\30\2}\21\3\2\2\2~\177\7\27\2\2\177\u0080\5\"\22\2\u0080\u0081\5*\26\2"+
		"\u0081\u0082\5,\27\2\u0082\u0083\5.\30\2\u0083\23\3\2\2\2\u0084\u0085"+
		"\7\30\2\2\u0085\u0086\5\"\22\2\u0086\u0087\5*\26\2\u0087\u0088\5,\27\2"+
		"\u0088\u0089\5.\30\2\u0089\25\3\2\2\2\u008a\u008b\7\31\2\2\u008b\u008c"+
		"\5\"\22\2\u008c\u008d\5*\26\2\u008d\u008e\5,\27\2\u008e\u008f\5\60\31"+
		"\2\u008f\u0090\5\62\32\2\u0090\27\3\2\2\2\u0091\u0092\7\32\2\2\u0092\u0093"+
		"\5<\37\2\u0093\u0094\5> \2\u0094\31\3\2\2\2\u0095\u0096\7\36\2\2\u0096"+
		"\33\3\2\2\2\u0097\u0098\7\37\2\2\u0098\35\3\2\2\2\u0099\u009a\7\4\2\2"+
		"\u009a\37\3\2\2\2\u009b\u009c\7\5\2\2\u009c!\3\2\2\2\u009d\u009f\7\36"+
		"\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0"+
		"\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\7\6"+
		"\2\2\u00a4\u00a5\5 \21\2\u00a5\u00a6\7\33\2\2\u00a6#\3\2\2\2\u00a7\u00a9"+
		"\7\36\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2"+
		"\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00ae"+
		"\7\16\2\2\u00ae\u00af\5 \21\2\u00af\u00b0\7\35\2\2\u00b0%\3\2\2\2\u00b1"+
		"\u00b3\7\36\2\2\u00b2\u00b1\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3"+
		"\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7"+
		"\u00b8\7\17\2\2\u00b8\u00b9\5 \21\2\u00b9\u00ba\7\35\2\2\u00ba\'\3\2\2"+
		"\2\u00bb\u00bd\7\36\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00be\3\2"+
		"\2\2\u00c1\u00c2\7\20\2\2\u00c2\u00c3\5 \21\2\u00c3\u00c4\7\35\2\2\u00c4"+
		")\3\2\2\2\u00c5\u00c7\7\36\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2"+
		"\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cb\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00cb\u00cc\7\7\2\2\u00cc\u00cd\5 \21\2\u00cd\u00ce\7\34\2\2"+
		"\u00ce+\3\2\2\2\u00cf\u00d1\7\36\2\2\u00d0\u00cf\3\2\2\2\u00d1\u00d4\3"+
		"\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d5\3\2\2\2\u00d4"+
		"\u00d2\3\2\2\2\u00d5\u00d6\7\b\2\2\u00d6\u00d7\5 \21\2\u00d7\u00d8\7\34"+
		"\2\2\u00d8-\3\2\2\2\u00d9\u00db\7\36\2\2\u00da\u00d9\3\2\2\2\u00db\u00de"+
		"\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00df\u00e0\7\t\2\2\u00e0\u00e1\5 \21\2\u00e1\u00e2\7\34"+
		"\2\2\u00e2/\3\2\2\2\u00e3\u00e5\7\36\2\2\u00e4\u00e3\3\2\2\2\u00e5\u00e8"+
		"\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e9\u00ea\7\n\2\2\u00ea\u00eb\5 \21\2\u00eb\u00ec\7\34"+
		"\2\2\u00ec\61\3\2\2\2\u00ed\u00ef\7\36\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2"+
		"\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f3\u00f4\7\13\2\2\u00f4\u00f5\5 \21\2\u00f5\u00f6\7"+
		"\34\2\2\u00f6\63\3\2\2\2\u00f7\u00f9\7\36\2\2\u00f8\u00f7\3\2\2\2\u00f9"+
		"\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\3\2"+
		"\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00fe\7\7\2\2\u00fe\u00ff\5 \21\2\u00ff"+
		"\u0100\7\35\2\2\u0100\65\3\2\2\2\u0101\u0103\7\36\2\2\u0102\u0101\3\2"+
		"\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
		"\u0107\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0108\7\b\2\2\u0108\u0109\5 "+
		"\21\2\u0109\u010a\7\35\2\2\u010a\67\3\2\2\2\u010b\u010d\7\36\2\2\u010c"+
		"\u010b\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010f\u0111\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0112\7\f\2\2\u0112"+
		"\u0113\5 \21\2\u0113\u0114\7\35\2\2\u01149\3\2\2\2\u0115\u0117\7\36\2"+
		"\2\u0116\u0115\3\2\2\2\u0117\u011a\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119"+
		"\3\2\2\2\u0119\u011b\3\2\2\2\u011a\u0118\3\2\2\2\u011b\u011c\7\r\2\2\u011c"+
		"\u011d\5 \21\2\u011d\u011e\7\35\2\2\u011e;\3\2\2\2\u011f\u0121\7\36\2"+
		"\2\u0120\u011f\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123"+
		"\3\2\2\2\u0123\u0125\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0126\7\21\2\2"+
		"\u0126\u0127\5 \21\2\u0127\u0128\7\33\2\2\u0128=\3\2\2\2\u0129\u012b\7"+
		"\36\2\2\u012a\u0129\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c"+
		"\u012d\3\2\2\2\u012d\u012f\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130\7\22"+
		"\2\2\u0130\u0131\5 \21\2\u0131\u0132\7\33\2\2\u0132?\3\2\2\2\27GMPSXe"+
		"\u00a0\u00aa\u00b4\u00be\u00c8\u00d2\u00dc\u00e6\u00f0\u00fa\u0104\u010e"+
		"\u0118\u0122\u012c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}