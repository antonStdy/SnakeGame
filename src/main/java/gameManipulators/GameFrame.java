package gameManipulators;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(JPanel jPanel) {
        this.add(jPanel);
        this.setTitle("Snake game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
