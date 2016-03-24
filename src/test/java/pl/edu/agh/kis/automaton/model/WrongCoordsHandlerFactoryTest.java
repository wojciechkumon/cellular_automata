package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class WrongCoordsHandlerFactoryTest {

	@Test
	public void testCreatingLimittedBoard() {
		WrongCoordsHandler handler = WrongCoordsHandlerFactory.create(BoardType.LIMITTED_BOARD, 2, 2);
		assertTrue(handler instanceof LimittedBoard);
	}

	@Test
	public void testCreatingUnlimittedBoard() {
		WrongCoordsHandler handler = WrongCoordsHandlerFactory.create(BoardType.UNLIMITTED_BOARD, 2, 2);
		assertTrue(handler instanceof UnlimittedBoard);
	}
	
}
