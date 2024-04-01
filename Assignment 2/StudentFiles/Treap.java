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

    // private Node<T> insert(Node<T> curr, T data) throws DatabaseException {
    // if (curr == null) {// if curr is null, create a new node
    // return new Node<T>(data);// return the new node
    // }

    // int cmp = data.compareTo(curr.data);// compare data with curr.data
    // if (cmp < 0) {
    // curr.left = insert(curr.left, data);
    // if (curr.left.priority > curr.priority) {
    // curr = rotateRight(curr);
    // }
    // } else if (cmp > 0) {
    // curr.right = insert(curr.right, data);
    // if (curr.right.priority > curr.priority) {
    // curr = rotateLeft(curr);
    // }
    // } else {

    // throw DatabaseException.duplicateInsert(data);
    // }
    // return curr;

    // }

    private Node<T> insert(Node<T> root, T data) throws DatabaseException {
        // If root is null, create a new node and return it
        if (root == null) {
            return new Node<T>(data);
        }

        // If data is smaller than root
        if (data.compareTo(root.data) < 0) {
            // Insert in left subtree
            root.left = insert(root.left, data);

            // Fix Heap property if it is violated
            if (root.left.priority > root.priority) {
                root = rotateRight(root);
            }
        } else if (data.compareTo(root.data) > 0) { // If data is greater
            // Insert in right subtree
            root.right = insert(root.right, data);

            // Fix Heap property if it is violated
            if (root.right.priority > root.priority) {
                root = rotateLeft(root);
            }
        } else {
            throw DatabaseException.duplicateInsert(data);
        }
        return root;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.left;// left node
        node.left = left.right;// left node's right child
        left.right = node;// left node's right child is node
        return left;// return left node
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.right;// right node
        node.right = right.left;// right node's left child
        right.left = node;// right node's left child is node
        return right;// return right node
    }

    // private Node<T> remove(Node<T> node, T data) {
    // if (node == null)
    // return null;

    // int cmp = data.compareTo(node.data);
    // if (cmp < 0)
    // node.left = remove(node.left, data);
    // else if (cmp > 0)
    // node.right = remove(node.right, data);
    // else {
    // if (node.left == null)
    // return node.right;
    // else if (node.right == null)
    // return node.left;
    // else {
    // if (node.left.priority > node.right.priority)
    // node = rotateRight(node);
    // else
    // node = rotateLeft(node);

    // node.parent = null;
    // if (node.left != null) {
    // node.left.parent = node;
    // }
    // if (node.right != null) {
    // node.right.parent = node;
    // }

    // node = remove(node, data);
    // }
    // }
    // return node;
    // }

    public Node<T> remove(T data) {
        root = remove(root, data);
        return root;
    }

    private Node<T> remove(Node<T> root, T data) {
        if (root == null)
            return root;

        // if (key < root.key)
        if (data.compareTo(root.data) < 0) {
            root.left = remove(root.left, data);

        }
        // else if (key > root.key)
        else if (data.compareTo(root.data) > 0) {
            root.right = remove(root.right, data);

        }

        // IF KEY IS AT ROOT

        // If left is NULL
        else if (root.left == null) {
            Node<T> temp = root.right;
            root = temp; // Make right child as root
        }
        // If Right is NULL
        else if (root.right == null) {
            Node<T> temp = root.left;
            root = temp; // Make left child as root
        }
        // If data is at root and both left and right are not NULL
        else if (root.left.priority < root.right.priority) {
            root = rotateLeft(root);
            root.left = remove(root.left, data);
        } else {
            root = rotateRight(root);
            root.right = remove(root.right, data);
        }

        return root;

    }

    public Node<T> access(T data) {
        return access(root, data);
    }

    private Node<T> access(Node<T> curr, T data) {
        if (curr == null) {
            return null;
        }
        int cmp = data.compareTo(curr.data);
        if (cmp == 0) {
            curr.priority++;// increase priority
            while (curr.parent != null && curr.priority >= curr.parent.priority) {
                if (curr == curr.parent.left) {
                    rotateRight(curr);
                } else {
                    rotateLeft(curr);
                }
            }
            return curr;
        } else if (cmp < 0) {
            return access(curr.left, data);
        } else {
            return access(curr.right, data);
        }
    }

}
