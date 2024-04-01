public class StandardBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
    @Override
    public void depthFirstTraversal() {
        // TODO: Implement this function
        depthFirstTraversalHelper(root);
    }

    public void depthFirstTraversalHelper(Leaf<T> curr) {
        if (curr == null)
            return;
        depthFirstTraversalHelper(curr.left);
        System.out.println(curr.toString());
        depthFirstTraversalHelper(curr.right);
    }

    @Override
    public int numLeavesInTree() {
        // TODO: Implement this function
        return numLeavesInTreeHelper(root);
    }

    public int numLeavesInTreeHelper(Leaf<T> curr) {
        if (curr == null)
            return 0;
        else if (curr != null && curr.left == null && curr.right == null)
            return 1;
        else
            return numLeavesInTreeHelper(curr.left) + numLeavesInTreeHelper(curr.right) + 1;
    }

    @Override
    public int height() {
        // TODO: Implement this function
        return heightHelper(root);
    }

    public int heightHelper(Leaf<T> curr) {
        if (curr == null)
            return 0;
        else if (curr != null && curr.left == null && curr.right == null) {
            return 0;
        } else {
            int left = heightHelper(curr.left);
            int right = heightHelper(curr.right);
            if (left > right)
                return left + 1;
            else
                return right + 1;
        }

    }

    @Override
    public Leaf<T> findParent(T data) {
        // TODO: Implement this function
        System.out.println(root.toString());
        return findParentHelper(root, data);
    }

    public Leaf<T> findParentHelper(Leaf<T> curr, T data) {
        if (curr == null)
            return null;
        else if (curr.data.compareTo(data) == 0) {
            return null;// SPecial case this one
        } else {
            if (curr.data.compareTo(data) > 0) {
                if (curr.left != null) {
                    curr = curr.left;
                    if (curr.data.compareTo(data) == 0) {
                        return curr;
                    } else
                        System.out.println(curr.toString());
                    return findParentHelper(curr, data);
                } else
                    return null;
            } else {
                if (curr.right != null) {
                    curr = curr.right;
                    if (curr.data.compareTo(data) == 0) {
                        return curr;
                    } else
                        System.out.println(curr.toString());
                    return findParentHelper(curr, data);
                } else
                    return null;
            }
        }
    }

    @Override
    public void insert(T data) {
        super.insert(data, true);

    }

    @Override
    public Leaf<T> find(T data) {
        // TODO: Implement this function
        System.out.println(root.toString());
        return findHelper(root, data);

    }

    public Leaf<T> findHelper(Leaf<T> curr, T data) {
        if (curr == null)
            return null;
        else if (curr.data.compareTo(data) == 0) {
            return curr;
        } else {
            if (curr.data.compareTo(data) > 0) {
                if (curr.left != null) {
                    curr = curr.left;
                    if (curr.data.compareTo(data) == 0) {
                        System.out.println(curr.toString());
                        return curr;
                    } else
                        System.out.println(curr.toString());
                    return findHelper(curr, data);
                } else
                    return null;
            } else {
                if (curr.right != null) {
                    curr = curr.right;
                    if (curr.data.compareTo(data) == 0) {
                        System.out.println(curr.toString());
                        return curr;
                    } else
                        System.out.println(curr.toString());
                    return findHelper(curr, data);
                } else
                    return null;
            }
        }
    }

    @Override
    public boolean perfectlyBalanced() {
        // TODO: Implement this function
        if (root == null)
            return true;
        else if (root != null && root.left == null && root.right == null)
            return true;
        else {
            int left = numLeavesInTreeHelper(root.left);
            int right = numLeavesInTreeHelper(root.right);
            if (left == right)
                return true;
            else
                return false;
        }
    }

    @Override
    public boolean contains(T data) {
        // TODO: Implement this function
        if (find(data) != null)
            return true;
        else
            return false;
    }

    @Override
    public BinaryTree<T> convertTree() {
        // TODO: Implement this function
        BinaryTree<T> newTree = new MirroredBinaryTree<T>();
        newTree.root = convertTreeHelper(root);
        return newTree;
    }

    public Leaf<T> convertTreeHelper(Leaf<T> curr) {
        if (curr == null)
            return null;
        else {
            Leaf<T> temp = new Leaf<T>(curr.data);
            temp.left = convertTreeHelper(curr.right);
            temp.right = convertTreeHelper(curr.left);
            return temp;
        }
    }

}
