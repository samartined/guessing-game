package com.guessgame;

import com.guessgame.gui.GameFrame;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.createAndShowDifficultyMenu();
        });
    }
}
