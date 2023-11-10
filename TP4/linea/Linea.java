package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Line {
	
	private ArrayList<ArrayList<Character>> board = new ArrayList();
	public int gameBase;
	public int gameHeight;
	private TriumphType gameTriumphType;
	private List<GameState> possibleCurrentGameStates = new ArrayList<GameState>( Arrays.asList( new TurnRed(), 
																		   						 new GameFinished() ) );
	private GameState gameState = new TurnRed();

	private int lastPlayedColumn;
	
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
		lastPlayedColumn = column;
		return this;
	}
	
	public void changeTurnTo(GameState nextTurn) {
		possibleCurrentGameStates.remove(0);
		possibleCurrentGameStates.add(0, nextTurn);
		gameState = possibleCurrentGameStates.stream()
						   					 .filter(turnInstance -> turnInstance.gameFinished() == 
						   					 						 finishedBeingTurnOf( gameState.turnColor() ) )
						   					 .toList()
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
	
	public boolean wins(char color) {
		return gameTriumphType.verifyTriumphInGameAsTypeWithColorAndColumn( color, lastPlayedColumn, this );
	}

	private boolean draw() {
		return board.stream()
					.allMatch( columnArray -> columnArray.size() == gameHeight );
	}
	
	public Character getColorAt(int x, int y) {
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
	
	public int getRow(int column) {
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
