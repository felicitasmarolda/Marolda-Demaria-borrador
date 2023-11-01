package juego.linea;

public class Line {

        private int base;
        private int altura;
        private char[][] tablero;
        private int negras;
        private int blancas;
        private int turno;

        public Line(int base, int altura, char turno ) {

            this.base = base;
            this.altura = altura;
            this.tablero = new char[altura][base];
            this.negras = 0;
            this.blancas = 0;
            this.turno = turno;

            for ( int i = 0; i < altura; i++ ) {

                for ( int j = 0; j < base; j++ ) {

                    this.tablero[i][j] = ' ';

                }

            }

        }

        public String show() {

            String result = "";

            for ( int i = 0; i < this.altura; i++ ) {

                for ( int j = 0; j < this.base; j++ ) {

                    result += this.tablero[i][j];

                }

                result += "\n";

            }

            return result;

        }

        public boolean finished() {

            return this.negras + this.blancas == this.base * this.altura;

        }

        public void playRedAt( int position ) {

            if ( position < 0 || position >= this.base ) {

                throw new RuntimeException( "Posición incorrecta" );

            }

            if ( this.tablero[0][position] != ' ' ) {

                throw new RuntimeException( "Posición ocupada" );

            }

            this.tablero[0][position] = 'X';
            this.negras++;

        }

        public void playBlueAt( int position ) {

            if ( position < 0 || position >= this.base ) {

                throw new RuntimeException( "Posición incorrecta" );

            }

            if ( this.tablero[0][position] != ' ' ) {

                throw new RuntimeException( "Posición ocupada" );

            }

            this.tablero[0][position] = 'X';
            this.blancas++;
        }

    // mirar lo de usar la palabra "tablero"
    // crear lista de listas y agregar a play blue y red que pasa cuando ponen bien
}//