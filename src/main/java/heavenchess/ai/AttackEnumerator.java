package heavenchess.ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;
import heavenchess.movement.ChessboardValidator;
import heavenchess.movement.Move;
import heavenchess.movement.Point;

public class AttackEnumerator implements ChessboardEvalutor {
    public int score(ChessboardState side, Chessboard chessboard) {
        ArrayList<Move> counterAttacks = getAllAttackableTargets(side, chessboard);
        return -counterAttacks.size();
    }

    // return all possible points where side.flip() can go to attack side
    // attack means both clamp and flick
    public ArrayList<Move> getAllAttackableTargets(ChessboardState side, Chessboard chessboard) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ArrayList<Move> targets = new ArrayList<>();
        
        run(targets, chessboard, side, cb->cb.getSlotsOfState(side.getFlip()), 
            (p1, p2)->p2.createWithPivot(p1), (p1, p2)->p1.projectionDistance(p2) == 1);
        run(targets, chessboard, side, cb->cb.getSlotsOfState(side), 
            (p1, p2)->p2.middle(p1), (p1, p2)->p1.getLineDistance(p2) == 2);
        stopwatch.stop();
        //System.out.println(stopwatch.elapsed(TimeUnit.MICROSECONDS));
        return targets;
    }

    private void run(ArrayList<Move> resultHolder, Chessboard chessboard, ChessboardState state, PointIterator lister,
            TargetPointSelector targetProvider, PointDistanceChecker distanceChecker) {
        ChessboardValidator validator = chessboard.getValidator();
        HashSet<Point> checkedTargets = new HashSet<>();
        for (Point pointOfThisSide : chessboard.getSlotsOfState(state)) {
            for (Point pointOfOtherSide : lister.apply(chessboard)) {
                if (!distanceChecker.apply(pointOfThisSide, pointOfOtherSide)) {
                    continue;
                }
                Point targetPointToMove = targetProvider.apply(pointOfThisSide, pointOfOtherSide);
                if (chessboard.getSlotState(targetPointToMove) != ChessboardState.Empty) {
                    continue;
                }
                if (checkedTargets.contains(targetPointToMove)) {
                    continue;
                }
                checkedTargets.add(targetPointToMove);
                for (Point pointOnTheOtherSide : chessboard.getSlotsOfState(state.getFlip())) {
                    Move candidateMove = new Move(pointOnTheOtherSide, targetPointToMove);
                    if (validator.isMovementValid(candidateMove, chessboard, state.getFlip())) {
                        resultHolder.add(candidateMove);
                    }
                }
            }
        }
    }
}