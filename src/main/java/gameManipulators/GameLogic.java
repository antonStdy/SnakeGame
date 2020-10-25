package gameManipulators;

import enums.CollisionResults;
import enums.GameStatus;
import enums.PlayerActions;
import fieldBlocks.FieldBlock;
import gameEntities.GameField;
import gameEntities.Snake;

import java.awt.*;
import java.util.Optional;

public class GameLogic {
    Snake snake;
    GameField field;
    GameStatus gameStatus;

    public GameLogic(Snake snake, GameField field) {
        gameStatus = GameStatus.GAME_STARTED;
        this.snake = snake;
        this.field = field;

    }

    public void handlePlayerAction(PlayerActions action) {
        snake.setDirection(action);
        Point nextSnakeHeadPosition = snake.getNextSnakeHeadPosition();

        Optional<FieldBlock> collisionBlock = field
                .getFieldBlockAt(nextSnakeHeadPosition);

        if (collisionBlock.isPresent()) {
            handleCollision(collisionBlock.get());
        } else {
            snake.move();
        }
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    private void handleCollision(FieldBlock collisionBlock) {
        CollisionResults collisionResult = collisionBlock
                .collideWithSnake();

        switch (collisionResult) {
            case GAME_OVER:
                gameStatus = GameStatus.INITIAL_STATE;
                break;
            case SNAKE_GOT_APPLE:
                snake.feedApple(collisionBlock.getCoordinates());
                field.setNewApple();
                break;
            default:
                break;
        }
    }

}
