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
        Iterable<Point> allCandidates = Iterables.filter(allEmptyPoints, p->p.sameLine(point));
        Iterable<Move> candidateMove = Iterables.transform(allCandidates, p->new Move(point, p));
        return Iterables.filter(candidateMove, m->validator.isMovementValid(m, chessboard, state));
    }

    
}