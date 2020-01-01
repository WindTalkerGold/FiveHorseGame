package heavenchess.board;

public enum ChessboardState {
    Empty(0), LeftOn(1), RightOn(2), Invalid(3);

    private final int status;

    ChessboardState(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public ChessboardState getFlip() {
        if(this == RightOn)
            return LeftOn;
        if(this == LeftOn)
            return RightOn;
        throw new IllegalArgumentException("Can only flip for LeftOn or RightOn!");
    }

    public boolean hasChessman() {
        return this == LeftOn || this == RightOn;
    }

    public static int getStatusCodesCount() {
        return 4;
    }
}
