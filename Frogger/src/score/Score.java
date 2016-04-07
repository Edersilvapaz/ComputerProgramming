package score;

/**
 * 
 * @author Eder Paz ; Neil Blake ; Logan Wedel
 */
public class Score {
	
	private static FileReader file = new FileReader("Test");
	
	public static String[] initials = new String[5];
	public static int[] score = new int[5];
	
	/**
	 * 
	 */
	public static void init(){
		
		for( int x=0 ; file.getFile().hasNext() ; x++){
			initials[x] = file.getFile().next();
			score[x] = file.getFile().nextInt();
		}
	}
	
	/**
	 * 
	 */
	public static void close(){
		file.close();
	}
}