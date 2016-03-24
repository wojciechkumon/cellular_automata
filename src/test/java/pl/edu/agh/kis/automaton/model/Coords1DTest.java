package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Coords1DTest {
	private Coords1D coords7;
	
	@Before
	public void init() {
		coords7 = new Coords1D(7);
	}

	@Test
	public void testHashCode() {
		assertEquals(coords7.hashCode(), new Coords1D(7).hashCode());
		assertNotEquals(coords7.hashCode(), new Coords1D(8).hashCode());
	}
	
	@Test
	public void testEqualsWithNull() {
		assertFalse(coords7.equals(null));
	}
	
	@Test
	public void testEqualsWithOtherClass() {
		assertFalse(coords7.equals(new Object()));
	}
	
	@Test
	public void testEqualsWithWrongData() {
		assertFalse(coords7.equals(new Coords1D(-7)));
	}
	
	@Test
	public void testEqualsWithRightData() {
		assertTrue(coords7.equals(new Coords1D(7)));
	}
	
	@Test
	public void testToString() {
		assertEquals("(7)", coords7.toString());
	}
	

}
