/**
 * This class is a custom exception that should be used
 * to indicate that a password has no special character
 * @author Evan Song
 *
 */
class NoSpecialCharacterException extends RuntimeException
{
  public NoSpecialCharacterException() {}
  public NoSpecialCharacterException(String message)
  {
    super(message);
  }
}