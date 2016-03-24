package pl.edu.agh.kis.automaton.controller.handlers;

import javax.swing.Icon;

import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.view.CellImages;
import pl.edu.agh.kis.automaton.view.GridCell;

/**
 * Allows setting new cell image.
 * @author Wojciech Kumoń
 */
public class QuadLifeCellColorSetter implements CellColorSetter {
	private Controller controller;

	public QuadLifeCellColorSetter(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Set correct image of clicked cell in Quad Life automaton.
	 * @param cell cell which will get new icon
	 */
	@Override
	public void click(GridCell cell) {
		Icon selectedIcon = controller.getSelectedColor();
		if (cell.getIcon() == selectedIcon) {
			cell.setIcon(CellImages.WHITE);
		} else {
			cell.setIcon(selectedIcon);
		}
	}

}
