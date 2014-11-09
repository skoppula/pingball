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
		COMMENT=1, ORIENTATIONVALUE=2, EQUALS=3, NAMEID=4, XID=5, YID=6, ORIENTATIONID=7, 
		WIDTHID=8, HEIGHTID=9, XVELOCITYID=10, YVELOCITYID=11, GRAVITYID=12, FRICTION1ID=13, 
		FRICTION2ID=14, TRIGGERID=15, ACTIONID=16, BOARDINITID=17, BALLID=18, 
		SQUAREBUMPERID=19, CIRCLEBUMPERID=20, TRIANGLEBUMPERID=21, RIGHTFLIPPERID=22, 
		LEFTFLIPPERID=23, ABSORBERID=24, FIREID=25, NAME=26, INTEGER=27, FLOAT=28, 
		WHITESPACE=29, NEWLINE=30;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT", "ORIENTATIONVALUE", "'='", "'name'", "'x'", "'y'", 
		"'orientation'", "'width'", "'height'", "'xVelocity'", "'yVelocity'", 
		"'gravity'", "'friction1'", "'friction2'", "'trigger'", "'action'", "'board'", 
		"'ball'", "'squareBumper'", "'circleBumper'", "'triangleBumper'", "'rightFlipper'", 
		"'leftFlipper'", "'absorber'", "'fire'", "NAME", "INTEGER", "FLOAT", "WHITESPACE", 
		"NEWLINE"
	};
	public static final int
		RULE_whitespace = 0, RULE_newline = 1, RULE_equals = 2, RULE_name = 3, 
		RULE_gravity = 4, RULE_friction1 = 5, RULE_friction2 = 6, RULE_intX = 7, 
		RULE_intY = 8, RULE_orientation = 9, RULE_width = 10, RULE_height = 11, 
		RULE_floatX = 12, RULE_floatY = 13, RULE_xVelocity = 14, RULE_yVelocity = 15, 
		RULE_trigger = 16, RULE_action = 17, RULE_root = 18, RULE_board = 19, 
		RULE_irrelevantLine = 20, RULE_boardInit = 21, RULE_bodyLine = 22, RULE_ball = 23, 
		RULE_squareBumper = 24, RULE_circleBumper = 25, RULE_triangleBumper = 26, 
		RULE_rightFlipper = 27, RULE_leftFlipper = 28, RULE_absorber = 29, RULE_fire = 30;
	public static final String[] ruleNames = {
		"whitespace", "newline", "equals", "name", "gravity", "friction1", "friction2", 
		"intX", "intY", "orientation", "width", "height", "floatX", "floatY", 
		"xVelocity", "yVelocity", "trigger", "action", "root", "board", "irrelevantLine", 
		"boardInit", "bodyLine", "ball", "squareBumper", "circleBumper", "triangleBumper", 
		"rightFlipper", "leftFlipper", "absorber", "fire"
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
		enterRule(_localctx, 0, RULE_whitespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(WHITESPACE);
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
		enterRule(_localctx, 2, RULE_newline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); match(NEWLINE);
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
		enterRule(_localctx, 4, RULE_equals);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(EQUALS);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
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
		enterRule(_localctx, 6, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); match(NAMEID);
			setState(69); equals();
			setState(70); match(NAME);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode GRAVITYID() { return getToken(PingBoardParser.GRAVITYID, 0); }
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
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
		enterRule(_localctx, 8, RULE_gravity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(GRAVITYID);
			setState(73); equals();
			setState(74); match(FLOAT);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FRICTION1ID() { return getToken(PingBoardParser.FRICTION1ID, 0); }
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
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
		enterRule(_localctx, 10, RULE_friction1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(FRICTION1ID);
			setState(77); equals();
			setState(78); match(FLOAT);
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
		public TerminalNode FRICTION2ID() { return getToken(PingBoardParser.FRICTION2ID, 0); }
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
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
		enterRule(_localctx, 12, RULE_friction2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(FRICTION2ID);
			setState(81); equals();
			setState(82); match(FLOAT);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
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
		enterRule(_localctx, 14, RULE_intX);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); match(XID);
			setState(85); equals();
			setState(86); match(INTEGER);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
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
		enterRule(_localctx, 16, RULE_intY);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); match(YID);
			setState(89); equals();
			setState(90); match(INTEGER);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
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
		enterRule(_localctx, 18, RULE_orientation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); match(ORIENTATIONID);
			setState(93); equals();
			setState(94); match(ORIENTATIONVALUE);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
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
		enterRule(_localctx, 20, RULE_width);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(WIDTHID);
			setState(97); equals();
			setState(98); match(INTEGER);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
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
		enterRule(_localctx, 22, RULE_height);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(HEIGHTID);
			setState(101); equals();
			setState(102); match(INTEGER);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
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
		enterRule(_localctx, 24, RULE_floatX);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104); match(XID);
			setState(105); equals();
			setState(106); match(FLOAT);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
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
		enterRule(_localctx, 26, RULE_floatY);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); match(YID);
			setState(109); equals();
			setState(110); match(FLOAT);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
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
		enterRule(_localctx, 28, RULE_xVelocity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(XVELOCITYID);
			setState(113); equals();
			setState(114); match(FLOAT);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(PingBoardParser.FLOAT, 0); }
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
		enterRule(_localctx, 30, RULE_yVelocity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); match(YVELOCITYID);
			setState(117); equals();
			setState(118); match(FLOAT);
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
		public TerminalNode TRIGGERID() { return getToken(PingBoardParser.TRIGGERID, 0); }
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
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
		enterRule(_localctx, 32, RULE_trigger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(TRIGGERID);
			setState(121); equals();
			setState(122); match(NAME);
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
		public EqualsContext equals() {
			return getRuleContext(EqualsContext.class,0);
		}
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
		enterRule(_localctx, 34, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); match(ACTIONID);
			setState(125); equals();
			setState(126); match(NAME);
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
		enterRule(_localctx, 36, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); board();
			setState(129); match(EOF);
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
		public BoardInitContext boardInit() {
			return getRuleContext(BoardInitContext.class,0);
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
		enterRule(_localctx, 38, RULE_board);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); boardInit();
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
		enterRule(_localctx, 40, RULE_irrelevantLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			_la = _input.LA(1);
			if (_la==COMMENT) {
				{
				setState(133); match(COMMENT);
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
		enterRule(_localctx, 42, RULE_boardInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); match(BOARDINITID);
			setState(137); name();
			setState(139);
			_la = _input.LA(1);
			if (_la==GRAVITYID) {
				{
				setState(138); gravity();
				}
			}

			setState(142);
			_la = _input.LA(1);
			if (_la==FRICTION1ID) {
				{
				setState(141); friction1();
				}
			}

			setState(145);
			_la = _input.LA(1);
			if (_la==FRICTION2ID) {
				{
				setState(144); friction2();
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
		enterRule(_localctx, 44, RULE_bodyLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			switch (_input.LA(1)) {
			case BALLID:
				{
				setState(147); ball();
				}
				break;
			case SQUAREBUMPERID:
				{
				setState(148); squareBumper();
				}
				break;
			case CIRCLEBUMPERID:
				{
				setState(149); circleBumper();
				}
				break;
			case TRIANGLEBUMPERID:
				{
				setState(150); triangleBumper();
				}
				break;
			case RIGHTFLIPPERID:
				{
				setState(151); rightFlipper();
				}
				break;
			case LEFTFLIPPERID:
				{
				setState(152); leftFlipper();
				}
				break;
			case ABSORBERID:
				{
				setState(153); absorber();
				}
				break;
			case FIREID:
				{
				setState(154); fire();
				}
				break;
			case EOF:
			case COMMENT:
				{
				setState(155); irrelevantLine();
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
		enterRule(_localctx, 46, RULE_ball);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(BALLID);
			setState(159); name();
			setState(160); floatX();
			setState(161); floatY();
			setState(162); xVelocity();
			setState(163); yVelocity();
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
		enterRule(_localctx, 48, RULE_squareBumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); match(SQUAREBUMPERID);
			setState(166); name();
			setState(167); intX();
			setState(168); intY();
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
		enterRule(_localctx, 50, RULE_circleBumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170); match(CIRCLEBUMPERID);
			setState(171); name();
			setState(172); intX();
			setState(173); intY();
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
		enterRule(_localctx, 52, RULE_triangleBumper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); match(TRIANGLEBUMPERID);
			setState(176); name();
			setState(177); intX();
			setState(178); intY();
			setState(179); orientation();
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
		enterRule(_localctx, 54, RULE_rightFlipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); match(RIGHTFLIPPERID);
			setState(182); name();
			setState(183); intX();
			setState(184); intY();
			setState(185); orientation();
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
		enterRule(_localctx, 56, RULE_leftFlipper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187); match(LEFTFLIPPERID);
			setState(188); name();
			setState(189); intX();
			setState(190); intY();
			setState(191); orientation();
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
		enterRule(_localctx, 58, RULE_absorber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); match(ABSORBERID);
			setState(194); name();
			setState(195); intX();
			setState(196); intY();
			setState(197); width();
			setState(198); height();
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
		enterRule(_localctx, 60, RULE_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200); match(FIREID);
			setState(201); trigger();
			setState(202); action();
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3 \u00cf\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\5\26\u0089\n\26\3\27\3\27\3\27"+
		"\5\27\u008e\n\27\3\27\5\27\u0091\n\27\3\27\5\27\u0094\n\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u009f\n\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \2\2"+
		"!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\2"+
		"\u00bb\2@\3\2\2\2\4B\3\2\2\2\6D\3\2\2\2\bF\3\2\2\2\nJ\3\2\2\2\fN\3\2\2"+
		"\2\16R\3\2\2\2\20V\3\2\2\2\22Z\3\2\2\2\24^\3\2\2\2\26b\3\2\2\2\30f\3\2"+
		"\2\2\32j\3\2\2\2\34n\3\2\2\2\36r\3\2\2\2 v\3\2\2\2\"z\3\2\2\2$~\3\2\2"+
		"\2&\u0082\3\2\2\2(\u0085\3\2\2\2*\u0088\3\2\2\2,\u008a\3\2\2\2.\u009e"+
		"\3\2\2\2\60\u00a0\3\2\2\2\62\u00a7\3\2\2\2\64\u00ac\3\2\2\2\66\u00b1\3"+
		"\2\2\28\u00b7\3\2\2\2:\u00bd\3\2\2\2<\u00c3\3\2\2\2>\u00ca\3\2\2\2@A\7"+
		"\37\2\2A\3\3\2\2\2BC\7 \2\2C\5\3\2\2\2DE\7\5\2\2E\7\3\2\2\2FG\7\6\2\2"+
		"GH\5\6\4\2HI\7\34\2\2I\t\3\2\2\2JK\7\16\2\2KL\5\6\4\2LM\7\36\2\2M\13\3"+
		"\2\2\2NO\7\17\2\2OP\5\6\4\2PQ\7\36\2\2Q\r\3\2\2\2RS\7\20\2\2ST\5\6\4\2"+
		"TU\7\36\2\2U\17\3\2\2\2VW\7\7\2\2WX\5\6\4\2XY\7\35\2\2Y\21\3\2\2\2Z[\7"+
		"\b\2\2[\\\5\6\4\2\\]\7\35\2\2]\23\3\2\2\2^_\7\t\2\2_`\5\6\4\2`a\7\4\2"+
		"\2a\25\3\2\2\2bc\7\n\2\2cd\5\6\4\2de\7\35\2\2e\27\3\2\2\2fg\7\13\2\2g"+
		"h\5\6\4\2hi\7\35\2\2i\31\3\2\2\2jk\7\7\2\2kl\5\6\4\2lm\7\36\2\2m\33\3"+
		"\2\2\2no\7\b\2\2op\5\6\4\2pq\7\36\2\2q\35\3\2\2\2rs\7\f\2\2st\5\6\4\2"+
		"tu\7\36\2\2u\37\3\2\2\2vw\7\r\2\2wx\5\6\4\2xy\7\36\2\2y!\3\2\2\2z{\7\21"+
		"\2\2{|\5\6\4\2|}\7\34\2\2}#\3\2\2\2~\177\7\22\2\2\177\u0080\5\6\4\2\u0080"+
		"\u0081\7\34\2\2\u0081%\3\2\2\2\u0082\u0083\5(\25\2\u0083\u0084\7\2\2\3"+
		"\u0084\'\3\2\2\2\u0085\u0086\5,\27\2\u0086)\3\2\2\2\u0087\u0089\7\3\2"+
		"\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089+\3\2\2\2\u008a\u008b"+
		"\7\23\2\2\u008b\u008d\5\b\5\2\u008c\u008e\5\n\6\2\u008d\u008c\3\2\2\2"+
		"\u008d\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u0091\5\f\7\2\u0090\u008f"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0094\5\16\b\2"+
		"\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094-\3\2\2\2\u0095\u009f\5"+
		"\60\31\2\u0096\u009f\5\62\32\2\u0097\u009f\5\64\33\2\u0098\u009f\5\66"+
		"\34\2\u0099\u009f\58\35\2\u009a\u009f\5:\36\2\u009b\u009f\5<\37\2\u009c"+
		"\u009f\5> \2\u009d\u009f\5*\26\2\u009e\u0095\3\2\2\2\u009e\u0096\3\2\2"+
		"\2\u009e\u0097\3\2\2\2\u009e\u0098\3\2\2\2\u009e\u0099\3\2\2\2\u009e\u009a"+
		"\3\2\2\2\u009e\u009b\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f"+
		"/\3\2\2\2\u00a0\u00a1\7\24\2\2\u00a1\u00a2\5\b\5\2\u00a2\u00a3\5\32\16"+
		"\2\u00a3\u00a4\5\34\17\2\u00a4\u00a5\5\36\20\2\u00a5\u00a6\5 \21\2\u00a6"+
		"\61\3\2\2\2\u00a7\u00a8\7\25\2\2\u00a8\u00a9\5\b\5\2\u00a9\u00aa\5\20"+
		"\t\2\u00aa\u00ab\5\22\n\2\u00ab\63\3\2\2\2\u00ac\u00ad\7\26\2\2\u00ad"+
		"\u00ae\5\b\5\2\u00ae\u00af\5\20\t\2\u00af\u00b0\5\22\n\2\u00b0\65\3\2"+
		"\2\2\u00b1\u00b2\7\27\2\2\u00b2\u00b3\5\b\5\2\u00b3\u00b4\5\20\t\2\u00b4"+
		"\u00b5\5\22\n\2\u00b5\u00b6\5\24\13\2\u00b6\67\3\2\2\2\u00b7\u00b8\7\30"+
		"\2\2\u00b8\u00b9\5\b\5\2\u00b9\u00ba\5\20\t\2\u00ba\u00bb\5\22\n\2\u00bb"+
		"\u00bc\5\24\13\2\u00bc9\3\2\2\2\u00bd\u00be\7\31\2\2\u00be\u00bf\5\b\5"+
		"\2\u00bf\u00c0\5\20\t\2\u00c0\u00c1\5\22\n\2\u00c1\u00c2\5\24\13\2\u00c2"+
		";\3\2\2\2\u00c3\u00c4\7\32\2\2\u00c4\u00c5\5\b\5\2\u00c5\u00c6\5\20\t"+
		"\2\u00c6\u00c7\5\22\n\2\u00c7\u00c8\5\26\f\2\u00c8\u00c9\5\30\r\2\u00c9"+
		"=\3\2\2\2\u00ca\u00cb\7\33\2\2\u00cb\u00cc\5\"\22\2\u00cc\u00cd\5$\23"+
		"\2\u00cd?\3\2\2\2\7\u0088\u008d\u0090\u0093\u009e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}