package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UniformStateFactoryTest {

	@Test
	public void stateTest() {
		UniformStateFactory factory = new UniformStateFactory(BinaryState.DEAD);
		assertEquals(BinaryState.DEAD, factory.initialState(new Coords1D(3)));
		assertEquals(BinaryState.DEAD, factory.initialState(new Coords2D(1, 2)));
		
		factory = new UniformStateFactory(WireElectronState.VOID);
		assertEquals(WireElectronState.VOID, factory.initialState(new Coords1D(3)));
		assertEquals(WireElectronState.VOID, factory.initialState(new Coords2D(1, 2)));
	}

}
