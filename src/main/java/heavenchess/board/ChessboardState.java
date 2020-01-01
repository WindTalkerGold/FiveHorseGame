package heavenchess.board;

public enum ChessboardState {
    Empty, LeftOn, RightOn, Invalid;

    public ChessboardState getFlip() {
        if(this == RightOn)
            return LeftOn;
        if(this == LeftOn)
            return RightOn;
        throw new IllegalArgumentException("Can only flip for LeftOn or RightOn!");
    }

    public boolean hasChessman() {
        return this == RightOn || this == LeftOn;
    }
}
