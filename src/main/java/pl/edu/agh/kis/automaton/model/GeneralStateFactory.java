package pl.edu.agh.kis.automaton.model;

import java.util.Map;

/**
 * State factory which always returns state based on map set.
 * @author Wojciech Kumoń
 */
public class GeneralStateFactory implements CellStateFactory {
	private Map<CellCoordinates, CellState> states;
	
	/**
	 * Constructs factory.
	 * @param cellStates map which will be used to map coordinated to state.
	 */
	public GeneralStateFactory(Map<CellCoordinates, CellState> cellStates) {
		states = cellStates;
	}

	/**
	 * Method which return state based on map set in constructor.
	 * @param coords base to get state.
	 * @return state state based on map set in constructor.
	 */
	@Override
	public CellState initialState(CellCoordinates coords) {
		return states.get(coords);
	}

}
