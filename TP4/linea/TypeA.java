package linea;

public class TypeA extends TriumphType {

	public boolean redWonInCurrentType(Line line) {
		return line.verificyTriumphInTypeAAs('R');
	}

	public boolean blueWonInCurrentType(Line line) {
		return line.verificyTriumphInTypeAAs('B');
	}

	public boolean canHandle(Character mode) {
		return mode == 'A';
	}
}