package pl.edu.agh.kis.automaton.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing Game of Life Automaton.
 * @author Wojciech Kumoń
 */
public class GameOfLife extends Automaton2Dim {
	private Set<Integer> brithRules = new HashSet<>(9);
	private Set<Integer> stayingAliveRules = new HashSet<>(9);
	
	/**
	 * Creates GameOfLife with standard rules (23/3).
	 * @param factory CellStateFactory to initialize new cell states
	 * @param neighborhood CellNeighborhood which decides which cells are neighbors
	 * @param width amount of cells in automaton row
	 * @param height amount of cells in automaton column
	 */
	public GameOfLife(CellStateFactory factory, CellNeighborhood neighborhood, int width, int height) {
		super(factory, neighborhood, width, height);
		brithRules.add(3);
		stayingAliveRules.add(2);
		stayingAliveRules.add(3);
	}
	
	/**
	 * Returns GameOfLife automaton with same size and rules.
	 * @return GameOfLife automaton with same size and rules.
	 */
	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
		GameOfLife newAutomaton = new GameOfLife(factory, neighborhood, getWidth(), getHeight());
		newAutomaton.setBirthRules(brithRules);
		newAutomaton.setStayingAliveRules(stayingAliveRules);
		return newAutomaton;
	}
	
	/**
	 * Calculates and returns next state for sent cell, which has sent neighbors.
	 * @param currentCell cell which next state will be calculated.
	 * @param neighborsStates set of cell which are currentCell neighbors.
	 * @return next binary state of cell based on set rules
	 */
	@Override
	protected CellState newCellState(Cell currentCell, Set<Cell> neighborsStates) {
		BinaryState state = (BinaryState) currentCell.state;
		int aliveNeighbors = countAliveNeighbors(neighborsStates);
		switch (state) {
		case ALIVE:
			return getNewStateForAliveCell(aliveNeighbors);
		case DEAD:
		default:
			return getNewStateForDeadCell(aliveNeighbors);
		}
	}
	
	private int countAliveNeighbors(Set<Cell> neighborsStates) {
		int aliveCells = 0;
		for (Cell cell : neighborsStates) {
			if (cell.state == BinaryState.ALIVE) {
				++aliveCells;
			}
		}
		return aliveCells;
	}
	
	private CellState getNewStateForDeadCell(int aliveNeighbors) {
		if (brithRules.contains(aliveNeighbors)) {
			return BinaryState.ALIVE;
		} else {
			return BinaryState.DEAD;
		}
	}

	private CellState getNewStateForAliveCell(int aliveNeighbors) {
		if (stayingAliveRules.contains(aliveNeighbors)) {
			return BinaryState.ALIVE;
		} else {
			return BinaryState.DEAD;
		}
	}
	
	/**
	 * Sets Game of Life birth rules
	 * @param birthRules set of Integers, which represents numbers of alive neighbors,
	 *            when cell can become alive.
	 */
	public void setBirthRules(Set<Integer> birthRules) {
		this.brithRules.clear();
		for(Integer i : birthRules) {
			this.brithRules.add(i);
		}
	}
	
	/**
	 * Sets Game of Life staying alive rules
	 * @param stayingAliveRules set of Integers, which represents numbers of alive neighbors,
	 *            when alive cell can be still alive.
	 */
	public void setStayingAliveRules(Set<Integer> stayingAliveRules) {
		this.stayingAliveRules.clear();
		stayingAliveRules.forEach(i -> this.stayingAliveRules.add(i));
	}

	/**
	 * Gives information if sent number of alive neighbors allows cell to stay alive.
	 * @param numberOfAliveNeighbors number of alive neighbors.
	 * @return true if numberOfAliveNeighbors allows cell to stay alive, false otherwise.
	 */
	public boolean isStayingAliveRuleSet(int numberOfAliveNeighbors) {
		return stayingAliveRules.contains(numberOfAliveNeighbors);
	}
	
	/**
	 * Gives information if sent number of alive neighbors makes dead cell alive.
	 * @param numberOfAliveNeighbors number of alive neighbors.
	 * @return true if numberOfAliveNeighbors makes dead cell alive, false otherwise.
	 */
	public boolean isBirthRuleSet(int numberOfAliveNeighbors) {
		return brithRules.contains(numberOfAliveNeighbors);
	}

}
