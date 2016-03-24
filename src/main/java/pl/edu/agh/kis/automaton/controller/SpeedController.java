package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles controlling of simulation speed.
 * @author Wojciech Kumoń
 */
public class SpeedController {
	private Controller controller;
	
	private SimulationType simulationType = SimulationType.REAL_TIME;
	private int simulationsPerSecond = 10;
	
	public SpeedController(Controller controller) {
		this.controller = controller;
	}
	
	public ActionListener getStepSpeedListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (simulationType == SimulationType.REAL_TIME) {
					controller.stop();
				}
				simulationType = SimulationType.STEP;
			}
		};
	}
	
	public ActionListener getRealSpeedListener(int loopsPerSecond) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				simulationType = SimulationType.REAL_TIME;
				simulationsPerSecond = loopsPerSecond;
				if (controller.isRunning()) {
					controller.stop();
					controller.start();
				}
			}
		};
	}

	public SimulationType getSimulationType() {
		return simulationType;
	}

	public int getSimulationsPerSecond() {
		return simulationsPerSecond;
	}
	
}
