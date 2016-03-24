package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AntStateTest {

	@Test
	public void testRotatingLeft() {
		assertEquals(AntState.WEST, AntState.rotateLeft(AntState.NORTH));
		assertEquals(AntState.SOUTH, AntState.rotateLeft(AntState.WEST));
		assertEquals(AntState.EAST, AntState.rotateLeft(AntState.SOUTH));
		assertEquals(AntState.NORTH, AntState.rotateLeft(AntState.EAST));
		assertEquals(AntState.NONE, AntState.rotateLeft(AntState.NONE));
	}
	
	@Test
	public void testRotatingRight() {
		assertEquals(AntState.EAST, AntState.rotateRight(AntState.NORTH));
		assertEquals(AntState.SOUTH, AntState.rotateRight(AntState.EAST));
		assertEquals(AntState.WEST, AntState.rotateRight(AntState.SOUTH));
		assertEquals(AntState.NORTH, AntState.rotateRight(AntState.WEST));
		assertEquals(AntState.NONE, AntState.rotateRight(AntState.NONE));
	}
	
	@Test
	public void testRotatingByBinaryState() {
		assertEquals(AntState.WEST, AntState.getRotatedState(new LangtonCell(BinaryState.DEAD, AntState.NORTH, 1)));
		assertEquals(AntState.EAST, AntState.getRotatedState(new LangtonCell(BinaryState.ALIVE, AntState.NORTH, 1)));
	}

}
