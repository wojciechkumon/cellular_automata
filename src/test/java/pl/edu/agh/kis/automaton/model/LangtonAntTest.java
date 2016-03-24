package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class LangtonAntTest {

	private final CellStateFactory deadWithoutAntFactory = c -> {
		return new LangtonCell(BinaryState.DEAD, AntState.NONE, -1);
	};
	private final CellStateFactory antFacingNorthAliveCellFactory = c -> {
		return new LangtonCell(BinaryState.ALIVE, AntState.NORTH, 0);
	};
	private final CellNeighborhood emptyNeighborhood = c -> Collections.emptySet();
	
	@Test
	public void testGetters() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 5, 3);
		assertEquals(5, langton.getWidth());
		assertEquals(3, langton.getHeight());
	}

	@Test
	public void testCells() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 5, 5);
		LangtonCell expectedCell = new LangtonCell(BinaryState.DEAD, AntState.NONE, -1);
		int counter = 0;
		for (Cell cell : langton) {
			Coords2D coords = (Coords2D) cell.coords;
			int cellNumber = coords.col + coords.row*langton.getWidth();
			assertEquals(counter, cellNumber);
			assertEquals(expectedCell, cell.state);
			++counter;
		}
		assertEquals(25, counter);
	}
	
	@Test
	public void testNewInstance() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 5, 5);
		Automaton newAutomaton = langton.newInstance(antFacingNorthAliveCellFactory, emptyNeighborhood);
		int counter = 0;
		for (Cell cell : newAutomaton) {
			Coords2D coords = (Coords2D) cell.coords;
			int cellNumber = coords.col + coords.row*langton.getWidth();
			assertEquals(counter, cellNumber);
			LangtonCell expectedCell = new LangtonCell(BinaryState.ALIVE, AntState.NORTH, 0);
			assertEquals(expectedCell, cell.state);
			++counter;
		}
		assertEquals(25, counter);
	}
	
	@Test
	public void testInitialCoordinates() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 5, 5);
		assertEquals(new Coords2D(0, -1), langton.initialCoordinates());
	}
	
	@Test
	public void testHasNextCoordinates() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 5, 4);
		assertTrue(langton.hasNextCoordinates(new Coords2D(0, -1)));
		assertTrue(langton.hasNextCoordinates(new Coords2D(0, 3)));
		assertTrue(langton.hasNextCoordinates(new Coords2D(3, 3)));
		assertTrue(langton.hasNextCoordinates(new Coords2D(0, 4)));
		assertFalse(langton.hasNextCoordinates(new Coords2D(3, 4)));
	}
	
	@Test
	public void testGettingNextCoordinates() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 5, 4);
		assertEquals(new Coords2D(0, 0), langton.nextCoordinates(new Coords2D(0, -1)));
		assertEquals(new Coords2D(1, 0), langton.nextCoordinates(new Coords2D(0, 4)));
	}
	
	@Test
	public void testInsertingStructure() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 2, 3);
		int column = 1;
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		for (int i = 0; i < langton.getHeight(); ++i)
			structure.put(new Coords2D(i, column), new LangtonCell(BinaryState.ALIVE, AntState.NONE, -1));
		langton.insertStructure(structure);
		
		LangtonCell expectedCell1 = new LangtonCell(BinaryState.DEAD, AntState.NONE, -1);
		LangtonCell expectedCell2 = new LangtonCell(BinaryState.ALIVE, AntState.NONE, -1);
		for (Cell cell : langton) {
			Coords2D coords = (Coords2D) cell.coords;
			if (coords.col == column) {
				assertEquals(expectedCell2, cell.state);
			} else {
				assertEquals(expectedCell1, cell.state);
			}
		}
	}

	@Test
	public void testGettingNewCellStateWithoutNeighbors() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 2, 3);
		CellState state = langton.newCellState(new Cell(null, new LangtonCell(BinaryState.DEAD, AntState.NONE, -1)),
				Collections.emptySet());
		assertEquals(new LangtonCell(BinaryState.DEAD, AntState.NONE, -1), state);
		state = langton.newCellState(new Cell(null, new LangtonCell(BinaryState.ALIVE, AntState.NONE, -1)),
				Collections.emptySet());
		assertEquals(new LangtonCell(BinaryState.ALIVE, AntState.NONE, -1), state);
	}

	@Test
	public void testAntGoingNorth() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 1, 2);
		Cell currentCell = new Cell(new Coords2D(0, 0), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		CellState expectedState = new LangtonCell(BinaryState.DEAD, AntState.NORTH, 0);
		Set<Cell> neighbors = new HashSet<>();
		neighbors.add(new Cell(new Coords2D(1, 0), new LangtonCell(BinaryState.DEAD, AntState.EAST, 0)));
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
		
		neighbors.clear();
		neighbors.add(new Cell(new Coords2D(1, 0), new LangtonCell(BinaryState.ALIVE, AntState.WEST, 0)));
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
	}
	
	@Test
	public void testAntGoingEast() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 2, 1);
		Cell currentCell = new Cell(new Coords2D(0, 1), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		CellState expectedState = new LangtonCell(BinaryState.DEAD, AntState.EAST, 0);
		Set<Cell> neighbors = new HashSet<>();
		neighbors.add(new Cell(new Coords2D(0, 0), new LangtonCell(BinaryState.DEAD, AntState.SOUTH, 0)));
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
		
		neighbors.clear();
		neighbors.add(new Cell(new Coords2D(0, 0), new LangtonCell(BinaryState.ALIVE, AntState.NORTH, 0)));
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
	}
	
	@Test
	public void testAntGoingSouth() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 1, 2);
		Cell currentCell = new Cell(new Coords2D(1, 0), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		CellState expectedState = new LangtonCell(BinaryState.DEAD, AntState.SOUTH, 0);
		Set<Cell> neighbors = new HashSet<>();
		neighbors.add(new Cell(new Coords2D(0, 0), new LangtonCell(BinaryState.DEAD, AntState.WEST, 0)));
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
		
		neighbors.clear();
		neighbors.add(new Cell(new Coords2D(0, 0), new LangtonCell(BinaryState.ALIVE, AntState.EAST, 0)));
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
	}
	
	@Test
	public void testAntGoingWest() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 1, 2);
		Cell currentCell = new Cell(new Coords2D(0, 0), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		CellState expectedState = new LangtonCell(BinaryState.DEAD, AntState.WEST, 0);
		Set<Cell> neighbors = new HashSet<>();
		neighbors.add(new Cell(new Coords2D(0, 1), new LangtonCell(BinaryState.DEAD, AntState.NORTH, 0)));
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
		
		neighbors.clear();
		neighbors.add(new Cell(new Coords2D(0, 1), new LangtonCell(BinaryState.ALIVE, AntState.SOUTH, 0)));
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
	}
	
	@Test
	public void testAntGoingFromOtherSideOfBoard() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 3, 3);
		Cell currentCell = new Cell(new Coords2D(0, 0), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		CellState expectedState = new LangtonCell(BinaryState.DEAD, AntState.EAST, 0);
		Set<Cell> neighbors = new HashSet<>();
		neighbors.add(new Cell(new Coords2D(0, 2), new LangtonCell(BinaryState.DEAD, AntState.SOUTH, 0)));
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
		
		neighbors.clear();
		neighbors.add(new Cell(new Coords2D(2, 0), new LangtonCell(BinaryState.DEAD, AntState.WEST, 0)));
		expectedState = new LangtonCell(BinaryState.DEAD, AntState.SOUTH, 0);
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
		
		currentCell = new Cell(new Coords2D(2, 2), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		neighbors.clear();
		neighbors.add(new Cell(new Coords2D(2, 0), new LangtonCell(BinaryState.DEAD, AntState.NORTH, 0)));
		expectedState = new LangtonCell(BinaryState.DEAD, AntState.WEST, 0);
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
		
		neighbors.clear();
		neighbors.add(new Cell(new Coords2D(0, 2), new LangtonCell(BinaryState.DEAD, AntState.EAST, 0)));
		expectedState = new LangtonCell(BinaryState.DEAD, AntState.NORTH, 0);
		assertEquals(expectedState, langton.newCellState(currentCell, neighbors));
	}
	
	@Test
	public void testAntGoingFromWrongCoords() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory, emptyNeighborhood, 2, 4);
		Cell currentCell = new Cell(new Coords2D(0, 0), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		Set<Cell> neighbors = new HashSet<>();
		neighbors.add(new Cell(new Coords2D(1, 0), new LangtonCell(BinaryState.DEAD, AntState.NORTH, 0)));
		neighbors.add(new Cell(new Coords2D(0, 1), new LangtonCell(BinaryState.DEAD, AntState.WEST, 0)));
		neighbors.add(new Cell(new Coords2D(1, 0), new LangtonCell(BinaryState.DEAD, AntState.SOUTH, 0)));
		neighbors.add(new Cell(new Coords2D(0, 1), new LangtonCell(BinaryState.DEAD, AntState.EAST, 0)));
		assertEquals(currentCell.state, langton.newCellState(currentCell, neighbors));
		
		currentCell = new Cell(new Coords2D(3, 1), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		neighbors.clear();
		neighbors.add(new Cell(new Coords2D(0, 1), new LangtonCell(BinaryState.DEAD, AntState.NORTH, 0)));
		neighbors.add(new Cell(new Coords2D(3, 0), new LangtonCell(BinaryState.DEAD, AntState.WEST, 0)));
		assertEquals(currentCell.state, langton.newCellState(currentCell, neighbors));
	}
	
	@Test
	public void testGettingNextState() {
		LangtonAnt langton = new LangtonAnt(deadWithoutAntFactory,
				new MooreNeighborhood(2, 3, new LimittedBoard()), 2, 3);
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		structure.put(new Coords2D(0, 0), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		structure.put(new Coords2D(0, 1), new LangtonCell(BinaryState.ALIVE, AntState.NONE, -1));
		structure.put(new Coords2D(1, 0), new LangtonCell(BinaryState.DEAD, AntState.EAST, 0));
		structure.put(new Coords2D(1, 1), new LangtonCell(BinaryState.ALIVE, AntState.WEST, 1));
		structure.put(new Coords2D(2, 0), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		structure.put(new Coords2D(2, 1), new LangtonCell(BinaryState.DEAD, AntState.NONE, -1));
		langton.insertStructure(structure);
		
		LangtonAnt nextWorld = (LangtonAnt) langton.nextState();
		Iterator<Cell> it = nextWorld.iterator();
		assertEquals(new LangtonCell(BinaryState.DEAD, AntState.NORTH, 0), it.next().state);
		assertEquals(new LangtonCell(BinaryState.ALIVE, AntState.NORTH, 1), it.next().state);
		assertEquals(new LangtonCell(BinaryState.ALIVE, AntState.NONE, -1), it.next().state);
		assertEquals(new LangtonCell(BinaryState.DEAD, AntState.NONE, -1), it.next().state);
		assertEquals(new LangtonCell(BinaryState.DEAD, AntState.NONE, -1), it.next().state);
		assertEquals(new LangtonCell(BinaryState.DEAD, AntState.NONE, -1), it.next().state);
	}

}
