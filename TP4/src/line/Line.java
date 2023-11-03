package juego.linea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Line {

        private int base;
        private int height;
        public ArrayList<ArrayList<Character>> board;
        private int red;
        private int blue;
        private char turn;
        private ArrayList<Character> turnHistory;

        public Line(int base, int height, char turno ) {

            this.base = base;
            this.height = height;
            this.board = new ArrayList<ArrayList<Character>>();
            this.red = 0;
            this.blue = 0;
            this.turn = turn;
            this.turnHistory = new ArrayList<Character>();

            for (int i = 0; i < base; i++) {
                ArrayList<Character> columnList = new ArrayList<Character>();
                this.board.add(columnList);}

        }
        // hacer show con el array en vez de [][] y agregar funcion que te dice que hay en una coordenada
        public String show() {
            return "";

            //String result = "";

            //for ( int i = 0; i < this.height; i++ ) {

              //  for ( int j = 0; j < this.base; j++ ) {

                //    result += this.board[i][j];

              //  }

              //  result += "\n";

          //  }

         //   return result;

        }

        public boolean finished() {

            return this.red + this.blue == this.base * this.height;

        }

        public void playRedAt( int column ) {
            if (this.red > 1){
                if (turnHistory.get(turnHistory.size() - 1) == 'R') {
                    throw new RuntimeException( "Same player can not play two times in a row" );
            }
            }
            if ( column < 0 || column >= this.base ) {
                throw new RuntimeException( "Incorrect column" );
            }
            ArrayList<Character> columnList = this.board.get(column);

            if (columnList.isEmpty()) {
                columnList.add('R');
                this.red += 1;
            }

            else if (columnList.size() < height) {
                columnList.add('R');
                this.red += 1;
                turnHistory.add('R');
                possibleTriumph(board, column, column.size()-1);
            } else {
                throw new RuntimeException( "Column is complete" );
            }
        }

        public void playBlueAt( int column ) {
                if (!turnHistory.isEmpty() && turnHistory.get(turnHistory.size() - 1) == 'B') {
                    throw new RuntimeException( "Same player can not play two times in a row" );
                }
            if ( column  < 0 || column >= this.base ) {
                throw new RuntimeException( "Incorrect column" );
            }
            ArrayList<Character> columnList = this.board.get(column);

            if (columnList.isEmpty()) {
                columnList.add('B');
                this.blue += 1;
            }

            else if (columnList.size() < height) {
                columnList.add('B');
                this.blue += 1;
                turnHistory.add('B');
            } else {
                throw new RuntimeException( "Column is complete" );
            }

        }// fijarse lo del uso de "tablero"
    // crear lista de listas y ver como afecta cuando agregas una ficha
}