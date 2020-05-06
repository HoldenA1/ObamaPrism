package obama.prism.engine.graphics3d;

public class Model {
	
	private Vec3d[] vertices;
	private int[] indexBuffer;
	
	public Model(Vec3d[] vertices, int[] indexBuffer) {
		this.vertices = vertices;
		this.indexBuffer = indexBuffer;
	}
	
	public Vec3d[] getVertices() {
		return vertices;
	}
	
	public int[] getIndexBuffer() {
		return indexBuffer;
	}

}
