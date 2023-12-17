package com.guessgame.gui;

import javax.swing.*;

import com.guessgame.logicgame.GuessNumberGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame {
    private JFrame frame;
    private JTextField textField;
    private JLabel resultLabel;
    private GuessNumberGame game;
    private DifficultyMenu difficultyMenu;
    private JLabel livesLabel;

    public void createAndShowDifficultyMenu() {
        difficultyMenu = new DifficultyMenu(this::startGameWithDifficulty);
    }

    public void startGameWithDifficulty() {
        String difficulty = difficultyMenu.getSelectedDifficulty();

        switch (difficulty) {
            case "Fácil":
                game = new GuessNumberGame(8);
                break;
            case "Intermedio":
                game = new GuessNumberGame(5);
                break;
            case "Difícil":
                game = new GuessNumberGame(3);
                break;
        }

        createAndShowGameGUI();
    }

    public void createAndShowGameGUI() {
        frame = new JFrame("Adivina el número");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        JLabel instructionLabel = new JLabel("Adivina el número entre 1 y 100:");
        textField = new JTextField(10);
        JButton guessButton = new JButton("Adivinar");
        resultLabel = new JLabel("");
        livesLabel = new JLabel("Vidas restantes: " + game.getMaxAttempts());

        guessButton.addActionListener(new GuessButtonListener());

        mainPanel.add(instructionLabel);
        mainPanel.add(textField);
        mainPanel.add(guessButton);
        mainPanel.add(resultLabel);
        mainPanel.add(livesLabel);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int userGuess = Integer.parseInt(textField.getText());
                game.incrementAttempts();

                if (game.checkGuess(userGuess)) {
                    resultLabel.setText("¡Correcto! Has adivinado el número en " + game.getAttempts() + " intentos.");
                    textField.setEditable(false);
                } else {
                    resultLabel.setText("Intenta con un número "
                            + (userGuess < game.getNumberToGuess() ? "más grande." : "más pequeño."));
                    livesLabel.setText("Vidas restantes: " + (game.getMaxAttempts() - game.getAttempts()));
                    textField.setText("");
                }

                if (game.isGameOver()) {
                    textField.setEditable(false);
                    showGameOverDialog();
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Por favor, ingresa un número válido.");
            }
        }
    }

    private void showGameOverDialog() {
        Object[] options = { "Volver a jugar", "Salir" };

        int choice = JOptionPane.showOptionDialog(
                frame,
                "Has perdido. El número era " + game.getNumberToGuess(),
                "¡Juego terminado!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            // Volver a la pantalla de selección de dificultad
            difficultyMenu.setVisible(true);
            frame.dispose(); // Cerrar la ventana actual
        } else if (choice == 1) {
            // Salir de la aplicación
            System.exit(0);
        }
    }
}
