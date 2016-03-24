package pl.edu.agh.kis.automaton.model;

/**
 * Enum representing all possible one-dimensional states of three neighboring binary state cells.
 * @author Wojciech Kumoń
 */
public enum OneDimNeighboring {
	ALIVE_ALIVE_ALIVE,
	ALIVE_ALIVE_DEAD,
	ALIVE_DEAD_ALIVE,
	ALIVE_DEAD_DEAD,
	DEAD_ALIVE_ALIVE,
	DEAD_ALIVE_DEAD,
	DEAD_DEAD_ALIVE,
	DEAD_DEAD_DEAD;
	
	/**
	 * Returns OneDimNeighboring which matches sent booleans.
	 * @param leftAlive ALIVE if true, DEAD otherwise.
	 * @param middleAlive ALIVE if true, DEAD otherwise.
	 * @param rightAlive ALIVE if true, DEAD otherwise.
	 * @return OneDimNeighboring based on boolean convertion.
	 */
	public static OneDimNeighboring getDim1Possibility(boolean leftAlive, boolean middleAlive, boolean rightAlive) {
		if (leftAlive) {
			return getDim1PossibilityForLeftAlive(middleAlive, rightAlive);
		} else {
			return getDim1PossibilityForLeftDead(middleAlive, rightAlive);
		}
	}
	
	private static OneDimNeighboring getDim1PossibilityForLeftAlive(boolean middleAlive, boolean rightAlive) {
		if (middleAlive) {
			if (rightAlive) {
				return ALIVE_ALIVE_ALIVE;
			} else {
				return ALIVE_ALIVE_DEAD;
			}
		} else {
			if (rightAlive) {
				return ALIVE_DEAD_ALIVE;
			} else {
				return ALIVE_DEAD_DEAD;
			}
		}
	}
	
	private static OneDimNeighboring getDim1PossibilityForLeftDead(boolean middleAlive, boolean rightAlive) {
		if (middleAlive) {
			if (rightAlive) {
				return DEAD_ALIVE_ALIVE;
			} else {
				return DEAD_ALIVE_DEAD;
			}
		} else {
			if (rightAlive) {
				return DEAD_DEAD_ALIVE;
			} else {
				return DEAD_DEAD_DEAD;
			}
		}
	}
}
