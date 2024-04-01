@SuppressWarnings("unchecked")
class BTreeNode<T extends Comparable<T>> {
	boolean leaf;// boolean to check if the node is a leaf
	int keyTally;// number of keys in the node
	Comparable<T> keys[];// array of keys
	BTreeNode<T> references[];// array of references
	int m;// order of the tree
	static int level = 0;// set the level to 0
	// Constructor for BTreeNode class

	public BTreeNode(int order, boolean leaf1) {
		// Copy the given order and leaf property
		m = order;// set the order to the given order
		leaf = leaf1;// set the leaf to the given leaf

		// Allocate memory for maximum number of possible keys
		// and child pointers
		keys = new Comparable[2 * m - 1];// set the keys to the given order
		references = new BTreeNode[2 * m];// set the references to the given order

		// Initialize the number of keys as 0
		keyTally = 0;// set the keyTally to 0
	}

	// Function to print all nodes in a subtree rooted with this node
	public void print()// print the tree
	{
		level++;// increment the level
		if (this != null) {// if the node is not null
			System.out.print("Level " + level + " ");// print the level
			this.printKeys();// print the keys
			System.out.println();// print a new line

			// If this node is not a leaf, then
			// print all the subtrees rooted with this node.
			if (!this.leaf)// if the node is not a leaf
			{
				for (int j = 0; j < 2 * m; j++)// for each reference
				{
					if (this.references[j] != null)// if the reference is not null
						this.references[j].print();// print the reference
				}
			}
		}
		level--;// decrement the level
	}

	// A utility function to print all the keys in this node
	private void printKeys()// print the keys
	{
		System.out.print("[");// print a bracket
		for (int i = 0; i < this.keyTally; i++)// for each key
		{
			System.out.print(" " + this.keys[i]);// print the key
		}
		System.out.print("]");// print a bracket
	}

	// A utility function to give a string representation of this node
	public String toString() {// return a string representation of the node
		String out = "[";// set the output to a bracket
		for (int i = 1; i <= (this.keyTally - 1); i++)// for each key
			out += keys[i - 1] + ",";// add the key to the output
		out += keys[keyTally - 1] + "] ";// add the last key to the output
		return out;// return the output
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	// Function to insert the given key in tree rooted with this node
	public BTreeNode<T> insert(T key) {
		BTreeNode<T> root = this;// set the root to this node
		if (root.keyTally == 2 * m - 1)// if the root is full
		{
			BTreeNode<T> s = new BTreeNode<>(m, false);// create a new node

			s.references[0] = this;// set the first reference to this node
			s.splitOnChild(0, this);// split the node
			int i = 0;// set i to 0
			if (s.keys[0].compareTo(key) < 0)// if the first key is less than the given key
			{
				i++;// increment i
			}

			s.references[i].insertOnSpscr(key);// insert the key
			root = s;// set the root to the new node
			return root;// return the root

		} else // If root is not full, call insertNonFull for root
		{
			root.insertOnSpscr(key);// insert the key
			return root;// return the root
		}
	}

	// Function to search a key in a subtree rooted with this node
	public BTreeNode<T> search(T key) {
		int i = 0;// set i to 0
		for (i = 0; i < keyTally; i++)// for each key
		{
			if (keys[i] == (key))// if the key is equal to the given key
			{
				return this;// return this node
			}

		}

		if (leaf == true)// if the node is a leaf
		{
			return null;// return null
		}

		return references[i].search(key);// return the reference
	}

	// Function to traverse all nodes in a subtree rooted with this node
	public void traverse() {
		int i;// declare i
		BTreeNode<T> root = this;// set the root to this node
		for (i = 0; i < root.keyTally; i++)// for each key
		{

			if (root.leaf == false)// if the node is not a leaf
			{
				root.references[i].traverse();// traverse the reference
			}

			System.out.print(" " + root.keys[i]);// print the key
		}

		if (root.leaf == false)// if the node is not a leaf
		{
			root.references[i].traverse();// traverse the reference
		}
	}

	// Helper
	private void splitOnChild(int i, BTreeNode<T> node) {
		BTreeNode<T> nodeB = new BTreeNode<T>(node.m, node.leaf);// create a new node
		nodeB.keyTally = m - 1;// set the keyTally to m - 1

		for (int j = 0; j < m - 1; j++)// for each key
		{
			nodeB.keys[j] = node.keys[j + m];// set the key to the key in the node
		}

		if (node.leaf == false)// if the node is not a leaf
		{
			for (int j = 0; j < m; j++)// for each reference
			{
				nodeB.references[j] = node.references[j + m];// set the reference to the reference in the node
			}

		}

		node.keyTally = m - 1;// set the keyTally to m - 1

		for (int j = keyTally; j >= i + 1; j--)// for each key
		{
			references[j + 1] = references[j];// set the reference to the reference in the node
		}

		references[i + 1] = nodeB;// set the reference to the new node

		for (int j = keyTally - 1; j >= i; j--)// for each key
		{
			keys[j + 1] = keys[j];// set the key to the key in the node
		}

		keys[i] = node.keys[m - 1];// set the key to the key in the node

		keyTally = keyTally + 1;// increment the keyTally
	}

	private void insertOnSpscr(T k)// insert the key
	{

		int i = keyTally - 1;// set i to the keyTally - 1

		if (leaf == true)// if the node is a leaf
		{
			while (i >= 0 && keys[i].compareTo(k) > 0)// while i is greater than or equal to 0 and the key is greater
														// than the given key
			{
				keys[i + 1] = keys[i];// set the key to the key in the node
				i--;// decrement i
			}

			keys[i + 1] = k;// set the key to the given key
			keyTally = keyTally + 1;// increment the keyTally
		} else// if the node is not a leaf
		{
			while (i >= 0 && keys[i].compareTo(k) > 0)// while i is greater than or equal to 0 and the key is greater
														// than the given key
			{
				i--;// decrement i
			}

			if (references[i + 1].keyTally == 2 * m - 1)// if the reference is full
			{
				splitOnChild(i + 1, references[i + 1]);// split the reference

				if (keys[i + 1].compareTo(k) < 0)// if the key is less than the given key
				{
					i++;// increment i
				}

			}
			references[i + 1].insertOnSpscr(k);// insert the key
		}
	}

}
