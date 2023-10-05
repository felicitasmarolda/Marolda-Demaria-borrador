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
	@Test public void test1NewNemoCoordinatesAreInOrigin() {
		// INICIALIZACION
		Nemo nemo = new Nemo();
	    assertEquals( Arrays.asList(0,0), nemo.getCoordinates() );
	  }
	
	@Test public void test2NewNemoHasHeightZero() {
		Nemo nemo = new Nemo();
		assertEquals(0, nemo.getHeight());
  }	
	@Test public void test3NewNemoHasDirectionZero() {
		Nemo nemo = new Nemo();
		assertEquals(0, nemo.getDirection());
  }
	// MOVIMIENTOS BASICOS
	@Test public void test4DescendsOneSpaceWithD() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("d");
		assertEquals(-1, nemo.getHeight());
  }	
	@Test public void test5AscendsOneSpaceWithU() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("u");
		assertEquals(1, nemo.getHeight());
  }	
	@Test public void test6CanChangeDirectionToLeft() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("l");
		assertEquals(90, nemo.getDirection());
	}
	@Test public void test7CanChangeDirectionToRight() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("r");
		assertEquals(270, nemo.getDirection());
	}
	@Test public void test8MovesForward() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("f");
		assertEquals(1, nemo.getDistX());
	}
	// FALTA M QUE NO ENTIENDO BIEN
	
	//MOVIMIENTOS MAS COMPLEJOS
	@Test public void test9DirectionAndMoveForward() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("rf");
		assertEquals(0, nemo.getDistX());
		assertEquals(-1, nemo.getDistY());
	}
	@Test public void test10ThreeTypesOfCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("lfu");
		assertEquals(0, nemo.getDistX());
		assertEquals(1, nemo.getDistY());
		assertEquals(1, nemo.getHeight());
	}
	@Test public void test11RepeatCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("uu");
		assertEquals(2, nemo.getHeight());
	}	
	@Test public void test12CanReturnToOriginWithOpositeCommands() {
		Nemo nemo = new Nemo();
		nemo.commandsForNemo("uurfllfdfdrrf");
		assertEquals(0, nemo.getHeight());
		assertEquals(0, nemo.getDistX());
		assertEquals(0, nemo.getDistY());
	}
	@Test public void testSuccesfulM() {}
	@Test public void testUnsuccesfulM() {}

}
