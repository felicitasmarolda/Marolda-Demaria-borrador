package juego.linea;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LineTest {
    @Test
    public void test00CreatedLineIsEmpty(){
        Line line = new Line( 3, 3, 'C' );
        assertEquals( new ArrayList<ArrayList<Character>>(Arrays.asList(new ArrayList<Character>(),
                                                    new ArrayList<Character>(),
                                                    new ArrayList<Character>())), line.board );
    }

    @Test public void test01RedPlaysCorrectlyAndShown(){
        Line line = new Line( 3, 3, 'C' );
        line.playRedAt( 1 );
        assertEquals( 'R', line.board.get( 1 ).get( 1 ).charValue());
    }

    @Test public void test02BluePlaysCorrectlyAndShown(){
        Line line = new Line( 3, 3, 'C');
        line.playBlueAt( 2 );
        assertEquals( 'B', line.board.get( 2 ).get( 1 ).charValue());
    }
    @Test public void test03GetsSpecificCoordinateCorrect(){
        Line line = new Line( 3, 3, 'C');
        line.playBlueAt( 2 );
        line.playRedAt(2);
        assertEquals( 'R', line.board.get( 2 ).get( 2 ).charValue());
    }
    @Test public void test04GameEndsWhenBoardFull(){
        Line line = new Line( 3, 2, 'C');
        line.playBlueAt( 0 );
        line.playRedAt(0);
        line.playBlueAt( 1 );
        line.playRedAt(1);
        line.playBlueAt( 2 );
        line.playRedAt(2);
        assertEquals( true, line.finished());
    }
    @Test public void test05CantPlayInNonExistentColumn(){
        Line line = new Line( 3, 2, 'C');
        try {
            line.playBlueAt( 3 );
        } catch (RuntimeException e) {
            assertEquals( "Incorrect column", e.getMessage());
        }
    }
    @Test public void test06CantPlayInFullColumn(){
        Line line = new Line( 3, 6, 'C');
        line.playBlueAt( 0 );
        line.playRedAt(0);
        line.playBlueAt( 0 );
        line.playRedAt(0);
        line.playBlueAt( 0 );
        line.playRedAt(0);
        try {
            line.playBlueAt( 0 );
        } catch (RuntimeException e) {
            assertEquals( "Column is complete", e.getMessage());
        }
    }
    @Test public void test07RedCanNotPlayTwoTimesInARow(){
        Line line = new Line( 4, 4, 'C');
        line.playBlueAt( 0 );
        line.playRedAt(0);
        line.playBlueAt( 0 );
        line.playRedAt(1);
        line.playBlueAt( 2 );
        line.playRedAt(1);
        try {
            line.playRedAt( 0 );
        } catch (RuntimeException e) {
            assertEquals( "Same player can not play two times in a row", e.getMessage());
        }
    }
    @Test public void test08BlueCanNotPlayTwoTimesInARow(){
        Line line = new Line( 4, 4, 'C');
        line.playBlueAt( 0 );
        line.playRedAt(0);
        line.playBlueAt( 1 );
        line.playRedAt(1);
        line.playBlueAt( 2 );
        try {
            line.playBlueAt( 1 );
        } catch (RuntimeException e) {
            assertEquals( "Same player can not play two times in a row", e.getMessage());
        }
    }
    @Test public void test09RedWinsByLineAsTypeA(){
        Line line = new Line( 4, 4, 'A');
        line.playBlueAt( 0 );
        line.playRedAt(0);
        line.playBlueAt( 1 );
        line.playRedAt(1);
        line.playBlueAt( 2 );
        line.playRedAt(2);
        line.playBlueAt( 3 );
        line.playRedAt(3);
        assertEquals( true, line.finished());
    }

    // test6 -- testear no se puede jugar cuando el juego termino
    // test7 -- testear no se puede jugar cuando no es el turno del jugador (playredat dos veces no se puede)
    // test8 -- testear gana por linea tipo A
    //test9 -- testear gana por columna tipo b
    //test10 -- gana por diagonal tipo c
}
