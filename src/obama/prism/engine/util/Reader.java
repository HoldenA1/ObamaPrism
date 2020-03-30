package obama.prism.engine.util;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Reader {
	
	public static BufferedImage readImage(String path) {
		try {
			BufferedImage image = ImageIO.read(Reader.class.getResource(path));
			return image;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
