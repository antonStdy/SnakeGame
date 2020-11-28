import enums.PlayerActions;
import fieldBlocks.SnakeBlock;
import gameEntities.Snake;

import java.awt.*;
import java.util.List;

public class SnakeTest {

    public static int SNAKE_SIZE = 3;
    public static int HEAD_SPAWN_X = 4;
    public static int HEAD_SPAWN_Y = 5;
    public static PlayerActions INIT_DIRECTION =
            PlayerActions.MOVE_RIGHT;

    Snake snake;

    public void testSnakeMove() {
       beforeEach();
       snake.move();

        List<SnakeBlock> blocks = snake.getSnakeBlocks();

        for (int i = blocks.size() - 1; i >= 0; i--) {
            int blockX = blocks.get(i).getCoordinates().x;
            int blockY = blocks.get(i).getCoordinates().y;

            if (blockX != HEAD_SPAWN_X + 1 - i) {
                printTestFailed("testSnakeMove",
                        "snake block " + i + ": "
                        + "invalid position x");
            }
            if (blockY != HEAD_SPAWN_Y) {
                printTestFailed("testSnakeMove",
                        "snake block " + i + ": "
                        + "invalid position y");
            }
        }

        printTestPassed("testSnakeMove");
    }

    public void testFeedApple() {
        beforeEach();
        Point appleCoordinates =
                new Point(HEAD_SPAWN_X + 1, HEAD_SPAWN_Y);
        snake.feedApple(appleCoordinates);

        Point snakeHeadCoords = snake
                .getSnakeBlocks().get(0).getCoordinates();

        if (!appleCoordinates.equals(snakeHeadCoords)) {
            printTestFailed("testFeedApple",
                    "snake didn't eat apple");
        }
        printTestPassed("testFeedApple");
    }

    public void testGetNextSnakeHeadPosition() {
        beforeEach();

        Point nextHeadPosition = snake.getNextSnakeHeadPosition();
        Point expected = new Point(HEAD_SPAWN_X + 1, HEAD_SPAWN_Y);

        if (!nextHeadPosition.equals(expected)) {
            printTestFailed("testGetNextSnakeHeadPosition",
                    "invalid nextSnakeHead position");
        }

        printTestPassed("testGetNextSnakeHeadPosition");
    }

    private void beforeEach() {
        snake = new Snake(
                SNAKE_SIZE,
                HEAD_SPAWN_X,
                HEAD_SPAWN_Y,
                INIT_DIRECTION
        );
    }

    private void printTestFailed(String testName, String message) {
        System.out.println("Test " + testName + " failed: " + message);
    }

    private void printTestPassed(String testName) {
        System.out.println(testName + " PASSED");
    }

    public static void main(String[] args) {
        SnakeTest st = new SnakeTest();

        st.testSnakeMove();
        st.testFeedApple();
        st.testGetNextSnakeHeadPosition();
    }
}
