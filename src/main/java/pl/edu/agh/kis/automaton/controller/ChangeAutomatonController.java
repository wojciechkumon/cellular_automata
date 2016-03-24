package pl.edu.agh.kis.automaton.controller;

import static pl.edu.agh.kis.automaton.controller.AutomatonType.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Handles change automaton event.
 * @author Wojciech Kumoń
 */
public class ChangeAutomatonController implements ActionListener {
	private Controller controller;
	
	public ChangeAutomatonController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.stop();
		String[] automatons = { ONE_DIM.toString(), GAME_OF_LIFE.toString(), QUAD_LIFE.toString(),
				WIREWORLD.toString(), LANGTON_ANT.toString() };
		String chosenAutomaton = (String) JOptionPane.showInputDialog(controller.getView(), "Wybierz automat:",
				"Wybór automatu", JOptionPane.QUESTION_MESSAGE, null, automatons,
				controller.getAutomatonType().toString());
		if (chosenAutomaton != null) {
			AutomatonType automatonType = AutomatonType.getFromString(chosenAutomaton);
			controller.changeAutomaton(automatonType);
		}
	}

}
