package pl.edu.agh.kis.automaton.controller.handlers;

import javax.swing.ImageIcon;

import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.model.QuadState;
import pl.edu.agh.kis.automaton.view.CellImages;

/**
 * Converts cell state into right image.
 * @author Wojciech Kumoń
 */
public class QuadLifeRefresher implements CellRefresher {

	/**
	 * Converts QuadState into right image.
	 * @param state cell state
	 * @return correct image for cell (white, blue, green, red or yellow)
	 */
	@Override
	public ImageIcon getIcon(CellState state) {
		if (state == QuadState.DEAD) {
			return CellImages.WHITE;
		}
		if (state == QuadState.RED) {
			return CellImages.RED;
		}
		if (state == QuadState.BLUE) {
			return CellImages.BLUE;
		}
		if (state == QuadState.GREEN) {
			return CellImages.GREEN;
		}
		return CellImages.YELLOW;
	}

}
