package linea;

public class TurnBlue extends GameState {

	public Line playBlueInGameWith(Line line, int column) {
		line.dropPieceWithColorAt('B', column);
		line.changeTurnTo(new TurnRed());
		return line;
	}

	public Line playRedInGameWith(Line line, int column) {
		throw new RuntimeException("Incorrect turn.");
	}


	public boolean gameFinished() {
		return false;
	}

	public Character turnColor() {
		return 'B';
	}
}
