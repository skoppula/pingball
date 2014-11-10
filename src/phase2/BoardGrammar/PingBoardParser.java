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
		BOARDINITID=1, COMMENT=2, ORIENTATIONVALUE=3, EQUALS=4, NAMEID=5, XID=6, 
		YID=7, ORIENTATIONID=8, WIDTHID=9, HEIGHTID=10, XVELOCITYID=11, YVELOCITYID=12, 
		GRAVITYID=13, FRICTION1ID=14, FRICTION2ID=15, TRIGGERID=16, ACTIONID=17, 
		BALLID=18, SQUAREBUMPERID=19, CIRCLEBUMPERID=20, TRIANGLEBUMPERID=21, 
		RIGHTFLIPPERID=22, LEFTFLIPPERID=23, ABSORBERID=24, FIREID=25, NAME=26, 
		INTEGER=27, FLOAT=28, WHITESPACE=29, NEWLINE=30;
	public static final String[] tokenNames = {
		"<INVALID>", "'board'", "COMMENT", "ORIENTATIONVALUE", "'='", "'name'", 
		"'x'", "'y'", "'orientation'", "'width'", "'height'", "'xVelocity'", "'yVelocity'", 
		"'gravity'", "'friction1'", "'friction2'", "'trigger'", "'action'", "'ball'", 
		"'squareBumper'", "'circleBumper'", "'triangleBumper'", "'rightFlipper'", 
		"'leftFlipper'", "'absorber'", "'fire'", "NAME", "INTEGER", "FLOAT", "WHITESPACE", 
		"NEWLINE"
	};
	public static final int
		RULE_root = 0, RULE_board = 1, RULE_irrelevantLine = 2, RULE_boardInit = 3, 
		RULE_bodyLine = 4, RULE_ball = 5, RULE_squareBumper = 6, RULE_circleBumper = 7, 
		RULE_triangleBumper = 8, RULE_rightFlipper = 9, RULE_leftFlipper = 10, 
		RULE_absorber = 11, RULE_fire = 12, RULE_whitespace = 13, RULE_newline = 14, 
		RULE_equals = 15, RULE_name = 16, RULE_gravity = 17, RULE_friction1 = 18, 
		RULE_friction2 = 19, RULE_intX = 20, RULE_intY = 21, RULE_orientation = 22, 
		RULE_width = 23, RULE_height = 24, RULE_floatX = 25, RULE_floatY = 26, 
		RULE_xVelocity = 27, RULE_yVelocity = 28, RULE_trigger = 29, RULE_action = 30;
	public static final String[] ruleNames = {
		"root", "board", "irrelevantLine", "boardInit", "bodyLine", "ball", "squareBumper", 
		"circleBumper", "triangleBumper", "rightFlipper", "leftFlipper", "absorber", 
		"fire", "whitespace", "newline", "equals", "name", "gravity", "friction1", 
		"friction2", "intX", "intY", "orientation", "width", "height", "floatX", 
		"floatY", "xVelocity", "yVelocity", "trigger", "action"
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BALLID) | (1L << SQUAREBUMPERID) | (1L << CIRCLEBUMPERID) | (1L << TRIANGLEBUMPERID) | (1L << RIGHTFLIPPERID) | (1L << LEFTFLIPPERID) | (1L << ABSORBERID) | (1L << FIREID) | (1L << NEWLINE))) != 0)) {
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

	public static class IrrelevantLineContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(PingBoardParser.COMMENT, 0); }
		public TerminalNode NEWLINE() { return getToken(PingBoardParser.NEWLINE, 0); }
		public IrrelevantLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_irrelevantLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).enterIrrelevantLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PingBoardListener ) ((PingBoardListener)listener).exitIrrelevantLine(this);
		}
	}

	public final IrrelevantLineContext irrelevantLine() throws RecognitionException {
		IrrelevantLineContext _localctx = new IrrelevantLineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_irrelevantLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(NEWLINE);
			setState(74);
			_la = _input.LA(1);
			if (_la==COMMENT) {
				{
				setState(73); match(COMMENT);
				}
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
		enterRule(_localctx, 6, RULE_boardInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(BOARDINITID);
			setState(77); name();
			setState(79);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(78); gravity();
				}
				break;
			}
			setState(82);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(81); friction1();
				}
				break;
			}
			setState(85);
			_la = _input.LA(1);
			if (_la==FRICTION2ID || _la==WHITESPACE) {
				{
				setState(84); friction2();
				}
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
		public IrrelevantLineContext irrelevantLine() {
			return getRuleContext(IrrelevantLineContext.class,0);
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
		public CircleBumperContext circleBumper() {
			return getRuleContext(CircleBumperContext.class,0);
		}
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
		enterRule(_localctx, 8, RULE_bodyLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
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
				{
				setState(91); rightFlipper();
				}
				break;
			case LEFTFLIPPERID:
				{
				setState(92); leftFlipper();
				}
				break;
			case ABSORBERID:
				{
				setState(93); absorber();
				}
				break;
			case FIREID:
				{
				setState(94); fire();
				}
				break;
			case NEWLINE:
				{
				setState(95); irrelevantLine();
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
		enterRule(_localctx, 10, RULE_ball);
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
		enterRule(_localctx, 12, RULE_squareBumper);
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
		enterRule(_localctx, 14, RULE_circleBumper);
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
		enterRule(_localctx, 16, RULE_triangleBumper);
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
		enterRule(_localctx, 18, RULE_rightFlipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121); match(RIGHTFLIPPERID);
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
		enterRule(_localctx, 20, RULE_leftFlipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); match(LEFTFLIPPERID);
			setState(128); name();
			setState(129); intX();
			setState(130); intY();
			setState(131); orientation();
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
		enterRule(_localctx, 22, RULE_absorber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133); match(ABSORBERID);
			setState(134); name();
			setState(135); intX();
			setState(136); intY();
			setState(137); width();
			setState(138); height();
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
		enterRule(_localctx, 24, RULE_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140); match(FIREID);
			setState(141); trigger();
			setState(142); action();
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
		enterRule(_localctx, 26, RULE_whitespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144); match(WHITESPACE);
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
		enterRule(_localctx, 28, RULE_newline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146); match(NEWLINE);
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
			setState(148); match(EQUALS);
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
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(150); match(WHITESPACE);
				}
				}
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(156); match(NAMEID);
			setState(157); equals();
			setState(158); match(NAME);
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
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(160); match(WHITESPACE);
				}
				}
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(166); match(GRAVITYID);
			setState(167); equals();
			setState(168); match(FLOAT);
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
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(170); match(WHITESPACE);
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(176); match(FRICTION1ID);
			setState(177); equals();
			setState(178); match(FLOAT);
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
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(180); match(WHITESPACE);
				}
				}
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(186); match(FRICTION2ID);
			setState(187); equals();
			setState(188); match(FLOAT);
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
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(190); match(WHITESPACE);
				}
				}
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(196); match(XID);
			setState(197); equals();
			setState(198); match(INTEGER);
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
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(200); match(WHITESPACE);
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(206); match(YID);
			setState(207); equals();
			setState(208); match(INTEGER);
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
		public TerminalNode ORIENTATIONVALUE() { return getToken(PingBoardParser.ORIENTATIONVALUE, 0); }
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
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(210); match(WHITESPACE);
				}
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(216); match(ORIENTATIONID);
			setState(217); equals();
			setState(218); match(ORIENTATIONVALUE);
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
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(220); match(WHITESPACE);
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226); match(WIDTHID);
			setState(227); equals();
			setState(228); match(INTEGER);
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
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(230); match(WHITESPACE);
				}
				}
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(236); match(HEIGHTID);
			setState(237); equals();
			setState(238); match(INTEGER);
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
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(240); match(WHITESPACE);
				}
				}
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(246); match(XID);
			setState(247); equals();
			setState(248); match(FLOAT);
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
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(250); match(WHITESPACE);
				}
				}
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(256); match(YID);
			setState(257); equals();
			setState(258); match(FLOAT);
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
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(260); match(WHITESPACE);
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(266); match(XVELOCITYID);
			setState(267); equals();
			setState(268); match(FLOAT);
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
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(270); match(WHITESPACE);
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(276); match(YVELOCITYID);
			setState(277); equals();
			setState(278); match(FLOAT);
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
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(280); match(WHITESPACE);
				}
				}
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(286); match(TRIGGERID);
			setState(287); equals();
			setState(288); match(NAME);
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
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(290); match(WHITESPACE);
				}
				}
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(296); match(ACTIONID);
			setState(297); equals();
			setState(298); match(NAME);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3 \u012f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\3\3\3\7\3F\n\3\f\3\16\3I\13\3\3\4\3\4\5\4M\n\4\3\5\3\5\3\5"+
		"\5\5R\n\5\3\5\5\5U\n\5\3\5\5\5X\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6c\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\7\22\u009a\n\22\f\22\16\22\u009d\13\22"+
		"\3\22\3\22\3\22\3\22\3\23\7\23\u00a4\n\23\f\23\16\23\u00a7\13\23\3\23"+
		"\3\23\3\23\3\23\3\24\7\24\u00ae\n\24\f\24\16\24\u00b1\13\24\3\24\3\24"+
		"\3\24\3\24\3\25\7\25\u00b8\n\25\f\25\16\25\u00bb\13\25\3\25\3\25\3\25"+
		"\3\25\3\26\7\26\u00c2\n\26\f\26\16\26\u00c5\13\26\3\26\3\26\3\26\3\26"+
		"\3\27\7\27\u00cc\n\27\f\27\16\27\u00cf\13\27\3\27\3\27\3\27\3\27\3\30"+
		"\7\30\u00d6\n\30\f\30\16\30\u00d9\13\30\3\30\3\30\3\30\3\30\3\31\7\31"+
		"\u00e0\n\31\f\31\16\31\u00e3\13\31\3\31\3\31\3\31\3\31\3\32\7\32\u00ea"+
		"\n\32\f\32\16\32\u00ed\13\32\3\32\3\32\3\32\3\32\3\33\7\33\u00f4\n\33"+
		"\f\33\16\33\u00f7\13\33\3\33\3\33\3\33\3\33\3\34\7\34\u00fe\n\34\f\34"+
		"\16\34\u0101\13\34\3\34\3\34\3\34\3\34\3\35\7\35\u0108\n\35\f\35\16\35"+
		"\u010b\13\35\3\35\3\35\3\35\3\35\3\36\7\36\u0112\n\36\f\36\16\36\u0115"+
		"\13\36\3\36\3\36\3\36\3\36\3\37\7\37\u011c\n\37\f\37\16\37\u011f\13\37"+
		"\3\37\3\37\3\37\3\37\3 \7 \u0126\n \f \16 \u0129\13 \3 \3 \3 \3 \3 \2"+
		"\2!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2"+
		"\2\u012b\2@\3\2\2\2\4C\3\2\2\2\6J\3\2\2\2\bN\3\2\2\2\nb\3\2\2\2\fd\3\2"+
		"\2\2\16k\3\2\2\2\20p\3\2\2\2\22u\3\2\2\2\24{\3\2\2\2\26\u0081\3\2\2\2"+
		"\30\u0087\3\2\2\2\32\u008e\3\2\2\2\34\u0092\3\2\2\2\36\u0094\3\2\2\2 "+
		"\u0096\3\2\2\2\"\u009b\3\2\2\2$\u00a5\3\2\2\2&\u00af\3\2\2\2(\u00b9\3"+
		"\2\2\2*\u00c3\3\2\2\2,\u00cd\3\2\2\2.\u00d7\3\2\2\2\60\u00e1\3\2\2\2\62"+
		"\u00eb\3\2\2\2\64\u00f5\3\2\2\2\66\u00ff\3\2\2\28\u0109\3\2\2\2:\u0113"+
		"\3\2\2\2<\u011d\3\2\2\2>\u0127\3\2\2\2@A\5\4\3\2AB\7\2\2\3B\3\3\2\2\2"+
		"CG\5\b\5\2DF\5\n\6\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2H\5\3\2\2"+
		"\2IG\3\2\2\2JL\7 \2\2KM\7\4\2\2LK\3\2\2\2LM\3\2\2\2M\7\3\2\2\2NO\7\3\2"+
		"\2OQ\5\"\22\2PR\5$\23\2QP\3\2\2\2QR\3\2\2\2RT\3\2\2\2SU\5&\24\2TS\3\2"+
		"\2\2TU\3\2\2\2UW\3\2\2\2VX\5(\25\2WV\3\2\2\2WX\3\2\2\2X\t\3\2\2\2Yc\5"+
		"\f\7\2Zc\5\16\b\2[c\5\20\t\2\\c\5\22\n\2]c\5\24\13\2^c\5\26\f\2_c\5\30"+
		"\r\2`c\5\32\16\2ac\5\6\4\2bY\3\2\2\2bZ\3\2\2\2b[\3\2\2\2b\\\3\2\2\2b]"+
		"\3\2\2\2b^\3\2\2\2b_\3\2\2\2b`\3\2\2\2ba\3\2\2\2c\13\3\2\2\2de\7\24\2"+
		"\2ef\5\"\22\2fg\5\64\33\2gh\5\66\34\2hi\58\35\2ij\5:\36\2j\r\3\2\2\2k"+
		"l\7\25\2\2lm\5\"\22\2mn\5*\26\2no\5,\27\2o\17\3\2\2\2pq\7\26\2\2qr\5\""+
		"\22\2rs\5*\26\2st\5,\27\2t\21\3\2\2\2uv\7\27\2\2vw\5\"\22\2wx\5*\26\2"+
		"xy\5,\27\2yz\5.\30\2z\23\3\2\2\2{|\7\30\2\2|}\5\"\22\2}~\5*\26\2~\177"+
		"\5,\27\2\177\u0080\5.\30\2\u0080\25\3\2\2\2\u0081\u0082\7\31\2\2\u0082"+
		"\u0083\5\"\22\2\u0083\u0084\5*\26\2\u0084\u0085\5,\27\2\u0085\u0086\5"+
		".\30\2\u0086\27\3\2\2\2\u0087\u0088\7\32\2\2\u0088\u0089\5\"\22\2\u0089"+
		"\u008a\5*\26\2\u008a\u008b\5,\27\2\u008b\u008c\5\60\31\2\u008c\u008d\5"+
		"\62\32\2\u008d\31\3\2\2\2\u008e\u008f\7\33\2\2\u008f\u0090\5<\37\2\u0090"+
		"\u0091\5> \2\u0091\33\3\2\2\2\u0092\u0093\7\37\2\2\u0093\35\3\2\2\2\u0094"+
		"\u0095\7 \2\2\u0095\37\3\2\2\2\u0096\u0097\7\6\2\2\u0097!\3\2\2\2\u0098"+
		"\u009a\7\37\2\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3"+
		"\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e"+
		"\u009f\7\7\2\2\u009f\u00a0\5 \21\2\u00a0\u00a1\7\34\2\2\u00a1#\3\2\2\2"+
		"\u00a2\u00a4\7\37\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3"+
		"\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8"+
		"\u00a9\7\17\2\2\u00a9\u00aa\5 \21\2\u00aa\u00ab\7\36\2\2\u00ab%\3\2\2"+
		"\2\u00ac\u00ae\7\37\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2"+
		"\2\2\u00b2\u00b3\7\20\2\2\u00b3\u00b4\5 \21\2\u00b4\u00b5\7\36\2\2\u00b5"+
		"\'\3\2\2\2\u00b6\u00b8\7\37\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2"+
		"\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b9"+
		"\3\2\2\2\u00bc\u00bd\7\21\2\2\u00bd\u00be\5 \21\2\u00be\u00bf\7\36\2\2"+
		"\u00bf)\3\2\2\2\u00c0\u00c2\7\37\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3"+
		"\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c6\u00c7\7\b\2\2\u00c7\u00c8\5 \21\2\u00c8\u00c9\7\35"+
		"\2\2\u00c9+\3\2\2\2\u00ca\u00cc\7\37\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf"+
		"\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf"+
		"\u00cd\3\2\2\2\u00d0\u00d1\7\t\2\2\u00d1\u00d2\5 \21\2\u00d2\u00d3\7\35"+
		"\2\2\u00d3-\3\2\2\2\u00d4\u00d6\7\37\2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d9"+
		"\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9"+
		"\u00d7\3\2\2\2\u00da\u00db\7\n\2\2\u00db\u00dc\5 \21\2\u00dc\u00dd\7\5"+
		"\2\2\u00dd/\3\2\2\2\u00de\u00e0\7\37\2\2\u00df\u00de\3\2\2\2\u00e0\u00e3"+
		"\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3"+
		"\u00e1\3\2\2\2\u00e4\u00e5\7\13\2\2\u00e5\u00e6\5 \21\2\u00e6\u00e7\7"+
		"\35\2\2\u00e7\61\3\2\2\2\u00e8\u00ea\7\37\2\2\u00e9\u00e8\3\2\2\2\u00ea"+
		"\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2"+
		"\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00ef\7\f\2\2\u00ef\u00f0\5 \21\2\u00f0"+
		"\u00f1\7\35\2\2\u00f1\63\3\2\2\2\u00f2\u00f4\7\37\2\2\u00f3\u00f2\3\2"+
		"\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6"+
		"\u00f8\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00f9\7\b\2\2\u00f9\u00fa\5 "+
		"\21\2\u00fa\u00fb\7\36\2\2\u00fb\65\3\2\2\2\u00fc\u00fe\7\37\2\2\u00fd"+
		"\u00fc\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2"+
		"\2\2\u0100\u0102\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103\7\t\2\2\u0103"+
		"\u0104\5 \21\2\u0104\u0105\7\36\2\2\u0105\67\3\2\2\2\u0106\u0108\7\37"+
		"\2\2\u0107\u0106\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d\7\r"+
		"\2\2\u010d\u010e\5 \21\2\u010e\u010f\7\36\2\2\u010f9\3\2\2\2\u0110\u0112"+
		"\7\37\2\2\u0111\u0110\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2"+
		"\u0113\u0114\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117"+
		"\7\16\2\2\u0117\u0118\5 \21\2\u0118\u0119\7\36\2\2\u0119;\3\2\2\2\u011a"+
		"\u011c\7\37\2\2\u011b\u011a\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3"+
		"\2\2\2\u011d\u011e\3\2\2\2\u011e\u0120\3\2\2\2\u011f\u011d\3\2\2\2\u0120"+
		"\u0121\7\22\2\2\u0121\u0122\5 \21\2\u0122\u0123\7\34\2\2\u0123=\3\2\2"+
		"\2\u0124\u0126\7\37\2\2\u0125\u0124\3\2\2\2\u0126\u0129\3\2\2\2\u0127"+
		"\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u012a\3\2\2\2\u0129\u0127\3\2"+
		"\2\2\u012a\u012b\7\23\2\2\u012b\u012c\5 \21\2\u012c\u012d\7\34\2\2\u012d"+
		"?\3\2\2\2\27GLQTWb\u009b\u00a5\u00af\u00b9\u00c3\u00cd\u00d7\u00e1\u00eb"+
		"\u00f5\u00ff\u0109\u0113\u011d\u0127";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}