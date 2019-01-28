package heavenchess.movement;

import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

import java.util.Arrays;
import java.util.Iterator;

import com.google.common.collect.Iterators;

public class BasicModeValidator implements ChessboardValidator {
    @Override
    public boolean isMovementValid(Move move, Chessboard chessboard, ChessboardState validateFor) {
        if(!validateFor.hasChessman()) {
            throw new IllegalArgumentException("Must validator for either LeftOn or RightOn!");
        }

        // 1) start != end
        if(move.start.equals(move.end)) {
            return false;
        }
        
        // 2) start must be validatorFor, end must be empty
        //    if start or end is not in range, InValid will be returned
        if(chessboard.getSlotState(move.start) != validateFor ||
           chessboard.getSlotState(move.end) != ChessboardState.Empty) {
            return false;
        }

        // 3) start and end must be in a same line
        Iterator<Point> innerPoints = getPointsOnTheWay(move.start, move.end);
        if(innerPoints == null) {
            return false;
        }

        // 4) all points inside must be empty
        return Iterators.all(innerPoints, p->chessboard.getSlotState(p) == ChessboardState.Empty);
    }

    @Override
    public boolean isPointValid(Point point, Chessboard chessboard) {
        return chessboard.getSlotState(point) != ChessboardState.Invalid;
    }

    private Integer[] generator = new Integer[] {1,2,3,4};

    // assume start != end
    private Iterator<Point> getPointsOnTheWay(Point start, Point end) {
        int sx = start.getX();
        int sy = start.getY();
        int ex = end.getX();
        int ey = end.getY();

        int dx = ex-sx;
        int dy = ey-sy;
        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);
        int d = Math.max(absDx, absDy);

        int dpx, dpy;
        if(dx == 0) {
            dpx = 0;
            dpy = dy / absDy;
        } else if(dy == 0) {
            dpx = dx / absDx;
            dpy = 0;
        } else if(absDx != absDy) {
            // is diagonal position, but not in any diagonal line
            return null;
        } else if ((sx+sy)%2 != 0){
            // is diagonal
            return null;
        } else {
            dpx = dx / absDx;
            dpy = dy / absDy;
        }

        return Arrays.stream(generator)
                .filter(step->step<d)
                .map(step->new Point(sx+dpx*step, sy+dpy*step))
                .iterator();
    }
}
