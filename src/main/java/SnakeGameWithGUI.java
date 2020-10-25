import enums.GameStatus;
import enums.PlayerActions;
import gameEntities.GameField;
import gameEntities.Snake;
import gameManipulators.GameDrawer;
import gameManipulators.GameFrame;
import gameManipulators.GameLogic;
import gameManipulators.PlayerKeyAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGameWithGUI extends JPanel implements ActionListener, Runnable {
    public static final int SQUARE_SCREEN_SIDE = 600;
    public static final int AMOUNT_OF_UNITS = 20 * 20;
    public static final int UNIT_SIZE = SQUARE_SCREEN_SIDE / ((int) Math.sqrt(AMOUNT_OF_UNITS));
    public static final int SNAKE_SIZE = 5;
    public static final int SNAKE_HEAD_X = SNAKE_SIZE + 1;
    public static final int SNAKE_HEAD_y = 2;
    public static final int DELAY = 75;
    public static final PlayerActions INITIAL_ACTION =
            PlayerActions.MOVE_RIGHT;

    private Timer timer;
    private GameDrawer gameDrawer;
    private GameLogic gameLogic;
    private GameField gameField;
    private boolean isGameStarted = false;
    private PlayerKeyAdapter keyAdapter = new PlayerKeyAdapter(
            INITIAL_ACTION,
            false
    );

    public static void main(String[] args) {
        GameDrawer gameDrawer = new GameDrawer();
        SnakeGameWithGUI snakeGameWithGUI = new SnakeGameWithGUI(gameDrawer);
        new GameFrame(snakeGameWithGUI);

        snakeGameWithGUI.run();
    }

    public SnakeGameWithGUI(GameDrawer gameDrawer) {
        this.gameDrawer = gameDrawer;
        this.setPreferredSize(new Dimension(SQUARE_SCREEN_SIDE, SQUARE_SCREEN_SIDE));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        setInitialGameState();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerActions performedAction = keyAdapter.getCurrentAction();
        if (isGameStarted) {
            handleRunningGameState(performedAction);
        } else {
            handleStoppedGameState(performedAction);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameDrawer.drawFieldObjects(g, UNIT_SIZE, gameField.getAllFieldBlocks());
    }

    @Override
    public void run() {
        this.timer = new Timer(DELAY, this);
        timer.start();
        this.startGame();
    }

    public void startGame() {
        setInitialGameState();
        isGameStarted = true;

        if (keyAdapter != null) {
            this.removeKeyListener(keyAdapter);
        }
        keyAdapter = new PlayerKeyAdapter(INITIAL_ACTION, true);
        this.addKeyListener(keyAdapter);
    }

    public void stopGame() {
        isGameStarted = false;
        keyAdapter.setGameStarted(false);
    }

    public void setInitialGameState() {
        Snake snake = new Snake(
                SNAKE_SIZE,
                SNAKE_HEAD_X,
                SNAKE_HEAD_y,
                INITIAL_ACTION);
        GameField gameField = new GameField(
                SQUARE_SCREEN_SIDE / UNIT_SIZE,
                SQUARE_SCREEN_SIDE / UNIT_SIZE,
                snake
        );

        this.gameField = gameField;
        this.gameLogic = new GameLogic(snake, gameField);
    }

    private void handleRunningGameState(PlayerActions playerAction) {
        gameLogic.handlePlayerAction(playerAction);
        this.repaint();

        if (gameLogic.getGameStatus() == GameStatus.INITIAL_STATE) {
            this.stopGame();
        }
    }

    private void handleStoppedGameState(PlayerActions playerAction) {
        if (playerAction == PlayerActions.START_GAME) {
            this.startGame();
        }
    }
}
