package heavenchess.board;

import com.google.common.base.Preconditions;
import heavenchess.movement.BasicModeValidator;
import heavenchess.movement.ChessboardValidator;
import heavenchess.movement.Move;
import heavenchess.movement.Point;

import java.util.ArrayList;

import com.google.common.collect.Iterables;

public class BasicChessboard implements Chessboard {
    public static final int ChessboardWidth = 5;
    public static final int ChessboardHeight = 5;
    private final ChessboardState[][] chessboard = new ChessboardState[ChessboardHeight][ChessboardWidth];
    private final ArrayList<ArrayList<Point>> pointsOfState = new ArrayList<>();

    public BasicChessboard() {
        for(int i=0;i<ChessboardState.getStatusCodesCount();i++) {
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
                pointsOfState.get(state.getStatus()).add(point);
            }
        }
    }
    
    public BasicChessboard(ChessboardState[][] chessboard) {
        Preconditions.checkArgument(chessboard.length == ChessboardHeight,
                "must have "+ChessboardHeight+" rows");
        for(int i=0;i<4;i++) {
            pointsOfState.add(new ArrayList<>());
        }
        for(int r=0;r<chessboard.length;r++) {
            Preconditions.checkArgument(chessboard[r].length == ChessboardWidth,
                    "each row must have "+ChessboardWidth+" columns");

            for(int c=0;c<ChessboardWidth;c++) {
                ChessboardState state = chessboard[r][c];
                this.chessboard[r][c] = state;
                if(state.hasChessman()) {
                    this.pointsOfState.get(state.getStatus()).add(new Point(r, c));
                }
            }
        }
    }

    @Override
    public int countSlotsOfState(ChessboardState state) {
        return pointsOfState.get(state.getStatus()).size();
    }

    @Override
    public Iterable<Point> getSlotsOfState(ChessboardState state) {
        return pointsOfState.get(state.getStatus());
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
    public boolean flip(Point point) {
        ChessboardState originalState = getSlotState(point);
        if(originalState.hasChessman()) {
            set(point, originalState.getFlip());
        }
        return false;
    }

    @Override
    public boolean set(Point point, ChessboardState state) {
        ChessboardState originalState = getSlotState(point);
        if(originalState == ChessboardState.Invalid || originalState == state) {
            return false;
        }
        chessboard[point.getX()][point.getY()] = state;
        pointsOfState.get(state.ordinal()).add(point);
        pointsOfState.get(originalState.ordinal()).remove(point);
        return true;
    }

    @Override
    public boolean move(Move move, ChessboardState moveFor) {
        if(!getValidator().isMovementValid(move, this, moveFor)) {
            return false;
        }

        Point start = move.getStart();
        Point end = move.getEnd();

        // assume the status is already checked by validator, so skip the validation here
        ChessboardState originalEndState = getSlotState(end);     // should be empty
        set(start, originalEndState);
        set(end, moveFor);
        return true;
    }

    @Override
    public Chessboard move2(Move move, ChessboardState moveFor) {
        if(!getValidator().isMovementValid(move, this, moveFor)) {
            return null;
        }

        BasicChessboard another = new BasicChessboard(this.chessboard);
        another.move(move, moveFor);
        return another;
    }

    @Override
    public Iterable<Point> getAdjacentCounterparts(Point point) {
        Preconditions.checkState(getSlotState(point).hasChessman(),
                "The point has not chessman on it!");

        ChessboardState reversed = getSlotState(point).getFlip();
        Iterable<Point> counterChessman = getSlotsOfState(reversed);
        boolean checkDiagonal = (point.getX()+point.getY()) % 2 == 0;
        if(checkDiagonal) {
            return Iterables.filter(counterChessman, p->p.projectionDistance(point)==1);
        } else {
            return Iterables.filter(counterChessman, p->p.blockDistance(point) == 1);
        }
    }
}
