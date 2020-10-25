package enums;

import java.awt.*;

public enum PlayerActions {
    MOVE_RIGHT(new Point(1, 0)),
    MOVE_LEFT(new Point(-1, 0)),
    MOVE_UP(new Point(0, -1)),
    MOVE_DOWN(new Point(0, 1)),
    START_GAME(new Point(0, 0));

    private Point movementDelta;

    PlayerActions(Point movementDelta) {
        this.movementDelta = movementDelta;
    }

    public Point getMovementDelta() {
        return this.movementDelta;
    }
}
