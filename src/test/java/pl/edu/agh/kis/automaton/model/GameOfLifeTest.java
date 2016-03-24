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

public class GameOfLifeTest {
	private final CellStateFactory deadFactory = c -> BinaryState.DEAD;
	private final CellStateFactory aliveFactory = c -> BinaryState.ALIVE;
	private final CellNeighborhood emptyNeighborhood = c -> Collections.emptySet();
	
	@Test
	public void testGetters() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 5, 3);
		assertEquals(5, gameOfLife.getWidth());
		assertEquals(3, gameOfLife.getHeight());
	}

	@Test
	public void testCells() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 5, 5);
		int counter = 0;
		for (Cell cell : gameOfLife) {
			Coords2D coords = (Coords2D) cell.coords;
			int cellNumber = coords.col + coords.row*gameOfLife.getWidth();
			assertEquals(counter, cellNumber);
			assertEquals(BinaryState.DEAD, cell.state);
			++counter;
		}
		assertEquals(25, counter);
	}
	
	@Test
	public void testNewInstance() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 5, 5);
		Automaton newAutomaton = gameOfLife.newInstance(aliveFactory, emptyNeighborhood);
		int counter = 0;
		for (Cell cell : newAutomaton) {
			Coords2D coords = (Coords2D) cell.coords;
			int cellNumber = coords.col + coords.row*gameOfLife.getWidth();
			assertEquals(counter, cellNumber);
			assertEquals(BinaryState.ALIVE, cell.state);
			++counter;
		}
		assertEquals(25, counter);
	}
	
	@Test
	public void testInitialCoordinates() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 5, 5);
		assertEquals(new Coords2D(0, -1), gameOfLife.initialCoordinates());
	}
	
	@Test
	public void testHasNextCoordinates() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 5, 4);
		assertTrue(gameOfLife.hasNextCoordinates(new Coords2D(0, -1)));
		assertTrue(gameOfLife.hasNextCoordinates(new Coords2D(3, 0)));
		assertTrue(gameOfLife.hasNextCoordinates(new Coords2D(3, 3)));
		assertTrue(gameOfLife.hasNextCoordinates(new Coords2D(4, 0)));
		assertFalse(gameOfLife.hasNextCoordinates(new Coords2D(3, 4)));
	}
	
	@Test
	public void testGettingNextCoordinates() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 5, 4);
		assertEquals(new Coords2D(0, 0), gameOfLife.nextCoordinates(new Coords2D(0, -1)));
		assertEquals(new Coords2D(1, 0), gameOfLife.nextCoordinates(new Coords2D(0, 4)));
	}
	
	@Test
	public void testInsertingStructure() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 2, 3);
		int aliveColumn = 1;
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		for (int i = 0; i < gameOfLife.getHeight(); ++i)
			structure.put(new Coords2D(i, aliveColumn), BinaryState.ALIVE);
		gameOfLife.insertStructure(structure);
		for (Cell cell : gameOfLife) {
			Coords2D coords = (Coords2D) cell.coords;
			if (coords.col == aliveColumn) {
				assertEquals(BinaryState.ALIVE, cell.state);
			} else {
				assertEquals(BinaryState.DEAD, cell.state);
			}
		}
	}

	@Test
	public void testGettingNewCellStateFromDeadWithNot3AliveNeighbors() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>();
		for (int i = 0; i < 9; ++i) {
			if (i != 3) {
				CellState newState = gameOfLife.newCellState(new Cell(null, BinaryState.DEAD), neighbors);
				assertEquals(BinaryState.DEAD, newState);
			}
			neighbors.add(new Cell(new Coords2D(2, 2), BinaryState.ALIVE));
		}

	}

	@Test
	public void testGettingNewCellStateFromDeadWith3AliveNeighbors() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>();
		for (int i = 0; i < 3; ++i) {
			neighbors.add(new Cell(new Coords2D(0, i), BinaryState.ALIVE));
		}
		CellState newState = gameOfLife.newCellState(new Cell(null, BinaryState.DEAD), neighbors);
		assertEquals(BinaryState.ALIVE, newState);
	}
	
	
	@Test
	public void testGettingNewCellStateFromAliveWithOtherThan2And3AliveNeighbors() {
		GameOfLife gameOfLife = new GameOfLife(aliveFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>();
		for (int i = 0; i < 9; ++i) {
			if (i != 2 && i != 3) {
				CellState newState = gameOfLife.newCellState(new Cell(null, BinaryState.ALIVE), neighbors);
				assertEquals(BinaryState.DEAD, newState);
			}
			neighbors.add(new Cell(new Coords2D(2, 2), BinaryState.ALIVE));
		}
	}

	@Test
	public void testGettingNewCellStateFromAliveWith2Or3AliveNeighbors() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>();
		for (int i = 0; i < 2; ++i) {
			neighbors.add(new Cell(new Coords2D(0, i), BinaryState.ALIVE));
		}
		CellState newState = gameOfLife.newCellState(new Cell(null, BinaryState.ALIVE), neighbors);
		assertEquals(BinaryState.ALIVE, newState);
		neighbors.add(new Cell(new Coords2D(2, 2), BinaryState.ALIVE));
		newState = gameOfLife.newCellState(new Cell(null, BinaryState.ALIVE), neighbors);
		assertEquals(BinaryState.ALIVE, newState);
	}

	@Test
	public void testMakingAliveCellFromDeadWithMixedRules() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 3, 3);
		for (int i = 0; i <= 8; ++i) {
			gameOfLife.setBirthRules(getRules(i));
			CellState newCellState = gameOfLife.newCellState(new Cell(null, BinaryState.DEAD),
					getNeighbors(i, BinaryState.ALIVE));
			assertEquals(BinaryState.ALIVE, newCellState);
		}
	}
	
	@Test
	public void testStayingAliveCellWithMixedRules() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 3, 3);
		for (int i = 0; i <= 8; ++i) {
			gameOfLife.setStayingAliveRules(getRules(i));
			CellState newCellState = gameOfLife.newCellState(new Cell(null, BinaryState.ALIVE), getNeighbors(i, BinaryState.ALIVE));
			assertEquals(BinaryState.ALIVE, newCellState);
		}
	}
	
	@Test
	public void testCheckingBirthRules() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 3, 3);
		gameOfLife.setBirthRules(getRules(1, 5, 7));
		for (int i = 0; i < 9; ++i) {
			if (i == 1 || i == 5 || i == 7) {
				assertTrue(gameOfLife.isBirthRuleSet(i));
			} else {
				assertFalse(gameOfLife.isBirthRuleSet(i));
			}
		}
	}
	
	@Test
	public void testCheckingStayingAliveRules() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory, emptyNeighborhood, 3, 3);
		gameOfLife.setStayingAliveRules(getRules(1, 5, 7));
		for (int i = 0; i < 9; ++i) {
			if (i == 1 || i == 5 || i == 7) {
				assertTrue(gameOfLife.isStayingAliveRuleSet(i));
			} else {
				assertFalse(gameOfLife.isStayingAliveRuleSet(i));
			}
		}
	}
	
	private Set<Integer> getRules(int... rules) {
		Set<Integer> rulesSet = new HashSet<>();
		for (int rule : rules) {
			rulesSet.add(rule);
		}
		return rulesSet;
	}
	
	private Set<Cell> getNeighbors(int neighbors, CellState state) {
		Set<Cell> neighborsSet = new HashSet<>();
		for (int i = 0; i < neighbors; ++i) {
			neighborsSet.add(new Cell(new Coords2D(0, i), state));
		}
		return neighborsSet;
	}

	@Test
	public void testGettingNextState() {
		GameOfLife gameOfLife = new GameOfLife(deadFactory,
				new MooreNeighborhood(2, 3, new LimittedBoard()), 2, 3);
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		structure.put(new Coords2D(0, 0), BinaryState.ALIVE);
		structure.put(new Coords2D(1, 0), BinaryState.DEAD);
		structure.put(new Coords2D(0, 1), BinaryState.ALIVE);
		structure.put(new Coords2D(1, 1), BinaryState.ALIVE);
		structure.put(new Coords2D(0, 2), BinaryState.DEAD);
		structure.put(new Coords2D(1, 2), BinaryState.DEAD);
		gameOfLife.insertStructure(structure);
		
		GameOfLife nextWorld = (GameOfLife) gameOfLife.nextState();
		Iterator<Cell> it = nextWorld.iterator();
		assertEquals(BinaryState.ALIVE, it.next().state);
		assertEquals(BinaryState.ALIVE, it.next().state);
		assertEquals(BinaryState.ALIVE, it.next().state);
		assertEquals(BinaryState.ALIVE, it.next().state);
		assertEquals(BinaryState.DEAD, it.next().state);
		assertEquals(BinaryState.DEAD, it.next().state);
	}
	
}
