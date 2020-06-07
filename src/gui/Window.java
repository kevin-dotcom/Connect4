package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import file.FileIO;
import game.GameLoop;

public class Window {

	private static JFrame frame;
	private static BoardCanvas canvas = new BoardCanvas();
	
	private static WindowListener listener = new WindowListener() {

		@Override
		public void windowOpened(WindowEvent e) {
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			GameLoop.stopRunning();
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}
		
	};

	public static void init() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(50, 50, screenSize.width - 100, screenSize.height - 100);
		frame.addWindowListener(listener);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		
		var image = FileIO.readImage("assets/red.jpg").scale(3.0, 3.0);
		canvas.addImage(image);
		
		frame.add(canvas);
		
		frame.setVisible(true);
	}
	
	public static void addWindowListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}
	
	public static BoardCanvas getCanvas() {
		return canvas;
	}

}
