package com.guessgame.gui;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.guessgame.bestgamerecord.BestGameRecord;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * La clase DifficultyMenu representa un componente de interfaz gráfica de
 * usuario que permite al usuario seleccionar el nivel de dificultad para un
 * juego.
 * Extiende la clase JPanel y proporciona métodos para obtener la dificultad
 * seleccionada.
 */

public class DifficultyMenu extends JPanel {
    private JFrame frame;
    private JComboBox<String> difficultyComboBox;
    private JTextArea historyTextArea;

    public DifficultyMenu(Runnable onSelectDifficulty) {

        // Configuramos FlatLaf para utilizar un tema oscuro
        try {
            UIManager.setLookAndFeel((new FlatDarkLaf()));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Personaliza los colores
        Color backgroundColor = new Color(255, 231, 186); // Amarillo claro
        Color textColor = new Color(34, 49, 63); // Azul oscuro
        Color buttonColor = new Color(242, 120, 75); // Naranja claro

        // Configuración de la ventana principal
        frame = new JFrame("¡Adivina el Número!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(backgroundColor);

        // Configuración del panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Borde para espacio
        mainPanel.setBackground(backgroundColor);

        // Configuración del título del juego
        JLabel tituloJuego = new JLabel("¡BIENVENIDO A \"ADIVINA EL NÚMERO!\"");
        tituloJuego.setFont(new Font("Arial", Font.BOLD, 18));
        tituloJuego.setAlignmentX(Component.CENTER_ALIGNMENT); // Título centrado horizontalmente
        tituloJuego.setForeground(textColor);

        // Configuración de la etiqueta de selección de dificultad
        JLabel label = new JLabel("Selecciona la dificultad:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        label.setForeground(textColor);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        try {
            // Configuración del ComboBox de dificultad
            difficultyComboBox = new JComboBox<>(
                    new String[] { "Fácil (8 vidas)", "Intermedio (5 vidas)", "Difícil (3 vidas)" });
            difficultyComboBox.setFont(new Font("Arial", Font.BOLD, 14));
            difficultyComboBox.setMaximumSize((new Dimension(200, 30)));
            difficultyComboBox.setAlignmentX(Component.CENTER_ALIGNMENT); // ComboBox centrado horizontalmente
            difficultyComboBox.setBackground(buttonColor);
            difficultyComboBox.setForeground(textColor);

            // Crea un renderizador personalizado para centrar el texto
            DefaultListCellRenderer renderer = new DefaultListCellRenderer();
            renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
            difficultyComboBox.setRenderer(renderer);

            difficultyComboBox.setAlignmentX(Component.CENTER_ALIGNMENT); // ComboBox centrado horizontalmente

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configuración del botón de inicio
        JButton startButton = new JButton("Comenzar");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Botón centrado horizontalmente
        startButton.setBackground(buttonColor);
        startButton.setForeground(textColor);

        // Acción al hacer clic en el botón de inicio
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelectDifficulty.run();
                frame.dispose();
            }

        });

        // Configuración del botón de historial
        JButton historyButton = new JButton("Mejores Partidas");
        historyButton.setAlignmentX(Component.LEFT_ALIGNMENT); // Botón alineado a la izquierda

        // Agregar el botón de historial al panel principal

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHistory(BestGameRecord.getBestGames());
            }
        });

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setBackground(backgroundColor);
        historyTextArea.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        historyPanel.add(historyButton);
        historyPanel.add(historyTextArea);
        historyPanel.setBackground(backgroundColor);

        // Agregar componentes al panel principal
        mainPanel.add(tituloJuego);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(label);
        mainPanel.add(difficultyComboBox);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(historyPanel);

        // Agregar panel principal a la ventana
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(GuiConstants.FRAME_WIDTH, GuiConstants.FRAME_HEIGHT); // Aumentamos el tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setVisible(true);
    }

    /**
     * Recupera la dificultad seleccionada del difficultyComboBox.
     * 
     * @return La dificultad seleccionada como una cadena de texto.
     */

    public String getSelectedDifficulty() {
        return (String) difficultyComboBox.getSelectedItem();
    }

    public void updateHistory(List<BestGameRecord> bestGames) {
        historyTextArea.setText("Historial de Mejores Partidas:\n");

        for (BestGameRecord bestGame : bestGames) {
            historyTextArea.append("Fecha: " + bestGame.getDate() +
                    ", Dificultad: " + bestGame.getDifficulty() +
                    ", Intentos: " + bestGame.getAttempts() + "\n");
        }
    }
}