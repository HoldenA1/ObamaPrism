package obama.prism.engine;

import java.awt.Point;

import obama.prism.engine.graphics.*;

public class Main {
	
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final String TITLE = "Obama Prism Render Engine";
	
//	private static Vertex[] triangle = new Vertex[3];

	private static Window frame;
	private static Screen screen;

	public static void main(String[] args) {
		
		frame = new Window(TITLE, WIDTH, HEIGHT);
		screen = new Screen(WIDTH, HEIGHT);
		frame.add(screen);
		
//		triangle[0] = new Vertex(-0.5f, -0.5f, 0);
//		triangle[1] = new Vertex(0.5f, -0.5f, 0);
//		triangle[2] = new Vertex(0.5f, 0.5f, 0);
		
		while (true) {			
			update();
			render();
			
			screen.clear();
			
			// An attempt to not turn your computer into a toaster
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Update is where you draw things. It is run before the pixels are rendered onto the screen.
	 */
	private static void update() {
		Point mouse = screen.getMousePosition();
		
		int x1 = 0;
		int y1 = 0;
		if (mouse != null) {
			x1 = (int) mouse.getX();
			y1 = (int) mouse.getY();
		}
		
		screen.drawLine(0, 0, x1, y1);
	}
	
	/**
	 * This is where the pixels are actually drawn on the screen
	 */
	private static void render() {
		screen.render();
	}

}
