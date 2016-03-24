package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.CellState;

/**
 * Handles start button clicking.
 * @author Wojciech Kumoń
 */
public class StartBtnController implements ActionListener {
	private Controller controller;
	
	public StartBtnController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton startButton = (JButton) e.getSource();
		if (startButton.getText().equals("STOP")) {
			controller.stop();
			return;
		}
		
		if (controller.getView().isClicked() || controller.getInsertionController().isJustInserted()) {
			controller.getInsertionController().setJustInserted(false);
			controller.setIteration(0);
			Map<CellCoordinates, CellState> structure = controller.getIconConverter()
					.convert(controller.getView().getIcons());
			controller.getModel().getAutomaton().insertStructure(structure);
		}
		controller.start();
	}
	
}
