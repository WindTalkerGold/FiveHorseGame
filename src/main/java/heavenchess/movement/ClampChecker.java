package heavenchess.movement;

import com.google.common.collect.Iterables;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

public class ClampChecker {
    public Iterable<Point> getClampedPoints(Chessboard chessboard, Point point) {
        Iterable<Point> nearbys = chessboard.getNearbyCounterparts(point);
        ChessboardState state = chessboard.getSlotState(point);
        // Invalid will be returned for out of bound
        return Iterables.filter(nearbys, p->
                chessboard.getSlotState(point.createWithPivot(p)) == state);


    }
}