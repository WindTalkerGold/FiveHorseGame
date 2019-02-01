package heavenchess.ai;

import junit.framework.TestCase;
import org.junit.Test;
import heavenchess.ai.AttackEnumerator;
import heavenchess.board.BasicChessboard;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;
import heavenchess.movement.AllPossibleMoveProvider;
import heavenchess.movement.BasicModeValidator;
import heavenchess.movement.ChessboardValidator;
import heavenchess.movement.FlickClampManager;
import heavenchess.movement.Move;
import heavenchess.ui.ConsoleChessboardDrawer;

public class ChessboardRankerTest extends TestCase {

    @Test
    public void testEnum() {
        Chessboard chessboard = new BasicChessboard();
        FlickClampManager chessboardManager = new FlickClampManager(chessboard);
        AttackEnumerator evaluator = new AttackEnumerator();
        ChessboardValidator validator = new BasicModeValidator();

        ConsoleChessboardDrawer drawer = new ConsoleChessboardDrawer();
        ChessboardState currentState = ChessboardState.LeftOn;
        AllPossibleMoveProvider enumerator = new AllPossibleMoveProvider(chessboard, currentState);

        int totalMoves = 0;
        int positiveMoves = 0;
        while (enumerator.hasNext()) {
            Move step = enumerator.next();
            Chessboard moveResult = chessboard.move2(step, currentState);
            int changedNum = chessboardManager.runFlip(currentState);
            int score = changedNum + evaluator.score(currentState, moveResult);
            totalMoves++;
            if(score >= 0) {
                positiveMoves ++;
            }
        }
        assertEquals(25, totalMoves);
        assertEquals(9, positiveMoves);
    }

}