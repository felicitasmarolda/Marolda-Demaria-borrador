package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class TriumphType {

	static List<TriumphType> possibleModes = new ArrayList<TriumphType>( Arrays.asList(new TypeA(),
			new TypeB(),
			new TypeC()) );

	public static TriumphType typeFor(Character typeChar) {
		return possibleModes.stream()
				.filter(typeInstance -> typeInstance.canHandle(typeChar))
				.collect(Collectors.toList())
				.get(0);
	}

	public abstract Line checkMeAsTriumphTypeFor(Line linea);
	public abstract boolean canHandle(char typeChar);
	public abstract boolean verifyTriumphInGameAsTypeWithColorAndColumn(char color, int column, Line line);


	protected boolean currentPieceVerticalTriumph(char color, int column, Line line) {
		return 4 <= IntStream.range(0, line.gameHeight)
				.takeWhile(y -> line.getColorAt(column - 1, y) == color)
				.count() ;
	}

	protected boolean currentPieceHorizontalTriumph(char color, int column, Line line) {
		return 4 <= IntStream.range(0, line.gameBase)
				.takeWhile(xCoord -> line.getColorAt(xCoord, line.getRow(column) - 1) == color)
				.count() ;
	}

	protected boolean currentPieceIncreasingDiagonalTriumph(char color, int column, Line line) {
		return 4 <= IntStream.range(0, line.gameHeight)
				.takeWhile(y -> line.getColorAt(column - line.getRow(column) + y, y) == color)
				.count() ;
	}

	protected boolean currentPieceDecreasingDiagonalTriumph(char color, int column, Line line) {
		return 4 <= IntStream.range(0, line.gameHeight)
				.takeWhile(y -> line.getColorAt(column - 1 + line.getRow(column) - 1 - y, y) == color)
				.count() ;
	}

	public boolean currentPieceDiagonalTriumph(char color, int column, Line line) {
		return currentPieceIncreasingDiagonalTriumph(color, column, line) ||
				currentPieceDecreasingDiagonalTriumph(color, column, line);
	}

	public abstract char getTriumphType();

}
