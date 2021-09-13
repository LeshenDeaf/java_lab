package edu.softwaredevelopment.labs.matrix;

import java.util.Arrays;

/**
 * @author      Alexey Glukhikh
 * @version     1.0
 */
public class Matrix {
    /**
     * number of columns
     */
    private final int n;
    /**
     * number of rows
     */
    private final int m;
    /**
     * name of the matrix
     */
    private String name;
    /**
     * matrix values
     */
    private int[][] matrix;

    /**
     * Creates zero filled matrix
     * @param cols number of columns
     * @param rows number of rows
     * @param name name of the matrix
     */
    public Matrix(int cols, int rows, String name) {
        this.n = cols;
        this.m = rows;
        this.name = name;
        matrix = new int[rows][cols];
        for (int[] row : matrix) {
            Arrays.fill(row, 0);
        }
    }

    /**
     * Creates matrix from your array
     * <p>Use {@link #setMatrix(int[][] matrix)} to set and validate data</p>
     * @param mtr the data for matrix
     * @param name name of matrix
     * @throws MatrixException if data was incorrect
     */
    public Matrix(int[][] mtr, String name) throws MatrixException {
        this.m = mtr.length;
        this.n = m > 0 ? mtr[0].length : 0;
        this.name = name;
        setMatrix(mtr);
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Validates provided data and sets matrix if data was correct
     * @param matrix matrix data
     * @throws MatrixException if data is incorrect (ex. different number of columns in rows)
     */
    public void setMatrix(int[][] matrix) throws MatrixException {
        if (this.m != matrix.length) {
            throw new MatrixException("Количество строк введенной матрицы не соответствует количеству строк матрицы");
        }
        for (int[] row : matrix) {
            if (this.n != row.length) {
                throw new MatrixException("Количество столбцов введенной матрицы не соответствует количеству столбцов матрицы");
            }
        }
        this.matrix = matrix;
    }

    /**
     * Sets provided value in selected cell
     * @param i row
     * @param j column
     * @param value value must be added
     * @throws MatrixException if one or both indexes out of matrix range
     */
    public void setCellValue(int i, int j, int value) throws MatrixException {
        if (i >= m || j >= n || i < 0 || j < 0) {
            throw new MatrixException(
                "i или j не входят в диапозон 0 <= i < " + m + "0 <= j < " + n
            );
        }
        matrix[i][j] = value;
    }

    /**
     * Returns value from cell
     * @param i row
     * @param j column
     * @return selected value
     * @throws MatrixException if one or both indexes out of matrix range
     */
    public int getCellValue(int i, int j) throws MatrixException {
        if (i >= m || j >= n || i < 0 || j < 0) {
            throw new MatrixException(
                "i или j не входят в диапозон 0 <= i < " + m + " 0 <= j < " + n
            );
        }
        return matrix[i][j];
    }

    /**
     * Returns row with provided index
     * @param row index of a row
     * @return selected row
     * @throws MatrixException if row index is out of range
     */
    public int[] getRow(int row) throws MatrixException {
        if (row >= m || row < 0) {
            throw new MatrixException(
                "Индекс строки не входит в диапозон 0 <= row < " + m
            );
        }
        return matrix[row];
    }

    /**
     * Returns column with provided index
     * @param column index of a column
     * @return selected column
     * @throws MatrixException if row column is out of range
     */
    public int[] getColumn(int column) throws MatrixException {
        if (column >= n || column < 0) {
            throw new MatrixException(
                "Индекс столбца не входит в диапозон 0 <= column < " + n
            );
        }
        int[] col = new int[m];
        for (int i = 0; i < m; i++) {
            col[i] = matrix[i][column];
        }
        return col;
    }

    /**
     * Prints the matrix
     * <p>Use {@link #concat(String, int...)} to implode a row</p>
     */
    public void print() {
        StringBuilder stringBuilder = new StringBuilder(name);

        if (matrix.length == 0) {
            System.out.println("Matrix is empty");
        }

        stringBuilder.append(" = [");
        for (int i = 0; i < m - 1; i++) {
            stringBuilder.append(
                concat("", "\n\t(", concat(",", matrix[i]), "),")
            );
        }

        stringBuilder.append(
            concat("", "\n\t(", concat(",", matrix[m-1]), ")\n]")
        );
        System.out.println(stringBuilder.toString());
    }

    /**
     * Prints info about the matrix
     * <p>Use {@link #concat(String, int...)} to concatenate data </p>
     */
    public void info() {
        System.out.println(
            concat(
                "",
                name, " - ", String.valueOf(m), " rows x ", String.valueOf(n), " columns"
            )
        );
    }

    /**
     * Concatenates provided arguments with delimiter
     * @param delimiter delimiter between arguments
     * @param arguments values that must be concatenated
     * @return concatenated string
     */
    private String concat(String delimiter, String... arguments) {
        StringBuilder sb = new StringBuilder();
        if (arguments.length == 0) {
            return "";
        }
        for (int i = 0; i < arguments.length - 1; i++) {
            sb.append(arguments[i]);
            sb.append(delimiter);
        }
        sb.append(arguments[arguments.length - 1]);
        return sb.toString();
    }
    private String concat(String delimiter, int... arguments) {
        StringBuilder sb = new StringBuilder();
        if (arguments.length == 0) {
            return "";
        }
        for (int i = 0; i < arguments.length - 1; i++) {
            sb.append(arguments[i]);
            sb.append(delimiter);
        }
        sb.append(arguments[arguments.length - 1]);

        return sb.toString();
    }

    /**
     * Sums or subtracts matrices based on isSum
     * @param isSum if true, it sums matrices, else subtracts from current the provided
     * @param matr matrix that will be summed|subtracted with
     * @return result of operation
     * @throws MatrixException if matrices have different sizes
     */
    private Matrix sumSubtract(boolean isSum, Matrix matr) throws MatrixException {
        if (n != matr.getN() || m != matr.getM()) {
            throw new MatrixException("Матрицы должны иметь одинаковый размер");
        }
        Matrix result = new Matrix(n, m, isSum ? "sum" : "subtraction");

        int operation = isSum ? 1 : -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result.setCellValue(i, j, matrix[i][j] + operation * matr.getCellValue(i, j));
            }
        }
        return result;
    }

    /**
     * Sums current matrix with provided and returns the result of operation
     * <p>Use {@link #sumSubtract(boolean isSum, Matrix matr)} to sum matrices, isSum = true </p>
     * @param matr matrix will be sum with
     * @return result of operation
     * @throws MatrixException if matrices have different sizes
     */
    public Matrix sum(Matrix matr) throws MatrixException {
        return sumSubtract(true, matr);
    }

    /**
     * Subtracts current matrix with provided and returns the result of operation
     * <p>Use {@link #sumSubtract(boolean isSum, Matrix matr)} to sum matrices, isSum = false </p>
     * @param matr matrix will be subtracted
     * @return result of operation
     * @throws MatrixException if matrices have different sizes
     */
    public Matrix subtract(Matrix matr) throws MatrixException {
        return sumSubtract(false, matr);
    }

    /**
     * Calculates cell value for multiplication
     * @param row row that will be multiplied
     * @param column column that will be multiplied
     * @return result of multiplication
     * @throws MatrixException if row and column have different lengths
     */
    private int makeMultiplicationCellValue(int[] row, int[] column) throws MatrixException {
        int res = 0;
        if (row.length != column.length) {
            throw new MatrixException("Строка и столбец должны быть одинаковой длины");
        }
        for (int i = 0; i < row.length; i++) {
            res += row[i] * column[i];
        }
        return res;
    }

    /**
     * Multiplies current matrix on provided one and returns the result of multiplication.
     * <p> Current and provided matrices are not touched </p>
     * @param matr matrix will be multiplied on
     * @return result on operation
     * @throws MatrixException
     * if number of rows of the current matrix is not equal the number of columns of provided one and vice versa
     */
    public Matrix multiply(Matrix matr) throws MatrixException {
        if (n != matr.getM() || m != matr.getN()) {
            throw new MatrixException(
                "Количество строк первой матрицы должно быть равно количеству столбцов у другой и наоборот"
            );
        }
        Matrix result = new Matrix(matr.getN(), m, "multiplication");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < matr.getN(); j++) {
                result.setCellValue(i, j, makeMultiplicationCellValue(matrix[i], matr.getColumn(j)));
            }
        }

        return result;
    }

}
