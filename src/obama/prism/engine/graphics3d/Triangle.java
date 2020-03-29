package obama.prism.engine.graphics3d;

import java.awt.Color;
import java.awt.Point;
import obama.prism.engine.graphics.Screen;

public class Triangle {
	
	private Vec v0, v1, v2;
	private Color color;
	
	public Triangle(Vec v0, Vec v1, Vec v2, Color triangleColor) {
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		
		color = triangleColor;
	}
	
	public Triangle(Vec v0, Vec v1, Vec v2) {
		this(v0, v1, v2, Color.magenta);
	}
	
	public void draw(Screen screen) {
		Point p0 = new Point((int)v0.x, (int)v0.y);
		Point p1 = new Point((int)v1.x, (int)v1.y);
		Point p2 = new Point((int)v2.x, (int)v2.y);
		
		screen.drawLine(p0.x, p0.y, p1.x, p1.y, color);
		screen.drawLine(p1.x, p1.y, p2.x, p2.y, color);
		screen.drawLine(p2.x, p2.y, p0.x, p0.y, color);
	}
	
	public void fill(Screen screen) {
		// Sort vertices
		if (v1.y < v0.y) swap(v0, v1);
		if (v2.y < v1.y) swap(v1, v2);
		if (v1.y < v0.y) swap(v0, v1);
		
		// Find out what type of triangle it is
		if (v0.y == v1.y) {
			// Flat top triangle
			if (v1.x < v0.x) swap(v0, v1);
			drawFlatTopTriangle(v0, v1, v2, screen);
		} else if (v1.y == v2.y) {
			// Flat bottom triangle
			if (v2.x < v1.x) swap(v1, v2);
			drawFlatBottomTriangle(v0, v1, v2, screen);
		} else {
			// General triangle
			// Find splitting Vec
			float alphaSplit = 
					(v1.y - v0.y) /
					(v2.y - v0.y);
			Vec vSplit = v2.sub(v0).mult(alphaSplit).add(v2);
			
			if (v1.x < vSplit.x) {
				// Major right
				drawFlatBottomTriangle(v0, v1, vSplit, screen);
				drawFlatTopTriangle(v1, vSplit, v2, screen);
			} else {
				// Major left
				drawFlatBottomTriangle(v0, vSplit, v1, screen);
				drawFlatTopTriangle(vSplit, v1, v2, screen);
			}
		}
	}
	
	private void drawFlatTopTriangle(Vec v0, Vec v1, Vec v2, Screen screen) {
		// Calculate slopes in screen space
		float m0 = (v2.x - v0.x) / (v2.y - v0.y);
		float m1 = (v2.x - v1.x) / (v2.y - v1.y);
		
		// Calculate start and end of scanlines
		int yStart = Math.round(v0.y - 0.5f);
		int yEnd = Math.round(v2.y - 0.5f);
		
		for (int y = yStart; y < yEnd; y++) {
			// Calculate start and end points
			float px0 = m0 * (y + 0.5f - v0.y) + v0.x;
			float px1 = m1 * (y + 0.5f - v1.y) + v1.x;
			
			// Calculate start and end pixels
			int xStart = Math.round(px0 - 0.5f);
			int xEnd = Math.round(px1 - 0.5f);
			
			for (int x = xStart; x < xEnd; x++) {
				screen.setPixel(x, y, color.getRGB());
			}
		}
	}
	
	private void drawFlatBottomTriangle(Vec v0, Vec v1, Vec v2, Screen screen) {
		// Calculate slopes in screen space
		float m0 = (v1.x - v0.x) / (v1.y - v0.y);
		float m1 = (v2.x - v0.x) / (v2.y - v0.y);
		
		// Calculate start and end of scanlines
		int yStart = Math.round(v0.y - 0.5f);
		int yEnd = Math.round(v2.y - 0.5f);
		
		for (int y = yStart; y < yEnd; y++) {
			// Calculate start and end points
			float px0 = m0 * (y + 0.5f - v0.y) + v0.x;
			float px1 = m1 * (y + 0.5f - v0.y) + v0.x;
			
			// Calculate start and end pixels
			int xStart = Math.round(px0 - 0.5f);
			int xEnd = Math.round(px1 - 0.5f);
			
			for (int x = xStart; x < xEnd; x++) {
				screen.setPixel(x, y, color.getRGB());
			}
		}
	}
	
	private void swap(Vec v1, Vec v2) {
		float tempX = v1.x;
		float tempY = v1.y;
		float tempZ = v1.z;
		v1.x = v2.x;
		v1.y = v2.y;
		v1.z = v2.z;
		v2.x = tempX;
		v2.y = tempY;
		v2.z = tempZ;
	}
	
	/**
	 * @param vecNum starts from 0
	 * @param v
	 */
	public void setVec(int vecNum, Vec v) {
		switch(vecNum) {
		case 0:
			v0 = v;
			break;
		case 1:
			v1 = v;
			break;
		case 2:
			v2 = v;
			break;
		default:
			System.err.println("Input a valid vector number");
		}
	}

}
