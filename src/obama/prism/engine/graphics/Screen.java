package obama.prism.engine.graphics;

import java.awt.*;
import java.awt.image.*;

import obama.prism.engine.graphics3d.Mat4x4;
import obama.prism.engine.graphics3d.Maths;
import obama.prism.engine.graphics3d.Model;
import obama.prism.engine.graphics3d.SpecialMatrices;
import obama.prism.engine.graphics3d.Vec3d;

/**
 * Screen contains everything to do with basic rendering
 * @author holden
 */
public class Screen extends Canvas {

	private static final long serialVersionUID = 2L;
	
	private int[] pixels;
	private int width, height;
	
	private Graphics graphics;
	private BufferedImage bufferedImage;
	
	private LineDrawer lineDrawer;
	private TriangleDrawer triangleDrawer;
	
	private Mat4x4 projMat, rotXMat, rotZMat;
	private float theta, elapsedTime;
	private double prevTime;
	private Vec3d camera;
	
	private long lastTime;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		
		/*
		 * This part is kind of like black magic. We are pointing this object to the
		 * internal array of pixels within our buffered image. That means when we
		 * change the values in this array, we are actually changing the pixel values
		 * of the buffered image.
		 */
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
		
		lineDrawer = new LineDrawer(this);
		triangleDrawer = new TriangleDrawer(this);
		
		// Projection matrix vars
		float near = 0.1f;
		float far = 1000.0f;
		float fov = 90.0f;
		float aspectRatio = (float)(height) / width;
		projMat = new Mat4x4();
		projMat.set(SpecialMatrices.createProjectionMatrix(fov, aspectRatio, far, near));
		
		rotXMat = new Mat4x4();
		rotZMat = new Mat4x4();
		theta = 0;
		prevTime = System.currentTimeMillis();
		
		camera = new Vec3d(0, 0, 0);
		
		// For frame rate counter
		lastTime = System.nanoTime();
	}
	
	public void drawLine(int x0, int y0, int x1, int y1, Color color) {
		lineDrawer.drawLine(x0, y0, x1, y1, color);
	}
	
	/**
	 * @param color0 is the color of the first point
	 * @param color1 is the color of the second point
	 */
	public void drawLine(int x0, int y0, int x1, int y1, Color color0, Color color1) {
		lineDrawer.drawLine(x0, y0, x1, y1, color0, color1);
	}
	
	public void drawTriangle(Point p0, Point p1, Point p2, Color color) {
		triangleDrawer.draw(p0, p1, p2, color);
	}
	
	public void fillTriangle(Point p0, Point p1, Point p2, Color color) {
		triangleDrawer.fill(p0, p1, p2, color);
	}
	
	public void drawModel(Model model) {
		Vec3d[] vertices = model.getVertices();
		int[] indexBuffer = model.getIndexBuffer();
		
		elapsedTime = (float) ((System.currentTimeMillis() - prevTime) / 1000.0);
		prevTime = System.currentTimeMillis();
		
		theta += elapsedTime;
		
		if (theta >= 2 * Math.PI) {
			theta -= 2 * Math.PI;
		}
		
		rotXMat.mat = SpecialMatrices.createRotationXMatrix(theta);
		rotZMat.mat = SpecialMatrices.createRotationZMatrix(theta);
		
		for (int triangle = 0; triangle < indexBuffer.length / 3; triangle++) {
			Vec3d[] triTranslated = new Vec3d[3];
			Vec3d[] triProjected = new Vec3d[3];
			Point[] finalTri = new Point[3];
			for (int p = 0; p < 3; p++) {
				// Rotate triangle
				Vec3d translated = Vec3d.copy(vertices[indexBuffer[triangle*3 + p]]);
				translated = Maths.multMatVec(rotXMat, translated);
				translated = Maths.multMatVec(rotZMat, translated);
				// Translate triangle backwards
				translated.z += 3.0f;
				
				triTranslated[p] = translated;
			}
			
			// Calculate normal
			Vec3d normal, line1, line2;
			line1 = Maths.sub(triTranslated[1], triTranslated[0]);
			line2 = Maths.sub(triTranslated[2], triTranslated[0]);
			normal = Maths.crossProduct(line1, line2);
			normal.normalize();
			
			// Cull the covered faces
			if (Maths.dotProduct(normal, Maths.sub(triTranslated[0], camera)) < 0.0f) {
				for (int p = 0; p < 3; p++) {
					// Project into 2d space
					triProjected[p] = Maths.multMatVec(projMat, triTranslated[p]);
					// Scale into view
					triProjected[p].add(1.0f);
					triProjected[p].x *= 0.5f * (width-1);
					triProjected[p].y *= 0.5f * (height-1);
					finalTri[p] = new Point((int)triProjected[p].x, (int)triProjected[p].y);
				}
				// Draw triangle
				triangleDrawer.fill(
						finalTri[0],
						finalTri[1],
						finalTri[2],
						Color.white
				);
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		// Create graphics context
		graphics = bs.getDrawGraphics();
		// Draw the frame
		graphics.drawImage(bufferedImage, 0, 0, width, height, null);
		// Draw the frame rate
		graphics.setColor(Color.YELLOW);
		int frameRate = calculateFrameRate();
		graphics.drawString(Integer.toString(frameRate), 10, 20);
		// Dispose of graphics context
		graphics.dispose();
		// Display buffer
		bs.show();
	}
	
	private int calculateFrameRate() {
		long now = System.nanoTime();
		double updateDelta = now - lastTime;
		lastTime = now;
		return (int) (1000000000.0 / updateDelta);
	}
	
	/**
	 * Clears the screen to get rid of the last frame
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	/**
	 * This sets the all pixels on the screen to the
	 * array you pass it
	 */
	public void setPixels(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}
	
	/**
	 * Sets the color of a single pixel
	 * @param x
	 * @param y
	 * @param color should be in hex
	 */
	public void setPixel(int x, int y, int color) {
		pixels[x + y * width] = color;
	}

}
