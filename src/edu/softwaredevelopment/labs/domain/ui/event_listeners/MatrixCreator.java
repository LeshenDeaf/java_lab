package edu.softwaredevelopment.labs.domain.ui.event_listeners;

import edu.softwaredevelopment.labs.domain.ui.InputPosition;
import edu.softwaredevelopment.labs.domain.ui.Panel;
import edu.softwaredevelopment.labs.domain.ui.matrix_inputs.AllInputs;
import edu.softwaredevelopment.labs.domain.ui.matrix_inputs.FieldsInputs;
import edu.softwaredevelopment.labs.domain.ui.matrix_inputs.InitialInputs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixCreator implements ActionListener {
    private final AllInputs inputs;

    private final Panel panel;

    public MatrixCreator(AllInputs inputs, Panel panel) {
        this.inputs = inputs;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        makeFieldsInput();

        inputs.getFields().addToPanel(panel);
    }

    private void makeFieldsInput() {
        InitialInputs initials = inputs.getInitials();

        if (inputs.getFields() != null) {
            changeInputFields(initials.getN(), initials.getM());

            return;
        }

        InputPosition startPos = initials.getStartPos();

        inputs.setFields(new FieldsInputs(
                initials.getN(), initials.getM(),
                new InputPosition(startPos.getX(), startPos.getY() + 36)
            )
        );
    }

    private void changeInputFields(int n, int m) {
        inputs.getFields().removeFromPanel(panel);

        try {
            inputs.getFields().setNewMatrixData(n, m);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
