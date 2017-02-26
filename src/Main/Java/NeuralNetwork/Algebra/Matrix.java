package NeuralNetwork.Algebra;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * The Class {@code Matrix} contains methods for performing basic
 * operations on matrices such as multiplication between matrices,
 * scalat multiplication, sum and transpose transformation.
 *
 * @author Bruno De Luca
 */

public class Matrix {

    /**
     * Returns the result of multiplying two matrices. If the number of columns of the
     * first matrix don't match the number of rows of the second matrix, it throws and
     * exception.
     * @param a the fist matrix
     * @param b the second matrix
     * @return result of both matrices multiplied.
     */

    public static float[][] multiply(float[][] a, float[][] b){
        int aRow = a.length;
        int aColumn = a[0].length;
        int bRow = b.length;
        int bColumn = b[0].length;

        if(aColumn != bRow) throw new IllegalArgumentException("A:Rows: " + aRow + " don't match B:Columns " + aColumn + ".");

        float[][] c = new float[aRow][bColumn];

        for(int i = 0; i < aRow; i++){
            for(int j = 0; j < bColumn; j++){
                for(int k = 0; k < aColumn; k++){
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    /**
     * Returns the result of multiplying a number with a matrix
     * @param a the matrix to be multiplied
     * @param b the number
     * @return the result of multiplying the {@param b} number with the {@param a} matrix
     */

    public static float[][] multiply(float a, float[][] b){
        int aRow = b.length;
        int aColumn = b[0].length;

        float[][] c = new float[aRow][aColumn];

        for(int i = 0; i < aRow; i++){
            for(int j = 0; j < aColumn; j++){
                c[i][j] = b[i][j]*a;
            }
        }

        return c;
    }

    /**
     * Returns a matrix with the result of scaling multiplying both matrices
     * @param a fist matrix
     * @param b second matrix
     * @return a new matrix
     */

    public static float[][] scalarMultiply(float[][] a, float[][] b){
        float[] aVec = toVector(a);
        float[] bVec = toVector(b);

        float[][] c = new float[aVec.length][1];

        for(int i = 0; i < aVec.length; i++){
            c[i][0] = aVec[i]*bVec[i];
        }

        return c;
    }

    /**
     * Returns a matrix transformed into a vector. If has more than one dimension
     * it throws an exception. It transposes the matrix so it is always columned.
     * @param x the matrix
     * @return a vector
     */


    public static float[] toVector(float[][] x){
        if((x[0].length > 1) && (x.length > 1)) throw new IllegalArgumentException("Invalid matrix");
        if(x[0].length > 1) x = transpose(x);

        int xRow = x.length;

        float[] c = new float[xRow];

        for(int i = 0; i < xRow; i++){
            c[i] = x[i][0];
        }

        return c;
    }

    /**
     * Returns the sum of two matrices. If the rows and columns of both matrices don't
     * match, it throws an exception.
     * @param a the fist matrix
     * @param b the second matrix
     * @return the sum of both matrices.
     */

    public static float[][] sum(float[][] a, float[][] b){
        int aRow = a.length;
        int aColumn = a[0].length;
        int bRow = b.length;
        int bColumn = b[0].length;

        if((aRow != bRow) || (aColumn != bColumn)) throw new IllegalArgumentException("Both matrix should have the same size");

        float[][] c = new float[aRow][aColumn];

        for(int i = 0; i < aRow; i++){
            for(int j = 0; j < aColumn; j++){
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }



    /**
     * Returns the transpose transformation of a matrix.
     * @param a the matrix to be transpose
     * @return the transpose transformation of the matrix {@param a}
     */

    public static float[][] transpose(float[][] a){
        int aRow = a.length;
        int aColumn = a[0].length;

        float[][] c = new float[aColumn][aRow];

        for(int i = 0; i < aRow; i++){
            for(int j = 0; j < aColumn; j++){
                c[j][i] = a[i][j];
            }
        }
        return c;
    }

    /**
     * Prints matrix
     * @param z the matrix to print
     */

    public static void printMatrix(float[][] z){
        Stream.of(z).map(Arrays::toString).forEach(System.out::println);
    }

}
