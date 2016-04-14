package score;

/**
 * This class holds the arrays that store the name and the score of the five high scores of the game.
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Score {
	
	private static FileHandler file = new FileHandler("Test");
	
	public static String[] initials = new String[5];
	public static int[] score = new int[5];
	
	/**
	 * Initiate the high scores of the game according to the data saved in the HighScores file in the resource folder.
	 */
	public static void init(){
		for( int x=0 ; file.getFile().hasNext() ; x++){
			initials[x] = file.getFile().next();
			score[x] = file.getFile().nextInt();
		}
	}
	
	/**
	 * Closes the scanner object used to read from the file.
	 */
	public static void close(){
		file.close();
	}
	
	/**
	 * 
	 */
	public static void updateHighScores(String newInitials,int newScore){
		for(int x=0 ; x<5 ; x++){
			if(score[x]<newScore){
				score[x]=newScore;
				initials[x]=newInitials;
				break;
			}
		}
	}
	
	/**
	 * 
	 */
	public static void storeHighScores(){
		file.write(initials,score);
	}
}