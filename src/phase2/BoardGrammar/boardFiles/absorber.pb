board name=Absorber gravity = 25.0

# define a ball
ball name=BallB x=19.25 y=3.25 xVelocity=0.1 yVelocity=0.1
ball name=BallC x=1.25 y=5.25 xVelocity=0.1 yVelocity=0.1

# defining a triangle bumper
triangleBumper name=Tri x=19 y=0 orientation=180

# defining some circle bumpers
circleBumper name=CircleA x=1 y=10
circleBumper name=CircleB x=2 y=10

# define an absorber to catch the ball
 absorber name=Abs x=0 y=18 width=20 height=2
 
# define events between gizmos