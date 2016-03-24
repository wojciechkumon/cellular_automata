package pl.edu.agh.kis.automaton.controller.handlers;

import javax.swing.ImageIcon;

import pl.edu.agh.kis.automaton.model.BinaryState;
import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.view.CellImages;

/**
 * Converts cell state into right image.
 * @author Wojciech Kumoń
 */
public class GolRefresher implements CellRefresher {
	
	/**
	 * Converts Binary state into right image.
	 * @param state cell state
	 * @return correct image for cell (black or white)
	 */
	@Override
	public ImageIcon getIcon(CellState state) {
		if (state == BinaryState.DEAD) {
			return CellImages.WHITE;
		}
		return CellImages.BLACK;
	}

}
