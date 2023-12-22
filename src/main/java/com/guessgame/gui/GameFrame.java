package com.guessgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.guessgame.logicgame.GuessNumberGame;

/**
 * La clase GameFrame representa la ventana principal del juego de adivinar el
 * número.
 * Contiene métodos para crear y mostrar el menú de dificultad, iniciar el juego
 * con una dificultad seleccionada,
 * crear y mostrar la interfaz gráfica del juego, y manejar los eventos del
 * botón de adivinar y el diálogo de fin de juego.
 */
public class GameFrame {
    private JFrame frame;
    private JTextField textField;
    private JLabel resultLabel;
    private GuessNumberGame game;
    private DifficultyMenu difficultyMenu;
    private JLabel livesLabel;

    /**
     * Crea y muestra el menú de dificultad.
     */
    public void createAndShowDifficultyMenu() {
        difficultyMenu = new DifficultyMenu(this::startGameWithDifficulty);
    }

    /**
     * Inicia el juego con la dificultad seleccionada por el usuario.
     */
    public void startGameWithDifficulty() {
        String selectedDifficulty = difficultyMenu.getSelectedDifficulty();

        switch (selectedDifficulty) {
            case "Fácil (8 vidas)":
                game = new GuessNumberGame(8, selectedDifficulty);
                break;
            case "Intermedio (5 vidas)":
                game = new GuessNumberGame(5, selectedDifficulty);
                break;
            case "Difícil (3 vidas)":
                game = new GuessNumberGame(3, selectedDifficulty);
                break;
        }

        // Inicia el juego
        createAndShowGameGUI();
    }

    /**
     * Esta función se encarga de crear y configurar los componentes de la interfaz
     * gráfica,
     * como el marco principal, los paneles, las etiquetas, los botones y los campos
     * de texto.
     * También establece los estilos y colores de los elementos de la interfaz.
     * Finalmente, muestra la ventana en la pantalla.
     */

    public void createAndShowGameGUI() {

        // Personaliza los colores
        Color backgroundColor = new Color(255, 231, 186); // Amarillo claro
        Color textColor = new Color(34, 49, 63); // Azul oscuro
        Color buttonColor = new Color(242, 120, 75); // Naranja claro

        // Crea y configura el marco principal de la ventana
        frame = new JFrame("Adivina el número");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setBackground(backgroundColor); // Establece el color de fondo del marco como amarillo
                                                               // claro

        // Etiqueta de instrucción
        JLabel instructionLabel = new JLabel("Adivina el número entre 1 y 100.");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente
        instructionLabel.setForeground(textColor); // Establece el color del texto como azul oscuro

        // Etiqueta de vidas restantes
        livesLabel = new JLabel("Vidas restantes: " + game.getMaxAttempts()); // Etiqueta para mostrar las vidas
        livesLabel.setForeground(textColor); // Establece el color del texto como azul oscuro
        livesLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente

        // Etiqueta de resultado. Se muestra cuando el usuario adivina el número.
        resultLabel = new JLabel(""); // Etiqueta para mostrar el resultado del juego restantes

        // Crea el panel principal y lo configura
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundColor); // Establece el color de fondo del panel

        // Configura las restricciones para la etiqueta de instrucción
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(instructionLabel, gbc);

        // Configura las restricciones para la etiqueta de vidas restantes
        gbc.gridy = 1;
        mainPanel.add(livesLabel, gbc);

        // Configura las restricciones para la etiqueta de resultado
        gbc.gridy = 2;
        mainPanel.add(resultLabel, gbc);

        // Fuentes y estilos
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        instructionLabel.setFont(labelFont);
        resultLabel.setFont(labelFont);
        livesLabel.setFont(labelFont);

        // Crea el campo de texto para ingresar el número
        textField = new JTextField(10); // Campo de texto para ingresar el número
        textField.setBackground(Color.white); // Establece el color de fondo del campo de texto como blanco
        textField.setForeground(textColor); // Establece el color del texto del campo de texto como azul oscuro
        textField.setFont(labelFont);
        textField.setHorizontalAlignment(JTextField.CENTER); // Centra el texto horizontalmente

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER); // Agrega el panel principal al marco

        // Crea un panel de entrada
        JPanel inputPanel = new JPanel();

        JButton guessButton = new JButton("Adivinar");
        guessButton.addActionListener(new GuessButtonListener()); // Evento del usuario para comprobar el número
                                                                  // introducido
        guessButton.setBackground(buttonColor); // Establece el color de fondo del botón como naranja claro
        guessButton.setForeground(textColor); // Establece el color del texto del botón como blanco
        guessButton.setFont(labelFont);

        JPanel menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton menuButton = new JButton("Volver al menú");
        menuButton.setBackground(buttonColor); // Establece el color de fondo del botón como naranja claro
        menuButton.setForeground(textColor); // Establece el color del texto del botón como blanco
        menuButton.addActionListener(new MenuButtonListener()); // Evento del usuario para volver al menú
        menuButton.setFont(labelFont.deriveFont(Font.BOLD, 12));
        menuButtonPanel.add(menuButton);

        // Configura el diseño y agrega los componentes al panel de entrada
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(textField);
        inputPanel.add(guessButton);

        // Agrega los paneles al marco principal
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(menuButtonPanel, BorderLayout.NORTH);
        frame.setSize(GuiConstants.FRAME_WIDTH, GuiConstants.FRAME_HEIGHT); // Aumentamos el tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setVisible(true);
    }

    // Nuevo ActionListener para el botón de menú
    private class MenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Volver al menú
            createAndShowDifficultyMenu();
            frame.dispose(); // Cierra la ventana actual
        }
    }

    // Escucha de eventos para el botón de adivinar
    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int userGuess = Integer.parseInt(textField.getText()); // Obtiene el número ingresado por el usuario
                game.incrementAttempts(); // Incrementa el contador de intentos del juego

                if (game.checkGuess(userGuess)) { // Verifica si el número ingresado es correcto
                    resultLabel.setText("¡Correcto! Has adivinado el número en " + game.getAttempts() + " intentos.");
                    textField.setEditable(false); // Deshabilita la edición del campo de texto
                } else {
                    resultLabel.setText("Intenta con un número "
                            + (userGuess < game.getNumberToGuess() ? "más grande." : "más pequeño."));
                    livesLabel.setText("Vidas restantes: " + (game.getMaxAttempts() - game.getAttempts()));
                    textField.setText(""); // Limpia el campo de texto
                }

                if (game.isGameOver()) { // Verifica si el juego ha terminado
                    textField.setEditable(false); // Deshabilita la edición del campo de texto
                    showGameOverDialog(); // Muestra el diálogo de fin de juego
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Por favor, ingresa un número válido."); // Muestra un mensaje de error si el número
                                                                             // ingresado no es válido
            }
        }
    }

    // Muestra un diálogo de fin de juego con dos opciones: "Volver a jugar" y
    // "Salir"
    private void showGameOverDialog() {
        Object[] options = { "Volver a jugar", "Salir" };

        // Muestra el diálogo de opción con el mensaje de juego terminado y el número a
        // adivinar
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
            createAndShowDifficultyMenu();
            // Volver a jugar
            frame.dispose(); // Cerrar la ventana actual
            // Volver a la pantalla de selección de dificultad
            difficultyMenu.setVisible(true);
        } else if (choice == 1) {
            // Salir de la aplicación
            System.exit(0);
        }
    }
}