/**
 * This class is a custom exception that should be used
 * to indicate that a stack has underflowed, usually when
 * an object is removed from an empty stack
 * @author Evan Song
 *
 */
class StackUnderflowException extends RuntimeException
{
  public StackUnderflowException() {}
  public StackUnderflowException(String message)
  {
    super(message);
  }
}