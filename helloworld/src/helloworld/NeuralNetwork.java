package helloworld;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class NeuralNetwork {

	private int hiddenSize, inputSize, outputSize, iters;
	private double[][] weightsItoH;
	private double[][] weightsHtoO;
	private double[] ah;
	private double[] ai;
	private double[] ao;
	private double LEARNING_RATE;
	private static final double E = 0.001;

	public NeuralNetwork(double learningRate, int inputSize, int hiddenSize, int outputSize) {
		defaultInit(learningRate, inputSize, hiddenSize, outputSize);
	}

	public NeuralNetwork(String filename, double defLr, int defInSize, int defHiSize, int defOutSize)
			throws FileNotFoundException {
		File file = new File(filename);
		Scanner in = new Scanner(file);
		try {
			this.LEARNING_RATE = in.nextDouble();
			this.inputSize     = in.nextInt();
			this.hiddenSize    = in.nextInt();
			this.outputSize    = in.nextInt();
		} catch (Exception e) {
			in.close();
			defaultInit(defLr, defInSize, defHiSize, defOutSize);
			return;
		}
		init();
		loadWeights(in, this.LEARNING_RATE, this.inputSize, this.hiddenSize, this.outputSize);
		in.close();
	}


	private void init() {
		this.weightsItoH = new double[this.inputSize][this.hiddenSize];
		this.weightsHtoO = new double[this.hiddenSize][this.outputSize];
		this.ai 		 = new double[this.inputSize];
		this.ah 		 = new double[this.hiddenSize];
		this.ao 		 = new double[this.outputSize];
		ah[this.hiddenSize - 1]   = 1.0; // Bias units
		ai[this.inputSize  - 1]   = 1.0;
		iters = 0;
	}
	
	
	private void defaultInit(double learningRate, int inputSize, int hiddenSize, int outputSize) {
		this.LEARNING_RATE = learningRate;
		this.inputSize   = inputSize + 1;
		this.hiddenSize  = hiddenSize + 1;
		this.outputSize  = outputSize;
		init();
		randomizeWeights();
	}
	/* method is used to load the weights for checking similarity */
	public void loadWeights(String filename) {
		try {
			Scanner in = new Scanner(new File(filename));
			double lr = in.nextDouble();
			int inSize = in.nextInt();
			int hiSize = in.nextInt();
			int outSize = in.nextInt();
			loadWeights(in, lr, inSize, hiSize, outSize);
			in.close();
		} catch (Exception e) {
			randomizeWeights();
		}
	}
	


	private void loadWeights(Scanner in, double lr, int inSize, int hiSize, int outSize) {
		if (lr != LEARNING_RATE || inputSize != inSize || hiSize != hiddenSize || outSize != outputSize) {
			randomizeWeights();
			return;
		}
		for (int i = 0; i < inputSize; i++)
			for (int j = 0; j < hiddenSize; j++)
				weightsItoH[i][j] = in.nextDouble();
		for (int j = 0; j < hiddenSize; j++)
			for (int k = 0; k < outputSize; k++)
			weightsHtoO[j][k] = in.nextDouble();
	}

/* saves the current numbers */
	public void saveWeights(String filename) throws IOException {
		FileWriter f = new FileWriter(new File(filename));
		f.write(LEARNING_RATE + " " + inputSize + " " + hiddenSize + " " + outputSize + "\n");
		for (int i = 0; i < inputSize; i++)
			for (int j = 0; j < hiddenSize; j++)
				f.write(String.format("%f\n", weightsItoH[i][j]));
		for (int j = 0; j < hiddenSize; j++)
			for (int k = 0; k < outputSize; k++)
				f.write(String.format("%f\n", weightsHtoO[j][k]));
		f.close();
	}

 /*randomizes the numbers*/
	public void randomizeWeights() {
		for (int i = 0; i < inputSize; i++)
			for (int j = 0; j < hiddenSize; j++)
				weightsItoH[i][j] = rand(-1.0, 1.0);
		for (int j = 0; j < hiddenSize; j++)
			for (int k = 0; k < outputSize; k++)
				weightsHtoO[j][k] = rand(-1.0, 1.0);
	}

	
	private double sigmoid(double x) {
		return 1./(1 + Math.exp(-x));
		
	}

	
	private double dSigmoid(double y) {
		return y * (1 - y);
	
	}

	
	private double rand(double a, double b) {
		return a + (b - a) * Math.random();
	}

	
	private void forwardPropagation(int[] inputs) {
		for (int i = 0; i < inputSize - 1; i++)
			ai[i] = inputs[i];

		for (int j = 0; j < hiddenSize - 1; j++) {
			ah[j] = 0.0;
			for (int i = 0; i < inputSize; i++)
				ah[j] += weightsItoH[i][j] * ai[i];
			ah[j] = sigmoid(ah[j]);
		}

		for (int k = 0; k < outputSize; k++) {
			ao[k] = 0.0;
			for (int j = 0; j < hiddenSize; j++)
				ao[k] += ah[j] * weightsHtoO[j][k];
			ao[k] = sigmoid(ao[k]);
		}
	}

	
	private void backPropagation(double[] errors) {
		double[] deltak = new double[outputSize];
		for (int k = 0; k < outputSize; k++)
			deltak[k] = dSigmoid(ao[k]) * errors[k];
		
		double[] deltaj = new double[hiddenSize];
		for (int j = 0; j < hiddenSize; j++)
			for (int k = 0; k < outputSize; k++)
				deltaj[j] += dSigmoid(ah[j]) * deltak[k] * weightsHtoO[j][k];

		for (int i = 0; i < inputSize; i++)
			for (int j = 0; j < hiddenSize; j++)
				weightsItoH[i][j] += LEARNING_RATE * deltaj[j] * ai[i];

		for (int j = 0; j < hiddenSize; j++)
			for (int k = 0; k < outputSize; k++)
				weightsHtoO[j][k] += LEARNING_RATE * deltak[k] * ah[j];
	}


	public void train(int[][] inputs, int[][] outputs, int iterLimit) {
		for (int c = 0; c < iterLimit; c++, iters++)
			for (int i = 0; i < inputs.length; i++) {
				forwardPropagation(inputs[i]);
				double[] errors = new double[outputSize];
				for (int k = 0; k < outputSize; k++)
					errors[k] = outputs[i][k] - ao[k];
				backPropagation(errors);
			}
	}
	

	public int eval(int[] pattern) {
		forwardPropagation(pattern);
		return interpret();
	}
	
	
	private int interpret() {
		if (outputSize == 1) return (ao[0] < 0.5)? 0 : 1;
		int index = 0;
		double max = ao[0];
		for (int k = 1; k < outputSize; k++)
			if (ao[k] > max) {
				max = ao[k]; index = k;
			}
		return index;
	}
	

	private int maxIndex(int[] pattern) {
		int index = 0;
		double max = pattern[0];
		for (int k = 1; k < outputSize; k++)
			if (pattern[k] > max) {
				max = pattern[k]; index = k;
			}
		return index;
	}


	public double[] test(int[][] inputs, int[][] outputs, boolean print) {
		double[] r = {0.0, 0.0};
		System.out.println("Iterations: " + iters);
		for (int i = 0; i < inputs.length; i++) {
			int x = eval(inputs[i]);
			int expected = maxIndex(outputs[i]);
			if (print) System.out.println("Expected: " + expected + " " + Arrays.toString(outputs[i]) +
										  " Result: " + x + " " + Arrays.toString(ao));
			for (int k = 0; k < outputSize; k++)
				r[1] += (outputs[i][k] - ao[k]) * (outputs[i][k] - ao[k]);
			if (expected == x) r[0] += 1.0/inputs.length;
			r[1] += (expected - x)*(expected - x)/(double)inputs.length;
		}
		r[1] *= 0.5;
		if (print) {
			
		}
		return r;
	}
	
	
	public int iters() {
		return iters;
	}
}