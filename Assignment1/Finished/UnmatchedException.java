/**
 * This class is a custom exception that should be used
 * to indicate that two passwords do not match
 * @author Evan Song
 *
 */
class UnmatchedException extends Exception
{
  public UnmatchedException() {}
  public UnmatchedException(String message)
  {
    super(message);
  }
}