package heavenchess.ai;

import heavenchess.movement.Point;
import heavenchess.board.Chessboard;

@FunctionalInterface
public interface PointIterator {
    Iterable<Point> apply(Chessboard chessboard);
}