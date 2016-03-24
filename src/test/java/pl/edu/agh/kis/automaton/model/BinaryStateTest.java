package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryStateTest {

	@Test
	public void testReversing() {
		assertEquals(BinaryState.DEAD, BinaryState.reverseState(BinaryState.ALIVE));
		assertEquals(BinaryState.ALIVE, BinaryState.reverseState(BinaryState.DEAD));
	}
	
	@Test
	public void testConvertingFromBoolean() {
		assertEquals(BinaryState.ALIVE, BinaryState.convertFromBoolean(true));
		assertEquals(BinaryState.DEAD, BinaryState.convertFromBoolean(false));
	}
	
	@Test
	public void testConvertingToBoolean() {
		assertEquals(true, BinaryState.convertToBoolean(BinaryState.ALIVE));
		assertEquals(false, BinaryState.convertToBoolean(BinaryState.DEAD));
	}

}
