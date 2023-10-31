package juego.linea;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineTest {
    @Test
    public void test00CreatedLineIsEmpty(){
        Line line = new Line( 3, 3, 'C' );
        String expected = "   \n   \n   \n";
        assertEquals( expected, line.show() );
    }

    @Test public void test01RedPlaysShownInBoard(){
        Line line = new Line( 3, 3, 'C' );
        line.playRedAt( 1 );
        String expected = " C \n   \n   \n";
        assertEquals( expected, line.show() );
    }

    @Test public void test02BluePlaysShownInBoard(){
        Line line = new Line( 3, 3, 'C' );
        line.playBlueAt( 1 );
        String expected = " C \n   \n   \n";
        assertEquals( expected, line.show() );
    }
    // prueba
}
