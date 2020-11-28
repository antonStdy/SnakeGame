package gameManipulators;

import enums.PlayerActions;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerKeyAdapter extends KeyAdapter {
    private PlayerActions currentAction;
    private boolean isGameStarted;

    public PlayerKeyAdapter(PlayerActions initialAction, boolean isGameStarted) {
        this.currentAction = initialAction;
        this.isGameStarted = isGameStarted;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                handleMovementAction(PlayerActions.MOVE_UP);
                break;
            case KeyEvent.VK_A:
                handleMovementAction(PlayerActions.MOVE_LEFT);
                break;
            case KeyEvent.VK_S:
                handleMovementAction(PlayerActions.MOVE_DOWN);
                break;
            case KeyEvent.VK_D:
                handleMovementAction(PlayerActions.MOVE_RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                if (!isGameStarted) {
                    currentAction = PlayerActions.START_GAME;
                }
                break;
            default:
                break;
        }
    }

    public PlayerActions getCurrentAction() {
        return this.currentAction;
    }

    public void setGameStarted(boolean gameStarted) {
        isGameStarted = gameStarted;
    }

    private void handleMovementAction(PlayerActions nextAction) {
        if (!hasMovementException(nextAction)) {
            this.currentAction = nextAction;
        }
    }


    private boolean hasMovementException(PlayerActions nextAction) {
        return isLeftRightException(nextAction)
                || isUpDownException(nextAction);
    }

    private boolean isLeftRightException(PlayerActions nextAction) {
        if (nextAction == PlayerActions.MOVE_RIGHT
                || nextAction == PlayerActions.MOVE_LEFT) {

            return currentAction == PlayerActions.MOVE_RIGHT
                    || currentAction == PlayerActions.MOVE_LEFT;
        }
        return false;
    }

    private boolean isUpDownException(PlayerActions nextAction) {
        if (nextAction == PlayerActions.MOVE_UP
                || nextAction == PlayerActions.MOVE_DOWN) {

            return currentAction == PlayerActions.MOVE_UP
                    || currentAction == PlayerActions.MOVE_DOWN;
        }
        return false;
    }
}
