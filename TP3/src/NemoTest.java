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
	
}