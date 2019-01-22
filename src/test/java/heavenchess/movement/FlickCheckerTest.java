package heavenchess.movement;

import com.google.common.collect.Iterables;
import heavenchess.board.BasicChessboard;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;
import junit.framework.TestCase;
import org.junit.Test;

public class FlickCheckerTest extends TestCase {
    @Test
    public void testNoFlick() {
        BasicChessboard chessboard = new BasicChessboard();
        FlickChecker checker = new FlickChecker(chessboard, new Point(0, 0));
        assertEquals(0, Iterables.size(checker.getFlickedPoints()));
    }

    @Test
    public void testFlickNoDiagonal() {
        BasicChessboard chessboard = new BasicChessboard();
        chessboard.set(new Point(0, 2), ChessboardState.LeftOn);
        chessboard.set(new Point(2, 2), ChessboardState.LeftOn);
        Point pt1 = new Point(0, 1);
        chessboard.set(pt1, ChessboardState.RightOn);
        FlickChecker checker1 = new FlickChecker(chessboard, pt1);
        assertEquals(2, Iterables.size(checker1.getFlickedPoints()));

        Point pt2 = new Point(1, 2);
        chessboard.set(pt2, ChessboardState.RightOn);
        FlickChecker checker2 = new FlickChecker(chessboard, pt2);
        assertEquals(2, Iterables.size(checker2.getFlickedPoints()));
    }

    @Test
    public void testFlickDiagonal() {
        BasicChessboard chessboard = new BasicChessboard();
        chessboard.set(new Point(0, 2), ChessboardState.LeftOn);
        chessboard.set(new Point(2, 2), ChessboardState.LeftOn);
        Point pt1 = new Point(1, 1);
        chessboard.set(pt1, ChessboardState.RightOn);
        FlickChecker checker1 = new FlickChecker(chessboard, pt1);
        assertEquals(4, Iterables.size(checker1.getFlickedPoints()));
    }

}
