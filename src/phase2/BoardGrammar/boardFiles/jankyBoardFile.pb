board name =        JankyBoard        

       #define a     ball
   ball name    =  Ball  x  =  10  y =  4.5   xVelocity  =  0 yVelocity  = 5.5        

   
   squareBumper name =  SQUARE x  =  7 y  =  7  
   
    circleBumper name=circle x=0 y=0	
    triangleBumper name=triangle   x=4     y=3  orientation = 90	     
      rightFlipper name=right x=5 y=3 orientation=0
    	leftFlipper name=left x=15 y=15 orientation=180 	
    	
    triangleBumper name=triangle2  x=19 y=2 orientation = 90
    
    
	absorber name=abs x=0 y=18 width=20 height=1

		fire    trigger  =    abs    action  =	SQUARE
   fire trigger=abs action=right
   fire trigger=SQUARE action    = left
      fire trigger =abs action=abs
      fire trigger=abs2 action=abs2
   #
   