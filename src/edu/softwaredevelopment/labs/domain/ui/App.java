package edu.softwaredevelopment.labs.domain.ui;

import javax.swing.*;

public class App {
    private final JFrame window;

    public App() {
        window = new JFrame("Matrix calculator");

        window.add(new Panel());

        window.setSize(450, 419);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}
