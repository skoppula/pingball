grammar PingBoard;
// This is the grammar definition for our ANTLR-based parser for pingball board files.

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 */
BOARDINITID : 'board';
COMMENT : '#' [ -~]* ;
ORIENTATIONVALUE : '0'|'90'|'180'|'270';
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
board : boardInit bodyLine*; // may contain multiple bodyLines
boardInit : BOARDINITID name gravity? friction1? friction2?;
bodyLine : WHITESPACE* (ball | squareBumper | circleBumper | triangleBumper | rightFlipper | leftFlipper | absorber | fire | comment | newline);
ball : BALLID name floatX floatY xVelocity yVelocity;
squareBumper : SQUAREBUMPERID name intX intY;
circleBumper : CIRCLEBUMPERID name intX intY;
triangleBumper : TRIANGLEBUMPERID name intX intY orientation;
rightFlipper : RIGHTFLIPPERID name intX intY orientation;
leftFlipper : LEFTFLIPPERID name intX intY orientation;
absorber : ABSORBERID name intX intY width height;
fire : FIREID trigger action;

whitespace : WHITESPACE;
newline: NEWLINE;
comment : COMMENT; // either the line is blank or the line has a comment
equals : EQUALS;
name : WHITESPACE* NAMEID equals NAME;
gravity : WHITESPACE* GRAVITYID equals FLOAT;
friction1 : WHITESPACE* FRICTION1ID equals FLOAT;
friction2 : WHITESPACE* FRICTION2ID equals FLOAT;
intX : WHITESPACE* XID equals INTEGER;
intY : WHITESPACE* YID equals INTEGER;
orientation : WHITESPACE* ORIENTATIONID equals ORIENTATIONVALUE;
width : WHITESPACE* WIDTHID equals INTEGER;
height : WHITESPACE* HEIGHTID equals INTEGER;
floatX : WHITESPACE* XID equals FLOAT;
floatY : WHITESPACE* YID equals FLOAT;
xVelocity : WHITESPACE* XVELOCITYID equals FLOAT;
yVelocity : WHITESPACE* YVELOCITYID equals FLOAT;
trigger : WHITESPACE* TRIGGERID equals NAME;
action : WHITESPACE* ACTIONID equals NAME;

