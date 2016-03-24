package pl.edu.agh.kis.automaton.model;

/**
 * State factory which always returns same state.
 * @author Wojciech Kumoń
 */
public class UniformStateFactory implements CellStateFactory {
	private CellState state;
	
	/**
	 * Constructs factory
	 * @param state state which will be always initial state
	 */
	public UniformStateFactory(CellState state) {
		this.state = state;
	}
	
	/**
	 * Method which return state set in constructor.
	 * @param coords skipped
	 * @return state set in constructor
	 */
	@Override
	public CellState initialState(CellCoordinates coords) {
		return state;
	}

}
