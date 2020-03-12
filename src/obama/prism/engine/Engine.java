package obama.prism.engine;

import obama.prism.engine.graphics.Screen;
import obama.prism.engine.graphics.Window;
import obama.prism.engine.graphics3d.Transformer;

public class Engine {
	
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final String TITLE = "Obama Prism Render Engine";
	
//	private static Vertex[] triangle = new Vertex[3];

	private Window frame;
	private Screen screen;
	
	private Transformer transform;
	private int velocity = 2;
	private int numVecs = 5;
	private static double theta = 0;
	
	public Engine() {
		frame = new Window(TITLE, WIDTH, HEIGHT);
		screen = new Screen(WIDTH, HEIGHT);
		frame.add(screen);
		
//		triangle[0] = new Vertex(-0.5f, -0.5f, 0);
//		triangle[1] = new Vertex(0.5f, -0.5f, 0);
//		triangle[2] = new Vertex(0.5f, 0.5f, 0);
		
		transform = new Transformer(numVecs, WIDTH, HEIGHT);
		
		transform.add(0, 0, 125, 0); //num, x, y, z
		transform.add(1, -100, -60, -100);
		transform.add(2, -100, -60, 100);
		transform.add(3, 100, -60, 100);
		transform.add(4, 100, -60, -100);
	}
	
	/**
	 * Loop this method to run the engine
	 */
	public void run() {
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
	
	/**
	 * Update is where you draw things. It is run before the pixels are rendered onto the screen.
	 */
	private void update() {
		theta += velocity / 100.0;
		transform.setMatrix(theta);
		transform.transformVecs();
		transform.paintVectors(screen);
	}
	
	/**
	 * This is where the pixels are actually drawn on the screen
	 */
	private void render() {
		screen.render();
	}

}
