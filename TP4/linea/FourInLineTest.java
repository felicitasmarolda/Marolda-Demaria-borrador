package linea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class FourInLineTest {
	
	@Test public void test00NewLinea() {
		FourInLine game = new FourInLine(3, 5, 'C');
		assertEquals(3 , game.tablero().size()) ;
	}
	
	@Test public void test01RedPlaysCorrectlyAndShown(){
        FourInLine fourInLine = new FourInLine( 3, 3, 'C' );
        fourInLine.playRedAt( 1 );
        assertEquals( 'X', fourInLine.tablero().get( 0 ).get( 0 ));
    }
	
	@Test public void test02BLuePlaysCorrectlyAndShown(){
        FourInLine fourInLine = new FourInLine( 3, 3, 'C' );
        fourInLine.playRedAt(2);
        fourInLine.playBlueAt( 1 );
        assertEquals( 'O', fourInLine.tablero().get( 0 ).get( 0 ));
    }
	
	@Test public void test03GameEndsWhenBoardFull(){
        FourInLine fourInLine = new FourInLine( 3, 2, 'C');
        allGameMoves(fourInLine, 1,2,2,3,3,1);
        assertTrue( fourInLine.finished() );
    }

    @Test public void test04CantPlayInNonExistentColumn() {
        FourInLine fourInLine = new FourInLine( 3, 2, 'C');
        assertThrowsLike("Index 3 out of bounds for length 3", () -> fourInLine.playRedAt(4));
    }
	
	@Test public void test05CanNotPlayInFullColumn(){
	    FourInLine fourInLine = new FourInLine( 3, 6, 'C');
        allGameMoves(fourInLine, 1,1,1,1,1,1);
        assertThrowsLike("Inadequate column.", () -> fourInLine.playRedAt(1));
    }

    @Test public void test06BlueCanNotStartTheGame(){
        FourInLine fourInLine = new FourInLine( 3, 3, 'C');
        assertThrowsLike("Incorrect turn.", () -> fourInLine.playBlueAt(1));
    }
	
	@Test public void test07CanNotPlayTwoTimesInARow(){
	    FourInLine fourInLine = new FourInLine( 4, 6, 'C');
        allGameMoves(fourInLine, 1,2,2,1,1);
        assertThrowsLike("Incorrect turn.", () -> fourInLine.playRedAt(1));
    }
	
	@Test public void test08RedWinsByHorizontalLineAsTypeA(){
        FourInLine fourInLine = new FourInLine( 4, 4, 'A');
        allGameMoves(fourInLine, 1,1,2,2,3,3,4);
        assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins('X'));
        assertFalse( fourInLine.wins('O'));
    }
	
	@Test public void test09BlueWinsByVerticalLineAsTypeA(){
        FourInLine fourInLine = new FourInLine( 6, 4, 'A');
        allGameMoves(fourInLine, 5,1,2,1,3,1,2,1);
        assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins('O'));
        assertFalse( fourInLine.wins('X'));
    }
	
	@Test public void test10BlueDoesNotWinByIncreasingDiagonalAsTypeA(){
        FourInLine fourInLine = new FourInLine( 7, 4, 'A');
        allGameMoves(fourInLine, 6,1,2,2,4,3,3,3,1,4,4,4);
        assertFalse( fourInLine.finished() );
    }
	
	@Test public void test10BlueDoesNotWinByDecreasingDiagonalAsTypeA(){
        FourInLine fourInLine = new FourInLine( 7, 4, 'A');
        allGameMoves(fourInLine, 5,1,1,1,2,1,2,2,3,3,3,4);
        assertFalse( fourInLine.finished() );
    }
	
	@Test public void test10BlueWinsByCrecentLineAsTypeB(){
        FourInLine fourInLine = new FourInLine( 7, 4, 'B');
        allGameMoves(fourInLine, 6,1,2,2,4,3,3,3,1,4,4,4);
        assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins('O'));
        assertFalse( fourInLine.wins('X'));
    }
	
	@Test public void test11BlueWinsByDecrecentLineAsTypeB(){
        FourInLine fourInLine = new FourInLine( 6, 4, 'B');
        allGameMoves(fourInLine, 5,1,1,1,2,1,2,2,3,3,3,4);
        assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins('O'));
        assertFalse( fourInLine.wins('X'));
    }

    @Test public void test12BlueWinsByHorizontalLineAsTypeC(){
        FourInLine fourInLine = new FourInLine( 4, 4, 'C');
        allGameMoves(fourInLine, 3,1,2,1,2,1,2,1);
        assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins('O'));
        assertFalse( fourInLine.wins('X'));
    }

    @Test public void test13RedWinsByVerticalLineAsTypeC(){
        FourInLine fourInLine = new FourInLine( 6, 5, 'C');
        allGameMoves(fourInLine, 2,1,2,1,2,1,2);
        assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins('X'));
        assertFalse( fourInLine.wins('O'));
    }

    @Test public void test14BlueWinsByCrescentLineAsTypeC(){
        FourInLine fourInLine = new FourInLine( 4, 4, 'C');
        allGameMoves(fourInLine, 2,1,3,2,3,3,4,4,4,4);
        assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins('O'));
        assertFalse( fourInLine.wins('X'));
    }
	
	@Test public void test15CantPlayWhenGameEnded(){
        FourInLine fourInLine = new FourInLine( 2, 4, 'C');
        allGameMoves(fourInLine, 2,1,2,1,2,1,2);
        assertThrows(Exception.class, () -> fourInLine.playBlueAt(2));
        assertThrowsLike("Game has finished.", () -> fourInLine.playRedAt(1));
    }

	@Test public void print1() {
		FourInLine line = new FourInLine( 4, 4, 'C');
        allGameMoves(line, 1,1,1,2,1,2,2,3,3,3,4);
		System.out.println("Print 1:\n");
		System.out.println(line.show());
	}

	@Test public void print2() {
		FourInLine line = new FourInLine( 4, 4, 'C');
        allGameMoves(line, 1,2,2,4,3,3,3,1,4,4,4);
		System.out.println("Print 2:\n");
		System.out.println(line.show());
	}

	@Test public void print3() {
		FourInLine line = new FourInLine( 4, 4, 'C');
        allGameMoves(line, 1,1,2,2,3,3,4);
		System.out.println("Print 3:\n");
		System.out.println(line.show());
	}

	@Test public void print4() {		
		FourInLine line = new FourInLine( 4, 4, 'C');
        allGameMoves(line, 1,2,1,2,1,2,3,2);
		System.out.println("Print 4:\n");
		System.out.println(line.show());
	}
	
    @Test public void testerPrint() {		
		FourInLine line = new FourInLine( 6, 6, 'C');
        allGameMoves(line, 1,1,2,2,3,3,4);
		System.out.println("Tester print:\n");
		System.out.println(line.show());
	}

    private void assertThrowsLike(String message, Executable executable) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }
        
    private void allGameMoves(FourInLine fourInLine, int ... charArrayOfColumnEntries) {
    
	    IntStream.range(0, charArrayOfColumnEntries.length)
	             .forEach(i -> {
	                 int column = charArrayOfColumnEntries[i];
	                 if (i % 2 == 0) {
	                     fourInLine.playRedAt(column);
	                 } else {
	                     fourInLine.playBlueAt(column);
	                 } });
    	
//        for (int i = 0; i < charArrayOfColumnEntries.length; i++) {
//            int column = charArrayOfColumnEntries[i];
//
//            if (i % 2 == 0) {
//                line.playRedAt(column);
//            } 
//            else {
//                line.playBlueAt(column);
//            }
//        }
    }

}
