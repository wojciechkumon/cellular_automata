package pl.edu.agh.kis.automaton.controller.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;

import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.model.WireElectronState;
import pl.edu.agh.kis.automaton.view.CellImages;

/**
 * Converts coordinates mapped to icons into coordinates mapped to cell states.
 * @author Wojciech Kumoń
 */
public class WireworldIconConverter implements IconToStateConverter {

	/**
	 * Converts coordinates mapped to icons into coordinates mapped to cell states.
	 * @param structure coords mapped to icons
	 * @return coords mapped to wire electron states
	 */
	@Override
	public Map<CellCoordinates, CellState> convert(Map<CellCoordinates, Icon> structure) {
		Map<CellCoordinates, CellState> outputMap = new HashMap<>();
		for (Entry<CellCoordinates, Icon> entry : structure.entrySet()) {
			WireElectronState state = getWireElectronStateFromIcon(entry.getValue());
			outputMap.put(entry.getKey(), state);
		}
		return outputMap;
	}

	private WireElectronState getWireElectronStateFromIcon(Icon icon) {
		if (icon == CellImages.BLACK)
			return WireElectronState.VOID;
		if (icon == CellImages.YELLOW)
			return WireElectronState.WIRE;
		if (icon == CellImages.BLUE)
			return WireElectronState.ELECTRON_HEAD;
		return WireElectronState.ELECTRON_TAIL;
	}

}
