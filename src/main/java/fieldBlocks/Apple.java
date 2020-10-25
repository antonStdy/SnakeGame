package fieldBlocks;

import enums.CollisionResults;

import java.awt.*;

public class Apple extends FieldBlock {
    public Apple(Point coordinates) {
        super(coordinates);
    }

    @Override
    public void draw(Graphics g, int unitSize) {
        g.setColor(Color.RED);
        int x = getCoordinates().x * unitSize;
        int y = getCoordinates().y * unitSize;
        g.fillOval(x, y, unitSize, unitSize);
    }

    public CollisionResults collideWithSnake() {
        return CollisionResults.SNAKE_GOT_APPLE;
    }
}
