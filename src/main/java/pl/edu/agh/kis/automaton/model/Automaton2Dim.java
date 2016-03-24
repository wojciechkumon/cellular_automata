package pl.edu.agh.kis.automaton.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Abstract class representing two-dimensional cellular automaton
 * @author Wojciech Kumoń
 */
public abstract class Automaton2Dim extends Automaton {
	private int width;
	private int height;

	/**
	 * Constructor which creates two-dimensional automaton with all cells, which
	 * are set by CellStateFactory.
	 * @param factory CellStateFactory to initialize new cell states
	 * @param neighborhood CellNeighborhood which decides which cells are neighbors
	 * @param width amount of cells in automaton row
	 * @param height amount of cells in automaton column
	 */
	public Automaton2Dim(CellStateFactory factory, CellNeighborhood neighborhood, int width, int height) {
		super(factory, neighborhood);
		this.width = width;
		this.height = height;

		Map<CellCoordinates, CellState> structure = new HashMap<>();
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				Coords2D coords = new Coords2D(j, i);
				structure.put(coords, factory.initialState(coords));
			}
		}
		insertStructure(structure);
	}

	/**
	 * Checks if sent coords have next.
	 * @param coords coords must be a subclass of Coords2D
	 * @return true if coords have next, false otherwise.
	 */
	@Override
	protected boolean hasNextCoordinates(CellCoordinates coords) {
		Coords2D coords2d = (Coords2D) coords;
		return (coords2d.col + 1 < width) || (coords2d.row + 1 < height);
	}

	/**
	 * Creates initial coordinates - coords before first acceptable.
	 * @return CellCoordinates which are before first possible.
	 */
	@Override
	protected CellCoordinates initialCoordinates() {
		return new Coords2D(0, -1);
	}

	/**
	 * Returns next coordinates. It may return wrong coords, so you have to
	 * check it using hasNextCoordinates().
	 * @param coords coords must be a subclass of Coords2D. It is base to return next.
	 * @return next coordinates
	 */
	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates coords) {
		Coords2D coords2d = (Coords2D) coords;
		if (coords2d.col + 1 < width)
			return new Coords2D(coords2d.row, coords2d.col + 1);
		return new Coords2D(coords2d.row + 1, 0);
	}

	/**
	 * Return amount of cells in one row.
	 * @return amount of cells in one row
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Return amount of cells in one column.
	 * @return amount of cells in one column
	 */
	public int getHeight() {
		return height;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 1;
		for (Cell cell : this) {
			hashCode = 31 * hashCode + cell.hashCode();
		}
		return 31 * hashCode + Objects.hash(getWidth(), getHeight());
	};
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !getClass().equals(obj.getClass())) {
			return false;
		}
		Automaton2Dim automaton2Dim = (Automaton2Dim) obj;
		if (getWidth() != automaton2Dim.getWidth() || getHeight() != automaton2Dim.getHeight()) {
			return false;
		}
		boolean equal = true;
		Iterator<Cell> it = automaton2Dim.iterator();
		for (Cell cell : this) {
			equal = equal && cell.equals(it.next());
		}
		return equal;
	};

}
