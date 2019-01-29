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

    public int blockDistance(Point another) {
        int dx = Math.abs(x-another.getX());
        int dy = Math.abs(y-another.getY());
        return dx+dy;
    }

    public int projectionDistance(Point another) {
        int dx = Math.abs(x-another.getX());
        int dy = Math.abs(y-another.getY());
        return Math.max(dx, dy);
    }
    
    // pivot is mid of this & return
    public Point createWithPivot(Point pivot) {
        int x = 2*pivot.getX()-this.x;
        int y = 2*pivot.getY()-this.y;
        return new Point(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

    private final int x;
    private final int y;
}
