package pl.edu.agh.kis.automaton.view;

import javax.swing.JPanel;

import pl.edu.agh.kis.automaton.controller.Controller;

/**
 * Panel containing settings for all automatons.
 * @author Wojciech Kumoń
 */
public class SettingsPanel extends JPanel {
	private static final long serialVersionUID = -2871043393069157141L;
	
	private GameOfLifeSettingsPanel gameOfLifeSettingsPanel;
	private QuadLifeSettingsPanel quadLifeSettingsPanel;
	private WireworldSettingsPanel wireworldSettingsPanel;
	private LangtonAntColorChooserPanel langtonAntColorChooser;
	private OneDimSettingsPanel oneDimSettingPanel;
	
	public SettingsPanel() {
		init();
	}
	
	/**
	 * Sets controller for settings panels.
	 * @param controller controller containing settings controllers
	 */
	public void setController(Controller controller) {
		gameOfLifeSettingsPanel.setController(controller.getGoLRulesController(),
				controller.getInsertionController().getGoLInsertingController());
		quadLifeSettingsPanel.setController(controller.getClickColorCtrl(),
				controller.getInsertionController().getQuadLifeInsertingController());
		wireworldSettingsPanel.setController(controller.getClickColorCtrl(),
				controller.getInsertionController().getWireworldInsertingController());
		langtonAntColorChooser.setController(controller.getClickColorCtrl());
		oneDimSettingPanel.setController(controller.getOneDimRulesController());
	}
	
	public void setGameOfLife() {
		removeAllSpecialItems();
		gameOfLifeSettingsPanel.setVisible(true);
	}
	
	public GameOfLifeSettingsPanel getGolPanel() {
		return gameOfLifeSettingsPanel;
	}
	
	public void setQuadLife() {
		removeAllSpecialItems();
		quadLifeSettingsPanel.setVisible(true);
		quadLifeSettingsPanel.reset();
		System.out.println("quad");
	}

	public QuadLifeSettingsPanel getQuadLifePanel() {
		return quadLifeSettingsPanel;
	}
	
	public void setWireWorld() {
		removeAllSpecialItems();
		wireworldSettingsPanel.setVisible(true);
		wireworldSettingsPanel.reset();
		System.out.println("wire");
	}

	public WireworldSettingsPanel getWireworldPanel() {
		return wireworldSettingsPanel;
	}
	
	public void setLangtonAnt() {
		removeAllSpecialItems();
		langtonAntColorChooser.setVisible(true);
		langtonAntColorChooser.reset();
	}

	public LangtonAntColorChooserPanel getLangtonAntPanel() {
		return langtonAntColorChooser;
	}
	
	public void setOneDimensional() {
		removeAllSpecialItems();
		oneDimSettingPanel.setVisible(true);
	}

	public OneDimSettingsPanel getOneDimPanel() {
		return oneDimSettingPanel;
	}
	
	private void removeAllSpecialItems() {
		gameOfLifeSettingsPanel.setVisible(false);
		quadLifeSettingsPanel.setVisible(false);
		wireworldSettingsPanel.setVisible(false);
		langtonAntColorChooser.setVisible(false);
		oneDimSettingPanel.setVisible(false);
	}
	
	private void init() {
		setOpaque(false);
		setLayout(null);
		gameOfLifeSettingsPanel = new GameOfLifeSettingsPanel();
		gameOfLifeSettingsPanel.setSize(180, 300);
		gameOfLifeSettingsPanel.setLocation(0, 0);
		add(gameOfLifeSettingsPanel);
		
		quadLifeSettingsPanel = new QuadLifeSettingsPanel();
		quadLifeSettingsPanel.setSize(180, 300);
		quadLifeSettingsPanel.setLocation(0, 0);
		quadLifeSettingsPanel.setVisible(false);
		add(quadLifeSettingsPanel);
		
		wireworldSettingsPanel = new WireworldSettingsPanel();
		wireworldSettingsPanel.setSize(180, 300);
		wireworldSettingsPanel.setLocation(0, 0);
		wireworldSettingsPanel.setVisible(false);
		add(wireworldSettingsPanel);
		
		langtonAntColorChooser = new LangtonAntColorChooserPanel();
		langtonAntColorChooser.setSize(180, 100);
		langtonAntColorChooser.setLocation(0, 0);
		langtonAntColorChooser.setVisible(false);
		add(langtonAntColorChooser);
		
		oneDimSettingPanel = new OneDimSettingsPanel();
		oneDimSettingPanel.setSize(180, 200);
		oneDimSettingPanel.setLocation(0, 0);
		oneDimSettingPanel.setVisible(false);
		add(oneDimSettingPanel);
	}

}
