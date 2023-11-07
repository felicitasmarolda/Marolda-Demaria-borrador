package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Linea {
	
	private ArrayList<ArrayList<Character>> tablero = new ArrayList();
	private int juegoBase;
	private int juegoAltura;
	private char juegoModo;
	
	private int red;
	private int blue;
	private char turno = 'B';

	public Linea(int base, int altura, char modo) {
		if ((base > 0)   &&
			(altura > 0) &&
		    (modo == 'A' || modo == 'B' || modo == 'C')) {
			juegoBase = base;
			juegoAltura = altura;
			juegoModo = modo;
	
			for (int i = 0; i < juegoBase; i++) {
				tablero.add(new ArrayList());
			}
		}
		else {
			throw new RuntimeException("Parámetros inválidos.");
		}
	}
	
	public Linea playRedAt(int columna) {
		if (turno == 'R'			   &&
			columnaIsInBounds(columna) && 
			columnaIsPlayable(columna) &&
			! finished()) {
			tablero.get(columna - 1).add('R');
			red++;
			turno = 'B';
			return this;
		}
		else {
			throw new RuntimeException("columna inadecuada");
		}
	}	

	public Linea playBlueAt(int columna) {
		if (turno == 'B'			   &&
			columnaIsInBounds(columna) && 
			columnaIsPlayable(columna) &&
			! finished()) {
			
			tablero.get(columna - 1).add('B');
			blue++;
			turno = 'R';
			return this;
		}
		else {
			throw new RuntimeException("No es posible jugar.");
		}
	}	
	
	public String show() {
		String grid = "|";
		for (int j = 0; j < juegoBase; j++) {
			grid += "---|";
		}
		grid += "\n";
		for (int i = juegoAltura - 1; i >= 0; i--) {
			String file = "|";
			for (int j = 0; j < juegoBase; j++) {
				file += " " + deQueColorEs(tablero, j, i) + " |";
			}
			file += "\n" + "|";
			for (int j = 0; j < juegoBase; j++) {
				file += "---|";
			}
			file += "\n";
			grid += file;
		}
		return grid;
	}
	
	public boolean finished() {
		return ganoBlue() ||
			   ganoRed()  ||
			   empate();
	}
	
	private boolean empate() {
		return red + blue == juegoBase * juegoAltura;
	}

	private boolean ganoRed() {
		return ganoHorizontal('R') 		  ||
			   ganoVertical('R') 		  ||
			   ganoDiagonalCreciente('R') ||
			   ganoDiagonalDecreciente('R');
	}

	private boolean ganoBlue() {
		return ganoHorizontal('B') 		  ||
			   ganoVertical('B') 		  ||
		   	   ganoDiagonalCreciente('B') ||
		   	   ganoDiagonalDecreciente('B');
	}
	
	private boolean ganoVertical(Character color) {
		return IntStream.range(0, juegoBase)
						.anyMatch(i -> 4 <= IntStream.range(0, juegoAltura)
													 .takeWhile(j -> deQueColorEs(tablero, i, j) == color)
													 .count() );
	}
	
	private boolean ganoHorizontal(Character color) {
		return IntStream.range(0, juegoAltura)
        				.anyMatch(j -> 4 <= IntStream.range(0, juegoBase)
        											 .takeWhile(i -> deQueColorEs(tablero, i, j) == color)
        											 .count() );
	}
	
	private boolean ganoDiagonalCreciente(Character color) {
		return IntStream.range(- juegoAltura, juegoBase)
						.anyMatch(i -> 4 == IntStream.range(0, juegoBase)
													 .takeWhile(j -> deQueColorEs(tablero, i + j, j) == color)
													 .count() );
	}
	
	private boolean ganoDiagonalDecreciente(Character color) {
		return IntStream.range(0, juegoBase + juegoAltura)
						.anyMatch(i -> 4 <= IntStream.range(0, juegoBase)
													 .takeWhile(j -> deQueColorEs(tablero, i - j, j) == color)
													 .count() );
	}
	
// VERTICAL:
//		for (int i = 0; i < juegoBase; i++) {
//			int connected = 0;
//			for (int j = 0; j < juegoAltura; j++) {
//				if (deQueColorEs(tablero, i, j) == color) {
//					connected++;
//				}
//				if (deQueColorEs(tablero, i, j) != color) {
//					connected = 0;
//				}
//				if (connected == 4) {
//					return true;
//				}
//			}
//		}
//		return false;
		
// HORIZONTAL:	
//		for (int i = 0; i < juegoAltura; i++) {
//			int connected = 0;
//			for (int j = 0; j < juegoBase; j++) {
//				if (deQueColorEs(tablero, j, i) == color) {
//					connected++;
//				}
//				if (deQueColorEs(tablero, j, i) != color) {
//					connected = 0;
//				}
//				if (connected == 4) {
//					return true;
//				}
//			}
//		}
//		return false;
		
// DIAGONAL CRECIENTE:
//		for (int i = - juegoAltura; i < juegoBase; i++) {
//			int connected = 0;
//			for (int j = 0; j < juegoAltura; j++) {
//				if (deQueColorEs(tablero, i + j, j) == color) {
//					connected++;
//				}
//				if (deQueColorEs(tablero, i + j, j) != color) {
//					connected = 0;
//				}
//				if (connected == 4) {
//					return true;
//				}
//			}
//		}
//		return false;
		
// DIAGONAL DECRECIENTE:
//		for (int i = juegoBase + juegoAltura; i > 0; i--) {
//			int connected = 0;
//			for (int j = 0; j < juegoAltura; j++) {
//				if (deQueColorEs(tablero, i - j, j) == color) {
//					connected++;
//				}
//				if (deQueColorEs(tablero, i - j, j) != color) {
//					connected = 0;
//				}
//				if (connected == 4) {
//					return true;
//				}
//			}
//		}
//		return false;
	
	private boolean columnaIsPlayable(int columna) {
		return tablero.get(columna - 1).size() != juegoAltura;
	}
	
	private boolean columnaIsInBounds(int columna) {
		return 1 <= columna && columna <= juegoBase;
	}
	
	private Character deQueColorEs(ArrayList<ArrayList<Character>> tablero, int x, int y) {
		if (x < 0 || 
			y < 0 || 
			x >= juegoBase || 
			y >= juegoAltura ||
			tablero.get(x).size() <= y) {
			
			return ' ';			
		}
		else {
			return tablero.get(x).get(y);
		}
	}

	public ArrayList<ArrayList<Character>> tablero() {
		return tablero;
	}
}
