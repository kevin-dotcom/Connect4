package file;

import java.io.*;

import statistics.GameStatistics;

public class FileIO {
	
	private static BufferedReader reader;
	private static PrintWriter writer;
	private static boolean isWriting;
	
	public static void beginWriting(String filepath) {
		try {
			if (isWriting) {
				throw new IOException("PrintWriter never closed!");
			}
			else {
				File file = new File(filepath);
				
				file.getParentFile().mkdirs();
				
				writer = new PrintWriter(file);
				isWriting = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void endWriting() {
		writer.close();
		isWriting = false;
	}
	
	public static void println() {
		writer.println();
	}
	
	public static void println(String str) {
		writer.println(str);
	}
	
	public static void print(String str) {
		writer.print(str);
	}
	
	public static void print(GameStatistics gs) {
		println(gs.toString());
	}
	
	public static void printf(String format, Object...args) {
		writer.printf(format, args);
	}
	
	public static String readFile(String filepath) {
		String text = "";
		try {
			reader = new BufferedReader(new FileReader(filepath));
			
			String line = reader.readLine();
			while (line != null) {
				text += line + '\n';
				line = reader.readLine();
			}
			
			return text;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean fileExists(String filePath) {
		return new File(filePath).exists();
	}
	
}
