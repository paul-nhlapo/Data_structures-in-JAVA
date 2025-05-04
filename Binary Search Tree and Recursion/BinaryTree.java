abstract class BinaryTree<T extends Comparable<T>> {
    // The root of the binary tree, initialized as null.
    protected Leaf<T> root = null;

    // Abstract method to insert data into the tree. Implementation is left to subclasses.
    public abstract void insert(T data);

    // Protected method to insert data into the tree with an option for standard or non-standard tree behavior.
    protected void insert(T data, boolean standardTree) {
        if (root == null) {
            // If the tree is empty, create a new root node.
            root = new Leaf<T>(data);
            return;
        }
        // Recursively insert the data into the tree.
        recInserter(root, standardTree, data);
    }

    // Abstract method for depth-first traversal. Implementation is left to subclasses.
    public abstract void depthFirstTraversal();

    // Abstract method to calculate the height of the tree. Implementation is left to subclasses.
    public abstract int height();

    // Abstract method to check if the tree contains a specific data value. Implementation is left to subclasses.
    public abstract boolean contains(T data);

    // Abstract method to count the number of leaves in the tree. Implementation is left to subclasses.
    public abstract int numLeavesInTree();

    // Abstract method to find a specific node in the tree. Implementation is left to subclasses.
    public abstract Leaf<T> find(T data);

    // Abstract method to find the parent of a specific node in the tree. Implementation is left to subclasses.
    public abstract Leaf<T> findParent(T data);

    // Abstract method to check if the tree is perfectly balanced. Implementation is left to subclasses.
    public abstract boolean perfectlyBalanced();

    // Private helper method to recursively insert data into the tree.
    private void recInserter(Leaf<T> curr, boolean standardTree, T data) {
        // Base case: if the current node is null or the data already exists, return.
        if (curr == null || curr.data.compareTo(data) == 0)
            return;

        // Logic for non-standard tree insertion.
        if (!standardTree) {
            if (curr.data.compareTo(data) > 0) {
                // Insert into the right subtree if the current node's data is greater than the new data.
                if (curr.right == null) {
                    curr.right = new Leaf<T>(data);
                } else {
                    recInserter(curr.right, standardTree, data);
                }
            } else {
                // Insert into the left subtree otherwise.
                if (curr.left == null) {
                    curr.left = new Leaf<T>(data);
                } else {
                    recInserter(curr.left, standardTree, data);
                }
            }
        } else {
            // Logic for standard tree insertion.
            if (curr.data.compareTo(data) < 0) {
                // Insert into the right subtree if the current node's data is less than the new data.
                if (curr.right == null) {
                    curr.right = new Leaf<T>(data);
                } else {
                    recInserter(curr.right, standardTree, data);
                }
            } else {
                // Insert into the left subtree otherwise.
                if (curr.left == null) {
                    curr.left = new Leaf<T>(data);
                } else {
                    recInserter(curr.left, standardTree, data);
                }
            }
        }
    }

    public abstract BinaryTree<T> convertTree();
}
