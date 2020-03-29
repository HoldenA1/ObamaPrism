package obama.prism.engine;

import java.awt.Color;

import obama.prism.engine.graphics.Screen;
import obama.prism.engine.graphics.Window;
import obama.prism.engine.graphics3d.Transformer;
import obama.prism.engine.graphics3d.Triangle;
import obama.prism.engine.graphics3d.Vec;
import obama.prism.engine.sound.Player;

/**
 * Engine contains the central logic for the rendering engine
 * @author holden
 */
public class Engine {
	
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final String TITLE = "Obama Prism Render Engine";

	private Window frame;
	private Screen screen;
	private Player player;
	
	private Transformer transform;
	private int velocity = 2;
	private int numVecs = 5;
	private double theta = 0;
	
	private boolean musicOn = false;
	
	Triangle t1, t2;
	
	public Engine() {
		frame = new Window(TITLE, WIDTH, HEIGHT);
		screen = new Screen(WIDTH, HEIGHT);
		frame.add(screen);
		
//		transform = new Transformer(numVecs, WIDTH, HEIGHT);
//		
//		transform.add(0, 0, 125, 0); //num, x, y, z
//		transform.add(1, -100, -60, -100);
//		transform.add(2, -100, -60, 100);
//		transform.add(3, 100, -60, 100);
//		transform.add(4, 100, -60, -100);
		
		t1 = new Triangle(
			new Vec(WIDTH * 0.25f, HEIGHT * 0.75f, 0),
			new Vec(WIDTH * 0.75f, HEIGHT * 0.75f, 0),
			new Vec(WIDTH * 0.75f, HEIGHT * 0.25f, 0),
			Color.cyan
		);
		
		t2 = new Triangle(
			new Vec(WIDTH * 0.25f, HEIGHT * 0.75f, 0),
			new Vec(WIDTH * 0.75f, HEIGHT * 0.25f, 0),
			new Vec(WIDTH * 0.25f, HEIGHT * 0.25f, 0),
			Color.magenta
		);
		
		if (musicOn) {
			player = new Player();
			player.play("/music/obama.wav");
		}
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
//		theta += velocity / 100.0;
//		transform.setMatrix(theta);
//		transform.transformVecs();
//		transform.paintVectors(screen);
		
		screen.fillTriangle(t1);
		screen.fillTriangle(t2);
	}
	
	/**
	 * This is where the pixels are actually drawn on the screen
	 */
	private void render() {
		screen.render();
	}

}
