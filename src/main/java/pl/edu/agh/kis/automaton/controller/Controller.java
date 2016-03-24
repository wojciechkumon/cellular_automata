package pl.edu.agh.kis.automaton.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.Timer;

import pl.edu.agh.kis.automaton.controller.handlers.AutomatonHandlersAbstractFactory;
import pl.edu.agh.kis.automaton.controller.handlers.IconToStateConverter;
import pl.edu.agh.kis.automaton.model.Automaton;
import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.CellNeighborhood;
import pl.edu.agh.kis.automaton.model.CellState;
import pl.edu.agh.kis.automaton.model.GameOfLife;
import pl.edu.agh.kis.automaton.model.Model;
import pl.edu.agh.kis.automaton.model.OneDimAutomaton;
import pl.edu.agh.kis.automaton.model.TwoDimNeighborhoodFactory;
import pl.edu.agh.kis.automaton.view.StartButtonLabel;
import pl.edu.agh.kis.automaton.view.View;

/**
 * Controller class, controls the entire application.
 * @author Wojciech Kumoń
 */
public class Controller {
	private Model model;
	private View view;
	private AutomatonType type;
	
	private StartBtnController startBtnCtrl = new StartBtnController(this);
	private GridCellController cellCtrl;
	private ChangeAutomatonController changeAutomatonCtrl = new ChangeAutomatonController(this);
	private SizeChangingController changeSizeCtrl = new SizeChangingController(this, 25, 25);
	private SpeedController speedCtrl = new SpeedController(this);
	private NeighborhoodController neighborhoodCtrl = new NeighborhoodController(this);
	private ClickImageController clickColorCtrl = new ClickImageController(this);
	private IconToStateConverter converter;
	
	private StructureInsertingController insertionController = new StructureInsertingController(this);
	private GolRulesController goLRulesController = new GolRulesController(this);
	private OneDimRulesController oneDimRulesController = new OneDimRulesController(this, 30);
	private Icon selectedColor;

	private int iteration = 0;
	private Timer timer = new Timer(0, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setNextState();
		}
	});
	
	/**
	 * Constructs controller.
	 * @param model model to set
	 * @param view view to set
	 * @param type Starting type of automaton.
	 */
	public Controller(Model model, View view, AutomatonType type) {
		this.model = model;
		this.view = view;
		this.type = type;
		AutomatonHandlersAbstractFactory factory = AutomatonHandlersAbstractFactory.getFactory(type);
		converter = factory.getIconToStateConverter();
		cellCtrl = new GridCellController(this, factory.getColorSetter(this));
		view.setRefresher(factory.getRefresher());
		model.addObserver(view);
		view.setController(this);
		view.setView(type);
	}
	
	/**
	 * Checks if simulation is running.1
	 * @return true if simulation is running, false otherwise.
	 */
	public boolean isRunning() {
		return timer.isRunning();
	}
	
	/**
	 * Starts simulation.
	 */
	public void start() {
		if (speedCtrl.getSimulationType() == SimulationType.STEP) {
			setNextState();
		} else {
			setTimerDelay();
			timer.start();
			view.setStartButtonLabel(StartButtonLabel.STOP);
		}
	}
	
	private void setTimerDelay() {
		if (speedCtrl.getSimulationsPerSecond() <= 0) {
			timer.setDelay(0);
		} else {
			int delayMillis = 1000 / speedCtrl.getSimulationsPerSecond();
			timer.setDelay(delayMillis);
		}
	}

	/**
	 * Stops simulation.
	 */
	public void stop() {
		timer.stop();
		view.setStartButtonLabel(StartButtonLabel.START);
	}
	
	private void clearBoard() {
		stop();
		setCurrentAutomaton();
	}
	
	public void putNewStructure(Map<CellCoordinates, CellState> structure) {
		Map<CellCoordinates, CellState> onBoardStructure = converter.convert(view.getIcons());
		onBoardStructure.putAll(structure);
		model.insertStructure(onBoardStructure);
	}
	
	private void setNextState() {
		Automaton oldAutomaton = model.getAutomaton();
		Automaton newAutomaton = oldAutomaton.nextState();
		if (oldAutomaton.equals(newAutomaton)) {
			stop();
		} else {
			model.setAutomaton(newAutomaton);
			view.setIterationsCount(++iteration);
		}
	}
	
	/**
	 * Changes automaton.
	 * @param automatonType automaton type of automaton to set
	 */
	public void changeAutomaton(AutomatonType automatonType) {
		type = automatonType;
		resetRules();
		AutomatonHandlersAbstractFactory factory = AutomatonHandlersAbstractFactory.getFactory(automatonType);
		converter = factory.getIconToStateConverter();
		cellCtrl.setCellColorSetter(factory.getColorSetter(this));
		view.setView(automatonType);
		view.setRefresher(factory.getRefresher());
		view.setBoardSize(changeSizeCtrl.getWidth(), changeSizeCtrl.getHeight());
		setCurrentAutomaton();
		view.addCellsListener(cellCtrl);
	}
	
	private void resetRules() {
		goLRulesController.resetRules();
		oneDimRulesController.resetRule();;
	}

	/**
	 * Changes board size to current settings.
	 */
	public void changeBoardSize() {
		Map<CellCoordinates, CellState> structure = converter.convert(view.getIcons());
		view.setBoardSize(changeSizeCtrl.getWidth(), changeSizeCtrl.getHeight());
		setCurrentAutomaton();
		model.insertStructure(structure);
		view.addCellsListener(cellCtrl);
	}
	
	private void setCurrentAutomaton() {
		AutomatonHandlersAbstractFactory factory = AutomatonHandlersAbstractFactory.getFactory(type);
		int width = changeSizeCtrl.getWidth();
		int height = changeSizeCtrl.getHeight();
		CellNeighborhood neighborhood = getNeighborhood();
		model.setAutomaton(factory.getAutomaton(neighborhood, width, height));
		setRules();
	}
	
	private void setRules() {
		if (type == AutomatonType.ONE_DIM) {
			((OneDimAutomaton) model.getAutomaton()).setRule(oneDimRulesController.getRule());
		} else if (type == AutomatonType.GAME_OF_LIFE) {
			((GameOfLife) model.getAutomaton()).setBirthRules(goLRulesController.getBirthRules());
			((GameOfLife) model.getAutomaton()).setStayingAliveRules(goLRulesController.getStayingAliveRules());
		}
	}

	/**
	 * Changes neighborhood to current settings.
	 */
	public void changeNeighborhood() {
		model.getAutomaton().setNeighborhood(getNeighborhood());
	}
	
	private CellNeighborhood getNeighborhood() {
		int width = changeSizeCtrl.getWidth();
		int height = changeSizeCtrl.getHeight();
		return TwoDimNeighborhoodFactory.create(neighborhoodCtrl.getNeighborhoodType(), neighborhoodCtrl.getBoardType(),
				new Dimension(width, height), neighborhoodCtrl.getRange());
	}

	public ActionListener getStartBtnCtrl() {
		return startBtnCtrl;
	}
	
	public ActionListener getCellCtrl() {
		return cellCtrl;
	}
	
	public ActionListener getChangeAutomatonCtrl() {
		return changeAutomatonCtrl;
	}
	
	public SizeChangingController getChangeSizeCtrl() {
		return changeSizeCtrl;
	}
	
	public SpeedController getSpeedController() {
		return speedCtrl;
	}
	
	public NeighborhoodController getNeighborhoodCtrl() {
		return neighborhoodCtrl;
	}
	
	public ClickImageController getClickColorCtrl() {
		return clickColorCtrl;
	}
	
	public GolRulesController getGoLRulesController() {
		return goLRulesController;
	}
	
	public StructureInsertingController getInsertionController() {
		return insertionController;
	}
	
	public OneDimRulesController getOneDimRulesController() {
		return oneDimRulesController;
	}
	
	public Model getModel() {
		return model;
	}
	
	public View getView() {
		return view;
	}
	
	public Icon getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(Icon colorSelected) {
		this.selectedColor = colorSelected;
	}

	public ActionListener getClearBtnCtrl() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearBoard();
			}
		};
	}
	
	public AutomatonType getAutomatonType() {
		return type;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public IconToStateConverter getIconConverter() {
		return converter;
	}

}
