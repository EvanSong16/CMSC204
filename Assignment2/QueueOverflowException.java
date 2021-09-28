/**
 * This class is a custom exception that should be used
 * to indicate that a queue has overflowed, usually when
 * an object is added to a full queue
 * @author Evan Song
 *
 */
class QueueOverflowException extends RuntimeException
{
  public QueueOverflowException() {}
  public QueueOverflowException(String message)
  {
    super(message);
  }
}