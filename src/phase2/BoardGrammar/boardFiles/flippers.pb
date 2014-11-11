board name=sampleBoard1 gravity=20.0 friction1=0.020 friction2=0.020
# This board is meant for stand-alone play. It is a loose 
# representation of the screenshot in the project handout.


  # define a ball
  ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5

  # define a series of square bumpers
  squareBumper name=Square0 x=0 y=2
  squareBumper name=Square1 x=1 y=2
  
  # define a series of circle bumpers
  circleBumper name=Circle4 x=4 y=3
  circleBumper name=Circle5 x=5 y=4
  
  # define some triangular bumpers
  triangleBumper name=Tri1 x=8 y=9 orientation=270
  triangleBumper name=Tri2 x=11 y=9 orientation=180
  
  # add some flippers
  leftFlipper name=FlipL1 x=8 y=2 orientation=0
  rightFlipper name=FlipR1 x=10 y=2 orientation=0
  leftFlipper name=FlipL2 x=8 y=7 orientation=0
  rightFlipper name=FlipR2 x=10 y=7 orientation=0

