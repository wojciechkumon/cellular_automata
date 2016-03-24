package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;

/**
 * Handles choosing an image e.g. new cell icon.
 * @author Wojciech Kumoń
 */
public class ClickImageController {
	private Controller controller;
	
	public ClickImageController(Controller controller) {
		this.controller = controller;
	}
	
	public ActionListener getClickColorListener(Icon image) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setSelectedColor(image);
			}
		};
	}
}
