public class Treap<T extends Comparable<T>> {
    public Node<T> root = null;

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }

        return root.toString() + "\n" + toString(root, "");
    }

    private String toString(Node<T> curr, String pre) {
        if (curr == null)
            return "";
        String res = "";

        if (curr.left != null) {
            if (curr.right != null) {
                res += pre + "├(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "|    ");
            } else {
                res += pre + "└(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "     ");
            }
        }

        if (curr.right != null) {
            res += pre + "└(R)─ " + curr.right.toString() + "\n" + toString(curr.right, pre + "   ");
        }
        return res;
    }

    /*
     * Don't change anything above this line
     */

    public void insert(T data) throws DatabaseException {
        root = insert(root, data);
    }

    private Node<T> insert(Node<T> curr, T data) throws DatabaseException {
        if (curr == null) {
            return new Node<T>(data);
        }
        if (curr.data.equals(data)) {// check if the data is already in the tree
            throw DatabaseException.duplicateInsert(data);

        }
        // check if the data is empty
        if (data == null) {// throw exception if data is null
            throw DatabaseException.duplicateInsert(data);
        }

        // int cmp = data.compareTo(curr.data);// compare data with curr.data
        if (data.compareTo(curr.data) < 0) {
            curr.left = insert(curr.left, data);
            if (curr.left.priority >= curr.priority) {
                // Then the node should be rotated about it parent
                curr = rotateRight(curr);

            }
        } else if (data.compareTo(curr.data) > 0) {
            curr.right = insert(curr.right, data);
            if (curr.right.priority >= curr.priority) {
                curr = rotateLeft(curr);
            }
        } else {

            throw DatabaseException.duplicateInsert(data);// throw exception
        }
        return curr;

    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.left;
        node.left = left.right;
        left.right = node;
        return left;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.right;
        node.right = right.left;
        right.left = node;
        return right;
    }

    public Node<T> remove(T data) {
        root = remove(root, data);
        return root;
    }

    private Node<T> remove(Node<T> node, T data) {
        if (node == null) {
            // check if the node is empty
            return null;
        }
        // int cmp = data.compareTo(node.data);// compare data with node.data
        if (data.compareTo(node.data) < 0) {// check if data is less than node.data
            node.left = remove(node.left, data);

        } else if (data.compareTo(node.data) > 0) {// check if data is greater than node.data
            node.right = remove(node.right, data);

        } else if (node.left == null) {
            Node<T> temp = node.right;
            node = null;
            node = temp;
        } else if (node.right == null) {
            Node<T> temp = node.left;
            node = null;
            node = temp;
        } else if (node.left.priority > node.right.priority) {
            node = rotateRight(node);
            node.right = remove(node.right, data);
        } else {
            node = rotateLeft(node);
            node.left = remove(node.left, data);
        }
        return node;
    }

    public Node<T> access(T data) {
        return access(root, data);
    }

    private Node<T> access(Node<T> curr, T data) {
        if (curr == null) {
            return null;
        }
        if (curr.data.equals(data)) {
            curr.priority += 1;
            // Perform rotations
            while (curr.parent != null && curr.priority >= curr.parent.priority) {
                if (curr == curr.parent.left) {// check if the current node is the left child of the parent node
                    rotateRight(curr);// rotate the current node to the right
                } else {// check if the current node is the right child of the parent node
                    rotateLeft(curr);// rotate the current node to the left
                }
            }
            return curr;
        } else if (data.compareTo(curr.data) < 0) {// check if data is less than curr.data
            return access(curr.left, data);
        } else {
            return access(curr.right, data);
        }

    }

}
