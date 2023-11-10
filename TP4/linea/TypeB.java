package linea;

public class TypeB extends TriumphType {

	public boolean canHandle(char mode) {
		return mode == 'B';
	}
	
	public Line checkMeAsTriumphTypeFor(Line line) {
		line.establishTriumphType(new TypeB());
		return line;
	}

//	public boolean wonInCurrentType(Line line, char color, int column) {
//		return line.verifyTriumphInTypeBAs(color, column);
//	}
	
	public boolean verifyTriumphInGameAsTypeWithColorAndColumn(Line line, char color, int column) {
		return line.currentPieceDiagonalTriumph(color, column);	
	}
}