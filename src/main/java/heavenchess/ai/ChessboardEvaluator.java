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

public class ChessboardEvaluator {
    // return all possible points where side.flip() can go to attack side
    // attack means both clamp and flick
    public Iterable<Move> isAttackable(ChessboardState side, Chessboard chessboard) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ChessboardValidator validator = chessboard.getValidator();
        
        ArrayList<Move> targets = new ArrayList<>();
        HashSet<Point> checkedTargets = new HashSet<>();
        // is clampable
        for(Point pointOfThisSide : chessboard.getSlotsOfState(side)) {
            for(Point pointOfOtherSide : chessboard.getSlotsOfState(side.getFlip())) {
                if(pointOfOtherSide.projectionDistance(pointOfThisSide) != 1) {
                    continue;
                }
                Point targetPointToMove = pointOfOtherSide.createWithPivot(pointOfThisSide);
                if(chessboard.getSlotState(targetPointToMove) != ChessboardState.Empty) {
                    continue;
                }
                if(checkedTargets.contains(targetPointToMove)) {
                    continue;
                }
                checkedTargets.add(targetPointToMove);
                for(Point pointOnTheOtherSide : chessboard.getSlotsOfState(side.getFlip())) {
                    Move candidateMove = new Move(pointOnTheOtherSide, targetPointToMove);
                    if(validator.isMovementValid(candidateMove, chessboard, side.getFlip())) {
                        targets.add(candidateMove);
                    }
                }
            }
        }

        checkedTargets.clear();
        // is flickable
        for(Point pointOfThisSide : chessboard.getSlotsOfState(side)) {
            for(Point pointOfThisSide2 : chessboard.getSlotsOfState(side)) {
                if(pointOfThisSide.getLineDistance(pointOfThisSide2) != 2) {
                    continue;
                }
                Point targetPointToMove = pointOfThisSide.middle(pointOfThisSide2);
                if(chessboard.getSlotState(targetPointToMove) != ChessboardState.Empty) {
                    continue;
                }
                if(checkedTargets.contains(targetPointToMove)) {
                    continue;
                }
                checkedTargets.add(targetPointToMove);
                for(Point pointOnTheOtherSide : chessboard.getSlotsOfState(side.getFlip())) {
                    Move candidateMove = new Move(pointOnTheOtherSide, targetPointToMove);
                    if(validator.isMovementValid(candidateMove, chessboard, side.getFlip())) {
                        targets.add(candidateMove);
                    }
                }
            }
        }
        stopwatch.stop();
        //System.out.println(stopwatch.elapsed(TimeUnit.MICROSECONDS));
        return targets;
    }
}