package heavenchess.ai;

import heavenchess.movement.Point;
import heavenchess.board.Chessboard;

@FunctionalInterface
interface PointsIterator {
    Iterable<Point> apply(Chessboard chessboard);
}