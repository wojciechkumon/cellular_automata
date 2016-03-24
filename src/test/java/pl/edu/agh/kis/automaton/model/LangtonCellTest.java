package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LangtonCellTest {

	@Test
	public void testHashcode() {
		LangtonCell cell1 = new LangtonCell(BinaryState.DEAD, AntState.NORTH, 5);
		LangtonCell cell2 = new LangtonCell(BinaryState.DEAD, AntState.NORTH, 5);
		assertEquals(cell1.hashCode(), cell2.hashCode());
	}
	
	@Test
	public void testEqualsWithCorrectData() {
		LangtonCell cell = new LangtonCell(BinaryState.DEAD, AntState.NORTH, 5);
		assertTrue(cell.equals( new LangtonCell(BinaryState.DEAD, AntState.NORTH, 5)));
	}
	
	@Test
	public void testEqualsWithNull() {
		LangtonCell cell = new LangtonCell(BinaryState.DEAD, AntState.NORTH, 5);
		assertFalse(cell.equals(false));
	}
	
	@Test
	public void testEqualsWithWrongObjectClass() {
		LangtonCell cell = new LangtonCell(BinaryState.DEAD, AntState.NORTH, 5);
		assertFalse(cell.equals(new Object()));
	}
	
	
	@Test
	public void testEqualsWithWrongData() {
		LangtonCell cell1 = new LangtonCell(BinaryState.DEAD, AntState.NORTH, 5);
		assertFalse(cell1.equals(new LangtonCell(BinaryState.DEAD, AntState.NONE, 5)));
		assertFalse(cell1.equals(new LangtonCell(BinaryState.ALIVE, AntState.NORTH, 5)));
		assertFalse(cell1.equals(new LangtonCell(BinaryState.DEAD, AntState.NORTH, 7)));
	}
	
	
	@Test
	public void testToString() {
		LangtonCell cell1 = new LangtonCell(BinaryState.DEAD, AntState.NORTH, 5);
		assertEquals("DEAD, NORTH id=5", cell1.toString());
	}

}
