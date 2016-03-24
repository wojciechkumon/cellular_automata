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
import pl.edu.agh.kis.automaton.model.Coords1D;

/**
 * {@link CellPanel} which presents one-dimensional automaton.
 * @author Wojciech Kumoń
 */
public class OneDimCellPanel extends CellPanel {
	private static final long serialVersionUID = -7972732241783136762L;
	
	private Board board;
	private CellRefresher refresher;
	private boolean clicked = false;
	
	/**
	 * Constructs OneDimCellPanel
	 * @param cells number of cells
	 * @param dimension dimension of one cell
	 * @param type Starting automaton type
	 */
	public OneDimCellPanel(int cells, Dimension dimension, AutomatonType type) {
		setLayout(null);
		setSize(dimension);
		this.refresher = AutomatonHandlersAbstractFactory.getFactory(type).getRefresher();
		init(cells);
	}
	
	/**
	 * Set amount of cells
	 * @param width amount of cells
	 * @param height skipped
	 */
	@Override
	public void setCellAmount(int width, int height) {
		board.forEach(cell -> {
			cell.setVisible(false);
			remove(cell);
		});
		init(width);
		board.forEach(cell -> cell.repaint());
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
		for (int i = 0; i < board.getCols(); ++i) {
			for (int j = board.getRows() - 1; j > 0 ; --j) {
				board.get(j, i).setIcon(board.get(j - 1, i).getIcon());
			}
		}
		for (Cell cell : automaton) {
			board.get(0, counter).setIcon(refresher.getIcon(cell.state));
			++counter;
		}
	}

	/**
	 * Add {@link ActionListener} for every cell
	 * @param actionListener actionListener to set
	 */
	@Override
	public void addCellsListener(ActionListener actionListener) {
		for (int i = 0; i < board.getCols(); ++i) {
			board.get(0, i).addActionListener(actionListener);
		}
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
	 * Returns {@link Coords1D} mapped to cell icons.
	 * @return {@link Coords1D} mapped to cell icons
	 */
	@Override
	public Map<CellCoordinates, Icon> getIcons() {
		Map<CellCoordinates, Icon> cells = new HashMap<>();
		for (int i = 0; i < board.getCols(); ++i) {
			cells.put(new Coords1D(i), board.get(0, i).getIcon());
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
	
	private void init(int cells) {
		setOpaque(false);
		board = new Board(cells - 1, cells);
		int size = getHeight() / cells;
		Dimension cellSize = new Dimension(size, size);
		initClickableCells(cellSize);
		initNonClickableCells(cellSize);
	}

	private void initClickableCells(Dimension cellSize) {
		for (int i = 0; i < board.getCols(); ++i) {
			board.set(0, i, new GridCell(cellSize));
			board.get(0, i).setLocation(i * cellSize.width, 0);
			add(board.get(0, i));
		}
	}
	
	private void initNonClickableCells(Dimension cellSize) {
		for (int i = 0; i < board.getCols(); ++i) {
			for (int j = 1; j < board.getRows(); ++j) {
				board.set(j, i, new GridCell(cellSize));
				board.get(j, i).setLocation(i * cellSize.width, (j + 1) * cellSize.height);
				add(board.get(j, i));
			}
		}
	}

}
