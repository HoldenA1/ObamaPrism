package obama.prism.engine;

import obama.prism.engine.graphics.*;

public class Main {
	
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final String TITLE = "Obama Prism Render Engine";

	public static void main(String[] args) {
		Window frame = new Window(TITLE, WIDTH, HEIGHT);
		
		Screen screen = new Screen(WIDTH, HEIGHT);
		
		frame.add(screen);
		
		int[] pixels = new int[WIDTH * HEIGHT];
		
		for (int rows = 0; rows < HEIGHT; rows++) {
			for (int cols = 0; cols < WIDTH; cols++) {
				pixels[cols + rows * WIDTH] = 0x4285F4;
			}
		}
		
		screen.setPixels(pixels);
		
		while (true) {
			screen.render();
			
			// An attempt to not turn your computer into a toaster
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
