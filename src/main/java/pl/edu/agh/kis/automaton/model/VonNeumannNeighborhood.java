package pl.edu.agh.kis.automaton.model;

import java.util.Set;

/**
 * Class representing von Neumann neighborhood.
 * @author Wojciech Kumoń
 */
public class VonNeumannNeighborhood extends TwoDimNeighborhood {
	
	/**
	 * Constructs von Neumann neighborhood.
	 * @param width board width
	 * @param height board height
	 * @param strategy wrong coords strategy
	 */
	public VonNeumannNeighborhood(int width, int height, WrongCoordsHandler strategy) {
		super(width, height, strategy);
	}
	
	/**
	  * Constructs von Neumann neighborhood.
	 * @param width board width
	 * @param height board height
	 * @param strategy wrong coords strategy
	 * @param range neighborhood range
	 */
	public VonNeumannNeighborhood(int width, int height, WrongCoordsHandler strategy, int range) {
		super(width, height, strategy, range);
	}

	/**
	 * Adds all von Neumann neighbors.
	 * @param coordsSet set to fill with wanted neighbor coords
	 * @param cellCoords coords which will get neighbors
	 */
	protected void addAllPossibleNeighbors(Set<CellCoordinates> coordsSet, Coords2D cellCoords) {
		coordsSet.add(new Coords2D(cellCoords.row+1, cellCoords.col));
		coordsSet.add(new Coords2D(cellCoords.row, cellCoords.col+1));
		coordsSet.add(new Coords2D(cellCoords.row-1, cellCoords.col));
		coordsSet.add(new Coords2D(cellCoords.row, cellCoords.col-1));
	}

}
