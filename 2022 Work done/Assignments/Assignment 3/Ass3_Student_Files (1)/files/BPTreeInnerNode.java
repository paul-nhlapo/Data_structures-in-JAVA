/**
 * A B+ tree internal node
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
class BPTreeInnerNode<TKey extends Comparable<TKey>, TValue> extends BPTreeNode<TKey, TValue> {
	
	protected Object[] references; 
	
	public BPTreeInnerNode(int order) {
		this.m = order;
		// The strategy used here first inserts and then checks for overflow. 
		// Thus an extra space is required i.e. m instead of m-1/m+1 instead of m.
		// You can change this if needed. 
		this.keys = new Object[m];
		this.references = new Object[m + 1];
	}
	
	@SuppressWarnings("unchecked")
	public BPTreeNode<TKey, TValue> getChild(int index) {
		return (BPTreeNode<TKey, TValue>)this.references[index];
	}

	public void setChild(int index, BPTreeNode<TKey, TValue> child) {
		this.references[index] = child;
		if (child != null)
			child.setParent(this);
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}

	////// You should not change any code above this line //////

	////// Implement functions below this line //////
	@SuppressWarnings("unchecked")
	public BPTreeNode<TKey, TValue> Insert(TKey key, TValue value) {
		int pos = keyTally - 1;
		BPTreeNode<TKey, TValue> curr = this;
		
		while(!curr.isLeaf()) {
			for(; pos >= 0 && key.compareTo((TKey)keys[pos]) < 0; pos--);
			curr = ((BPTreeInnerNode<TKey, TValue>)curr).getChild(++pos);
			pos = keyTally - 1;
		}
		
		if(curr.keyTally == m - 1 && curr.isLeaf()) {
			
			BPTreeLeafNode<TKey, TValue> current = ((BPTreeLeafNode<TKey, TValue>)curr);
			BPTreeInnerNode<TKey, TValue> parent = (BPTreeInnerNode<TKey, TValue>)current.getParent();

			if(parent.keyTally != m) {//split leaf while parent is not full
				int i = 0;

				//add even though leaf is full but split later
				for(i = current.keyTally -1; i >=0 && key.compareTo((TKey)current.keys[i]) < 0; i--) {
					current.keys[i + 1] = current.keys[i];
					current.values[i + 1] = current.values[i];
				}
				current.keys[i + 1] = key;
				current.values[i + 1] = value;
				++current.keyTally;
				
				BPTreeLeafNode<TKey, TValue>cu = (BPTreeLeafNode<TKey, TValue>)parent.getChild(parent.keyTally);
				//Shift references and keys in parent
				for(i = parent.keyTally; i > 0 && key.compareTo((TKey)parent.getKey(i - 1)) < 0; i--) {
					((BPTreeNode<TKey, TValue>)parent).setKey(i, parent.getKey(i - 1));
					parent.setChild(i + 1, parent.getChild(i));
				}
				parent.keyTally++;
				i++;

				//create the newNode by splitting child
				BPTreeLeafNode<TKey, TValue> newNode = new BPTreeLeafNode<TKey, TValue>(m);
				int length = current.keyTally - current.keyTally/2;
				System.arraycopy(current.keys, current.keyTally/2, newNode.keys, 0, length);
				System.arraycopy(current.values, current.keyTally/2, newNode.values, 0, length);
				newNode.keyTally = length;
				current.keyTally /= 2;

				//insert the newNode
				if(parent.keyTally != m) {
					parent.setChild(i, newNode);
					parent.setKey(i - 1, newNode.getKey(0));
					newNode.leftSibling = current;
					newNode.rightSibling = current.rightSibling;
					current.rightSibling = newNode;
				}
				else {
					System.out.println(i + " " + parent.references.length);
					parent.setChild(i + 1, newNode);
					parent.setKey(i - 1, newNode.getKey(0));
					parent.setChild(4, cu);
					System.out.println(parent.getChild(4).keys[2]);
				}

			}
			return this;
		}
		else curr.insert(key, value);

		return this;
	}

}