package obama.prism.engine.graphics3d;

public class Model {
	
	private Vec[] vertices;
	private int[] indexBuffer;
	
	public Model(Vec[] vertices, int[] indexBuffer) {
		this.vertices = vertices;
		this.indexBuffer = indexBuffer;
	}
	
	public Vec[] getVertices() {
		return vertices;
	}
	
	public int[] getIndexBuffer() {
		return indexBuffer;
	}

}
