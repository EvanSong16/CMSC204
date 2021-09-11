/**
 * This class is a custom exception that should be used
 * to indicate that a password is weak
 * @author Evan Song
 *
 */
class WeakPasswordException extends RuntimeException
{
  public WeakPasswordException() {}
  public WeakPasswordException(String message)
  {
    super(message);
  }
}