package pl.edu.agh.kis.automaton.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Cell with JButton possibilities.
 * @author Wojciech Kumoń
 */
public class GridCell extends JButton {
	private static final long serialVersionUID = -4848619941459842386L;
	
	/**
	 * Contructs GridCell
	 * @param dim size of cell
	 */
	public GridCell(Dimension dim) {
		setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
        setSize(dim);
        setIcon(CellImages.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	/**
	 * Sets resized icon as icon (not resized icon is saved).
	 * @param icon icon to set
	 */
	public void setResizedIcon(ImageIcon icon) {
		Image img = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		setIcon(new ResizedImageIcon(img, icon));
	}

}
