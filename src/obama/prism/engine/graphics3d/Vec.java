package obama.prism.engine.graphics3d;

/**
 * Vec (vector) is a data structure that stores a 3D point
 * @author holden
 */
public class Vec {
	
	public float x, y, z;
	
	public Vec(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @param addend
	 * @return the sum of the two vectors
	 */
	public Vec add(Vec vector) {
		return new Vec(
				x + vector.x,
				y + vector.y,
				z + vector.z
			);
	}
	
	/**
	 * @param subtrahend
	 * @return the difference of the two vectors
	 */
	public Vec sub(Vec vector) {
		return new Vec(
				x - vector.x,
				y - vector.y,
				z - vector.z
			);
	}
	
	/**
	 * 
	 * @param multiplier scalar value
	 * @return the product of the vector and scalar
	 */
	public Vec mult(float scalar) {
		return new Vec(
				x * scalar,
				y * scalar,
				z * scalar
			);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

}
