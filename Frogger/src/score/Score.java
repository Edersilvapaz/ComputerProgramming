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
	 * Test if the list of highest scores needs to be upgraded and if it does, upgrade it.
	 */
	public static void updateHighScores(String newInitials,int newScore){
		int x, tempScore, auxScore;
		String tempInitials, auxInitials;
		for(x=0 ; x<5 ; x++){
			if(score[x]<newScore){
				break;
			}
		}
		
		tempScore = score[x];
		tempInitials = initials[x];
		score[x] = newScore;
		initials[x] = newInitials;
		
		for(x+=1 ; x<5 ; x++){
			auxScore = score[x];
			auxInitials = initials[x];
			score[x] = tempScore;
			initials[x] = tempInitials;
			tempScore = auxScore;
			tempInitials = auxInitials;
		}
	}
	
	/**
	 * Rewrites a the high score file with the new high score data.
	 */
	public static void storeHighScores(){
		file.write(initials,score);
	}
}