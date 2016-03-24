package pl.edu.agh.kis.automaton.controller.handlers;

import pl.edu.agh.kis.automaton.controller.AutomatonType;
import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.CellNeighborhood;
/**
 * Abstact factory creating automaton helpers factories.
 * @author Wojciech Kumoń
 */
public abstract class AutomatonHandlersAbstractFactory {
	
	/**
	 * Creates automaton helpers factory
	 * @param type type of automaton
	 * @return automaton helpers factory connected with automaton type
	 */
	public static AutomatonHandlersAbstractFactory getFactory(AutomatonType type) {
		switch (type) {
		case GAME_OF_LIFE:
			return new GolHandlersFactory();
		case QUAD_LIFE:
			return new QuadLifeHandlersFactory();
		case WIREWORLD:
			return new WireworldHandlersFactory();
		case LANGTON_ANT:
			return new LangtonAntHandlersFactory();
		case ONE_DIM:
		default:
			return new OneDimAutomatonHandlersFactory();
		}
	}
	
	public abstract CellColorSetter getColorSetter(Controller controller);
	public abstract IconToStateConverter getIconToStateConverter();
	public abstract Automaton getAutomaton(CellNeighborhood neighborhood, int width, int height);
	public abstract CellRefresher getRefresher();

}
