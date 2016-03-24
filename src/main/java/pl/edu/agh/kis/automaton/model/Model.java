package pl.edu.agh.kis.automaton.model;

import java.util.Map;
import java.util.Observable;

/**
 * Class representing observable automaton model.
 * @author Wojciech Kumoń
 */
public class Model extends Observable {
	private Automaton automaton;
	
	/**
	 * Constructs model with automaton.
	 * @param automaton initialization automaton
	 */
	public Model(Automaton automaton) {
		this.automaton = automaton;
	}

	/**
	 * Return current automaton.
	 * @return current automaton
	 */
	public Automaton getAutomaton() {
		return automaton;
	}

	/**
	 * Replaces automaton and notifies observers.
	 * @param automaton automaton to set
	 */
	public void setAutomaton(Automaton automaton) {
		this.automaton = automaton;
		setChanged();
		notifyObservers();
	}

	/**
	 * Insert structure into automaton and notifies observers.
	 * @param structure cell structure to insert
	 */
	public void insertStructure(Map<CellCoordinates, CellState> structure) {
		automaton.insertStructure(structure);
		setChanged();
		notifyObservers();
	}
}
