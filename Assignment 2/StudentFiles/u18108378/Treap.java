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

        if (data == null) {
            throw DatabaseException.duplicateInsert(data);
        } else if (curr == null) {
            return new Node<T>(data);
        }

        int cmp = data.compareTo(curr.data);// compare data with curr.data
        if (cmp < 0) {
            curr.left = insert(curr.left, data);
            if (curr.left.priority >= curr.priority) {
                curr = rotateRight(curr);
            }
        } else if (cmp > 0) {
            curr.right = insert(curr.right, data);
            if (curr.right.priority >= curr.priority) {
                curr = rotateLeft(curr);
            }
        } else {

            throw DatabaseException.duplicateInsert(data);
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
        if (node == null)
            return null;

        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            node.left = remove(node.left, data);
        else if (cmp > 0)
            node.right = remove(node.right, data);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                if (node.left.priority >= node.right.priority)
                    node = rotateRight(node);
                else
                    node = rotateLeft(node);

                node = remove(node, data);
            }
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
        int cmp = data.compareTo(curr.data);
        if (cmp == 0) {
            curr.priority++;
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
