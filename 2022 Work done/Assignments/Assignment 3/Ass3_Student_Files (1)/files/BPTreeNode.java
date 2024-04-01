/**
 * A B+ tree generic node
 * Abstract class with common methods and data. Each kind of node implements
 * this class.
 * 
 * @param <TKey>   the data type of the key
 * @param <TValue> the data type of the value
 */
abstract class BPTreeNode<TKey extends Comparable<TKey>, TValue> {

	protected Object[] keys;
	protected int keyTally;
	protected int m;
	protected BPTreeNode<TKey, TValue> parentNode;
	protected BPTreeNode<TKey, TValue> leftSibling;
	protected BPTreeNode<TKey, TValue> rightSibling;
	protected static int level = 0; // do not modify this variable's value as it is used for printing purposes. You
									// can create another variable with a different name if you need to store the
									// level.

	protected BPTreeNode() {
		this.keyTally = 0;
		this.parentNode = null;
		this.leftSibling = null;
		this.rightSibling = null;
	}

	public int getKeyCount() {
		return this.keyTally;
	}

	@SuppressWarnings("unchecked")
	public TKey getKey(int index) {
		return (TKey) this.keys[index];
	}

	public void setKey(int index, TKey key) {
		this.keys[index] = key;
	}

	public BPTreeNode<TKey, TValue> getParent() {
		return this.parentNode;
	}

	public void setParent(BPTreeNode<TKey, TValue> parent) {
		this.parentNode = parent;
	}

	public abstract boolean isLeaf();

	/**
	 * Print all nodes in a subtree rooted with this node
	 */
	@SuppressWarnings("unchecked")
	public void print(BPTreeNode<TKey, TValue> node) {
		level++;
		if (node != null) {
			System.out.print("Level " + level + " ");
			node.printKeys();
			System.out.println();

			// If this node is not a leaf, then
			// print all the subtrees rooted with this node.
			if (!node.isLeaf()) {
				BPTreeInnerNode inner = (BPTreeInnerNode<TKey, TValue>) node;
				for (int j = 0; j < (node.m); j++) {
					this.print((BPTreeNode<TKey, TValue>) inner.references[j]);
				}
			}
		}
		level--;
	}

	/**
	 * Print all the keys in this node
	 */
	protected void printKeys() {
		System.out.print("[");
		for (int i = 0; i < this.getKeyCount(); i++) {
			System.out.print(" " + this.keys[i]);
		}
		System.out.print("]");
	}

	////// You may not change any code above this line. You may add extra variables
	////// if need be //////

	////// Implement the functions below this line //////

	/**
	 * Search a key on the B+ tree and return its associated value using the index
	 * set. If the given key
	 * is not found, null should be returned.
	 */
	public TValue search(TKey key) {
		// Your code goes here
		if (this != null) {
			int pos = keyTally - 1;
			BPTreeNode<TKey, TValue> curr = this;

			while (!curr.isLeaf()) {
				for (; pos >= 0 && key.compareTo((TKey) keys[pos]) < 0; pos--)
					;
				curr = ((BPTreeInnerNode<TKey, TValue>) curr).getChild(++pos);
				pos = keyTally - 1;
			}

			for (int i = 0; i < curr.keyTally; i++) {
				if (key.equals((TKey) curr.getKey(i)))
					return (TValue) ((BPTreeLeafNode<TKey, TValue>) curr).values[i];
			}
		}
		return null;
	}

	/**
	 * Insert a new key and its associated value into the B+ tree. The root node of
	 * the
	 * changed tree should be returned.
	 */
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value) {
		// Your code goes here
		if (search(key) != null)
			return this;
		if (this.keyTally == 0 && this.isLeaf()) {
			this.keys[0] = key;
			keyTally++;
			((BPTreeLeafNode<TKey, TValue>) this).setValue(0, value);
			return this;
		} else if (this.isLeaf())
			return ((BPTreeLeafNode<TKey, TValue>) this).Insert(key, value);
		else
			return ((BPTreeInnerNode<TKey, TValue>) this).Insert(key, value);

	}

	/**
	 * Delete a key and its associated value from the B+ tree. The root node of the
	 * changed tree should be returned.
	 */
	public BPTreeNode<TKey, TValue> delete(TKey key) {
		// Your code goes here
		// this=this.keyTally[0 - key];
		return this;
	}

	/**
	 * Return all associated key values on the B+ tree in ascending key order using
	 * the sequence set. An array
	 * of the key values should be returned.
	 */
	@SuppressWarnings("unchecked")
	public TValue[] values() {
		// Your code goes here
		BPTreeNode<TKey, TValue> curr = this;
		int j = 0;
		Object[] arr = new Object[10000];
		Object[] arr1;
		while (!curr.isLeaf()) {
			curr = ((BPTreeInnerNode<TKey, TValue>) curr).getChild(0);
		}

		while (curr != null) {
			for (int i = 0; i < curr.keyTally; i++) {
				arr[j] = ((BPTreeLeafNode<TKey, TValue>) curr).values[i];
				++j;
			}
			curr = curr.rightSibling;
		}
		arr1 = new Object[j];
		System.arraycopy(arr, 0, arr1, 0, j);
		arr = null;

		for (int i = 0; i < arr1.length; i++) {
			for (j = i + 1; j < arr1.length; j++) {
				Object temp;

				if (((TKey) arr1[i]).compareTo((TKey) arr1[j]) > 0) {
					temp = arr1[i];
					arr1[i] = arr1[j];
					arr1[j] = temp;
				}
			}
		}
		return (TValue[]) arr1;
	}

}