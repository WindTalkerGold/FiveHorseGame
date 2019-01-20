package heavenchess.board;

import heavenchess.movement.Point;

import java.util.Iterator;

public interface Chessboard {
    int countSlotsOfState(ChessboardState stat);
    Iterator<Point> getSlotsOfState(ChessboardState stat);
    ChessboardState getSlotState(Point point);
    Iterator<ChessboardState> getSlotsState(Iterator<Point> points);

    ChessboardState[][] getChessboard();
}
