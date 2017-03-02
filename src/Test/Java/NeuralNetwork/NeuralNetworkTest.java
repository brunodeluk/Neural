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
        float[] paramsInit = nn.getParams();
        float epsilon = (float) Math.pow(10, -4);

        for(int i = 0; i < 9; i++){
            pertub[i] = epsilon;
            nn.setParams(Matrix.sum(paramsInit, pertub));
            float loss2 = nn.backpropagation(X, y);

            nn.setParams(Matrix.sum(paramsInit, Matrix.multiply(-1, pertub)));
            float loss1 = nn.backpropagation(X, y);

            numgrad[i] = ((loss2 - loss1) / (2*epsilon));

            pertub[i] = 0;
        }

        nn.setParams(paramsInit);

        for(int i = 0; i < numgrad.length; i++){
            System.out.println(numgrad[i]);
        }

        System.out.println();

        for(int i = 0; i < numgrad.length; i++){
            System.out.println(concatenate[i]);
        }

        System.out.println();

        System.out.println(norm(Matrix.sum(concatenate,Matrix.multiply(-1,numgrad)))/norm(Matrix.sum(concatenate,numgrad)));
    }


    public float norm(float[] a){
        float result = 0;

        for(float p: a){
            result += Math.pow(p, 2);
        }

        return (float) Math.sqrt(result);
    }

}