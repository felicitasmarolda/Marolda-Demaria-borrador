package nemo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import pharma.Pharmacy;


public class NemoTest {
	@Test public void test1Coordinates() {
		Nemo nemo = new Nemo();
	    assertEquals( Arrays.asList(0,0), nemo.getCoordinates() );
	  }
}
