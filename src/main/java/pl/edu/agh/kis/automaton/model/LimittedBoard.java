package pl.edu.agh.kis.automaton.model;

/**
 * Represents board limmited on sides.
 * @author Wojciech Kumoń
 */
public class LimittedBoard implements WrongCoordsHandler {
	
	/**
	 * Returns null, because all coords outside board dont't 
	 * have correct replacements.
	 * @param coords2d coords outside board.
	 * @return null
	 */
	@Override
	public Coords2D getCorrectCoords(Coords2D coords2d) {
		return null;
	}
	
}
