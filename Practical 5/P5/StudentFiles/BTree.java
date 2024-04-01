public class BTree<T extends Comparable<T>> {

	private int m;
	private Node<T> root;

	/**
	 * 
	 * @param m
	 */
	public BTree(int m) {
		this.m = m;
		this.root = null;

	}

	// function to print this B-Tree
	public void print() {
		if (root != null) {
			root.print();
			System.out.println();
		}
	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> insert(T data) {
		if (root == null) {
			root = new Node<T>(m);
			root.keys[0] = data;
			root.leaf = true;
			root.keyTally = 1;
			return root;
		} else {
			if (root.keyTally == m - 1) {
				Node<T> newRoot = new Node<T>(m);
				newRoot.leaf = false;
				newRoot.children[0] = root;
				newRoot.splitChild(0, root);
				int i = 0;
				if (newRoot.keys[0].compareTo(data) < 0)
					i++;
				newRoot.children[i].insertNonFull(data);
				root = newRoot;
			} else
				root.insertNonFull(data);
		}
		return root;

	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> find(T data) {
		// return find(root, data);
		if (root != null)
			return root.find(data);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	protected Node<T> find(Node<T> node, T data) {
		if (node != null) {
			int i = 1;
			for (; i <= node.keyTally && data.compareTo((T) node.keys[i - 1]) > 0; i++)
				;
			if (i > node.keyTally || node.keys[i - 1].compareTo(data) > 0) {

				return find(node.children[i - 1], data);
			}
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
		int i = 0;
		root.fillArray(nodes, i);
		// print the array
		for (int j = 0; j < nodes.length; j++) {
			System.out.println(nodes[j]);
		}
		return nodes;

	}

	public int numKeys() {

		// This function should count the number of keys in the tree.
		// ∗ Note: actual number of keys and not the theoretical number
		// of keys.
		// ∗ If the tree is empty, return 0

		if (root == null) {

			return 0;

		}

		else {

			return root.countNumKeys();

		}

	}

	public int countNumNodes() {

		// This function should count the number of nodes in the tree.
		// ∗ Note: actual number of nodes and not the theoretical number
		// of nodes.
		// ∗ If the tree is empty, return 0

		if (root == null) {

			return 0;

		}

		else {

			return root.countNumNodes();

		}

	}

	// Print the tree
	// function to traverse this B-Tree
	public void traverse() {
		if (root != null) {
			root.traverse();
			System.out.println();
		}
	}

}