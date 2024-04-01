public class BTree<T extends Comparable<T>> {

	private int m;
	private Node<T> root;

	/**
	 * 
	 * @param m
	 */
	public BTree(int m) {
		this.m = m;
		root = new Node<T>(m);
	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> insert(T data) {
		if (root == null) {
			root = new Node<T>(m);
			root.keys[0] = data;
			root.keyTally++;
			return root;
		} else {
			root = root.insert(data);
			return root;
		}
	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> find(T data) {
		if (root == null) {
			return null;
		} else {
			return root.find(data);
		}
	}

	@SuppressWarnings("unchecked")
	public Node<T>[] nodes() {
		// This function should return an array containing all the nodes in the tree.
		// The array should be ordered by the level of the nodes, with the root node
		// being at index 0, and the leaf nodes being at the end of the array.
		// The array should be the exact size needed to hold all the nodes in the tree.
		// The array should not be filled with null values for any unused indexes.

		Node<T>[] List = new Node[countNumNodes()];
		int index = 0;

		if (root == null) {
			return null;
		} else {
			return root.nodes(List, index);
		}

	}

	public int numKeys() {

		if (root == null) {
			return 0;
		} else {
			return root.numKeys();
		}

	}

	public int countNumNodes() {

		if (root == null) {
			return 0;
		} else {
			return root.countNumNodes();
		}

	}

}