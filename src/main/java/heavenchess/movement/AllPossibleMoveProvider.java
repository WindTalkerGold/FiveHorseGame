package heavenchess.movement;

import java.util.Iterator;
import heavenchess.ai.MoveEnumerator;
import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

public class AllPossibleMoveProvider implements MoveProvider, Iterator<Move> {
    private final Chessboard chessboard;
    private final ChessboardState side;
    private final MoveEnumerator enumerator;
    private final Iterator<Point> pointsOfThisSide;
    private Iterator<Move> validMoveIter;
    private Move nextMove;
    private Point currentPoint;

    public AllPossibleMoveProvider(Chessboard chessboard, ChessboardState side) {
        this.chessboard = chessboard;
        this.side = side;
        this.pointsOfThisSide = chessboard.getSlotsOfState(side).iterator();
        this.enumerator = new MoveEnumerator(chessboard.getValidator());
        this.currentPoint = null;
    }

    public Move next() {
        if(nextMove == null) {
            throw new IllegalAccessError("Must make sure hasNext() returns true before calling next()");
        }

        Move ret = nextMove;
        nextMove = null;
        return ret;
    }

    public boolean hasNext() {
        if(nextMove != null)
            return true;
        
        while(true) {
            if(currentPoint == null) {
                if(pointsOfThisSide.hasNext()) {
                    currentPoint = pointsOfThisSide.next();
                } else {
                    return false;
                }
            }
            if(validMoveIter == null) {
                validMoveIter = enumerator.allValidMoves(chessboard, currentPoint).iterator();    
            }
            if(validMoveIter.hasNext()) {
                nextMove = validMoveIter.next();
                return true;
            } else {
                validMoveIter = null;
                currentPoint = null;
            }
        }
    }

}