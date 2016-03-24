package pl.edu.agh.kis.automaton.model;

/**
 * Represents unlimmited board, allows passing through walls.
 * @author Wojciech Kumoń
 */
public class UnlimittedBoard implements WrongCoordsHandler {
	private int width;
	private int height;

	/**
	 * Construct UnlimittedBoard
	 * @param width board width
	 * @param height board height
	 */
	public UnlimittedBoard(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * If coordinates are outside board, returns replacement on the opposite side of board in correct range.
	 * @param coords2d coords outside board.
	 * @return correct Coords2D or null if it's impossible.
	 */
	@Override
	public Coords2D getCorrectCoords(Coords2D coords2d) {
		try {
			int newX = getNewCoord(coords2d.col, width - 1);
			int newY = getNewCoord(coords2d.row, height - 1);
			return new Coords2D(newY, newX);
		} catch (WrongCoordinatesException e) {
			return null;
		}
	}

	private int getNewCoord(int currentCoord, int maxCoord) throws WrongCoordinatesException {
		int newCoord = currentCoord;
		if (currentCoord < 0) {
			newCoord = maxCoord + currentCoord + 1;
		} else if (currentCoord > maxCoord) {
			newCoord = currentCoord - maxCoord - 1;
		}
		if (newCoord < 0 || newCoord > maxCoord) {
			throw new WrongCoordinatesException();
		}
		return newCoord;
	}

}
