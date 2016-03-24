package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import pl.edu.agh.kis.automaton.model.GameOfLife;

/**
 * Handles setting Game of Life rules.
 * @author Wojciech Kumoń
 */
public class GolRulesController {
	private Controller controller;
	private Set<Integer> stayingAliveRules;
	private Set<Integer> birthRules;

	public GolRulesController(Controller controller) {
		this.controller = controller;
		stayingAliveRules = new HashSet<>();
		stayingAliveRules.add(2);
		stayingAliveRules.add(3);
		birthRules = new HashSet<>();
		birthRules.add(3);
	}
	
	public void resetRules() {
		stayingAliveRules = new HashSet<>();
		stayingAliveRules.add(2);
		stayingAliveRules.add(3);
		birthRules = new HashSet<>();
		birthRules.add(3);
	}

	public ActionListener getStayingAliveRulesListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox[] checkBoxArray = createNumberedCheckBoxesArray(9);
				GameOfLife gameOfLife = (GameOfLife) controller.getModel().getAutomaton();
				setCheckBoxesWithCurrentStayingAliveRules(checkBoxArray, gameOfLife);

				boolean wasRunning = controller.isRunning();
				stopIfNecessary(wasRunning);

				int result = JOptionPane.showConfirmDialog(controller.getView(), checkBoxArray,
						"Komórka pozostaje żywa, gdy żywych sąsiadów jest", JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					stayingAliveRules = getRulesSetFromCheckBoxes(checkBoxArray);
					gameOfLife.setStayingAliveRules(stayingAliveRules);
				}
				startIfNecessary(wasRunning);
			}

			private void setCheckBoxesWithCurrentStayingAliveRules(JCheckBox[] checkBoxArray, GameOfLife gameOfLife) {
				for (int i = 0; i < checkBoxArray.length; ++i) {
					if (gameOfLife.isStayingAliveRuleSet(i)) {
						checkBoxArray[i].setSelected(true);
					}
				}
			}
		};
	}

	public ActionListener getBirthRulesListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox[] checkBoxArray = createNumberedCheckBoxesArray(9);
				GameOfLife gameOfLife = (GameOfLife) controller.getModel().getAutomaton();
				setCheckBoxesWithCurrentBirthRules(checkBoxArray, gameOfLife);

				boolean wasRunning = controller.isRunning();
				stopIfNecessary(wasRunning);

				int result = JOptionPane.showConfirmDialog(controller.getView(), checkBoxArray,
						"Komórka rodzi się, gdy żywych sąsiadów jest", JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					birthRules = getRulesSetFromCheckBoxes(checkBoxArray);
					gameOfLife.setBirthRules(birthRules);
				}
				startIfNecessary(wasRunning);
			}

			private void setCheckBoxesWithCurrentBirthRules(JCheckBox[] checkBoxArray, GameOfLife gameOfLife) {
				for (int i = 0; i < checkBoxArray.length; ++i) {
					if (gameOfLife.isBirthRuleSet(i)) {
						checkBoxArray[i].setSelected(true);
					}
				}
			}
		};
	}
	
	private JCheckBox[] createNumberedCheckBoxesArray(int size) {
		JCheckBox[] checkBoxArray = new JCheckBox[size];
		for (int i = 0; i < checkBoxArray.length; ++i) {
			checkBoxArray[i] = new JCheckBox(Integer.toString(i));
		}
		return checkBoxArray;
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
	
	private Set<Integer> getRulesSetFromCheckBoxes(JCheckBox[] checkBoxArray) {
		Set<Integer> rulesSet = new HashSet<>();
		for (int i = 0; i < checkBoxArray.length; ++i) {
			if (checkBoxArray[i].isSelected()) {
				rulesSet.add(i);
			}
		}
		return rulesSet;
	}

	public Set<Integer> getStayingAliveRules() {
		return stayingAliveRules;
	}

	public Set<Integer> getBirthRules() {
		return birthRules;
	}
	
}
