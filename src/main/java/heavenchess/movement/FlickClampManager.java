package heavenchess.movement;

import java.util.HashSet;

import com.google.common.base.Preconditions;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

public final class FlickClampManager {
    private final Chessboard chessboard;
    private final FlickChecker flickChecker;
    private final ClampChecker clampChecker;

    public FlickClampManager(Chessboard chessboard) {
        this.chessboard = chessboard;
        this.flickChecker = new FlickChecker(chessboard);
        this.clampChecker = new ClampChecker(chessboard);
    }

    public int runFlip(ChessboardState side) {
        Preconditions.checkArgument(side.hasChessman(),
                "side must be either leftOn or rightOn");

        int totalChanged = 0;
        while(true) {
            boolean hasAnyChessmanChanged = false;
            Iterable<Point> flippedByFlick = runFlickCheck(side);
            for(Point p : flippedByFlick) {
                chessboard.flip(p);
                hasAnyChessmanChanged = true;
                totalChanged++;
            }
            // after flipping, we need to recheck for flick
            if(hasAnyChessmanChanged) {
                continue;
            }

            Point clamppedPoint = findFirstClamp(side);
            if(clamppedPoint == null)
                break;
            chessboard.flip(clamppedPoint);
            totalChanged++;
        }

        return totalChanged;
    }

    private Iterable<Point> runFlickCheck(ChessboardState side) {
        HashSet<Point> points = new HashSet<>();
        for(Point pointOfThisSide : chessboard.getSlotsOfState(side)) {
            for(Point flippedPoints : flickChecker.getFlickedPoints(pointOfThisSide)) {
                points.add(flippedPoints);
            }
        }
        return points;
    }

    private Point findFirstClamp(ChessboardState side) {
        for(Point pointOfThisSide : chessboard.getSlotsOfState(side)) {
            for(Point clamppedPoints : clampChecker.getClampedPoints(pointOfThisSide)) {
                return clamppedPoints;
            }
        }
        return null;
    }
}