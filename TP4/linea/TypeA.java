package linea;

public class TypeA extends TriumphType {

	public boolean canHandle(char mode) {
		return mode == 'A';
	}

	public Line checkMeAsTriumphTypeFor(Line line) {
		line.establishTriumphType(new TypeA());
		return line;
	}

	public boolean verifyTriumphInGameAsTypeWithColorAndColumn(Line line, char color, int column) {
		return line.currentPieceHorizontalTriumph(color, column)         ||
				line.currentPieceVerticalTriumph(color, column);
	}
}