package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


public class NemoTest {
	
	@Test public void test00CanPlaceNewNemoAnywherePointingToWhereDesired() {
		Nemo nemo = new Nemo(1, 2, new South());
		assertEquals(1, nemo.xCoord);
		assertEquals(2, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals("Sur", nemo.direction());
	}
	
	@Test public void test01IgnoresEmptyCommand() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand("");
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals("Norte", nemo.direction());
	}

	@Test public void test02DescendsOneUnit() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('d');
		assertEquals(-1, nemo.zCoord);
    }	

	@Test public void test03AscendsOneUnit() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('d');
		nemo.executeNemoCommand('u');
		assertEquals(0, nemo.zCoord);
	}
	
	@Test public void test04CannotAscendWhenInSurface() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('u');
		assertEquals(0, nemo.zCoord);
	}

	@Test public void test05OpensCapsule() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('m');
		assertTrue(nemo.capsule());
	}
	
	@Test public void test06TurnsLeftNinetyDegrees() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('l');
		assertEquals("Oeste", nemo.direction());
	}
	
	@Test public void test07TurnsRightNinetyDegrees() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('r');
		assertEquals("Este", nemo.direction());
	}
	
	@Test public void test08MovesForward() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('f');
		assertEquals(1, nemo.yCoord);
	}
	
	@Test public void test09CanPointToAnyDirectionByTurningLeft() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('l');
		assertEquals("Oeste", nemo.direction());
		nemo.executeNemoCommand('l');
		assertEquals("Sur", nemo.direction());
		nemo.executeNemoCommand('l');
		assertEquals("Este", nemo.direction());
		nemo.executeNemoCommand('l');
		assertEquals("Norte", nemo.direction());
	}
	
	@Test public void test10CanPointToAnyDirectionByTurningRight() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('r');
		assertEquals("Este", nemo.direction());
		nemo.executeNemoCommand('r');
		assertEquals("Sur", nemo.direction());
		nemo.executeNemoCommand('r');
		assertEquals("Oeste", nemo.direction());
		nemo.executeNemoCommand('r');
		assertEquals("Norte", nemo.direction());
	}
	
	@Test public void test11AllowsStringsRepresentingCommandChains() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand("fm");
		assertEquals(1, nemo.yCoord);
		assertTrue(nemo.capsule());
	}
	
	@Test public void test12CanRepeatCommands() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand("ffff");
		assertEquals(4, nemo.yCoord);
	}
	
	@Test public void test13CanAdvanceOnAnyDirection() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand('f');
		assertEquals(1, nemo.yCoord);
		nemo.executeNemoCommand("rf");
		assertEquals(1, nemo.xCoord);
		nemo.executeNemoCommand("rf");
		assertEquals(0, nemo.yCoord);
		nemo.executeNemoCommand("rf");
		assertEquals(0, nemo.xCoord);
	}
	
	@Test public void test14CanReturnToOriginalStateWithOpositeMoves() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand("rfllfdufdrrufl");
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals("Norte", nemo.direction());
	}
	
	@Test public void test15CanReachAnyDesiredPositionAndDirection() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.executeNemoCommand("dddddrffffffffffffrfffffffffffffl");
		assertEquals(12, nemo.xCoord);
		assertEquals(-13, nemo.yCoord);
		assertEquals(-5, nemo.zCoord);
		assertEquals("Este", nemo.direction());
	}
	
	@Test public void test16CannotOpenCapsuleBelowSomeDepth() {
		Nemo nemo = new Nemo(0, 0, new North());
		assertEquals( "La capsula no puede ser liberada", 
				assertThrows( Exception.class, () -> nemo.executeNemoCommand("ddm"))
				.getMessage());
	}
	
	@Test public void test17CannotOpenAlreadyOpenedCapsule() {
		Nemo nemo = new Nemo(0, 0, new North());
		assertEquals( "La capsula no puede ser liberada", 
				assertThrows( Exception.class, () -> nemo.executeNemoCommand("mm"))
				.getMessage());
	}
}


