package obama.prism.engine.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import obama.prism.engine.graphics3d.*;

public class Reader {
	
	public static BufferedImage readImage(String path) {
		try {
			BufferedImage image = ImageIO.read(Reader.class.getResource(path));
			return image;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Model readModelFromObj(String path) {
		ArrayList<Vec3d> vertices = new ArrayList<Vec3d>();
		ArrayList<Integer> indexBuffer = new ArrayList<Integer>();
		
		try {
			URI uri = Reader.class.getResource(path).toURI();
			File obj = new File(uri);
			Scanner reader = new Scanner(obj);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				
				if (data.startsWith("v")) {
					String[] values = data.split(" ");
					float x = Float.parseFloat(values[1]);
					float y = Float.parseFloat(values[2]);
					float z = Float.parseFloat(values[3]);
					vertices.add(new Vec3d(x, y, z));
				} else if (data.startsWith("f")) {
					String[] values = data.split(" ");
					indexBuffer.add(Integer.parseInt(values[1]) - 1);
					indexBuffer.add(Integer.parseInt(values[2]) - 1);
					indexBuffer.add(Integer.parseInt(values[3]) - 1);
				}
			}
			reader.close();
		} catch (Exception e) {
			System.err.println("An error occurred reading the obj file");
			e.printStackTrace();
		}
		
		Vec3d[] vertArray = new Vec3d[vertices.size()];
		for (int i = 0; i < vertices.size(); i++) {
			vertArray[i] = vertices.get(i);
		}
		
		int[] indexBufferArray = new int[indexBuffer.size()];
		for (int i = 0; i < indexBuffer.size(); i++) {
			indexBufferArray[i] = indexBuffer.get(i);
		}
		
		return new Model(vertArray, indexBufferArray);
	}

}
