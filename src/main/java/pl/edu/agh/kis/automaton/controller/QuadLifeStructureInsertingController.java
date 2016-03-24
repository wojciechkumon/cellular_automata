package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.model.Coords2D;
import pl.edu.agh.kis.automaton.model.QuadState;

/**
 * Handles inserting Quad Life structures.
 * @author Wojciech Kumoń
 */
public class QuadLifeStructureInsertingController {
	private Controller controller;

	public QuadLifeStructureInsertingController(Controller controller) {
		this.controller = controller;
	}

	public ActionListener getGliderListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xStart = (controller.getChangeSizeCtrl().getWidth() / 2) - 1;
				int yStart = (controller.getChangeSizeCtrl().getHeight() / 2) + 1;
				controller.getInsertionController().setJustInserted(true);
				controller.putNewStructure(getGliderStructure(xStart, yStart));
			}

			private Map<CellCoordinates, CellState> getGliderStructure(int xStart, int yStart) {
				Map<CellCoordinates, CellState> structure = new HashMap<>();
				structure.put(new Coords2D(yStart, xStart), QuadState.BLUE);
				structure.put(new Coords2D(yStart, xStart + 1), QuadState.GREEN);
				structure.put(new Coords2D(yStart, xStart + 2), QuadState.RED);
				structure.put(new Coords2D(yStart - 1, xStart + 2), QuadState.YELLOW);
				structure.put(new Coords2D(yStart - 2, xStart + 1), QuadState.BLUE);
				return structure;
			}
		};
	}

	public ActionListener getDakotaListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xStart = (controller.getChangeSizeCtrl().getWidth() / 2) - 1;
				int yStart = (controller.getChangeSizeCtrl().getHeight() / 2) - 1;
				controller.getInsertionController().setJustInserted(true);
				controller.putNewStructure(getDakotaStructure(xStart, yStart));
			}

			private Map<CellCoordinates, CellState> getDakotaStructure(int xStart, int yStart) {
				Map<CellCoordinates, CellState> structure = new HashMap<>();
				structure.put(new Coords2D(yStart + 1, xStart), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 2, xStart), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 3, xStart), QuadState.RED);
				structure.put(new Coords2D(yStart, xStart + 1), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 3, xStart + 1), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 3, xStart + 2), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 3, xStart + 3), QuadState.RED);
				structure.put(new Coords2D(yStart, xStart + 4), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 2, xStart + 4), QuadState.BLUE);
				return structure;
			}
		};
	}

	public ActionListener getPentadecathlonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.getChangeSizeCtrl().getWidth() < 12) {
					notifyAboutWrongWidth();
					return;
				}
				int xStart = (controller.getChangeSizeCtrl().getWidth() / 2) - 5;
				int yStart = (controller.getChangeSizeCtrl().getHeight() / 2) - 1;
				controller.getInsertionController().setJustInserted(true);
				controller.putNewStructure(getPentadecathlonStructure(xStart, yStart));
			}

			private void notifyAboutWrongWidth() {
				JOptionPane.showMessageDialog(controller.getView(), "Minimalna szerokość: 12",
						"Zbyt mała szerokośc planszy", JOptionPane.WARNING_MESSAGE);
			}

			private Map<CellCoordinates, CellState> getPentadecathlonStructure(int xStart, int yStart) {
				Map<CellCoordinates, CellState> structure = new HashMap<>();
				structure.put(new Coords2D(yStart + 1, xStart), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 1, xStart + 1), QuadState.GREEN);
				structure.put(new Coords2D(yStart, xStart + 2), QuadState.RED);
				structure.put(new Coords2D(yStart + 2, xStart + 2), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 1, xStart + 3), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 1, xStart + 4), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 1, xStart + 5), QuadState.RED);
				structure.put(new Coords2D(yStart + 1, xStart + 6), QuadState.YELLOW);
				structure.put(new Coords2D(yStart, xStart + 7), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 2, xStart + 7), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 1, xStart + 8), QuadState.RED);
				structure.put(new Coords2D(yStart + 1, xStart + 9), QuadState.YELLOW);
				return structure;
			}
		};
	}
	
	public ActionListener getGliderGunListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.getChangeSizeCtrl().getWidth() < 38) {
					notifyAboutWrongWidth();
					return;
				}
				int xStart = (controller.getChangeSizeCtrl().getWidth() / 2) - 18;
				int yStart = (controller.getChangeSizeCtrl().getHeight() / 2) - 5;
				controller.getInsertionController().setJustInserted(true);
				controller.putNewStructure(getGliderGunStructure(xStart, yStart));
			}

			private void notifyAboutWrongWidth() {
				JOptionPane.showMessageDialog(controller.getView(), "Minimalna szerokość: 38",
						"Zbyt mała szerokośc planszy", JOptionPane.WARNING_MESSAGE);
			}

			private Map<CellCoordinates, CellState> getGliderGunStructure(int xStart, int yStart) {
				Map<CellCoordinates, CellState> structure = new HashMap<>();
				structure.put(new Coords2D(yStart + 4, xStart), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 5, xStart), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 4, xStart + 1), QuadState.RED);
				structure.put(new Coords2D(yStart + 5, xStart + 1), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 4, xStart + 10), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 5, xStart + 10), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 6, xStart + 10), QuadState.RED);
				structure.put(new Coords2D(yStart + 3, xStart + 11), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 7, xStart + 11), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 2, xStart + 12), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 8, xStart + 12), QuadState.RED);
				structure.put(new Coords2D(yStart + 2, xStart + 13), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 8, xStart + 13), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 5, xStart + 14), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 3, xStart + 15), QuadState.RED);
				structure.put(new Coords2D(yStart + 7, xStart + 15), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 4, xStart + 16), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 5, xStart + 16), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 6, xStart + 16), QuadState.RED);
				structure.put(new Coords2D(yStart + 5, xStart + 17), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 2, xStart + 20), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 3, xStart + 20), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 4, xStart + 20), QuadState.RED);
				structure.put(new Coords2D(yStart + 2, xStart + 21), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 3, xStart + 21), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 4, xStart + 21), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 1, xStart + 22), QuadState.RED);
				structure.put(new Coords2D(yStart + 5, xStart + 22), QuadState.YELLOW);
				structure.put(new Coords2D(yStart, xStart + 24), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 1, xStart + 24), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 5, xStart + 24), QuadState.RED);
				structure.put(new Coords2D(yStart + 6, xStart + 24), QuadState.YELLOW);
				structure.put(new Coords2D(yStart + 2, xStart + 34), QuadState.BLUE);
				structure.put(new Coords2D(yStart + 3, xStart + 34), QuadState.GREEN);
				structure.put(new Coords2D(yStart + 2, xStart + 35), QuadState.RED);
				structure.put(new Coords2D(yStart + 3, xStart + 35), QuadState.YELLOW);
				return structure;
			}
		};
	}

}
