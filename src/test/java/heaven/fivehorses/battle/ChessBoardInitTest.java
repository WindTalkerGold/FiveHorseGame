package heaven.fivehorses.battle;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardInitTest extends TestCase {
    private ChessBoard chessBoard = new ChessBoard();

    @Test
    public void test_chessBoardInitPlayerSide() {
        for(int i=0;i<chessBoard.Cols;i++) {
            assertEquals(1, chessBoard.getPlayerSide(i, 0));
            assertEquals(-1, chessBoard.getPlayerSide(i, chessBoard.Cols-1));
            for(int j=1;j<chessBoard.Cols-1;j++) {
                assertEquals(0, chessBoard.getPlayerSide(i, j));
            }
        }
        for(int i=0;i<chessBoard.Cols;i++) {
            if(i==4 || i== 0)
                assertEquals(2, chessBoard.getPlayerSide(5, i));
            else
                assertEquals(0, chessBoard.getPlayerSide(5, i));
        }
        for(int i=0;i<chessBoard.Cols;i++) {
            if(i != 2)
                assertEquals(2, chessBoard.getPlayerSide(6, i));
            else
                assertEquals(0, chessBoard.getPlayerSide(6, i));
        }
    }

    @Test
    public void test_chessBoardInitPositionMovements_00() {
        assertTrue(chessBoard.canMove(Direction.DownRight, 0, 0));
        assertTrue(chessBoard.canMove(Direction.Down, 0, 0));
        assertTrue(chessBoard.canMove(Direction.Right, 0, 0));
        assertEquals(3, chessBoard.getValidMovementCount(0, 3));
    }

    @Test
    public void test_chessBoardInitPositionMovements_01() {
        assertTrue(chessBoard.canMove(Direction.Left, 0, 1));
        assertTrue(chessBoard.canMove(Direction.Down, 0, 1));
        assertTrue(chessBoard.canMove(Direction.Right, 0, 1));
        assertEquals(3, chessBoard.getValidMovementCount(0, 1));
    }

    @Test
    public void test_chessBoardInitPositionMovements_02() {
        assertFalse(chessBoard.canMove(Direction.Up, 0, 2));
        assertFalse(chessBoard.canMove(Direction.UpLeft, 0, 2));
        assertFalse(chessBoard.canMove(Direction.UpRight, 0, 2));
        assertEquals(5, chessBoard.getValidMovementCount(0, 2));
    }

    @Test
    public void test_chessBoardInitPositionMovements_04() {
        assertTrue(chessBoard.canMove(Direction.DownLeft, 0, 4));
        assertTrue(chessBoard.canMove(Direction.Down, 0, 4));
        assertTrue(chessBoard.canMove(Direction.Left, 0, 4));
        assertEquals(3, chessBoard.getValidMovementCount(0, 4));
    }
}
