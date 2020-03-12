package obama.prism.engine;

public class Main {
	
	private static Engine engine;

	public static void main(String[] args) {
		engine = new Engine();
		
		while (true) {			
			engine.run();
		}
	}

}
