package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NemoTest {
	
	@Test public void test00CanPlaceNewNemoAnywherePointingToWhereDesired() {
		assertCoordinates(new Nemo(1, 2, new South()), 1, 2, 0);
		assertCardinalDirection(new Nemo(1, 2, new South()), "Sur");
	}
	
	@Test public void test01IgnoresEmptyCommand() {
		nemoIsInOrigin(nemoWithCommandInFormOfString(""));
		assertCardinalDirection(nemoWithCommandInFormOfString(""), "Norte");
	}

	@Test public void test02DescendsOneUnit() {
		assertSpecificCoordinate(-1, nemoWithCommandInFormOfCharacter('d').zCoord);
    }	

	@Test public void test03AscendsOneUnit() {
		assertSpecificCoordinate(0, nemoWithCommandInFormOfCharacter('u').zCoord);
	}
	
	@Test public void test04CannotAscendWhenInSurface() {
		assertSpecificCoordinate(0, nemoWithCommandInFormOfCharacter('u').zCoord);
	}

	@Test public void test05OpensCapsule() {
		assertTrue(nemoWithCommandInFormOfCharacter('m').capsule());
	}
	
	@Test public void test06TurnsLeftNinetyDegrees() {
		assertCardinalDirection(nemoWithCommandInFormOfCharacter('l'), "Oeste");
	}
	
	@Test public void test07TurnsRightNinetyDegrees() {
		assertCardinalDirection(nemoWithCommandInFormOfCharacter('r'), "Este");
	}
	
	@Test public void test08MovesForward() {
		assertSpecificCoordinate(1, nemoWithCommandInFormOfCharacter('f').yCoord);
	}
	
	@Test public void test09CanPointToAnyDirectionByTurningLeft() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemoCanPointToAnyDirection(nemo, "Oeste", "Sur", "Este", "Norte", "l", "l", nemo.direction());
		}
	
	@Test public void test10CanPointToAnyDirectionByTurningRight() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemoCanPointToAnyDirection(new Nemo(0, 0, new North()), "Este", "Sur", "Oeste", "Norte", "r", "r", nemo.direction());
	}
	
	@Test public void test11AllowsStringsRepresentingCommandChains() {
		assertSpecificCoordinate(1, nemoWithCommandInFormOfString("fm").yCoord);
		assertTrue(nemoWithCommandInFormOfString("fm").capsule());
	}
	
	@Test public void test12CanRepeatCommands() {
		assertSpecificCoordinate(4, nemoWithCommandInFormOfString("ffff").yCoord);
	}
	
	@Test public void test13CanAdvanceOnAnyDirection() {
		Nemo nemo = new Nemo(0, 0, new North());
		Object val1 = 1;
		Object val2 = 1;
		Object val3 = 0;
		Object val4 = 0;
		String command1 = "f";
		String commandOthers = "rf";
		Object coord1 = nemo.yCoord;
		Object coord2 = nemo.xCoord;
		
		nemoCanPointToAnyDirection(nemo, "Este", "Sur", "Oeste", "Norte", "r", "r", nemo.direction());
		
		nemo.executeNemoCommand(command1);
		assertEquals(val1, nemo.yCoord);
		nemo.executeNemoCommand(commandOthers);
		assertEquals(val2, nemo.xCoord);
		nemo.executeNemoCommand(commandOthers);
		assertEquals(val3, nemo.yCoord);
		nemo.executeNemoCommand(commandOthers);
		assertEquals(val4, nemo.xCoord);
	}
	
	@Test public void test14CanReturnToOriginalStateWithOpositeMoves() {
		assertEquals("Norte", nemoWithCommandInFormOfString("rfllfdufdrrufl").direction());
	}
	
	@Test public void test15CanReachAnyDesiredPositionAndDirection() {
		assertCoordinates(nemoWithCommandInFormOfString("dddddrffffffffffffrfffffffffffffl"), 12, -13, -5);
		assertEquals("Este", nemoWithCommandInFormOfString("dddddrffffffffffffrfffffffffffffl").direction());
	}
	
	@Test public void test16CannotOpenCapsuleBelowSomeDepth() {
		assertEquals( "La capsula no puede ser liberada", 
				assertThrows( Exception.class, () -> new Nemo(0, 0, new North()).executeNemoCommand("ddm"))
				.getMessage());
	}
	
	@Test public void test17CannotOpenAlreadyOpenedCapsule() {
		assertEquals( "La capsula no puede ser liberada", 
				assertThrows( Exception.class, () -> new Nemo(0, 0, new North()).executeNemoCommand("mm"))
				.getMessage());
	}
	

	private void assertCoordinates(Nemo nemo, int x, int y, int z) {
		assertEquals(x, nemo.xCoord);
		assertEquals(y, nemo.yCoord);
		assertEquals(z, nemo.zCoord);
	}

	private void nemoIsInOrigin(Nemo nemo) {
		assertCoordinates(nemo, 0, 0, 0);
	}

	private void nemoCanPointToAnyDirection(Nemo nemo, Object cardinal1, Object cardinal2, Object cardinal3, Object cardinal4,
			String command1, String command2, Object atributo) {
		nemo.executeNemoCommand(command1);
		assertEquals(cardinal1, atributo);
		nemo.executeNemoCommand(command2);
		assertEquals(cardinal2, atributo);
		nemo.executeNemoCommand(command2);
		assertEquals(cardinal3, atributo);
		nemo.executeNemoCommand(command2);
		assertEquals(cardinal4, atributo);
	}

	private Nemo nemoWithCommandInFormOfCharacter(Character commandToExecute) {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand(commandToExecute);
		return nemo;
	}

	private Nemo nemoWithCommandInFormOfString(String commandToExecute) {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand(commandToExecute);
		return nemo;
	}

	private void assertSpecificCoordinate(int value, int coord) {
		assertEquals(value, coord);
	}

	private void assertCardinalDirection(Nemo nemo, String cardinal) {
		assertEquals(cardinal, nemo.direction());
	}
}