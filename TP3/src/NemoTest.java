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
	
	@Test public void test00NewNemoCoordinatesAreInOrigin() {
		Nemo nemo = new Nemo();
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals("Norte", nemo.direction());
	}
	
	@Test public void test01StaysTheSameWithEmptyCommand() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("");
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals("Norte", nemo.direction());
	}

	@Test public void test02DescendsOneSpaceWithD() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('d');
		assertEquals(-1, nemo.zCoord);
    }	

	@Test public void test03AscendsOneSpaceWithU() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("ddd");
		nemo.commandsForNemo('u');
		assertEquals(-2, nemo.zCoord);
	}
	
	@Test public void test04CanNotGoAboveTheSurfaceWithUWhenInSurface() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('u');
		assertEquals(0, nemo.zCoord);
	}

	@Test public void test05CanChangeDirectionToLeft() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('l');
		assertEquals("Oeste", nemo.direction());
		nemo.commandsForNemo('l');
		assertEquals("Sur", nemo.direction());
		nemo.commandsForNemo('l');
		assertEquals("Este", nemo.direction());
		nemo.commandsForNemo('l');
		assertEquals("Norte", nemo.direction());
	}
	
	@Test public void test06CanChangeDirectionToRight() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('r');
		assertEquals("Este", nemo.direction());
		nemo.commandsForNemo('r');
		assertEquals("Sur", nemo.direction());
		nemo.commandsForNemo('r');
		assertEquals("Oeste", nemo.direction());
		nemo.commandsForNemo('r');
		assertEquals("Norte", nemo.direction());
	}
	
	@Test public void test07CanMoveForward() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('f');
		assertEquals(1, nemo.yCoord);
		nemo.commandsForNemo("rf");
		assertEquals(1, nemo.xCoord);
		nemo.commandsForNemo("rf");
		assertEquals(0, nemo.yCoord);
		nemo.commandsForNemo("rf");
		assertEquals(0, nemo.xCoord);
	}
	
	@Test public void test08SuccesfulM() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo('m');
		assertEquals(0, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(0, nemo.zCoord);
		assertEquals("Norte", nemo.direction());
		assertEquals(true, nemo.capsule);
	}
	
	@Test public void test09AscendsOneSpaceWithU() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("ddu");
		assertEquals(-1, nemo.zCoord);
    }
	
	@Test public void test10DirectionAndMoveForward() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("rf");
		assertEquals(1, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
	}
	
	@Test public void test11ThreeTypesOfCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("lfd");
		assertEquals(-1, nemo.xCoord);
		assertEquals(0, nemo.yCoord);
		assertEquals(-1, nemo.zCoord);
	}
	
	@Test public void test12RepeatCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("ff");
		assertEquals(2, nemo.yCoord);
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
		nemo.commandsForNemo("dmfddflfuff");
		assertEquals(-2, nemo.zCoord);
		assertEquals(-3, nemo.xCoord);
		assertEquals(2, nemo.yCoord);
	}
	
	@Test public void test15CannotOpenCapsuleBelowSomeDepth() {
		Nemo nemo = new Nemo();
		assertEquals( "La capsula no puede ser liberada", 
				assertThrows( Exception.class, () -> nemo.commandsForNemo("ddm"))
				.getMessage());
	}
	
	@Test public void test16CannotOpenAlreadyOpenedCapsule() {
		Nemo nemo = new Nemo();
		assertEquals( "La capsula no puede ser liberada", 
				assertThrows( Exception.class, () -> nemo.commandsForNemo("mm"))
				.getMessage());
	}
}


