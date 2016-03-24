package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;
import java.util.Set;

import org.junit.Test;

public class MooreNeighborhoodTest {
	private int width = 3;
	private int height = 4;
	private WrongCoordsHandler notChangingStrategy = c -> c;
	private WrongCoordsHandler nullStrategy = c -> null;

	@Test
	public void testNeighborsForTopLeftCorner() {
		MooreNeighborhood neighborhood = new MooreNeighborhood(width, height, notChangingStrategy);
		Coords2D coords = new Coords2D(0, 0);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(coords);
		check8Neighbors(neighbors, coords.col, coords.row);
	}

	@Test
	public void testNeighborsForBottomRightCorner() {
		MooreNeighborhood neighborhood = new MooreNeighborhood(width, height, notChangingStrategy);
		Coords2D coords = new Coords2D(height - 1, width - 1);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(coords);
		check8Neighbors(neighbors, coords.col, coords.row);
	}

	private void check8Neighbors(Set<CellCoordinates> neighbors, int coordX, int coordY) {
		assertEquals(8, neighbors.size());
		for (int i = -1; i <= 1; ++i) {
			assertTrue(neighbors.contains(new Coords2D(coordY-1, coordX+i)));
			assertTrue(neighbors.contains(new Coords2D(coordY+1, coordX+i)));
		}
		assertTrue(neighbors.contains(new Coords2D(coordY+1, coordX)));
		assertTrue(neighbors.contains(new Coords2D(coordY-1, coordX)));
	}

	@Test
	public void testNeighborsForTopRightCornerWithNullStrategy() {
		MooreNeighborhood neighborhood = new MooreNeighborhood(width, height, nullStrategy);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(new Coords2D(0, width - 1));
		assertEquals(3, neighbors.size());
		assertTrue(neighbors.contains(new Coords2D(0, width - 2)));
		assertTrue(neighbors.contains(new Coords2D(1, width - 1)));
		assertTrue(neighbors.contains(new Coords2D(1, width - 2)));
	}
	
	@Test
	public void testNeighborhoodWithRange2AndBoard7x7() {
		int size = 7;
		MooreNeighborhood neighborhood = new MooreNeighborhood(size, size, nullStrategy, 2);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(new Coords2D(3, 3));
		assertEquals(24, neighbors.size());
		for (int i = 1; i < size-1; ++i) {
			for (int j = 1; j < size-1; ++j) {
				if (i != 3 && j != 3)
					assertTrue(neighbors.contains(new Coords2D(i, j)));
			}
		}
	}
	
	@Test
	public void testNeighborhoodWithRange3AndBoard7x7() {
		int size = 7;
		MooreNeighborhood neighborhood = new MooreNeighborhood(size, size, nullStrategy, 3);
		Set<CellCoordinates> neighbors = neighborhood.cellNeighbors(new Coords2D(3, 3));
		assertEquals(48, neighbors.size());
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (i != 3 && j != 3)
					assertTrue(neighbors.contains(new Coords2D(i, j)));
			}
		}
	}
	
	@Test (expected = InvalidParameterException.class)
	public void checkingLessThanZeroRange() {
		new MooreNeighborhood(1, 1, nullStrategy, -1);
	}

}
