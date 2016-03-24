package pl.edu.agh.kis.automaton.model;

import java.util.Objects;

/**
 * Two-dimensional coordinates.
 * @author Wojciech Kumoń
 */
public class Coords2D implements CellCoordinates {
	/**
	 * vertiacal coordinate
	 */
	public final int row;
	
	/**
	 * horizontal coordinate
	 */
	public final int col;
	
	/**
	 * Constructor which sets blank final coords row and col.
	 * @param row vertiacal coordinate to set
	 * @param col horizontal coordinate to set
	 */
	public Coords2D(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(row, col);
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null || !getClass().equals(object.getClass())) {
			return false;
		}
		Coords2D secondCoords = (Coords2D) object;
		return Objects.equals(col, secondCoords.col) && Objects.equals(row, secondCoords.row);
	}
	
	@Override
	public String toString() {
		return "(" + row + "," + col + ")";
	}
	
}
