package heavenchess;

import junit.framework.TestCase;
import org.apache.commons.cli.ParseException;
import org.junit.Test;

public class ChessOptionsTest extends TestCase {

    @Test
    public void testBothSizeHuman() {
        String[] args = new String[]{};
        try {
            ChessOptions options = ChessOptions.parse(args);
            assertFalse(options.isLeftBot());
            assertFalse(options.isRightBot());
        } catch(ParseException pex) {
            assertFalse(true);
        }
    }

    @Test
    public void testLeftHuman() {
        String[] args = new String[]{"-l"};
        try {
            ChessOptions options = ChessOptions.parse(args);
            assertTrue(options.isLeftBot());
            assertFalse(options.isRightBot());
        } catch(ParseException pex) {
            assertFalse(true);
        }
    }

    @Test
    public void testRightHuman() {
        String[] args = new String[]{"-r"};
        try {
            ChessOptions options = ChessOptions.parse(args);
            assertFalse(options.isLeftBot());
            assertTrue(options.isRightBot());
        } catch(ParseException pex) {
            assertFalse(true);
        }
    }

    @Test
    public void testBothBot() {
        String[] args = new String[]{"-l", "-r"};
        try {
            ChessOptions options = ChessOptions.parse(args);
            // expect exception here, shouldn't reach this line
            assertFalse(true);
        } catch(ParseException pex) {
            assertFalse(true);
        } catch(IllegalArgumentException aex) {
            assertFalse(false);
        }
    }
}
