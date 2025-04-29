public class TransposeList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        // TODO: Implement the function
        return new TransposeList<T>();
    }

    @Override
    public void access(T data) {
        // TODO: Implement the function

        Node<T> curr = head;
        while (curr != null) {
            if (curr.data.compareTo(data) == 0) { // use compareTo() to compare objects
                if (curr.prev != null) {
                    Node<T> prev = curr.prev;
                    Node<T> next = curr.next;
                    prev.next = next;
                    if (next != null) {
                        next.prev = prev;
                    }
                    curr.prev = prev.prev;
                    curr.next = prev;
                    if (prev.prev != null) {
                        prev.prev.next = curr;
                    } else {
                        head = curr;
                    }
                    prev.prev = curr;
                }
                return;
            }
            curr = curr.next;
        }
    }

}
