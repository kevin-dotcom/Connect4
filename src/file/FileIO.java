package file;

import java.io.*;

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
				writer = new PrintWriter(filepath);
				isWriting = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void endWriting() {
		writer.close();
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
	
	public static void printf(String format, Object...args) {
		writer.printf(format, args);
	}
	
	public static String readFile(String filepath) {
		String text = "";
		try {
			reader = new BufferedReader(new FileReader(filepath));
			
			String line = reader.readLine();
			while (line != null) {
				text += line;
			}
			
			return text;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
