public class RecursiveTraverse<T extends Comparable<T>> extends Traverser<T> {
    public RecursiveTraverse() {
        // TODO: Implement the function
        list = null;
    }

    public RecursiveTraverse(SelfOrderingList<T> list) {
        // TODO: Implement the function
        // this.list = list;
        // perform a deep copy of the list
        this.list = list.getBlankList();
    }

    @Override
    public SelfOrderingList<T> reverseList() {
        // TODO: Implement the function
        return reverseListHelper(list.head, list.getBlankList());

    }

    private SelfOrderingList<T> reverseListHelper(Node<T> curr, SelfOrderingList<T> reversedList) {
        if (curr == null) {
            return reversedList;
        }
        Node<T> temp = curr.next;
        curr.next = curr.prev;
        curr.prev = temp;
        if (curr.prev == null) {
            reversedList.head = curr;
        }

        return reverseListHelper(curr.prev, reversedList);
    }

    @Override
    public boolean contains(T data) {
        // TODO: Implement the function
        return containsHelper(list.head, data);

    }

    private boolean containsHelper(Node<T> curr, T data) {
        if (curr == null) {
            return false;
        }
        if (curr.data.equals(data)) {
            return true;
        }
        return containsHelper(curr.next, data);
    }

    @Override
    public String toString() {
        // TODO: Implement the function
        return toStringHelper(list.head);
    }

    private String toStringHelper(Node<T> curr) {
        if (curr == null) {
            return "";
        }
        if (curr == list.head) {
            return "->" + toStringHelper(curr.next);
        }
        // return "->" + curr.toString() + toStringHelper(curr.next);
        return "->" + toStringHelper(curr.next);
    }

    @Override
    public Node<T> get(int pos) {
        // TODO: Implement the function
        return getHelper(list.head, pos);

    }

    private Node<T> getHelper(Node<T> curr, int pos) {
        if (curr == null) {
            return null;
        }
        if (pos == 0) {
            return curr;
        }
        return getHelper(curr.next, pos - 1);
    }

    @Override
    public Node<T> find(T data) {
        // TODO: Implement the function
        return findHelper(list.head, data);

    }

    private Node<T> findHelper(Node<T> curr, T data) {
        if (curr == null) {
            return null;
        }
        if (curr.data.equals(data)) {
            return curr;
        }
        return findHelper(curr.next, data);
    }

    @Override
    public int size() {
        // TODO: Implement the function
        return sizeHelper(list.head);

    }

    private int sizeHelper(Node<T> curr) {
        if (curr == null) {
            return 0;
        }
        return 1 + sizeHelper(curr.next);
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        // TODO: Implement the function
        SelfOrderingList<T> cloneList = otherList.getBlankList();
        Node<T> curr = otherList.head;
        cloneHelper(curr, cloneList);
        return cloneList;
    }

    private void cloneHelper(Node<T> curr, SelfOrderingList<T> cloneList) {
        if (curr == null) {
            return;
        }
        cloneList.insert(curr.data);
        cloneHelper(curr.next, cloneList);
    }

}
