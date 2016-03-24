package pl.edu.agh.kis.automaton.model;

import java.awt.Dimension;

/**
 * Factory which creates CellNeighborhood
 * @author Wojciech Kumoń
 */
public class TwoDimNeighborhoodFactory {

	/**
	 * Method which creates CellNeighborhood.
	 * @param neighborhoodType type of neighborhood to create
	 * @param boardType board type to create
	 * @param dimension size of board
	 * @param range range to find neighbors
	 * @return CellNeighborhood based on parameters
	 */
	public static CellNeighborhood create(TwoDimNeighborhoodType neighborhoodType, BoardType boardType, Dimension dimension,
			int range) {
		WrongCoordsHandler handler = WrongCoordsHandlerFactory.create(boardType, dimension.width, dimension.height);
		switch (neighborhoodType) {
		case MOORE:
			return new MooreNeighborhood(dimension.width, dimension.height, handler, range);
		case VON_NEUMANN:
		default:
			return new VonNeumannNeighborhood(dimension.width, dimension.height, handler, range);
		}
	}
}
