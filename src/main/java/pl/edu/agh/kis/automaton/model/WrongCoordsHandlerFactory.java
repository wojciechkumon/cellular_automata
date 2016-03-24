package pl.edu.agh.kis.automaton.model;

/**
 * Factory of wrong coords handlers.
 * @author Wojciech Kumoń
 */
public class WrongCoordsHandlerFactory {
	
	/**
	 * Creates wrong coords handler depending on board type.
	 * @param type type o wrong coords handler
	 * @param width board width
	 * @param height board height
	 * @return Subclass of WrongCoordsHandler
	 */
	public static WrongCoordsHandler create(BoardType type, int width, int height) {
		switch (type) {
		case UNLIMITTED_BOARD:
			return new UnlimittedBoard(width, height);
		case LIMITTED_BOARD:
		default:
			return new LimittedBoard();
		}
	}

}
