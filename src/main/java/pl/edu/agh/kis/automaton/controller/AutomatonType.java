package pl.edu.agh.kis.automaton.controller;

import java.security.InvalidParameterException;

/**
 * All supported automatons.
 * @author Wojciech Kumoń
 */
public enum AutomatonType {
	ONE_DIM("Jednowymiarowy"), WIREWORLD("Wireworld"), GAME_OF_LIFE("Game of Life"),
	QUAD_LIFE("Quad Life"), LANGTON_ANT("Mrówka Langtona");
	
	private String name;
	
	private AutomatonType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Return AutomatonType from String
	 * @param name name of automaton
	 * @return correct AutomatonType
	 */
	public static AutomatonType getFromString(String name) {
		if (name.equals(ONE_DIM.toString())) {
			return ONE_DIM;
		} else if (name.equals(WIREWORLD.toString())) {
			return WIREWORLD;
		} else if (name.equals(GAME_OF_LIFE.toString())) {
			return GAME_OF_LIFE;
		} else if (name.equals(QUAD_LIFE.toString())) {
			return QUAD_LIFE;
		} else if (name.equals(LANGTON_ANT.toString())) {
			return LANGTON_ANT;
		}
		throw new InvalidParameterException("Wrong automaton name");
	}
}
