package heavenchess.movement;

import com.google.common.collect.Iterables;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

public class FlickChecker {

    public Iterable<Point> getFlickedPoints(Chessboard chessboard, Point point) {
        Iterable<Point> nearbys = chessboard.getNearbyCounterparts(point);
        ChessboardState reversed = chessboard.getSlotState(point).getFlip();
        // Invalid will be returned for out of bound
        return Iterables.filter(nearbys, p->
             chessboard.getSlotState(p.createWithPivot(point)) == reversed);
    }
}
