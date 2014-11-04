grammar PingBoard;

INTEGER : [0-9]+ ;
FLOAT : -?([0-9]+.[0-9]*|.?[0-9]+) ;
NAME : [A-Za-z_][A-Za-z_0-9]* ;
ORIENTATIONVALUE : '0'|'90'|'180'|'270';
COMMENT : '#' '.'* ;
WHITESPACE :[ \t];
EXTRAWHITESPACE : [ \t]*;
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

// Our convention is that any ID token should include the whitespace BEFORE it. Equals signs are responsible for their surrounding whitespace.
whitespace : WHITESPACE EXTRAWHITESPACE;
equals : EXTRAWHITESPACE EQUALS EXTRAWHITESPACE;
name : whitespace NAMEID equals NAME;
gravity : whitespace GRAVITYID equals FLOAT;
friction1 : whitespace FRICTION1ID equals FLOAT;
friction2 : whitespace FRICTION2ID equals FLOAT;
intX : whitespace XID equals INTEGER;
intY : whitespace YID equals INTEGER;
orientation : whitespace ORIENTATIONID equals ORIENTATIONVALUE;
width : whitespace WIDTHID equals INTEGER;
height : whitespace HEIGHTID equals INTEGER;
floatX : whitespace XID equals FLOAT;
floatY : whitespace YID equals FLOAT;
xVelocity : whitespace XVELOCITYID equals FLOAT;
yVelocity : whitespace YVELOCITYID equals FLOAT;
trigger : whitespace TRIGGERID equals NAME;
action : whitespace ACTIONID equals NAME;



root : board EOF;
board : boardInit bodyLine*;
irrelevantLine : ( '' | comment) ; // either the line is only whitespace, in which case bodyLine will grab that whitespace, or the line has a comment
comment : COMMENT;
boardInit : BOARDINITID name gravity? friction1? friction2?;
bodyLine : EXTRAWHITESPACE (ball | squareBumper | circleBumper | triangleBumper | rightFlipper | leftFlipper | absorber | fire | irrelevant) EXTRAWHITESPACE;
ball : BALLID name floatX floatY xVelocity yVelocity ;
squareBumper : SQUAREBUMPERID name intX intY;
circleBumper : CIRCLEBUMPERID name intX intY;
triangleBumper : TRIANGLEBUMPERID name intX intY orientation ;
rightFlipper : RIGHTFLIPPERID name intX intY orientation ;
leftFlipper : LEFTFLIPPERID name intX intY orientation ;
absorber : ABSORBERID name intX intY width height ;
fire : FIREID trigger action ;