package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import java.awt.Dimension;

import org.junit.Test;

public class TwoDimNeighborhoodFactoryTest {

	@Test
	public void testCreatingMooreNeighborhood() {
		CellNeighborhood neighborhood = TwoDimNeighborhoodFactory.create(TwoDimNeighborhoodType.MOORE, BoardType.LIMITTED_BOARD,
				new Dimension(2, 2), 1);
		assertTrue(neighborhood instanceof MooreNeighborhood);
	}

	@Test
	public void testCreatingVonNeumannNeighborhood() {
		CellNeighborhood neighborhood = TwoDimNeighborhoodFactory.create(TwoDimNeighborhoodType.VON_NEUMANN,
				BoardType.LIMITTED_BOARD, new Dimension(2, 2), 1);
		assertTrue(neighborhood instanceof VonNeumannNeighborhood);
	}

}
