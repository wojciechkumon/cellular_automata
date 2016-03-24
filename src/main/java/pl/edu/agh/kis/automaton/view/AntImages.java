package pl.edu.agh.kis.automaton.view;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import pl.edu.agh.kis.automaton.model.AntState;

/**
 * Provides images for ants.
 * @author Wojciech Kumoń
 */
public class AntImages {
	public static final ImageIcon ANT_NORTH_BLUE_WHITE;
	public static final ImageIcon ANT_NORTH_GREEN_WHITE;
	public static final ImageIcon ANT_NORTH_RED_WHITE;
	public static final ImageIcon ANT_NORTH_YELLOW_WHITE;

	public static final ImageIcon ANT_EAST_BLUE_WHITE;
	public static final ImageIcon ANT_EAST_GREEN_WHITE;
	public static final ImageIcon ANT_EAST_RED_WHITE;
	public static final ImageIcon ANT_EAST_YELLOW_WHITE;

	public static final ImageIcon ANT_SOUTH_BLUE_WHITE;
	public static final ImageIcon ANT_SOUTH_GREEN_WHITE;
	public static final ImageIcon ANT_SOUTH_RED_WHITE;
	public static final ImageIcon ANT_SOUTH_YELLOW_WHITE;

	public static final ImageIcon ANT_WEST_BLUE_WHITE;
	public static final ImageIcon ANT_WEST_GREEN_WHITE;
	public static final ImageIcon ANT_WEST_RED_WHITE;
	public static final ImageIcon ANT_WEST_YELLOW_WHITE;

	public static final ImageIcon ANT_NORTH_BLUE_BLACK;
	public static final ImageIcon ANT_NORTH_GREEN_BLACK;
	public static final ImageIcon ANT_NORTH_RED_BLACK;
	public static final ImageIcon ANT_NORTH_YELLOW_BLACK;

	public static final ImageIcon ANT_EAST_BLUE_BLACK;
	public static final ImageIcon ANT_EAST_GREEN_BLACK;
	public static final ImageIcon ANT_EAST_RED_BLACK;
	public static final ImageIcon ANT_EAST_YELLOW_BLACK;

	public static final ImageIcon ANT_SOUTH_BLUE_BLACK;
	public static final ImageIcon ANT_SOUTH_GREEN_BLACK;
	public static final ImageIcon ANT_SOUTH_RED_BLACK;
	public static final ImageIcon ANT_SOUTH_YELLOW_BLACK;

	public static final ImageIcon ANT_WEST_BLUE_BLACK;
	public static final ImageIcon ANT_WEST_GREEN_BLACK;
	public static final ImageIcon ANT_WEST_RED_BLACK;
	public static final ImageIcon ANT_WEST_YELLOW_BLACK;

	static {
		try {
			ANT_NORTH_BLUE_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_up_blue.png")));
			ANT_NORTH_GREEN_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_up_green.png")));
			ANT_NORTH_RED_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_up_red.png")));
			ANT_NORTH_YELLOW_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_up_yellow.png")));

			ANT_EAST_BLUE_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_right_blue.png")));
			ANT_EAST_GREEN_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_right_green.png")));
			ANT_EAST_RED_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_right_red.png")));
			ANT_EAST_YELLOW_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_right_yellow.png")));

			ANT_SOUTH_BLUE_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_down_blue.png")));
			ANT_SOUTH_GREEN_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_down_green.png")));
			ANT_SOUTH_RED_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_down_red.png")));
			ANT_SOUTH_YELLOW_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_down_yellow.png")));

			ANT_WEST_BLUE_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_left_blue.png")));
			ANT_WEST_GREEN_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_left_green.png")));
			ANT_WEST_RED_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_left_red.png")));
			ANT_WEST_YELLOW_WHITE = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_left_yellow.png")));

			ANT_NORTH_BLUE_BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_up_blue_dark.png")));
			ANT_NORTH_GREEN_BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_up_green_dark.png")));
			ANT_NORTH_RED_BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_up_red_dark.png")));
			ANT_NORTH_YELLOW_BLACK = new ImageIcon(
					ImageIO.read(ClassLoader.getSystemResource("arrow_up_yellow_dark.png")));

			ANT_EAST_BLUE_BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_right_blue_dark.png")));
			ANT_EAST_GREEN_BLACK = new ImageIcon(
					ImageIO.read(ClassLoader.getSystemResource("arrow_right_green_dark.png")));
			ANT_EAST_RED_BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_right_red_dark.png")));
			ANT_EAST_YELLOW_BLACK = new ImageIcon(
					ImageIO.read(ClassLoader.getSystemResource("arrow_right_yellow_dark.png")));

			ANT_SOUTH_BLUE_BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_down_blue_dark.png")));
			ANT_SOUTH_GREEN_BLACK = new ImageIcon(
					ImageIO.read(ClassLoader.getSystemResource("arrow_down_green_dark.png")));
			ANT_SOUTH_RED_BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_down_red_dark.png")));
			ANT_SOUTH_YELLOW_BLACK = new ImageIcon(
					ImageIO.read(ClassLoader.getSystemResource("arrow_down_yellow_dark.png")));

			ANT_WEST_BLUE_BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_left_blue_dark.png")));
			ANT_WEST_GREEN_BLACK = new ImageIcon(
					ImageIO.read(ClassLoader.getSystemResource("arrow_left_green_dark.png")));
			ANT_WEST_RED_BLACK = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("arrow_left_red_dark.png")));
			ANT_WEST_YELLOW_BLACK = new ImageIcon(
					ImageIO.read(ClassLoader.getSystemResource("arrow_left_yellow_dark.png")));
		} catch (IOException e) {
			throw new RuntimeException("Couldn't read cell image file", e);
		}
	}
	
	private AntImages() {}

	/**
	 * Checks if icon is an arrow with black background.
	 * @param icon icon to check
	 * @return True if icon is an arrow with black background, false otherwise.
	 */
	public static boolean isAntWithBlackBackground(ImageIcon icon) {
		if (icon == ANT_NORTH_BLUE_BLACK || icon == ANT_NORTH_GREEN_BLACK || icon == ANT_NORTH_RED_BLACK
				|| icon == ANT_NORTH_YELLOW_BLACK || icon == ANT_EAST_BLUE_BLACK || icon == ANT_EAST_GREEN_BLACK
				|| icon == ANT_EAST_RED_BLACK || icon == ANT_EAST_YELLOW_BLACK || icon == ANT_SOUTH_BLUE_BLACK
				|| icon == ANT_SOUTH_GREEN_BLACK || icon == ANT_SOUTH_RED_BLACK || icon == ANT_SOUTH_YELLOW_BLACK
				|| icon == ANT_WEST_BLUE_BLACK || icon == ANT_WEST_GREEN_BLACK || icon == ANT_WEST_RED_BLACK
				|| icon == ANT_WEST_YELLOW_BLACK) {
			return true;
		}
		return false;
	}

	/**
	 * Gets ant color
	 * @param icon icon of ant
	 * @return color of the ant
	 */
	public static AntColor getAntColorFromIcon(ImageIcon icon) {
		if (icon == ANT_NORTH_BLUE_BLACK || icon == ANT_NORTH_BLUE_WHITE || icon == ANT_EAST_BLUE_BLACK
				|| icon == ANT_EAST_BLUE_WHITE || icon == ANT_SOUTH_BLUE_BLACK || icon == ANT_SOUTH_BLUE_WHITE
				|| icon == ANT_WEST_BLUE_BLACK || icon == ANT_WEST_BLUE_WHITE) {
			return AntColor.BLUE;
		}
		if (icon == ANT_NORTH_GREEN_BLACK || icon == ANT_NORTH_GREEN_WHITE || icon == ANT_EAST_GREEN_BLACK
				|| icon == ANT_EAST_GREEN_WHITE || icon == ANT_SOUTH_GREEN_BLACK || icon == ANT_SOUTH_GREEN_WHITE
				|| icon == ANT_WEST_GREEN_BLACK || icon == ANT_WEST_GREEN_WHITE) {
			return AntColor.GREEN;
		}
		if (icon == ANT_NORTH_RED_BLACK || icon == ANT_NORTH_RED_WHITE || icon == ANT_EAST_RED_BLACK
				|| icon == ANT_EAST_RED_WHITE || icon == ANT_SOUTH_RED_BLACK || icon == ANT_SOUTH_RED_WHITE
				|| icon == ANT_WEST_RED_BLACK || icon == ANT_WEST_RED_WHITE) {
			return AntColor.RED;
		}
		if (icon == ANT_NORTH_YELLOW_BLACK || icon == ANT_NORTH_YELLOW_WHITE || icon == ANT_EAST_YELLOW_BLACK
				|| icon == ANT_EAST_YELLOW_WHITE || icon == ANT_SOUTH_YELLOW_BLACK || icon == ANT_SOUTH_YELLOW_WHITE
				|| icon == ANT_WEST_YELLOW_BLACK || icon == ANT_WEST_YELLOW_WHITE) {
			return AntColor.YELLOW;
		}
		throw new RuntimeException("wrong icon");
	}

	/**
	 * Converts image of ant into ant state
	 * @param icon image to check
	 * @return AntState direction if there is an ant, AntStat.NONE otherwise.
	 */
	public static AntState getAntStateFromIcon(ImageIcon icon) {
		if (icon == ANT_NORTH_BLUE_BLACK || icon == ANT_NORTH_BLUE_WHITE || icon == ANT_NORTH_GREEN_BLACK
				|| icon == ANT_NORTH_GREEN_WHITE || icon == ANT_NORTH_RED_BLACK || icon == ANT_NORTH_RED_WHITE
				|| icon == ANT_NORTH_YELLOW_BLACK || icon == ANT_NORTH_YELLOW_WHITE) {
			return AntState.NORTH;
		}
		if (icon == ANT_EAST_BLUE_BLACK || icon == ANT_EAST_BLUE_WHITE || icon == ANT_EAST_GREEN_BLACK
				|| icon == ANT_EAST_GREEN_WHITE || icon == ANT_EAST_RED_BLACK || icon == ANT_EAST_RED_WHITE
				|| icon == ANT_EAST_YELLOW_BLACK || icon == ANT_EAST_YELLOW_WHITE) {
			return AntState.EAST;
		}
		if (icon == ANT_SOUTH_BLUE_BLACK || icon == ANT_SOUTH_BLUE_WHITE || icon == ANT_SOUTH_GREEN_BLACK
				|| icon == ANT_SOUTH_GREEN_WHITE || icon == ANT_SOUTH_RED_BLACK || icon == ANT_SOUTH_RED_WHITE
				|| icon == ANT_SOUTH_YELLOW_BLACK || icon == ANT_SOUTH_YELLOW_WHITE) {
			return AntState.SOUTH;
		}
		if (icon == ANT_WEST_BLUE_BLACK || icon == ANT_WEST_BLUE_WHITE || icon == ANT_WEST_GREEN_BLACK
				|| icon == ANT_WEST_GREEN_WHITE || icon == ANT_WEST_RED_BLACK || icon == ANT_WEST_RED_WHITE
				|| icon == ANT_WEST_YELLOW_BLACK || icon == ANT_WEST_YELLOW_WHITE) {
			return AntState.WEST;
		}

		return AntState.NONE;
	}

}
