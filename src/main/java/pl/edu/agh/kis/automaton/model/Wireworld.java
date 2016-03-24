package pl.edu.agh.kis.automaton.model;

import static pl.edu.agh.kis.automaton.model.WireElectronState.ELECTRON_HEAD;
import static pl.edu.agh.kis.automaton.model.WireElectronState.ELECTRON_TAIL;
import static pl.edu.agh.kis.automaton.model.WireElectronState.VOID;
import static pl.edu.agh.kis.automaton.model.WireElectronState.WIRE;

import java.util.Set;

/**
 * Class representing Wireworld Automaton.
 * @author Wojciech Kumoń
 */
public class Wireworld extends Automaton2Dim {
	/**
	 * Creates Wireworld
	 * @param factory CellStateFactory to initialize new cell states
	 * @param neighborhood CellNeighborhood which decides which cells are neighbors
	 * @param width amount of cells in automaton row
	 * @param height amount of cells in automaton column
	 */
	public Wireworld(CellStateFactory factory, CellNeighborhood neighborhood, int width, int height) {
		super(factory, neighborhood, width, height);
	}
	
	/**
	 * Returns Wireworld automaton with same size.
	 * @return Wireworld automaton with same size.
	 */
	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
		return new Wireworld(factory, neighborhood, getWidth(), getHeight());
	}

	/**
	 * Calculates and returns next state for sent cell, which has sent neighbors.
	 * @param currentCell cell which next state will be calculated.
	 * @param neighborsStates set of cell which are currentCell neighbors.
	 * @return WireElectronState in next iteration based on Wireworld rules
	 */
	@Override
	protected CellState newCellState(Cell currentCell, Set<Cell> neighborsStates) {
		WireElectronState state = (WireElectronState) currentCell.state;
		if (state == VOID)
			return VOID;
		if (state == WIRE)
			return getStateFromWire(neighborsStates);
		if (state == ELECTRON_HEAD)
			return ELECTRON_TAIL;
		return WIRE;
	}

	private CellState getStateFromWire(Set<Cell> neighborsStates) {
		int electronHeads = countElectronHeads(neighborsStates);
		boolean changeToElectronHead = (electronHeads == 1) || (electronHeads == 2);
		if (changeToElectronHead)
			return ELECTRON_HEAD;
		return WIRE;
	}

	private int countElectronHeads(Set<Cell> neighborsStates) {
		int electronHeadsCounter = 0;
		for (Cell cell : neighborsStates) {
			WireElectronState cellState = (WireElectronState) cell.state;
			if (cellState == ELECTRON_HEAD)
				++electronHeadsCounter;
		}
		return electronHeadsCounter;
	}

}
