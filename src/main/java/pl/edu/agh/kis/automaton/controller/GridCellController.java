package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.edu.agh.kis.automaton.controller.handlers.CellColorSetter;
import pl.edu.agh.kis.automaton.view.GridCell;

/**
 * Handles cell clicking.
 * @author Wojciech Kumoń
 *
 */
public class GridCellController implements ActionListener {
	private Controller controller;
	private CellColorSetter colorSetter;

	public GridCellController(Controller controller, CellColorSetter colorSetter) {
		this.controller = controller;
		this.colorSetter = colorSetter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GridCell cell = (GridCell) e.getSource();
		colorSetter.click(cell);
		controller.getView().setClicked(true);
	}
	
	/**
	 * Sets color setter which is used to decide about new icon for a cell. 
	 * @param colorSetter colorSetter to set
	 */
	public void setCellColorSetter(CellColorSetter colorSetter) {
		this.colorSetter = colorSetter;
	}

}
