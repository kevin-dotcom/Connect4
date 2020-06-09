package math;

import java.awt.*;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Maths.
*/
public class Maths {
	
	// Distance
	
	public static int getDistance(int c1, int r1, int c2, int r2) {
		return getDistance(c2 - c1, r2 - r1);
	}
	
	public static int getDistance(int dc, int dr) {
		return Math.max(Math.abs(dc), Math.abs(dr));
	}
	
	// Conditionals ----------
	
	public static boolean inRange(int val, int min, int max) {
		return val >= min && val <= max;
	}
	
	public static boolean inRange(double val, double min, double max) {
		return val >= min && val <= max;
	}
	
	// Time Conversion ----------
	
	public static String secondsToCombination(double s) {
		return millisecondsToCombination(s * 1000.0);
	}
	
	public static String millisecondsToCombination(long ms) {
		int milliseconds = (int) (ms % 1000);
		int seconds = (int) (ms / 1000);
		int minutes = (int) (seconds / 60);
		
		return minutes + "min " + seconds + "s " + milliseconds + "ms";
	}
	
	public static String millisecondsToCombination(double ms) {
		int milliseconds = (int) (ms % 1000.0);
		int seconds = (int) (ms / 1000);
		int minutes = (int) (seconds / 60);
		
		return minutes + "min " + seconds + "s " + milliseconds + "ms";
	}
	
	public static double timeToSeconds(String t) {
		double time = 0;
		
		int minIndex = t.indexOf("min");
		if (minIndex == -1) {
			return -1;
		}
		time += 60 * Integer.parseInt(t.substring(0, minIndex));
		
		int secIndex = t.indexOf('s');
		if (secIndex == -1) {
			return -1;
		}
		time += Integer.parseInt(t.substring(minIndex + 4, secIndex));
		
		int msIndex = t.indexOf("ms");
		if (msIndex == -1) {
			return 0;
		}
		time += Double.parseDouble(t.substring(secIndex + 2, msIndex)) / 1000.0;
		
		return time;
	}
	
	// Average ----------
	
	public static double getAverage(double x, double y, double dx, double dy) {
		return (x * y + dy) / (x + dx);
	}
	
	// Font ----------
	
	public static Font getMaxFittingFontSize(Graphics g, Font font, String string, int width, int height) {
		int minSize = 0;
		int maxSize = 288;
		int curSize = font.getSize();

		while (maxSize - minSize > 2) {
			FontMetrics fm = g.getFontMetrics(new Font(font.getName(), font.getStyle(), curSize));
			int fontWidth = fm.stringWidth(string);
			int fontHeight = fm.getLeading() + fm.getMaxAscent() + fm.getMaxDescent();

			if ((fontWidth > width) || (fontHeight > height)) {
				maxSize = curSize;
				curSize = (maxSize + minSize) / 2;
			}
			else {
				minSize = curSize;
				curSize = (minSize + maxSize) / 2;
			}
		}

		return new Font(font.getName(), font.getStyle(), curSize);
	}
	
}
