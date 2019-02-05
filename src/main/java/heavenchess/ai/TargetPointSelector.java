package heavenchess.ai;

import heavenchess.movement.Point;

@FunctionalInterface
interface TargetPointSelector {
    Point apply(Point p1, Point p2);
}