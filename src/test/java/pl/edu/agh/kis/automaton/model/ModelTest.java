package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ModelTest {
	private final CellStateFactory deadFactory = c -> BinaryState.DEAD;
	
	@Test
	public void testSettersAndGetters() {
		GameOfLife gol = new GameOfLife(deadFactory, null, 5, 5);
		OneDimAutomaton automaton1dim = new OneDimAutomaton(deadFactory, null, 5);
		Model model = new Model(gol);
		assertEquals(gol, model.getAutomaton());
		
		model.setAutomaton(automaton1dim);
		assertEquals(automaton1dim, model.getAutomaton());
	}
	
	@Test
	public void testInsertingStructure() {
		Model model = new Model(new GameOfLife(deadFactory, null, 2, 1));
		
		Map<CellCoordinates, CellState> structure = new HashMap<>();
		structure.put(new Coords2D(0, 0), BinaryState.ALIVE);
		structure.put(new Coords2D(0, 1), BinaryState.ALIVE);
		model.insertStructure(structure);
		
		for (Cell cell : model.getAutomaton()) {
			assertEquals(BinaryState.ALIVE, cell.state);
		}
	}

}
