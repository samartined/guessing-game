package com.guessgame.gui;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Configuración del ComboBox de dificultad
        difficultyComboBox = new JComboBox<>(new String[] { "Fácil", "Intermedio", "Difícil" });
        difficultyComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        difficultyComboBox.setMaximumSize((new Dimension(200, 30)));
        difficultyComboBox.setAlignmentX(Component.CENTER_ALIGNMENT); // ComboBox centrado horizontalmente
        difficultyComboBox.setBackground(buttonColor);
        difficultyComboBox.setForeground(textColor);

        // Configuración del botón de inicio
        JButton startButton = new JButton("Comenzar");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Botón centrado horizontalmente
        startButton.setBackground(buttonColor);
        startButton.setForeground(Color.white);

        // Acción al hacer clic en el botón de inicio
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelectDifficulty.run();
                frame.dispose();
            }
        });

        // Agregar componentes al panel principal
        mainPanel.add(tituloJuego);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(label);
        mainPanel.add(difficultyComboBox);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(startButton);

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
}