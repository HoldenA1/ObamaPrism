package obama.prism.engine.graphics;

import java.awt.*;
import java.awt.image.*;

/**
 * Screen will contain all of the code having to do with rendering pixels on the screen
 * @author holden
 */
public class Screen extends Canvas {

	private static final long serialVersionUID = 2L;
	
	private int[] pixels;
	private int width, height;
	
	private Graphics graphics;
	private BufferedImage bufferedImage;
	
	public Render render;
	
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
		
		render = new Render(width, height);
		
		// For frame rate counter
		lastTime = System.nanoTime();
	}
	
	public void update() {
		render.clear();
	}
	
	public void render() {
		Point mouse = getMousePosition();
		
		int x1 = 0;
		int y1 = 0;
		if (mouse != null) {
			x1 = (int) mouse.getX();
			y1 = (int) mouse.getY();
		}
		
		setPixels(render.plotLine(0, 0, x1, y1));
		
		renderGraphics(bufferedImage);
	}
	
	private void renderGraphics(BufferedImage image) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		// Create graphics context
		graphics = bs.getDrawGraphics();
		// Clears screen
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, width, height);
		// Draw the frame
		graphics.drawImage(image, 0, 0, width, height, null);
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
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void setPixels(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}

}
