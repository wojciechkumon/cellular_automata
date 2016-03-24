package pl.edu.agh.kis.automaton.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Holds application background.
 * @author Wojciech Kumoń
 */
public class Background {
	public static final Image BACKGROUND;

	static {
		try {
			BACKGROUND = ImageIO.read(ClassLoader.getSystemResource("background.png"));
		} catch (IOException e) {
			throw new RuntimeException("Couldn't read background image file", e);
		}
	}

	private Background() {}
}
