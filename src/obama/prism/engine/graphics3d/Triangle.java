package obama.prism.engine.graphics3d;

import obama.prism.engine.graphics.Screen;

public class Triangle {
	
	private Vertex[] vertices;
	
	public Triangle(Vertex v1, Vertex v2, Vertex v3) {
		vertices = new Vertex[3];
		
		vertices[0] = v1;
		vertices[1] = v2;
		vertices[2] = v3;
	}
	
	public Triangle() {
		this(
			new Vertex(0, 0, 0),
			new Vertex(0, 0, 0),
			new Vertex(0, 0, 0)
		);
	}
	
	public void draw(Screen screen) {
		drawEdge(vertices[0], vertices[1], screen);
		drawEdge(vertices[1], vertices[2], screen);
		drawEdge(vertices[2], vertices[0], screen);
	}
	
	private void drawEdge(Vertex v1, Vertex v2, Screen screen) {
		int v1x = (int) v1.getX();
		int v1y = (int) v1.getY();
		int v2x = (int) v2.getX();
		int v2y = (int) v2.getY();
		screen.drawLine(v1x, v1y, v2x, v2y);
	}
	
	public void setVertex(int vertexNum, Vertex vert) {
		vertices[vertexNum] = vert;
	}
	
	public void setVertices(Vertex[] vertices) {
		if (vertices.length == 3) {
			this.vertices = vertices;
		} else {
			System.err.println("Triangles have three vertices, mate");
		}
	}

}
