package linea;

public class TypeB extends TriumphType {

	public boolean redWonInCurrentType(Line line) {
		return line.verificyTriumphInTypeBAs('R');
	}

	public boolean blueWonInCurrentType(Line line) {
		return line.verificyTriumphInTypeBAs('B');
	}

	public boolean canHandle(Character mode) {
		return mode == 'B';
	}
}