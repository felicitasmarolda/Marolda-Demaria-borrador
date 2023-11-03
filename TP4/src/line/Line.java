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

        public Line(int base, int height, char turn ) {

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
                if (!turnHistory.isEmpty() && turnHistory.get(turnHistory.size() - 1) == 'R') {
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
                turnHistory.add('R');
            }

            else if (columnList.size() < height) {
                columnList.add('R');
                this.red += 1;
                turnHistory.add('R');
                if (searchForTriumph(column, board.get(column).size()-1, 'R')){
                    throw new RuntimeException( "Red player won" );
                }
            } else {
                throw new RuntimeException( "Column is complete" );
            }
        }

        public void playBlueAt( int column ) {
            if (turnHistory.size() > 1) {
                if (!turnHistory.isEmpty() && turnHistory.get(turnHistory.size() - 1) == 'B') {
                    throw new RuntimeException("Same player can not play two times in a row");
                }
            }
            if ( column  < 0 || column >= this.base ) {
                throw new RuntimeException( "Incorrect column" );
            }
            ArrayList<Character> columnList = this.board.get(column);

            if (columnList.isEmpty()) {
                columnList.add('B');
                this.blue += 1;
                turnHistory.add('B');
            }

            else if (columnList.size() < height) {
                columnList.add('B');
                this.blue += 1;
                turnHistory.add('B');
                if (searchForTriumph(column, board.get(column).size()-1, 'B')){
                    throw new RuntimeException( "Blue player won" );
                }
            } else {
                throw new RuntimeException( "Column is complete" );
            }

        }

        public ArrayList<Character> getHorizontalLine(int heightY) {
            ArrayList<Character> horizontalLine = new ArrayList<Character>();
            for (int i = 0; i < this.base; i++) {
                ArrayList<Character> columnList = this.board.get(i);
                if (columnList.size() > heightY) {
                    horizontalLine.add(columnList.get(heightY));
                } else {
                    horizontalLine.add(' ');
                }
            }
            return horizontalLine;
        }

        public ArrayList<Character> getDiagonalLine1(int heightY, int baseX){
            ArrayList<Character> diagonalLine = new ArrayList<Character>();
            int countY1 = heightY;
            int countX1 = baseX;
            while (countX1 < this.base && countY1 < this.height){
                if (this.board.get(countX1).size() >= height) {
                    diagonalLine.add(this.board.get(countX1).get(countY1));
                }
                else{
                    diagonalLine.add(' ');
                }
                countX1 += 1;
                countY1 += 1;
            }
            int countY2 = heightY;
            int countX2 = baseX;
            while (countX2 > 0 && countY2 > 0){
                if (this.board.get(countX2).size() >= height) {
                    diagonalLine.add(this.board.get(countX2).get(countY2));
                }
                else{
                    diagonalLine.add(' ');
                }
                countX2 -= 1;
                countY2 -= 1;
            }
            return diagonalLine;
        }

        public ArrayList<Character> getDiagonalLine2(int heightY, int baseX){
            ArrayList<Character> diagonalLine = new ArrayList<Character>();
            int countY1 = heightY;
            int countX1 = baseX;
            while (countX1 < this.base && countY1 > 0){
                if (this.board.get(countX1).size() >= height) {
                    diagonalLine.add(this.board.get(countX1).get(countY1));
                }
                else{
                    diagonalLine.add(' ');
                }
                countX1 += 1;
                countY1 -= 1;
            }
            int countY2 = heightY;
            int countX2 = baseX;
            while (countX2 > 0 && countY2 < this.height){
                if (this.board.get(countX2).size() >= height) {
                    diagonalLine.add(this.board.get(countX2).get(countY2));
                }
                else{
                    diagonalLine.add(' ');
                }
                countX2 -= 1;
                countY2 += 1;
            }
            return diagonalLine;
        }

        public boolean searchForTriumph(int columnX, int heightY, char player) {
            ArrayList<Character> horizontalLine = getHorizontalLine(heightY);
            int counter = 0;
            // horizontal
            for (char c : horizontalLine) {
                if (c == player) {
                    counter += 1;
                } else {
                    counter = 0;
                }
                ;
                if (counter == 4) {
                    return true;
                }
            }
            // vertical
            for (char c : this.board.get(columnX)) {
                if (c == player) {
                    counter += 1;
                } else {
                    counter = 0;
                }
                ;
                if (counter == 4) {
                    return true;
                }
            }
            //diagonal
            ArrayList<Character> diagonalLine1 = getDiagonalLine1(heightY, columnX);
            for (char c : diagonalLine1) {
                if (c == player) {
                    counter += 1;
                } else {
                    counter = 0;
                }
                ;
                if (counter == 4) {
                    return true;
                }
            }
            ArrayList<Character> diagonalLine2 = getDiagonalLine2(heightY, columnX);
            for (char c : diagonalLine2) {
                if (c == player) {
                    counter += 1;
                } else {
                    counter = 0;
                }
                if (counter == 4) {
                    return true;
                }
            }

            return false;

        }

}