package pl.edu.agh.kis.automaton.model;

import java.util.Set;

/**
 * Class representing Quad Life Automaton.
 * @author Wojciech Kumoń
 */
public class QuadLife extends Automaton2Dim {

	/**
	 * Creates QuadLife with standard rules (23/3).
	 * @param factory CellStateFactory to initialize new cell states
	 * @param neighborhood CellNeighborhood which decides which cells are neighbors
	 * @param width amount of cells in automaton row
	 * @param height amount of cells in automaton column
	 */
	public QuadLife(CellStateFactory factory, CellNeighborhood neighborhood, int width, int height) {
		super(factory, neighborhood, width, height);
	}

	/**
	 * Returns QuadLife automaton with same size.
	 * @return QuadLife automaton with same size.
	 */
	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
		return new QuadLife(factory, neighborhood, getWidth(), getHeight());
	}

	/**
	 * Calculates and returns next state for sent cell, which has sent neighbors. 
	 * Based on Quad Life rules.
	 * @param currentCell cell which next state will be calculated.
	 * @param neighborsStates set of cell which are currentCell neighbors.
	 * @return next iteration quad state of cell
	 */
	@Override
	protected CellState newCellState(Cell currentCell, Set<Cell> neighborsStates) {
		QuadState state = (QuadState) currentCell.state;
		switch (state) {
		case DEAD:
			return getNewStateForDeadCell(neighborsStates);
		default:
			return getNewStateForAliveCell(currentCell.state, neighborsStates);
		}
	}

	private CellState getNewStateForDeadCell(Set<Cell> neighborsStates) {
		CellColorCounter counter = CellColorCounter.getCellColorCounter(neighborsStates);
		return counter.getQuadLifeStateForNewCell();
	}

	private CellState getNewStateForAliveCell(CellState currentState, Set<Cell> neighborsStates) {
		int aliveNeighbors = countAliveNeighbors(neighborsStates);
		if (aliveNeighbors == 2 || aliveNeighbors == 3)
			return currentState;
		return QuadState.DEAD;
	}

	private int countAliveNeighbors(Set<Cell> neighborsStates) {
		int aliveCells = 0;
		for (Cell cell : neighborsStates) {
			if (cell.state != QuadState.DEAD) {
				++aliveCells;
			}
		}
		return aliveCells;
	}

}