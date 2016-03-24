package pl.edu.agh.kis.automaton.controller;

/**
 * Class containing all inserting controllers.
 * @author Wojciech Kumoń
 */
public class StructureInsertingController {
	private Controller controller;
	private boolean justInserted = false;

	public StructureInsertingController(Controller controller) {
		this.controller = controller;
	}

	public GolStructureInsertingController getGoLInsertingController() {
		return new GolStructureInsertingController(controller);
	}

	public QuadLifeStructureInsertingController getQuadLifeInsertingController() {
		return new QuadLifeStructureInsertingController(controller);
	}

	public WireworldStructureInsertingController getWireworldInsertingController() {
		return new WireworldStructureInsertingController(controller);
	}

	public boolean isJustInserted() {
		return justInserted;
	}

	public void setJustInserted(boolean justInserted) {
		this.justInserted = justInserted;
	}

}
