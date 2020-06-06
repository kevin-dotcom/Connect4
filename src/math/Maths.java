package math;

public class Maths {
	
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
	
	public static double getAverage(double x, double y, double dx, double dy) {
		return (x * y + dy) / (x + dx);
	}
	
}
