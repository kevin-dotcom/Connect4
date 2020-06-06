package statistics;

import java.util.*;

public class Timer {
	
	private static Map<String, Timer> timers = new HashMap<>();
	private static long previousTime;
	private static boolean isTiming;
	
	private List<Long> moveTimes = new ArrayList<>();
	private long lastStartedTime;
	private long totalTime;
	
	private boolean isRunning = false;
	
	public static void createTimer(String id) {
		timers.put(id, new Timer());
	}
	
	public static void startTiming() {
		previousTime = System.currentTimeMillis();
		isTiming = true;
	}
	
	public static void stopTiming() {
		isTiming = false;
	}
	
	public static void startTimer(String id) {
		timers.get(id).startTimer();
	}
	
	private void startTimer() {
		lastStartedTime = System.currentTimeMillis();
		updateTimers();
		isRunning = true;
		moveTimes.add(0l);
	}
	
	public static void stopTimer(String id) {
		timers.get(id).stopTimer();
	}
	
	private void stopTimer() {
		long currentTime = System.currentTimeMillis();
		long delta = currentTime - previousTime;
		
		update(delta);
		
		isRunning = false;
	}
	
	public static void updateTimers() {
		if (isTiming) {
			long currentTime = System.currentTimeMillis();
			long delta = currentTime - previousTime;
			
			for (String key : timers.keySet()) {
				Timer timer = timers.get(key);
				
				if (timer.lastStartedTime > previousTime) {
					continue;
				}
				
				timer.update(delta);
			}
			
			previousTime = currentTime;
		}
	}
	
	private void update(long delta) {
		if (isRunning) {
			totalTime += delta;
			
			int laps = moveTimes.size();
			moveTimes.set(laps - 1, moveTimes.get(laps - 1) + delta);
		}
	}
	
	public long getTime() {
		return totalTime;
	}
	
	public List<Long> getMoveTimes() {
		return moveTimes;
	}
	
	public static Timer getTimer(String id) {
		return timers.get(id);
	}
	
}
