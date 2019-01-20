package heavenchess.movement;

// might be two different validators given normal and advanced chessboard mode
public interface ChessboardValidator {
    boolean isPointValid(Point point);
    boolean isMovementValid(Move move);
}
