package linea;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LineTest {
	
	@Test public void test00NewLinea() {
		Line game = new Line(3, 5, 'C');
		assertEquals(3 , game.tablero().size()) ;
	}
	
	@Test public void test01RedPlaysCorrectlyAndShown(){
        Line line = new Line( 3, 3, 'C' );
        line.playBlueAt( 1 );
        assertEquals( 'B', line.tablero().get( 0 ).get( 0 ).charValue());
    }
	
	@Test public void test02RedPlaysCorrectlyAndShown(){
        Line line = new Line( 3, 3, 'C' );
        line.playBlueAt( 1 );
        assertEquals( 'B', line.tablero().get( 0 ).get( 0 ).charValue());
    }
	
	@Test public void test03GameEndsWhenBoardFull(){
        Line line = new Line( 3, 2, 'C');
        line.playBlueAt(1);
        line.playRedAt(1);
        line.playBlueAt(2);
        line.playRedAt(2);
        line.playBlueAt(3);
        line.playRedAt(3);
        assertTrue( line.finished() );
    }
	
	@Test public void test04CantPlayInNonExistentColumn() {
        Line line = new Line( 3, 2, 'C');
        assertThrows(Exception.class, () -> line.playBlueAt(4));
	}
	
	@Test public void test05CantPlayInFullColumn(){
	    Line line = new Line( 3, 6, 'C');
	    line.playBlueAt(1);
	    line.playRedAt(1);
	    line.playBlueAt(1);
	    line.playRedAt(1);
	    line.playBlueAt(1);
	    line.playRedAt(1);
        assertThrows(Exception.class, () -> line.playBlueAt(1));
	}
	
	@Test public void test06CantPlayTwoTimesInARow(){
	    Line line = new Line( 4, 6, 'C');
	    line.playBlueAt(1);
	    line.playRedAt(1);
	    line.playBlueAt(1);
	    line.playRedAt(1);
	    line.playBlueAt(1);
	    line.playRedAt(1);
        assertThrows(Exception.class, () -> line.playRedAt(1));
	}
	
	@Test public void test07BlueWinsByHorizontalLineAsTypeA(){
        Line line = new Line( 4, 4, 'A');
        line.playBlueAt(1);
        line.playRedAt(1);
        line.playBlueAt(2);
        line.playRedAt(2);
        line.playBlueAt(3);
        line.playRedAt(3);
        line.playBlueAt(4);
        assertTrue( line.finished() );
    }
	
	@Test public void test08BlueWinsByVerticalLineAsTypeA(){
        Line line = new Line( 4, 4, 'A');
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(3);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        assertTrue( line.finished() );
    }
	
	@Test public void test09BlueWinsByCrecentLineAsTypeB(){
        Line line = new Line( 4, 4, 'B');
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(2);
        line.playRedAt(4);
        line.playBlueAt(3);
        line.playRedAt(3);
        line.playBlueAt(3);
        line.playRedAt(1);
        line.playBlueAt(4);
        line.playRedAt(4);
        line.playBlueAt(4);
        assertTrue( line.finished() );
    }
	
	@Test public void test10BlueWinsByDecrecentLineAsTypeB(){
        Line line = new Line( 4, 4, 'B');
        line.playBlueAt(1);
        line.playRedAt(1);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(2);
        line.playRedAt(3);
        line.playBlueAt(3);
        line.playRedAt(3);
        line.playBlueAt(4);
        assertTrue( line.finished() );
    }
	
	@Test public void test11CantPlayWhenGameEnded(){
        Line line = new Line( 5, 5, 'C');
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        assertTrue( line.finished() );
        assertThrows(Exception.class, () -> line.playRedAt(3));
    }
	
	@Test public void testForgiven01ActualPieceVerticalTriumph() {
        Line line = new Line( 5, 5, 'C');
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        assertTrue( line.actualPieceVerticalTriumph('B', 1) );
    }
	
	@Test public void testForgiven02ActualPieceHorizontalTriumph() {
		Line line = new Line( 4, 4, 'C');
		line.playBlueAt(1);
	    line.playRedAt(1);
	    line.playBlueAt(2);
	    line.playRedAt(2);
	    line.playBlueAt(3);
	    line.playRedAt(3);
	    line.playBlueAt(4);
	    assertTrue( line.actualPieceHorizontalTriumph('B', 4) );
	}
	
	@Test public void testForgiven03ActualPieceIncreasingDiagonalTriumph() {
		Line line = new Line( 4, 4, 'C');
		line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(2);
        line.playRedAt(4);
        line.playBlueAt(3);
        line.playRedAt(3);
        line.playBlueAt(3);
        line.playRedAt(1);
        line.playBlueAt(4);
        line.playRedAt(4);
        line.playBlueAt(4);
	    assertTrue( line.actualPieceIncreasingDiagonalTriumph('B', 4) );
	}
	
	@Test public void testForgiven04ActualPieceDecreasingDiagonalTriumph() {
		Line line = new Line( 4, 4, 'C');
		line.playBlueAt(1);
        line.playRedAt(1);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(2);
        line.playRedAt(3);
        line.playBlueAt(3);
        line.playRedAt(3);
        line.playBlueAt(4);
	    assertTrue( line.actualPieceDecreasingDiagonalTriumph('B', 4) );
	}
	
	@Test public void print1() {
		Line line = new Line( 4, 4, 'C');
        line.playBlueAt(1);
        line.playRedAt(1);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(2);
        line.playRedAt(3);
        line.playBlueAt(3);
        line.playRedAt(3);
        line.playBlueAt(4);
		System.out.println(line.show());
	}
	
	@Test public void print2() {		
		Line line = new Line( 4, 4, 'C');
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(2);
        line.playRedAt(4);
        line.playBlueAt(3);
        line.playRedAt(3);
        line.playBlueAt(3);
        line.playRedAt(1);
        line.playBlueAt(4);
        line.playRedAt(4);
        line.playBlueAt(4);
		System.out.println(line.show());
	}
	
	@Test public void print3() {		
		Line line = new Line( 4, 4, 'C');
		line.playBlueAt(1);
        line.playRedAt(1);
        line.playBlueAt(2);
        line.playRedAt(2);
        line.playBlueAt(3);
        line.playRedAt(3);
        line.playBlueAt(4);
		System.out.println(line.show());
	}
	
	@Test public void print4() {		
		Line line = new Line( 4, 4, 'C');
		line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(3);
        line.playRedAt(2);
		System.out.println(line.show());
	}
	
	// tester que hay en un lugar especifico en cualquier momento
	
}