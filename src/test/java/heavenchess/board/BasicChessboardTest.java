package heavenchess.board;

import com.google.common.collect.Iterables;
import heavenchess.movement.Move;
import heavenchess.movement.Point;
import junit.framework.TestCase;
import org.junit.Test;

public class BasicChessboardTest extends TestCase {
    @Test
    public void testInitChessboard() {
        BasicChessboard chessboard = new BasicChessboard();
        validateChessboard(chessboard, 0, 4);
    }

    @Test
    public void testHorizontalMovement() {
        BasicChessboard chessboard = new BasicChessboard();
        for(int i=0;i<5;i++) {
            assertTrue(chessboard.move(new Move(new Point(i, 0), new Point(i, 1)), ChessboardState.LeftOn));
            assertFalse(chessboard.move(new Move(new Point(i, 0), new Point(i, 1)), ChessboardState.LeftOn));
            assertFalse(chessboard.move(new Move(new Point(i, 4), new Point(i, 3)), ChessboardState.LeftOn));
            assertTrue(chessboard.move(new Move(new Point(i, 4), new Point(i, 3)), ChessboardState.RightOn));
        }
        validateChessboard(chessboard, 1, 3);
    }

    @Test
    public void testVerticalMovement() {
        BasicChessboard chessboard = new BasicChessboard();
        Move move1 = new Move(new Point(0, 0), new Point(0, 2));
        assertTrue(chessboard.move(move1, ChessboardState.LeftOn));
        assertEquals(ChessboardState.Empty, chessboard.getSlotState(new Point(0, 0)));
        assertEquals(ChessboardState.LeftOn, chessboard.getSlotState(new Point(0, 2)));

        Move move2 = new Move(new Point(0, 2), new Point(2, 2));
        assertTrue(chessboard.move(move2, ChessboardState.LeftOn));

        assertEquals(ChessboardState.Empty, chessboard.getSlotState(new Point(0, 2)));
        assertEquals(ChessboardState.LeftOn, chessboard.getSlotState(new Point(2, 2)));
    }

    @Test
    public void testDiagonalMove() {
        BasicChessboard chessboard = new BasicChessboard();
        Move move1 = new Move(new Point(0, 0), new Point(2, 2));
        assertTrue(chessboard.move(move1, ChessboardState.LeftOn));

        assertEquals(ChessboardState.Empty, chessboard.getSlotState(new Point(0, 0)));
        assertEquals(ChessboardState.LeftOn, chessboard.getSlotState(new Point(2, 2)));

        Move move2 = new Move(new Point(1, 0), new Point(2, 1));
        assertFalse(chessboard.move(move2, ChessboardState.LeftOn));
        assertEquals(ChessboardState.Empty, chessboard.getSlotState(new Point(2, 1)));
        assertEquals(ChessboardState.LeftOn, chessboard.getSlotState(new Point(1, 0)));

        Move move3 = new Move(new Point(4, 4), new Point(0, 0));
        assertFalse(chessboard.move(move3, ChessboardState.RightOn));

        Move move4 = new Move(new Point(2, 2), new Point(2, 3));

        assertTrue(chessboard.move(move4, ChessboardState.LeftOn));
        assertTrue(chessboard.move(move3, ChessboardState.RightOn));

        assertEquals(ChessboardState.Empty, chessboard.getSlotState(new Point(2, 2)));
        assertEquals(ChessboardState.LeftOn, chessboard.getSlotState(new Point(2, 3)));
        assertEquals(ChessboardState.Empty, chessboard.getSlotState(new Point(4, 4)));
        assertEquals(ChessboardState.RightOn, chessboard.getSlotState(new Point(0, 0)));
    }

    private void validateChessboard(BasicChessboard chessboard, int i2, int i3) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ChessboardState expectedState;
                if (j == i2) {
                    expectedState = ChessboardState.LeftOn;
                } else if (j == i3) {
                    expectedState = ChessboardState.RightOn;
                } else {
                    expectedState = ChessboardState.Empty;
                }
                Point point = new Point(i, j);
                assertEquals(expectedState, chessboard.getSlotState(point));
                assertTrue(Iterables.indexOf(chessboard.getSlotsOfState(expectedState), pt -> point.equals(pt)) >= 0);
            }
        }
    }
}