package heavenchess.movement;

public class Move {
    public Move(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    final Point start;
    final Point end;
}
