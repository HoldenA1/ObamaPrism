package obama.prism.engine.graphics3d;

public class Mat4x4 {
	
	public float[][] mat;
	
	public Mat4x4() {
		mat = new float[4][4];
	}
	
	public void set(float[][] mat) {
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				try {
					this.mat[r][c] = mat[r][c];
				} catch (ArrayIndexOutOfBoundsException e) {
					System.err.println("Matrix must be 4x4!");
				}
			}
		}
	}

}
