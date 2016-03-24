package pl.edu.agh.kis.automaton.model;

import java.util.Objects;

/**
 * Class representing automaton cell.
 * @author Wojciech Kumoń
 */
public class Cell {
	/**
	 * final field representing CellCoordinates
	 */
	public final CellCoordinates coords;
	/**
	 * final field representing CellState
	 */
	public final CellState state;
	
	/**
	 * Contructor which sets blank final fields
	 * @param coords coords to set
	 * @param state state to set
	 */
	public Cell(CellCoordinates coords, CellState state) {
		this.coords = coords;
		this.state = state;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(coords, state);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !getClass().equals(obj.getClass())) {
			return false;
		}
		Cell cell = (Cell) obj;
		return Objects.equals(coords, cell.coords) && Objects.equals(state, cell.state);
	}
	
}
