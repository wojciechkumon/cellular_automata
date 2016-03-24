package pl.edu.agh.kis.automaton.controller.handlers;

import pl.edu.agh.kis.automaton.controller.AutomatonType;
import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.AutomatonFactory;
import pl.edu.agh.kis.automaton.model.BinaryState;
import pl.edu.agh.kis.automaton.model.CellNeighborhood;
import pl.edu.agh.kis.automaton.model.UniformStateFactory;

/**
 * One-dimensional automaton helpers factory.
 * @author Wojciech Kumoń
 */
public class OneDimAutomatonHandlersFactory extends AutomatonHandlersAbstractFactory {

	@Override
	public CellColorSetter getColorSetter(Controller controller) {
		return new OneDimCellColorSetter();
	}

	@Override
	public IconToStateConverter getIconToStateConverter() {
		return new OneDimIconConverter();
	}

	@Override
	public Automaton getAutomaton(CellNeighborhood neighborhood, int width, int height) {
		return AutomatonFactory.create(AutomatonType.ONE_DIM, new UniformStateFactory(BinaryState.DEAD), neighborhood,
				width, height);
	}

	@Override
	public CellRefresher getRefresher() {
		return new OneDimRefresher();
	}

}
