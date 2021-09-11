import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The Password Checker Utility utilizes various methods 
 * to check the validity of either a single password
 * or an Array List of several passwords. Upon
 * receiving an invalid password, the Utility
 * throws a corresponding Exception.
 * 
 * @author Evan Song
 * @since 2021/09/11
 */
public class PasswordCheckerUtility {
	/**
	 * This method checks whether the password being
	 * inputed is at least 6 characters long
	 * @param pass This is a String representing a password
	 * @return boolean This should always return true, but will not reach the return statement if the password is less than 6 characters long, throwing an exception instead
	 * @throws LengthException A custom exception used when a password's length is invalid
	 */
	public static boolean isValidLength(String pass) throws LengthException {
		if(pass.length() < 6) {
			throw new LengthException("The password must be at least 6 characters long");
		}
		return true;
	}
	/**
	 * This method checks whether the password contains
	 * an upper case alphabetic character
	 * @param pass This is a String representing a password
	 * @return boolean This should always return true, but will not reach the return statement if the password has no upper case alphabetic character, throwing an exception instead
	 * @throws NoUpperAlphaException A custom exception used when a password does not contain an upper case character
	 */
	public static boolean hasUpperAlpha(String pass) throws NoUpperAlphaException {
		Pattern upper = Pattern.compile("[A-Z]");
		Matcher upperM = upper.matcher(pass);
		if(!upperM.find()) {
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
		}
		return true;
	}
	/**
	 * This method checks whether the password contains
	 * a lower case alphabetic character
	 * @param pass This is a String representing a password
	 * @return boolean This should always return true, but will not reach the return statement if the password has no lower case alphabetic character, throwing an exception instead
	 * @throws NoLowerAlphaException A custom exception used when a password does not contain a lower case character
	 */
	public static boolean hasLowerAlpha(String pass) throws NoLowerAlphaException {
		Pattern lower = Pattern.compile("[a-z]");
		Matcher lowerM = lower.matcher(pass);
		if(!lowerM.find()) {
			throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
		}
		return true;
	}
	/**
	 * This method checks whether the password contains
	 * a digit, or a numeric character from 0-9
	 * @param pass This is a String representing a password
	 * @return boolean This should always return true, but will not reach the return statement if the password does not contain a number, throwing an exception instead
	 * @throws NoDigitException A custom exception used when a password does not contain a numeric character
	 */
	public static boolean hasDigit(String pass) throws NoDigitException {
		Pattern number = Pattern.compile("[0-9]");
		Matcher numberM = number.matcher(pass);
		if(!numberM.find()) {
			throw new NoDigitException("The password must contain at least one digit");
		}
		return true;
	}
	/**
	 * This method checks whether the password contains
	 * a special character, defined by this program as any 
	 * character that is not alphanumeric (a letter or number)
	 * @param pass This is a String representing a password
	 * @return boolean This should always return true, but will not reach the return statement if the password only contains alphanumeric characters, throwing an exception instead
	 * @throws NoSpecialCharacterException A custom exception used when a password does not contain a special character
	 */
	public static boolean hasSpecialChar(String pass) throws NoSpecialCharacterException {
		Pattern special = Pattern.compile("[^A-Za-z0-9]");
		Matcher specialM = special.matcher(pass);
		if(!specialM.find()) {
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		}
		return true;
	}
	/**
	 * This method checks whether the password contains
	 * the same character three times in a row in any
	 * part of the password, ie; liZZZo or &&&&pi
	 * @param pass This is a String representing a password
	 * @return boolean This should always return true, but will not reach the return statement if the password has three consecutive identical characters, throwing an exception instead
	 * @throws InvalidSequenceException A custom exception used when a password has three of the same character in a row
	 */
	public static boolean hasSameCharInSequence(String pass) throws InvalidSequenceException {
		Pattern triples = Pattern.compile("([^ ])\\1\\1");
		Matcher triplesM = triples.matcher(pass);
		if(triplesM.find()) {
			throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
		}
		return true;
	}
	/**
	 * This method checks whether two passwords are equal to 
	 * each other, returning false if they do not match
	 * @param pass This is a String representing a password
	 * @param pass2 This is a String representing a second password, to be compared to the first
	 * @return boolean This should return true if the two passwords match, and false if they are different
	 */
	public static boolean comparePasswordsWithReturn(String pass, String pass2) {
		return pass.equals(pass2);
	}
	/**
	 * This method checks whether two passwords are equal to
	 * each other, throwing an exception if they do not match
	 * @param pass This is a String representing a password
	 * @param pass2 This is a String representing a second password, to be compared to the first
	 * @throws UnmatchedException A custom exception used when two passwords do not match each other
	 */
	public static void comparePasswords(String pass, String pass2) throws UnmatchedException {
		if(!pass.equals(pass2)) {
			throw new UnmatchedException("Passwords do not match");
		}
	}
	/**
	 * This method checks whether a password satisfies all components
	 * of a valid password, throwing a different exception if any
	 * component is missed.
	 * @param pass This is a String representing a password
	 * @return boolean This should return true if the password is valid, and should not return anything if the password is invalid, throwing a custom exception instead
	 * @throws LengthException A custom exception used when the password is less than 6 characters long
	 * @throws NoUpperAlphaException A custom exception used when the password has no upper case characters
	 * @throws NoLowerAlphaException A custom exception used when the password has no lower case characters
	 * @throws NoDigitException A custom exception used when the password contains no numbers
	 * @throws NoSpecialCharacterException A custom exception used when the password has only letters and numbers
	 * @throws InvalidSequenceException A custom exception used when the password has three or more consecutive identical characters
	 */
	public static boolean isValidPassword(String pass) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		//Length Exception Check
		isValidLength(pass);
		//Upper Exception Check
		hasUpperAlpha(pass);
		//Lower Exception Check
		hasLowerAlpha(pass);
		//Number Exception Check
		hasDigit(pass);
		//Special Exception Check
		hasSpecialChar(pass);
		//Sequence Check
		hasSameCharInSequence(pass);
		return true;
	}
	/**
	 * This method throws an exception if the password has 
	 * between six and nine characters, making it weak
	 * <p>
	 * Precondition: pass must be a valid password
	 * @param pass This is a String representing a valid password
	 * @return boolean This should throw an exception if the password is weak, returning false otherwise
	 * @throws WeakPasswordException A custom exception used when the password is between 6 and 9 characters long
	 */
	public static boolean isWeakPassword(String pass) throws WeakPasswordException {
		if(hasBetweenSixAndNineChars(pass)) {
			throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters");
		}
		return false;
	}
	/**
	 * This method checks whether a password has
	 * between six and nine characters
	 * @param pass This is a String representing a password
	 * @return boolean This should return true if the password is between 6 and 9 characters, and false otherwise
	 */
	public static boolean hasBetweenSixAndNineChars(String pass) {
		return (pass.length() >= 6 && pass.length() <= 9);
	}
	/**
	 * This method takes an Array List of passwords, going
	 * through each of them and determining whether each 
	 * one is valid. Upon finding an invalid password, the
	 * offending password is added to an empty Array List along
	 * with the reason for invalidity and then, once all of
	 * the passwords have been sorted, the Array List of 
	 * invalid passwords is returned.
	 * @param pws This is an ArrayList of Strings, each String representing a different password
	 * @return An ArrayList of all of the invalid passwords that were present in the input ArrayList, paired with the justification for putting them there
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> pws) {
		ArrayList<String> invals = new ArrayList<String>();
		for(String pass : pws) {
			try {
				isValidPassword(pass);
			} catch(Exception e) {
				invals.add(pass + " -> " + e.getMessage());
			}
		}
		return invals;
	}
}