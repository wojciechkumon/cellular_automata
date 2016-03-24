package pl.edu.agh.kis.automaton.model;

/**
 * Enum representing ant direction. It may be north, east, south, north or none.
 * @author Wojciech Kumoń
 */
public enum AntState {
	NONE, NORTH, SOUTH, EAST, WEST;

	/**
	 * Returns AntState rotated counter-clockwise or NONE if NONE is a
	 * parameter.
	 * @param state AntState to rotate counter-clockwise.
	 * @return rotated left AntState or NONE if NONE was parameter.
	 */
	public static AntState rotateLeft(AntState state) {
		switch (state) {
		case NORTH:
			return WEST;
		case WEST:
			return SOUTH;
		case SOUTH:
			return EAST;
		case EAST:
			return NORTH;
		default:
			return NONE;
		}
	}

	/**
	 * Returns AntState rotated clockwise or NONE if NONE is a parameter.
	 * @param state AntState to rotate clockwise.
	 * @return rotated right AntState or NONE if NONE was a parameter.
	 */
	public static AntState rotateRight(AntState state) {
		switch (state) {
		case NORTH:
			return EAST;
		case EAST:
			return SOUTH;
		case SOUTH:
			return WEST;
		case WEST:
			return NORTH;
		default:
			return NONE;
		}
	}

	/**
	 * Rotates LangtonCell AntState depending on field. If field is dead,
	 * AntState is rotated left, right otherwise.
	 * @param cell AntState to rotate clockwise.
	 * @return AntState rotated left or right or NONE if LangtonCell AntState was NONE.
	 */
	public static AntState getRotatedState(LangtonCell cell) {
		if (cell.cellState == BinaryState.DEAD) {
			return rotateLeft(cell.antState);
		}
		return rotateRight(cell.antState);
	}
}
