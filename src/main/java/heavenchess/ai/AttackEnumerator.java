package heavenchess.ai;

import java.util.ArrayList;
import java.util.HashSet;

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
        ArrayList<Move> targets = new ArrayList<>();

        // run clamp test
        // 1) iterate all points of state as p1 
        // 2) iterate all points of ~state as p2
        // 3) check if any x and y are on the same ling && |p1, p2| == 1
        // 4) check if any point of ~state can move to p2.pivotWith(p1)
        run(targets, chessboard, side, cb -> cb.getSlotsOfState(side.getFlip()), 
                (p1, p2) -> p1.projectionDistance(p2) == 1,
                (p1, p2) -> p2.createWithPivot(p1));
        
        // run flick test
        // 1) iterate all points of state as p1
        // 2) iterate all points of state as p2
        // 3) check if any x and y are on the same ling && |p1, p2| == 2
        // 4) check if any point of ~state can move to p1.mid(p2)
        run(targets, chessboard, side, cb -> cb.getSlotsOfState(side), 
                (p1, p2) -> p1.getLineDistance(p2) == 2, 
                (p1, p2) -> p2.middle(p1));
        return targets;
    }

    private void run(ArrayList<Move> resultHolder, Chessboard chessboard, ChessboardState state, PointsIterator lister,
                     PointDistanceChecker distanceChecker, TargetPointSelector targetProvider) {
        ChessboardValidator validator = chessboard.getValidator();
        HashSet<Point> checkedTargets = new HashSet<>();
        for (Point pointOfThisSide : chessboard.getSlotsOfState(state)) {
            for (Point anotherPoint : lister.apply(chessboard)) {
                if (!distanceChecker.apply(pointOfThisSide, anotherPoint)) {
                    continue;
                }
                Point targetPointToMove = targetProvider.apply(pointOfThisSide, anotherPoint);
                if (chessboard.getSlotState(targetPointToMove) != ChessboardState.Empty) {
                    continue;
                }
                if (checkedTargets.contains(targetPointToMove)) {
                    continue;
                }
                checkedTargets.add(targetPointToMove);
                // if some point of flipped can go to target, then record it
                ChessboardState flippedSide = state.getFlip();
                for (Point pointOnTheOtherSide : chessboard.getSlotsOfState(flippedSide)) {
                    Move candidateMove = new Move(pointOnTheOtherSide, targetPointToMove);
                    if (validator.isMovementValid(candidateMove, chessboard, flippedSide)) {
                        resultHolder.add(candidateMove);
                    }
                }
            }
        }
    }
}