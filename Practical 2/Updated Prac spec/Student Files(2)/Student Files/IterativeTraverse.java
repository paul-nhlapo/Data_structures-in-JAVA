public class IterativeTraverse<T extends Comparable<T>> extends Traverser<T> {
    public IterativeTraverse() {
        // TODO: Implement the function
        list = null;

    };

    public IterativeTraverse(SelfOrderingList<T> list) {
        // TODO: Implement the function
        // this.list = list;
        // perform a deep copy of the list
        this.list = list.getBlankList();

    }

    @Override
    public SelfOrderingList<T> reverseList() {
        // TODO: Implement the function
        SelfOrderingList<T> reverseList = list.getBlankList();
        Node<T> temp = null;
        Node<T> curr = list.head;
        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }
        if (temp != null) {
            reverseList.head = temp.prev;
        }
        return reverseList;

    }

    @Override
    public boolean contains(T data) {
        // TODO: Implement the function
        Node<T> curr = list.head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return true;
            }
            curr = curr.next;
        }
        return false;

    }

    @Override
    public String toString() {
        // TODO: Implement the function
        Node<T> curr = list.head;
        String str = "";
        while (curr != null) {
            if (curr == list.head) {
                str += "->" + curr.toString();
            } else {
                str += "->" + curr.toString();
            }
            curr = curr.next;
        }
        return str;
    }

    @Override
    public Node<T> get(int pos) {
        // TODO: Implement the function
        Node<T> curr = list.head;
        int count = 0;
        if (pos < 0 || pos >= size()) {
            return null;
        }
        while (curr != null) {
            if (count == pos) {
                return curr;
            }
            count++;
            curr = curr.next;
        }
        return null;
    }

    @Override
    public Node<T> find(T data) {
        // TODO: Implement the function
        Node<T> curr = list.head;
        while (curr != null) {
            if (curr.data == data) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    @Override
    public int size() {
        // TODO: Implement the function
        Node<T> curr = list.head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        // TODO: Implement the function
        if (otherList == null) {
            return null;
        }
        SelfOrderingList<T> cloneList = otherList.getBlankList();
        Node<T> curr = otherList.head;
        while (curr != null) {
            cloneList.insert(curr.data);
            curr = curr.next;
        }
        return cloneList;

    }
}
