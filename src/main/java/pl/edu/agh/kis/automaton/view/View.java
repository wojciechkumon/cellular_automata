package pl.edu.agh.kis.automaton.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import pl.edu.agh.kis.automaton.controller.AutomatonType;
import pl.edu.agh.kis.automaton.controller.Controller;
import pl.edu.agh.kis.automaton.controller.handlers.CellRefresher;
import pl.edu.agh.kis.automaton.model.CellCoordinates;
import pl.edu.agh.kis.automaton.model.Model;

/**
 * Application main window.
 * @author Wojciech Kumoń
 */
public class View extends JFrame implements Observer {
	private static final long serialVersionUID = -4548314053722795925L;
	
	private TwoDimCellPanel gridTwoDim;
	private OneDimCellPanel gridOnedim;
	private CellPanel currentGrid;
	private JButton startBtn;
	private JButton clearBtn;
	private JButton changeAutomatonBtn;
	private JButton changeBoardSizeBtn;
	private SpeedPanel speedPanel;
	private NeighborhoodPanel neighborhoodPanel;
	private JLabel automatonName;
	private JLabel iterationsCountLbl;
	
	private SettingsPanel settingsPanel;

	/**
	 * Constructs application window.
	 * @param type Starting automaton type
	 */
	public View(AutomatonType type) {
		init(type);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			Model model = (Model) o;
			currentGrid.updateCells(model.getAutomaton());
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set controller for all buttons and cells.
	 * @param controller application controller
	 */
	public void setController(Controller controller) {
		addCellsListener(controller.getCellCtrl());
		startBtn.addActionListener(controller.getStartBtnCtrl());
		clearBtn.addActionListener(controller.getClearBtnCtrl());
		changeAutomatonBtn.addActionListener(controller.getChangeAutomatonCtrl());
		changeBoardSizeBtn.addActionListener(controller.getChangeSizeCtrl());
		speedPanel.setController(controller.getSpeedController());
		neighborhoodPanel.setController(controller.getNeighborhoodCtrl());
		
		settingsPanel.setController(controller);
		controller.getModel().setAutomaton(controller.getModel().getAutomaton());
	}
	
	/**
	 * Add {@link ActionListener} to every cell.
	 * @param actionListener actionListener to add
	 */
	public void addCellsListener(ActionListener actionListener) {
		gridOnedim.addCellsListener(actionListener);
		gridTwoDim.addCellsListener(actionListener);
	}
	
	/**
	 * Set grid {@link CellRefresher}.
	 * @param refresher refresher to set
	 */
	public void setRefresher(CellRefresher refresher) {
		currentGrid.setRefresher(refresher);
	}
	
	
	/**
	 * Returns true if any cell has been clicked, false otherwise.
	 * @return true if any cell has been clicked, false otherwise
	 */
	public boolean isClicked() {
		return currentGrid.isClicked();
	}
	
	/**
	 * Set if any cell has been clicked.
	 * @param clicked true if any cell has been clicked, false otherwise
	 */
	public void setClicked(boolean clicked) {
		currentGrid.setClicked(clicked);
	}
	
	/**
	 * Returns coordinates mapped to cell icons.
	 * @return coordinates mapped to cell icons
	 */
	public Map<CellCoordinates, Icon> getIcons() {
		return currentGrid.getIcons();
	}
	
	/**
	 * Set grid size
	 * @param width amount of horizontal cells
	 * @param height grid amount of vertical cells
	 */
	public void setBoardSize(int width, int height) {
		currentGrid.setCellAmount(width, height);
	}
	
	/**
	 * Set start button text.
	 * @param label text to set
	 */
	public void setStartButtonLabel(StartButtonLabel label) {
		startBtn.setText(label.name());
	}
	
	/**
	 * Set number of iterations.
	 * @param iterationsCount number of iterations
	 */
	public void setIterationsCount(int iterationsCount) {
		iterationsCountLbl.setText("Iteracja: " + iterationsCount);
	}
	
	/**
	 * Set view for chosen {@link AutomatonType}.
	 * @param type type of automaton to set
	 */
	public void setView(AutomatonType type) {
		switch (type) {
		case GAME_OF_LIFE:
			setGameOfLife();
			break;
		case QUAD_LIFE:
			setQuadLife();
			break;
		case WIREWORLD:
			setWireWorld();
			break;
		case LANGTON_ANT:
			setLangtonAnt();
			break;
		case ONE_DIM:
		default:
			setOneDimensional();
		}
	}
	
	private void setGameOfLife() {
		setTwoDimensional();
		settingsPanel.setGameOfLife();
		automatonName.setText(AutomatonType.GAME_OF_LIFE.toString());
	}

	private void setQuadLife() {
		setTwoDimensional();
		settingsPanel.setQuadLife();
		automatonName.setText(AutomatonType.QUAD_LIFE.toString());
	}

	private void setWireWorld() {
		setTwoDimensional();
		settingsPanel.setWireWorld();
		automatonName.setText(AutomatonType.WIREWORLD.toString());
	}

	private void setLangtonAnt() {
		setTwoDimensional();
		settingsPanel.setLangtonAnt();
		automatonName.setText(AutomatonType.LANGTON_ANT.toString());
	}
	
	private void setTwoDimensional() {
		neighborhoodPanel.setVisible(true);
		gridOnedim.setVisible(false);
		gridTwoDim.setVisible(true);
		currentGrid = gridTwoDim;
	}

	private void setOneDimensional() {
		settingsPanel.setOneDimensional();
		neighborhoodPanel.setVisible(false);
		gridTwoDim.setVisible(false);
		gridOnedim.setVisible(true);
		currentGrid = gridOnedim;
		automatonName.setText(AutomatonType.ONE_DIM.toString());
	}
	

	private void init(AutomatonType type) {
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Automaton");
		setResizable(false);
		int windowWidth = 1200;
		int windowHeight = 675;
		setSize(windowWidth, windowHeight);
		setForeground(new Color(51, 51, 51));
		setContentPane(new JLabel(new ImageIcon(Background.BACKGROUND)));
		
		gridTwoDim = new TwoDimCellPanel(25, 25, new Dimension(600, 600), type);
		gridTwoDim.setLocation(30, 30);
		add(gridTwoDim);
		currentGrid = gridTwoDim;
		
		gridOnedim = new OneDimCellPanel(25, new Dimension(600, 600), type);
		gridOnedim.setLocation(30, 30);
		gridOnedim.setVisible(false);
		add(gridOnedim);
		
		iterationsCountLbl = new JLabel();
		setIterationsCount(0);
		iterationsCountLbl.setSize(100, 20);
		iterationsCountLbl.setFont(new Font("Arial", Font.PLAIN, 10));
		iterationsCountLbl.setLocation(40, 10);
		add(iterationsCountLbl);
		
		automatonName = new JLabel(AutomatonType.GAME_OF_LIFE.toString());
		automatonName.setSize(400, 50);
		automatonName.setFont(new Font("Arial", Font.PLAIN, 36));
		automatonName.setLocation(windowWidth - 410, 20);
		add(automatonName);
		
		startBtn = new JButton("START");
		startBtn.setSize(120, 55);
		startBtn.setLocation(windowWidth - 150, 90);
		add(startBtn);
		
		clearBtn = new JButton("Wyczyść");
		clearBtn.setSize(100, 45);
		clearBtn.setLocation(windowWidth - 140, 180);
		add(clearBtn);
		
		changeAutomatonBtn = new JButton("Zmień automat");
		changeAutomatonBtn.setSize(150, 45);
		changeAutomatonBtn.setLocation(windowWidth - 165, 230);
		add(changeAutomatonBtn);
		
		changeBoardSizeBtn = new JButton("Zmień rozmiar");
		changeBoardSizeBtn.setSize(150, 45);
		changeBoardSizeBtn.setLocation(windowWidth - 165, 280);
		add(changeBoardSizeBtn);
		
		speedPanel = new SpeedPanel();
		speedPanel.setSize(160, 190);
		speedPanel.setLocation(windowWidth - 340, 100);
		add(speedPanel);
		
		neighborhoodPanel = new NeighborhoodPanel();
		neighborhoodPanel.setSize(160, 350);
		neighborhoodPanel.setLocation(windowWidth - 340, 290);
		add(neighborhoodPanel);
		
		settingsPanel = new SettingsPanel();
		settingsPanel.setSize(180, 300);
		settingsPanel.setLocation(windowWidth - 530, 130);
		add(settingsPanel);
		
		setView(type);
		
		int width = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getWidth();
		int height = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getHeight();
		setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
	}

}
