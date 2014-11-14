board name=ExampleB gravity = 10.0

# define a ball
ball name=BallA x=1.8 y=4.5 xVelocity=10.4 yVelocity=10.3 
ball name=BallB x=10.0 y=13.0 xVelocity=-3.4 yVelocity=-7.3 
ball name=BallC x=4.0 y=13.0 xVelocity=-3.4 yVelocity=-7.3 
ball name=BallD x=4.0 y=16.0 xVelocity=-3.4 yVelocity=-7.3 

# define some bumpers
squareBumper name=SquareA x=8 y=8
squareBumper name=SquareB x=2 y=2
squareBumper name=SquareC x=5 y=5

circleBumper name=Circle x=14 y=14
triangleBumper name=Tri x=19 y=0 orientation=90

# define some flippers
leftFlipper name=FlipL x=10 y=7 orientation=90
rightFlipper name=FlipR x=15 y=7 orientation=0


# define an absorber to catch the ball
 absorber name=Abs x=10 y=17 width=10 height=2 

# define events between gizmos
fire trigger=SquareA action=FlipL
fire trigger=SquareB action=FlipL
fire trigger=SquareC action=FlipL
fire trigger=Tri action=FlipR
fire trigger=Circle action=FlipR

# make the absorber self-triggering
fire trigger=Abs action=Abs 