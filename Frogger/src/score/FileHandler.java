package score;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is responsible for loading and writting in the .txt file which contains the player's initials and scores.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class FileHandler {
	
	private Scanner file = null;
	private FileWriter writer = null;
	private String txtName;
	
	/**
	 * Loads and stores as a scanner the text file saved in the resource folder with the name specified by txtName.
	 * @param txtName Name of the file saved in the resource folder.
	 */
	public FileHandler(String txtName){
		this.txtName = txtName;
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
	
	/**
	 * Rewrites the high scores files with the new high scores information.
	 * @param initials Initials of the player's high scores
	 * @param scores Scores of the player's high scores
	 */
	public void write(String[] initials,int[] scores){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File fileName = new File(classLoader.getResource("ScoreTrack/"+txtName+".txt").getFile());
		
		try {
			this.writer = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int x=0 ; x<initials.length ; x++){
			try {
				writer.write(initials[x]+"\t"+scores[x]+"\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}