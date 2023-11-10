package linea;

public class TypeC extends TriumphType {

    public boolean canHandle(char mode) {
        return mode == 'C';
    }

    public Line checkMeAsTriumphTypeFor(Line line) {
        line.establishTriumphType(new TypeC());
        return line;
    }

    public boolean verifyTriumphInGameAsTypeWithColorAndColumn(char color, int column, Line line) {
        return currentPieceHorizontalTriumph(color, column, line)         ||
                currentPieceVerticalTriumph(color, column, line)   		  ||
                currentPieceDiagonalTriumph(color, column, line);
    }
    public char getTriumphType() {
        return 'C';
    }
}