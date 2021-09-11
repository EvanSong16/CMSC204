/**
 * This class is a custom exception that should be used
 * to indicate that a password has no numbers
 * @author Evan Song
 *
 */
class NoDigitException extends RuntimeException
{
  public NoDigitException() {}
  public NoDigitException(String message)
  {
    super(message);
  }
}