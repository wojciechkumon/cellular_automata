package pl.edu.agh.kis.automaton.view;

import java.util.function.Consumer;

/**
 * Handles access to two-dimensional board of cells.
 * @author Wojciech Kumoń
 */
public class Board {
	private GridCell[][] array;
	
	/**
	 * Constructs board of cells 
	 * @param rows amount of rows
	 * @param cols amount of columns
	 */
	public Board(int rows, int cols) {
		array = new GridCell[rows][cols];
	}

	/**
	 * Returns cell on specified position.
	 * @param row index of row
	 * @param col index of column
	 * @return Chosen GridCell
	 */
	public GridCell get(int row, int col) {
		return array[row][col];
	}
	
	/**
	 * Sets cell on specified position
	 * @param row index of row
	 * @param col index of column
	 * @param cell cell to set
	 */
	public void set(int row, int col, GridCell cell) {
		array[row][col] = cell;
	}
	
	public int getRows() {
		return array.length;
	}
	
	public int getCols() {
		return array[0].length;
	}
	
	/**
	 * Applies consumer to all cell on board.
	 * @param consumer operation to execute
	 */
	public void forEach(Consumer<GridCell> consumer) {
		for (int i = 0; i < getRows(); ++i) {
			for (int j = 0; j < getCols(); ++j) {
				consumer.accept(array[i][j]);
			}
		}
	}
	
}
