package linea;

public class TypeNull extends TriumphType {

	public boolean canHandle(char mode) {
		return mode != 'A' && 
			   mode != 'B' &&
			   mode != 'C';
	}

	public Line checkMeAsTriumphTypeFor(Line line) {
		throw new RuntimeException("Parámetro de tipo de estrategia inválido.");
	}
	

//	public boolean wonInCurrentType(Line line, char color, int column) {
//		return false;
//	}
	
	public boolean verifyTriumphInGameAsTypeWithColorAndColumn(Line line, char color, int column) {
		return false;	
	}
}