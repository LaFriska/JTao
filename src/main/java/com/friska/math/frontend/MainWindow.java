package com.friska.math.frontend;

import com.friska.math.questions.Questions;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private final JFrame window;

    public MainWindow(){
        window = new JFrame();
        window.setTitle("Math Questions");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 500);
        window.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(0, 0, 250, 250);
        window.add(panel);

        ImageIcon i = new ImageIcon(TeXRenderer.renderImage(Questions.matrixVectorMultiplication(3, 5, 3, 5, -5, 12).questionTex()));
        Image newImage = i.getImage().getScaledInstance((int) (i.getIconWidth() * 0.2), (int) (i.getIconHeight() * 0.2), Image.SCALE_SMOOTH);
        i = new ImageIcon(newImage);
        window.add(new JLabel(i));
    }

    public void show(){
        window.setVisible(true);
    }

}
