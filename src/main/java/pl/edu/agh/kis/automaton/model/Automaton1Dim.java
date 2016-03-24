package pl.edu.agh.kis.automaton.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Abstract class representing one-dimensional cellular automaton
 * @author Wojciech Kumoń
 */
public abstract class Automaton1Dim extends Automaton {
	private int size;

	/**
	 * Constructor which creates one-dimensional automaton with all cells, which
	 * are set by CellStateFactory.
	 * @param factory CellStateFactory to initialize new cell states
	 * @param neighborhood CellNeighborhood which decides which cells are neighbors
	 * @param size amount of cells in automaton
	 */
	public Automaton1Dim(CellStateFactory factory, CellNeighborhood neighborhood, int size) {
		super(factory, neighborhood);
		this.size = size;

		Map<CellCoordinates, CellState> structure = new HashMap<>();
		for (int i = 0; i < size; ++i) {
			Coords1D coords = new Coords1D(i);
			structure.put(coords, factory.initialState(coords));
		}
		insertStructure(structure);
	}

	/**
	 * Checks if sent coords have next.
	 * @param coords coords must be a subclass of Coords1D
	 * @return true if coords have next, false otherwise.
	 */
	@Override
	protected boolean hasNextCoordinates(CellCoordinates coords) {
		Coords1D coords1d = (Coords1D) coords;
		return (coords1d.x >= -1) && (coords1d.x + 1 < size);
	}
	
	/**
	 * Creates initial coordinates - coords before first acceptable.
	 * @return CellCoordinates which are before first possible.
	 */
	@Override
	protected CellCoordinates initialCoordinates() {
		return new Coords1D(-1);
	}

	/**
	 * Returns next coordinates. It may return wrong coords, so you have to
	 * check it using hasNextCoordinates().
	 * @param coords coords must be a subclass of Coords1D. It is base to return next.
	 * @return next coordinates
	 */
	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates coords) {
		Coords1D coords1d = (Coords1D) coords;
		return new Coords1D(coords1d.x + 1);
	}

	/**
	 * Returns amount of cells in one-dimensional automaton
	 * @return amount of cells
	 */
	public int getSize() {
		return size;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 31 + getSize();
		for (Cell cell : this) {
			hashCode = 31 * hashCode + cell.hashCode();
		}
		return hashCode;
	};
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !getClass().equals(obj.getClass())) {
			return false;
		}
		Automaton1Dim automaton2Dim = (Automaton1Dim) obj;
		if (getSize() != automaton2Dim.getSize()) {
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
