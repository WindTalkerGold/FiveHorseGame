package heavenchess.board;

import heavenchess.movement.*;

public interface Chessboard {
    int countSlotsOfState(ChessboardState stat);
    Iterable<Point> getSlotsOfState(ChessboardState stat);
    ChessboardState getSlotState(Point point);
    Iterable<ChessboardState> getSlotsState(Iterable<Point> points);

    ChessboardState[][] getChessboard();
    ChessboardValidator getValidator();
    boolean move(Move move, ChessboardState moveFor);
    // rename this method. This move will not change the original chessboard, but generate a new one
    Chessboard move2(Move move, ChessboardState moveFor);
    boolean flip(Point point);
    boolean set(Point point, ChessboardState state);
    Iterable<Point> getAdjacentCounterparts(Point point);
}
