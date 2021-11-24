import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * A class which is mainly used statically during tests and in the JavaFX application.
 * It contains a static reference to a MorseCodeTree and is mainly used to convert 
 * Strings and files from Morse code into English. It can also print the static reference 
 * to the MorseCodeTree using the printTree method
 * 
 * @author Evan Song
 *
 */
public class MorseCodeConverter {
	private static MorseCodeTree tree = new MorseCodeTree();
	
	/**
	 * A simple static method which returns a String made up of the contents of the 
	 * MorseCodeTree in LNR order, using the MorseCodeTree's toArrayList method
	 * @return String The String form of the tree in LNR order
	 */
	public static String printTree() {
		String result = "";
		ArrayList<String> lnr = tree.toArrayList();
		for(String s : lnr) {
			result += s + " ";
		}
		return result.strip();
	}
	
	/**
	 * A static method that uses the fetch method to convert Strings from Morse code 
	 * to English. The code treats all characters which are not periods, spaces, 
	 * or slashes as dashes, allowing for either underscores or dashes to be used 
	 * interchangeably. Additionally, the slashes only register as spaces when 
	 * given a buffer of a space on either side. These could be problematic if 
	 * the initial String is formatted incorrectly, but should work perfectly 
	 * assuming correct format.
	 * @param code The initial message to be converted from Morse code to English
	 * @return String The translated message
	 */
	public static String convertToEnglish(String code) {
		String result = "";
		String[] words = code.split(" / ");
		for(String word : words) {
			String[] letters = word.split(" ");
			for(String letter : letters) {
				result += tree.fetch(letter);
			}
			result += " ";
		}
		return result.strip();
	}
	
	/**
	 * An overloaded method using File instead of String. This method simply 
	 * converts the input file into a String and then uses the convertToEnglish 
	 * method using that String.
	 * @param codeFile A file containing the initial message to be converted from Morse code to English
	 * @return String The translated message
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		Scanner scan = new Scanner(codeFile);
		String message = "";
		while (scan.hasNextLine()) {
			message += scan.nextLine();
		}
		scan.close();
		return convertToEnglish(message);
	}
}
