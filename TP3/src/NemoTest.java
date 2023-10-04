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
		assertEquals(-1, (nemo.commandsForNemo("d")).getHeight());
  }	
	@Test public void test5AscendsOneSpaceWithU() {
		Nemo nemo = new Nemo();
		assertEquals(1, (nemo.commandsForNemo("u")).getHeight());
  }	
	@Test public void test6CanChangeDirectionToLeft() {
		Nemo nemo = new Nemo();
		assertEquals(90, (nemo.commandsForNemo("l")).getDirection());
	}
	@Test public void test7CanChangeDirectionToRight() {
		Nemo nemo = new Nemo();
		assertEquals(270, (nemo.commandsForNemo("r")).getDirection());
	}
	@Test public void test8MovesForward() {
		Nemo nemo = new Nemo();
		assertEquals(1, (nemo.commandsForNemo("f")).getDistX());
	}
	// FALTA M QUE NO ENTIENDO BIEN
	
	//MOVIMIENTOS MAS COMPLEJOS
}
