package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;

import pl.edu.agh.kis.automaton.controller.AutomatonType;

public class AutomatonFactoryTest {

	private CellStateFactory deadFactory = c -> BinaryState.DEAD;
	private CellNeighborhood neighborhood = c -> Collections.emptySet();
	
	@Test
	public void testCreatingGameOfLife() {
		Automaton automaton = AutomatonFactory.create(AutomatonType.GAME_OF_LIFE, deadFactory, neighborhood, 2, 2);
		assertTrue(automaton instanceof GameOfLife);
	}

	@Test
	public void testCreatingGameOfQuadLife() {
		Automaton automaton = AutomatonFactory.create(AutomatonType.QUAD_LIFE, deadFactory, neighborhood, 2, 2);
		assertTrue(automaton instanceof QuadLife);
	}
	
	@Test
	public void testCreatingWireworld() {
		Automaton automaton = AutomatonFactory.create(AutomatonType.WIREWORLD, deadFactory, neighborhood, 2, 2);
		assertTrue(automaton instanceof Wireworld);
	}
	
	@Test
	public void testCreatingLangtonAnt() {
		Automaton automaton = AutomatonFactory.create(AutomatonType.LANGTON_ANT, deadFactory, neighborhood, 2, 2);
		assertTrue(automaton instanceof LangtonAnt);
	}
	
	@Test
	public void testCreatingOneDimAutomaton() {
		Automaton automaton = AutomatonFactory.create(AutomatonType.ONE_DIM, deadFactory, neighborhood, 2, 2);
		assertTrue(automaton instanceof OneDimAutomaton);
	}

}
