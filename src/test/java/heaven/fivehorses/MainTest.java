package heaven.fivehorses;

import junit.framework.TestCase;
import org.junit.Test;

public class MainTest extends TestCase {

    @Test
    public void test_sum() {
        Main main = new Main();
        assertEquals(3, main.sum(1, 2));
    }
}
