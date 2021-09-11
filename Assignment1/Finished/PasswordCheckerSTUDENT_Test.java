
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Evan Song
 *
 */
public class PasswordCheckerSTUDENT_Test {
	ArrayList<String> invalidPasses;
	ArrayList<String> validPasses;
	String validPass = "ValidPass#1";
	String shortPass = "Short#1";
	String superShortPass = "Sh0r+";
	String noUpper = "passwithoutuppers#1";
	String noLower = "ALLCAPS#1";
	String sequencePass = "TooooManyOs#1";
	String noDigit = "MissingDigit#?";
	/**
	 * This method finishes instantiating the two Array Lists,
	 * filling them with passwords. One of them contains only
	 * valid passwords, while the other has invalid ones
	 */
	@Before
	public void setUp() {
		//Setting up Invalid Array List
		invalidPasses = new ArrayList<String>();
		invalidPasses.add(superShortPass);
		invalidPasses.add(noUpper);
		invalidPasses.add(noLower);
		invalidPasses.add(sequencePass);
		invalidPasses.add(noDigit);
		//Setting up Valid Array List
		validPasses = new ArrayList<String>();
		validPasses.add(validPass);
		validPasses.add(shortPass);
	}
	/**
	 * This method erases both of the Array Lists
	 */
	@After
	public void tearDown() throws Exception {
		invalidPasses = null;
		validPasses = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		assertTrue(PasswordCheckerUtility.isValidLength(validPass));
		Assertions.assertThrows(LengthException.class, () -> {
		  PasswordCheckerUtility.isValidPassword(superShortPass);
		});
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		assertTrue(PasswordCheckerUtility.hasUpperAlpha(validPass));
		Assertions.assertThrows(NoUpperAlphaException.class, () -> {
		  PasswordCheckerUtility.isValidPassword(noUpper);
		});
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		assertTrue(PasswordCheckerUtility.hasLowerAlpha(validPass));
		Assertions.assertThrows(NoLowerAlphaException.class, () -> {
		  PasswordCheckerUtility.isValidPassword(noLower);
		});
	}
	/**
	 * Test if the password is valid but less than 10 characters
	 * This test should return false for first case (Fully valid password)
	 * This test should throw a WeakPasswordException for second case (Weak but valid password)
	 */
	@Test
	public void testIsWeakPassword()
	{
		assertFalse(PasswordCheckerUtility.isWeakPassword(validPass));
		Assertions.assertThrows(WeakPasswordException.class, () -> {
		  PasswordCheckerUtility.isWeakPassword(shortPass);
		});
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		assertTrue(PasswordCheckerUtility.hasSameCharInSequence(validPass));
		Assertions.assertThrows(InvalidSequenceException.class, () -> {
		  PasswordCheckerUtility.isValidPassword(sequencePass);
		});
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		assertTrue(PasswordCheckerUtility.hasDigit(validPass));
		Assertions.assertThrows(NoDigitException.class, () -> {
		  PasswordCheckerUtility.isValidPassword(noDigit);
		});
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		assertTrue(PasswordCheckerUtility.isValidPassword(validPass));
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 * First case should return an empty ArrayList
	 */
	@Test
	public void testInvalidPasswords() {
		//Check if all valid passwords get through
		ArrayList<String> checker;
		checker = PasswordCheckerUtility.getInvalidPasswords(validPasses);
		assertEquals(checker.size(), 0);
		//Check if the Array of invalid passwords is correct
		checker = PasswordCheckerUtility.getInvalidPasswords(invalidPasses);
		assertEquals(checker.get(0), "Sh0r+ -> The password must be at least 6 characters long");
		assertEquals(checker.get(1), "passwithoutuppers#1 -> The password must contain at least one uppercase alphabetic character");
		assertEquals(checker.get(2), "ALLCAPS#1 -> The password must contain at least one lower case alphabetic character");
		assertEquals(checker.get(3), "TooooManyOs#1 -> The password cannot contain more than two of the same character in sequence");
		assertEquals(checker.get(4), "MissingDigit#? -> The password must contain at least one digit");
	}
	
}