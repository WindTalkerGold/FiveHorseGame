package heaven.fivehorses;

import  junit.framework.*;
import org.junit.Test;

public class MainTest extends TestCase  {

    @Test
    public void testAdd1Plus1() {

        Main main = new Main();
        assertEquals(3, main.add(1, 2));
    }

}
