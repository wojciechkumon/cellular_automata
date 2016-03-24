package pl.edu.agh.kis.automaton.model;

/**
 * Enum representing cell states in Wireworld.
 * @author Wojciech Kumoń
 */
public enum WireElectronState implements CellState {
	VOID, WIRE, ELECTRON_HEAD, ELECTRON_TAIL;
}
