package pl.edu.agh.kis.automaton.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import pl.edu.agh.kis.automaton.controller.AutomatonType;
import pl.edu.agh.kis.automaton.controller.handlers.AutomatonHandlersAbstractFactory;
import pl.edu.agh.kis.automaton.controller.handlers.CellRefresher;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.Cell;
import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.Coords2D;

/**
 * {@link CellPanel} which presents two-dimensional automaton.
 * @author Wojciech Kumoń
 */
public class TwoDimCellPanel extends CellPanel {
	private static final long serialVersionUID = 6030599825143505678L;
	
	private Board board;
	private CellRefresher refresher;
	private boolean clicked = false;
	
	/**
	 * Constructs TwoDimCellPanel
	 * @param horizontalCells number of horizontal cells
	 * @param verticalCells number of vertical cells
	 * @param dim dimension of one cell
	 * @param type Starting automaton type
	 */
	public TwoDimCellPanel(int horizontalCells, int verticalCells, Dimension dim, AutomatonType type) {
		setLayout(null);
		setSize(dim);
		this.refresher = AutomatonHandlersAbstractFactory.getFactory(type).getRefresher();
		init(horizontalCells, verticalCells);
	}

	/**
	 * Set amount of cells
	 * @param width number of horizontal cells
	 * @param height number of vertical cells
	 */
	@Override
	public void setCellAmount(int width, int height) {
		board.forEach(c -> {
			c.setVisible(false);
			remove(c);
		});
		init(width, height);
		board.forEach(c -> c.repaint());
	}
	
	/**
	 * Update cells look to state from automaton
	 * @param automaton automaton witch is used to set grid cells colors
	 */
	@Override
	public void updateCells(Automaton automaton) {
		updateCellColors(automaton);
		setClicked(false);
	}
	
	private void updateCellColors(Automaton automaton) {
		int counter = 0;
		for (Cell cell : automaton) {
			int row = counter / board.getCols();
			int col = counter % board.getCols();
			refreshCell(cell, board.get(row, col));
			++counter;
		}
	}
	
	private void refreshCell(Cell modelCell, GridCell gridCell) {
		if (CellImages.isCellImage(refresher.getIcon(modelCell.state))) {
			gridCell.setIcon(refresher.getIcon(modelCell.state));
		} else {
			gridCell.setResizedIcon(refresher.getIcon(modelCell.state));
		}
	}

	/**
	 * Add {@link ActionListener} for every cell
	 * @param actionListener actionListener to set
	 */
	@Override
	public void addCellsListener(ActionListener actionListener) {
		board.forEach(c -> c.addActionListener(actionListener));
	}
	
	/**
	 * Set {@link CellRefresher}.
	 * @param refresher refresher to set
	 */
	@Override
	public void setRefresher(CellRefresher refresher) {
		this.refresher = refresher;
	}

	/**
	 * Returns {@link Coords2D} mapped to cell icons.
	 * @return {@link Coords2D} mapped to cell icons
	 */
	@Override
	public Map<CellCoordinates, Icon> getIcons() {
		Map<CellCoordinates, Icon> cells = new HashMap<>();
		for (int i = 0; i < board.getCols(); ++i) {
			for (int j = 0; j < board.getRows(); ++j) {
				cells.put(new Coords2D(j, i), board.get(j, i).getIcon());
			}
		}
		return cells;
	}
	
	/**
	 * Set if any cell has been clicked.
	 * @param clicked true if any cell has been clicked, false otherwise
	 */
	@Override
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	/**
	 * Returns true if any cell has been clicked, false otherwise.
	 * @return true if any cell has been clicked, false otherwise
	 */
	@Override
	public boolean isClicked() {
		return clicked;
	}
	
	private void init(int width, int height) {
		setOpaque(false);
		board = new Board(height, width);
		
		Dimension cellSize = getCellSize(width, height);
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				board.set(j, i, new GridCell(cellSize));
				board.get(j, i).setLocation(i * cellSize.width, j * cellSize.height);
				add(board.get(j, i));
			}
		}
	}
	
	private Dimension getCellSize(int x, int y) {
		int longerSide = (x > y) ? x : y;
		int size = getHeight() / longerSide;
		return new Dimension(size, size);
	}
	
}
