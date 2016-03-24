package pl.edu.agh.kis.automaton.controller.handlers;

import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.CellNeighborhood;
import pl.edu.agh.kis.automaton.model.CellStateFactory;
import pl.edu.agh.kis.automaton.model.QuadLife;
import pl.edu.agh.kis.automaton.model.QuadState;
import pl.edu.agh.kis.automaton.model.UniformStateFactory;

/**
 * Quad Life helpers factory.
 * @author Wojciech Kumoń
 */
public class QuadLifeHandlersFactory extends AutomatonHandlersAbstractFactory {

	@Override
	public CellColorSetter getColorSetter(Controller controller) {
		return new QuadLifeCellColorSetter(controller);
	}

	@Override
	public IconToStateConverter getIconToStateConverter() {
		return new QuadLifeIconConverter();
	}

	@Override
	public Automaton getAutomaton(CellNeighborhood neighborhood, int width, int height) {
		CellStateFactory stateFactory = new UniformStateFactory(QuadState.DEAD);
		return new QuadLife(stateFactory, neighborhood, width, height);
	}

	@Override
	public CellRefresher getRefresher() {
		return new QuadLifeRefresher();
	}

}
