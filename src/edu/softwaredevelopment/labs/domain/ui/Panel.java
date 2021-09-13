package edu.softwaredevelopment.labs.domain.ui;

import edu.softwaredevelopment.labs.domain.ui.event_listeners.MatrixCreator;
import edu.softwaredevelopment.labs.domain.ui.event_listeners.ResultMatrixCreator;
import edu.softwaredevelopment.labs.domain.ui.matrix_inputs.AllInputs;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final AllInputs AInputs = new AllInputs();

    private final AllInputs BInputs = new AllInputs();

    private final MatrixOutput output = new MatrixOutput(this);

    private final JPanel panel = this;

    private final InputPosition initInputEndPos;

    public static final String[] operations = {"Multiply", "Subtract", "Add"};
    private final JComboBox<String> operationsList;


    public Panel() {
        setLayout(null);

        initInputEndPos = AInputs.getInitials().setBounds(new InputPosition(10, 10));

        AInputs.getInitials().getSubmitBtn().addActionListener(
                new MatrixCreator(AInputs, this)
        );

        AInputs.getInitials().addToPanel(this);

        BInputs.getInitials().setBounds(
                new InputPosition(initInputEndPos.getX() + 126, initInputEndPos.getY())
        );
        BInputs.getInitials().getSubmitBtn().addActionListener(
                new MatrixCreator(BInputs, this)
        );

        operationsList = new JComboBox<>();
        configureOperationsList();

        this.add(operationsList);
        this.add(createSubmitButton());

        BInputs.getInitials().addToPanel(this);
    }

    public JComboBox<String> getOperationsList() {
        return operationsList;
    }

    public MatrixOutput getOutput() {
        return output;
    }

    public AllInputs getAInputs() {
        return AInputs;
    }

    public AllInputs getBInputs() {
        return BInputs;
    }

    private void configureOperationsList() {
        operationsList.setModel(new DefaultComboBoxModel<String>(operations));
        operationsList.setSelectedIndex(0);
        operationsList.setBounds(10, 240, 100, 20);
    }

    private JButton createSubmitButton() {
        JButton submitBtn = new JButton("Ok");
        submitBtn.setBounds(120, 240, 30, 20);
        submitBtn.setMargin(new Insets(0, 0, 0, 0));

        submitBtn.addActionListener(
                new ResultMatrixCreator(AInputs, BInputs, this)
        );

        return submitBtn;
    }
}
