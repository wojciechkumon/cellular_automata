package pl.edu.agh.kis.automaton.controller.handlers;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.view.AntImages;
import pl.edu.agh.kis.automaton.view.CellImages;
import pl.edu.agh.kis.automaton.view.GridCell;
import pl.edu.agh.kis.automaton.view.ResizedImageIcon;

/**
 * Allows setting new cell image.
 * @author Wojciech Kumoń
 */
public class LangtonAntCellColorSetter implements CellColorSetter {
	private Controller controller;
	private int counter = 0;

	public LangtonAntCellColorSetter(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Set correct image of clicked cell in Langton's ant automaton.
	 * @param cell cell which will get new icon
	 */
	@Override
	public void click(GridCell cell) {
		Icon selectedColor = controller.getSelectedColor();
		if (selectedColor == CellImages.WHITE) {
			cell.setIcon(CellImages.WHITE);
		} else if (selectedColor == CellImages.BLACK) {
			cell.setIcon(CellImages.BLACK);
		} else {
			setNewAnt(cell);
		}
	}

	private void setNewAnt(GridCell cell) {
		counter %= 4;
		switch (counter) {
		case 0:
			setBlueAnt(cell);
			break;
		case 1:
			setGreenAnt(cell);
			break;
		case 2:
			setRedAnt(cell);
			break;
		default:
			setYellowAnt(cell);
		}
		++counter;
	}

	private void setBlueAnt(GridCell cell) {
		setNorthAnt(cell, AntImages.ANT_NORTH_BLUE_WHITE, AntImages.ANT_NORTH_BLUE_BLACK);
	}

	private void setGreenAnt(GridCell cell) {
		setNorthAnt(cell, AntImages.ANT_NORTH_GREEN_WHITE, AntImages.ANT_NORTH_GREEN_BLACK);
	}

	private void setRedAnt(GridCell cell) {
		setNorthAnt(cell, AntImages.ANT_NORTH_RED_WHITE, AntImages.ANT_NORTH_RED_BLACK);
	}

	private void setYellowAnt(GridCell cell) {
		setNorthAnt(cell, AntImages.ANT_NORTH_YELLOW_WHITE, AntImages.ANT_NORTH_YELLOW_BLACK);
	}

	private void setNorthAnt(GridCell cell, ImageIcon white, ImageIcon black) {
		if (cell.getIcon() == CellImages.BLACK) {
			cell.setResizedIcon(black);
		} else if (cell.getIcon() == CellImages.WHITE) {
			cell.setResizedIcon(white);
		} else {
			ResizedImageIcon icon = (ResizedImageIcon) cell.getIcon();
			if (AntImages.isAntWithBlackBackground(icon.getSource())) {
				cell.setResizedIcon(black);
			} else {
				cell.setResizedIcon(white);
			}
		}
	}

}
