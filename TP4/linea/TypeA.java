package linea;

public class TypeA extends TriumphType {

	public boolean canHandle(char mode) {
		return mode == 'A';
	}

	public Line checkMeAsTriumphTypeFor(Line line) {
		line.establishTriumphType(new TypeA());
		return line;
	}

	public boolean verifyTriumphInGameAsTypeWithColorAndColumn(char color, int column, Line line) {
		return currentPieceHorizontalTriumph(color, column, line)         ||
				currentPieceVerticalTriumph(color, column, line);
	}

	@Override
	public char getTriumphType() {
		return 'A';
	}
}