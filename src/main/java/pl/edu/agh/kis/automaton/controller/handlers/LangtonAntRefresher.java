package pl.edu.agh.kis.automaton.controller.handlers;

import javax.swing.ImageIcon;

import pl.edu.agh.kis.automaton.model.AntState;
import pl.edu.agh.kis.automaton.model.BinaryState;
import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.model.LangtonCell;
import pl.edu.agh.kis.automaton.view.AntImages;
import pl.edu.agh.kis.automaton.view.CellImages;

/**
 * Converts cell state into right image.
 * @author Wojciech Kumoń
 */
public class LangtonAntRefresher implements CellRefresher {

	/**
	 * Converts LangtonCell into right image.
	 * @param state cell state
	 * @return correct image for cell (colored ant in correct direction or empty field)
	 */
	@Override
	public ImageIcon getIcon(CellState state) {
		LangtonCell langtonState = (LangtonCell) state;
		if (langtonState.antState == AntState.NONE) {
			return getBlankFieldIcon(langtonState.cellState);
		} 
		if (langtonState.antState == AntState.NORTH) {
			return getNorthAntIcon(langtonState);
		}
		if (langtonState.antState == AntState.EAST) {
			return getEastAntIcon(langtonState);
		}
		if (langtonState.antState == AntState.SOUTH) {
			return getSouthAntIcon(langtonState);
		}
		return getWestAntState(langtonState);
	}

	private ImageIcon getBlankFieldIcon(BinaryState cellState) {
		if (cellState == BinaryState.ALIVE) {
			return CellImages.BLACK;
		}
		return CellImages.WHITE;
	}

	private ImageIcon getNorthAntIcon(LangtonCell langtonState) {
		if (langtonState.cellState == BinaryState.DEAD) {
			return getColoredAntIcon(langtonState.antId, AntImages.ANT_NORTH_BLUE_WHITE,
					AntImages.ANT_NORTH_GREEN_WHITE, AntImages.ANT_NORTH_RED_WHITE, AntImages.ANT_NORTH_YELLOW_WHITE);
		} else {
			return getColoredAntIcon(langtonState.antId, AntImages.ANT_NORTH_BLUE_BLACK,
					AntImages.ANT_NORTH_GREEN_BLACK, AntImages.ANT_NORTH_RED_BLACK, AntImages.ANT_NORTH_YELLOW_BLACK);
		}
	}
	
	private ImageIcon getEastAntIcon(LangtonCell langtonState) {
		if (langtonState.cellState == BinaryState.DEAD) {
			return getColoredAntIcon(langtonState.antId, AntImages.ANT_EAST_BLUE_WHITE, AntImages.ANT_EAST_GREEN_WHITE,
					AntImages.ANT_EAST_RED_WHITE, AntImages.ANT_EAST_YELLOW_WHITE);
		} else {
			return getColoredAntIcon(langtonState.antId, AntImages.ANT_EAST_BLUE_BLACK, AntImages.ANT_EAST_GREEN_BLACK,
					AntImages.ANT_EAST_RED_BLACK, AntImages.ANT_EAST_YELLOW_BLACK);
		}
	}
	
	private ImageIcon getSouthAntIcon(LangtonCell langtonState) {
		if (langtonState.cellState == BinaryState.DEAD) {
			return getColoredAntIcon(langtonState.antId, AntImages.ANT_SOUTH_BLUE_WHITE,
					AntImages.ANT_SOUTH_GREEN_WHITE, AntImages.ANT_SOUTH_RED_WHITE, AntImages.ANT_SOUTH_YELLOW_WHITE);
		} else {
			return getColoredAntIcon(langtonState.antId, AntImages.ANT_SOUTH_BLUE_BLACK,
					AntImages.ANT_SOUTH_GREEN_BLACK, AntImages.ANT_SOUTH_RED_BLACK, AntImages.ANT_SOUTH_YELLOW_BLACK);
		}
	}
	
	private ImageIcon getWestAntState(LangtonCell langtonState) {
		if (langtonState.cellState == BinaryState.DEAD) {
			return getColoredAntIcon(langtonState.antId, AntImages.ANT_WEST_BLUE_WHITE, AntImages.ANT_WEST_GREEN_WHITE,
					AntImages.ANT_WEST_RED_WHITE, AntImages.ANT_WEST_YELLOW_WHITE);
		} else {
			return getColoredAntIcon(langtonState.antId, AntImages.ANT_WEST_BLUE_BLACK, AntImages.ANT_WEST_GREEN_BLACK,
					AntImages.ANT_WEST_RED_BLACK, AntImages.ANT_WEST_YELLOW_BLACK);
		}
	}
	
	private ImageIcon getColoredAntIcon(int antId, ImageIcon blue, ImageIcon green, ImageIcon red, ImageIcon yellow) {
		antId %= 4;
		switch (antId) {
		case 0:
			return blue;
		case 1:
			return green;
		case 2:
			return red;
		case 3:
		default:
			return yellow;
		}
	}

}
