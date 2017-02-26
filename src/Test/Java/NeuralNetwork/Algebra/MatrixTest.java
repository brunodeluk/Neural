package NeuralNetwork.Algebra;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by brunodeluca on 2/26/17.
 */
public class MatrixTest {
    @Test
    public void multiply() throws Exception {

    }

    @Test
    public void multiply1() throws Exception {

    }

    @Test
    public void scalarMultiply() throws Exception {
        float[][] a = {{1},{2},{3}};
        float[][] b = {{2}, {3}, {4}};

        float[][] result = Matrix.scalarMultiply(a, b);
        Matrix.printMatrix(result);

        float[][] compare = {{2,6,12}};
        Matrix.printMatrix(compare);
    }

    @Test
    public void toVectorWithColumnedMatrix() throws Exception {
        float[][] c = {{1},{2},{3}};
        float[] result = Matrix.toVector(c);

        float[] compare = {1,2,3};

        assertTrue(Arrays.equals(result, compare));
    }

    @Test
    public void toVectorNotColumn() throws Exception {
        float[][] c = {{1,2,3}};
        float[] result = Matrix.toVector(c);

        float[] compare = {1,2,3};

        assertTrue(Arrays.equals(result, compare));
    }

    @Test
    public void toVectorExceptionError() throws Exception {
        float[][] c = {{1,2,3}, {1,2,3}, {1,2,3}};
        float[] result = Matrix.toVector(c);
    }

    @Test
    public void sum() throws Exception {
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        System.out.println(matrix[0][1]);
    }

    @Test
    public void transpose() throws Exception {

    }

    @Test
    public void printMatrix() throws Exception {

    }

}