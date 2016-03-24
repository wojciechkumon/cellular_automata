package pl.edu.agh.kis.automaton.model;

import static pl.edu.agh.kis.automaton.model.BinaryState.convertFromBoolean;
import static pl.edu.agh.kis.automaton.model.BinaryState.convertToBoolean;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Class representing one-dimensional Automaton.
 * @author Wojciech Kumoń
 */
public class OneDimAutomaton extends Automaton1Dim {
	private static int DEFAULT_RULE = 30;
	private Map<OneDimNeighboring, Boolean> rules = new HashMap<>(8);

	/**
	 * Creates one-dimensional automaton rule 30.
	 * @param factory CellStateFactory to initialize new cell states
	 * @param neighborhood CellNeighborhood which decides which cells are neighbors
	 * @param size amount of cells in automaton
	 */
	public OneDimAutomaton(CellStateFactory factory, CellNeighborhood neighborhood, int size) {
		super(factory, neighborhood, size);
		setRule(DEFAULT_RULE);
	}
	
	/**
	 * Returns OneDimAutomaton automaton with same size and rule.
	 * @return OneDimAutomaton automaton with same size and rule.
	 */
	@Override
	protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
		OneDimAutomaton newAutomaton = new OneDimAutomaton(factory, neighborhood, getSize());
		newAutomaton.setRules(rules);
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
		Coords1D currentCellCoords = (Coords1D) currentCell.coords;
		BinaryState leftNeighBorState = getLeftNeighborState(currentCellCoords, neighborsStates);
		BinaryState middleCellState = (BinaryState) currentCell.state;
		BinaryState rightNeighBorState = getRightNeighborState(currentCellCoords, neighborsStates);

		OneDimNeighboring fieldsState = OneDimNeighboring.getDim1Possibility(convertToBoolean(leftNeighBorState),
				convertToBoolean(middleCellState), convertToBoolean(rightNeighBorState));

		return convertFromBoolean(rules.get(fieldsState));
	}

	private BinaryState getLeftNeighborState(Coords1D currentCoords, Set<Cell> neighborsStates) {
		for (Cell neighbor : neighborsStates) {
			Coords1D neighborCoords = (Coords1D) neighbor.coords;
			if (neighborCoords.x + 1 == currentCoords.x) {
				return (BinaryState) neighbor.state;
			}
		}
		return BinaryState.DEAD;
	}

	private BinaryState getRightNeighborState(Coords1D currentCoords, Set<Cell> neighborsStates) {
		for (Cell neighbor : neighborsStates) {
			Coords1D neighborCoords = (Coords1D) neighbor.coords;
			if (neighborCoords.x - 1 == currentCoords.x) {
				return (BinaryState) neighbor.state;
			}
		}
		return BinaryState.DEAD;
	}

	private boolean booleanFromChar(char c) {
		if (c == '1') {
			return true;
		}
		return false;
	}

	private void setRules(Map<OneDimNeighboring, Boolean> rulesMap) {
		for (Entry<OneDimNeighboring, Boolean> entry : rulesMap.entrySet()) {
			rules.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Sets rule. Allowed rules 0-255.
	 * @param rule 0-255 rule
	 */
	public final void setRule(int rule) {
		String rulesString = String.format("%8s", Integer.toBinaryString(rule)).replace(' ', '0');
		rules.put(OneDimNeighboring.ALIVE_ALIVE_ALIVE, booleanFromChar(rulesString.charAt(0)));
		rules.put(OneDimNeighboring.ALIVE_ALIVE_DEAD, booleanFromChar(rulesString.charAt(1)));
		rules.put(OneDimNeighboring.ALIVE_DEAD_ALIVE, booleanFromChar(rulesString.charAt(2)));
		rules.put(OneDimNeighboring.ALIVE_DEAD_DEAD, booleanFromChar(rulesString.charAt(3)));
		rules.put(OneDimNeighboring.DEAD_ALIVE_ALIVE, booleanFromChar(rulesString.charAt(4)));
		rules.put(OneDimNeighboring.DEAD_ALIVE_DEAD, booleanFromChar(rulesString.charAt(5)));
		rules.put(OneDimNeighboring.DEAD_DEAD_ALIVE, booleanFromChar(rulesString.charAt(6)));
		rules.put(OneDimNeighboring.DEAD_DEAD_DEAD, booleanFromChar(rulesString.charAt(7)));
	}
	
	/**
	 * Gets current rule.
	 * @return current rule (0-255).
	 */
	public int getRule() {
		StringBuilder ruleString = new StringBuilder();
		addToString(OneDimNeighboring.ALIVE_ALIVE_ALIVE, ruleString);
		addToString(OneDimNeighboring.ALIVE_ALIVE_DEAD, ruleString);
		addToString(OneDimNeighboring.ALIVE_DEAD_ALIVE, ruleString);
		addToString(OneDimNeighboring.ALIVE_DEAD_DEAD, ruleString);
		addToString(OneDimNeighboring.DEAD_ALIVE_ALIVE, ruleString);
		addToString(OneDimNeighboring.DEAD_ALIVE_DEAD, ruleString);
		addToString(OneDimNeighboring.DEAD_DEAD_ALIVE, ruleString);
		addToString(OneDimNeighboring.DEAD_DEAD_DEAD, ruleString);
		return Integer.parseInt(ruleString.toString(), 2);
	}

	private void addToString(OneDimNeighboring dim1Possibility, StringBuilder ruleString) {
		if (rules.get(dim1Possibility)) {
			ruleString.append('1');
		} else {
			ruleString.append('0');
		}
	}
	
}
