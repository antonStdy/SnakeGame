package gameEntities;


import fieldBlocks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class GameField {
    private int fieldWidth;
    private int fieldHeight;
    private List<Wall> walls;
    private Snake snake;
    private Apple apple;

    public GameField(int fieldWidth, int fieldHeight, Snake snake) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.snake = snake;
        generateInitialField();
    }

    public List<FieldBlock> getAllFieldBlocks() {
        List<FieldBlock> fieldBlocks = new ArrayList<>();
        fieldBlocks.addAll(
                snake.getSnakeBlocks()
        );
        fieldBlocks.addAll(walls);
        fieldBlocks.add(apple);

        return fieldBlocks;
    }

    public Optional<FieldBlock> getFieldBlockAt(Point coordinates) {
        List<FieldBlock> fieldBlocksAt = getAllFieldBlocks()
                .stream()
                .filter(fieldBlock -> fieldBlock.at(coordinates))
                .collect(Collectors.toList());

        if (fieldBlocksAt.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(fieldBlocksAt.get(0));
    }

    public void generateInitialField() {
        walls = createFieldBoundaries();
        setNewApple();
    }

    public void setNewApple() {
        Apple newApple;
        int fieldWidthWithoutWall = fieldWidth - 3;
        int fieldHeightWithoutWall = fieldHeight - 3;
        int distanceFromWall = 2;

        do {
            int x = getRandomIntWithin(fieldWidthWithoutWall, distanceFromWall);
            int y = getRandomIntWithin(fieldHeightWithoutWall, distanceFromWall);

            Point coordinates = new Point(x, y);
            newApple = new Apple(coordinates);
        } while (isBlockInSnake(newApple));

        this.apple = newApple;
    }

    private int getRandomIntWithin(int maxValue, int minValue) {
        Random random = new Random();
        return random
                .nextInt(maxValue -  minValue)
                + minValue;
    }

    private boolean isBlockInSnake(FieldBlock block) {
        List<SnakeBlock> snakeBlocks = snake.getSnakeBlocks();

        for (SnakeBlock snakeBlock : snakeBlocks) {
            if (block.at(snakeBlock.getCoordinates())) {
                return true;
            }
        }

        return false;
    }

    private List<Wall> createFieldBoundaries() {
        List<Wall> fieldBoundaries = new ArrayList<>();

        int leftBoundaryX = 0;
        int rightBoundaryX = fieldWidth - 1;
        int topBoundaryY = 0;
        int bottomBoundaryY = fieldHeight - 1;

        for (int x = 0; x < fieldWidth; x++) {
            Wall topWallBlock = new Wall(
                    new Point(x, topBoundaryY)
            );
            Wall bottomWallBlock = new Wall(
                    new Point(x, bottomBoundaryY)
            );
            fieldBoundaries.add(topWallBlock);
            fieldBoundaries.add(bottomWallBlock);
        }

        for (int y = 1; y < fieldWidth - 1; y++) {
            Wall leftWallBlock = new Wall(
                    new Point(leftBoundaryX, y)
            );
            Wall rightWallBlock = new Wall(
                    new Point(rightBoundaryX, y)
            );
            fieldBoundaries.add(leftWallBlock);
            fieldBoundaries.add(rightWallBlock);
        }
        return fieldBoundaries;
    }
}
