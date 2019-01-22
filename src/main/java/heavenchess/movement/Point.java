package heavenchess.movement;

public class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return (this.x << 2) ^ this.y;
    }

    @Override
    public boolean equals(Object another) {
        if(another == null)
            return false;
        if(another == this)
            return true;

        if(another instanceof Point) {
            Point p = (Point)another;
            return p.x == this.x && p.y == this.y;
        }

        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private final int x;
    private final int y;
}
