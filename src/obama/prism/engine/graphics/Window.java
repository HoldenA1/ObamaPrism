package obama.prism.engine.graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Window is a simple JFrame wrapper that makes JFrame easier to use
 * @author holden
 */
public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Window(String name, int width, int height) {
		super(name);
		getContentPane().setPreferredSize(new Dimension(width, height));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * @param operation use JFrame.EXIT_ON_CLOSE for example
	 */
	public Window changeCloseOperation(int operation) {
		setDefaultCloseOperation(operation);
		return this;		
	}
	
	public Window setResizeable(boolean isResizable) {
		setResizable(isResizable);
		return this;		
	}

}
