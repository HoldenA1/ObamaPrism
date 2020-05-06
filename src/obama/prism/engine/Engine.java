package obama.prism.engine;

import obama.prism.engine.graphics.Screen;
import obama.prism.engine.graphics.Window;
import obama.prism.engine.graphics3d.Model;
import obama.prism.engine.graphics3d.Vec3d;
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
	
	private boolean musicOn = false;
	
	private Model cube;
	
	public Engine() {
		frame = new Window(TITLE, WIDTH, HEIGHT);
		screen = new Screen(WIDTH, HEIGHT);
		frame.add(screen);
		
		Vec3d[] vertices = {
				new Vec3d(0, 0, 0),
				new Vec3d(0, 0, 1),
				new Vec3d(0, 1, 0),
				new Vec3d(0, 1, 1),
				new Vec3d(1, 0, 0),
				new Vec3d(1, 0, 1),
				new Vec3d(1, 1, 0),
				new Vec3d(1, 1, 1)
		};
		
		int[] indexBuffer = { 0, 2, 6,
							  0, 6, 4,
							  4, 6, 7,
							  4, 7, 5,
							  5, 7, 3,
							  5, 3, 1,
							  1, 3, 2,
							  1, 2, 0,
							  2, 3, 7,
							  2, 7, 6,
							  5, 1, 0,
							  5, 0, 4 };
		
		cube = new Model(vertices, indexBuffer);
		
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
