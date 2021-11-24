/**
 * This class is a simple data storing class which holds a reference to a data value
 * as well as a reference to two child nodes, a left and right child. This class will
 * mostly be used for the implementation of a Binary Search Tree, holding letter values
 * 
 * @author Evan Song
 *
 * @param <T> The type of data to be stored by the Tree Node, a String
 * for the Morse Code Tree implementation
 */
public class TreeNode<T> {
	private T data;
	private TreeNode<T> left, right;
	/**
	 * Constructor; initializes a node with a reference to a specified data value
	 * @param dataNode The value that the node is storing
	 */
	public TreeNode(T dataNode) {
		data = dataNode;
		left = null;
		right = null;
	}
	
	/**
	 * Secondary constructor used for deep copies; creates a node with the same data
	 * and children, but different references, allowing for the adjustment of values in
	 * the original node without affecting the new node
	 * @param node The node to create a copy of
	 */
	public TreeNode(TreeNode<T> node) {
		this.data = node.getData();
		if(node.getLeft() != null) {
			this.left = new TreeNode(node.getLeft());
		} else {
			this.left = null;
		}
		if(node.getRight() != null) {
			this.right = new TreeNode(node.getRight());
		} else {
			this.right = null;
		}
	}
	
	/**
	 * Simple getter method; returns a reference to the data value stored by the node
	 * @return T the data being stored
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Simple getter method; returns a reference to the left child node of the current node, or null
	 * if the node has no left child
	 * @return TreeNode<T> The left child of the current node
	 */
	public TreeNode<T> getLeft() {
		return left;
	}
	
	/**
	 * Simple getter method; returns a reference to the right child node of the current node, or null
	 * if the node has no right child
	 * @return TreeNode<T> The right child of the current node
	 */
	public TreeNode<T> getRight() {
		return right;
	}
	
	/**
	 * Simple setter method; changes the value of the data stored in the node to a new value
	 * @param dataToAdd The new value to be updated
	 */
	public void setData(T dataToAdd) {
		this.data = dataToAdd;
	}
	
	/**
	 * Simple setter method; changes the reference of the left child node stored
	 * in the current node to a new reference
	 * @param leftToAdd The new reference to be updated
	 */
	public void setLeft(TreeNode<T> leftToAdd) {
		this.left = leftToAdd;
	}
	
	/**
	 * Simple setter method; changes the reference of the right child node stored
	 * in the current node to a new reference
	 * @param leftToAdd The new reference to be updated
	 */
	public void setRight(TreeNode<T> rightToAdd) {
		this.right = rightToAdd;
	}
}
