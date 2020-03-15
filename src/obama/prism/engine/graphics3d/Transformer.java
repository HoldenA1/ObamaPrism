package obama.prism.engine.graphics3d;

import obama.prism.engine.graphics.Screen;

public class Transformer {
	
	private int width, height;
	private double[][] matrix;
	private int[][] inputs, outputs;
	private int numVectors;

	public Transformer (int n, int w, int h) {
		matrix = new double[2][3];
		
		inputs = new int[n][3];
		outputs = new int[n][2];
		numVectors = n;
		width = w;
		height = h;
    }

	public void add (int n, int x, int y, int z) {
		inputs[n][0] = x; inputs[n][1] = y; inputs[n][2] = z;
		outputs[n][0] = x; outputs[n][1] = y;
	}

	public void setMatrix (double theta) {
		matrix[0][0] = Math.cos(theta);
		matrix[1][0] = 0;
		matrix[0][1] = 0;
		matrix[1][1] = 1;
		matrix[0][2] = Math.sin(theta);
		matrix[1][2] = 0;
	}
	
	public void transformVecs() {
		for (int i = 0; i < numVectors; i++) {
			transform(inputs[i], outputs[i]);
		}
	}

	public void transform (int[] vector, int[] output) {
		int sum = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				sum += matrix[i][j] * vector[j];
			}
			output[i] = sum;
			sum = 0;
		}
	}

	public void paintVectors (Screen page) {
		for (int i = 0; i < numVectors; i++) {
			//center
			page.drawLine(width / 2, height / 2, 
			width / 2 + outputs[i][0], height / 2 - outputs[i][1]);

			//tip
			page.drawLine(width / 2 + outputs[0][0], height / 2 - outputs[0][1], 
			width / 2 + outputs[i][0], height / 2 - outputs[i][1]);

			//base
			page.drawLine(width / 2 + outputs[i][0], height / 2 - outputs[i][1],
			width / 2 + outputs[(i+1)%numVectors][0], height / 2 - outputs[(i+1)%numVectors][1]);
		} 
	}
}
