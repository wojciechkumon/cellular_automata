package pl.edu.agh.kis.automaton.controller.handlers;

import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.model.AntState;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.BinaryState;
import pl.edu.agh.kis.automaton.model.CellNeighborhood;
import pl.edu.agh.kis.automaton.model.CellStateFactory;
import pl.edu.agh.kis.automaton.model.LangtonAnt;
import pl.edu.agh.kis.automaton.model.LangtonCell;
import pl.edu.agh.kis.automaton.model.UniformStateFactory;

/**
 * Langton's ant helpers factory.
 * @author Wojciech Kumoń
 */
public class LangtonAntHandlersFactory extends AutomatonHandlersAbstractFactory {

	@Override
	public CellColorSetter getColorSetter(Controller controller) {
		return new LangtonAntCellColorSetter(controller);
	}

	@Override
	public IconToStateConverter getIconToStateConverter() {
		return new LangtonAntIconConverter();
	}

	@Override
	public Automaton getAutomaton(CellNeighborhood neighborhood, int width, int height) {
		LangtonCell emptyCell = new LangtonCell(BinaryState.DEAD, AntState.NONE, -1);
		CellStateFactory stateFactory = new UniformStateFactory(emptyCell);
		return new LangtonAnt(stateFactory, neighborhood, width, height);
	}

	@Override
	public CellRefresher getRefresher() {
		return new LangtonAntRefresher();
	}

}
