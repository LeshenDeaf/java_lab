package edu.softwaredevelopment.labs.domain.ui;

import edu.softwaredevelopment.labs.domain.ui.Panel;
import edu.softwaredevelopment.labs.matrix.Matrix;
import edu.softwaredevelopment.labs.matrix.MatrixException;

import javax.swing.*;

public class MatrixOutput {
    private final Panel panel;

    private JTextArea textArea = new JTextArea();

    public boolean onPanel = false;

    private Matrix matrix;

    public MatrixOutput(Panel panel) {
        this.panel = panel;

        textArea.setBounds(10, 260, 300, 250 );
    }

    public void addToPanel(Matrix matrix) throws MatrixException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < matrix.getM(); i++) {
            stringBuilder.append("\n");
            for (int j = 0; j < matrix.getN(); j++) {
                stringBuilder.append(matrix.getCellValue(i, j));
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("\n]");

        textArea.setText(stringBuilder.toString());

        panel.add(textArea);

        onPanel = true;

        reload();
    }

    private void reload() {
        panel.revalidate();
        panel.repaint();
    }


    public void removeFromPanel() {
        panel.remove(textArea);

        textArea.setText("");

        onPanel = false;

        reload();
    }
}
