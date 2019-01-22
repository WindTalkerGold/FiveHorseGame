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
}
