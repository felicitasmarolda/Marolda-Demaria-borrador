package linea;

public class GameFinished extends GameState {

	public Line playBlueInGameWith(Line line, int column) {
		throw new RuntimeException("Game has finished.");
	}

	public Line playRedInGameWith(Line line, int column) {
		throw new RuntimeException("Game has finished.");
	}

	public boolean gameFinished() {
		return true;
	}

	public Character turnColor() {
		return null;
	}
}