package file;

import java.io.*;

import javax.imageio.ImageIO;

import components.Image;
import statistics.GameStatistics;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Deals with input and output for the program.
*/

public class FileIO {
	
	/**
	 * Reader for text files.
	 */
	private static BufferedReader reader;
	
	/**
	 * Writes text files.
	 */
	private static PrintWriter writer;
	
	/**
	 * Stores if the writer is writing.
	 */
	private static boolean isWriting;
	
	/**
	 * Begin writing to a given file.
	 * @param filepath the file path/\.
	 */
	public static void beginWriting(String filepath) {
		try {
			if (isWriting) {
				throw new IOException("PrintWriter never closed!");
			}
			else {
				File file = new File(filepath);
				
				// Creates the parent directory if not existing
				file.getParentFile().mkdirs();
				
				writer = new PrintWriter(file);
				isWriting = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Save the file.
	 */
	public static void endWriting() {
		writer.close();
		isWriting = false;
	}
	
	/**
	 * Ends the line.
	 */
	public static void println() {
		writer.println();
	}
	
	/**
	 * Prints a string and start a new line.
	 * @param str the string.
	 */
	public static void println(String str) {
		writer.println(str);
	}
	
	/**
	 * Prints a string.
	 * @param str the string.
	 */
	public static void print(String str) {
		writer.print(str);
	}
	
	/**
	 * Prints the game statistics.
	 * @param gs the game statistics.
	 */
	public static void print(GameStatistics gs) {
		println(gs.toString());
	}
	
	/**
	 * Prints using a format.
	 * @param format the format.
	 * @param args the arguments.
	 */
	public static void printf(String format, Object...args) {
		writer.printf(format, args);
	}
	
	/**
	 * Reads a file and returns the contents.
	 * @param filepath the file path.
	 * @return the contents of the file.
	 */
	public static String readFile(String filepath) {
		String text = "";
		try {
			reader = new BufferedReader(new FileReader(filepath));
			
			String line = reader.readLine();
			while (line != null) {
				text += line + '\n';
				line = reader.readLine();
			}
			
			reader.close();
			
			return text;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	/**
	 * Checks if a file/directory exists.
	 * @param filePath the file path.
	 * @return {@code true} if the file exists.
	 */
	public static boolean fileExists(String filePath) {
		return new File(filePath).exists();
	}
	
	/**
	 * Returns an Image object of an image at the given path.
	 * @param filepath the file path.
	 * @return the image.
	 */
	public static Image readImage(String filepath) {
		try {
			return new Image(ImageIO.read(new File(filepath)));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
}
