package pl.edu.agh.kis.automaton.view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pl.edu.agh.kis.automaton.controller.SpeedController;

/**
 * Panel to controll speed.
 * @author Wojciech Kumoń
 */
public class SpeedPanel extends JPanel {
	private static final long serialVersionUID = 5196261069218362709L;

	private ButtonGroup speedRadioGroup;
	private JRadioButton stepSpeed;
	private JRadioButton speed1;
	private JRadioButton speed5;
	private JRadioButton speed10;
	private JRadioButton speed20;
	private JRadioButton maxSpeed;

	public SpeedPanel() {
		init();
	}

	/**
	 * Sets controller for radio buttons.
	 * @param controller controller for simulation speed
	 */
	public void setController(SpeedController controller) {
		stepSpeed.addActionListener(controller.getStepSpeedListener());
		speed1.addActionListener(controller.getRealSpeedListener(1));
		speed5.addActionListener(controller.getRealSpeedListener(5));
		speed10.addActionListener(controller.getRealSpeedListener(10));
		speed20.addActionListener(controller.getRealSpeedListener(20));
		maxSpeed.addActionListener(controller.getRealSpeedListener(-1));
	}
	
	private void init() {
		stepSpeed = new JRadioButton("na kliknięcie");
		speed1 = new JRadioButton("1/s");
		speed5 = new JRadioButton("5/s");
		speed10 = new JRadioButton("10/s");
		speed20 = new JRadioButton("20/s");
		maxSpeed = new JRadioButton("max");
		speed10.setSelected(true);

		speedRadioGroup = new ButtonGroup();
		speedRadioGroup.add(stepSpeed);
		speedRadioGroup.add(speed1);
		speedRadioGroup.add(speed5);
		speedRadioGroup.add(speed10);
		speedRadioGroup.add(speed20);
		speedRadioGroup.add(maxSpeed);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(stepSpeed);
		add(Box.createVerticalStrut(5));
		add(speed1);
		add(Box.createVerticalStrut(5));
		add(speed5);
		add(Box.createVerticalStrut(5));
		add(speed10);
		add(Box.createVerticalStrut(5));
		add(speed20);
		add(Box.createVerticalStrut(5));
		add(maxSpeed);
		
		setOpaque(false);
		stepSpeed.setOpaque(false);
		speed1.setOpaque(false);
		speed5.setOpaque(false);
		speed10.setOpaque(false);
		speed20.setOpaque(false);
		maxSpeed.setOpaque(false);
		
		setBorder(BorderFactory.createTitledBorder("Prędkość symulacji"));
	}
	
}
