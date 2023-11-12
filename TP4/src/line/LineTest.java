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
        line.playRedAt(0);
        line.playBlueAt( 0 );
        line.playRedAt(1);
        line.playBlueAt( 1 );
        line.playRedAt(2);
        line.playBlueAt( 2 );
        assertEquals( true, line.finished());
    }
    @Test public void test05CantPlayInNonExistentColumn(){
        Line line = new Line( 3, 2, 'C');
        try {
            line.playRedAt( 3 );
        } catch (RuntimeException e) {
            assertEquals( "Incorrect column", e.getMessage());
        }
    }
    @Test public void test06CantPlayInFullColumn(){
        Line line = new Line( 3, 6, 'C');
        line.playRedAt(0);
        line.playBlueAt( 0 );
        line.playRedAt(0);
        line.playBlueAt( 0 );
        line.playRedAt(0);
        line.playBlueAt( 0 );
        try {
            line.playRedAt( 0 );
        } catch (RuntimeException e) {
            assertEquals( "Column is complete", e.getMessage());
        }
    }
    @Test public void test07RedCanNotPlayTwoTimesInARow(){
        Line line = new Line( 4, 4, 'C');
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
    @Test public void test09RedWinsByHorizontalLine(){
        Line line = new Line( 5, 5, 'A');
        line.playRedAt(0);
        line.playBlueAt( 0 );
        line.playRedAt(1);
        line.playBlueAt( 1 );
        line.playRedAt(2);
        line.playBlueAt( 2 );
        try {
            line.playRedAt( 3 );
        } catch (RuntimeException e) {
            assertEquals( "Red player won", e.getMessage());
        }
    }

    @Test public void test10BlueWinsByVerticalLine(){
        Line line = new Line( 5, 5, 'B');
        line.playRedAt(4);
        line.playBlueAt( 0 );
        line.playRedAt(0);
        line.playBlueAt( 0 );
        line.playRedAt(1);
        line.playBlueAt( 0 );
        line.playRedAt(2);
        try {
            line.playBlueAt( 0 );
        } catch (RuntimeException e) {
            assertEquals( "Blue player won", e.getMessage());
        }
    }
    @Test public void test11BlueWinsByDiagonalLine() {
        Line line = new Line(6, 6, 'C');
        line.playRedAt(5);
        line.playBlueAt(0);
        line.playRedAt(1);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(3);
        line.playRedAt(2);
        line.playBlueAt(2);
        line.playRedAt(3);
        line.playBlueAt(2);
        line.playRedAt(3);
        try {
            line.playBlueAt(3);
        } catch (RuntimeException e) {
            assertEquals("Blue player won", e.getMessage());
        }
    }
    @Test public void test12RedWinsByDiagonalLine(){
        Line line = new Line( 6, 6, 'C');
        line.playRedAt(5);
        line.playBlueAt(3);
        line.playRedAt(2);
        line.playBlueAt(2);
        line.playRedAt(1);
        line.playBlueAt(0);
        line.playRedAt(1);
        line.playBlueAt(1);
        line.playRedAt(0);
        line.playBlueAt(1);
        line.playRedAt(0);
        line.playBlueAt(0);
        try {
            line.playRedAt(0);
        } catch (RuntimeException e) {
            assertEquals("Red player won", e.getMessage());
        }
    }
// falta test de que solo empiece rojo
}