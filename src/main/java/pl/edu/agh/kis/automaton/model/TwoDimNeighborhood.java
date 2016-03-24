package pl.edu.agh.kis.automaton.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class which allow getting neighbors. It is parametrized by range,
 * wrong coords handler and template method.
 * @author Wojciech Kumoń
 */
public abstract class TwoDimNeighborhood implements CellNeighborhood {
	private int width;
	private int height;
	private WrongCoordsHandler strategy;
	private int range;

	/**
	 * Constructs two-dimensional neighborhood.
	 * @param width board width
	 * @param height board height
	 * @param strategy wrong coords strategy
	 */
	public TwoDimNeighborhood(int width, int height, WrongCoordsHandler strategy) {
		this(width, height, strategy, 1);
	}

	/**
	 * Constructs two-dimensional neighborhood.
	 * @param width board width
	 * @param height board height
	 * @param strategy wrong coords strategy
	 * @param range neighborhood range
	 */
	public TwoDimNeighborhood(int width, int height, WrongCoordsHandler strategy, int range) {
		this.width = width;
		this.height = height;
		this.strategy = strategy;
		this.range = range;
		if (range <= 0) {
			throw new InvalidParameterException();
		}
	}

	/**
	 * Returns neighbors of sent cell
	 * @param currentCell cell which will get neighbors
	 * @return neighbors of the cell
	 */
	@Override
	public Set<CellCoordinates> cellNeighbors(CellCoordinates currentCell) {
		Coords2D cellCoords = (Coords2D) currentCell;
		Set<CellCoordinates> neighbors = new HashSet<>();
		addAllPossibleNeighbors(neighbors, cellCoords);
		neighbors = getCorrectCoords2d(neighbors);

		int rangeLeft = range;
		while (rangeLeft > 1) {
			neighbors = getNeighborsOfNeighbors(neighbors);
			--rangeLeft;
		}
		neighbors.remove(currentCell);
		return neighbors;
	}

	private Set<CellCoordinates> getCorrectCoords2d(Set<CellCoordinates> coordsSet) {
		Set<CellCoordinates> correctCoordsSet = new HashSet<>(8);
		for (CellCoordinates coords : coordsSet) {
			Coords2D coords2d = (Coords2D) coords;
			if (isCoord2dCorrect(coords2d)) {
				correctCoordsSet.add(coords2d);
			} else {
				handleNotCorrectCoords(correctCoordsSet, coords2d);
			}
		}
		return correctCoordsSet;
	}

	private Set<CellCoordinates> getNeighborsOfNeighbors(Set<CellCoordinates> neighbors) {
		Set<CellCoordinates> newNeighbors = new HashSet<>(neighbors);
		for (CellCoordinates coords : neighbors) {
			addAllPossibleNeighbors(newNeighbors, (Coords2D) coords);
		}
		return getCorrectCoords2d(newNeighbors);
	}

	private boolean isCoord2dCorrect(Coords2D coords2d) {
		if ((coords2d.col >= 0) && (coords2d.col < width) && (coords2d.row >= 0) && (coords2d.row < height)) {
			return true;
		}
		return false;
	}

	private void handleNotCorrectCoords(Set<CellCoordinates> correctCoordsSet, Coords2D coords2d) {
		CellCoordinates correctCoords = strategy.getCorrectCoords(coords2d);
		if (correctCoords != null) {
			correctCoordsSet.add(correctCoords);
		}
	}

	/**
	 * Adds all wanted Coords2D, which will be checked later.
	 * @param coordsSet set to fill with wanted neighbor coords
	 * @param cellCoords coords which will get neighbors
	 */
	protected abstract void addAllPossibleNeighbors(Set<CellCoordinates> coordsSet, Coords2D cellCoords);
}
