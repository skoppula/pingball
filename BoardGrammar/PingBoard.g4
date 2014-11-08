grammar PingBoard;
// This is the grammar definition for our ANTLR-based parser for pingball board files.

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 */
INTEGER : [0-9]+ ;
FLOAT : [-]?([0-9]*[.])?[0-9]+;
NAME : [A-Za-z_][A-Za-z_0-9]* ;
ORIENTATIONVALUE : '0'|'90'|'180'|'270';
COMMENT : '#' '.'* ;
WHITESPACE : [ \t]+;
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
BOARDINITID : 'board';
BALLID : 'ball';
SQUAREBUMPERID : 'squareBumper';
CIRCLEBUMPERID : 'circleBumper';
TRIANGLEBUMPERID : 'triangleBumper';
RIGHTFLIPPERID : 'rightFlipper';
LEFTFLIPPERID : 'leftFlipper';
ABSORBERID : 'absorber';
FIREID : 'fire';


/*
 * These are the parser rules. They define the structures used by the parser. 
 */
whitespace : WHITESPACE;
equals : EQUALS;
name : NAMEID equals NAME;
gravity : GRAVITYID equals FLOAT;
friction1 : FRICTION1ID equals FLOAT;
friction2 : FRICTION2ID equals FLOAT;
intX : XID equals INTEGER;
intY : YID equals INTEGER;
orientation : ORIENTATIONID equals ORIENTATIONVALUE;
width : WIDTHID equals INTEGER;
height : HEIGHTID equals INTEGER;
floatX : XID equals FLOAT;
floatY : YID equals FLOAT;
xVelocity : XVELOCITYID equals FLOAT;
yVelocity : YVELOCITYID equals FLOAT;
trigger : TRIGGERID equals NAME;
action : ACTIONID equals NAME;



root : board EOF;
board : boardInit;
irrelevantLine : COMMENT? ; // either the line is blank or the line has a comment
boardInit : BOARDINITID name gravity? friction1? friction2?;
bodyLine : (ball | squareBumper | circleBumper | triangleBumper | rightFlipper | leftFlipper | absorber | fire | irrelevantLine);
ball : BALLID name floatX floatY xVelocity yVelocity ;
squareBumper : SQUAREBUMPERID name intX intY;
circleBumper : CIRCLEBUMPERID name intX intY;
triangleBumper : TRIANGLEBUMPERID name intX intY orientation ;
rightFlipper : RIGHTFLIPPERID name intX intY orientation ;
leftFlipper : LEFTFLIPPERID name intX intY orientation ;
absorber : ABSORBERID name intX intY width height ;
fire : FIREID trigger action ;