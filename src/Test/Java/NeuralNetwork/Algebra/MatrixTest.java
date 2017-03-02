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
        float[] result = Matrix.toArray(c);

        float[] compare = {1,2,3};

        assertTrue(Arrays.equals(result, compare));
    }

    @Test
    public void toVectorNotColumn() throws Exception {
        float[][] c = {{1,2,3}};
        float[] result = Matrix.toArray(c);

        float[] compare = {1,2,3};

        assertTrue(Arrays.equals(result, compare));
    }

    @Test
    public void toVectorExceptionError() throws Exception {
        float[][] c = {{1,2,3}, {1,2,3}, {1,2,3}};
        float[] result = Matrix.toArray(c);
    }

    @Test
    public void sum() throws Exception {
    }

    @Test
    public void transpose() throws Exception {

    }

    @Test
    public void printMatrix() throws Exception {

    }

    @Test
    public void concatenate() throws Exception {
        float[][] a = {{1,2}, {3,4}};
        float[][] b = {{5,4},{9,0}};

        float[] ap = Matrix.ravel(a);
        float[] bp = Matrix.ravel(b);

        float[] p = Matrix.concatenate(ap, bp);
        float[] result = {1,2,3,4,5,4,9,0};
        assertTrue(Arrays.equals(result, p));
    }

    @Test
    public void ravel() throws Exception {
        float[][] a = {{1,2}, {3,4}};

        float[] c = Matrix.ravel(a);
        float[] result = {1,2,3,4};

        assertTrue(Arrays.equals(result, c));
    }

}