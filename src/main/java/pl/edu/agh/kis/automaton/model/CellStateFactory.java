package pl.edu.agh.kis.automaton.model;

/**
 * Interface representing factory to create CellStates
 * @author Wojciech Kumoń
 */
public interface CellStateFactory {
	/**
	 * Method which return cell stated based on coordinates.
	 * @param coords coordinates which will get matching state.
	 * @return CellState based on coords.
	 */
	CellState initialState(CellCoordinates coords);
}
