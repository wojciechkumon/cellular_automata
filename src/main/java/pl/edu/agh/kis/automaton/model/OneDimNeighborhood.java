package pl.edu.agh.kis.automaton.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing one-dimensional neighbors.
 * @author Wojciech Kumoń
 */
public class OneDimNeighborhood implements CellNeighborhood {
	private int size;

	/**
	 * Constructs one-dimensional neighborhood
	 * @param size board size
	 */
	public OneDimNeighborhood(int size) {
		this.size = size;
	}

	/**
	 * Returns two neighbors if exists.
	 * @param cell cell which will get neighbors
	 * @return neighbors of the cell
	 */
	@Override
	public Set<CellCoordinates> cellNeighbors(CellCoordinates cell) {
		Coords1D cellCoords = (Coords1D) cell;
		Set<CellCoordinates> neighbors = new HashSet<>();

		if (cellCoords.x > 0) {
			neighbors.add(new Coords1D(cellCoords.x - 1));
		}
		if (cellCoords.x < size - 1) {
			neighbors.add(new Coords1D(cellCoords.x + 1));
		}
		return neighbors;
	}
}
