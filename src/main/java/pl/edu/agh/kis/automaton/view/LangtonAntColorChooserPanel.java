package pl.edu.agh.kis.automaton.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pl.edu.agh.kis.automaton.controller.ClickImageController;

/**
 * Panel with choosing colors in Langton's ant.
 * @author Wojciech Kumoń
 */
public class LangtonAntColorChooserPanel extends JPanel {
	private static final long serialVersionUID = -1030808803561890913L;
	
	private ButtonGroup colorRadioGroup;
	private JRadioButton ant;
	private JRadioButton white;
	private JRadioButton black;
	
	public LangtonAntColorChooserPanel() {
		init();
	}
	
	/**
	 * Sets controller for radio buttons.
	 * @param controller controller for choosing cell type
	 */
	public void setController(ClickImageController controller) {
		ant.addActionListener(controller.getClickColorListener(CellImages.RED));
		white.addActionListener(controller.getClickColorListener(CellImages.WHITE));
		black.addActionListener(controller.getClickColorListener(CellImages.BLACK));
		reset();
	}
	
	/**
	 * Reset panel to default state.
	 */
	public void reset() {
		ant.doClick();
	}

	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
		
		ant = new JRadioButton("mrówka", true);
		white = new JRadioButton("białe");
		black = new JRadioButton("czarne");
		
		colorRadioGroup = new ButtonGroup();
		colorRadioGroup.add(ant);
		colorRadioGroup.add(white);
		colorRadioGroup.add(black);
		
		radioPanel.add(ant);
		radioPanel.add(white);
		radioPanel.add(black);
		
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
		
		Image img = AntImages.ANT_NORTH_RED_WHITE.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JLabel lbl = new JLabel(new ImageIcon(img));
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPanel.add(lbl);
		imgPanel.add(Box.createVerticalStrut(2));
		img = CellImages.WHITE.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		lbl = new JLabel(new ImageIcon(img));
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPanel.add(lbl);
		imgPanel.add(Box.createVerticalStrut(2));
		img = CellImages.BLACK.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		lbl = new JLabel(new ImageIcon(img));
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPanel.add(lbl);
		
		add(radioPanel);
		add(imgPanel);
		setBorder(BorderFactory.createTitledBorder("Wstawiane pole"));
		
		setOpaque(false);
		radioPanel.setOpaque(false);
		imgPanel.setOpaque(false);
		ant.setOpaque(false);
		white.setOpaque(false);
		black.setOpaque(false);
	}

}
