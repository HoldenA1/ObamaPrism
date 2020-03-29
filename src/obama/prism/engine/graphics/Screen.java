package obama.prism.engine.graphics;

import java.awt.*;
import java.awt.image.*;

import obama.prism.engine.graphics3d.Triangle;

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
		
		// For frame rate counter
		lastTime = System.nanoTime();
	}
	
	public void drawLine(int x0, int y0, int x1, int y1, Color color) {
		Point[] line = LineDrawer.getLine(x0, y0, x1, y1);
		
		for (Point p: line) {
			pixels[p.x + p.y * width] = color.getRGB();
		}
	}
	
	/**
	 * Uses linear interpolation to color the line
	 * @param color0 is the color of the first point
	 * @param color1 is the color of the second point
	 */
	public void drawLine(int x0, int y0, int x1, int y1, Color color0, Color color1) {
		Point[] line = LineDrawer.getLine(x0, y0, x1, y1);
		
		Color[] colors = LineDrawer.interpolateColors(line, color0, color1);
		
		for (int i = 0; i < line.length; i++) {
			Point p = line[i];
			pixels[p.x + p.y * width] = colors[i].getRGB();
		}
	}
	
	public void drawTriangle(Triangle triangle) {
		triangle.draw(this);
	}
	
	public void fillTriangle(Triangle triangle) {
		triangle.fill(this);
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
