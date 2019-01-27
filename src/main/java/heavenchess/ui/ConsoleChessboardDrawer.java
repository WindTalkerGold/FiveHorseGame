package heavenchess.ui;

import heavenchess.board.Chessboard;
import heavenchess.board.ChessboardState;

public class ConsoleChessboardDrawer {
    public void Draw(Chessboard chessboard) {
        StringBuilder sb = new StringBuilder();
        for(ChessboardState[] row : chessboard.getChessboard()) {
            sb.setLength(0);
            for(ChessboardState col : row) {
                sb.append(col.ordinal());
                sb.append(' ');
            }
            System.out.println(sb.toString());
        }
    }
}