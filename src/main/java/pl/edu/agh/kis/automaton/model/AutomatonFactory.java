package pl.edu.agh.kis.automaton.model;

import pl.edu.agh.kis.automaton.controller.AutomatonType;

/**
 * Factory which creates automatons.
 * @author Wojciech Kumoń
 */
public class AutomatonFactory {
	
	/**
	 * Method which create automaton
	 * @param type AutomatonType which decides which Automaton subclass should be created
	 * @param factory CellStateFactory which which will allow to initialize Cells.
	 * @param neighborhood CellNeighborhood which will allow to get cell neighbors.
	 * @param width amount of cells in a row.
	 * @param height amount of cells in a row. If type is one-dimensional automaton, it will be skipped.
	 * @return created automaton
	 */
	public static Automaton create(AutomatonType type, CellStateFactory factory, CellNeighborhood neighborhood,
			int width, int height) {
		switch (type) {
		case ONE_DIM:
			return new OneDimAutomaton(factory, new OneDimNeighborhood(width), width);
		case WIREWORLD:
			return new Wireworld(factory, neighborhood, width, height);
		case QUAD_LIFE:
			return new QuadLife(factory, neighborhood, width, height);
		case LANGTON_ANT:
			return new LangtonAnt(factory, neighborhood, width, height);
		case GAME_OF_LIFE:
		default:
			return new GameOfLife(factory, neighborhood, width, height);
		}
	}

}
