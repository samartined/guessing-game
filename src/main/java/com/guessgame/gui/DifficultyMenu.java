package com.guessgame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyMenu {
    private JFrame frame;
    private JComboBox<String> difficultyComboBox;

    public DifficultyMenu(Runnable onSelectDifficulty) {
        frame = new JFrame("Selecciona la dificultad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Selecciona la dificultad:");
        difficultyComboBox = new JComboBox<>(new String[] { "Fácil", "Intermedio", "Difícil" });
        JButton startButton = new JButton("Comenzar");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSelectDifficulty.run();
                frame.dispose();
            }
        });

        mainPanel.add(label);
        mainPanel.add(difficultyComboBox);
        mainPanel.add(startButton);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    public String getSelectedDifficulty() {
        return (String) difficultyComboBox.getSelectedItem();
    }
}
