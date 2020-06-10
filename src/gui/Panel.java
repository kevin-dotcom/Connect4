package gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-10-2020
 * Description: Panel class.
*/
public abstract class Panel extends JPanel {

	private static final long serialVersionUID = -548829795603564353L;
	
	/**
	 * Size of the window.
	 */
	protected Dimension size = Window.getAppSize();
	
	/**
	 * Called when a mouse button is released.
	 * @param e the mouse event.
	 */
	public abstract void onMouseReleased(MouseEvent e);
	
	/**
	 * Called when a mouse button is pressed.
	 * @param e the mouse event.
	 */
	public abstract void onMousePressed(MouseEvent e);

}
