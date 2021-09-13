package edu.softwaredevelopment.labs.domain.ui.event_listeners;

import edu.softwaredevelopment.labs.domain.ui.matrix_inputs.AllInputs;
import edu.softwaredevelopment.labs.domain.ui.matrix_inputs.FieldsInputs;
import edu.softwaredevelopment.labs.domain.ui.Panel;
import edu.softwaredevelopment.labs.matrix.Matrix;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultMatrixCreator implements ActionListener {
    private AllInputs aMatrix;
    private AllInputs bMatrix;

    private final Panel panel;

    public ResultMatrixCreator(
            AllInputs AMatrix, AllInputs BMatrix, Panel panel
    ) {
        super();

        this.panel = panel;

        try {
            aMatrix = AMatrix;
            bMatrix = BMatrix;
        } catch (Exception matrixException) {
            JOptionPane.showMessageDialog(this.panel, matrixException.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (panel.getOutput().onPanel) {
                panel.getOutput().removeFromPanel();
            }

            Matrix res = getResultMatrix(
                    panel.getOperationsList().getSelectedItem().toString()
            );

            panel.getOutput().addToPanel(res);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(panel, exception.getMessage());
        }

    }

    private Matrix getResultMatrix(String operation) throws Exception {
        if (aMatrix.getFields() == null) {
            throw new Exception("Не заполнена первая матрица!");
        }

        if (bMatrix.getFields() == null) {
            throw new Exception("Не заполнена вторая матрица!");
        }

        if (operation.equals("Multiply")) {
            return aMatrix
                    .getFields()
                    .toMatrix()
                    .multiply(
                            bMatrix.getFields().toMatrix()
                    );
        }

        if (operation.equals("Subtract")) {
            return aMatrix
                    .getFields()
                    .toMatrix()
                    .subtract(
                            bMatrix.getFields().toMatrix()
                    );
        }

        if (operation.equals("Add")) {
            return aMatrix
                    .getFields()
                    .toMatrix()
                    .sum(
                            bMatrix.getFields().toMatrix()
                    );
        }

        throw new Exception("Неизвестная операция");
    }
}
