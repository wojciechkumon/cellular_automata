package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;
import java.util.Set;

import org.junit.Test;

public class VonNeumannNeighborhoodTest {
	private int width = 3;
	private int height = 4;
	private WrongCoordsHandler notChangingStrategy = c -> c;
	private WrongCoordsHandler nullStrategy = c -> null;

	@Test
	public void testNeighborsForTopLeftCorner() {
		VonNeumannNeighborhood neighborhood = new VonNeumannNeighborhood(width, height, notChangingStrategy);
		Coords2D coords = new Coords2D(0, 0);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(coords);
		check4Neighbors(neighbors, coords.col, coords.row);
	}

	@Test
	public void testNeighborsForBottomRightCorner() {
		VonNeumannNeighborhood neighborhood = new VonNeumannNeighborhood(width, height, notChangingStrategy);
		Coords2D coords = new Coords2D(height - 1, width - 1);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(coords);
		check4Neighbors(neighbors, coords.col, coords.row);
	}

	private void check4Neighbors(Set<CellCoordinates> neighbors, int coordX, int coordY) {
		assertEquals(4, neighbors.size());
		assertTrue(neighbors.contains(new Coords2D(coordY - 1, coordX)));
		assertTrue(neighbors.contains(new Coords2D(coordY + 1, coordX)));
		assertTrue(neighbors.contains(new Coords2D(coordY, coordX - 1)));
		assertTrue(neighbors.contains(new Coords2D(coordY, coordX + 1)));
	}

	@Test
	public void testNeighborsForTopRightCornerWithNullStrategy() {
		VonNeumannNeighborhood neighborhood = new VonNeumannNeighborhood(width, height, nullStrategy);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(new Coords2D(0, width - 1));
		assertEquals(2, neighbors.size());
		assertTrue(neighbors.contains(new Coords2D(0, width - 2)));
		assertTrue(neighbors.contains(new Coords2D(1, width - 1)));
	}
	
	@Test
	public void testNeighborsWithRange2Board5x5() {
		int size = 5;
		VonNeumannNeighborhood neighborhood = new VonNeumannNeighborhood(size, size, nullStrategy, 2);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(new Coords2D(2, 2));
		assertEquals(12, neighbors.size());
		for (int i = 1; i <= 1; ++i) {
			assertTrue(neighbors.contains(new Coords2D(1, i)));
			assertTrue(neighbors.contains(new Coords2D(size-2, i)));
		}
		
		for (int i = 0; i <= 1; ++i) {
			assertTrue(neighbors.contains(new Coords2D(2, i)));
			assertTrue(neighbors.contains(new Coords2D(2, size-2+i)));
			assertTrue(neighbors.contains(new Coords2D(i, 2)));
			assertTrue(neighbors.contains(new Coords2D(size-2+i, 2)));
		}
	}
	
	@Test
	public void testNeighborsWithRange3Board5x5() {
		int size = 5;
		VonNeumannNeighborhood neighborhood = new VonNeumannNeighborhood(size, size, nullStrategy, 3);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(new Coords2D(2, 2));
		assertEquals(20, neighbors.size());
		for (int i = 1; i < size-1; ++i) {
			for (int j = 1; j < size-1; ++j) {
				if (i != 2 && j != 2)
					assertTrue(neighbors.contains(new Coords2D(j, i)));
			}
		}
		for (int i = 1; i < size-1; ++i) {
			assertTrue(neighbors.contains(new Coords2D(0, i)));
			assertTrue(neighbors.contains(new Coords2D(size-1, i)));
			assertTrue(neighbors.contains(new Coords2D(i, 0)));
			assertTrue(neighbors.contains(new Coords2D(i, size-1)));
		}
	}

	@Test (expected = InvalidParameterException.class)
	public void checkingLessThanZeroRange() {
		new VonNeumannNeighborhood(1, 1, nullStrategy, -1);
	}
	
}
