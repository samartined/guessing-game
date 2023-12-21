package com.guessgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.guessgame.logicgame.GuessNumberGame;

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
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añade un borde interno al panel
        mainPanel.setBackground(Color.WHITE); // Establece el color de fondo del panel

        JLabel instructionLabel = new JLabel("Adivina el número entre 1 y 100:");
        textField = new JTextField(10);
        resultLabel = new JLabel("");
        livesLabel = new JLabel("Vidas restantes: " + game.getMaxAttempts());
        mainPanel.add(instructionLabel, BorderLayout.NORTH);
        mainPanel.add(resultLabel, BorderLayout.CENTER);
        mainPanel.add(livesLabel, BorderLayout.SOUTH);

        // Fuentes y estilos
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        instructionLabel.setFont(labelFont);
        resultLabel.setFont(labelFont);
        livesLabel.setFont(labelFont);

        JPanel inputPanel = new JPanel();

        JButton guessButton = new JButton("Adivinar");
        guessButton.addActionListener(new GuessButtonListener()); // Evento del usuario para comprobar eñl número
                                                                  // introducido
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(textField);
        inputPanel.add(guessButton);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        frame.setSize(400, 250); // Aumentamos el tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
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
