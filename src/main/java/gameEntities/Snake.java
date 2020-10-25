package gameEntities;

import enums.PlayerActions;
import fieldBlocks.SnakeBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeBlock> snakeBlocks = new ArrayList<>();
    private PlayerActions currentDirection;

    public Snake(
            int snakeSize,
            int headSpawnX,
            int headSpawnY,
            PlayerActions initialDirection) {
        if (isSnakeOutOfBorders(snakeSize, headSpawnX)) {
            throw new Error("Snake is spawned out of borders!");
        }

        this.currentDirection = initialDirection;

        for (int xCoordinate = headSpawnX;
             xCoordinate > headSpawnX - snakeSize;
             xCoordinate--) {
            Point snakeBlockCoordinates = new Point(xCoordinate, headSpawnY);
            snakeBlocks.add(
                    new SnakeBlock(snakeBlockCoordinates)
            );
        }
    }

    public void move() {
        SnakeBlock snakeHead = snakeBlocks.get(0);

        for (int blockIndex = snakeBlocks.size() - 1; blockIndex >= 1; blockIndex--) {
            SnakeBlock leadingBlock = snakeBlocks.get(blockIndex - 1);
            SnakeBlock followingBlock = snakeBlocks.get(blockIndex);

            followingBlock.moveBehind(leadingBlock);
        }

        moveSnakeHead(snakeHead);
    }

    public void feedApple(Point appleCoordinates) {
        SnakeBlock newSnakeHead = new SnakeBlock(appleCoordinates);
        snakeBlocks.add(0, newSnakeHead);
    }

    public void setDirection(PlayerActions playerAction) {
        this.currentDirection = playerAction;
    }

    public Point getNextSnakeHeadPosition() {
        Point movementDelta = currentDirection.getMovementDelta();
        Point nextPosition = new Point(snakeBlocks.get(0).getCoordinates());
        nextPosition.translate(movementDelta.x, movementDelta.y);

        return nextPosition;
    }

    public List<SnakeBlock> getSnakeBlocks() {
        return snakeBlocks;
    }

    private void moveSnakeHead(SnakeBlock snakeHead) {
        Point nextPosition = getNextSnakeHeadPosition();
        snakeHead.updateCoordinates(nextPosition);
    }

    private boolean isSnakeOutOfBorders(int snakeSize, int headX) {
        return headX - snakeSize < 1;
    }
}
