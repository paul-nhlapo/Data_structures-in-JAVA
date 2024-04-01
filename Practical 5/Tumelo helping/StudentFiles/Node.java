public class Node<T extends Comparable<T>> {

	private Comparable<T>[] keys;
	private Node<T>[] children;
	private int keyTally;
	private boolean leaf;
	private int m;

	/**
	 * 
	 * @param m
	 */
	@SuppressWarnings("unchecked")
	public Node(int m) {
		this.m = m;
		keys = new Comparable[m - 1];
		children = new Node[m];
		keyTally = 0;
		leaf = true;

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

	@SuppressWarnings("unchecked")
	public Node<T> insert(T data, Node<T> root) {
		Node<T> node = this;
		if (!node.leaf) {
			for (int i = 0; i < keyTally; i++) {
				if (node.keys[i].equals(data)) {
					return root;
				} else if (keys[i].compareTo(data) > 0) {
					node = children[i].insert(data, root);
				} else if (i + 1 == keyTally) {
					node = children[i + 1].insert(data, root);
				}
			}
		} else {
			if (keyTally < m - 1) {
				int i;
				for (i = keyTally - 1; i >= 0 && keys[i].compareTo(data) > 0; i--) {
					keys[i + 1] = keys[i];
				}
				keys[i + 1] = data;
				keyTally++;
				return this;
			} else { // node is full and need to split when spliting it need to use the formular
						// (m-1)/2 and take the floor
						// and the middle element will be the parent make sure to rebalance the BTree
						// after the split
				Node<T> newNode = new Node<T>(m);
				Node<T> parent = new Node<T>(m);
				int middle = (m - 1) / 2;
				for (int i = 0; i < middle; i++) {
					newNode.keys[i] = keys[i];
					newNode.keyTally++;
				}
				for (int i = middle + 1; i < m - 1; i++) {
					keys[i - middle - 1] = keys[i];
					keyTally--;
				}
				newNode.children[0] = children[0];
				newNode.children[1] = children[1];
				newNode.leaf = false;
				children[0] = null;
				children[1] = null;
				if (data.compareTo((T) keys[middle]) > 0) {
					insertNonFull(data);
				} else {
					newNode.insertNonFull(data);
				}
				parent.keys[0] = keys[middle];
				parent.keyTally++;
				parent.children[0] = newNode;
				parent.children[1] = this;
				parent.leaf = false;
				return parent;

			}
		}
		return root;

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
		keyTally++;

	}
}