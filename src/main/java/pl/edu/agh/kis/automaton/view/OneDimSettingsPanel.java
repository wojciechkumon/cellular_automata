package pl.edu.agh.kis.automaton.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import pl.edu.agh.kis.automaton.controller.OneDimRulesController;

/**
 * Panel with change rules button in one-dimensional automaton.
 * @author Wojciech Kumoń
 */
public class OneDimSettingsPanel extends JPanel {
	private static final long serialVersionUID = -4331658073177267686L;

	private JButton oneDimRulesBtn;

	public OneDimSettingsPanel() {
		init();
	}

	/**
	 * Sets controller for button.
	 * @param rulesController controller setting one-dimensional rules
	 */
	public void setController(OneDimRulesController rulesController) {
		oneDimRulesBtn.addActionListener(rulesController.getOneDimRulesListener());
	}

	private void init() {
		Dimension buttonDim = new Dimension(130, 25);

		JPanel rulesPanel = new JPanel();
		Dimension rulesPanelDim = new Dimension(140, 60);
		rulesPanel.setPreferredSize(rulesPanelDim);
		rulesPanel.setSize(rulesPanelDim);
		rulesPanel.setLayout(new FlowLayout());
		rulesPanel.setBorder(BorderFactory.createTitledBorder("Ustaw zasady"));

		oneDimRulesBtn = new JButton("Zasady");
		oneDimRulesBtn.setPreferredSize(buttonDim);
		oneDimRulesBtn.setSize(buttonDim);
		rulesPanel.add(oneDimRulesBtn);

		add(rulesPanel);

		setOpaque(false);
		rulesPanel.setOpaque(false);
	}

}
