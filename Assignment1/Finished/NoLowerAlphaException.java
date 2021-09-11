/**
 * This class is a custom exception that should be used
 * to indicate that a password has no lower case letters
 * @author Evan Song
 *
 */
class NoLowerAlphaException extends RuntimeException
{
  public NoLowerAlphaException() {}
  public NoLowerAlphaException(String message)
  {
    super(message);
  }
}