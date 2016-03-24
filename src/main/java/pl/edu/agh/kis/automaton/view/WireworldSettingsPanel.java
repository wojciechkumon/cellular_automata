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
import pl.edu.agh.kis.automaton.controller.WireworldStructureInsertingController;

/**
 * Panel with Wireworld settings.
 * @author Wojciech Kumoń
 */
public class WireworldSettingsPanel extends JPanel {
	private static final long serialVersionUID = -5330441296661641211L;
	
	private ButtonGroup colorRadioGroup;
	private JRadioButton blue;
	private JRadioButton red;
	private JRadioButton yellow;
	
	private JButton addDiodes;
	private JButton addClocks;
	private JButton addXor;
	private JButton addFrequencyDoubler;
	
	public WireworldSettingsPanel() {
		init();
	}

	/**
	 * Sets controller for buttons.
	 * @param imageController controller for colors
	 * @param insertingController controller for structure inserting
	 */
	public void setController(ClickImageController imageController,
			WireworldStructureInsertingController insertingController) {
		blue.addActionListener(imageController.getClickColorListener(CellImages.BLUE));
		red.addActionListener(imageController.getClickColorListener(CellImages.RED));
		yellow.addActionListener(imageController.getClickColorListener(CellImages.YELLOW));
		
		addDiodes.addActionListener(insertingController.getDiodesListener());
		addClocks.addActionListener(insertingController.getClocksListener());
		addXor.addActionListener(insertingController.getXorListener());
		addFrequencyDoubler.addActionListener(insertingController.getFrequencyDoublerListener());
		reset();
	}
	
	/**
	 * Reset panel to default state.
	 */
	public void reset() {
		blue.doClick();
	}
	
	private void init() {
		JPanel colorPickerPanel = new JPanel();
		Dimension colorPickerPanelDim = new Dimension(175, 100);
		colorPickerPanel.setSize(colorPickerPanelDim);
		colorPickerPanel.setPreferredSize(colorPickerPanelDim);
		colorPickerPanel.setLayout(new BoxLayout(colorPickerPanel, BoxLayout.X_AXIS));
		
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
		
		blue = new JRadioButton("głowa elektronu", true);
		red = new JRadioButton("ogon elektronu");
		yellow = new JRadioButton("przewodnik");
		
		colorRadioGroup = new ButtonGroup();
		colorRadioGroup.add(blue);
		colorRadioGroup.add(red);
		colorRadioGroup.add(yellow);
		
		radioPanel.add(blue);
		radioPanel.add(red);
		radioPanel.add(yellow);
		
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
		
		Image img = CellImages.BLUE.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JLabel lbl = new JLabel(new ImageIcon(img));
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPanel.add(lbl);
		imgPanel.add(Box.createVerticalStrut(2));
		img = CellImages.RED.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
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
		add(colorPickerPanel);
		
		Dimension structurePanelDim = new Dimension(175, 145);
		JPanel createStructuresPanel = new JPanel();
		createStructuresPanel.setPreferredSize(structurePanelDim);
		createStructuresPanel.setSize(structurePanelDim);
		createStructuresPanel.setLayout(new FlowLayout());
		createStructuresPanel.setBorder(BorderFactory.createTitledBorder("Wstawianie"));
		
		Dimension buttonDim = new Dimension(165, 25);
		addDiodes = new JButton("Diody");
		addDiodes.setPreferredSize(buttonDim);
		addDiodes.setSize(buttonDim);
		createStructuresPanel.add(addDiodes);
		
		addClocks = new JButton("Zegary");
		addClocks.setPreferredSize(buttonDim);
		addClocks.setSize(buttonDim);
		createStructuresPanel.add(addClocks);
		
		addXor = new JButton("XOR");
		addXor.setPreferredSize(buttonDim);
		addXor.setSize(buttonDim);
		createStructuresPanel.add(addXor);
		
		addFrequencyDoubler = new JButton("Mnoznik x2");
		addFrequencyDoubler.setPreferredSize(buttonDim);
		addFrequencyDoubler.setSize(buttonDim);
		createStructuresPanel.add(addFrequencyDoubler);
		
		add(createStructuresPanel);
		
		setOpaque(false);
		colorPickerPanel.setOpaque(false);
		createStructuresPanel.setOpaque(false);
		radioPanel.setOpaque(false);
		imgPanel.setOpaque(false);
		blue.setOpaque(false);
		red.setOpaque(false);
		yellow.setOpaque(false);
	}

}
