public class BTree<T extends Comparable<T>> {

	private int m;
	public Node<T> root;

	/**
	 * 
	 * @param m
	 */
	public BTree(int m) {
		this.m = m;
		this.root = null;

	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> insert(T data) {
		if (root == null) {
			root = new Node<T>(m);
		}
		root = root.insert(data);
		return root.find(data);
	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> find(T data) {
		if (root != null) {
			return root.find(data);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Node<T>[] nodes() {
		if (root == null) {
			return null;
		}
		// This function should return an array containing all the nodes in the tree
		// the array should not contain any nulls and be exactly sized to the number of
		// nodes in the tree

		Node<T>[] nodes = new Node[countNumNodes()];

		int index = 0;

		if (root == null) {
			return null;
		} else {
			return root.nodes(nodes, index);
		}

	}

	public int numKeys() {

		// This function should count the number of keys in the tree.
		// ∗ Note: actual number of keys and not the theoretical number
		// of keys.
		// ∗ If the tree is empty, return 0
		if (root == null) {
			return 0;
		} else {
			return root.numKeys();
		}

	}

	public int countNumNodes() {

		// This function should count the number of nodes in the tree.
		// ∗ Note: actual number of nodes and not the theoretical number
		// of nodes.
		// ∗ If the tree is empty, return 0
		if (root == null) {
			return 0;
		} else {
			return root.countNumNodes();
		}
	}

}