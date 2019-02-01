package heavenchess.board;

public class Compresser {
    public String toString(Chessboard chessboard) {
        StringBuilder sb = new StringBuilder();
        ChessboardState[][] board = chessboard.getChessboard();
        for(ChessboardState[] row : board) {
            for(ChessboardState col : row) {
                sb.append(col.ordinal());
            }
        }
        return sb.toString();
    }

    public Chessboard fromString(String string) {
        int rows = BasicChessboard.ChessboardHeight;
        int cols = BasicChessboard.ChessboardWidth;
        if(string.length() != rows*cols) {
            throw new IllegalArgumentException("string length error!");
        }
        ChessboardState[][] board = new ChessboardState[rows][cols];
        for(int r=0;r<rows;r++) {
            for(int c=0;c<cols;c++) {
                char ch = string.charAt(r*c+c);
                int n = (int)(ch-'0');
                board[r][c] = ChessboardState.values()[n];
            }
        }
        return new BasicChessboard(board);
    }
}