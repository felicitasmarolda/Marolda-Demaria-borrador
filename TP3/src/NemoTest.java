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
	@Test public void test0NewNemoCoordinatesAreInOrigin() {
		// INICIALIZACION
		Nemo nemo = new Nemo();
		assertEquals(0, nemo.getXCoord());
		assertEquals(0, nemo.getYCoord());
		assertEquals(0, nemo.getHeight());
		assertEquals(0, nemo.getDirection());
	  }
	@Test public void test1StaysTheSameWithEmptyCommand() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("");
		assertEquals(0, nemo.getXCoord());
		assertEquals(0, nemo.getYCoord());
		assertEquals(0, nemo.getHeight());
		assertEquals(0, nemo.getDirection());
	}

	// MOVIMIENTOS BASICOS
	@Test public void test2DescendsOneSpaceWithD() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("d");
		assertEquals(-1, nemo.getHeight());
  }	
	@Test public void test3AscendsOneSpaceWithU() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("u");
		assertEquals(1, nemo.getHeight());
  }	
	@Test public void test4CanChangeDirectionToLeft() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("l");
		assertEquals(90, nemo.getDirection());
	}
	@Test public void test5CanChangeDirectionToRight() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("r");
		assertEquals(270, nemo.getDirection());
	}
	@Test public void test6MovesForward() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("f");
		assertEquals(1, nemo.getXCoord());
	}
	// FALTA M QUE NO ENTIENDO BIEN
	
	//MOVIMIENTOS MAS COMPLEJOS
	@Test public void test7DirectionAndMoveForward() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("rf");
		assertEquals(0, nemo.getXCoord());
		assertEquals(-1, nemo.getYCoord());
	}
	@Test public void test8ThreeTypesOfCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("lfu");
		assertEquals(0, nemo.getXCoord());
		assertEquals(1, nemo.getYCoord());
		assertEquals(1, nemo.getHeight());
	}
	@Test public void test9RepeatCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("uu");
		assertEquals(2, nemo.getHeight());
	}	
	@Test public void test10CanReturnToOriginWithOpositeCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("uurfllfdfdrrf");
		assertEquals(0, nemo.getHeight());
		assertEquals(0, nemo.getXCoord());
		assertEquals(0, nemo.getYCoord());
	}
	
	@Test public void test11CanReachExpectedPoint() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("drfddflfuff");
		assertEquals(-2, nemo.getHeight());
		assertEquals(3, nemo.getXCoord());
		assertEquals(-2, nemo.getYCoord());
	}
	@Test public void testSuccesfulM() {}
	@Test public void testUnsuccesfulM() {}

}
