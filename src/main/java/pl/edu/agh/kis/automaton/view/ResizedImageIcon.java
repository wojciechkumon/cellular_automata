package pl.edu.agh.kis.automaton.view;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ImageIcon which holds not resized image.
 * @author Wojciech Kumoń
 */
public class ResizedImageIcon extends ImageIcon {
	private static final long serialVersionUID = 207608227583404902L;
	private ImageIcon source;
	
	/**
	 * Contructs ResizedImageIcon
	 * @param imageToSet image to set
	 * @param source original image to save
	 */
	public ResizedImageIcon(Image imageToSet, ImageIcon source) {
		super(imageToSet);
		this.source = source;
	}

	public ImageIcon getSource() {
		return source;
	}

}
