import java.util.ArrayList;

/**
 * 
 * This class represents a Binary Search Tree holding various strings, each 
 * representing a letter of the English alphabet. The positions of these strings
 * are determined by Morse code, allowing the user to easily add and retrieve 
 * letters, assisting in the conversion of Morse code to English
 * 
 * @author Evan Song
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface {
	private TreeNode<String> root = new TreeNode<String>("");
	
	/**
	 * A constructor; simply builds the tree using the buildTree method
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	/**
	 * Constructs a tree, with letters inserted into the tree based on a Morse code
	 * map. This method uses individual calls of the insert method for each letter, 
	 * which is slightly inefficient. In the future, a combination of arrays and for 
	 * loops would be more fficient. However, the code works, which is what matters 
	 * at heart
	 */
	@Override
	public void buildTree() {
		setRoot(new TreeNode<String>(""));
		insert(".", "e");
		insert("-", "t");
		
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		
		insert(".-..", "l");
		
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
		
	}
	
	/**
	 * Simple getter method; returns a reference to the tree's root node, 
	 * which should be a node containing an empty string as data
	 * @return TreeNode<String> The tree's root node
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}
	
	/**
	 * Simple setter method; adjusts the tree's reference to the root node
	 * @param newNode a TreeNode that will become the new root of the tree
	 */
	@Override
	public void setRoot(TreeNode newNode) {
		this.root = newNode;
	}
	
	/**
	 * The backbone of the buildTree method. This method inserts a new node
	 * into the tree using the recursive addNode method, starting at the root of the tree
	 * @param code The Morse code equivalent of the letter to be added, for example,
	 * 'l' would have a code of '.-..'
	 * @param result The letter to be added into the tree. Standard English alphabet only, with
	 * no numbers or special characters
	 * @return LinkedConverterTreeInterface A reference to the tree, which remains largely unused
	 */
	@Override
	public LinkedConverterTreeInterface insert(Object code, Object result) {
		addNode(root, code, result);
		return this;
	}
	
	/**
	 * A recursive method which adds a Node to the tree. It does this by taking
	 * the first character of the code and then recursively calling itself, but
	 * with a new root of the child of the current root based on that first 
	 * character, and a new code of the original code without the first character.
	 * Once there is only one character left in the code, it adds a new node to the
	 * current node, based on that character, and then stops calling itself.
	 * @param recursiveRoot The node that the function is currently referencing,
	 * which will have a new child added to it if code only has one character left
	 * @param code The Morse code equivalent of the letter to be added, for example,
	 * 'l' would have a code of '.-..'
	 * @param letter The letter to be added into the tree. Standard English alphabet only, with
	 * no numbers or special characters
	 */
	@Override
	public void addNode(TreeNode recursiveRoot, Object code, Object letter) {
		String sCode = (String) code;
		String sLetter = (String) letter;
		if(sCode.length() == 1) {
			if(sCode.substring(0, 1).equals(".")) {
				recursiveRoot.setLeft(new TreeNode(sLetter));
				return;
			} else {
				recursiveRoot.setRight(new TreeNode(sLetter));
				return;
			}
		} else {
			if(sCode.substring(0, 1).equals(".")) {
				addNode(recursiveRoot.getLeft(), sCode.substring(1), sLetter);
			} else {
				addNode(recursiveRoot.getRight(), sCode.substring(1), sLetter);
			}
		}
	}
	
	/**
	 * Similar to the insert method, this method retrieves the data stored in a 
	 * specific node from the tree, without altering or removing the node itself. 
	 * It calls the fetchNode recursive method starting at the root of the tree
	 * @param code The Morse code equivalent of the letter to be fetched, for example,
	 * 'l' would have a code of '.-..'
	 * @return Object The data stored in the node, always a String (specifically 
	 * a letter) in this case.
	 */
	@Override
	public Object fetch(String code) {
		return fetchNode(root, code);
	}
	
	/**
	 * A recursive method that works similarly to addNode, which follows the same steps as 
	 * addNode except that once it reaches the last character in code, it retrieves the 
	 * data held by the relevant child, instead of adding a new node.
	 * @param recursiveRoot The node that the function is currently referencing,
	 * which will have a its child returned if code only has one character left
	 * @param code The Morse code equivalent of the letter to be fetched, for example,
	 * 'l' would have a code of '.-..'
	 * @return Object The data stored in the node, always a String (specifically 
	 * a letter) in this case.
	 */
	@Override
	public Object fetchNode(TreeNode recursiveRoot, Object code) {
		String sCode = (String) code;
		if(sCode.length() == 1) {
			if(sCode.substring(0, 1).equals(".")) {
				return recursiveRoot.getLeft().getData();
			} else {
				return recursiveRoot.getRight().getData();
			}
		} else {
			if(sCode.substring(0, 1).equals(".")) {
				return fetchNode(recursiveRoot.getLeft(), sCode.substring(1));
			} else {
				return fetchNode(recursiveRoot.getRight(), sCode.substring(1));
			}
		}
	}
	
	/**
	 * An unsupported method which throws the UnsupportedOperationException
	 * @param data The data to be deleted, though this method is unsupported 
	 * and therefore this parameter is useless
	 * @return LinkedConverterTreeInterface A reference to the tree, which is useless, 
	 * as the method is unsupported
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface delete(Object data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Sorry, I don't know how to do that.");
	}

	/**
	 * An unsupported method which throws the UnsupportedOperationException
	 * @return LinkedConverterTreeInterface A reference to the tree, which is useless, 
	 * as the method is unsupported
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Sorry, I don't know how to do that.");
	}

	/**
	 * Similar to the insert and fetch methods, this method returns an ArrayList 
	 * holding the nodes contained by the tree in LNR order, using the recursive 
	 * method LNRoutputTraversal starting at the root of the tree
	 * @return ArrayList An ArrayList of strings holding all of the tree's data in LNR order
	 */
	@Override
	public ArrayList toArrayList() {
		ArrayList<String> result = new ArrayList<>();
		LNRoutputTraversal(root, result);
		return result;
	}
	
	/**
	 * A recursive method that checks whether the current node has a left child, 
	 * and recursively calls itself using that node as a root if so. It then adds 
	 * its own data to the list, and then repeats the first step using the right node.
	 * @param recursiveRoot The node being checked, which will eventually add its data to the list
	 * @param list The ArrayList that is being added to, which will eventually be 
	 * returned by the toArrayList method
	 */
	@Override
	public void LNRoutputTraversal(TreeNode recursiveRoot, ArrayList list) {
		if(recursiveRoot.getLeft() != null) {
			LNRoutputTraversal(recursiveRoot.getLeft(), list);
		}
		list.add(recursiveRoot.getData());
		if(recursiveRoot.getRight() != null) {
			LNRoutputTraversal(recursiveRoot.getRight(), list);
		}
	}
}