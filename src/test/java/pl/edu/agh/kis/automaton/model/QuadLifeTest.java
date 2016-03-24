package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class QuadLifeTest {

	private final CellStateFactory deadFactory = c -> QuadState.DEAD;
	private final CellStateFactory blueFactory = c -> QuadState.BLUE;
	private final CellNeighborhood emptyNeighborhood = c -> Collections.emptySet();
	
	@Test
	public void testGetters() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 5, 3);
		assertEquals(5, quadLife.getWidth());
		assertEquals(3, quadLife.getHeight());
	}

	@Test
	public void testCells() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 5, 5);
		int counter = 0;
		for (Cell cell : quadLife) {
			Coords2D coords = (Coords2D) cell.coords;
			int cellNumber = coords.col + coords.row*quadLife.getWidth();
			assertEquals(counter, cellNumber);
			assertEquals(QuadState.DEAD, cell.state);
			++counter;
		}
		assertEquals(25, counter);
	}
	
	@Test
	public void testNewInstance() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 5, 5);
		Automaton newAutomaton = quadLife.newInstance(blueFactory, emptyNeighborhood);
		int counter = 0;
		for (Cell cell : newAutomaton) {
			Coords2D coords = (Coords2D) cell.coords;
			int cellNumber = coords.col + coords.row*quadLife.getWidth();
			assertEquals(counter, cellNumber);
			assertEquals(QuadState.BLUE, cell.state);
			++counter;
		}
		assertEquals(25, counter);
	}
	
	@Test
	public void testInitialCoordinates() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 5, 5);
		assertEquals(new Coords2D(0, -1), quadLife.initialCoordinates());
	}
	
	@Test
	public void testHasNextCoordinates() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 5, 4);
		assertTrue(quadLife.hasNextCoordinates(new Coords2D(0, -1)));
		assertTrue(quadLife.hasNextCoordinates(new Coords2D(0, 3)));
		assertTrue(quadLife.hasNextCoordinates(new Coords2D(3, 3)));
		assertTrue(quadLife.hasNextCoordinates(new Coords2D(0, 4)));
		assertFalse(quadLife.hasNextCoordinates(new Coords2D(3, 4)));
	}
	
	@Test
	public void testGettingNextCoordinates() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 5, 4);
		assertEquals(new Coords2D(0, 0), quadLife.nextCoordinates(new Coords2D(0, -1)));
		assertEquals(new Coords2D(1, 0), quadLife.nextCoordinates(new Coords2D(0, 4)));
	}
	
	@Test
	public void testInsertingStructure() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 2, 3);
		int aliveColumn = 1;
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		for (int i = 0; i < quadLife.getHeight(); ++i)
			structure.put(new Coords2D(i, aliveColumn), QuadState.RED);
		quadLife.insertStructure(structure);
		for (Cell cell : quadLife) {
			Coords2D coords = (Coords2D) cell.coords;
			if (coords.col == aliveColumn) {
				assertEquals(QuadState.RED, cell.state);
			} else {
				assertEquals(QuadState.DEAD, cell.state);
			}
		}
	}

	@Test
	public void testGettingNewCellStateFromDeadWithNot3AliveNeighbors() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>();
		for (int i = 0; i < 9; ++i) {
			if (i != 3) {
				CellState newState = quadLife.newCellState(new Cell(null, QuadState.DEAD), neighbors);
				assertEquals(QuadState.DEAD, newState);
			}
			neighbors.add(new Cell(new Coords2D(2, i), QuadState.RED));
		}
	}

	@Test
	public void testGettingNewCellStateFromDeadWith3AliveNeighbors() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>();
		for (int i = 0; i < 3; ++i) {
			neighbors.add(new Cell(new Coords2D(2, i), QuadState.RED));
		}
		CellState newState = quadLife.newCellState(new Cell(null, QuadState.DEAD), neighbors);
		assertEquals(QuadState.RED, newState);
	}
	
	
	@Test
	public void testGettingNewCellStateFromAliveWithOtherThan2And3AliveNeighbors() {
		QuadLife quadLife = new QuadLife(blueFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>();
		for (int i = 0; i < 9; ++i) {
			if (i != 2 && i != 3) {
				CellState newState = quadLife.newCellState(new Cell(null, QuadState.RED), neighbors);
				assertEquals(QuadState.DEAD, newState);
			}
			neighbors.add(new Cell(new Coords2D(2, i), QuadState.RED));
		}

	}

	@Test
	public void testGettingNewCellStateFromAliveWith2Or3AliveNeighbors() {
		QuadLife quadLife = new QuadLife(deadFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>();
		for (int i = 0; i < 2; ++i) {
			neighbors.add(new Cell(new Coords2D(2, i), QuadState.RED));
		}
		CellState newState = quadLife.newCellState(new Cell(null, QuadState.BLUE), neighbors);
		assertEquals(QuadState.BLUE, newState);
		neighbors.add(new Cell(new Coords2D(1, 2), QuadState.RED));
		newState = quadLife.newCellState(new Cell(null, QuadState.BLUE), neighbors);
		assertEquals(QuadState.BLUE, newState);
	}

	@Test
	public void testGettingNextState() {
		QuadLife quadLife = new QuadLife(deadFactory,
				new MooreNeighborhood(2, 3, new LimittedBoard()), 2, 3);
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		structure.put(new Coords2D(0, 0), QuadState.RED);
		structure.put(new Coords2D(0, 1), QuadState.DEAD);
		structure.put(new Coords2D(1, 0), QuadState.BLUE);
		structure.put(new Coords2D(1, 1), QuadState.GREEN);
		structure.put(new Coords2D(2, 0), QuadState.DEAD);
		structure.put(new Coords2D(2, 1), QuadState.DEAD);
		quadLife.insertStructure(structure);
		
		QuadLife nextWorld = (QuadLife) quadLife.nextState();
		Iterator<Cell> it = nextWorld.iterator();
		assertEquals(QuadState.RED, it.next().state);
		assertEquals(QuadState.YELLOW, it.next().state);
		assertEquals(QuadState.BLUE, it.next().state);
		assertEquals(QuadState.GREEN, it.next().state);
		assertEquals(QuadState.DEAD, it.next().state);
		assertEquals(QuadState.DEAD, it.next().state);
	}

}
