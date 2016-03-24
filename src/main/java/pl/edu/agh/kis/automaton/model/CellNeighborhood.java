package pl.edu.agh.kis.automaton.model;

import java.util.Set;

/**
 * Interface representing type of neighborhood.
 * @author Wojciech Kumoń
 */
public interface CellNeighborhood {
	/**
	 * Returns neighbors of sent cell
	 * @param cell cell which will get neighbors
	 * @return neighbors of the cell
	 */
	Set<CellCoordinates> cellNeighbors(CellCoordinates cell);
}
