package pl.edu.agh.kis.automaton.controller.handlers;

import javax.swing.ImageIcon;

import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.model.WireElectronState;
import pl.edu.agh.kis.automaton.view.CellImages;

/**
 * Converts cell state into right image.
 * @author Wojciech Kumoń
 */
public class WireworldRefresher implements CellRefresher {

	/**
	 * Converts WireElectronState into right image.
	 * @param state cell state
	 * @return correct image for cell (void, wire, electron head or electron tail)
	 */
	@Override
	public ImageIcon getIcon(CellState state) {
		if (state == WireElectronState.VOID) {
			return CellImages.BLACK;
		}
		if (state == WireElectronState.WIRE) {
			return CellImages.YELLOW;
		}
		if (state == WireElectronState.ELECTRON_HEAD) {
			return CellImages.BLUE;
		}
		return CellImages.RED;
	}

}
