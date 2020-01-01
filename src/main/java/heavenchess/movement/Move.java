package heavenchess.movement;

public final class Move {
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

    @Override
    public String toString() {
        return start.toString() + "->" + end.toString();
    }

    final Point start;
    final Point end;
}
