package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class OneDimNeighborhoodTest {

	@Test
	public void testGettingNeighborsForLeftEnd() {
		OneDimNeighborhood neighborhood = new OneDimNeighborhood(3);
		Coords1D coords = new Coords1D(0);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(coords);
		assertEquals(1, neighbors.size());
		assertEquals(new Coords1D(1), neighbors.iterator().next());
	}
	
	@Test
	public void testGettingNeighborsForRightEnd() {
		OneDimNeighborhood neighborhood = new OneDimNeighborhood(3);
		Coords1D coords = new Coords1D(2);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(coords);
		assertEquals(1, neighbors.size());
		assertEquals(new Coords1D(1), neighbors.iterator().next());
	}
	
	@Test
	public void testGettingNeighborsForMiddleCell() {
		OneDimNeighborhood neighborhood = new OneDimNeighborhood(3);
		Coords1D coords = new Coords1D(1);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(coords);
		assertEquals(2, neighbors.size());
		assertTrue(neighbors.contains(new Coords1D(0)));
		assertTrue(neighbors.contains(new Coords1D(2)));
	}

}
