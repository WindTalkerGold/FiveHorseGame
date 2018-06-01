package heaven.fivehorses.battle;

import java.util.EnumSet;

public class ChessBoard {
    public final int Rows = 7;
    public final int Cols = 5;

    private ChessBoardPosition[][] board;

    // 1 stands for one player, -1 stands for another
    // 0 stands for a position that can be moved to.
    // 2 stands for a position that is invalid
    public ChessBoard() {
        board = new ChessBoardPosition[Rows][Cols];
        init();
    }

    public boolean canMove(Direction direction, int row, int col) {
        return board[row][col].canMove(direction);
    }

    public int getValidMovementCount(int row, int col) {
        return board[row][col].getValidMovementCount();
    }

    public int getPlayerSide(int row, int col) {
        return board[row][col].getPlayer();
    }

    private void init() {
        initChessBoardPositions();
        initChessBoardMovements();
    }

    private void initChessBoardPositions() {
        for(int row=0;row<Cols;row++) {
            board[row][0] = new ChessBoardPosition(1);
            board[row][Cols-1] = new ChessBoardPosition(-1);
            for(int col=1;col<Cols-1;col++) {
                board[row][col] = new ChessBoardPosition(0);;
            }
        }
        board[Rows-2][0] = new ChessBoardPosition(2);
        board[Rows-2][1] = new ChessBoardPosition(0);
        board[Rows-2][2] = new ChessBoardPosition(0);
        board[Rows-2][3] = new ChessBoardPosition(0);
        board[Rows-2][4] = new ChessBoardPosition(2);
        board[Rows-1][0] = new ChessBoardPosition(2);
        board[Rows-1][1] = new ChessBoardPosition(2);
        board[Rows-1][2] = new ChessBoardPosition(0);
        board[Rows-1][3] = new ChessBoardPosition(2);
        board[Rows-1][4] = new ChessBoardPosition(2);
    }

    private void initChessBoardMovements() {
        for(int i=0;i<Cols;i++) {
            for(int j=0;j<Cols;j++) {
                addValidMovements(i, j);
            }
        }

        // for (i, j) where i > 4
        board[5][1].addValidDirection(Direction.Right);
        board[5][1].addValidDirection(Direction.UpRight);
        board[5][1].addValidDirection(Direction.DownRight);
        board[5][2].addValidDirection(Direction.Right);
        board[5][2].addValidDirection(Direction.Up);
        board[5][2].addValidDirection(Direction.Down);
        board[5][2].addValidDirection(Direction.Left);
        board[5][3].addValidDirection(Direction.Left);
        board[5][3].addValidDirection(Direction.UpLeft);
        board[5][3].addValidDirection(Direction.DownLeft);

        board[6][2].addValidDirection(Direction.Up);
        board[5][2].addValidDirection(Direction.UpLeft);
        board[5][2].addValidDirection(Direction.UpRight);
    }

    // for (i, j) where i, j in [0, 4]
    private void addValidMovements(int row, int col) {
        ChessBoardPosition position = board[row][col];
        if (row != 0)
            position.addValidDirection(Direction.Up);
        if (row != 4)
            position.addValidDirection(Direction.Down);
        if (col != 0)
            position.addValidDirection(Direction.Left);
        if (col != 4)
            position.addValidDirection(Direction.Right);

        if (((row + col) & 1) != 0)
            return;

        EnumSet<Direction> validDirections = EnumSet.of(Direction.DownLeft, Direction.DownRight, Direction.UpLeft, Direction.UpRight);
        if (row == 0) {
            validDirections.remove(Direction.UpRight);
            validDirections.remove(Direction.UpLeft);
        }
        if (col == 0) {
            validDirections.remove(Direction.DownLeft);
            validDirections.remove(Direction.UpLeft);
        }
        if (row == 4 && col != 2) {
            validDirections.remove(Direction.DownRight);
            validDirections.remove(Direction.DownLeft);
        }
        if (col == 4) {
            validDirections.remove(Direction.UpRight);
            validDirections.remove(Direction.DownRight);
        }
        for(Direction direction : validDirections) {
            position.addValidDirection(direction);
        }
    }

}
