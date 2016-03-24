package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class UnlimittedBoardTest {
	private int width = 10;
	private int height = 10;
	private UnlimittedBoard strategy = new UnlimittedBoard(width, height);

	@Test
	public void testTopLeftCorner() {
		Coords2D newCoords = strategy.getCorrectCoords(new Coords2D(-1,-1));
		assertEquals(new Coords2D(width-1, height-1), newCoords);
	}
	
	@Test
	public void testTopRightCorner() {
		Coords2D newCoords = strategy.getCorrectCoords(new Coords2D(width,-1));
		assertEquals(new Coords2D(0, height-1), newCoords);
	}
	
	@Test
	public void testBottomLeftCorner() {
		Coords2D newCoords = strategy.getCorrectCoords(new Coords2D(-1,height));
		assertEquals(new Coords2D(width-1, 0), newCoords);
	}
	
	@Test
	public void testBottomRightCorner() {
		Coords2D newCoords = strategy.getCorrectCoords(new Coords2D(width,height));
		assertEquals(new Coords2D(0, 0), newCoords);
	}
	
	@Test
	public void testTopSide() {
		Coords2D newCoords = strategy.getCorrectCoords(new Coords2D(0,-1));
		assertEquals(new Coords2D(0, height-1), newCoords);
	}
	
	@Test
	public void testLeftSide() {
		Coords2D newCoords = strategy.getCorrectCoords(new Coords2D(-1,0));
		assertEquals(new Coords2D(width-1, 0), newCoords);
	}
	
	@Test
	public void testRightSide() {
		Coords2D newCoords = strategy.getCorrectCoords(new Coords2D(width,0));
		assertEquals(new Coords2D(0, 0), newCoords);
	}
	
	@Test
	public void testBottomSide() {
		Coords2D newCoords = strategy.getCorrectCoords(new Coords2D(0, height));
		assertEquals(new Coords2D(0, 0), newCoords);
	}
	
	@Test
	public void testWrongCoordinatesFurtherthanBoardSide() {
		assertNull(strategy.getCorrectCoords(new Coords2D(0, 3 * height)));
	}

}
