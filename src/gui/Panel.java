package gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public abstract class Panel extends JPanel {

	private static final long serialVersionUID = -548829795603564353L;
	
	protected Dimension size = Window.getAppSize();
	
	public abstract void onMouseReleased(MouseEvent e);
	public abstract void onMousePressed(MouseEvent e);

}
