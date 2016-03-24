package pl.edu.agh.kis.automaton.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing Langton's ant Automaton.
 * @author Wojciech Kumoń
 */
public class LangtonAnt extends Automaton2Dim {
	/**
	 * Creates LangtonAnt
	 * @param factory CellStateFactory to initialize new cell states
	 * @param neighborhood CellNeighborhood which decides which cells are neighbors
	 * @param width amount of cells in automaton row
	 * @param height amount of cells in automaton column
	 */
	public LangtonAnt(CellStateFactory factory, CellNeighborhood neighborhood, int width, int height) {
		super(factory, neighborhood, width, height);
	}

	/**
	 * Returns LangtonAnt automaton with same size.
	 * @return LangtonAnt automaton with same size.
	 */
	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
		return new LangtonAnt(factory, neighborhood, getWidth(), getHeight());
	}

	/**
	 * Calculates and returns next state for sent cell, which has sent neighbors.
	 * @param currentCell cell which next state will be calculated.
	 * @param neighborsStates set of cell which are currentCell neighbors.
	 * @return new LangtonCell in next iteration based on Langton's ant rules
	 */
	@Override
	protected CellState newCellState(Cell currentCell, Set<Cell> neighborsStates) {
		LangtonCell newLangtonCell = (LangtonCell) currentCell.state;
		Set<Cell> neighborsWithAnt = getNeighborsWithAnt(neighborsStates);

		if (newLangtonCell.antState != AntState.NONE) {
			newLangtonCell = new LangtonCell(BinaryState.reverseState(newLangtonCell.cellState), AntState.NONE, -1);
		}

		newLangtonCell = setAntIfNecessary(currentCell, newLangtonCell, neighborsWithAnt, newLangtonCell);
		return newLangtonCell;
	}
	
	private Set<Cell> getNeighborsWithAnt(Set<Cell> neighborsStates) {
		Set<Cell> neighborsWithAnt = new HashSet<>();
		for (Cell cell : neighborsStates) {
			LangtonCell langtonCell = (LangtonCell) cell.state;
			if (langtonCell.antState != AntState.NONE) {
				neighborsWithAnt.add(cell);
			}
		}
		return neighborsWithAnt;
	}

	private LangtonCell setAntIfNecessary(Cell currentCell, LangtonCell currentState, Set<Cell> neighborsWithAnt,
			LangtonCell newLangtonCell) {
		for (Cell cell : neighborsWithAnt) {
			if (antGoesHere(currentCell, cell)) {
				LangtonCell lagtonCell = (LangtonCell) cell.state;
				newLangtonCell = new LangtonCell(currentState.cellState, AntState.getRotatedState(lagtonCell),
						lagtonCell.antId);
			}
		}
		return newLangtonCell;
	}

	private boolean antGoesHere(Cell currentCell, Cell antCell) {
		Coords2D currentCoords = (Coords2D) currentCell.coords;
		Coords2D antCoords = (Coords2D) antCell.coords;
		LangtonCell langtonCell = (LangtonCell) antCell.state;
		AntState newAntState = AntState.getRotatedState(langtonCell);
		if (newAntState == AntState.NORTH && new Coords2D(antCoords.row - 1, antCoords.col).equals(currentCoords)) {
			return true;
		}
		if (newAntState == AntState.EAST && new Coords2D(antCoords.row, antCoords.col + 1).equals(currentCoords)) {
			return true;
		}
		if (newAntState == AntState.SOUTH && new Coords2D(antCoords.row + 1, antCoords.col).equals(currentCoords)) {
			return true;
		}
		if (newAntState == AntState.WEST && new Coords2D(antCoords.row, antCoords.col - 1).equals(currentCoords)) {
			return true;
		}
		return checkBoardSides(newAntState, currentCoords, antCoords);
	}

	private boolean checkBoardSides(AntState newAntState, Coords2D currentCoords, Coords2D antCoords) {
		if (currentCoords.col == 0 && newAntState == AntState.EAST
				&& antCoords.equals(new Coords2D(currentCoords.row, getWidth() - 1))) {
			return true;
		}
		if (currentCoords.col == getWidth() - 1 && newAntState == AntState.WEST
				&& antCoords.equals(new Coords2D(currentCoords.row, 0))) {
			return true;
		}
		if (currentCoords.row == 0 && newAntState == AntState.SOUTH
				&& antCoords.equals(new Coords2D(getHeight() - 1, currentCoords.col))) {
			return true;
		}
		if (currentCoords.row == getHeight() - 1 && newAntState == AntState.NORTH
				&& antCoords.equals(new Coords2D(0, currentCoords.col))) {
			return true;
		}
		return false;
	}

}
