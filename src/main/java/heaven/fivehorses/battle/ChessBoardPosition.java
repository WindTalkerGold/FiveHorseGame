package heaven.fivehorses.battle;

import java.util.EnumSet;

class ChessBoardPosition {
    private int occupation;
    // 1 and -1 for two players, 0 for not occupied, 2 for invalid
    private EnumSet<Direction> validMoveDirections;

    public ChessBoardPosition(int initState) {
        occupation = initState;
        validMoveDirections = EnumSet.noneOf(Direction.class);
    }

    public boolean isOccupied() {
        return occupation == 1 || occupation == -1;
    }

    public boolean isValid() {
        return occupation != 2;
    }

    public int getPlayer() {
        return occupation;
    }

    public int getValidMovementCount() {
        return validMoveDirections.size();
    }

    public boolean canMove(Direction direction) {
        return validMoveDirections.contains(direction);
    }

    void addValidDirection(Direction direction) {
        validMoveDirections.add(direction);
    }
}
