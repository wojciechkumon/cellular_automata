package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {

	@Test
	public void testHashCode() {
		Cell cell = new Cell(new Coords1D(0), BinaryState.DEAD);
		assertEquals(cell.hashCode(), new Cell(new Coords1D(0), BinaryState.DEAD).hashCode());
	}
	
	@Test
	public void testEquals() {
		Cell cell = new Cell(new Coords1D(0), BinaryState.DEAD);
		assertTrue(cell.equals(new Cell(new Coords1D(0), BinaryState.DEAD)));
		assertFalse(cell.equals(new Cell(new Coords1D(1), BinaryState.DEAD)));
		assertFalse(cell.equals(new Cell(new Coords1D(0), BinaryState.ALIVE)));
		assertFalse(cell.equals(null));
		assertFalse(cell.equals(new Object()));
	}

}
