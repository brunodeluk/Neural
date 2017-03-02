package NeuralNetwork;

import NeuralNetwork.Algebra.Matrix;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by brunodeluca on 2/26/17.
 */
public class NeuralNetworkTest {

    @Test
    public void forward() throws Exception {
        NeuralNetwork nn = new NeuralNetwork(2,3,1);
        float[][] X = {{3,5}, {2,7}, {4,8}};
        float[][] y = {{0.75f}, {0.85f}, {0.90f}};

        float[][] yHat = nn.forward(X);
        Matrix.printMatrix(yHat);

        System.out.println();

        System.out.println(nn.backpropagation(X, y));

        float[][][] costPrime = nn.costFunctionPrime(X, y);
        Matrix.printMatrix(costPrime[0]);
        System.out.println();
        Matrix.printMatrix(costPrime[1]);
    }

    @Test
    public void numericGradientChecking() throws Exception {
        NeuralNetwork nn = new NeuralNetwork(2,3,1);
        float[][] X = {{3,5}, {2,7}, {4,8}};
        float[][] y = {{0.75f}, {0.85f}, {0.90f}};

        float[][][] costPrime = nn.costFunctionPrime(X,y);
        float[][] w1 = costPrime[0];
        float[][] w2 = costPrime[1];

        float[] concatenate = Matrix.concatenate(Matrix.ravel(w1), Matrix.ravel(w2));

        //---------------------------

        float[] numgrad = new float[9];
        float[] pertub = new float[9];
        float[] params = nn.getParams();
        float epsilon = (float) Math.pow(10, -4);

        for(int i = 0; i < params.length; i++){

        }
    }

}