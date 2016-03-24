package pl.edu.agh.kis.automaton.controller.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;

import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.model.QuadState;
import pl.edu.agh.kis.automaton.view.CellImages;

/**
 * Converts coordinates mapped to icons into coordinates mapped to cell states.
 * @author Wojciech Kumoń
 */
public class QuadLifeIconConverter implements IconToStateConverter {

	/**
	 * Converts coordinates mapped to icons into coordinates mapped to cell states.
	 * @param structure coords mapped to icons
	 * @return coords mapped to quad states
	 */
	@Override
	public Map<CellCoordinates, CellState> convert(Map<CellCoordinates, Icon> structure) {
		Map<CellCoordinates, CellState> outputMap = new HashMap<>();
		for (Entry<CellCoordinates, Icon> entry : structure.entrySet()) {
			QuadState state = getQuadStateFromIcon(entry.getValue());
			outputMap.put(entry.getKey(), state);
		}
		return outputMap;
	}
	
	private QuadState getQuadStateFromIcon(Icon icon) {
		if (icon == CellImages.WHITE)
			return QuadState.DEAD;
		if (icon == CellImages.GREEN)
			return QuadState.GREEN;
		if (icon == CellImages.BLUE)
			return QuadState.BLUE;
		if (icon == CellImages.RED)
			return QuadState.RED;
		return QuadState.YELLOW;
	}

}
