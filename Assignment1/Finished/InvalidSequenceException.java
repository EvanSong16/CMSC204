/**
 * This class is a custom exception that should be used
 * to indicate that a password has too many identical 
 * characters in a row, in this case, three or more 
 * @author Evan Song
 *
 */
class InvalidSequenceException extends RuntimeException
{
  public InvalidSequenceException() {}
  public InvalidSequenceException(String message)
  {
    super(message);
  }
}