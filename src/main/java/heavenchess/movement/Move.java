package heavenchess.movement;

public class Move {
    public Move(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    private final Point start;
    private final Point end;
}
