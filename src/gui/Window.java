package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import game.GameLoop;

public class Window {

	private static JFrame frame;
	private static BoardCanvas canvas;
	
	private static Dimension appSize;
	
	private static boolean disposed;
	
	private static WindowListener listener = new WindowListener() {

		@Override
		public void windowOpened(WindowEvent e) {
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			disposed = true;
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
		appSize = new Dimension(screenSize.width - 100, screenSize.height - 100);
		frame.setBounds((screenSize.width - appSize.width) / 2, (screenSize.height - appSize.height) / 2, appSize.width, appSize.height);
		frame.addWindowListener(listener);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		
		canvas = new BoardCanvas();
		frame.add(canvas);
		
		frame.setVisible(true);
	}
	
	public static void addWindowListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}
	
	public static void dispose() {
		if (!disposed) {
			frame.dispose();
			disposed = true;
		}
	}
	
	public static BoardCanvas getCanvas() {
		return canvas;
	}
	
	public static Dimension getAppSize() {
		return appSize;
	}

}
