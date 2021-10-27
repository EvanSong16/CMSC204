/**
 * This class is a custom exception that should be used
 * to indicate that an attempted operation or method has
 * been called that has not yet been implemented
 * @author Evan Song
 *
 */
class UnsupportedOperationsExceptions extends RuntimeException
{
  public UnsupportedOperationsExceptions() {}
  public UnsupportedOperationsExceptions(String message)
  {
    super(message);
  }
}