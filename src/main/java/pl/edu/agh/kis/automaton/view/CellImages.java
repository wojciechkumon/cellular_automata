package pl.edu.agh.kis.automaton.view;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Provides images for cells.
 * @author Wojciech Kumoń
 */
public class CellImages {
	public static final ImageIcon WHITE;
	public static final ImageIcon BLACK;
	public static final ImageIcon YELLOW;
	public static final ImageIcon RED;
	public static final ImageIcon BLUE;
	public static final ImageIcon GREEN;

	static {
		try {
			WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResource("greyzz.png")));
			BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResource("black.png")));
			YELLOW = new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResource("yellow.png")));
			RED = new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResource("red.png")));
			BLUE = new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResource("blue.png")));
			GREEN = new ImageIcon(ImageIO.read(ClassLoader.getSystemClassLoader().getResource("green.png")));
		} catch (IOException e) {
			throw new RuntimeException("Couldn't read cell image file", e);
		}
	}

	private CellImages() {}

	/**
	 * Checks if image is cell image.
	 * @param imageIcon imageIcon to check
	 * @return True if imageIcon is cell image, false otherwise.
	 */
	public static boolean isCellImage(ImageIcon imageIcon) {
		return imageIcon == WHITE || imageIcon == BLACK || imageIcon == YELLOW || imageIcon == RED || imageIcon == BLUE
				|| imageIcon == GREEN;
	}

}
