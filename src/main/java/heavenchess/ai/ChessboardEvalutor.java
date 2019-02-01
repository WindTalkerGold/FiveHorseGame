package heavenchess.ai;

import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

public interface ChessboardEvalutor {
    public int score(ChessboardState side, Chessboard chessboard);
}