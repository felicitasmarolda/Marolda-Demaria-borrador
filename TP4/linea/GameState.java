package linea;

public abstract class GameState {
	public abstract Line playBlueInGameWith(Line line, int column);
	public abstract Line playRedInGameWith(Line line, int column);
	public abstract boolean gameFinished();
	public abstract Character turnColor();
}
