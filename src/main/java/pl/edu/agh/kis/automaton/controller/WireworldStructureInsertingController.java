package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.model.Coords2D;
import pl.edu.agh.kis.automaton.model.WireElectronState;

/**
 * Handles inserting Wireworld structures.
 * @author Wojciech Kumoń
 */
public class WireworldStructureInsertingController {
	private Controller controller;

	public WireworldStructureInsertingController(Controller controller) {
		this.controller = controller;
	}

	public ActionListener getDiodesListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xStart = (controller.getChangeSizeCtrl().getWidth() / 2) - 5;
				int yStart = (controller.getChangeSizeCtrl().getHeight() / 2) - 3;
				controller.getInsertionController().setJustInserted(true);
				controller.putNewStructure(getDiodeStructure(xStart, yStart));
			}

			private Map<CellCoordinates, CellState> getDiodeStructure(int xStart, int yStart) {
				Map<CellCoordinates, CellState> structure = new HashMap<>();
				structure.put(new Coords2D(yStart, xStart), WireElectronState.ELECTRON_TAIL);
				structure.put(new Coords2D(yStart, xStart + 1), WireElectronState.ELECTRON_HEAD);
				for (int i = 2; i <= 3; ++i) {
					structure.put(new Coords2D(yStart, xStart + i), WireElectronState.WIRE);
					structure.put(new Coords2D(yStart + 6, xStart + i), WireElectronState.WIRE);
				}
				structure.put(new Coords2D(yStart - 1, xStart + 4), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart, xStart + 4), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 1, xStart + 4), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart - 1, xStart + 5), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 1, xStart + 5), WireElectronState.WIRE);
				
				for (int i = 6; i <= 9; ++i) {
					structure.put(new Coords2D(yStart, xStart + i), WireElectronState.WIRE);
					structure.put(new Coords2D(yStart + 6, xStart + i), WireElectronState.WIRE);
				}
				structure.put(new Coords2D(yStart + 6, xStart), WireElectronState.ELECTRON_TAIL);
				structure.put(new Coords2D(yStart + 6, xStart + 1), WireElectronState.ELECTRON_HEAD);
				structure.put(new Coords2D(yStart + 5, xStart + 4), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 7, xStart + 4), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 5, xStart + 5), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 6, xStart + 5), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 7, xStart + 5), WireElectronState.WIRE);
				return structure;
			}
		};
	}

	public ActionListener getClocksListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xStart = (controller.getChangeSizeCtrl().getWidth() / 2) - 7;
				int yStart = (controller.getChangeSizeCtrl().getHeight() / 2) - 3;
				controller.getInsertionController().setJustInserted(true);
				controller.putNewStructure(getClocksStructure(xStart, yStart));
			}
			
			private Map<CellCoordinates, CellState> getClocksStructure(int xStart, int yStart) {
				Map<CellCoordinates, CellState> structure = new HashMap<>();
				structure.put(new Coords2D(yStart + 1, xStart), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart, xStart + 1), WireElectronState.ELECTRON_TAIL);
				structure.put(new Coords2D(yStart + 1, xStart + 1), WireElectronState.ELECTRON_HEAD);
				
				structure.put(new Coords2D(yStart + 6, xStart), WireElectronState.ELECTRON_HEAD);
				structure.put(new Coords2D(yStart + 5, xStart + 1), WireElectronState.ELECTRON_TAIL);
				structure.put(new Coords2D(yStart + 7, xStart + 1), WireElectronState.WIRE);
				for (int i = 2; i <= 6; ++i) {
					structure.put(new Coords2D(yStart, xStart + i), WireElectronState.WIRE);
					structure.put(new Coords2D(yStart + 6, xStart + i), WireElectronState.WIRE);
				}
				return structure;
			}
		};
	}

	public ActionListener getXorListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.getChangeSizeCtrl().getWidth() < 22) {
					notifyAboutWrongWidth();
					return;
				}
				int xStart = (controller.getChangeSizeCtrl().getWidth() / 2) - 11;
				int yStart = (controller.getChangeSizeCtrl().getHeight() / 2) - 3;
				controller.getInsertionController().setJustInserted(true);
				controller.putNewStructure(getXorStructure(xStart, yStart));
			}
			
			private void notifyAboutWrongWidth() {
				JOptionPane.showMessageDialog(controller.getView(), "Minimalna szerokość: 22",
						"Zbyt mała szerokośc planszy", JOptionPane.WARNING_MESSAGE);
			}

			private Map<CellCoordinates, CellState> getXorStructure(int xStart, int yStart) {
				Map<CellCoordinates, CellState> structure = new HashMap<>();
				structure.put(new Coords2D(yStart, xStart), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 6, xStart), WireElectronState.WIRE);
				for (int i = 1; i <= 8; ++i) {
					structure.put(new Coords2D(yStart - 1, xStart + i), WireElectronState.WIRE);
					structure.put(new Coords2D(yStart + 1, xStart + i), WireElectronState.WIRE);
					structure.put(new Coords2D(yStart + 5, xStart + i), WireElectronState.WIRE);
					structure.put(new Coords2D(yStart + 7, xStart + i), WireElectronState.WIRE);
				}
				structure.put(new Coords2D(yStart + 1, xStart + 1), WireElectronState.ELECTRON_HEAD);
				structure.put(new Coords2D(yStart + 7, xStart + 1), WireElectronState.ELECTRON_HEAD);
				structure.put(new Coords2D(yStart + 1, xStart + 2), WireElectronState.ELECTRON_TAIL);
				structure.put(new Coords2D(yStart + 7, xStart + 2), WireElectronState.ELECTRON_TAIL);
				structure.put(new Coords2D(yStart - 1, xStart + 4), WireElectronState.ELECTRON_TAIL);
				structure.put(new Coords2D(yStart - 1, xStart + 5), WireElectronState.ELECTRON_HEAD);
				structure.put(new Coords2D(yStart + 7, xStart + 7), WireElectronState.ELECTRON_HEAD);
				structure.put(new Coords2D(yStart + 7, xStart + 8), WireElectronState.ELECTRON_TAIL);
				for (int i = 9; i <= 14; ++i) {
					structure.put(new Coords2D(yStart, xStart + i), WireElectronState.WIRE);
					structure.put(new Coords2D(yStart + 6, xStart + i), WireElectronState.WIRE);
				}
				structure.put(new Coords2D(yStart + 3, xStart + 14), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 1, xStart + 15), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 5, xStart + 15), WireElectronState.WIRE);
				for (int i = 14; i <= 17; ++i) {
					structure.put(new Coords2D(yStart + 2, xStart + i), WireElectronState.WIRE);
					structure.put(new Coords2D(yStart + 4, xStart + i), WireElectronState.WIRE);
				}
				for (int i = 17; i <= 21; ++i) {
					structure.put(new Coords2D(yStart + 3, xStart + i), WireElectronState.WIRE);
				}
				return structure;
			}
		};
	}

	public ActionListener getFrequencyDoublerListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.getChangeSizeCtrl().getWidth() < 15) {
					notifyAboutWrongWidth();
					return;
				}
				int xStart = (controller.getChangeSizeCtrl().getWidth() / 2) - 7;
				int yStart = (controller.getChangeSizeCtrl().getHeight() / 2) - 3;
				controller.getInsertionController().setJustInserted(true);
				controller.putNewStructure(getFrequencyDoubleStructure(xStart, yStart));
			}
			
			private void notifyAboutWrongWidth() {
				JOptionPane.showMessageDialog(controller.getView(), "Minimalna szerokość: 15",
						"Zbyt mała szerokośc planszy", JOptionPane.WARNING_MESSAGE);
			}

			private Map<CellCoordinates, CellState> getFrequencyDoubleStructure(int xStart, int yStart) {
				Map<CellCoordinates, CellState> structure = new HashMap<>();
				structure.put(new Coords2D(yStart , xStart + 1), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart , xStart + 2), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart , xStart + 5), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart , xStart + 6), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart , xStart + 8), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart , xStart + 9), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 1, xStart), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 1, xStart + 3), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 1, xStart + 4), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 2, xStart + 1), WireElectronState.ELECTRON_HEAD);
				structure.put(new Coords2D(yStart + 2, xStart + 2), WireElectronState.ELECTRON_TAIL);
				for (int i = 1; i <= 4; ++i) {
					structure.put(new Coords2D(yStart + i, xStart + 7), WireElectronState.WIRE);
				}
				structure.put(new Coords2D(yStart + 5, xStart + 8), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 4, xStart + 9), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 1, xStart + 10), WireElectronState.WIRE);
				structure.put(new Coords2D(yStart + 3, xStart + 10), WireElectronState.WIRE);
				for (int i = 9; i <= 14; ++i) {
					structure.put(new Coords2D(yStart + 2, xStart + i), WireElectronState.WIRE);
				}
				return structure;
			}
		};
	}

}
