package edu.softwaredevelopment.labs.domain;

import edu.softwaredevelopment.labs.matrix.Matrix;

import java.util.HashMap;
import java.util.Map;

public class Program {
    private final Map<String, Matrix> matrixMap = new HashMap<>();

    public static void main(String[] args) {
        Program program = new Program();
        program.add(new Matrix(2, 4, "multTest1"));
        program.add(new Matrix(2, 4, "sumSubTest1"));

        try {
            program.info("multTest1");
            int[][] m = new int[][] {{1,2}, {3,4}, {5,6}, {7, 8}};
            int[][] x = new int[][] {{1,2, 3, 4}, {5, 6, 7, 8}};

            program.add(new Matrix(x, "multTest2"));

            program.getMatrix("multTest1").setMatrix(m);
            program.getMatrix("sumSubTest1").setMatrix(m);

            program.print("multTest1");
            program.print("sumSubTest1");
            program.print("multTest2");

            program.add(program.sum("multTest1", "sumSubTest1"), "Summa");
            program.getMatrix("Summa").print();

            program.add(
                program.subtract("multTest1", "sumSubTest1")
            );
            program.print("subtraction");

            program.add(
                program.multiply("multTest1", "multTest2"),
                "mtest"
            );
            program.getMatrix("mtest").print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a matrix with provided name
     * @param name name of the wanted matrix
     * @return matrix with provided name
     * @throws Exception Matrix is undefined
     */
    public Matrix getMatrix(String name) throws Exception{
        Matrix matrix = matrixMap.get(name);
        if (matrix == null) {
            throw new Exception("Матрица " + name + " не определена");
        }
        return matrix;
    }

    /**
     * Adds matrix to the matrices list
     * @param m matrix that should be added
     */
    public void add(Matrix m) {
        matrixMap.put(m.getName(), m);
    }

    /**
     * Adds matrix to the matrices list
     * @param m matrix that should be added
     * @param name name for provided matrix
     */
    public void add(Matrix m, String name) {
        m.setName(name);
        matrixMap.put(name, m);
    }

    /**
     * Multiplies matrices and returns the resulting matrix
     * @param xName Name of the first matrix
     * @param yName Name of the second matrix
     * @return result of multiplication
     * @throws Exception if matrices can't be multiplied or provided data is incorrect
     */
    public Matrix multiply(String xName, String yName) throws Exception {
        return getMatrix(xName).multiply(getMatrix(yName));
    }

    /**
     * Sums matrices and returns resulting matrix
     * @param xName Name of the first matrix
     * @param yName Name of the second matrix
     * @return result of sum
     * @throws Exception if matrices can't be summed or provided data is incorrect
     */
    public Matrix sum(String xName, String yName) throws Exception {
        return getMatrix(xName).sum(getMatrix(yName));
    }

    /**
     * Subtracts matrices and returns resulting matrix
     * @param xName Name of a minuend matrix
     * @param yName Name of a subtrahend matrix
     * @return difference
     * @throws Exception if matrices can't be subtracted or provided data is incorrect
     */
    public Matrix subtract(String xName, String yName) throws Exception {
        return getMatrix(xName).subtract(getMatrix(yName));
    }

    /**
     * Prints info about a matrix
     * @param name name of a matrix you want to get info about
     * @throws Exception if matrix is undefined
     */
    public void info(String name) throws Exception {
        if (!matrixMap.containsKey(name)) {
            throw new Exception("Матрица " + name + " не определена.");
        }
        matrixMap.get(name).info();
    }

    /**
     * Prints the matrix
     * @param name name of a matrix you want to print
     * @throws Exception if matrix is undefined
     */
    public void print(String name) throws Exception {
        if (!matrixMap.containsKey(name) || matrixMap.get(name) == null) {
            throw new Exception("Матрица " + name + " не определена.");
        }
        matrixMap.get(name).print();
    }

    /**
     * Prints all the matrices in matrices list
     */
    public void printAll() {
        for(Map.Entry<String, Matrix> entry : matrixMap.entrySet()) {
            Matrix value = entry.getValue();
            value.print();
            System.out.println("\n");
        }
    }
}
