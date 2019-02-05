package heavenchess.ai;

import heavenchess.movement.Point;

@FunctionalInterface
interface PointDistanceChecker {
    boolean apply(Point p1, Point p2);
}