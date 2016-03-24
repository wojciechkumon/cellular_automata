package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class OneDimAutomatonTest {

	private final CellStateFactory deadFactory = c -> BinaryState.DEAD;
	private final CellStateFactory aliveFactory = c -> BinaryState.ALIVE;
	private final CellNeighborhood emptyNeighborhood = c -> Collections.emptySet();
	
	@Test
	public void testGetter() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, 5);
		assertEquals(5, automaton.getSize());
	}

	@Test
	public void testCells() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, 10);
		int counter = 0;
		for (Cell cell : automaton) {
			Coords1D coords = (Coords1D) cell.coords;
			assertEquals(counter, coords.x);
			assertEquals(BinaryState.DEAD, cell.state);
			++counter;
		}
		assertEquals(10, counter);
	}
	
	@Test
	public void testNewInstance() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, 10);
		Automaton newAutomaton = automaton.newInstance(aliveFactory, emptyNeighborhood);
		int counter = 0;
		for (Cell cell : newAutomaton) {
			Coords1D coords = (Coords1D) cell.coords;
			assertEquals(counter, coords.x);
			assertEquals(BinaryState.ALIVE, cell.state);
			++counter;
		}
		assertEquals(10, counter);
	}
	
	@Test
	public void testInitialCoordinates() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, 5);
		assertEquals(new Coords1D(-1), automaton.initialCoordinates());
	}
	
	@Test
	public void testHasNextCoordinates() {
		int size = 5;
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, size);
		for (int i = -1; i < size-1; ++i) {
			assertTrue(automaton.hasNextCoordinates(new Coords1D(i)));
		}
		assertFalse(automaton.hasNextCoordinates(new Coords1D(4)));
		assertFalse(automaton.hasNextCoordinates(new Coords1D(-2)));
	}
	
	@Test
	public void testGettingNextCoordinates() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, 5);
		assertEquals(new Coords1D(0), automaton.nextCoordinates(new Coords1D(-1)));
		assertEquals(new Coords1D(4), automaton.nextCoordinates(new Coords1D(3)));
	}
	
	@Test
	public void testInsertingStructure() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, 5);
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		for (int i = 1; i < automaton.getSize(); ++i)
			structure.put(new Coords1D(i), BinaryState.ALIVE);
		
		automaton.insertStructure(structure);
		Iterator<Cell> it = automaton.iterator();
		assertEquals(BinaryState.DEAD, it.next().state);
		while(it.hasNext()) {
			assertEquals(BinaryState.ALIVE, it.next().state);
		}
	}
	
	@Test
	public void test110Rule() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, 5);
		automaton.setRule(110);
		CellCoordinates leftCoords = new Coords1D(0);
		CellCoordinates rightCoords = new Coords1D(2);
		
		Cell leftCell = new Cell(leftCoords, BinaryState.ALIVE);
		Cell middleCell = new Cell(new Coords1D(1), BinaryState.ALIVE);
		Cell rightCell = new Cell(rightCoords, BinaryState.ALIVE);
		CellState state = automaton.newCellState(middleCell, new HashSet<>(Arrays.asList(leftCell, rightCell)));
		assertEquals(BinaryState.DEAD, state);
		
		rightCell = new Cell(rightCoords, BinaryState.DEAD);
		state = automaton.newCellState(middleCell, new HashSet<>(Arrays.asList(leftCell, rightCell)));
		assertEquals(BinaryState.ALIVE, state);
		
		leftCell = new Cell(leftCoords, BinaryState.DEAD);
		state = automaton.newCellState(middleCell, new HashSet<>(Arrays.asList(leftCell, rightCell)));
		assertEquals(BinaryState.ALIVE, state);
		
		rightCell = new Cell(rightCoords, BinaryState.ALIVE);
		state = automaton.newCellState(middleCell, new HashSet<>(Arrays.asList(leftCell, rightCell)));
		assertEquals(BinaryState.ALIVE, state);
		
		middleCell = new Cell(new Coords1D(1), BinaryState.DEAD);
		state = automaton.newCellState(middleCell, new HashSet<>(Arrays.asList(leftCell, rightCell)));
		assertEquals(BinaryState.ALIVE, state);
		
		leftCell = new Cell(leftCoords, BinaryState.ALIVE);
		state = automaton.newCellState(middleCell, new HashSet<>(Arrays.asList(leftCell, rightCell)));
		assertEquals(BinaryState.ALIVE, state);
		
		rightCell = new Cell(rightCoords, BinaryState.DEAD);
		state = automaton.newCellState(middleCell, new HashSet<>(Arrays.asList(leftCell, rightCell)));
		assertEquals(BinaryState.DEAD, state);
		
		leftCell = new Cell(leftCoords, BinaryState.DEAD);
		state = automaton.newCellState(middleCell, new HashSet<>(Arrays.asList(leftCell, rightCell)));
		assertEquals(BinaryState.DEAD, state);
	}
	
	
	@Test
	public void testGettingNextState() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, new OneDimNeighborhood(5), 5);
		automaton.setRule(30);
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		structure.put(new Coords1D(0), BinaryState.DEAD);
		structure.put(new Coords1D(1), BinaryState.ALIVE);
		structure.put(new Coords1D(2), BinaryState.ALIVE);
		structure.put(new Coords1D(3), BinaryState.DEAD);
		structure.put(new Coords1D(4), BinaryState.ALIVE);
		automaton.insertStructure(structure);
		
		OneDimAutomaton nextWorld = (OneDimAutomaton) automaton.nextState();
		Iterator<Cell> it = nextWorld.iterator();
		assertEquals(BinaryState.ALIVE, it.next().state);
		assertEquals(BinaryState.ALIVE, it.next().state);
		assertEquals(BinaryState.DEAD, it.next().state);
		assertEquals(BinaryState.DEAD, it.next().state);
		assertEquals(BinaryState.ALIVE, it.next().state);
	}
	
	@Test
	public void testHashCode() {
		OneDimAutomaton wireworld = new OneDimAutomaton(deadFactory, emptyNeighborhood, 2);
		assertEquals(wireworld.hashCode(), new OneDimAutomaton(deadFactory, emptyNeighborhood, 2).hashCode());
	}
	
	@Test
	public void testEquals() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, 2);
		assertTrue(automaton.equals(new OneDimAutomaton(deadFactory, emptyNeighborhood, 2)));
		assertFalse(automaton.equals(null));
		assertFalse(automaton.equals(new Object()));
		assertFalse(automaton.equals(new OneDimAutomaton(deadFactory, emptyNeighborhood, 1)));
		assertFalse(automaton.equals(new OneDimAutomaton(aliveFactory, emptyNeighborhood, 2)));
	}
	
	@Test
	public void testGettingRules() {
		OneDimAutomaton automaton = new OneDimAutomaton(deadFactory, emptyNeighborhood, 2);
		int rule = 110;
		automaton.setRule(rule);
		assertEquals(rule, automaton.getRule());
		rule = 90;
		automaton.setRule(rule);
		assertEquals(rule, automaton.getRule());
	}

}
