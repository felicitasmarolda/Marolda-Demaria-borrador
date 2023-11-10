package linea;

public class TypeB extends TriumphType {

	public boolean canHandle(char mode) {
		return mode == 'B';
	}

	public Line checkMeAsTriumphTypeFor(Line line) {
		line.establishTriumphType(new TypeB());
		return line;
	}

	public boolean verifyTriumphInGameAsTypeWithColorAndColumn(char color, int column, Line line) {
		return currentPieceDiagonalTriumph(color, column, line);
	}
}