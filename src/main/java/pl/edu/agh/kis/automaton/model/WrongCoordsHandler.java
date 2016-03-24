package pl.edu.agh.kis.automaton.model;

/**
 * Interface representing strategy to handle coordinates, which 
 * are outside board.
 * @author Wojciech Kumoń
 */
public interface WrongCoordsHandler {
	
	/**
	 * Returns correct coords or null if there are no correct coords.
	 * @param coords2d coords outside board.
	 * @return correct Coords2D or null if it's impossible.
	 */
	Coords2D getCorrectCoords(Coords2D coords2d);
}
