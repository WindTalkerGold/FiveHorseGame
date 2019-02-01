package heavenchess.ai;

import org.junit.Test;
import com.google.common.collect.Iterables;

import heavenchess.board.BasicChessboard;
import heavenchess.board.ChessboardState;
import heavenchess.movement.Point;
import heavenchess.movement.Move;
import junit.framework.TestCase;

public class AttackEnumeratorTest extends TestCase {
    @Test
    public void testNotAttackable() {
        BasicChessboard chessboard = new BasicChessboard();
        AttackEnumerator evaluator = new AttackEnumerator();
        assertEquals(0, Iterables.size(evaluator.getAllAttackableTargets(ChessboardState.LeftOn, chessboard)));
        assertEquals(0, Iterables.size(evaluator.getAllAttackableTargets(ChessboardState.RightOn, chessboard)));
    }

    @Test
    public void testCombined() {
        BasicChessboard chessboard = new BasicChessboard();
        AttackEnumerator evaluator = new AttackEnumerator();

        chessboard.move(new Move(new Point(1, 0), new Point(1,1)), ChessboardState.LeftOn);
        assertEquals(0, Iterables.size(evaluator.getAllAttackableTargets(ChessboardState.RightOn, chessboard)));

        chessboard.move(new Move(new Point(4, 4), new Point(4,2)), ChessboardState.RightOn);
        assertEquals(2, Iterables.size(evaluator.getAllAttackableTargets(ChessboardState.RightOn, chessboard)));
    }

    @Test
    public void testFlickable() {
        BasicChessboard chessboard = new BasicChessboard();
        AttackEnumerator evaluator = new AttackEnumerator();

        chessboard.move(new Move(new Point(0, 0), new Point(2,2)), ChessboardState.LeftOn);
        assertEquals(1, Iterables.size(evaluator.getAllAttackableTargets(ChessboardState.LeftOn, chessboard)));
        assertEquals(0, Iterables.size(evaluator.getAllAttackableTargets(ChessboardState.RightOn, chessboard)));
    }

    @Test
    public void testClampable() {
        BasicChessboard chessboard = new BasicChessboard();
        AttackEnumerator evaluator = new AttackEnumerator();

        chessboard.move(new Move(new Point(0, 0), new Point(3,3)), ChessboardState.LeftOn);
        assertEquals(3, Iterables.size(evaluator.getAllAttackableTargets(ChessboardState.LeftOn, chessboard)));
        assertEquals(0, Iterables.size(evaluator.getAllAttackableTargets(ChessboardState.RightOn, chessboard)));
    }
}