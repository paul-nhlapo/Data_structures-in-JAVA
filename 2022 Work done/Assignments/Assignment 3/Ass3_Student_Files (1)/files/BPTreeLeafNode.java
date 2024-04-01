/**
 * A B+ tree leaf node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeLeafNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] values;
	
	public BPTreeLeafNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1.
		// You can change this if needed.
		this.keys = new Object[m];
		this.values = new Object[m];
	}

	@SuppressWarnings("unchecked")
	public TValue getValue(int index) {
		return (TValue)this.values[index];
	}

	public void setValue(int index, TValue value) {
		this.values[index] = value;
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////
	@SuppressWarnings("unchecked")
	public BPTreeNode<TKey, TValue> Insert(TKey key, TValue value) {
		if(keyTally != m - 1) {
			int index = keyTally - 1;	
			
			for(; index >= 0 && key.compareTo(getKey(index)) < 0; index--) {
				setKey(index + 1, (TKey)keys[index]);
				setValue(index + 1, (TValue)values[index]);
			}
			
			setKey(index + 1, key);
			setValue(index + 1, value);
			keyTally++;
			return this;
		}
		else {
			int pos = keyTally - 1;

			for(; pos >= 0 && key.compareTo((TKey)keys[pos]) < 0; pos--) {
				keys[pos + 1] = keys[pos];
				values[pos + 1] = values[pos];
			}				
			keys[pos + 1] = key;
			values[pos + 1] = value;
			keyTally++;

			BPTreeLeafNode<TKey, TValue> newNode = new 	BPTreeLeafNode<TKey, TValue>(m);
			int length = keyTally - keyTally/2;
			System.arraycopy(keys, keyTally/2, newNode.keys, 0, length);
			System.arraycopy(values, keyTally/2, newNode.values, 0, length);
			newNode.keyTally = length;
			keyTally /= 2;
			
			BPTreeInnerNode<TKey, TValue> parent = new BPTreeInnerNode<TKey, TValue>(m);
			parent.keyTally = 1;
			parent.setKey(0, newNode.getKey(0));
			parent.setChild(0, this);
			parent.setChild(1, newNode);
			this.rightSibling = newNode;
			newNode.leftSibling = this;
			return parent;
		}
	}

}
