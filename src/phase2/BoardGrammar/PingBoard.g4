grammar PingBoard;
// This is the grammar definition for our ANTLR-based parser for pingball board files.

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 */
BOARDINITID : 'board';
COMMENT : '#' [ -~]* ;
// ORIENTATIONVALUE : '0'|'90'|'180'|'270';
EQUALS : '=';
NAMEID : 'name';
XID : 'x';
YID : 'y';
ORIENTATIONID : 'orientation';
WIDTHID : 'width';
HEIGHTID : 'height';
XVELOCITYID : 'xVelocity';
YVELOCITYID : 'yVelocity';
GRAVITYID : 'gravity';
FRICTION1ID : 'friction1';
FRICTION2ID : 'friction2';
TRIGGERID : 'trigger';
ACTIONID : 'action';
BALLID : 'ball';
SQUAREBUMPERID : 'squareBumper';
CIRCLEBUMPERID : 'circleBumper';
TRIANGLEBUMPERID : 'triangleBumper';
RIGHTFLIPPERID : 'rightFlipper';
LEFTFLIPPERID : 'leftFlipper';
ABSORBERID : 'absorber';
FIREID : 'fire';
NAME : [A-Za-z_][A-Za-z_0-9]* ;
INTEGER : ('0'..'9')+ ;
FLOAT : [-]?([0-9]*[.])?[0-9]+;
WHITESPACE : [ \t]+;
NEWLINE : [\n]+;


/*
 * These are the parser rules. They define the structures used by the parser. 
 */
root : board EOF;
board : bodyLine*; // may contain multiple bodyLines
boardInit : BOARDINITID name gravity? friction1? friction2?;
bodyLine : WHITESPACE* (boardInit | ball | squareBumper | circleBumper | triangleBumper | flipper | absorber | fire | comment | newline) WHITESPACE*;
ball : BALLID name floatX floatY xVelocity yVelocity;
squareBumper : SQUAREBUMPERID name intX intY;
circleBumper : CIRCLEBUMPERID name intX intY;
triangleBumper : TRIANGLEBUMPERID name intX intY orientation;
flipper : (RIGHTFLIPPERID | LEFTFLIPPERID) name intX intY orientation;
absorber : ABSORBERID name intX intY width height;
fire : FIREID trigger action;

whitespace : WHITESPACE;
newline: NEWLINE;
comment : COMMENT; // either the line is blank or the line has a comment
equals : WHITESPACE* EQUALS WHITESPACE*;
name : WHITESPACE* NAMEID equals NAME;
gravity : WHITESPACE* GRAVITYID equals (FLOAT | INTEGER);
friction1 : WHITESPACE* FRICTION1ID equals (FLOAT | INTEGER);
friction2 : WHITESPACE* FRICTION2ID equals (FLOAT | INTEGER);
intX : WHITESPACE* XID equals INTEGER;
intY : WHITESPACE* YID equals INTEGER;
orientation : WHITESPACE* ORIENTATIONID equals INTEGER;
width : WHITESPACE* WIDTHID equals INTEGER;
height : WHITESPACE* HEIGHTID equals INTEGER;
floatX : WHITESPACE* XID equals (FLOAT | INTEGER);
floatY : WHITESPACE* YID equals (FLOAT | INTEGER);
xVelocity : WHITESPACE* XVELOCITYID equals (FLOAT | INTEGER);
yVelocity : WHITESPACE* YVELOCITYID equals (FLOAT | INTEGER);
trigger : WHITESPACE* TRIGGERID equals NAME;
action : WHITESPACE* ACTIONID equals NAME;

