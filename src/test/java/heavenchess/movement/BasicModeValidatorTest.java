package heavenchess.movement;

import junit.framework.TestCase;
import org.junit.Test;

import heavenchess.board.BasicChessboard;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

public class BasicModeValidatorTest extends TestCase {
    @Test
    public void testInvalidMove_NotSameLine() {
        Chessboard chessboard = new BasicChessboard();
        ChessboardValidator validator = chessboard.getValidator();
        Move move = new Move(new Point(0, 0), new Point(2, 1));
        assertFalse(validator.isMovementValid(move, chessboard, ChessboardState.LeftOn));
    }

    @Test
    public void testInvalidMove_EndNotEmpty() {
        Chessboard chessboard = new BasicChessboard();
        ChessboardValidator validator = chessboard.getValidator();
        Move move1 = new Move(new Point(0, 0), new Point(0, 4));
        assertFalse(validator.isMovementValid(move1, chessboard, ChessboardState.LeftOn));
        Move move2 = new Move(new Point(0, 0), new Point(4, 4));
        assertFalse(validator.isMovementValid(move2, chessboard, ChessboardState.LeftOn));
    }

    @Test
    public void testInvalidMove_StartNotCorrectState() {
        Chessboard chessboard = new BasicChessboard();
        ChessboardValidator validator = chessboard.getValidator();
        Move move1 = new Move(new Point(0, 0), new Point(0, 3));
        assertFalse(validator.isMovementValid(move1, chessboard, ChessboardState.RightOn));
        Move move2 = new Move(new Point(0, 0), new Point(3, 3));
        assertFalse(validator.isMovementValid(move2, chessboard, ChessboardState.RightOn));
    }

    @Test
    public void testInvalidMove_StartNotInDiagonal() {
        Chessboard chessboard = new BasicChessboard();
        ChessboardValidator validator = chessboard.getValidator();
        Move move1 = new Move(new Point(1, 0), new Point(2, 1));
        assertFalse(validator.isMovementValid(move1, chessboard, ChessboardState.LeftOn));
        Move move2 = new Move(new Point(1, 0), new Point(0, 1));
        assertFalse(validator.isMovementValid(move2, chessboard, ChessboardState.LeftOn));
    }

    @Test
    public void testInvalidMove_OccupiedOnTheWay() {
        Chessboard chessboard = new BasicChessboard();
        ChessboardValidator validator = chessboard.getValidator();
        Move move1 = new Move(new Point(0, 0), new Point(3, 3));
        assertTrue(validator.isMovementValid(move1, chessboard, ChessboardState.LeftOn));
        chessboard.set(new Point(2, 2), ChessboardState.LeftOn);
        assertFalse(validator.isMovementValid(move1, chessboard, ChessboardState.LeftOn));
    }

    @Test
    public void testValidMove() {
        Chessboard chessboard = new BasicChessboard();
        ChessboardValidator validator = chessboard.getValidator();
        Move move1 = new Move(new Point(0, 0), new Point(3, 3));
        assertTrue(validator.isMovementValid(move1, chessboard, ChessboardState.LeftOn));

        chessboard.set(new Point(2, 2), ChessboardState.LeftOn);
        Move move2 = new Move(new Point(2, 2), new Point(0, 2));
        assertTrue(validator.isMovementValid(move2, chessboard, ChessboardState.LeftOn));
    
        Move move3 = new Move(new Point(0, 0), new Point(0, 3));
        assertTrue(validator.isMovementValid(move3, chessboard, ChessboardState.LeftOn));
    }
}