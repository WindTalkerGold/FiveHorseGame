package heavenchess.ai;

import com.google.common.collect.Iterables;

import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;
import heavenchess.movement.ChessboardValidator;
import heavenchess.movement.Move;
import heavenchess.movement.Point;

// to enumerator all valid movements of a given point
public class MoveEnumerator {
    private final Chessboard chessboard;
    private final ChessboardValidator validator;

    public MoveEnumerator(Chessboard chessboard) {
        this.chessboard = chessboard;
        this.validator = chessboard.getValidator();
    }

    public Iterable<Move> allValidMoves(Point point) {
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