package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class GeneralStateFactoryTest {

	@Test
	public void test() {
		Map<CellCoordinates, CellState> map = new HashMap<>();
		Coords1D coords1d = new Coords1D(77);
		Coords2D coords2d = new Coords2D(17, 89);
		CellStateFactory factory = new GeneralStateFactory(map);
		map.put(coords1d, BinaryState.ALIVE);
		map.put(coords2d, WireElectronState.ELECTRON_HEAD);
		assertEquals(BinaryState.ALIVE, factory.initialState(coords1d));
		assertEquals(WireElectronState.ELECTRON_HEAD, factory.initialState(coords2d));
	}

}
