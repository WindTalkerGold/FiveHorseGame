package heavenchess.ai;

import com.google.common.collect.Iterables;

import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;
import heavenchess.movement.ChessboardValidator;
import heavenchess.movement.Move;
import heavenchess.movement.Point;

public class MoveEnumerator {
    private final ChessboardValidator validator;
    public MoveEnumerator(ChessboardValidator validator) {
        this.validator = validator;
    }

    public Iterable<Move> allValidMoves(Chessboard chessboard, Point point) {
        ChessboardState state = chessboard.getSlotState(point);
        if(!state.hasChessman()) {
            throw new IllegalArgumentException("the point must have chessman on it");
        }

        Iterable<Point> allEmptyPoints = chessboard.getSlotsOfState(ChessboardState.Empty);
        Iterable<Point> allCandidates = Iterables.filter(allEmptyPoints, p->sameLine(p, point));
        Iterable<Move> candidateMove = Iterables.transform(allCandidates, p->new Move(point, p));
        return Iterables.filter(candidateMove, m->validator.isMovementValid(m, chessboard, state));
    }

    private boolean sameLine(Point p1, Point p2) {
        //System.out.println(p1.toString()+"=>"+p2.toString());
        if(p1.getX() == p2.getX() || p1.getY() == p2.getY()) {
            //System.out.println("1, true");
            return true;
        }

        if((p1.getX()+p1.getY()) % 2 != 0) {
            //System.out.println("2, false");
            return false;
        }
        boolean onDiaganal = Math.abs(p1.getX()-p2.getX()) == Math.abs(p1.getY()-p2.getY());
        //System.out.println("3, "+onDiaganal);
        return onDiaganal;
    }
}