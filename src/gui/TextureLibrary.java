package gui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import file.FileIO;

public class TextureLibrary {
	
	private static Map<String, Texture> textures = new HashMap<>();
	
	public static void loadTextures() {
		if (FileIO.fileExists("assets")) {
			File assets = new File("assets");
			for (String textureFile : assets.list()) {
				int extensionIndex = textureFile.lastIndexOf('.');
				
				textures.put(textureFile.substring(0, extensionIndex), FileIO.readTexture("assets/" + textureFile));
			}
		}
	}
	
	public static Texture getTexture(String id) {
		return textures.containsKey(id) ? textures.get(id) : textures.get("unknown_texture");
	}

}
