package heavenchess.movement;

import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

// might be two different validators given normal and advanced chessboard mode
public interface ChessboardValidator {
    boolean isPointValid(Point point, Chessboard chessboard);
    boolean isMovementValid(Move move, Chessboard chessboard, ChessboardState validateFor);
}
