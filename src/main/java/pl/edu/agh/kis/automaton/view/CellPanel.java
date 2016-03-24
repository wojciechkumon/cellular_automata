package pl.edu.agh.kis.automaton.view;

import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JPanel;

import pl.edu.agh.kis.automaton.controller.handlers.CellRefresher;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.CellCoordinates;

/** JPanel with set interface for using this as board with cells.
 * @author Wojciech Kumoń
 */
public abstract class CellPanel extends JPanel {
	private static final long serialVersionUID = -6231006467238921451L;

	/**
	 * Set amount of cells
	 * @param width horizontal cells
	 * @param height vertical cells
	 */
	public abstract void setCellAmount(int width, int height);
	
	/**
	 * Update cells look to state from automaton
	 * @param automaton automaton witch is used to set grid cells colors
	 */
	public abstract void updateCells(Automaton automaton);
	
	/**
	 * Add {@link ActionListener} for every cell
	 * @param actionListener actionListener to set
	 */
	public abstract void addCellsListener(ActionListener actionListener);
	
	/**
	 * Set {@link CellRefresher}.
	 * @param refresher refresher to set
	 */
	public abstract void setRefresher(CellRefresher refresher);
	
	/**
	 * Returns coordinates mapped to cell icons.
	 * @return coordinates mapped to cell icons
	 */
	public abstract Map<CellCoordinates, Icon> getIcons();
	
	/**
	 * Set if any cell has been clicked.
	 * @param clicked true if any cell has been clicked, false otherwise
	 */
	public abstract void setClicked(boolean clicked);
	
	/**
	 * Returns true if any cell has been clicked, false otherwise.
	 * @return true if any cell has been clicked, false otherwise
	 */
	public abstract boolean isClicked();
}
