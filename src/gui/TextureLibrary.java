package gui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import file.FileIO;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-10-2020
 * Description: Stores all textures.
*/
public class TextureLibrary {
	
	/**
	 * Texture map.
	 */
	private static Map<String, Texture> textures = new HashMap<>();
	
	/**
	 * Loads all textures in the assets file.
	 */
	public static void loadTextures() {
		if (FileIO.fileExists("assets")) {
			File assets = new File("assets");
			for (String textureFile : assets.list()) {
				int extensionIndex = textureFile.lastIndexOf('.');
				
				textures.put(textureFile.substring(0, extensionIndex), FileIO.readTexture("assets/" + textureFile));
			}
		}
	}
	
	/**
	 * Gets the texture associated with the id.
	 * @param id the id.
	 * @return the texture, or a default unknown texture if not found.
	 */
	public static Texture getTexture(String id) {
		return textures.containsKey(id) ? textures.get(id) : textures.get("unknown_texture");
	}

}
