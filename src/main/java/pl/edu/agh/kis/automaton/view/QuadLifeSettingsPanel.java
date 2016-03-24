package pl.edu.agh.kis.automaton.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pl.edu.agh.kis.automaton.controller.ClickImageController;
import pl.edu.agh.kis.automaton.controller.QuadLifeStructureInsertingController;

/**
 * Panel with Quad Life settings and inserting structures.
 * @author Wojciech Kumoń
 */
public class QuadLifeSettingsPanel extends JPanel {
	private static final long serialVersionUID = -426388146457185831L;
	
	private ButtonGroup colorRadioGroup;
	private JRadioButton red;
	private JRadioButton blue;
	private JRadioButton green;
	private JRadioButton yellow;
	
	private JButton addGlider;
	private JButton addGliderGun;
	private JButton addDakota;
	private JButton addPentadecathlon;

	public QuadLifeSettingsPanel() {
		init();
	}

	/**
	 * Sets controller for buttons.
	 * @param imageController controller for colors
	 * @param insertingController controller for structure inserting
	 */
	public void setController(ClickImageController imageController,
			QuadLifeStructureInsertingController insertingController) {
		red.addActionListener(imageController.getClickColorListener(CellImages.RED));
		blue.addActionListener(imageController.getClickColorListener(CellImages.BLUE));
		green.addActionListener(imageController.getClickColorListener(CellImages.GREEN));
		yellow.addActionListener(imageController.getClickColorListener(CellImages.YELLOW));
		
		addGlider.addActionListener(insertingController.getGliderListener());
		addGliderGun.addActionListener(insertingController.getGliderGunListener());
		addDakota.addActionListener(insertingController.getDakotaListener());
		addPentadecathlon.addActionListener(insertingController.getPentadecathlonListener());
	}

	/**
	 * Reset panel to default state.
	 */
	public void reset() {
		red.doClick();
	}

	private void init() {
		JPanel colorPickerPanel = new JPanel();
		Dimension colorPickerPanelDim = new Dimension(140, 120);
		colorPickerPanel.setSize(colorPickerPanelDim);
		colorPickerPanel.setPreferredSize(colorPickerPanelDim);
		colorPickerPanel.setLayout(new BoxLayout(colorPickerPanel, BoxLayout.X_AXIS));
		
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
		
		red = new JRadioButton();
		red.setSelected(true);
		blue = new JRadioButton();
		green = new JRadioButton();
		yellow = new JRadioButton();
		
		colorRadioGroup = new ButtonGroup();
		colorRadioGroup.add(red);
		colorRadioGroup.add(blue);
		colorRadioGroup.add(green);
		colorRadioGroup.add(yellow);
		
		radioPanel.add(red);
		radioPanel.add(blue);
		radioPanel.add(green);
		radioPanel.add(yellow);
		
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
		
		Image img = CellImages.RED.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JLabel lbl = new JLabel(new ImageIcon(img));
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPanel.add(lbl);
		imgPanel.add(Box.createVerticalStrut(2));
		img = CellImages.BLUE.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		lbl = new JLabel(new ImageIcon(img));
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPanel.add(lbl);
		imgPanel.add(Box.createVerticalStrut(2));
		img = CellImages.GREEN.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		lbl = new JLabel(new ImageIcon(img));
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPanel.add(lbl);
		imgPanel.add(Box.createVerticalStrut(2));
		img = CellImages.YELLOW.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		lbl = new JLabel(new ImageIcon(img));
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPanel.add(lbl);
		
		colorPickerPanel.add(radioPanel);
		colorPickerPanel.add(imgPanel);
		colorPickerPanel.setBorder(BorderFactory.createTitledBorder("Wybierz kolor"));
		
		Dimension structurePanelDim = new Dimension(140, 145);
		JPanel createStructuresPanel = new JPanel();
		createStructuresPanel.setPreferredSize(structurePanelDim);
		createStructuresPanel.setSize(structurePanelDim);
		createStructuresPanel.setLayout(new FlowLayout());
		createStructuresPanel.setBorder(BorderFactory.createTitledBorder("Wstawianie"));
		
		Dimension buttonDim = new Dimension(130, 25);
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
		
		add(colorPickerPanel);
		add(createStructuresPanel);
		
		setOpaque(false);
		colorPickerPanel.setOpaque(false);
		createStructuresPanel.setOpaque(false);
		radioPanel.setOpaque(false);
		imgPanel.setOpaque(false);
		red.setOpaque(false);
		blue.setOpaque(false);
		green.setOpaque(false);
		yellow.setOpaque(false);
	}
	
}
