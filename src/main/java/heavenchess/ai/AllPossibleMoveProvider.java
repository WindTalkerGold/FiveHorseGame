package heavenchess.ai;

import java.util.Iterator;
import heavenchess.movement.*;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

// todo: add the filter to exclude attackable moves
public class AllPossibleMoveProvider implements MoveProvider, Iterator<Move> {
    private final MoveEnumerator enumerator;
    private final Iterator<Point> pointsOfThisSide;
    private Iterator<Move> validMoveIter;
    private Move nextMove;
    private Point currentPoint;

    public AllPossibleMoveProvider(Chessboard chessboard, ChessboardState side) {
        this.pointsOfThisSide = chessboard.getSlotsOfState(side).iterator();
        this.enumerator = new MoveEnumerator(chessboard);
        this.currentPoint = null;
    }

    public Move next() {
        if (nextMove == null) {
            throw new IllegalAccessError("Must make sure hasNext() returns true before calling next()");
        }

        Move ret = nextMove;
        nextMove = null;
        return ret;
    }

    public boolean hasNext() {
        if (nextMove != null)
            return true;

        while (true) {
            if (currentPoint == null) {
                if (pointsOfThisSide.hasNext()) {
                    currentPoint = pointsOfThisSide.next();
                } else {
                    return false;
                }
            }
            if (validMoveIter == null) {
                validMoveIter = enumerator.allValidMoves(currentPoint).iterator();
            }
            if (validMoveIter.hasNext()) {
                nextMove = validMoveIter.next();
                return true;
            } else {
                validMoveIter = null;
                currentPoint = null;
            }
        }
    }

}