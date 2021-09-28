/**
 * This class is a custom exception that should be used
 * to indicate that a stack has overflowed, usually when
 * an object is added to a full stack
 * @author Evan Song
 *
 */
class StackOverflowException extends RuntimeException
{
  public StackOverflowException() {}
  public StackOverflowException(String message)
  {
    super(message);
  }
}