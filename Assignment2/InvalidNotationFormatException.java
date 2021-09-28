/**
 * This class is a custom exception that should be used
 * to indicate that an expression, either prefix or 
 * postfix, has incorrect notation. 
 * @author Evan Song
 *
 */
class InvalidNotationFormatException extends RuntimeException
{
  public InvalidNotationFormatException() {}
  public InvalidNotationFormatException(String message)
  {
    super(message);
  }
}