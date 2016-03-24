package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Coords2DTest {
	private Coords2D coords53;
	
	@Before
	public void init() {
		coords53 = new Coords2D(5, 3);
	}

	@Test
	public void testHashCode() {
		assertEquals(coords53.hashCode(), new Coords2D(5, 3).hashCode());
		assertNotEquals(coords53.hashCode(), new Coords2D(5, 4).hashCode());
	}
	
	@Test
	public void testEqualsWithNull() {
		assertFalse(coords53.equals(null));
	}
	
	@Test
	public void testEqualsWithOtherClass() {
		assertFalse(coords53.equals(new Object()));
	}
	
	@Test
	public void testEqualsWithWrongData() {
		assertFalse(coords53.equals(new Coords2D(5, 7)));
		assertFalse(coords53.equals(new Coords2D(4, 3)));
		assertFalse(coords53.equals(new Coords2D(0, 0)));
	}
	
	@Test
	public void testEqualsWithRightData() {
		assertTrue(coords53.equals(new Coords2D(5, 3)));
	}
	
	@Test
	public void testToString() {
		assertEquals("(5,3)", coords53.toString());
	}

}
