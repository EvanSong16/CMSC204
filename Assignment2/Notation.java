/**
 * A utility class that can convert equations 
 * from infix to postfix and vice versa, as well
 *  as evaluating postfix expressions
 * @author Evan Song
 * @since 9/27/2021
 */
public class Notation {
	static final char[] OPERANDS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
	static final char[] OPERATORS = {'+', '-', '*', '/',};
	/**
	 * A quick helper method to check whether a char Array
	 * contains a specific character, sequentially
	 * @param cArr An array of chars to be searched through
	 * @param lookFor The character the method is trying to find in cArr
	 * @return boolean Should return true when cArr contains c, and false if not
	 */
	public static boolean contains(char[] cArr, char lookFor) {
		for(char c : cArr) {
			if(c == lookFor) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Utility method used to determine
	 * operator precedence of different chars
	 * @param c A char, should only be a '+', '-', '*', or '/'
	 * @return int Should return 1 for a low precedence character and 2 for a high precedence character
	 */
	public static int precedence(char c) {
		if(c == '+' || c == '-') {
			return 1;
		}
		return 2;
	}
	/**
	 * Evaluates a simple expression involving a single operation
	 * @param front A String representing the first operand, should be a number
	 * @param c An operation to perform between front and back
	 * @param back A String representing a second operand, should also be a number
	 * @return String A string representing the fully evaluated expression, should be a number
	 */
	public static String evaluate(String front, char c, String back) {
		double result = 0.0;
		if(c == '+') {
			result = (Double.parseDouble(front) + Double.parseDouble(back));
		}
		if(c == '-') {
			result = (Double.parseDouble(front) - Double.parseDouble(back));
		}
		if(c == '*') {
			result = (Double.parseDouble(front) * Double.parseDouble(back));
		}
		if(c == '/') {
			result = (Double.parseDouble(front) / Double.parseDouble(back));
		}
		return "" + result;
	}
	/**
	 * A method that converts infix expressions into
	 * postfix expressions
	 * <p>
	 * Precondition: infix must have only single digit
	 * operands and only use the + - * / operators
	 * @param infix A string representing an infix equation
	 * @return String A string representing a postfix equation 
	 * equivalent to the infix equation given as input
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException, QueueOverflowException, QueueUnderflowException, StackOverflowException, StackUnderflowException {
		NotationQueue<Character> solution = new NotationQueue<Character>();
		NotationStack<Character> operators = new NotationStack<Character>();
		char[] infixCharArray = infix.toCharArray();
		for(char c : infixCharArray) {
			if(contains(OPERANDS, c)) {
				solution.enqueue(c);
			}
			if(c == '(') {
				operators.push(c);
			}
			if(contains(OPERATORS, c)) {
				while(operators.size() > 0 && (operators.top() != '(') && (precedence(operators.top()) >= precedence(c))) {
						solution.enqueue(operators.pop());
				}
				operators.push(c);
			}
			if(c == ')') {
				while(operators.size() > 0 && (operators.top() != '(')) {
					solution.enqueue(operators.pop());
				}
				if(operators.size() == 0) {
					throw new InvalidNotationFormatException("You cretin! You've messed up the infix equation!");
				} else {
					operators.pop();
				}
			}
		}
		while(operators.size() > 0) {
			if(operators.top() == '(') {
				throw new InvalidNotationFormatException("You dolt! You've messed up the infix equation!");
			} else {
				solution.enqueue(operators.pop());
			}
		}
		if(operators.size() != 0) {
			throw new InvalidNotationFormatException("You dullard! You've messed up the infix equation!");
		}
		return solution.toString();
	}
	/**
	 * A method that converts postfix expressions into
	 * infix expressions
	 * <p>
	 * Precondition: postfix must have only single digit
	 * operands and only use the + - * / operators
	 * @param postfix A string representing a postfix equation
	 * @return String A string representing an infix equation 
	 * equivalent to the postfix equation given as input
	 */
	public static String convertPostfixToInfix(String postfix) {
		NotationStack<String> solution = new NotationStack<String>();
		String front;
		String back;
		char[] postfixCharArray = postfix.toCharArray();
		for(char c : postfixCharArray) {
			if(contains(OPERANDS, c)) {
				solution.push(Character.toString(c));
			}
			if(contains(OPERATORS, c)) {
				if(solution.size() < 2) {
					throw new InvalidNotationFormatException("You dunderhead! You've messed up the postfix equation!");
				}
				back = solution.pop();
				front = solution.pop();
				solution.push("(" + front + Character.toString(c) + back + ")");
			}
		}
		if (solution.size() != 1) {
			throw new InvalidNotationFormatException("You dunce! You've messed up the postfix equation!");
		}
		return solution.pop();
	}
	/**
	 * A method that takes in a postfix expression in String form
	 * and converts it into a double representing the final result
	 * <p>
	 * Precondition: postfix must have only single digit
	 * operands and only use the + - * / operators
	 * @param postfix A string representing a postfix equation
	 * @return double A double representing the final product
	 * given upon evaluating the postfix expression
	 */
	public static double evaluatePostfixExpression(String postfix) {
		NotationStack<String> solution = new NotationStack<String>();
		String front;
		String back;
		char[] postfixCharArray = postfix.toCharArray();
		for(char c : postfixCharArray) {
			if(contains(OPERANDS, c)) {
				solution.push(Character.toString(c));
			}
			if(contains(OPERATORS, c)) {
				if(solution.size() < 2) {
					throw new InvalidNotationFormatException("You dunderhead! You've messed up the postfix equation!");
				}
				back = solution.pop();
				front = solution.pop();
				solution.push(evaluate(front, c, back));
			}
		}
		if (solution.size() != 1) {
			throw new InvalidNotationFormatException("You dunce! You've messed up the postfix equation!");
		}
		return Double.parseDouble(solution.pop());
	}
}
