package edu.softwaredevelopment.labs.domain.ui.matrix_inputs;

import edu.softwaredevelopment.labs.domain.ui.InputPosition;
import edu.softwaredevelopment.labs.matrix.Matrix;
import edu.softwaredevelopment.labs.matrix.MatrixException;
import jdk.internal.util.xml.impl.Input;

import javax.swing.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldsInputs {
    private JFormattedTextField[][] cells;

    private List<JLabel> colsLabels = new ArrayList<JLabel>();
    private List<JLabel> rowsLabels = new ArrayList<JLabel>();

    private int rows;
    private int columns;
    private InputPosition firstCellPosition;
    private final NumberFormat format;
    private InputPosition lastCellPosition;

    public FieldsInputs(int rows, int columns, InputPosition firstCellPosition) {
        cells = new JFormattedTextField[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.firstCellPosition = firstCellPosition;

        format = NumberFormat.getNumberInstance();
        format.setMaximumIntegerDigits(2);
        format.setParseIntegerOnly(true);
    }

    public void setNewMatrixData(int rows, int columns) throws Exception {
        if (rows <= 0 || columns <= 0) {
            throw new Exception("Number of rows and columns must be greater than zero!");
        }

        this.rows = rows;
        this.columns = columns;
        cells = new JFormattedTextField[rows][columns];

        colsLabels = new ArrayList<JLabel>();
        rowsLabels = new ArrayList<JLabel>();
    }

    public JFormattedTextField[][] getCells() {
        return cells;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) throws Exception {
        if (rows <= 0) {
            throw new Exception("Rows number must be greater than zero");
        }
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) throws Exception {
        if (columns <= 0) {
            throw new Exception("Columns number must be greater than zero");
        }
        this.columns = columns;
    }

    public InputPosition getFirstCellPosition() {
        return firstCellPosition;
    }

    public void setFirstCellPosition(InputPosition firstCellPosition) {
        this.firstCellPosition = firstCellPosition;
    }

    public InputPosition getLastCellPosition() {
        return lastCellPosition;
    }

    private JFormattedTextField makeCell(InputPosition position) {
        JFormattedTextField cell = new JFormattedTextField(format);
        cell.setBounds(position.getX(), position.getY(), 20, 16);

        return cell;
    }

    public InputPosition addToPanel(JPanel panel) {
        lastCellPosition = createMatrixColumnLabels(panel);

        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            rowsLabels.add(makeLabel(lastCellPosition, rowIndex + 1));
            panel.add(rowsLabels.get(rowIndex));

            lastCellPosition.incrementX(16);

            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                cells[rowIndex][columnIndex] = makeCell(lastCellPosition);

                lastCellPosition.incrementX(21);

                panel.add(cells[rowIndex][columnIndex]);
            }

            lastCellPosition.incrementY(17);
            lastCellPosition.setX(firstCellPosition.getX());

        }

        reload(panel);

        return lastCellPosition;
    }

    public void removeFromPanel(JPanel panel) {
        for (JFormattedTextField[] row : cells) {
            for (JFormattedTextField cell : row) {
                panel.remove(cell);
            }
        }

        cells = new JFormattedTextField[0][0];

        for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
            panel.remove(colsLabels.get(columnIndex));
        }

        colsLabels = new ArrayList<JLabel>();

        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            panel.remove(rowsLabels.get(rowIndex));
        }

        rowsLabels = new ArrayList<JLabel>();
    }

    private JLabel makeLabel(InputPosition position, Integer text) {
        JLabel label = new JLabel(Integer.toString(text));
        label.setBounds(position.getX(), position.getY(), 20, 16);

        return label;
    }

    private InputPosition createMatrixColumnLabels(JPanel panel) {
        InputPosition lastPos = new InputPosition(firstCellPosition.getX() + 21, firstCellPosition.getY());

        for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
            colsLabels.add(makeLabel(lastPos, columnIndex + 1));

            lastPos.incrementX(21);

            panel.add(colsLabels.get(columnIndex));
        }

        lastPos.setY(firstCellPosition.getY() + 17);
        lastPos.setX(firstCellPosition.getX());

        return lastPos;
    }

    private String generateName() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);

        return  new String(array, StandardCharsets.UTF_8);
    }

    public Matrix toMatrix() throws Exception {
        Matrix m = new Matrix(columns, rows, generateName());

        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                if (cells[rowIndex][columnIndex].getText().equals("")) {
                    throw new Exception(
                            "Не заполнено поле n = "
                            + (rowIndex + 1) + " m = " + (columnIndex + 1)
                    );
                }

                m.setCellValue(
                        rowIndex, columnIndex,
                        Integer.parseInt(
                                cells[rowIndex][columnIndex].getText()
                        )
                );
            }
        }


        return m;
    }

    private void reload(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }
}
