public class Node<T extends Comparable<T>> {
	boolean leaf;// boolean to check if the node is a leaf
	int keyTally;// number of keys in the node
	Comparable<T>[] keys;// array of keys
	Node<T>[] children;// array of children
	int m;// order of the tree
	static int level = 0;// level of the node

	/**
	 * 
	 * @param m
	 */
	@SuppressWarnings("unchecked")
	public Node(int m) {
		// TODO - implement Node.Node
		this.m = m;
		this.leaf = true;
		this.keyTally = 0;
		this.keys = new Comparable[m - 1];
		this.children = new Node[m];
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

	public Node<T> insert(T data) {
		// TODO - implement Node.insert
		if (this.keyTally == 0) {// if the node is empty
			this.keys[0] = data;// insert the data
			this.keyTally++;//
			return this;// return the node
		} else {
			if (this.keyTally == this.m - 1) {// if the node is full
				Node<T> newRoot = new Node<T>(this.m);// create a new node
				newRoot.leaf = false;// set the new node to be a non-leaf
				newRoot.children[0] = this;// set the first child of the new node to be the current node
				newRoot.splitChild(0, this);// split the current node
				newRoot = newRoot.insertNonFull(data);// insert the data into the new node
				return newRoot;// return the new node
			} else {// if the node is not full
				this.insertNonFull(data);// insert the data into the node
				return this;// return the node
			}

		}

	}

	@SuppressWarnings("unchecked")
	private Node<T> insertNonFull(T data) {
		int i = this.keyTally - 1;// set i to be the index of the last key in the node
		if (this.leaf) {// if the node is a leaf
			while (i >= 0 && data.compareTo((T) this.keys[i]) < 0) {// while the data is less than the key at index i
				this.keys[i + 1] = this.keys[i];// shift the keys to the right
				i--;// decrement i
			}
			this.keys[i + 1] = data;// insert the data
			this.keyTally++;// increment the keyTally
			return this;// return the node
		} else {// if the node is not a leaf
			while (i >= 0 && data.compareTo((T) this.keys[i]) < 0) {// while the data is less than the key at index i
				i--;// decrement i
			}
			i++;// increment i
			if (this.children[i].keyTally == this.m - 1) {// if the child at index i is full
				this.splitChild(i, this.children[i]);// split the child at index i
				if (data.compareTo((T) this.keys[i]) > 0) {// if the data is greater than the key at index i
					i++;// increment i
				}
			}
			this.children[i] = this.children[i].insertNonFull(data);// insert the data into the child at index i
			return this;// return the node
		}
	}

	private void splitChild(int i, Node<T> node) {
		// The key to be moved to the root is determined as follows (m-1)/2

		Node<T> newNode = new Node<T>(this.m);// create a new node
		newNode.leaf = node.leaf;// set the new node to be a leaf if the current node is a leaf
		newNode.keyTally = (this.m - 1) / 2;// set the keyTally of the new node to be (m-1)/2
		for (int j = 0; j < (this.m - 1) / 2; j++) {// copy the keys from the current node to the new node
			newNode.keys[j] = node.keys[j + (this.m + 1) / 2];
		}
		if (!node.leaf) {// if the current node is not a leaf
			for (int j = 0; j < (this.m + 1) / 2; j++) {// copy the children from the current node to the new node
				newNode.children[j] = node.children[j + (this.m + 1) / 2];
			}
		}
		node.keyTally = (this.m - 1) / 2;// set the keyTally of the current node to be (m-1)/2
		for (int j = this.keyTally; j > i; j--) {// shift the children of the current node to the right
			this.children[j + 1] = this.children[j];
		}
		this.children[i + 1] = newNode;// set the new node to be the child of the current node
		for (int j = this.keyTally - 1; j >= i; j--) {// shift the keys of the current node to the right
			this.keys[j + 1] = this.keys[j];
		}
		this.keys[i] = node.keys[(this.m - 1) / 2];// set the middle key of the current node to be the key of the new
													// node
		this.keyTally++;// increment the keyTally of the current node

	}

	@SuppressWarnings("unchecked")
	public Node<T> find(T data) {
		// TODO - implement Node.find
		int i = 0;// set i to be 0
		while (i < this.keyTally && data.compareTo((T) this.keys[i]) > 0) {// while the data is greater than the key at
																			// index i
			i++;// increment i
		}
		if (i < this.keyTally && data.compareTo((T) this.keys[i]) == 0) {// if the data is equal to the key at index i
			return this;// return the node
		} else if (this.leaf) {// if the node is a leaf
			return null;// return null
		} else {// if the node is not a leaf
			return this.children[i].find(data);// return the child at index i
		}
	}

	public int numKeys() {
		int count = 0;// set count to be 0
		for (int i = 0; i < this.keyTally; i++) {// for each key in the node
			count++;// increment count
		}
		if (!this.leaf) {// if the node is not a leaf
			for (int i = 0; i < this.keyTally + 1; i++) {// for each child in the node
				count += this.children[i].numKeys();// increment count by the number of keys in the child
			}
		}
		return count;// return count
	}

	public int countNumNodes() {

		int count = 1;// set count to be 1
		if (!this.leaf) {// if the node is not a leaf
			for (int i = 0; i < this.keyTally + 1; i++) {// for each child in the node
				count += this.children[i].countNumNodes();// increment count by the number of nodes in the child
			}
		}
		return count;// return count
	}

	public Node<T>[] nodes(Node<T>[] list, int index) {
		list[index] = this;// add the node to the list
		index++;// increment index
		if (!this.leaf) {// if the node is not a leaf
			for (int i = 0; i < this.keyTally + 1; i++) {// for each child in the node
				list = this.children[i].nodes(list, index);// add the child to the list
			}
		}
		return list;
	}

}