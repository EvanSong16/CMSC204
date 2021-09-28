/**
 * This class is a custom exception that should be used
 * to indicate that a queue has underflowed, usually when
 * an object is removed from an empty queue
 * @author Evan Song
 *
 */
class QueueUnderflowException extends RuntimeException
{
  public QueueUnderflowException() {}
  public QueueUnderflowException(String message)
  {
    super(message);
  }
}