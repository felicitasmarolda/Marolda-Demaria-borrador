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

public class LineaTest {
	
	@Test public void test00NewLinea() {
		Linea game = new Linea(3, 5, 'C');
		assertEquals(3 , game.tablero().size()) ;
	}
	
	@Test public void test01RedPlaysCorrectlyAndShown(){
        Linea linea = new Linea( 3, 3, 'C' );
        linea.playBlueAt( 1 );
        assertEquals( 'B', linea.tablero().get( 0 ).get( 0 ).charValue());
    }
	
	@Test public void test02RedPlaysCorrectlyAndShown(){
        Linea linea = new Linea( 3, 3, 'C' );
        linea.playBlueAt( 1 );
        assertEquals( 'B', linea.tablero().get( 0 ).get( 0 ).charValue());
    }
	
	@Test public void test03GameEndsWhenBoardFull(){
        Linea linea = new Linea( 3, 2, 'C');
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        assertTrue( linea.finished() );
    }
	
	@Test public void test04CantPlayInNonExistentColumn() {
        Linea linea = new Linea( 3, 2, 'C');
        assertThrows(Exception.class, () -> linea.playBlueAt(4));
	}
	
	@Test public void test05CantPlayInFullColumn(){
	    Linea linea = new Linea( 3, 6, 'C');
	    linea.playBlueAt(1);
	    linea.playRedAt(1);
	    linea.playBlueAt(1);
	    linea.playRedAt(1);
	    linea.playBlueAt(1);
	    linea.playRedAt(1);
        assertThrows(Exception.class, () -> linea.playBlueAt(1));
	}
	
	@Test public void test06CantPlayTwoTimesInARow(){
	    Linea linea = new Linea( 4, 6, 'C');
	    linea.playBlueAt(1);
	    linea.playRedAt(1);
	    linea.playBlueAt(1);
	    linea.playRedAt(1);
	    linea.playBlueAt(1);
	    linea.playRedAt(1);
        assertThrows(Exception.class, () -> linea.playRedAt(1));
	}
	
	@Test public void test07BlueWinsByHorizontalLineAsTypeA(){
        Linea linea = new Linea( 4, 4, 'A');
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(4);
        assertTrue( linea.finished() );
    }
	
	@Test public void test08BlueWinsByVerticalLineAsTypeB(){
        Linea linea = new Linea( 4, 4, 'B');
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(1);
        linea.playRedAt(3);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(1);
        assertTrue( linea.finished() );
    }
	
	@Test public void test09BlueWinsByCrecentLineAsTypeC(){
        Linea linea = new Linea( 4, 4, 'B');
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(4);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(1);
        linea.playBlueAt(4);
        linea.playRedAt(4);
        linea.playBlueAt(4);
        assertTrue( linea.finished() );
    }
	
	@Test public void test10BlueWinsByDecrecentLineAsTypeC(){
        Linea linea = new Linea( 4, 4, 'B');
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(4);
        assertTrue( linea.finished() );
    }
	
	@Test public void print1() {
		Linea linea = new Linea( 4, 4, 'B');
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(4);
		System.out.println(linea.show());
	}
	
	@Test public void print2() {		
		Linea linea = new Linea( 4, 4, 'B');
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(4);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(1);
        linea.playBlueAt(4);
        linea.playRedAt(4);
        linea.playBlueAt(4);
		System.out.println(linea.show());
	}
	
	@Test public void print3() {		
		Linea linea = new Linea( 4, 4, 'B');
		linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(4);
		System.out.println(linea.show());
	}
	
	@Test public void print4() {		
		Linea linea = new Linea( 4, 4, 'B');
		linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(2);
		System.out.println(linea.show());
	}
	
}
    // test6 -- testear no se puede jugar cuando el juego termino
    // test7 -- testear no se puede jugar cuando no es el turno del jugador (playredat dos veces no se puede)
    // test8 -- testear gana por linea tipo A
    //test9 -- testear gana por columna tipo b
    //test10 -- gana por diagonal tipo c
