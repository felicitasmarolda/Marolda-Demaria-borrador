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
		nemo.execute("");
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals("Norte", nemo.direction());
	}

	@Test public void test02DescendsOneUnit() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('d');
		assertEquals(-1, nemo.zCoord);
    }	

	@Test public void test03AscendsOneUnit() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('d');
		nemo.execute('u');
		assertEquals(0, nemo.zCoord);
	}
	
	@Test public void test04CannotAscendWhenInSurface() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('u');
		assertEquals(0, nemo.zCoord);
	}

	@Test public void test05OpensCapsule() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('m');
		assertTrue(nemo.capsule());
	}
	
	@Test public void test06TurnsLeftNinetyDegrees() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('l');
		assertEquals("Oeste", nemo.direction());
	}
	
	@Test public void test07TurnsRightNinetyDegrees() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('r');
		assertEquals("Este", nemo.direction());
	}
	
	@Test public void test08MovesForward() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('f');
		assertEquals(1, nemo.yCoord);
	}
	
	@Test public void test09CanPointToAnyDirectionByTurningLeft() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('l');
		assertEquals("Oeste", nemo.direction());
		nemo.execute('l');
		assertEquals("Sur", nemo.direction());
		nemo.execute('l');
		assertEquals("Este", nemo.direction());
		nemo.execute('l');
		assertEquals("Norte", nemo.direction());
	}
	
	@Test public void test10CanPointToAnyDirectionByTurningRight() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('r');
		assertEquals("Este", nemo.direction());
		nemo.execute('r');
		assertEquals("Sur", nemo.direction());
		nemo.execute('r');
		assertEquals("Oeste", nemo.direction());
		nemo.execute('r');
		assertEquals("Norte", nemo.direction());
	}
	
	@Test public void test11AllowsStringsRepresentingCommandChains() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute("fm");
		assertEquals(1, nemo.yCoord);
		assertTrue(nemo.capsule());
	}
	
	@Test public void test12CanRepeatCommands() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute("ffff");
		assertEquals(4, nemo.yCoord);
	}
	
	@Test public void test13CanAdvanceOnAnyDirection() {
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute('f');
		assertEquals(1, nemo.yCoord);
		nemo.execute("rf");
		assertEquals(1, nemo.xCoord);
		nemo.execute("rf");
		assertEquals(0, nemo.yCoord);
		nemo.execute("rf");
		assertEquals(0, nemo.xCoord);
	}
	
	@Test public void test14CanReturnToOriginalStateWithOpositeMoves() {
		Nemo nemo = new Nemo(0, 0, new North());
		Nemo nemo2 = new Nemo(10, 15, new West());
		nemo.execute("rfllfdufdrrufl");
		nemo2.execute("rfllfdufdrrufl");
		
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals("Norte", nemo.direction());
		
		assertEquals(10, nemo2.xCoord);
		assertEquals(15, nemo2.yCoord);
		assertEquals(0, nemo2.zCoord);
		assertEquals("Oeste", nemo2.direction());
	}
	
	@Test public void test15CanMeetAnyOtherNemo() {
		Nemo nemo2 = new Nemo(12, -13, new East());
		nemo2.execute("ddddd");
		Nemo nemo = new Nemo(0, 0, new North());
		nemo.execute("dddddrffffffffffffrfffffffffffffl");
		assertEquals(nemo2.xCoord, nemo.xCoord);
		assertEquals(nemo2.yCoord, nemo.yCoord);
		assertEquals(nemo2.zCoord, nemo.zCoord);
		assertEquals(nemo2.direction(), nemo.direction());
	}
	
	@Test public void test16CannotOpenCapsuleBelowSomeDepth() {
		Nemo nemo = new Nemo(0, 0, new North());
		assertEquals( "La capsula no puede ser liberada", 
				assertThrows( Exception.class, () -> nemo.execute("ddm"))
				.getMessage());
	}
	
	@Test public void test17CannotOpenAlreadyOpenedCapsule() {
		Nemo nemo = new Nemo(0, 0, new North());
		assertEquals( "La capsula no puede ser liberada", 
				assertThrows( Exception.class, () -> nemo.execute("mm"))
				.getMessage());
	}
}


