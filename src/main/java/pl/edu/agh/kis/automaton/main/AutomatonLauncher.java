package pl.edu.agh.kis.automaton.main;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

import pl.edu.agh.kis.automaton.controller.AutomatonType;
import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.controller.handlers.AutomatonHandlersAbstractFactory;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.BoardType;
import pl.edu.agh.kis.automaton.model.CellNeighborhood;
import pl.edu.agh.kis.automaton.model.Model;
import pl.edu.agh.kis.automaton.model.TwoDimNeighborhoodFactory;
import pl.edu.agh.kis.automaton.model.TwoDimNeighborhoodType;
import pl.edu.agh.kis.automaton.view.View;

/**
 * Application launcher.
 * @author Wojciech Kumoń
 */
public class AutomatonLauncher {
	private static int INITIAL_WIDTH = 25;
	private static int INITIAL_HEIGHT = 25;

	public static void main(String[] args) {
		AutomatonType startingType = AutomatonType.GAME_OF_LIFE;
		CellNeighborhood neighborhood = TwoDimNeighborhoodFactory.create(TwoDimNeighborhoodType.MOORE,
				BoardType.UNLIMITTED_BOARD, new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT), 1);
		
		AutomatonHandlersAbstractFactory factory = AutomatonHandlersAbstractFactory.getFactory(startingType);
		Automaton startingAutomaton = factory.getAutomaton(neighborhood, INITIAL_WIDTH, INITIAL_HEIGHT);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Model model = new Model(startingAutomaton);
				View view = new View(startingType);
				new Controller(model, view, startingType);
			}
		});
	}
}
