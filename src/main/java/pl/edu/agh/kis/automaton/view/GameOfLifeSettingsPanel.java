package pl.edu.agh.kis.automaton.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import pl.edu.agh.kis.automaton.controller.GolRulesController;
import pl.edu.agh.kis.automaton.controller.GolStructureInsertingController;

/**
 * Panel with Game of Life settings and inserting structures.
 * @author Wojciech Kumoń
 */
public class GameOfLifeSettingsPanel extends JPanel {
	private static final long serialVersionUID = -2450742834670047702L;
	
	private JButton stayingAliveRulesBtn;
	private JButton birthRulesBtn;
	private JButton addGlider;
	private JButton addGliderGun;
	private JButton addDakota;
	private JButton addPentadecathlon;

	public GameOfLifeSettingsPanel() {
		init();
	}

	public void setController(GolRulesController rulesController, GolStructureInsertingController insertingController) {
		stayingAliveRulesBtn.addActionListener(rulesController.getStayingAliveRulesListener());
		birthRulesBtn.addActionListener(rulesController.getBirthRulesListener());

		addGlider.addActionListener(insertingController.getGliderListener());
		addGliderGun.addActionListener(insertingController.getGliderGunListener());
		addDakota.addActionListener(insertingController.getDakotaListener());
		addPentadecathlon.addActionListener(insertingController.getPentadecathlonListener());
	}

	private void init() {
		Dimension buttonDim = new Dimension(130, 25);
		
		JPanel rulesPanel = new JPanel();
		Dimension rulesPanelDim = new Dimension(140, 85);
		rulesPanel.setPreferredSize(rulesPanelDim);
		rulesPanel.setSize(rulesPanelDim);
		rulesPanel.setLayout(new FlowLayout());
		rulesPanel.setBorder(BorderFactory.createTitledBorder("Ustaw zasady"));

		stayingAliveRulesBtn = new JButton("Przeżywanie");
		stayingAliveRulesBtn.setPreferredSize(buttonDim);
		stayingAliveRulesBtn.setSize(buttonDim);
		rulesPanel.add(stayingAliveRulesBtn);
		
		birthRulesBtn = new JButton("Rodzenie się");
		birthRulesBtn.setPreferredSize(buttonDim);
		birthRulesBtn.setSize(buttonDim);
		rulesPanel.add(birthRulesBtn);

		Dimension structurePanelDim = new Dimension(140, 145);
		JPanel createStructuresPanel = new JPanel();
		createStructuresPanel.setPreferredSize(structurePanelDim);
		createStructuresPanel.setSize(structurePanelDim);
		createStructuresPanel.setLayout(new FlowLayout());
		createStructuresPanel.setBorder(BorderFactory.createTitledBorder("Wstawianie"));
		
		addGlider = new JButton("Glider");
		addGlider.setPreferredSize(buttonDim);
		addGlider.setSize(buttonDim);
		createStructuresPanel.add(addGlider);
		
		addGliderGun = new JButton("Glider Gun");
		addGliderGun.setPreferredSize(buttonDim);
		addGliderGun.setSize(buttonDim);
		createStructuresPanel.add(addGliderGun);
		
		addDakota = new JButton("Dakota");
		addDakota.setPreferredSize(buttonDim);
		addDakota.setSize(buttonDim);
		createStructuresPanel.add(addDakota);
		
		addPentadecathlon = new JButton("Krokodyl");
		addPentadecathlon.setPreferredSize(buttonDim);
		addPentadecathlon.setSize(buttonDim);
		createStructuresPanel.add(addPentadecathlon);
		
		add(rulesPanel);
		add(createStructuresPanel);
		
		setOpaque(false);
		rulesPanel.setOpaque(false);
		createStructuresPanel.setOpaque(false);
	}

}
