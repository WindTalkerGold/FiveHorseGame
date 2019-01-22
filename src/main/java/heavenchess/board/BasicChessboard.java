package heavenchess.board;

import heavenchess.movement.BasicModeValidator;
import heavenchess.movement.ChessboardValidator;
import heavenchess.movement.Move;
import heavenchess.movement.Point;

import java.util.ArrayList;
import com.google.common.collect.Iterables;

public class BasicChessboard implements Chessboard {
    public static final int ChessboardWidth = 5;
    public static final int ChessboardHeight = 5;
    private ChessboardState[][] chessboard = new ChessboardState[ChessboardHeight][ChessboardWidth];
    private ArrayList<ArrayList<Point>> pointsOfState = new ArrayList<>();

    public BasicChessboard() {
        for(int i=0;i<4;i++) {
            pointsOfState.add(new ArrayList<>());
        }

        for(int i=0;i<ChessboardHeight;i++) {
            for(int j=0;j<ChessboardWidth;j++) {
                ChessboardState state;
                if(j == 0) {
                    state = ChessboardState.LeftOn;
                } else if(j == ChessboardWidth-1) {
                    state = ChessboardState.RightOn;
                } else {
                    state = ChessboardState.Empty;
                }
                chessboard[i][j] = state;
                Point point = new Point(i, j);
                pointsOfState.get(state.ordinal()).add(point);
            }
        }
    }

    @Override
    public int countSlotsOfState(ChessboardState state) {
        int count = 0;
        for (Point p:getSlotsOfState(state)) {
            count++;
        }
        return count;
    }

    @Override
    public Iterable<Point> getSlotsOfState(ChessboardState state) {
        return pointsOfState.get(state.ordinal());
    }

    @Override
    public ChessboardState getSlotState(Point point) {
        if(point == null || point.getX() < 0 || point.getX() >= ChessboardHeight
                || point.getY() < 0 || point.getY() >= ChessboardWidth)
            return ChessboardState.Invalid;

        return chessboard[point.getX()][point.getY()];
    }

    @Override
    public Iterable<ChessboardState> getSlotsState(Iterable<Point> points) {
        return Iterables.transform(points, p->getSlotState(p));
    }

    @Override
    public ChessboardState[][] getChessboard() {
        return chessboard;
    }

    private ChessboardValidator validator = null;
    @Override
    public ChessboardValidator getValidator() {
        if(validator == null) {
            validator = new BasicModeValidator();
        }
        return validator;
    }

    @Override
    public boolean move(Move move, ChessboardState moveFor) {
        if(!getValidator().isMovementValid(move, this, moveFor)) {
            return false;
        }

        Point start = move.getStart();
        Point end = move.getEnd();

        ChessboardState originalStartState = getSlotState(start);
        ChessboardState originalEndState = getSlotState(end);
        chessboard[start.getX()][start.getY()] = originalEndState;
        chessboard[end.getX()][end.getY()] = originalStartState;

        pointsOfState.get(originalEndState.ordinal()).remove(end);
        pointsOfState.get(originalEndState.ordinal()).add(start);
        pointsOfState.get(originalStartState.ordinal()).remove(start);
        pointsOfState.get(originalStartState.ordinal()).add(end);

        // todo: flip chessman if any
        return true;
    }
}