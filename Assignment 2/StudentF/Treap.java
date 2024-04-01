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
        root = insertHelper(root, data);
    }

    private Node<T> insertHelper(Node<T> curr, T data) throws DatabaseException {
        if (curr == null) {
            return new Node<T>(data);
        }
        if (curr.data.compareTo(data) == 0) {
            throw DatabaseException.duplicateInsert(data);
        }
        if (curr.data.compareTo(data) > 0) {
            curr.left = insertHelper(curr.left, data);
            if (curr.left.priority > curr.priority) {
                curr = rotateRight(curr);
            }
        } else {
            curr.right = insertHelper(curr.right, data);
            if (curr.right.priority > curr.priority) {
                curr = rotateLeft(curr);
            }
        }
        return curr;
    }

    private Node<T> rotateLeft(Node<T> curr) {
        Node<T> temp = curr.right;
        curr.right = temp.left;
        temp.left = curr;
        return temp;
    }

    private Node<T> rotateRight(Node<T> curr) {
        Node<T> temp = curr.left;
        curr.left = temp.right;
        temp.right = curr;
        return temp;
    }

    public Node<T> remove(T data) {
        Node<T> temp = new Node<T>(null);
        root = removeHelper(root, data, temp);
        return temp.left;
    }

    private Node<T> removeHelper(Node<T> curr, T data, Node<T> temp) {
        if (curr == null) {
            return null;
        }
        if (curr.data.compareTo(data) == 0) {
            temp.left = curr;
            if (curr.left == null) {
                return curr.right;
            }
            if (curr.right == null) {
                return curr.left;
            }
            if (curr.left.priority > curr.right.priority) {
                curr = rotateRight(curr);
                curr.right = removeHelper(curr.right, data, temp);
            } else {
                curr = rotateLeft(curr);
                curr.left = removeHelper(curr.left, data, temp);
            }
        } else if (curr.data.compareTo(data) > 0) {
            curr.left = removeHelper(curr.left, data, temp);
        } else {
            curr.right = removeHelper(curr.right, data, temp);
        }
        return curr;
    }

    public Node<T> access(T data) {
        return accessHelper(root, data);
    }

    private Node<T> accessHelper(Node<T> curr, T data) {
        if (curr == null) {
            return null;
        }
        if (curr.data.compareTo(data) == 0) {
            return curr;
        } else if (curr.data.compareTo(data) > 0) {
            return accessHelper(curr.left, data);
        } else {
            return accessHelper(curr.right, data);
        }
    }

}
