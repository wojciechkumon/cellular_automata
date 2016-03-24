package pl.edu.agh.kis.automaton.model;

/**
 * Enum representing binary state of cell. It can be DEAD or ALIVE.
 * @author Wojciech Kumoń
 */
public enum BinaryState implements CellState {
	DEAD, ALIVE;
	
	/**
	 * Method which reverses BinaryState.
	 * @param state base to reverse.
	 * @return ALIVE if state was DEAD, DEAD otherwise.
	 */
	public static BinaryState reverseState(BinaryState state) {
		if (state == DEAD)
			return ALIVE;
		return DEAD;
	}
	
	/**
	 * Method which converts BinaryState to boolean.
	 * @param state base to convert.
	 * @return true if state == ALIVE, false otherwise.
	 */
	public static boolean convertToBoolean(BinaryState state) {
		return state == ALIVE;
	}
	
	/**
	 * Method which converts boolean to BinaryState.
	 * @param isAlive base to convert.
	 * @return ALIVE if isAlive == true, DEAD otherwise.
	 */
	public static BinaryState convertFromBoolean(boolean isAlive) {
		if (isAlive)
			return ALIVE;
		return DEAD;
	}
	
}
