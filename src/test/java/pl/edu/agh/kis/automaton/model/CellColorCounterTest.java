package pl.edu.agh.kis.automaton.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CellColorCounterTest {

	@Test
	public void testCounting() {
		Set<Cell> cells = prepareCells(0, 1, 2, 3, 2);
		CellColorCounter counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(6, counter.getAliveCells());
		assertEquals(0, counter.getBlueCells());
		assertEquals(1, counter.getGreenCells());
		assertEquals(2, counter.getRedCells());
		assertEquals(3, counter.getYellowCells());
	}
	
	
	@Test
	public void testMoreReds() {
		Set<Cell> cells = prepareCells(0, 1, 2, 0, 6);
		CellColorCounter counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.RED, counter.getQuadLifeStateForNewCell());
		cells = prepareCells(0, 0, 3, 0, 5);
		counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.RED, counter.getQuadLifeStateForNewCell());
	}
	
	@Test
	public void testMoreYellows() {
		Set<Cell> cells = prepareCells(0, 1, 0, 2, 6);
		CellColorCounter counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.YELLOW, counter.getQuadLifeStateForNewCell());
		cells = prepareCells(0, 0, 0, 3, 5);
		counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.YELLOW, counter.getQuadLifeStateForNewCell());
	}
	
	@Test
	public void testMoreBlues() {
		Set<Cell> cells = prepareCells(2, 1, 0, 0, 6);
		CellColorCounter counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.BLUE, counter.getQuadLifeStateForNewCell());
		cells = prepareCells(3, 0, 0, 0, 5);
		counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.BLUE, counter.getQuadLifeStateForNewCell());
	}
	
	@Test
	public void testMoreGreens() {
		Set<Cell> cells = prepareCells(0, 2, 1, 0, 6);
		CellColorCounter counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.GREEN, counter.getQuadLifeStateForNewCell());
		cells = prepareCells(0, 3, 0, 0, 5);
		counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.GREEN, counter.getQuadLifeStateForNewCell());
	}
	
	@Test
	public void testEqualAmountsOfStates() {
		Set<Cell> cells = prepareCells(1, 1, 1, 0, 6);
		CellColorCounter counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.YELLOW, counter.getQuadLifeStateForNewCell());
		
		cells = prepareCells(0, 1, 1, 1, 6);
		counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.BLUE, counter.getQuadLifeStateForNewCell());
		
		cells = prepareCells(1, 0, 1, 1, 6);
		counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.GREEN, counter.getQuadLifeStateForNewCell());
		
		cells = prepareCells(1, 1, 0, 1, 6);
		counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.RED, counter.getQuadLifeStateForNewCell());
	}
	
	@Test
	public void testGettingStateFromNot3AliveNeighbors() {
		Set<Cell> cells = prepareCells(0, 1, 1, 0, 6);
		CellColorCounter counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.DEAD, counter.getQuadLifeStateForNewCell());
		
		cells = prepareCells(2, 1, 1, 0, 6);
		counter = CellColorCounter.getCellColorCounter(cells);
		assertEquals(QuadState.DEAD, counter.getQuadLifeStateForNewCell());
	}
	
	
	private Set<Cell> prepareCells(int blue, int green, int red, int yellow, int dead) {
		Set<Cell> cells = new HashSet<>();
		for (int i = 0; i < blue; ++i) {
			cells.add(new Cell(new Coords2D(0, i), QuadState.BLUE));
		}
		for (int i = 0; i < green; ++i) {
			cells.add(new Cell(new Coords2D(0, i), QuadState.GREEN));
		}
		for (int i = 0; i < red; ++i) {
			cells.add(new Cell(new Coords2D(0, i), QuadState.RED));
		}
		for (int i = 0; i < yellow; ++i) {
			cells.add(new Cell(new Coords2D(0, i), QuadState.YELLOW));
		}
		for (int i = 0; i < dead; ++i) {
			cells.add(new Cell(new Coords2D(0, i), QuadState.DEAD));
		}
		return cells;
	}

}
