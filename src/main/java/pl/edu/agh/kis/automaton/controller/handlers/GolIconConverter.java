package pl.edu.agh.kis.automaton.controller.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;

import pl.edu.agh.kis.automaton.model.BinaryState;
import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.view.CellImages;

/**
 * Converts coordinates mapped to icons into coordinates mapped to cell states.
 * @author Wojciech Kumoń
 */
public class GolIconConverter implements IconToStateConverter {

	/**
	 * Converts coordinates mapped to icons into coordinates mapped to cell states.
	 * @param structure coords mapped to icons
	 * @return coords mapped to binary states
	 */
	@Override
	public Map<CellCoordinates, CellState> convert(Map<CellCoordinates, Icon> structure) {
		Map<CellCoordinates, CellState> outputMap = new HashMap<>();
		for (Entry<CellCoordinates, Icon> entry : structure.entrySet()) {
			BinaryState state = (entry.getValue() == CellImages.WHITE) ? BinaryState.DEAD : BinaryState.ALIVE;
			outputMap.put(entry.getKey(), state);
		}
		return outputMap;
	}

}
