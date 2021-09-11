/**
 * This class is a custom exception that should be used
 * to indicate that a password is too short, in this
 * case meaning less than six characters long
 * @author Evan Song
 *
 */
class LengthException extends RuntimeException
{
  public LengthException() {}
  public LengthException(String message)
  {
    super(message);
  }
}