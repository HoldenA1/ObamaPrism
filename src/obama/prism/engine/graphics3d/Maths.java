package obama.prism.engine.graphics3d;

public class Maths {
	
	public static Vec3d add(Vec3d v1, Vec3d v2) {
		return new Vec3d(
				v1.x + v2.x,
				v1.y + v2.y,
				v1.z + v2.z
			);
	}
	
	public static Vec3d sub(Vec3d v1, Vec3d v2) {
		return new Vec3d(
				v1.x - v2.x,
				v1.y - v2.y,
				v1.z - v2.z
			);
	}
	
	public static float dotProduct(Vec3d v1, Vec3d v2) {
		return (v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z);
	}
	
	public static Vec3d crossProduct(Vec3d v1, Vec3d v2) {
		return new Vec3d(
				(v1.y * v2.z) - (v1.z * v2.y),
				(v1.z * v2.x) - (v1.x * v2.z),
				(v1.x * v2.y) - (v1.y * v2.x)
			);
	}
	
	public static Vec3d multMatVec(Mat4x4 m, Vec3d v) {
		Vec3d out = new Vec3d(0, 0, 0);
		out.x = v.x * m.mat[0][0] + v.y * m.mat[1][0] + v.z * m.mat[2][0] + m.mat[3][0];
		out.y = v.x * m.mat[0][1] + v.y * m.mat[1][1] + v.z * m.mat[2][1] + m.mat[3][1];
		out.z = v.x * m.mat[0][2] + v.y * m.mat[1][2] + v.z * m.mat[2][2] + m.mat[3][2];
		float w = v.x * m.mat[0][3] + v.y * m.mat[1][3] + v.z * m.mat[2][3] + m.mat[3][3];
		if (w != 0.0f) {
			out.x /= w;
			out.y /= w;
			out.z /= w;
		}
		return out;
	}

}
