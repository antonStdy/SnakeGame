package fieldBlocks;

import enums.CollisionResults;

import java.awt.*;

public class SnakeBlock extends FieldBlock {
    public SnakeBlock(Point coordinates) {
        super(coordinates);
    }

    @Override
    public CollisionResults collideWithSnake() {
        return CollisionResults.GAME_OVER;
    }

    @Override
    public void draw(Graphics g, int unitSize) {
        g.setColor(Color.GREEN);
        int x = getCoordinates().x * unitSize;
        int y = getCoordinates().y * unitSize;
        g.fillRect(x, y, unitSize, unitSize);
    }

    public void moveBehind(SnakeBlock snakeBlock) {

        this.updateCoordinates(snakeBlock.getCoordinates());
    }
}
