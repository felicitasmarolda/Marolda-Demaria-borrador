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

	private int playsCount = 0;
	
	public Line(int base, int height, char type) {
		
		if ((base > 0)   &&
			(height > 0) &&
		    (type == 'A' || type == 'B' || type == 'C')) {
			
			gameBase = base;
			gameHeight = height;
			gameTriumphType = TriumphType.typeFor(type);
	
			for (int i = 0; i < gameBase; i++) {
				board.add(new ArrayList());
			}
		}
		else {
			throw new RuntimeException("Parámetros inválidos.");
		}
	}
	
	public Line dropPieceWithColorAt(Character color, int column) {
		board.get(column - 1).add(color);
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
								   					  turnInstance.gameFinished() == finished() ) )
						   .toList()
						   .get(0);
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
	
	public boolean finished() {
		return blueWins() ||
			   redWins()  ||
			   draw();
	}

	private boolean draw() {
		return playsCount == gameBase * gameHeight;
	}

	private boolean redWins() {
		return gameTriumphType.redWonInCurrentType( this );
	}
	
	private boolean blueWins() {
		return gameTriumphType.blueWonInCurrentType( this );
	}
	
	public boolean verificyTriumphInTypeAAs(char color) {
		return horizontalTriumph(color) ||
			   verticalTriumph(color);
	}
	
	public boolean verificyTriumphInTypeBAs(char color) {
		return increasingDiagonalTriumph(color) ||
			   decreasingDiagonalTriumph(color);
	}
	
	public boolean verificyTriumphInTypeCAs(char color) {
		return horizontalTriumph(color) 	    || 
			   verticalTriumph(color)	 	    ||
			   increasingDiagonalTriumph(color) ||
			   decreasingDiagonalTriumph(color);
	}
	
	private int getRow(int column) {
		return board.get(column - 1).size();
	}
	
	public boolean actualPieceVerticalTriumph(Character color, int column) {
		return 4 <= IntStream.range(0, gameHeight)
							 .takeWhile(y -> getColorAt(column - 1, y) == color)
							 .count() ;
	}
	
	public boolean actualPieceHorizontalTriumph(Character color, int column) {
		return 4 <= IntStream.range(0, gameBase)
							 .takeWhile(xCoord -> getColorAt(xCoord, getRow(column) - 1) == color)
							 .count() ;
	}
	
	public boolean actualPieceIncreasingDiagonalTriumph(Character color, int column) {
		return 4 <= IntStream.range(0, gameHeight)
							 .takeWhile(y -> getColorAt(column - getRow(column) + y, y) == color)
							 .count() ;
	}
	
	public boolean actualPieceDecreasingDiagonalTriumph(Character color, int column) {
		return 4 <= IntStream.range(0, gameHeight)
							 .takeWhile(y -> getColorAt(column - 1 + getRow(column) - 1 - y, y) == color)
							 .count() ;
	}
	
	private boolean verticalTriumph(Character color) {
		return IntStream.range(0, gameBase)
						.anyMatch(i -> 4 <= IntStream.range(0, gameHeight)
													 .takeWhile(j -> getColorAt(i, j) == color)
													 .count() );
	}
	
	private boolean horizontalTriumph(Character color) {
		return IntStream.range(0, gameHeight)
        				.anyMatch(j -> 4 <= IntStream.range(0, gameBase)
        											 .takeWhile(i -> getColorAt(i, j) == color)
        											 .count() );
	}
	
	private boolean increasingDiagonalTriumph(Character color) {
		return IntStream.range(- gameHeight, gameBase)
						.anyMatch(i -> 4 == IntStream.range(0, gameBase)
													 .takeWhile(j -> getColorAt(i + j, j) == color)
													 .count() );
	}
	
	private boolean decreasingDiagonalTriumph(Character color) {
		return IntStream.range(0, gameBase + gameHeight)
						.anyMatch(i -> 4 <= IntStream.range(0, gameBase)
													 .takeWhile(j -> getColorAt(i - j, j) == color)
													 .count() );
	}
	
	private boolean columnIsValid(int column) {
		return board.get(column - 1).size() != gameHeight &&
			   1 <= column && column <= gameBase;
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
