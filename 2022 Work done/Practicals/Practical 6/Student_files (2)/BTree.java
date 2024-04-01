class BTree<T extends Comparable<T>> {
	BTreeNode<T> root; // Pointer to root node
	int m; // B-Tree of order 2m

	// Constructor (Initializes B-Tree as empty)
	BTree(int order) {
		root = null;// set the root to null
		m = order;// set the order to the given order
	}

	// function to print this B-Tree
	public void print() {
		if (root != null) {// if the tree is not empty
			root.print();// print the tree
			System.out.println();// print a new lines
		}
	}

	// function to insert the given key in this B-Tree
	public void insert(T key) {
		// If the tree is empty
		if (root == null) {
			root = new BTreeNode<T>(m, true); // Create new root
			root.keys[0] = key; // Insert key
			root.keyTally = 1; // Update number of keys in root
		}
		// If the tree is not empty
		else {
			root = root.insert(key);// insert the key
		}
	}

	// function to search a key in this B-Tree
	public BTreeNode<T> search(T key) {
		if (root != null) // if the tree is not empty
			return root.search(key);// search for the key
		else
			return null;// if the tree is empty, return null
	}

	// function to traverse this B-Tree
	public void traverse()// traverse the tree
	{
		if (root != null) // if the tree is not empty
		{
			root.traverse();// traverse the tree
			System.out.println();// print a new line
		}
	}
}