package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Line {

	private ArrayList<ArrayList<Character>> board = new ArrayList();
	private int gameBase;
	private int gameHeight;
	private TriumphType gameTriumphType;
	private List<GameState> possibleCurrentGameStates = new ArrayList<GameState>( Arrays.asList( new TurnBlue(),
			new GameFinished() ) );
	private GameState gameState = new TurnBlue();

	private int lastPlayedColumnPosition;
	private int playsCount;

	public Line(int base, int height, char type) {

		if ((base > 0)   &&
				(height > 0)) {

			gameBase = base;
			gameHeight = height;
			checkAndDetermineTriumphType(type);

			for (int i = 0; i < gameBase; i++) {
				board.add(new ArrayList());
			}
		}
		else {
			throw new RuntimeException("Parámetros inválidos.");
		}
	}

	public Line checkAndDetermineTriumphType(char type) {
		TriumphType typeInstance = TriumphType.typeFor(type);
		return typeInstance.checkMeAsTriumphTypeFor( this );
	}

	public Line establishTriumphType(TriumphType type) {
		gameTriumphType = type;
		return this;
	}

	public Line playRedAt(int column) {
		if (columnIsValid(column)) {

			gameState.playRedInGameWith(this, column);
			return this;
		}
		else {
			throw new RuntimeException("Inadequate column.");
		}
	}

	public Line playBlueAt(int column) {
		if (columnIsValid(column)) {

			gameState.playBlueInGameWith(this, column);
			return this;
		}
		else {
			throw new RuntimeException("Can not play.");
		}
	}

	private boolean columnIsValid(int column) {
		return board.get(column - 1).size() != gameHeight &&
				1 <= column && column <= gameBase;
	}

	public Line dropPieceWithColorAt(char color, int column) {
		board.get(column - 1).add(color);
		lastPlayedColumnPosition = column;
		return this;
	}

	public void changeTurnTo(GameState nextTurn) {
		playsCount++;
		possibleCurrentGameStates.remove(0);
		possibleCurrentGameStates.add(0, nextTurn);
		gameState = possibleCurrentGameStates.stream()
				.filter(turnInstance -> ( playsCount < 4 &&
						! turnInstance.gameFinished() ) ||
						( playsCount >= 4 &&
								turnInstance.gameFinished() == finishedBeingTurnOf( gameState.turnColor() ) ) )
				.collect(Collectors.toList())
				.get(0);
	}

	public boolean finished() {
		return wins('B') ||
				wins('R') ||
				draw();
	}

	public boolean finishedBeingTurnOf(char color) {
		return wins(color) ||
				draw();
	}

	private boolean wins(char color) {
		return gameTriumphType.verifyTriumphInGameAsTypeWithColorAndColumn( this, color, lastPlayedColumnPosition );
	}

	private boolean draw() {
		return playsCount == gameBase * gameHeight;
	}

	public boolean currentPieceVerticalTriumph(char color, int column) {
		return 4 <= IntStream.range(0, gameHeight)
				.takeWhile(y -> getColorAt(column - 1, y) == color)
				.count() ;
	}

	public boolean currentPieceHorizontalTriumph(char color, int column) {
		return 4 <= IntStream.range(0, gameBase)
				.takeWhile(xCoord -> getColorAt(xCoord, getRow(column) - 1) == color)
				.count() ;
	}

	public boolean currentPieceIncreasingDiagonalTriumph(char color, int column) {
		return 4 <= IntStream.range(0, gameHeight)
				.takeWhile(y -> getColorAt(column - getRow(column) + y, y) == color)
				.count() ;
	}

	public boolean currentPieceDecreasingDiagonalTriumph(char color, int column) {
		return 4 <= IntStream.range(0, gameHeight)
				.takeWhile(y -> getColorAt(column - 1 + getRow(column) - 1 - y, y) == color)
				.count() ;
	}

	public boolean currentPieceDiagonalTriumph(char color, int column) {
		return currentPieceIncreasingDiagonalTriumph(color, column) ||
				currentPieceDecreasingDiagonalTriumph(color, column);
	}

	private Character getColorAt(int x, int y) {
		if (x < 0 ||
				y < 0 ||
				x >= gameBase ||
				y >= gameHeight ||
				board.get(x).size() <= y) {

			return ' ';
		}
		else {
			return board.get(x).get(y);
		}
	}

	private int getRow(int column) {
		return board.get(column - 1).size();
	}

	public String show() {
		String line = "┼" + IntStream.range(0, gameBase)
				.mapToObj(i -> "───┼")
				.collect(Collectors.joining()) + "\n";

		String grid = line + IntStream.range(0, gameHeight)
				.mapToObj(i -> "│" + IntStream.range(0, gameBase).mapToObj(j -> " " + getColorAt(j, gameHeight - 1 - i) + " │")
						.collect(Collectors.joining()) + "\n" + line)
				.collect(Collectors.joining());

		return grid;
	}

	public ArrayList<ArrayList<Character>> tablero() {
		return board;
	}
}
