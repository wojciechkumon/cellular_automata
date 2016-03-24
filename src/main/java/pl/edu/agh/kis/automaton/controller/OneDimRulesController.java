package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.edu.agh.kis.automaton.model.OneDimAutomaton;

/**
 * Handles one-dimensional automaton rule setting.
 * @author Wojciech Kumoń
 */
public class OneDimRulesController {
	private Controller controller;
	private int rule;

	public OneDimRulesController(Controller controller, int rule) {
		this.controller = controller;
		this.rule = rule;
	}
	
	public int getRule() {
		return rule;
	}
	
	public void resetRule() {
		rule = 30;
	}

	public ActionListener getOneDimRulesListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				OneDimAutomaton oneDimAutomaton = (OneDimAutomaton) controller.getModel().getAutomaton();
				JTextField rulesField = new JTextField(Integer.toString(oneDimAutomaton.getRule())) {
					private static final long serialVersionUID = 1L;
					@Override
					public void addNotify() {
						super.addNotify();
						requestFocus();
					}
				};
				boolean wasRunning = controller.isRunning();
				stopIfNecessary(wasRunning);

				JPanel rulesPanel = new JPanel();
				rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
				rulesPanel.add(new JLabel("Wybierz zasady (0-255):"));
				rulesPanel.add(rulesField);
				rulesPanel.add(Box.createVerticalStrut(25));
				int result = JOptionPane.showConfirmDialog(controller.getView(), rulesPanel, "Ustaw wielkość planszy:",
						JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					try {
						int rule = Integer.parseInt(rulesField.getText());
						if (isCorrectRuleInput(rule)) {
							OneDimRulesController.this.rule = rule;
							oneDimAutomaton.setRule(rule);
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(controller.getView(), "Wprowadź liczbę z zakresu 0-255",
								"Błąd wprowadzonych danych", JOptionPane.WARNING_MESSAGE);
					}

				}
				startIfNecessary(wasRunning);
			}

			private boolean isCorrectRuleInput(int rule) throws NumberFormatException {
				if ((rule < 0) || (rule > 255)) {
					throw new NumberFormatException("Wrong rule. It must be (0-255) but was " + rule);
				}
				return true;
			}
		};
	}

	private void startIfNecessary(boolean start) {
		if (start) {
			controller.start();
		}
	}

	private void stopIfNecessary(boolean stop) {
		if (stop) {
			controller.stop();
		}
	}

}
