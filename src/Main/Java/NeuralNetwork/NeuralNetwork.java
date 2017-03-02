package NeuralNetwork;

import java.util.Random;
import NeuralNetwork.Algebra.Matrix;

/**
 * An implementation of a basic Neural Network
 * that takes two inputs and outputs a result.
 * It can be used in different scenarios such as
 * guessing the result of a test based on the
 * amount of sleep time and study time.
 * @author Bruno De Luca
 */

public class NeuralNetwork {

    /**
     * The number of inputs, hidden neurons and the number of outputs
     */

    private int inputLayerSize;
    private int hiddenLayerSize;
    private int outputLayerSize;

    /**
     * The weighs which will change the values of out input to get the final result
     */

    private float[][] w1;
    private float[][] w2;

    /**
     * Matrices being transformed and used for different methods
     */

    private float[][] z3;
    private float[][] z1;
    private float[][] a2;

    /**
     * Constructor. It sets the fields and generates random values for the wight matrices
     * @param inputLayerSize the number of inputs
     * @param hiddenLayerSize number of neurons hidden
     * @param outputLayerSize number of outputs
     */

    public NeuralNetwork(int inputLayerSize, int hiddenLayerSize, int outputLayerSize){
        this.inputLayerSize = inputLayerSize;
        this.hiddenLayerSize = hiddenLayerSize;
        this.outputLayerSize = outputLayerSize;
        this.w1 = generateRandomParameters(inputLayerSize, hiddenLayerSize);
        this.w2 = generateRandomParameters(hiddenLayerSize, outputLayerSize);
    }

    /**
     * Returns a matrix with random weights. it is intended to be instanced once
     * @param row number of rows of the matrix
     * @param col number of columns of the matrix
     * @return a matrix with random weights
     */

    private float[][] generateRandomParameters(int row, int col){
        float[][] w = new float[row][col];
        for(int i = 0; i < w.length; i++){
            for(int j = 0; j < w[i].length; j++){
                w[i][j] = (new Random()).nextFloat();
            }
        }
        return w;
    }

    /**
     * it returns the matrix with the outputs. For each input, it is multiplied by its weight.
     * Then it passes though a sigmoid function to normalize the values and finally
     * it is multiplied by another weight parameter and normalized again.
     * @param X the matrix with the inputs
     * @return the matrix with the final results
     */

    public float[][] forward(float[][] X){
        this.z1 = Matrix.multiply(X, this.w1);
        this.a2 = sigmoidOperation(z1);
        this.z3 = Matrix.multiply(a2, this.w2);
        return sigmoidOperation(z3);
    }

    /**
     * Returns a matrix transformed by the sigmoid function
     * @param z the matrix to be transformed
     * @return a matrix with its values being transformed by the sigmoid function
     */

    private float[][] sigmoidOperation(float[][] z){
        for(int i = 0; i < z.length; i++){
            for(int j = 0; j < z[i].length; j++){
                z[i][j] = sigmoid(z[i][j]);
            }
        }
        return z;
    }

    /**
     * Returns a matrix transformed by the derivative of the sigmoid function
     * @param z the matrix to be transformed
     * @return a matrix with its values being transformed by the derivative of the sigmoid function
     */

    private float[][] sigmoidPrimeOperation(float[][] z){
        for(int i = 0; i < z.length; i++){
            for(int j = 0; j < z[i].length; j++){
                z[i][j] = sigmoidPrime(z[i][j]);
            }
        }
        return z;
    }

    /**
     * The sigmoid function
     * @param z the initial value
     * @return the transformation of the initial value
     */

    private float sigmoid(float z){
        return (float) (1 / (1 + Math.exp(-z)));
    }

    /**
     * The derivative of the sigmoid function
     * @param z the initial value
     * @return the transformation of the inital value
     */

    private float sigmoidPrime(float z) { return (float) (Math.exp(-z)/Math.pow(1 + Math.exp(-z),2));}

    public float backpropagation(float[][] X, float[][] y){
        float[][] yHat = forward(X);

        int row = yHat.length;
        int col = yHat[0].length;

        float result = 0;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                result += costFunction(y[i][j], yHat[i][j]);
            }
        }

        return result;
    }

    /**
     * Returns a matrix with the costs given the initial values and the final result
     *
     * J = ∑ 0.5 * (y - y')^2
     *
     * @param y matrix with the initial values
     * @param yHat matrix with the original results
     * @return matrix with the values of each cost
     */

    public float costFunction(float y, float yHat){
        return (float) (0.5f*Math.pow(y - yHat, 2));
    }

    /**
     * Returns a matrix with the costs  respect to W1 and W2 given the initial values and the final result
     *
     *  dj/dW2 = (a2)^T * ẟ3
     *  ẟ3 = -(y - y) * ƒ'(z3)
     *
     *  dj/dW1 = X^T * ẟ2
     *  ẟ2 = ẟ3 * (W2)^T * ƒ'(z2)
     *
     * @param X matrix with the initial values
     * @param y matrix with the original results
     * @return matrix with the values of each cost
     */

    public float[][][] costFunctionPrime(float[][] X, float[][] y){
        float[][] yHat = forward(X);

        float[][] delta3 = Matrix.scalarMultiply(Matrix.multiply(-1,Matrix.sum(y, Matrix.multiply(-1, yHat))), sigmoidPrimeOperation(z3));
        float[][] a2Transpose = Matrix.transpose(a2);
        float[][] d3 = Matrix.multiply(a2Transpose, delta3);

        float[][] delta2 = Matrix.multiply(delta3, Matrix.multiply(Matrix.transpose(w2), sigmoidPrimeOperation(z1)));
        float[][] d2 = Matrix.multiply(Matrix.transpose(X), delta2);

        float[][][] c = {d2, d3};
        return c;
    }

    public float[] getParams(){
        return Matrix.concatenate(Matrix.ravel(w1), Matrix.ravel(w2));
    }

    public void setParams(float[] params){
        float[][] w1 = new float[2][3];

        int count = 0;

        for(int i = 0; i < w1.length; i++){
            for(int j = 0; j < w1[0].length; j++){
                w1[i][j] = params[count];
                count++;
            }
        }

        this.w1 = w1;

        float[][] w2 = new float[3][1];

        for(int i = 0; i < w2.length; i++){
            for(int j = 0; j < w2[0].length; j++){
                w2[i][j] = params[count];
            }
        }

        this.w2 = w2;
    }

}