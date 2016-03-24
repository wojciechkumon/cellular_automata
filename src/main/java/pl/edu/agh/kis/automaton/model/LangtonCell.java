package pl.edu.agh.kis.automaton.model;

import java.util.Objects;

/**
 * Class representing cell state in Langton's ant automaton.
 * @author Wojciech Kumoń
 */
public class LangtonCell implements CellState {
	/**
	 * final field representing binary state of field.
	 */
	public final BinaryState cellState;
	
	/**
	 * final field representing and id.
	 */
	public final int antId;
	
	/**
	 * final field representing ant state.
	 */
	public final AntState antState;

	/**
	 * Constructs Langton cell.
	 * @param cellState cell state to set
	 * @param antState ant state to set
	 * @param antId ant id to set
	 */
	public LangtonCell(BinaryState cellState, AntState antState, int antId) {
		this.cellState = cellState;
		this.antState = antState;
		this.antId = antId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cellState, antId, antState);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !getClass().equals(obj.getClass())) {
			return false;
		}
		LangtonCell cell = (LangtonCell) obj;
		return Objects.equals(cellState, cell.cellState) && Objects.equals(antId, cell.antId)
				&& Objects.equals(antState, cell.antState);
	}
	
	@Override
	public String toString() {
		return cellState+", "+antState+" id="+antId;
	}
	
}
