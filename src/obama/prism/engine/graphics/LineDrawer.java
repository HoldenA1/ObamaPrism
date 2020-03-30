package obama.prism.engine.graphics;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LineDrawer handles the drawing of lines on the Screen.
 * This is an implementation of Bresenham's line drawing
 * algorithm.
 * <a href="https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm">link</a>
 * @author holden
 */
public class LineDrawer {
	
	private Screen screen;
	
	public LineDrawer(Screen screen) {
		this.screen = screen;
	}
	
	public void drawLine(int x0, int y0, int x1, int y1, Color color) {
		Point[] line = getLine(x0, y0, x1, y1);
		
		for (Point p: line) {
			screen.setPixel(p.x, p.y, color.getRGB());
		}
	}
	
	/**
	 * Uses linear interpolation to color the line
	 * @param color0 is the color of the first point
	 * @param color1 is the color of the second point
	 */
	public void drawLine(int x0, int y0, int x1, int y1, Color color0, Color color1) {
		Point[] line = getLine(x0, y0, x1, y1);
		
		Color[] colors = interpolateColors(line, color0, color1);
		
		for (int i = 0; i < line.length; i++) {
			Point p = line[i];
			screen.setPixel(p.x, p.y, colors[i].getRGB());
		}
	}
	
	public Point[] getLine(int x0, int y0, int x1, int y1) {
		List<Point> line = new ArrayList<Point>();
		
		if (Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
			if (x0 > x1) {
				plotLineLow(x1, y1, x0, y0, line);
				Collections.reverse(line);
			} else {
				plotLineLow(x0, y0, x1, y1, line);
			}
		} else {
			if (y0 > y1) {
				plotLineHigh(x1, y1, x0, y0, line);
				Collections.reverse(line);
			} else {
				plotLineHigh(x0, y0, x1, y1, line);
			}
		}
		
		return line.toArray(new Point[line.size()]);
	}
	
	public Color[] interpolateColors(Point[] line, Color color0, Color color1) {
		Color[] colors = new Color[line.length];
		
		for (int i = 0; i < line.length; i++) {
			double ratio = (double)(i) / line.length;
			double inverseRatio = 1.0 - ratio;
			int r = (int) (ratio * color1.getRed() + inverseRatio * color0.getRed());
			int g = (int) (ratio * color1.getGreen() + inverseRatio * color0.getGreen());
			int b = (int) (ratio * color1.getBlue() + inverseRatio * color0.getBlue());
			colors[i] = new Color(r, g, b);
		}
		
		return colors;
	}
	
	private void plotLineLow(int x0, int y0, int x1, int y1, List<Point> line) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		int yi = 1;
		
		if (dy < 0) {
			yi = -1;
			dy = -dy;
		}
		
		int D = 2*dy - dx;
		int y = y0;
		
		for (int x = x0; x <= x1; x++) {
			line.add(new Point(x, y));
			if (D > 0) {
				y = y + yi;
				D = D - 2*dx;
			}
			D = D + 2*dy;
		}
	}
	
	private void plotLineHigh(int x0, int y0, int x1, int y1, List<Point> line) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		int xi = 1;
		
		if (dx < 0) {
			xi = -1;
			dx = -dx;
		}
		
		int D = 2*dx - dy;
		int x = x0;
		
		for (int y = y0; y <= y1; y++) {
			line.add(new Point(x, y));
			if (D > 0) {
				x = x + xi;
				D = D - 2*dy;
			}
			D = D + 2*dx;
		}
	}

}
