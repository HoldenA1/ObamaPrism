package obama.prism.engine.graphics;

import java.awt.Color;
import java.awt.Point;

/**
 * TriangleDrawer handles the drawing of triangles on the Screen.
 * This is an implementation comes from this website.
 * <a href="http://www.sunshine2k.de/coding/java/TriangleRasterization/TriangleRasterization.html">link</a>
 * NOTE: This code is missing edge cases and double renders some parts. For optimization, handle the edge cases
 * @author holden
 */
public class TriangleDrawer {
	
	private Screen screen;
	
	public TriangleDrawer(Screen screen) {
		this.screen = screen;
	}
	
	public void draw(Point p0, Point p1, Point p2, Color color) {
		screen.drawLine(p0.x, p0.y, p1.x, p1.y, color);
		screen.drawLine(p1.x, p1.y, p2.x, p2.y, color);
		screen.drawLine(p2.x, p2.y, p0.x, p0.y, color);
	}
	
	public void fill(Point p0, Point p1, Point p2, Color color) {
		// Sort vertices
		if (p1.y < p0.y) swap(p0, p1);
		if (p2.y < p1.y) swap(p1, p2);
		if (p1.y < p0.y) swap(p0, p1);

		/* here we know that p0.y <= p1.y <= p2.y */
		if (p1.y == p2.y) {
			/* check for trivial case of bottom-flat triangle */
			fillBottomFlatTriangle(p0, p1, p2, color);
		} else if (p0.y == p1.y) {
			/* check for trivial case of top-flat triangle */
			fillTopFlatTriangle(p0, p1, p2, color);
		} else {
			/* general case - split the triangle in a topflat and bottom-flat one */
			Point pSplit = new Point((int)(p0.x + ((float)(p1.y - p0.y) / (float)(p2.y - p0.y)) * (p2.x - p0.x)), p1.y);
			fillBottomFlatTriangle(p0, p1, pSplit, color);
			fillTopFlatTriangle(p1, pSplit, p2, color);
		}
	}
	
	private void fillBottomFlatTriangle(Point p0, Point p1, Point p2, Color color) {
		float invslope1 , invslope2;
		invslope1 = (float)(p1.x - p0.x) / (float)(p1.y - p0.y);
		invslope2 = (float)(p2.x - p0.x) / (float)(p2.y - p0.y);

		float curx1 = p0.x;
		float curx2 = p0.x;

		for (int scanlineY = p0.y; scanlineY <= p1.y; scanlineY++) {
			screen.drawLine((int)curx1, scanlineY, (int)curx2, scanlineY, color);
			curx1 += invslope1;
			curx2 += invslope2;
		}
	 }
	
	private void fillTopFlatTriangle(Point p0, Point p1, Point p2, Color color) {
		float invslope1 = (float)(p2.x - p0.x) / (float)(p2.y - p0.y);
		float invslope2 = (float)(p2.x - p1.x) / (float)(p2.y - p1.y);
		
		float curx1 = p2.x;
		float curx2 = p2.x;
		
		for (int scanlineY = p2.y; scanlineY > p0.y; scanlineY--) {
			screen.drawLine((int)curx1, scanlineY, (int)curx2, scanlineY, color);
			curx1 -= invslope1;
			curx2 -= invslope2;
		}
	}
	
	private void swap(Point p0, Point p1) {
		int tempX = p0.x;
		int tempY = p0.y;
		p0.x = p1.x;
		p0.y = p1.y;
		p1.x = tempX;
		p1.y = tempY;
	}

}
