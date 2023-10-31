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

    @Test public void
}
