package linea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class FourInLineTest {
    @Test public void test00NewLine(){
        assertEquals(showInTest("|   |   |   |\n"), new FourInLine( 3, 3, 'C' ).show() );
    }

    @Test public void test01RedPlaysCorrectlyAndShown(){
        FourInLine fourInLine = new FourInLine( 3, 3, 'C' );
        fourInLine.playRedAt( 1 );
        assertEquals(showInTest("| X |   |   |\n"), fourInLine.show() );
    }

    @Test public void test02BLuePlaysCorrectlyAndShown(){
        FourInLine fourInLine = new FourInLine( 3, 3, 'C' );
        allGameMoves(fourInLine, 2,1);
        assertEquals(showInTest("| O | X |   |\n"), fourInLine.show() );
    }

    @Test public void test03GameEndsWhenBoardFull(){
        FourInLine fourInLine = new FourInLine( 3, 2, 'C');
        allGameMoves(fourInLine, 1,2,2,3,3,1);
        assertTrue( fourInLine.finished() );
    }

    @Test public void test04RedWinsByHorizontalLineAsTypeA(){
        FourInLine fourInLine = new FourInLine( 4, 4, 'A');
        allGameMoves(fourInLine, 1,1,2,2,3,3,4);
        assertFinishedWinningWithAndLoosingWith(fourInLine, 'X', 'O');
    }

    @Test public void test05BlueWinsByVerticalLineAsTypeA(){
        FourInLine fourInLine = new FourInLine( 6, 4, 'A');
        allGameMoves(fourInLine, 5,1,2,1,3,1,2,1);
        assertFinishedWinningWithAndLoosingWith(fourInLine, 'O', 'X');
    }

    @Test public void test06BlueDoesNotWinByIncreasingDiagonalAsTypeA(){
        FourInLine fourInLine = new FourInLine( 7, 4, 'A');
        allGameMoves(fourInLine, 6,1,2,2,4,3,3,3,1,4,4,4);
        assertFalse( fourInLine.finished() );
    }

    @Test public void test07BlueDoesNotWinByDecreasingDiagonalAsTypeA(){
        FourInLine fourInLine = new FourInLine( 7, 4, 'A');
        allGameMoves(fourInLine, 5,1,1,1,2,1,2,2,3,3,3,4);
        assertFalse( fourInLine.finished() );
    }

    @Test public void test08BlueWinsByCrecentLineAsTypeB(){
        FourInLine fourInLine = new FourInLine( 7, 4, 'B');
        allGameMoves(fourInLine, 6,1,2,2,4,3,3,3,1,4,4,4);
        assertFinishedWinningWithAndLoosingWith(fourInLine, 'O', 'X');
    }

    @Test public void test09BlueWinsByDecrecentLineAsTypeB(){
        FourInLine fourInLine = new FourInLine( 6, 4, 'B');
        allGameMoves(fourInLine, 5,1,1,1,2,1,2,2,3,3,3,4);
        assertFinishedWinningWithAndLoosingWith(fourInLine, 'O', 'X');
    }

    @Test public void test10BlueDoesNotWinByVerticalLineAsTypeB(){
        FourInLine fourInLine = new FourInLine( 4, 4, 'B');
        allGameMoves(fourInLine, 3,1,2,1,2,1,2,1);
        assertFalse( fourInLine.finished() );
    }

    @Test public void test11RedDoesNotWinByHorizontalLineAsTypeB(){
        FourInLine fourInLine = new FourInLine( 4, 4, 'B');
        allGameMoves(fourInLine, 1,1,2,2,3,3,4);
        assertFalse( fourInLine.finished() );
    }

    @Test public void test12BlueWinsByHorizontalLineAsTypeC(){
        FourInLine fourInLine = new FourInLine( 4, 4, 'C');
        allGameMoves(fourInLine, 3,1,2,1,2,1,2,1);
        assertFinishedWinningWithAndLoosingWith(fourInLine, 'O', 'X');
    }

    @Test public void test13RedWinsByVerticalLineAsTypeC(){
        FourInLine fourInLine = new FourInLine( 6, 5, 'C');
        allGameMoves(fourInLine, 2,1,2,1,2,1,2);
        assertFinishedWinningWithAndLoosingWith(fourInLine, 'X', 'O');
    }

    @Test public void test14BlueWinsByCrescentLineAsTypeC(){
        FourInLine fourInLine = new FourInLine( 4, 4, 'C');
        allGameMoves(fourInLine, 2,1,3,2,3,3,4,4,4,4);
        assertFinishedWinningWithAndLoosingWith(fourInLine, 'O', 'X');
    }
    @Test public void test15BlueWinsByDecrecentLineAsTypeC(){
        FourInLine fourInLine = new FourInLine( 6, 4, 'C');
        allGameMoves(fourInLine, 5,1,1,1,2,1,2,2,3,3,3,4);
        assertFinishedWinningWithAndLoosingWith(fourInLine, 'O', 'X');
    }

    @Test public void test16CantPlayWhenGameEnded(){
        FourInLine fourInLine = new FourInLine( 2, 4, 'C');
        allGameMoves(fourInLine, 2,1,2,1,2,1,2);
        assertThrowsLike("Game has finished.", () -> fourInLine.playRedAt(1));
    }

    @Test public void test17CantPlayInNonExistentColumn() {
        FourInLine fourInLine = new FourInLine( 3, 2, 'C');
        assertThrowsLike("Inadequate column.", () -> fourInLine.playRedAt(4));
    }

    @Test public void test18CanNotPlayInFullColumn(){
        FourInLine fourInLine = new FourInLine( 3, 6, 'C');
        allGameMoves(fourInLine, 1,1,1,1,1,1);
        assertThrowsLike("Inadequate column.", () -> fourInLine.playRedAt(1));
    }

    @Test public void test19BlueCanNotStartTheGame(){
        FourInLine fourInLine = new FourInLine( 3, 3, 'C');
        assertThrowsLike("Incorrect turn.", () -> fourInLine.playBlueAt(1));
    }

    @Test public void test20CanNotPlayTwoTimesInARow(){
        FourInLine fourInLine = new FourInLine( 4, 6, 'C');
        allGameMoves(fourInLine, 1,2,2,1,1);
        assertThrowsLike("Incorrect turn.", () -> fourInLine.playRedAt(1));
    }

    @Test public void test21BaseCanNotBeLessThan0(){
        assertThrowsLike("Parámetros inválidos.", () -> new FourInLine( -1, 4, 'C'));
    }

    @Test public void test22HeightCanNotBeLessThan0(){
        assertThrowsLike("Parámetros inválidos.", () -> new FourInLine( 4, -1, 'C'));
    }

    @Test public void test23TypeCanNotBeAnythingOtherThanABC(){
        assertThrowsLike("Parámetro de tipo de estrategia inválido.", () -> new FourInLine( 4, 6, 'T'));
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
    }
    private String showInTest(String changedLine){
        return "|---|---|---|\n" +
                "|   |   |   |\n" +
                "|---|---|---|\n" +
                "|   |   |   |\n" +
                "|---|---|---|\n" + changedLine + "|---|---|---|\n";
    }

    private static void assertFinishedWinningWithAndLoosingWith(FourInLine fourInLine, char win, char loose) {
        assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins(win));
        assertFalse( fourInLine.wins(loose));
    }

}
