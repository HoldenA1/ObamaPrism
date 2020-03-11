package obama.prism.engine;

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
	
	private static void update() {
		screen.update();
	}
	
	private static void render() {
		screen.render();
	}

}
