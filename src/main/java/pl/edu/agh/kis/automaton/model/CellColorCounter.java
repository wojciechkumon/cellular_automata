package pl.edu.agh.kis.automaton.model;

import java.util.Set;

/**
 * Class which helps with counting each color in Quad Life.
 * @author Wojciech Kumoń
 */
public class CellColorCounter {
	private int redCells;
	private int greenCells;
	private int blueCells;
	private int yellowCells;
	
	private CellColorCounter() {}
	
	/**
	 * Creates CellColorCounter object with counted cells with each Quad Life color.
	 * @param cells cells which will be checked to get their color
	 * @return CellColorCounter object holding each color amount
	 */
	public static CellColorCounter getCellColorCounter(Set<Cell> cells) {
		CellColorCounter counter = new CellColorCounter();
		for (Cell cell : cells) {
			QuadState state = (QuadState) cell.state;

			switch (state) {
			case BLUE:
				counter.blueCells++;
				break;
			case GREEN:
				counter.greenCells++;
				break;
			case YELLOW:
				counter.yellowCells++;
				break;
			case RED:
				counter.redCells++;
				break;
			default:
			}
		}
		return counter;
	}

	/**
	 * Return amount of red cells.
	 * @return amount of red cells
	 */
	public int getRedCells() {
		return redCells;
	}

	/**
	 * Return amount of green cells.
	 * @return amount of green cells
	 */
	public int getGreenCells() {
		return greenCells;
	}

	/**
	 * Return amount of blue cells.
	 * @return amount of blue cells
	 */
	public int getBlueCells() {
		return blueCells;
	}

	/**
	 * Return amount of yellow cells.
	 * @return amount of yellow cells
	 */
	public int getYellowCells() {
		return yellowCells;
	}

	/**
	 * Return amount of red cells.
	 * @return amount of red cells
	 */
	public int getAliveCells() {
		return redCells + blueCells + yellowCells + greenCells;
	}
	
	/**
	 * Calculates new QuadState for cell based on amount of each color.
	 * @return QuadState.DEAD if alives != 3, other QuadState based on Quad Life
	 *         rules.
	 */
	public QuadState getQuadLifeStateForNewCell() {
		if (getAliveCells() != 3) {
			return QuadState.DEAD;
		}
		
		if (redCells >= 2) {
			return QuadState.RED;
		} else if (blueCells >= 2) {
			return QuadState.BLUE;
		} else if (greenCells >= 2) {
			return QuadState.GREEN;
		} else if (yellowCells >= 2) {
			return QuadState.YELLOW;
		}
		
		if (redCells == 0) {
			return QuadState.RED;
		} else if (blueCells == 0) {
			return QuadState.BLUE;
		} else if (greenCells == 0) {
			return QuadState.GREEN;
		} else {
			return QuadState.YELLOW;
		}
	}

}