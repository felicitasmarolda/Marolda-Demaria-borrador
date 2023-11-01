package juego.linea;

public class Line {

        private int base;
        private int height;
        private char[][] board;
        private int red;
        private int blue;
        private int turn;

        public Line(int base, int altura, char turno ) {

            this.base = base;
            this.height = altura;
            this.board = new char[altura][base];
            this.red = 0;
            this.blue = 0;
            this.turn = turn;

            for ( int i = 0; i < altura; i++ ) {

                for ( int j = 0; j < base; j++ ) {

                    this.board[i][j] = ' ';

                }

            }

        }

        public String show() {

            String result = "";

            for ( int i = 0; i < this.height; i++ ) {

                for ( int j = 0; j < this.base; j++ ) {

                    result += this.board[i][j];

                }

                result += "\n";

            }

            return result;

        }

        public boolean finished() {

            return this.red + this.blue == this.base * this.height;

        }

        public void playRedAt( int position ) {

            if ( position < 0 || position >= this.base ) {

                throw new RuntimeException( "Incorrect position" );

            }

            if ( this.board[0][position] != ' ' ) {

                throw new RuntimeException( "Occupied position" );

            }

            this.board[0][position] = 'X';
            this.red++;

        }

        public void playBlueAt( int position ) {

            if ( position < 0 || position >= this.base ) {

                throw new RuntimeException( "Incorrect position" );

            }

            if ( this.board[0][position] != ' ' ) {

                throw new RuntimeException( "Occupied position" );

            }

            this.board[0][position] = 'X';
            this.blue++;

        }// fijarse lo del uso de "tablero"
    // crear lista de listas y ver como afecta cuando agregas una ficha
}