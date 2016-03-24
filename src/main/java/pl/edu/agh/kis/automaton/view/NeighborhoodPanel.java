package pl.edu.agh.kis.automaton.view;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pl.edu.agh.kis.automaton.controller.NeighborhoodController;

/**
 * Panel containing neighborhood settings.
 * @author Wojciech Kumoń
 *
 */
public class NeighborhoodPanel extends JPanel {
	private static final long serialVersionUID = 2185162499391013025L;

	private ButtonGroup neighborhoodRadioGroup;
	private JRadioButton vonNeumann;
	private JRadioButton moore;
	
	private ButtonGroup boardTypeRadioGroup;
	private JRadioButton unlimitted;
	private JRadioButton limitted;
	
	private ButtonGroup rangeRadioGroup;
	private JRadioButton range1;
	private JRadioButton range2;
	private JRadioButton range3;
	
	public NeighborhoodPanel() {
		init();
	}
	
	/**
	 * Sets controller for radio buttons.
	 * @param controller neighborhood controller
	 */
	public void setController(NeighborhoodController controller) {
		vonNeumann.addActionListener(controller.getVonNeumannListener());
		moore.addActionListener(controller.getMooreListener());
		
		unlimitted.addActionListener(controller.getUnlimittedBoardListener());
		limitted.addActionListener(controller.getLimittedBoardListener());
		
		range1.addActionListener(controller.getRangeListener(1));
		range2.addActionListener(controller.getRangeListener(2));
		range3.addActionListener(controller.getRangeListener(3));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Background.BACKGROUND, 0, 0, null);
	}

	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		moore = new JRadioButton("Moor");
		vonNeumann = new JRadioButton("von Neumann");
		moore.setSelected(true);

		neighborhoodRadioGroup = new ButtonGroup();
		neighborhoodRadioGroup.add(moore);
		neighborhoodRadioGroup.add(vonNeumann);

		JPanel neighborhoodTypePanel = new JPanel();
		
		neighborhoodTypePanel.setLayout(new BoxLayout(neighborhoodTypePanel, BoxLayout.Y_AXIS));
		neighborhoodTypePanel.add(moore);
		neighborhoodTypePanel.add(Box.createVerticalStrut(5));
		neighborhoodTypePanel.add(vonNeumann);
		neighborhoodTypePanel.setBorder(BorderFactory.createTitledBorder("Typ sąsiedztwa"));
		add(neighborhoodTypePanel);
		
		add(Box.createVerticalStrut(5));
		
		unlimitted = new JRadioButton("zawinięta");
		limitted = new JRadioButton("ograniczona");
		unlimitted.setSelected(true);
		
		boardTypeRadioGroup = new ButtonGroup();
		boardTypeRadioGroup.add(unlimitted);
		boardTypeRadioGroup.add(limitted);
		
		JPanel boardTypePanel = new JPanel();
		boardTypePanel.setLayout(new BoxLayout(boardTypePanel, BoxLayout.Y_AXIS));
		boardTypePanel.add(unlimitted);
		boardTypePanel.add(Box.createVerticalStrut(5));
		boardTypePanel.add(limitted);
		boardTypePanel.setBorder(BorderFactory.createTitledBorder("Typ planszy"));
		add(boardTypePanel);
		
		add(Box.createVerticalStrut(5));
		
		range1 = new JRadioButton("1");
		range2 = new JRadioButton("2");
		range3 = new JRadioButton("3");
		range1.setSelected(true);
		
		rangeRadioGroup = new ButtonGroup();
		rangeRadioGroup.add(range1);
		rangeRadioGroup.add(range2);
		rangeRadioGroup.add(range3);
		
		JPanel rangePanel = new JPanel();
		rangePanel.setLayout(new BoxLayout(rangePanel, BoxLayout.Y_AXIS));
		rangePanel.add(range1);
		rangePanel.add(Box.createVerticalStrut(5));
		rangePanel.add(range2);
		rangePanel.add(Box.createVerticalStrut(5));
		rangePanel.add(range3);
		rangePanel.setBorder(BorderFactory.createTitledBorder("Promień sąsiedztwa"));
		add(rangePanel);	
		
		setOpaque(false);
		vonNeumann.setOpaque(false);
		moore.setOpaque(false);
		unlimitted.setOpaque(false);
		limitted.setOpaque(false);
		range1.setOpaque(false);
		range2.setOpaque(false);
		range3.setOpaque(false);
		neighborhoodTypePanel.setOpaque(false);
		boardTypePanel.setOpaque(false);
		rangePanel.setOpaque(false);
	}
}
