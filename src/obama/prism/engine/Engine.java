package obama.prism.engine;

import obama.prism.engine.graphics.*;
import obama.prism.engine.graphics3d.*;
import obama.prism.engine.sound.Player;
import obama.prism.engine.util.Reader;

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
	
	private boolean musicOn = false;
	
	private Model cube;
	
	public Engine() {
		frame = new Window(TITLE, WIDTH, HEIGHT);
		screen = new Screen(WIDTH, HEIGHT);
		frame.add(screen);
		
		cube = Reader.readModelFromObj("/models/cube.obj");
		
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
		screen.drawModel(cube);
	}
	
	/**
	 * This is where the pixels are actually drawn on the screen
	 */
	private void render() {
		screen.render();
	}

}
