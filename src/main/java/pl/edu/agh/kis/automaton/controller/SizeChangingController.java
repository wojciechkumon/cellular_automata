package pl.edu.agh.kis.automaton.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Handles changing board size.
 * @author Wojciech Kumoń
 */
public class SizeChangingController implements ActionListener {
	private Controller controller;
	private int width;
	private int height;

	public SizeChangingController(Controller controller, int width, int height) {
		this.controller = controller;
		this.width = width;
		this.height = height;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		controller.stop();
		DimStrategy strategy = (controller.getAutomatonType() == AutomatonType.ONE_DIM) ? new Dim1Strategy()
				: new Dim2Strategy();
		JTextField widthField = new JTextField(Integer.toString(width), 5) {
			private static final long serialVersionUID = 1L;

			@Override
			public void addNotify() {
				super.addNotify();
				requestFocus();
			}
		};
		JTextField heightField = strategy.createHeightField();

		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.add(new JLabel("Szerokość (10-100):"));
		myPanel.add(widthField);
		strategy.addHeightField(myPanel, heightField);
		int result = JOptionPane.showConfirmDialog(controller.getView(), myPanel, "Ustaw wielkość planszy:",
				JOptionPane.OK_CANCEL_OPTION);

		if (result != JOptionPane.OK_OPTION) {
			return;
		}

		try {
			int width = Integer.parseInt(widthField.getText());
			int height = strategy.getHeight(heightField);
			checkBoardSize(width, height);
			this.width = width;
			this.height = height;
			controller.changeBoardSize();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(controller.getView(), "Wprowadź liczby z zakresu 10-100",
					"Błąd wprowadzonych danych", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void checkBoardSize(int width, int height) throws NumberFormatException {
		if ((width < 10) || (width > 100) || (height < 10) || (height > 100)) {
			throw new NumberFormatException("Wrong board size");
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	private interface DimStrategy {
		JTextField createHeightField();
		int getHeight(JTextField heightField);
		void addHeightField(JPanel panel, JTextField field);
	}
	
	private class Dim2Strategy implements DimStrategy {
		@Override
		public JTextField createHeightField() {
			return new JTextField(Integer.toString(height), 5);
		}
		
		@Override
		public int getHeight(JTextField heightField) {
			return Integer.parseInt(heightField.getText());
		}
		
		@Override
		public void addHeightField(JPanel panel, JTextField field) {
			panel.add(Box.createVerticalStrut(10));
			panel.add(new JLabel("Wysokość (10-100):"));
			panel.add(field);
		}
	}
	
	private class Dim1Strategy implements DimStrategy {
		@Override
		public JTextField createHeightField() {
			return null;
		}

		@Override
		public int getHeight(JTextField heightField) {
			return height;
		}

		@Override
		public void addHeightField(JPanel panel, JTextField field) {
			panel.add(Box.createVerticalStrut(25));
		}
	}

}
