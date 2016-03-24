package pl.edu.agh.kis.automaton.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Abstract class representing cellular automaton, which implements Iterable&lt;Cell&gt;.
 * @author Wojciech Kumoń
 */
public abstract class Automaton implements Iterable<Cell> {
	private Map<CellCoordinates, CellState> cells = new HashMap<>();
	private CellStateFactory stateFactory;
	private CellNeighborhood neighborsStrategy;

	/*
	 * Sets CellStateFactory and CellNeighborhood for use.
	 * @param factory CellStateFactory which allows to initialize Cells.
	 * @param neighborhood CellNeighborhood which allows to get cell neighbors.
	 */
	public Automaton(CellStateFactory factory, CellNeighborhood neighborhood) {
		this.stateFactory = factory;
		this.neighborsStrategy = neighborhood;
	}

	/**
	 * Calculates and returns Automaton with cells after one iteration.
	 * @return Subclass of Automaton matching to this class.
	 */
	public Automaton nextState() {
		Automaton newAutomaton = newInstance(stateFactory, neighborsStrategy);
		CellIterator iter = newAutomaton.iterator();

		while (iter.hasNext()) {
			Cell cell = iter.next();
			Set<CellCoordinates> neighborsCoords = neighborsStrategy.cellNeighbors(cell.coords);
			Set<Cell> neighbors = mapCoordinates(neighborsCoords);
			CellState newState = newCellState(new Cell(cell.coords, cells.get(cell.coords)), neighbors);
			iter.setState(newState);
		}
		return newAutomaton;
	}

	/**
	 * Inserts structure of cells into automaton, which is represented by
	 * CellCoordinates mapped to CellState.
	 * @param structure structure of cells
	 */
	public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure) {
		cells.putAll(structure);
	}

	/**
	 * Allows changing neighborhood after automaton creation.
	 * @param neighborhood CellNeighborhood to set.
	 */
	public void setNeighborhood(CellNeighborhood neighborhood) {
		neighborsStrategy = neighborhood;
	}

	/**
	 * Returns CellIterator (subclass of Iterator&lt;Cell&gt;).
	 * @return CellIterator
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public CellIterator iterator() {
		return new CellIterator();
	}

	/**
	 * Allows iterating over automaton cells.
	 */
	class CellIterator implements Iterator<Cell> {
		private CellCoordinates currentCoords;

		private CellIterator() {
			currentCoords = initialCoordinates();
		}

		/**
		 * Checks if next coordinate exist.
		 * @return Returns true if next coordinate exists, false otherwise.
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return hasNextCoordinates(currentCoords);
		}

		/**
		 * Returns Cell and moves pointer to next Cell.
		 * @return next cell
		 * @see java.util.Iterator#next()
		 */
		public Cell next() {
			currentCoords = nextCoordinates(currentCoords);
			return new Cell(currentCoords, cells.get(currentCoords));
		}

		/**
		 * Sets new state for previous CellCoordinates (for last CellCoordinates
		 * returned by next() method).
		 * @param state CellState to set
		 */
		public void setState(CellState state) {
			cells.put(currentCoords, state);
		}
	}

	/**
	 * Returns automaton subclass equals to this automaton with all same attributes.
	 * @param factory factory to set
	 * @param neighborhood neighborhood to set
	 * @return automaton subclass equals to this automaton with all same attributes.
	 */
	protected abstract Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood);

	/**
	 * Checks if sent CellCoordinates have next.
	 * @param coords coordinates to check
	 * @return true if coords have next, false otherwise.
	 */
	protected abstract boolean hasNextCoordinates(CellCoordinates coords);

	/**
	 * Creates initial coordinates - coords before first acceptable.
	 * @return CellCoordinates which are before first possible.
	 */
	protected abstract CellCoordinates initialCoordinates();

	/**
	 * Returns next coordinates. It may return wrong coords, so you have to
	 * check it using hasNextCoordinates().
	 * @param coords CellCoordinates which are base to return next.
	 * @return next coordinates
	 */
	protected abstract CellCoordinates nextCoordinates(CellCoordinates coords);

	/**
	 * Calculates and return next state for sent cell, which has sent neighbors.
	 * @param currentCell cell which next state will be calculated.
	 * @param neighborsStates set of cell which are currentCell neighbors.
	 * @return next state of cell
	 */
	protected abstract CellState newCellState(Cell currentCell, Set<Cell> neighborsStates);

	private Set<Cell> mapCoordinates(Set<CellCoordinates> coords) {
		Set<Cell> cellSet = new HashSet<>();
		coords.forEach(coord -> cellSet.add(new Cell(coord, cells.get(coord))));
		return cellSet;
	}

}
