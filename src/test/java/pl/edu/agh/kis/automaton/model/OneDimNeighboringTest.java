package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;
import static pl.edu.agh.kis.automaton.model.OneDimNeighboring.*;

import org.junit.Test;

public class OneDimNeighboringTest {

	@Test
	public void testGettingAliveAliveAlive() {
		assertEquals(ALIVE_ALIVE_ALIVE, getDim1Possibility(true, true, true));
	}
	
	@Test
	public void testGettingAliveAliveDead() {
		assertEquals(ALIVE_ALIVE_DEAD, getDim1Possibility(true, true, false));
	}
	
	@Test
	public void testGettingAliveDeadAlive() {
		assertEquals(ALIVE_DEAD_ALIVE, getDim1Possibility(true, false, true));
	}
	
	@Test
	public void testGettingAliveDeadDead() {
		assertEquals(ALIVE_DEAD_DEAD, getDim1Possibility(true, false, false));
	}
	
	@Test
	public void testGettingDeadAliveAlive() {
		assertEquals(DEAD_ALIVE_ALIVE, getDim1Possibility(false, true, true));
	}
	
	@Test
	public void testGettingDeadAliveDead() {
		assertEquals(DEAD_ALIVE_DEAD, getDim1Possibility(false, true, false));
	}
	
	@Test
	public void testGettingDeadDeadAlive() {
		assertEquals(DEAD_DEAD_ALIVE, getDim1Possibility(false, false, true));
	}
	
	@Test
	public void testGettingDeadDeadDead() {
		assertEquals(DEAD_DEAD_DEAD, getDim1Possibility(false, false, false));
	}

}
