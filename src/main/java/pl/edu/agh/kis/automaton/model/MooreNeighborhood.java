package pl.edu.agh.kis.automaton.model;

import java.util.Set;

/**
 * Class representing Moore neighborhood.
 * @author Wojciech Kumoń
 */
public class MooreNeighborhood extends TwoDimNeighborhood {

	/**
	 * Constructs Moore neighborhood.
	 * @param width board width
	 * @param height board height
	 * @param strategy wrong coords strategy
	 */
	public MooreNeighborhood(int width, int height, WrongCoordsHandler strategy) {
		super(width, height, strategy);
	}
	
	/**
	  * Constructs Moore neighborhood.
	 * @param width board width
	 * @param height board height
	 * @param strategy wrong coords strategy
	 * @param range neighborhood range
	 */
	public MooreNeighborhood(int width, int height, WrongCoordsHandler strategy, int range) {
		super(width, height, strategy, range);
	}

	/**
	 * Adds all Moore neighbors.
	 * @param coordsSet set to fill with wanted neighbor coords
	 * @param cellCoords coords which will get neighbors
	 */
	protected void addAllPossibleNeighbors(Set<CellCoordinates> coordsSet, Coords2D cellCoords) {
		for (int i = -1; i <= 1; ++i) {
			coordsSet.add(new Coords2D(cellCoords.row + 1, cellCoords.col + i));
			coordsSet.add(new Coords2D(cellCoords.row - 1, cellCoords.col + i));
		}
		coordsSet.add(new Coords2D(cellCoords.row, cellCoords.col - 1));
		coordsSet.add(new Coords2D(cellCoords.row, cellCoords.col + 1));
	}

}
