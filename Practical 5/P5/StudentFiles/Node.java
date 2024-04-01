public class Node<T extends Comparable<T>> {

	boolean leaf;// boolean to check if the node is a leaf
	int keyTally;// number of keys in the node
	Comparable<T> keys[];// array of keys
	BTreeNode<T> children[];// array of children
	int m;// order of the tree
	static int level = 0;// set the level to 0

	/**
	 * 
	 * @param m
	 */
	// Constructor for the node class
	@SuppressWarnings("unchecked")
	public Node(int m) {
		// TODO - implement Node.Node
		keys = new Comparable[m - 1];// create an array of keys
		children = new Node[m];// create an array of children
		leaf = true;// set the leaf to true
		keyTally = 0;// set the keyTally to 0

		// for (int i = 0; i < m; i++)
		// children[i] = null;

	}

	// Initialize a node with a key
	// Node(T key) {
	// keys[0] = key;
	// }

	// Use the toString method to print the node
	public void print() {
		level++;
		if (this != null) {
			System.out.print("Level " + level + " ");
			this.printKeys();
			System.out.println();

			// If this node is not a leaf, then
			// print all the subtrees rooted with this node.
			if (!this.leaf) {
				for (int j = 0; j < 2 * m; j++) {
					if (this.children[j] != null)
						this.children[j].print();
				}
			}
		}
		level--;
	}

	// A utility function to print all the keys in this node
	private void printKeys() {
		System.out.print("[");
		for (int i = 0; i < this.keyTally; i++) {
			System.out.print(" " + this.keys[i]);
		}
		System.out.print("]");
	}

	@Override
	public String toString() {
		String res = "[";
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null)
				res += keys[i];
			else
				res += "null";
			res += ",";
		}
		if (res.length() > 1) {
			res = res.substring(0, res.length() - 1);
		}
		return res + "]";
	}

	// function to insert the given key in the subtree rooted with this node
	// The assumption is, the node must be non-full when this function is called
	public Node<T> insert(T data) {

		// Initialize index as index of rightmost element
		int i = keyTally - 1;

		// If this is a leaf node
		if (leaf == true) {
			// The following loop does two things
			// a) Finds the location of new key to be inserted
			// b) Moves all greater keys to one place ahead
			while (i >= 0 && keys[i].compareTo(data) > 0) {
				keys[i + 1] = keys[i];
				i--;
			}

			// Insert the new key at found location
			keys[i + 1] = data;
			keyTally = keyTally + 1;
		} else // If this node is not leaf
		{
			// Find the child which is going to have the new key
			while (i >= 0 && keys[i].compareTo(data) > 0)
				i--;

			// See if the found child is full
			if (children[i + 1].keyTally == m - 1) {
				// If the child is full, then split it
				splitChild(i + 1, children[i + 1]);

				// After split, the middle key of C[i] goes up and
				// C[i] is splitted into two. See which of the two
				// is going to have the new key
				if (keys[i + 1].compareTo(data) < 0)
					i++;
			}
			children[i + 1].insert(data);
		}
		return this;
	}

	public void splitChild(int i, Node<T> root) {
		Node<T> z = new Node<T>(m);
		Node<T> y = root.children[i];
		z.leaf = y.leaf;
		// The key that will be moved to the parent during splitting can
		// be determined as follows pos = (m-1)/2
		z.keyTally = m / 2;
		for (int j = 0; j < m / 2; j++) {
			z.keys[j] = y.keys[j + m / 2];
		}
		if (!y.leaf) {
			for (int j = 0; j < m / 2; j++) {
				z.children[j] = y.children[j + m / 2];
			}
		}
		y.keyTally = m / 2;
		for (int j = root.keyTally; j >= i + 1; j--) {
			root.children[j + 1] = root.children[j];
		}
		root.children[i + 1] = z;
		for (int j = root.keyTally - 1; j >= i; j--) {
			root.keys[j + 1] = root.keys[j];
		}
		root.keys[i] = y.keys[m / 2];
		root.keyTally = root.keyTally + 1;
	}

	public Node<T> find(T key) {
		int i = 0;
		for (i = 0; i < keyTally; i++) {
			if (keys[i] == (key)) {
				return this;
			}

		}

		if (leaf == true) {
			return null;
		}

		return children[i].find(key);
	}

	public void insertNonFull(T data) {
		int i = keyTally - 1;
		if (leaf == true) {
			while (i >= 0 && keys[i].compareTo(data) > 0) {
				keys[i + 1] = keys[i];
				i--;
			}
		}

		keys[i + 1] = data;

	}

	public int countNumKeys() {

		int count = 0;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				count++;
			}
		}
		return count;
	}

	public int countNumNodes() {

		// Count all nodes in the tree including the root
		int count = 0;
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null) {
				count++;
			}
		}
		return count;

	}

	public void fillArray(Node<T>[] nodes, int i) {
		// TODO - implement Node.fillArray

		if (this != null) {
			nodes[i] = this;
			i++;
			for (int j = 0; j < children.length; j++) {
				if (children[j] != null) {
					children[j].fillArray(nodes, i);
				}
			}
		}

	}

	public void traverse() {
		int i;
		BTreeNode<T> root = this;
		for (i = 0; i < root.keyTally; i++) {

			if (root.leaf == false) {
				root.children[i].traverse();
			}

			System.out.print(" " + root.keys[i]);
		}

		if (root.leaf == false) {
			root.children[i].traverse();
		}
	}

}