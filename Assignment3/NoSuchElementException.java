/**
 * This class is a custom exception that should be used
 * to indicate that an iterator has tried to access an
 * element that does not exist, for example, if the next()
 * function is called at the end of the list
 * @author Evan Song
 *
 */
class NoSuchElementException extends RuntimeException
{
  public NoSuchElementException() {}
  public NoSuchElementException(String message)
  {
    super(message);
  }
}