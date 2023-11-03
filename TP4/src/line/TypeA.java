package juego.linea;

import java.util.ArrayList;

public class TypeA extends Triumph{

    @Override
    public boolean possibleTriumph(ArrayList<ArrayList<Character>> board, int column, int height) {
        return false;
    }
}
//triunfo en fila