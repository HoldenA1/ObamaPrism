package obama.prism.engine.graphics3d;

public class SpecialMatrices {
	
	public static float[][] createProjectionMatrix(float fovDegrees, float aspectRatio, float far, float near) {
		float fovRad = (float)(1.0f / Math.tan(Math.toRadians(fovDegrees) * 0.5));
		float[][] m = new float[4][4];
		m[0][0] = aspectRatio * fovRad;
		m[1][1] = fovRad;
		m[2][2] = far / (far - near);
		m[3][2] = (-far * near) / (far - near);
		m[2][3] = 1.0f;
		m[3][3] = 0.0f;
		return m;
	}
	
	public static float[][] createRotationXMatrix(float angleRad) {
		float[][] m = new float[4][4];
		m[0][0] = 1.0f;
		m[1][1] = (float) Math.cos(angleRad);
		m[1][2] = (float) Math.sin(angleRad);
		m[2][1] = (float) -Math.sin(angleRad);
		m[2][2] = (float) Math.cos(angleRad);
		m[3][3] = 1.0f;
		return m;
	}
	
	public static float[][] createRotationYMatrix(float angleRad) {
		float[][] m = new float[4][4];
		m[0][0] = (float) Math.cos(angleRad);
		m[0][2] = (float) Math.sin(angleRad);
		m[2][0] = (float) -Math.sin(angleRad);
		m[1][1] = 1.0f;
		m[2][2] = (float) Math.cos(angleRad);
		m[3][3] = 1.0f;
		return m;
	}
	
	public static float[][] createRotationZMatrix(float angleRad) {
		float[][] m = new float[4][4];
		m[0][0] = (float) Math.cos(angleRad);
		m[0][1] = (float) Math.sin(angleRad);
		m[1][0] = (float) -Math.sin(angleRad);
		m[1][1] = (float) Math.cos(angleRad);
		m[2][2] = 1.0f;
		m[3][3] = 1.0f;
		return m;
	}

}
