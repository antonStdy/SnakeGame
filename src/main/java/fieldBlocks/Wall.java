package fieldBlocks;

import enums.CollisionResults;

import java.awt.*;

public class Wall extends FieldBlock {

    public Wall(Point coordinates) {
        super(coordinates);
    }

    @Override
    public void draw(Graphics g, int unitSize) {
        g.setColor(Color.GRAY);
        int x = getCoordinates().x * unitSize;
        int y = getCoordinates().y * unitSize;
        g.fillRect(x, y, unitSize, unitSize);
    }

    public CollisionResults collideWithSnake() {
        return CollisionResults.GAME_OVER;
    }
}
