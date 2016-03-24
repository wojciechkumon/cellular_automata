package pl.edu.agh.kis.automaton.model;

/**
 * One-dimensional coordinates.
 * @author Wojciech Kumoń
 */
public class Coords1D implements CellCoordinates {
	/**
	 * coordinate
	 */
	public final int x;
	
	/**
	 * Constructor which sets blank final coord x.
	 * @param x coordinate to set
	 */
	public Coords1D(int x) {
		this.x = x;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(x);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass()) {
			return false;
		}
		return x == ((Coords1D) o).x;
	}
	
	@Override
	public String toString() {
		return "(" + x + ")";
	}
	
}
