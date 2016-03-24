package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LimittedBoardTest {

	@Test
	public void testGettingNulls() {
		LimittedBoard strategy = new LimittedBoard();
		for (int i = -2; i < 10; ++i) {
			for (int j = -1; j < 10; ++j) {
				assertNull(strategy.getCorrectCoords(new Coords2D(i, j)));
			}
		}
	}

}
