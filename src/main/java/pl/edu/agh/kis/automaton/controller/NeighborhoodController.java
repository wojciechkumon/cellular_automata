package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.edu.agh.kis.automaton.model.BoardType;
import pl.edu.agh.kis.automaton.model.TwoDimNeighborhoodType;

/**
 * Handle neighoborhood setting and holds informations about current neighborhood.
 * @author Wojciech Kumoń
 */
public class NeighborhoodController {
	private Controller controller;
	
	private TwoDimNeighborhoodType neighborhoodType = TwoDimNeighborhoodType.MOORE;
	private BoardType boardType = BoardType.UNLIMITTED_BOARD;
	private int range = 1;
	
	public NeighborhoodController(Controller controller) {
		this.controller = controller;
	}

	public ActionListener getVonNeumannListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				neighborhoodType = TwoDimNeighborhoodType.VON_NEUMANN;
				controller.changeNeighborhood();
			}
		};
	}

	public ActionListener getMooreListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				neighborhoodType = TwoDimNeighborhoodType.MOORE;
				controller.changeNeighborhood();
			}
		};
	}

	public ActionListener getUnlimittedBoardListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardType = BoardType.UNLIMITTED_BOARD;
				controller.changeNeighborhood();
			}
		};
	}

	public ActionListener getLimittedBoardListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardType = BoardType.LIMITTED_BOARD;
				controller.changeNeighborhood();
			}
		};
	}

	public ActionListener getRangeListener(int r) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				range = r;
				controller.changeNeighborhood();
			}
		};
	}

	public TwoDimNeighborhoodType getNeighborhoodType() {
		return neighborhoodType;
	}

	public BoardType getBoardType() {
		return boardType;
	}

	public int getRange() {
		return range;
	}

}
