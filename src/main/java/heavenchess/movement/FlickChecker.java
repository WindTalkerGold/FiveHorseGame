package heavenchess.movement;

import com.google.common.collect.Iterables;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

import java.util.ArrayList;

public class FlickChecker {
    private final Point point;
    private final Chessboard chessboard;

    public FlickChecker(Chessboard chessboard, Point point) {
        if(!chessboard.getSlotState(point).hasChessman()) {
            throw new IllegalArgumentException("Must init FlickChecker with chessman on!");
        }

        this.point = point;
        this.chessboard = chessboard;
    }

    public Iterable<Point> getFlickedPoints() {
        ChessboardState reversed = chessboard.getSlotState(point).getFlip();

        Iterable<Point> counterChessman = chessboard.getSlotsOfState(reversed);
        Iterable<Point> nearbys = Iterables.filter(counterChessman, p->p.projectionDistance(point)==1);
        boolean checkDiagonal = (point.getX()+point.getY()) % 2 == 0;
        if(!checkDiagonal) {
            nearbys = Iterables.filter(nearbys, p->p.blockDistance(point) == 1);
        }

        // Invalid will be returned for out of bound
        return Iterables.filter(nearbys, p->
             chessboard.getSlotState(p.createWithPivot(point)) == reversed);


    }
}
