package pl.edu.agh.kis.automaton.controller.handlers;

import javax.swing.ImageIcon;

import pl.edu.agh.kis.automaton.model.CellState;

/**
 * Converts cell state into right image.
 * @author Wojciech Kumoń
 */
public interface CellRefresher {
	/**
	 * Converts cell state into right image.
	 * @param state cell state
	 * @return correct image for cell
	 */
	ImageIcon getIcon(CellState state); 
}
