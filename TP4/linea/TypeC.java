package linea;

public class TypeC extends TriumphType {

	public boolean redWonInCurrentType(Line line) {
		return line.verificyTriumphInTypeCAs('R');
	}

	public boolean blueWonInCurrentType(Line line) {
		return line.verificyTriumphInTypeCAs('B');
	}

	public boolean canHandle(Character mode) {
		return mode == 'C';
	}
}