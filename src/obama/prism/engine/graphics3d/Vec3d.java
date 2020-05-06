package obama.prism.engine.graphics3d;

/**
 * Vec (vector) is a data structure that stores a 3D point
 * @author holden
 */
public class Vec3d {
	
	public float x, y, z;
	
	public Vec3d(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vec3d copy(Vec3d v) {
		return new Vec3d(
				v.x,
				v.y,
				v.z
			);
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x*x + y*y + z*z);
	}
	
	public void normalize() {
		float mag = this.magnitude();
		this.x /= mag;
		this.y /= mag;
		this.z /= mag;
	}
	
	public void add(float scalar) {
		this.x += scalar;
		this.y += scalar;
		this.z += scalar;
	}
	
	public void sub(float scalar) {
		this.x -= scalar;
		this.y -= scalar;
		this.z -= scalar;
	}
	
	public void scale(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

}
