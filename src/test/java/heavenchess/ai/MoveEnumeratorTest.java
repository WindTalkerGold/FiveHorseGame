package heavenchess.ai;

import org.junit.Test;
import com.google.common.collect.Iterables;

import heavenchess.board.BasicChessboard;
import heavenchess.board.ChessboardState;
import heavenchess.movement.ChessboardValidator;
import heavenchess.movement.Point;
import junit.framework.TestCase;

public class MoveEnumeratorTest extends TestCase {
    @Test
    public void testEnumMove1() {
        BasicChessboard chessboard = new BasicChessboard();

        MoveEnumerator enumerator = new MoveEnumerator(chessboard);
        assertEquals(6, Iterables.size(enumerator.allValidMoves(new Point(0, 0))));
        assertEquals(3, Iterables.size(enumerator.allValidMoves(new Point(1, 0))));
        assertEquals(7, Iterables.size(enumerator.allValidMoves(new Point(2, 0))));
        
        chessboard.set(new Point(2, 2), ChessboardState.LeftOn);
        assertEquals(4, Iterables.size(enumerator.allValidMoves(new Point(0, 0))));
    }
}