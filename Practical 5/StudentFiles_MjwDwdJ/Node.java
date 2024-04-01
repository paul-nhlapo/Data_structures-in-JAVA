public class Node<T extends Comparable<T>> {

	private Comparable<T>[] keys;
	private Node<T>[] children;
	private boolean leaf;
	private int keyTally;
	private int m;
	private Node<T> parent;

	/**
	 * 
	 * @param m
	 */
	@SuppressWarnings("unchecked")
	public Node(int m) {
		// TODO - implement Node.Node
		keys = new Comparable[m - 1];
		children = new Node[m];
		leaf = true;
		keyTally = 0;
		this.m = m;
		parent = null;
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

	public Node<T> insert(T data, Node<T> root) {
		if (!leaf) {
			for (int i = 0; i < keyTally; i++) {
				if (keys[i].equals(data)) {
					return root;
				} else if (keys[i].compareTo(data) > 0) {
					return children[i].insert(data, root);
				}
			}
			return children[keyTally].insert(data, root);
		} else {
			if (keyTally < m - 1) {
				int i;
				for (i = keyTally - 1; i >= 0 && keys[i].compareTo(data) > 0; i--) {
					keys[i + 1] = keys[i];
				}
				keys[i + 1] = data;
				keyTally++;
				return root;
			} else {
				return splitNode(data, root, null);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Node<T> splitNode(T data, Node<T> root, Node<T> node) {
		int pos = (m - 1) / 2;

		if (leaf == true) {
			Comparable<T>[] tempkeys = new Comparable[m];
			int i;
			for (i = m - 2; i >= 0 && keys[i].compareTo(data) > 0; i--) {
				tempkeys[i + 1] = keys[i];
				keys[i] = null;
			}
			tempkeys[i + 1] = data;

			for (; i >= 0; i--) {
				tempkeys[i] = keys[i];
				keys[i] = null;
			}

			Node<T> newNode = new Node<T>(m);
			for (i = pos + 1; i < m; i++) {
				newNode.keys[i - pos - 1] = tempkeys[i];
				newNode.keyTally++;
			}

			keyTally = pos - 1;
			for (i = 0; i < pos; i++) {
				keys[i] = tempkeys[i];
			}

			if (parent == null) {
				Node<T> newRoot = new Node<T>(m);
				newRoot.keys[0] = tempkeys[pos];
				newRoot.children[0] = this;
				newRoot.children[1] = newNode;
				newRoot.keyTally = 1;
				newRoot.leaf = false;
				parent = newRoot;
				newNode.parent = newRoot;
				return newRoot;
			} else {
				return parent.splitNode((T) tempkeys[pos], root, newNode);
			}

		} else {

			if (keyTally < m - 1) {
				int i;
				for (i = keyTally - 1; i >= 0 && keys[i].compareTo(data) > 0; i--) {
					keys[i + 1] = keys[i];
				}
				keys[i + 1] = data;
				keyTally++;
				pos = i + 1;

				for (i = keyTally - 1; i > pos; i--) {
					children[i + 1] = children[i];
				}
				children[i + 1] = node;
				return root;
			} else {
				Comparable<T>[] tempkeys = new Comparable[m];
				Node<T>[] tempChildren = new Node[m + 1];
				int i;
				for (i = m - 2; i >= 0 && keys[i].compareTo(data) > 0; i--) {
					tempkeys[i + 1] = keys[i];
					keys[i] = null;

					tempChildren[i + 2] = children[i + 1];
					children[i + 1] = null;
				}
				tempkeys[i + 1] = data;
				tempChildren[i + 2] = node;

				for (int j = i; j >= 0; j--) {
					tempkeys[j] = keys[j];
					keys[j] = null;
				}

				for (int j = i + 1; j >= 0; j--) {
					tempChildren[j] = children[j];
					children[j] = null;
				}
				pos = (m - 1) / 2;

				Node<T> newNode = new Node<T>(m);
				for (i = pos + 1; i < m; i++) {
					newNode.keys[i - pos - 1] = tempkeys[i];
					newNode.keyTally++;
				}

				for (i = pos + 1; i < m + 1; i++) {
					newNode.children[i - pos - 1] = tempChildren[i];
				}

				keyTally = pos - 1;
				for (i = 0; i < pos; i++) {
					keys[i] = tempkeys[i];
				}

				for (i = 0; i <= pos; i++) {
					children[i] = tempChildren[i];
				}
				newNode.leaf = false;

				if (parent == null) {
					Node<T> newRoot = new Node<T>(m);
					newRoot.keys[0] = tempkeys[pos];
					newRoot.children[0] = this;
					newRoot.children[1] = newNode;
					newRoot.keyTally = 1;
					newRoot.leaf = false;
					parent = newRoot;
					newNode.parent = newRoot;
					return newRoot;
				} else {
					return parent.splitNode((T) tempkeys[pos], root, newNode);
				}
			}
		}
	}

	public Node<T> find(T data) {
		Node<T> node = null;
		for (int i = 0; i < keyTally; i++) {
			if (keys[i].equals(data)) {
				return this;
			} else if (leaf == true) {
				return null;
			} else if (keys[i].compareTo(data) > 0) {
				node = children[i].find(data);
			} else if (i == keyTally - 1) {
				node = children[keyTally].find(data);
			}
		}
		return node;
	}

	public int numKeys() {
		if (leaf == true) {
			return keyTally;
		} else {
			int count = 0;
			for (int i = 0; i < keyTally + 1; i++) {
				count += children[i].numKeys();
			}
			return count + keyTally;
		}
	}

	public int countNumNodes() {
		int count = 0;
		if (leaf == true) {
			return 1;
		} else {
			for (int i = 0; i < keyTally + 1; i++) {
				count += children[i].countNumNodes();
			}
		}
		return count + 1;
	}

	public Node<T>[] nodes(Node<T>[] list, int index) {

		if (leaf == true) {
			list[index] = this;
			return list;
		} else {
			list[index] = this;
			for (int i = 0; i < keyTally + 1; i++) {
				list = children[i].nodes(list, index + 1);
			}
		}
		return list;
	}
}
