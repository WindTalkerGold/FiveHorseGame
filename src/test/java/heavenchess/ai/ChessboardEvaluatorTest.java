package heavenchess.ai;

import org.junit.Test;
import com.google.common.collect.Iterables;

import heavenchess.board.BasicChessboard;
import heavenchess.board.ChessboardState;
import heavenchess.movement.Point;
import heavenchess.movement.Move;
import junit.framework.TestCase;

public class ChessboardEvaluatorTest extends TestCase {
    @Test
    public void testNotAttackable() {
        BasicChessboard chessboard = new BasicChessboard();
        ChessboardEvaluator evaluator = new ChessboardEvaluator();
        assertEquals(0, Iterables.size(evaluator.isAttackable(ChessboardState.LeftOn, chessboard)));
        assertEquals(0, Iterables.size(evaluator.isAttackable(ChessboardState.RightOn, chessboard)));
    }

    @Test
    public void testFlickable() {
        BasicChessboard chessboard = new BasicChessboard();
        ChessboardEvaluator evaluator = new ChessboardEvaluator();

        chessboard.move(new Move(new Point(0, 0), new Point(2,2)), ChessboardState.LeftOn);
        assertEquals(1, Iterables.size(evaluator.isAttackable(ChessboardState.LeftOn, chessboard)));
        assertEquals(0, Iterables.size(evaluator.isAttackable(ChessboardState.RightOn, chessboard)));
    }

    @Test
    public void testClampable() {
        BasicChessboard chessboard = new BasicChessboard();
        ChessboardEvaluator evaluator = new ChessboardEvaluator();

        chessboard.move(new Move(new Point(0, 0), new Point(3,3)), ChessboardState.LeftOn);
        assertEquals(3, Iterables.size(evaluator.isAttackable(ChessboardState.LeftOn, chessboard)));
        assertEquals(0, Iterables.size(evaluator.isAttackable(ChessboardState.RightOn, chessboard)));
    }
}