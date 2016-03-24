package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class WireWorldTest {
	private final CellStateFactory voidFactory = c -> WireElectronState.VOID;
	private final CellStateFactory wireFactory = c -> WireElectronState.WIRE;
	private final CellNeighborhood emptyNeighborhood = c -> Collections.emptySet();
	
	@Test
	public void testGetters() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 5, 3);
		assertEquals(5, wireWorld.getWidth());
		assertEquals(3, wireWorld.getHeight());
	}
	
	@Test
	public void testCells() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 5, 5);
		int counter = 0;
		for (Cell cell : wireWorld) {
			Coords2D coords = (Coords2D) cell.coords;
			int cellNumber = coords.col + coords.row*wireWorld.getWidth();
			assertEquals(counter, cellNumber);
			assertEquals(WireElectronState.VOID, cell.state);
			++counter;
		}
		assertEquals(25, counter);
	}
	
	@Test
	public void testNewInstance() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 5, 5);
		Automaton newAutomaton = wireWorld.newInstance(wireFactory, emptyNeighborhood);
		int counter = 0;
		for (Cell cell : newAutomaton) {
			Coords2D coords = (Coords2D) cell.coords;
			int cellNumber = coords.col + coords.row*wireWorld.getWidth();
			assertEquals(counter, cellNumber);
			assertEquals(WireElectronState.WIRE, cell.state);
			++counter;
		}
		assertEquals(25, counter);
	}
	
	@Test
	public void testInitialCoordinates() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 5, 5);
		assertEquals(new Coords2D(0, -1), wireWorld.initialCoordinates());
	}
	
	@Test
	public void testHasNextCoordinates() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 5, 4);
		assertTrue(wireWorld.hasNextCoordinates(new Coords2D(0, -1)));
		assertTrue(wireWorld.hasNextCoordinates(new Coords2D(0, 3)));
		assertTrue(wireWorld.hasNextCoordinates(new Coords2D(3, 3)));
		assertTrue(wireWorld.hasNextCoordinates(new Coords2D(0, 4)));
		assertFalse(wireWorld.hasNextCoordinates(new Coords2D(3, 4)));
	}
	
	@Test
	public void testGettingNextCoordinates() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 5, 4);
		assertEquals(new Coords2D(0, 0), wireWorld.nextCoordinates(new Coords2D(0, -1)));
		assertEquals(new Coords2D(1, 0), wireWorld.nextCoordinates(new Coords2D(0, 4)));
	}
	
	@Test
	public void testInsertingStructure() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 2, 3);
		int wireColumn = 1;
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		for (int i = 0; i < wireWorld.getHeight(); ++i)
			structure.put(new Coords2D(i, wireColumn), WireElectronState.WIRE);
		wireWorld.insertStructure(structure);
		for (Cell cell : wireWorld) {
			Coords2D coords = (Coords2D) cell.coords;
			if (coords.col == wireColumn) {
				assertEquals(WireElectronState.WIRE, cell.state);
			} else {
				assertEquals(WireElectronState.VOID, cell.state);
			}
		}
	}
	
	@Test
	public void testGettingNewCellStateFromVoid() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 2, 3);
		CellState state = wireWorld.newCellState(new Cell(null, WireElectronState.VOID), Collections.emptySet());
		assertEquals(WireElectronState.VOID, state);
	}
	
	@Test
	public void testGettingNewCellStateFromElectronHead() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 2, 3);
		CellState state = wireWorld.newCellState(new Cell(null, WireElectronState.ELECTRON_HEAD), Collections.emptySet());
		assertEquals(WireElectronState.ELECTRON_TAIL, state);
	}
	
	@Test
	public void testGettingNewCellStateFromElectronTail() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 2, 3);
		CellState state = wireWorld.newCellState(new Cell(null, WireElectronState.ELECTRON_TAIL), Collections.emptySet());
		assertEquals(WireElectronState.WIRE, state);
	}
	
	@Test
	public void testGettingNewCellStateFromWireFromElectronHeads() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>(8);
		for (int i = 0; i < 9; ++i) {
			CellState state = wireWorld.newCellState(new Cell(null, WireElectronState.WIRE), neighbors);
			if (i == 1 || i == 2) {
				assertEquals("electron head neighbors = " + i + " state should be an electron head",
						WireElectronState.ELECTRON_HEAD, state);
			} else {
				assertEquals("electron head neighbors = " + i + " state should be a wire", WireElectronState.WIRE,
						state);
			}
			neighbors.add(new Cell(new Coords2D(0, i), WireElectronState.ELECTRON_HEAD));
		}
	}
	
	@Test
	public void testGettingNewCellStateFromWireFromElectronTails() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 2, 3);
		Set<Cell> neighbors = new HashSet<>(8);
		for (int i = 0; i < 9; ++i) {
			CellState state = wireWorld.newCellState(new Cell(null, WireElectronState.WIRE), neighbors);
			assertEquals(WireElectronState.WIRE, state);
			neighbors.add(new Cell(new Coords2D(0, i), WireElectronState.ELECTRON_TAIL));
		}
	}
	
	@Test
	public void testGettingNextState() {
		Wireworld wireWorld = new Wireworld(voidFactory, emptyNeighborhood, 2, 3);
		wireWorld.setNeighborhood(new MooreNeighborhood(2, 3, new LimittedBoard()));
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		structure.put(new Coords2D(0, 0), WireElectronState.ELECTRON_HEAD);
		structure.put(new Coords2D(0, 1), WireElectronState.WIRE);
		structure.put(new Coords2D(1, 0), WireElectronState.VOID);
		structure.put(new Coords2D(1, 1), WireElectronState.ELECTRON_TAIL);
		structure.put(new Coords2D(2, 0), WireElectronState.ELECTRON_HEAD);
		structure.put(new Coords2D(2, 1), WireElectronState.ELECTRON_HEAD);
		wireWorld.insertStructure(structure);
		
		Wireworld nextWorld = (Wireworld) wireWorld.nextState();
		Iterator<Cell> it = nextWorld.iterator();
		assertEquals(WireElectronState.ELECTRON_TAIL, it.next().state);
		assertEquals(WireElectronState.ELECTRON_HEAD, it.next().state);
		assertEquals(WireElectronState.VOID, it.next().state);
		assertEquals(WireElectronState.WIRE, it.next().state);
		assertEquals(WireElectronState.ELECTRON_TAIL, it.next().state);
		assertEquals(WireElectronState.ELECTRON_TAIL, it.next().state);
	}

	@Test
	public void testHashCode() {
		Wireworld wireworld = new Wireworld(voidFactory, emptyNeighborhood, 2, 3);
		assertEquals(wireworld.hashCode(), new Wireworld(voidFactory, emptyNeighborhood, 2, 3).hashCode());
	}
	
	@Test
	public void testEquals() {
		Wireworld wireworld = new Wireworld(voidFactory, emptyNeighborhood, 2, 3);
		assertTrue(wireworld.equals(new Wireworld(voidFactory, emptyNeighborhood, 2, 3)));
		assertFalse(wireworld.equals(null));
		assertFalse(wireworld.equals(new Object()));
		assertFalse(wireworld.equals(new Wireworld(voidFactory, emptyNeighborhood, 1, 3)));
		assertFalse(wireworld.equals(new Wireworld(voidFactory, emptyNeighborhood, 2, 4)));
		assertFalse(wireworld.equals(new Wireworld(wireFactory, emptyNeighborhood, 2, 3)));
	}
	
}
