package pl.edu.agh.kis.automaton.controller.handlers;

import pl.edu.agh.kis.automaton.controller.AutomatonType;
import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.AutomatonFactory;
import pl.edu.agh.kis.automaton.model.CellNeighborhood;
import pl.edu.agh.kis.automaton.model.UniformStateFactory;
import pl.edu.agh.kis.automaton.model.WireElectronState;

/**
 * Wireworld helpers factory.
 * @author Wojciech Kumoń
 */
public class WireworldHandlersFactory extends AutomatonHandlersAbstractFactory {

	@Override
	public CellColorSetter getColorSetter(Controller controller) {
		return new WireworldCellColorSetter(controller);
	}

	@Override
	public IconToStateConverter getIconToStateConverter() {
		return new WireworldIconConverter();
	}

	@Override
	public Automaton getAutomaton(CellNeighborhood neighborhood, int width, int height) {
		return AutomatonFactory.create(AutomatonType.WIREWORLD, new UniformStateFactory(WireElectronState.VOID),
				neighborhood, width, height);
	}

	@Override
	public CellRefresher getRefresher() {
		return new WireworldRefresher();
	}

}
