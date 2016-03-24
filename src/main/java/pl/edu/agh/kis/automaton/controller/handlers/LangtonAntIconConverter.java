package pl.edu.agh.kis.automaton.controller.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import pl.edu.agh.kis.automaton.model.AntState;
import pl.edu.agh.kis.automaton.model.BinaryState;
import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.model.LangtonCell;
import pl.edu.agh.kis.automaton.view.AntColor;
import pl.edu.agh.kis.automaton.view.AntImages;
import pl.edu.agh.kis.automaton.view.CellImages;
import pl.edu.agh.kis.automaton.view.ResizedImageIcon;

/**
 * Converts coordinates mapped to icons into coordinates mapped to cell states.
 * @author Wojciech Kumoń
 */
public class LangtonAntIconConverter implements IconToStateConverter {

	/**
	 * Converts coordinates mapped to icons into coordinates mapped to cell states.
	 * @param structure coords mapped to icons
	 * @return coords mapped to langton cells
	 */
	@Override
	public Map<CellCoordinates, CellState> convert(Map<CellCoordinates, Icon> structure) {
		Map<CellCoordinates, CellState> outputMap = new HashMap<>();
		for (Entry<CellCoordinates, Icon> entry : structure.entrySet()) {
			LangtonCell state = getLangtonCellFromIcon(entry.getValue());
			outputMap.put(entry.getKey(), state);
		}
		return outputMap;
	}

	private LangtonCell getLangtonCellFromIcon(Icon icon) {
		if (icon == CellImages.WHITE)
			return new LangtonCell(BinaryState.DEAD, AntState.NONE, -1);
		if (icon == CellImages.BLACK)
			return new LangtonCell(BinaryState.ALIVE, AntState.NONE, -1);
		
		ResizedImageIcon resizedIcon = (ResizedImageIcon) icon;
		return antCellFromResizedIcon((ImageIcon) resizedIcon.getSource());
	}

	private LangtonCell antCellFromResizedIcon(ImageIcon source) {
		BinaryState binaryState = AntImages.isAntWithBlackBackground(source) ? BinaryState.ALIVE : BinaryState.DEAD;
		AntColor color = AntImages.getAntColorFromIcon(source);
		int antId = getIdFromColor(color);
		AntState antState = AntImages.getAntStateFromIcon(source);

		return new LangtonCell(binaryState, antState, antId);
	}

	private int getIdFromColor(AntColor color) {
		switch (color) {
		case BLUE:
			return 0;
		case GREEN:
			return 1;
		case RED:
			return 2;
		case YELLOW:
		default:
			return 3;
		}
	}

}
