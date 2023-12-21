package com.guessgame.gui;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyMenu extends JPanel {
    private JFrame frame;
    private JComboBox<String> difficultyComboBox;

    public DifficultyMenu(Runnable onSelectDifficulty) {

        // Configuramos FlatLaf
        try {
            UIManager.setLookAndFeel((new FlatDarkLaf()));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame("¡Adivina el Número!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Personaliza los colores
        Color backgroundColor = new Color(255, 231, 186); // Amarillo claro
        Color textColor = new Color(34, 49, 63); // Azul oscuro
        Color buttonColor = new Color(242, 120, 75); // Naranja claro

        frame.getContentPane().setBackground(backgroundColor);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Borde para espacio
        mainPanel.setBackground(backgroundColor);

        JLabel tituloJuego = new JLabel("¡BIENVENIDO A ADIVINA EL NÚMERO!");
        tituloJuego.setFont(new Font("Arial", Font.BOLD, 18));
        tituloJuego.setAlignmentX(Component.CENTER_ALIGNMENT); // Título centrado horizontalmente
        tituloJuego.setForeground(textColor);

        JLabel label = new JLabel("Selecciona la dificultad:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        label.setForeground(textColor);

        difficultyComboBox = new JComboBox<>(new String[] { "Fácil", "Intermedio", "Difícil" });
        difficultyComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        difficultyComboBox.setMaximumSize((new Dimension(200, 30)));
        tituloJuego.setAlignmentX(Component.CENTER_ALIGNMENT); // Título centrado horizontalmente
        difficultyComboBox.setBackground(buttonColor);
        difficultyComboBox.setForeground(textColor);

        JButton startButton = new JButton("Comenzar");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Título centrado horizontalmente
        startButton.setBackground(buttonColor);
        startButton.setForeground(Color.white);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelectDifficulty.run();
                frame.dispose();
            }
        });

        mainPanel.add(tituloJuego);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(label);
        mainPanel.add(difficultyComboBox);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(startButton);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 250); // Aumentamos el tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setVisible(true);
    }

    public String getSelectedDifficulty() {
        return (String) difficultyComboBox.getSelectedItem();
    }
}