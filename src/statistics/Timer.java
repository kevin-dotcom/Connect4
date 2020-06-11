package statistics;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Timer class.
*/
public class Timer {
	
	/**
	 * All instances of timers.
	 */
	private static Map<String, Timer> timers = new HashMap<>();
	
	/**
	 * The previous time.
	 */
	private static long previousTime;
	
	/**
	 * Stores if the timer is timing.
	 */
	private static boolean isTiming;
	
	/**
	 * Time to make each move.
	 */
	private List<Long> moveTimes = new ArrayList<>();
	
	/**
	 * The last time the timer started.
	 */
	private long lastStartedTime;
	
	/**
	 * The total time.
	 */
	private long totalTime;
	
	/**
	 * Stores if this instance is timing.
	 */
	private boolean isRunning = false;
	
	/**
	 * Gets current time with the given format.
	 * @param format the format.
	 * @return the formatted time.
	 */
	public static String getCurrentTime(String format) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}
	
	/**
	 * Creates a new timer.
	 * @param id an identifier.
	 */
	public static void createTimer(String id) {
		timers.put(id, new Timer());
	}
	
	/**
	 * Start timing.
	 */
	public static void startTiming() {
		previousTime = System.currentTimeMillis();
		isTiming = true;
	}
	
	/**
	 * Stop timing.
	 */
	public static void stopTiming() {
		isTiming = false;
	}
	
	/**
	 * Start the timer with the given id.
	 * @param id the identifier.
	 */
	public static void startTimer(String id) {
		timers.get(id).startTimer();
	}
	
	/**
	 * Starts the timer.
	 */
	private void startTimer() {
		lastStartedTime = System.currentTimeMillis();
		isRunning = true;
		moveTimes.add(0l);
	}
	
	/**
	 * Stops the timer with the given id.
	 * @param id the identifier.
	 */
	public static void stopTimer(String id) {
		timers.get(id).stopTimer();
	}
	
	/**
	 * Stops the timer and update its time.
	 */
	private void stopTimer() {
		long currentTime = System.currentTimeMillis();
		long delta = currentTime - previousTime;
		
		update(delta);
		
		isRunning = false;
	}
	
	/**
	 * Updates the running timers.
	 */
	public static void updateTimers() {
		if (isTiming) {
			long currentTime = System.currentTimeMillis();
			long delta = currentTime - previousTime; // Change in time
			
			for (String key : timers.keySet()) { // Update all timers
				Timer timer = timers.get(key);
				
				if (timer.lastStartedTime > previousTime) { // Check if timer just started
					continue;
				}
				
				timer.update(delta);
			}
			
			previousTime = currentTime;
		}
	}
	
	/**
	 * Updates the timer.
	 * @param delta the change in time.
	 */
	private void update(long delta) {
		if (isRunning && isTiming) {
			totalTime += delta;
			
			int laps = moveTimes.size();
			moveTimes.set(laps - 1, moveTimes.get(laps - 1) + delta);
		}
	}
	
	/**
	 * @return the time.
	 */
	public long getTime() {
		long time = totalTime;
		
		if (isRunning) {
			time += System.currentTimeMillis() - previousTime;
		}
		
		return time;
	}
	
	/**
	 * @return the list of move times.
	 */
	public List<Long> getMoveTimes() {
		return moveTimes;
	}
	
	/**
	 * Gets the timer with the given id.
	 * @param id the identifier.
	 * @return the timer.
	 */
	public static Timer getTimer(String id) {
		return timers.get(id);
	}
	
}
