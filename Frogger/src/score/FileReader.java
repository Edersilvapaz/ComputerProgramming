package score;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is responsible for loading the .txt file which contains the player's initials and scores.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class FileReader {
	
	private Scanner file = null;
	
	/**
	 * Loads and stores as a scanner the text file saved in the resource folder with the name specified by txtName.
	 * @param txtName Name of the file saved in the resource folder.
	 */
	public FileReader(String txtName){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File fileName = new File(classLoader.getResource("ScoreTrack/"+txtName+".txt").getFile());
		
		try{
			this.file = new Scanner(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the scores file to be read and or written.
	 * @return File which stores the high scores of the game.
	 */
	public Scanner getFile(){
		return file;
	}
	
	/**
	 * Closes the scanner object, used in the end of the game when no reading and writing is necessary anymore.
	 */
	public void close(){
		file.close();
	}
}