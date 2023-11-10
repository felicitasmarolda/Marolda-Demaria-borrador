package linea;

public class TurnRed extends GameState {

	public Line playBlueInGameWith(Line line, int column) {
		throw new RuntimeException("Incorrect turn.");
	}

	public Line playRedInGameWith(Line line, int column) {
		line.dropPieceWithColorAt('R', column);
		line.changeTurnTo(new TurnBlue());
		return line;
	}


	public boolean gameFinished() {
		return false;
	}
}
