package edu.softwaredevelopment.labs.domain.ui.matrix_inputs;

import edu.softwaredevelopment.labs.domain.ui.InputPosition;
import edu.softwaredevelopment.labs.domain.ui.Position;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class InitialInputs {

    private final JFormattedTextField nInput;

    private final JLabel nLabel = new JLabel("n");

    private final JFormattedTextField mInput;

    private final JLabel mLabel = new JLabel("m");

    private final JButton submitBtn = new JButton("Ok");

    private InputPosition endPos;
    private InputPosition startPos;

    public InitialInputs() {
        MaskFormatter numberFormat = createFormatter("#");

        nInput = new JFormattedTextField(numberFormat);
        mInput = new JFormattedTextField(numberFormat);
    }

    public JFormattedTextField getnInput() {
        return nInput;
    }

    public JFormattedTextField getmInput() {
        return mInput;
    }

    public Integer getN() {
        return Integer.parseInt(nInput.getText());
    }

    public Integer getM() {
        return Integer.parseInt(mInput.getText());
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public Position getEndPos() {
        return endPos;
    }

    public InputPosition getStartPos() {
        return startPos;
    }

    public static MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;

        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }

        return formatter;
    }

    public InputPosition setBounds(InputPosition position) {
        startPos = position;

        int x =  position.getX();
        int y = position.getY();

        nLabel.setBounds(x + 5, y, 20, 16);
        nInput.setBounds(x, y + 16, 20, 16);

        mLabel.setBounds(x + 35, y, 20, 16);
        mInput.setBounds(x + 30, y + 16, 20, 16);

        submitBtn.setBounds(x + 60, y + 16, 30, 16);
        submitBtn.setMargin(new Insets(0, 0, 0, 0));

        endPos = new InputPosition(x + 90, y);

        return endPos;
    }

    public void addToPanel(JPanel panel) {
        panel.add(nInput);
        panel.add(nLabel);
        panel.add(mInput);
        panel.add(mLabel);
        panel.add(submitBtn);
    }
}
