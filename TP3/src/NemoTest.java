package nemo;

import static org.junit.Assert.assertThrows;
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
		// INICIALIZACION
		Nemo nemo = new Nemo();
		assertEquals(0, nemo.getXCoord());
		assertEquals(0, nemo.getYCoord());
		assertEquals(0, nemo.getZCoord());
		assertEquals(0, nemo.getDirection());
	  }
	
	@Test public void test01StaysTheSameWithEmptyCommand() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("");
		assertEquals(0, nemo.getXCoord());
		assertEquals(0, nemo.getYCoord());
		assertEquals(0, nemo.getZCoord());
		assertEquals(0, nemo.getDirection());
	}

	// MOVIMIENTOS BASICOS
	@Test public void test02DescendsOneSpaceWithD() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("d");
		assertEquals(-1, nemo.getZCoord());
  }	

	@Test public void test03CanNotGoAboveTheSurfaceWithUWhenInSurface() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("u");
		assertEquals(0, nemo.getZCoord());
	}

	@Test public void test04CanChangeDirectionToLeft() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("l");
		assertEquals(90, nemo.getDirection());
	}
	@Test public void test05CanChangeDirectionToRight() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("r");
		assertEquals(270, nemo.getDirection());
	}
	@Test public void test06MovesForward() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("f");
		assertEquals(1, nemo.getXCoord());
	}
	@Test public void testSuccesfulM() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("m");
		assertFalse(nemo.getCapsuleState());
	}
	
	@Test public void testUnsuccesfulM() {}

	// FALTA M QUE NO ENTIENDO BIEN
	
	//MOVIMIENTOS MAS COMPLEJOS
	@Test public void test07AscendsOneSpaceWithU() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("ddu");
		assertEquals(-1, nemo.getZCoord());
  }	
	@Test public void test08DirectionAndMoveForward() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("rf");
		assertEquals(0, nemo.getXCoord());
		assertEquals(-1, nemo.getYCoord());
	}
	@Test public void test09ThreeTypesOfCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("lfd");
		assertEquals(0, nemo.getXCoord());
		assertEquals(1, nemo.getYCoord());
		assertEquals(-1, nemo.getZCoord());
	}
	@Test public void test10RepeatCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("ff");
		assertEquals(2, nemo.getXCoord());
	}	
	@Test public void test11CanReturnToOriginWithOpositeCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("rfllfdufdrruf");
		assertEquals(0, nemo.getZCoord());
		assertEquals(0, nemo.getXCoord());
		assertEquals(0, nemo.getYCoord());
	}
	
	@Test public void test12CanReachExpectedPoint() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("drfddflfuff");
		assertEquals(-2, nemo.getZCoord());
		assertEquals(3, nemo.getXCoord());
		assertEquals(-2, nemo.getYCoord());
	}
}


