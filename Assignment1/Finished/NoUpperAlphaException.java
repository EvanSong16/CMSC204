/**
 * This class is a custom exception that should be used
 * to indicate that a password has no upper case letters
 * @author Evan Song
 *
 */
class NoUpperAlphaException extends RuntimeException
{
  public NoUpperAlphaException() {}
  public NoUpperAlphaException(String message)
  {
    super(message);
  }
}