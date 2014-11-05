package phase1;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import phase1.Flipper.FlipperRotation;
import phase1.Util.InvalidInvariantException;
import physics.Vect;
import physics.LineSegment;

public class GameObjectTest {

	///////////////////TESTING STRATEGY////////////////////
	/*
	 * Testing the following classes:
	 * 1. Ball
	 * 2. CircleBumper
	 * 3. SquareBumper
	 * 4. Absorber
	 * 5. TriangleBumper
	 * 6. Flipper 
	 * 7. Boundary
	 * 
	 * Correct behavior is determined by physics calculations
	 */
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	////////////////////////BALL TESTS//////////////////////
	/*
	 * Ball Testing Strategy:
	 * We partitioned inputs by
	 * 1. UpdateCenter
	 * 2. UpdateVelocity: positive, negative
	 * 3. toString
	 * 4. TimeUntilCollision: Infinity, 0, ~5
	 * 5. ReachWhenHit
	 * Correct behaviour is determined by physics calculations
	 */
	
	@Test
	public void testBallUpdateCenter() throws InvalidInvariantException {
		Ball ball = new Ball(5,5,0.25,new Vect(0.3,0.1));
		ball.updateCenterX(5.5);
		assertTrue(ball.getCenterX()==5.5);
		ball.updateCenterY(6);
		assertTrue(ball.getCenterY()==6);
	}
	
	@Test
	public void testBallUpdateVelocity() throws InvalidInvariantException {
		Ball ball = new Ball(5,5,0.25,new Vect(0.3,0.1));
		ball.updateVelocity(new Vect(0.4, 0.2));
		assertEquals(ball.getVelocity(), ball.getVelocity());
	}
	
	@Test
	public void testBallUpdateVelocityNegative() throws InvalidInvariantException {
		Ball ball = new Ball(5,5,0.25,new Vect(-0.3,0.1));
		ball.updateVelocity(new Vect(0.0, 0.2));
		assertEquals(ball.getVelocity(), ball.getVelocity());
	}

	@Test
	public void testBallToString() throws InvalidInvariantException {
		Ball ball = new Ball(5,5,0.25,new Vect(0.3,0.1));
		assertEquals(ball.toString(), "*");
	}
	
	@Test
	public void testBallTimeUntilCollisionInfinity() throws InvalidInvariantException {
		Ball ball1 = new Ball(5,5,0.25,new Vect(-0.3,0.1));
		Ball ball2 = new Ball(5,6,0.25,new Vect(0.4,0.5));
		double infinity = Double.POSITIVE_INFINITY;
		assertTrue(ball1.timeUntilCollision(ball2)==infinity);
	}
	
	@Test
	public void testBallTimeUntilCollisionAdjacent() throws InvalidInvariantException {
		Ball ball1 = new Ball(5,5,0.5,new Vect(0.4,0.1));
		Ball ball2 = new Ball(6,5,0.5,new Vect(-0.4,0.1));
		assertTrue(ball1.timeUntilCollision(ball2)==0.0);
	}
	
	
	@Test
	public void testBallTimeUntilCollisionClose() throws InvalidInvariantException {
		Ball ball1 = new Ball(5,5,0.25,new Vect(0.4,0.1));
		Ball ball2 = new Ball(10,5,0.25,new Vect(-0.4,0.1));
		double collisionTime = ball1.timeUntilCollision(ball2);
		assertTrue(collisionTime>4.0 && collisionTime<6.0);
	}
	
	@Test
	public void testBallReactWhenHit() throws InvalidInvariantException {
		Ball thisBall = new Ball(5,5,0.25, new Vect(0,0));
		Vect otherVelocity = new Vect(0,1);
		Ball otherBall =new Ball(5,3,0.25, otherVelocity);
		try {
			thisBall.reactWhenHit(otherBall, thisBall.timeUntilCollision(otherBall));
			assertTrue(Math.abs(thisBall.getVelocityX()-otherVelocity.x())<0.1);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	/////////////////////TESTING CIRCLEBUMPER////////////////
	/*
	 * Testing Strategy for CircleBumper:
	 * 1. toString()
	 * 2. TimeUntilCollision: Infinity, zero, close
	 * 3. ReactWhenHit: Perpendicular to Tangent, 45 degrees to Tangent
	 *
	 * Correct behaviour is determined by physics calculations
	 * 	 */
	
	@Test
	public void testCircleBumperToString() throws InvalidInvariantException {
		CircleBumper cb = new CircleBumper(5,5);
		assertEquals(cb.toString(), "o");
	}
	
	@Test
	public void testCircleBumperTimeUntilCollisionInfinity() throws InvalidInvariantException {
		CircleBumper cb = new CircleBumper(5,5);
		Ball ball2 = new Ball(10,5,0.25,new Vect(0.4,0.1));
		double infinity = Double.POSITIVE_INFINITY;
		assertTrue(cb.timeUntilCollision(ball2)==infinity);	
		}
	
	@Test
	public void testCircleBumperTimeUntilCollisionAdjacent() throws InvalidInvariantException {
		CircleBumper cb = new CircleBumper(5,5);
		Ball ball2 = new Ball(6,5,0.5,new Vect(-0.1,0));
		assertTrue(cb.timeUntilCollision(ball2)==0.0);
	}
	
	@Test
	public void testCircleBumperTimeUntilCollisionClose() throws InvalidInvariantException {
		CircleBumper cb = new CircleBumper(5,5);
		Ball ball2 = new Ball(10,5,0.25,new Vect(-1,0));
		double ETA = cb.timeUntilCollision(ball2);
		assertTrue(ETA<6.0 && ETA>3.0);
	}
	
	@Test
	public void testCircleBumperReactWhenHitPerpendicularToTangent() throws InvalidInvariantException {
		CircleBumper cb = new CircleBumper(5,5);
		Vect oldVelocity = new Vect(0, 1);
		Ball ball2 = new Ball(6,3,0.25,oldVelocity);
		try {
			int initialVelocityY = 1;
			int gravity = 25;
			double finalVelocityY = initialVelocityY+gravity*cb.timeUntilCollision(ball2);
			cb.reactWhenHit(ball2, cb.timeUntilCollision(ball2));
			assertTrue(ball2.getVelocityY()<0);
			assertTrue(ball2.getVelocityY()<-finalVelocityY);
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
	}
	
	@Test
	public void testCircleBumperReactWhenHit45degreesToTangentFromTop() throws InvalidInvariantException {
		CircleBumper cb = new CircleBumper(5,5);
		Ball ball2 = new Ball(4,4,0.25,new Vect(1, 1));
		try {
			int initialVelocityY = 1;
			int gravity = 25;
			double finalVelocity = initialVelocityY+gravity*cb.timeUntilCollision(ball2);
			cb.reactWhenHit(ball2, cb.timeUntilCollision(ball2));
			assertTrue(ball2.getVelocityX()<1);
			assertTrue(ball2.getVelocityY()<Math.abs(finalVelocity));

		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
	}
	
	///////////////////////TESTING SQUARE BUMPER////////////////////
	/*
	 * Testing Strategy for Square Bumper:
	 * 1. toString()
	 * 2. TimeUntilCollision: Infinity, zero, close
	 * 3. ReactWhenHit: Perpendicular to Tangent, 45 degrees to Tangent
	 * 
	 * Correct behaviour is determined by physics calculations
	 */
	@Test
	public void testSquareBumperToString() throws InvalidInvariantException {
		SquareBumper sb = new SquareBumper(5,5);
		assertEquals(sb.toString(), "#");
	}
	
	@Test
	public void testSquareBumperTimeUntilCollisionInfinity() throws InvalidInvariantException {
		SquareBumper sb = new SquareBumper(5,5);
		Ball ball2 = new Ball(10,5,0.25,new Vect(0.4,0.1));
		double infinity = Double.POSITIVE_INFINITY;
		assertTrue(sb.timeUntilCollision(ball2)==infinity);	
		}
	
	@Test
	public void testSquareBumperTimeUntilCollisionAdjacent() throws InvalidInvariantException {
		SquareBumper sb = new SquareBumper(5,5);
		Ball ball2 = new Ball(6,5,1.0,new Vect(-1,0));
		assertTrue(sb.timeUntilCollision(ball2)==0.0);
	}
	
	@Test
	public void testSquareBumperTimeUntilCollisionClose() throws InvalidInvariantException {
		SquareBumper sb = new SquareBumper(3,5);
		Ball OneDimensionalSphere = new Ball(5,5,1.0, new Vect(-1,0));
		assertTrue(sb.timeUntilCollision(OneDimensionalSphere)==0.0);
	}
	
	@Test
	public void testSquareBumperReactWhenHit90degreesFromSide() throws InvalidInvariantException {
		SquareBumper sb = new SquareBumper(5,5);
		Vect oldVelocity2 = new Vect(-10.0, 0);
		Ball ball2 = new Ball(8,6,0.25,oldVelocity2);
		try {
			sb.reactWhenHit(ball2, sb.timeUntilCollision(ball2));
			assertTrue(Math.abs(ball2.getVelocityX()-(-1.0*oldVelocity2.x()))<1.0);
			assertTrue(ball2.getVelocityY()>0 && ball2.getVelocityY()<10);
			
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
	}
	
	@Test
	public void testSquareBumperReactWhenHitAbout45degreesFromSide() throws InvalidInvariantException {
		SquareBumper sb = new SquareBumper(5,5);
		Vect oldVelocity = new Vect(-1.0, -1.0);
		Ball ball = new Ball(7,7,.25, oldVelocity);
		try {
			double initialVelocity=oldVelocity.y();
			int gravity=25;
			double finalVelocityY=initialVelocity+gravity*sb.timeUntilCollision(ball);
			sb.reactWhenHit(ball, sb.timeUntilCollision(ball));
			assertTrue(ball.getVelocityX()>0);
			assertTrue(Math.abs(ball.getVelocityY())<finalVelocityY && ball.getVelocityY()>0);
		} catch(Exception e) {
			throw new UnsupportedOperationException();
		}
	}
	
	///////////////////////TESTING ABSORBER////////////////////
	/*
	 * Testing Strategy: Absorber
	 * 1. timeUntilCollision: Close, adjacent, infinity
	 * 2. ReactWhenHit: perpendicular, at an angle 
	 * 
	 * Correct behaviour is determined by physics calculations
	 */
	@Test
	public void testAbsorberToString() throws InvalidInvariantException {
		Absorber a = new Absorber(5,5,1,1);
		assertEquals(a.toString(), "=");
	}
	
	@Test
	public void testAbsorberTimeUntilCollisionClose() throws InvalidInvariantException{
		Absorber a = new Absorber(5,5,3,3);
		Ball ball = new Ball(6,2,0.25,new Vect(0,1));
		double ETA = a.timeUntilCollision(ball);
		assertTrue(ETA<3.0 && ETA>1.0);
	}
	
	@Test
	public void testAbsorberTimeUntilCollisionAdjacent()  throws InvalidInvariantException{
		Absorber a = new Absorber(5,5,3,3);
		Ball ball = new Ball(6,4,0.25,new Vect(0,1));
		double ETA = a.timeUntilCollision(ball);
		assertTrue(ETA==0.75);
	}
	
	@Test
	public void testAbsorberTimeUntilCollisionINFINITY()  throws InvalidInvariantException{
		Absorber a = new Absorber(5,5,3,3);
		Ball ball = new Ball(2,2,0.25,new Vect(0,1));
		double ETA = a.timeUntilCollision(ball);
		Double infinity = Double.POSITIVE_INFINITY;
		assertTrue(ETA==infinity);
	}
	
	@Test
	public void testAbsorberReactWhenHitPerpendicular()  throws InvalidInvariantException{
		Absorber a = new Absorber(5,5,3,3);
		Ball ball = new Ball(6,2,0.25,new Vect(0,1));
		try {
			a.reactWhenHit(ball, a.timeUntilCollision(ball));
			assertTrue(ball.getCenterX()==7.75);
			assertTrue(ball.getCenterY()==7.75);
			assertTrue(ball.getVelocityX()==0.0 && ball.getVelocityY()==0.0);
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
	}
	
	@Test
	public void testAbsorberReactWhenHitAtAnAngle()  throws InvalidInvariantException{
		Absorber a = new Absorber(5,5,3,3);
		Ball ball = new Ball(6,3,0.25,new Vect(1,1));
		try {
			a.reactWhenHit(ball, a.timeUntilCollision(ball));
			assertTrue(ball.getCenterX()==7.75);
			assertTrue(ball.getCenterY()==7.75);
			assertTrue(ball.getVelocityX()==0.0 && ball.getVelocityY()==0.0);
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
	}
	
	/////////////////////TRIANGLE BUMPER TESTS////////////////
	/*
	 * Testing Strategy: TriangleBumper
	 * 1. toString: upleft, upright, downleft, downright
	 * 2. timeUntilCollision: Close, adjacent, infinity
	 * 3. ReactWhenHit: perpendicular, at an angle
	 * 
	 * Correct behaviour is determined by physics calculations
	 */
	
	@Test
	public void testTriangleBumperToString() throws InvalidInvariantException {
		TriangleBumper tb = new TriangleBumper(5,5,TriangleBumper.TriangleBumperType.UPLEFT);
		TriangleBumper tb1 = new TriangleBumper(5,5,TriangleBumper.TriangleBumperType.UPRIGHT);
		TriangleBumper tb2 = new TriangleBumper(5,5,TriangleBumper.TriangleBumperType.DOWNLEFT);
		TriangleBumper tb3 = new TriangleBumper(5,5,TriangleBumper.TriangleBumperType.DOWNRIGHT);
		assertEquals(tb.toString(),"/");
		assertEquals(tb1.toString(),"\\");
		assertEquals(tb2.toString(),"\\");
		assertEquals(tb3.toString(),"/");

	}
	
	@Test
	public void testTriangleBumperTimeUntilCollisionClose() throws InvalidInvariantException {
		TriangleBumper tb = new TriangleBumper(5,5,TriangleBumper.TriangleBumperType.UPLEFT);
		Ball ball = new Ball(5,3,1.25,new Vect(0,1));
		double ETC = tb.timeUntilCollision(ball);
		assertTrue(ETC==0.75);
	}
	
	@Test
	public void testTriangleBumperTimeUntilCollisionAdjacent() throws InvalidInvariantException {
		TriangleBumper tb = new TriangleBumper(5,5,TriangleBumper.TriangleBumperType.UPLEFT);
		Ball ball = new Ball(5,5,0.25,new Vect(0,1));
		double ETC = tb.timeUntilCollision(ball);
		assertTrue(ETC<0.751);
	}
	
	@Test
	public void testTriangleBumperTimeUntilCollisionINFINITY() throws InvalidInvariantException {
		TriangleBumper tb = new TriangleBumper(5,5,TriangleBumper.TriangleBumperType.UPLEFT);
		Ball ball = new Ball(7,7,0.25,new Vect(0,1));
		double ETC = tb.timeUntilCollision(ball);
		double infinity = Double.POSITIVE_INFINITY;
		assertTrue(ETC==infinity);
	}
	
	@Test
	public void testTriangleBumperReactWhenHit() throws InvalidInvariantException {
		TriangleBumper tb = new TriangleBumper(5,5,TriangleBumper.TriangleBumperType.DOWNRIGHT);
		Ball ball = new Ball(6,3,0.25, new Vect(0,1));
		try {
			int gravity=25;
			double initialV=1;
			double finalV=initialV+gravity*tb.timeUntilCollision(ball);
			tb.reactWhenHit(ball, tb.timeUntilCollision(ball));
			assertTrue(ball.getVelocityY()<0);
			assertTrue(Math.abs(ball.getVelocityY())<finalV);
			
		} catch (Exception e) {
			assertFalse(false);
			System.out.print(e);
		}
	}
	
	////////////////////////FLIPPER TESTS/////////////////////
	/*
	 * Testing Strategy for Flipper:
	 * 1. toString: left, right - position up
	 * 2. togglePosition: position up, position down
	 * 3. timeUntilCollision: Close, adjacent, infinity
	 * 4. reactWhenHit: from top, bottom, sides
	 * 
	 * Correct behaviour is determined by physics calculations
	 */
	
	@Test
	public void testFlipperToStringLeftRightANDPositionUP() throws InvalidInvariantException {
		Flipper fLeft = new Flipper(5,5,Flipper.FlipperTypeExternal.LEFT, 
				FlipperRotation.NINETY,Arrays.asList());
		Flipper fRight = new Flipper(5,5,Flipper.FlipperTypeExternal.RIGHT,
				FlipperRotation.NINETY,Arrays.asList());
		assertEquals(fLeft.toString(), "-");
		assertEquals(fRight.toString(), "-");
	}
	
	@Test
	public void testFlipperToStringTogglePositionDOWN() throws InvalidInvariantException {
		Flipper fLeft = new Flipper(5,5,Flipper.FlipperTypeExternal.LEFT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());
		Flipper fRight = new Flipper(5,5,Flipper.FlipperTypeExternal.RIGHT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());
		fLeft.togglePosition();
		assertEquals(fLeft.toString(), "|");
		fRight.togglePosition();
		assertEquals(fRight.toString(), "|");
		
	}
	
	@Test
	public void testFlipperToStringTogglePositionUP() throws InvalidInvariantException {
		Flipper fLeft = new Flipper(5,5,Flipper.FlipperTypeExternal.LEFT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());
		Flipper fRight = new Flipper(5,5,Flipper.FlipperTypeExternal.RIGHT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());
		fLeft.togglePosition();
		fLeft.togglePosition();
		assertEquals(fLeft.toString(), "-");
		fRight.togglePosition();
		fRight.togglePosition();
		assertEquals(fRight.toString(), "-");
		
	}
	
	@Test
	public void testFlipperTimeUntilCollisionClose() throws InvalidInvariantException {
		Flipper f = new Flipper(5,5,Flipper.FlipperTypeExternal.LEFT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());
		Ball ball = new Ball(5,3,0.25,new Vect(0,1));
		assertTrue(Math.abs(f.timeUntilCollision(ball)-1.0)<0.751);
	}
	
	@Test
	public void testFlipperTimeUntilCollisionAdjacent() throws InvalidInvariantException {
		Flipper f = new Flipper(5,5,Flipper.FlipperTypeExternal.LEFT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());
		Ball ball = new Ball(5,4,0.25,new Vect(0,1));
		assertTrue(Math.abs(f.timeUntilCollision(ball)-0.0)<0.751);	
		}
	
	@Test
	public void testFlipperTimeUntilCollisionInfinity() throws InvalidInvariantException {
		Flipper f = new Flipper(5,5,Flipper.FlipperTypeExternal.LEFT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());
		Ball ball = new Ball(6,4,0.25,new Vect(1,1));
		Double infinity = Double.POSITIVE_INFINITY;
		assertTrue(f.timeUntilCollision(ball)== infinity);	
		}
	
//	@Test
	public void testFlipperReactWhenHitFromTop() throws InvalidInvariantException {
		Flipper f = new Flipper(5,5,Flipper.FlipperTypeExternal.LEFT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());	
		Vect oldVelocity = new Vect(0,1);
		Ball ball = new Ball(5,4,0.25, oldVelocity);
		try {
			f.reactWhenHit(ball,f.timeUntilCollision(ball));
			assertTrue(ball.getVelocityX()==0.0);
			assertTrue(Math.abs(ball.getVelocityY()-(-1.0*oldVelocity.y()))<0.1);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	@Test
	public void testFlipperReactWhenHitFromBottom() throws InvalidInvariantException {
		Flipper f = new Flipper(5,5,Flipper.FlipperTypeExternal.LEFT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());
		Vect oldVelocity = new Vect(0,-10);
		Ball ball = new Ball(5,7,0.25, oldVelocity);
		try {
			f.reactWhenHit(ball, f.timeUntilCollision(ball));
			assertTrue(ball.getVelocityX()==0);
			assertTrue(ball.getVelocityY()>0);
			assertTrue(Math.abs(ball.getVelocityY()-(-1.0*oldVelocity.y()))<5);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
//	@Test
	public void testFlipperReactWhenHitFromSide() throws InvalidInvariantException {
		Flipper f = new Flipper(5,5,Flipper.FlipperTypeExternal.LEFT,
				Flipper.FlipperRotation.ZERO,Arrays.asList());
		Vect oldVelocity = new Vect(-1,0);
		Ball ball = new Ball(6,5,0.25, oldVelocity);
		try {
			f.reactWhenHit(ball, f.timeUntilCollision(ball));
			assertTrue(ball.getVelocityY()<0.01);
			assertTrue(Math.abs(ball.getVelocityX()-(-1.0*oldVelocity.x()))<0.1);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	
	///////////////////////////BOUNDARY TESTS///////////////////////
	/*
	 * Testing Strategy for Boundary:
	 * 1.toString
	 * 2.timeUntilCollision:Infinity, adjacent, close
	 * 3.ReactWhenHit: 45 degrees, 90 degrees
	 * 
	 * Correct behaviour is determined by physics calculations
	 */
	
	@Test
	public void testBoundaryToString() throws InvalidInvariantException {
		Boundary b = new Boundary(5,5,new LineSegment(new Vect(5,5), new Vect(10,5)));
		assertTrue(b.toString()==".");
	}
	
	@Test
	public void testBoundaryTimeUntiCollisionInfinity() throws InvalidInvariantException {
		Boundary b = new Boundary(5,5,new LineSegment(new Vect(5,5), new Vect(10,5)));
		Ball ball = new Ball(3,3, 0.25, new Vect(0,1));
		Double infinity = Double.POSITIVE_INFINITY;
		assertTrue(b.timeUntilCollision(ball)==infinity);
	}
	
	@Test
	public void testBoundaryTimeUntilCollisionClose() throws InvalidInvariantException {
		Boundary b = new Boundary(5,5,new LineSegment(new Vect(5,5), new Vect(10,5)));
		Ball ball = new Ball(6,3, 0.25, new Vect(0,1));
		assertTrue(b.timeUntilCollision(ball)==1.75);
	}
	
	@Test
	public void testBoundaryTimeUntilCollisionAdjacent() throws InvalidInvariantException {
		Boundary b = new Boundary(5,5,new LineSegment(new Vect(5,5), new Vect(10,5)));
		Ball ball = new Ball(6,4, 0.25, new Vect(0,1));
		assertTrue(b.timeUntilCollision(ball)==0.75);
	}
	
	@Test
	public void testBoundaryReactWhenHit90degreesFromTop() throws InvalidInvariantException {
		Boundary b = new Boundary(5,5,new LineSegment(new Vect(5,5), new Vect(10,5)));
		Vect oldVelocity = new Vect(0,1);
		Ball ball = new Ball(6,3, 0.25, oldVelocity);
		try {
			int initialVelocity = 1;
			int gravity = 25;
			double finalVelocity = initialVelocity+gravity*b.timeUntilCollision(ball);
			b.reactWhenHit(ball, b.timeUntilCollision(ball));
			assertTrue(ball.getVelocityX()==0);
			assertTrue(ball.getVelocityY()<0 && ball.getVelocityY()<Math.abs(finalVelocity));
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	@Test
	public void testBoundaryReactWhenHit45degrees() throws InvalidInvariantException {
		Boundary b = new Boundary(5,5,new LineSegment(new Vect(5,5), new Vect(10,5)));
		Vect oldVelocity = new Vect(1,1);
		Ball ball = new Ball(6,3, 0.25, oldVelocity);
		try {
			int initialVelocityY = 1;
			int gravity = 25;
			double finalVelocity = initialVelocityY+gravity*b.timeUntilCollision(ball);
			
			b.reactWhenHit(ball, b.timeUntilCollision(ball));
			assertTrue(ball.getVelocityX()<oldVelocity.x());
			assertTrue(ball.getVelocityY()<0 && ball.getVelocityY()<Math.abs(finalVelocity));
		} catch (Exception e) {
			System.out.print(e);
		}
	}
}

