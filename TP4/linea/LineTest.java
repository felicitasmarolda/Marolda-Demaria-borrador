package linea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class LineTest {
	
	@Test public void test00NewLinea() {
		Line game = new Line(3, 5, 'C');
		assertEquals(3 , game.tablero().size()) ;
	}
	
	@Test public void test01RedPlaysCorrectlyAndShown(){
        Line line = new Line( 3, 3, 'C' );
        line.playRedAt( 1 );
        assertEquals( 'R', line.tablero().get( 0 ).get( 0 ).charValue());
    }
	
	@Test public void test02RedPlaysCorrectlyAndShown(){
        Line line = new Line( 3, 3, 'C' );
        line.playRedAt(2);
        line.playBlueAt( 1 );
        assertEquals( 'B', line.tablero().get( 0 ).get( 0 ).charValue());
    }
	
	@Test public void test03GameEndsWhenBoardFull(){
        Line line = new Line( 3, 2, 'C');
        allGameMoves(line, 1,2,2,3,3,1);
        assertTrue( line.finished() );
    }

    @Test public void test04CantPlayInNonExistentColumn() {
        Line line = new Line( 3, 2, 'C');
        assertThrowsLike("Index 3 out of bounds for length 3", () -> line.playRedAt(4));
    }
	
	@Test public void test05CanNotPlayInFullColumn(){
	    Line line = new Line( 3, 6, 'C');
        allGameMoves(line, 1,1,1,1,1,1);
        assertThrowsLike("Inadequate column.", () -> line.playRedAt(1));
    }

    @Test public void test06BlueCanNotStartTheGame(){
        Line line = new Line( 3, 3, 'C');
        assertThrowsLike("Incorrect turn.", () -> line.playBlueAt(1));
    }
	
	@Test public void test07CanNotPlayTwoTimesInARow(){
	    Line line = new Line( 4, 6, 'C');
        allGameMoves(line, 1,2,2,1,1);
        assertThrowsLike("Incorrect turn.", () -> line.playRedAt(1));
    }
	
	@Test public void test08RedWinsByHorizontalLineAsTypeA(){
        Line line = new Line( 4, 4, 'A');
        allGameMoves(line, 1,1,2,2,3,3,4);
        assertTrue( line.finished() );
        assertTrue( line.redWins());
        assertFalse( line.blueWins());
    }
	
	@Test public void test09BlueWinsByVerticalLineAsTypeA(){
        Line line = new Line( 6, 4, 'A');
        allGameMoves(line, 5,1,2,1,3,1,2,1);
        assertTrue( line.finished() );
        assertTrue( line.blueWins());
        assertFalse( line.redWins());
    }
	
	@Test public void test10BlueWinsByCrecentLineAsTypeB(){
        Line line = new Line( 7, 4, 'B');
        allGameMoves(line, 6,1,2,2,4,3,3,3,1,4,4,4);
        assertTrue( line.finished() );
        assertTrue( line.blueWins());
        assertFalse( line.redWins());
    }
	
	@Test public void test11BlueWinsByDecrecentLineAsTypeB(){
        Line line = new Line( 6, 4, 'B');
        allGameMoves(line, 5,1,1,1,2,1,2,2,3,3,3,4);
        assertTrue( line.finished() );
        assertTrue( line.blueWins());
        assertFalse( line.redWins());
    }

    @Test public void test12BlueWinsByHorizontalLineAsTypeC(){
        Line line = new Line( 4, 4, 'C');
        allGameMoves(line, 3,1,2,1,2,1,2,1);
        assertTrue( line.finished() );
        assertTrue( line.blueWins());
        assertFalse( line.redWins());
    }

    @Test public void test13RedWinsByVerticalLineAsTypeC(){
        Line line = new Line( 6, 5, 'C');
        allGameMoves(line, 2,1,2,1,2,1,2);
        assertTrue( line.finished() );
        assertTrue( line.redWins());
        assertFalse( line.blueWins());
    }

    @Test public void test14BlueWinsByCrescentLineAsTypeC(){
        Line line = new Line( 4, 4, 'C');
        allGameMoves(line, 2, 1, 3, 2, 4, 3, 3, 4, 4, 4);
        assertTrue( line.finished() );
        assertTrue( line.blueWins());
        assertFalse( line.redWins());
    }
	
	@Test public void test15CantPlayWhenGameEnded(){
        Line line = new Line( 2, 4, 'C');
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        line.playBlueAt(1);
        line.playRedAt(2);
        assertThrows(Exception.class, () -> line.playBlueAt(2));
        assertThrowsLike("Game has finished.", () -> line.playRedAt(1));
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

    public void assertThrowsLike(String message, Executable executable) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }
    private static void allGameMoves(Line line, int ... columnsListOfEntries) {
        for (int i = 0; i < columnsListOfEntries.length; i++) {
            int column = columnsListOfEntries[i];

            if (i % 2 == 0) {
                line.playRedAt(column);
            } else {
                line.playBlueAt(column);
            }
        }
    }

    }