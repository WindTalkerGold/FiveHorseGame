package heavenchess.movement;

import com.google.common.collect.Iterables;
import heavenchess.board.BasicChessboard;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;
import junit.framework.TestCase;
import org.junit.Test;

public class FlickClampCheckerTest extends TestCase {
    @Test
    public void testNoFlick() {
        BasicChessboard chessboard = new BasicChessboard();
        FlickChecker checker = new FlickChecker(chessboard);
        assertEquals(0, Iterables.size(checker.getFlickedPoints(new Point(0, 0))));
    }

    @Test
    public void testFlickNoDiagonal() {
        BasicChessboard chessboard = new BasicChessboard();
        chessboard.set(new Point(0, 2), ChessboardState.LeftOn);
        chessboard.set(new Point(2, 2), ChessboardState.LeftOn);
        Point pt1 = new Point(0, 1);
        chessboard.set(pt1, ChessboardState.RightOn);
        FlickChecker checker1 = new FlickChecker(chessboard);
        assertEquals(2, Iterables.size(checker1.getFlickedPoints(pt1)));

        Point pt2 = new Point(1, 2);
        chessboard.set(pt2, ChessboardState.RightOn);
        assertEquals(2, Iterables.size(checker1.getFlickedPoints(pt2)));
    }

    @Test
    public void testFlickDiagonal() {
        BasicChessboard chessboard = new BasicChessboard();
        chessboard.set(new Point(0, 2), ChessboardState.LeftOn);
        chessboard.set(new Point(2, 2), ChessboardState.LeftOn);
        Point pt1 = new Point(1, 1);
        chessboard.set(pt1, ChessboardState.RightOn);
        FlickChecker checker1 = new FlickChecker(chessboard);
        assertEquals(4, Iterables.size(checker1.getFlickedPoints(pt1)));
    }

    @Test
    public void testClampNoDiagonal() {
        BasicChessboard chessboard = new BasicChessboard();
        chessboard.set(new Point(1, 1), ChessboardState.RightOn);
        chessboard.set(new Point(2, 1), ChessboardState.RightOn);
        Point pt1 = new Point(1, 2);
        chessboard.set(pt1, ChessboardState.LeftOn);

        ClampChecker checker1 = new ClampChecker(chessboard);
        assertEquals(1, Iterables.size(checker1.getClampedPoints(pt1)));
    }

    @Test
    public void testClampDiagonal() {
        BasicChessboard chessboard = new BasicChessboard();
        chessboard.set(new Point(0, 1), ChessboardState.RightOn);
        chessboard.set(new Point(1, 1), ChessboardState.RightOn);
        Point pt1 = new Point(0, 2);
        chessboard.set(pt1, ChessboardState.LeftOn);
        ClampChecker checker1 = new ClampChecker(chessboard);
        assertEquals(2, Iterables.size(checker1.getClampedPoints(pt1)));
    }

    @Test
    public void testCombined() {
        Chessboard chessboard = new BasicChessboard();
        FlickClampManager manager = new FlickClampManager(chessboard);
        Move move1 = new Move(new Point(0, 0), new Point(2,2));
        chessboard.move(move1, ChessboardState.LeftOn);
        assertEquals(0, manager.runFlip(ChessboardState.LeftOn));
    
        Move move2 = new Move(new Point(3, 4), new Point(3,1));
        chessboard.move(move2, ChessboardState.RightOn);
        assertEquals(2, manager.runFlip(ChessboardState.RightOn));
        assertEquals(7, chessboard.countSlotsOfState(ChessboardState.RightOn));
        assertEquals(3, chessboard.countSlotsOfState(ChessboardState.LeftOn));
    
        Move move3 = new Move(new Point(1, 0), new Point(0,0));
        chessboard.move(move3, ChessboardState.LeftOn);
        assertEquals(0, manager.runFlip(ChessboardState.LeftOn));
    
        Move move4 = new Move(new Point(1, 4), new Point(1, 0));
        chessboard.move(move4, ChessboardState.RightOn);
        assertEquals(3, manager.runFlip(ChessboardState.RightOn));
        assertEquals(10, chessboard.countSlotsOfState(ChessboardState.RightOn));
        assertEquals(0, chessboard.countSlotsOfState(ChessboardState.LeftOn));

    }

}
