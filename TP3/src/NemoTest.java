package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

<<<<<<< HEAD
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


public class NemoTest {
	@Test public void test00NewNemoCoordinatesAreInOrigin() {
		Nemo nemo = new Nemo();
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals(0, nemo.direction);
	  }
	
	@Test public void test01StaysInTheSamePositionWithEmptyCommand() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("");
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals(0, nemo.direction);
	}

	@Test public void test02DescendsOneSpaceWithD() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('d');
		assertEquals(-1, nemo.zCoord);
  }	

	@Test public void test03CanNotGoAboveTheSurfaceWithUWhenInSurface() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('u');
		assertEquals(0, nemo.zCoord);
	}

	@Test public void test04CanChangeDirectionToLeft() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('l');
		assertEquals(90, nemo.direction);
	}
	@Test public void test05CanChangeDirectionToRight() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('r');
		assertEquals(270, nemo.direction);
	}
	@Test public void test06MovesForwardTowardsEast() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('f');
		assertEquals(1, nemo.xCoord);
	}
	@Test public void test07SuccesfulM() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('m');
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals(0, nemo.direction);
	}
	
	@Test public void test08AscendsOneSpaceWithU() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("ddu");
		assertEquals(-1, nemo.zCoord);
  }	
	@Test public void test09CanMoveSouth() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("rf");
		assertEquals(0, nemo.xCoord);
		assertEquals(-1, nemo.yCoord);
	}
	@Test public void test10CanMoveNorth() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("lf");
		assertEquals(0, nemo.xCoord);
		assertEquals(1, nemo.yCoord);
	}
	@Test public void test11CanMoveWest() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("rrf");
		assertEquals(-1, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
	}
	@Test public void test11ThreeTypesOfCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("lfd");
		assertEquals(0, nemo.xCoord);
		assertEquals(1, nemo.yCoord);
		assertEquals(-1, nemo.zCoord);
	}
	@Test public void test12RepeatCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("ff");
		assertEquals(2, nemo.xCoord);
	}	
	@Test public void test13CanReturnToOriginWithOpositeCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("rfllfdufdrruf");
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
	}
	
	@Test public void test14CanReachExpectedPoint() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("drmfddflfuff");
		assertEquals(-2, nemo.zCoord);
		assertEquals(3, nemo.xCoord);
		assertEquals(-2, nemo.yCoord);
	}
	@Test public void test15UnsuccesfulM() {
		Nemo nemo = new Nemo();
		assertEquals( "La capsula no puede ser liberada", 
				assertThrows( Exception.class, () -> nemo.commandsForNemo("ddm"))
				.getMessage());
		}
}


=======
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import nemo.Cardinals.East;
import nemo.Cardinals.North;
import nemo.Cardinals.South;
import nemo.Cardinals.West;


public class NemoTest {
	
	private Nemo nemo;
	String Oeste;
	String Este;
	String Norte;
	String Sur;
	
	@BeforeEach void setUp() {
		nemo = new Nemo(0, 0, new North());
		Oeste = "Oeste";
		Este = "Este";
		Norte = "Norte";
		Sur = "Sur";
	}
	
	@Test public void test00CanPlaceNemoAnywherePointingToWhereDesired() {
		Nemo nemo = new Nemo(1, 2, new West());
		assertNemoPositionAndOrientation(1, 2, 0, Oeste, nemo);
	}
	
	@Test public void test01DescendsOneUnit() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('d', 0, 0, -1, Norte, nemo);
    }	

	@Test public void test02AscendsOneUnit() {
		nemo.proceedWithNemoCommandExecution('d');
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('u', 0, 0, 0, Norte, nemo);
	}

	@Test public void test03MovesForward() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('f', 0, 1, 0, Norte, nemo);
	}
	
	@Test public void test04CanTurnLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('l', 0, 0, 0, Oeste, nemo);
	}
	
	@Test public void test05CanTurnRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('r', 0, 0, 0, Este, nemo);
	}
	
	@Test public void test06CanShootCapsule() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('m', 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test07CannotAscendWhenInSurface() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('u', 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test08AllowsStringsRepresentingCommandChains() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("fldurm", 0, 1, 0, Norte, nemo);
	}
	
	@Test public void test09SlideChangesNemoPosition() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("ffffudufduuddudff", 0, 7, -2, Norte, nemo);
	}
	
	@Test public void test10RotationChangesNemoOrientation() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("lllrrlrllrrlrrlrr", 0, 0, 0, Este, nemo);
	}
	
	@Test public void test11RotationDoesNotChangeNemoPosition() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rlrllrlrlrrlrlrlr", 0, 0, 0, Este, nemo);
	}
	
	@Test public void test12SlideDoesNotChangeNemoOrientation() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("fduudufufuduufudu", 0, 4, 0, Norte, nemo);
	}

	@Test public void test13CanTurn90DegreesByTurningLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("l", 0, 0, 0, Oeste, nemo);
	}
	
	@Test public void test14CanTurn180DegreesByTurningLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("ll", 0, 0, 0, Sur, nemo);
	}
	
	@Test public void test15CanTurn270DegreesByTurningLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("lll", 0, 0, 0, Este, nemo);
	}
	
	@Test public void test16CanTurn360DegreesByTurningLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("llll", 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test17CanTurn90DegreesByTurningRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("r", 0, 0, 0, Este, nemo);
	}
	
	@Test public void test18CanTurn180DegreesByTurningRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rr", 0, 0, 0, Sur, nemo);
	}
	
	@Test public void test19CanTurn270DegreesByTurningRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rrr", 0, 0, 0, Oeste, nemo);
	}
	
	@Test public void test20CanTurn360DegreesByTurningRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rrrr", 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test21CanReturnToOriginalState() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rfllfdufdrrufl", 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test22IgnoresEmptyCommand() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("", 0, 0, 0, Norte, nemo);

	}
	
	@Test public void test23CanReachAnyDesiredPositionAndDirection() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("dddddrffffffffffffrfffffffffffffl", 12, -13, -5, Este, nemo);
	}
	
	@Test public void test24CannotOpenCapsuleBelowSomeDepth() {
		assertEquals( "Intento de expulsión de cápsula en condiciones no óptimas. Nemo ha implosionado.", 
				assertThrows( Error.class, () -> nemo.proceedWithNemoCommandExecution("ddm"))
				.getMessage());
	}
	
	private void assertNemoPositionAndOrientation(int x, int y, int depth, String cardinal, Nemo nemo) {
		assertEquals(x, nemo.getXCoord());
		assertEquals(y, nemo.getYCoord());
		assertEquals(depth, nemo.getDepthValue());
		assertEquals(cardinal, nemo.getOrientationValue());
	}
	
	private void assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
	(Character command, int x, int y, int depth, String cardinal, Nemo nemo) {
		nemo.proceedWithNemoCommandExecution(command);		
		assertNemoPositionAndOrientation(x, y, depth, cardinal, nemo);
	}

	private void assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
	(String command, int x, int y, int depth, String cardinal, Nemo nemo) {
		nemo.proceedWithNemoCommandExecution(command);		
		assertNemoPositionAndOrientation(x, y, depth, cardinal, nemo);
	}
}
>>>>>>> santi-branch
