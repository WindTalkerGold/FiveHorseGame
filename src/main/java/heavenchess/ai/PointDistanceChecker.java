package heavenchess.ai;

import heavenchess.movement.Point;

@FunctionalInterface
public interface PointDistanceChecker {
    public boolean apply(Point p1, Point p2);
}