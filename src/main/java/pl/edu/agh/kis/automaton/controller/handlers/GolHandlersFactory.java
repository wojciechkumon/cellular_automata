package pl.edu.agh.kis.automaton.controller.handlers;

import pl.edu.agh.kis.automaton.controller.AutomatonType;
import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.AutomatonFactory;
import pl.edu.agh.kis.automaton.model.BinaryState;
import pl.edu.agh.kis.automaton.model.CellNeighborhood;
import pl.edu.agh.kis.automaton.model.UniformStateFactory;

/**
 * Game of Life helpers factory.
 * @author Wojciech Kumoń
 */
public class GolHandlersFactory extends AutomatonHandlersAbstractFactory {

	@Override
	public CellColorSetter getColorSetter(Controller controller) {
		return new GolCellColorSetter();
	}

	@Override
	public IconToStateConverter getIconToStateConverter() {
		return new GolIconConverter();
	}

	@Override
	public Automaton getAutomaton(CellNeighborhood neighborhood, int width, int height) {
		return AutomatonFactory.create(AutomatonType.GAME_OF_LIFE, new UniformStateFactory(BinaryState.DEAD),
				neighborhood, width, height);
	}

	@Override
	public CellRefresher getRefresher() {
		return new GolRefresher();
	}

}
