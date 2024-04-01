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
        // This function will insert a new node into the treap.
        // If the node already exists, throw a DatabaseException.
        // If the node does not exist, insert it and then rotate the tree as necessary.
        // If the node is inserted at the root, update the root variable

        try {
            insertHelper(data);
        } catch (DatabaseException e) {
            throw e;
        }

    }

    private void insertHelper(T data) throws DatabaseException {
        Node<T> curr = root;
        Node<T> prev = null;
        // check if the root is null
        if (root == null) {
            root = new Node<T>(data);
            return;
        }
        while (curr != null) {
            if (curr.data.compareTo(data) == 0) {
                throw DatabaseException.duplicateInsert(data);
            } else if (curr.data.compareTo(data) > 0) {
                prev = curr;
                curr = curr.left;
            } else {
                prev = curr;
                curr = curr.right;
            }
        }

        if (prev.data.compareTo(data) > 0) {
            prev.left = new Node<T>(data);
            curr = prev.left;
        } else {
            prev.right = new Node<T>(data);
            curr = prev.right;
        }

        while (curr.priority < prev.priority) {
            if (prev.left == curr) {
                rotateRight(prev);
            } else {
                rotateLeft(prev);
            }
        }
    }

    private void rotateRight(Node<T> curr) {
        Node<T> temp = curr.left;
        curr.left = temp.right;
        temp.right = curr;
    }

    private void rotateLeft(Node<T> curr) {
        Node<T> temp = curr.right;
        curr.right = temp.left;
        temp.left = curr;
    }

    public Node<T> remove(T data) {
        try {
            return removeHelper(data);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Node<T> removeHelper(T data) {
        // if the root is null, return null
        if (root == null) {
            return null;
        }
        // This function will remove and return the node whose data element matches the
        // passed-in parameter.

        // search for the node to remove
        Node<T> curr = root;
        Node<T> prev = null;
        while (curr != null) {
            if (curr.data.compareTo(data) == 0) {
                break;
            } else if (curr.data.compareTo(data) > 0) {
                prev = curr;
                curr = curr.left;
            } else {
                prev = curr;
                curr = curr.right;
            }
        }
        // if the node is not found, return null
        if (curr.data != data) {
            return null;
        }
        // if the node is found, remove it
        // if the node is a leaf, remove it
        if (curr.left == null && curr.right == null) {
            if (prev.left == curr) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        }
        // if the node has one child, remove it
        if (curr.left == null && curr.right != null) {
            if (prev.left == curr) {
                prev.left = curr.right;
            } else {
                prev.right = curr.right;
            }
        }
        if (curr.left != null && curr.right == null) {
            if (prev.left == curr) {
                prev.left = curr.left;
            } else {
                prev.right = curr.left;
            }
        }
        // if the node has two children, remove it
        if (curr.left != null && curr.right != null) {
            Node<T> temp = curr.right;
            Node<T> tempPrev = curr;
            while (temp.left != null) {
                tempPrev = temp;
                temp = temp.left;
            }
            curr.data = temp.data;
            if (temp.right != null) {
                tempPrev.left = temp.right;
            } else {
                tempPrev.left = null;
            }
        }
        // print the tree using the toString method
        System.out.println(this.toString());
        return curr;

    }

    public Node<T> access(T data) {
        // This function will return the node whose data element matches the passed-in
        // parameter.
        // If the node is not found, return null.
        // If the node is found, rotate it to the root.
        // If the node is found, return it.
        try {
            return accessHelper(data);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Node<T> accessHelper(T data) {
        Node<T> curr = root;
        Node<T> prev = null;
        while (curr != null) {
            if (curr.data.compareTo(data) == 0) {
                break;
            } else if (curr.data.compareTo(data) > 0) {
                prev = curr;
                curr = curr.left;
            } else {
                prev = curr;
                curr = curr.right;
            }
        }
        if (curr.data != data) {
            return null;
        }
        while (curr.priority > prev.priority) {
            if (prev.left == curr) {
                rotateRight(prev);
            } else {
                rotateLeft(prev);
            }
        }
        if (prev == root) {
            root = curr;
        }

        // print the tree using the toString method
        System.out.println(this.toString());
        return curr;
    }

}
