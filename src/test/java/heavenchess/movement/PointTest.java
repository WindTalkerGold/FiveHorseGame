package heavenchess.movement;

import junit.framework.TestCase;
import org.junit.Test;

public class PointTest extends TestCase {
    @Test
    public void testBlockDistance() {
        Point pt12 = new Point(1, 2);
        Point pt30 = new Point(3, 0);
        Point pt11 = new Point(1, 1);
        Point pt13 = new Point(1, 3);

        assertEquals(4, pt12.blockDistance(pt30));
        assertEquals(1, pt12.blockDistance(pt11));
        assertEquals(1, pt12.blockDistance(pt13));
        assertEquals(5, pt13.blockDistance(pt30));
    }

    @Test
    public void testCreateWithPivot() {
        Point pt12 = new Point(1, 2);
        Point pt30 = new Point(3, 0);

        Point pt5_2 = new Point(5, -2);
        assertEquals(pt5_2, pt12.createWithPivot(pt30));
    }
}
