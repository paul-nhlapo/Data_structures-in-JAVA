public class CountList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        // TODO: Implement the function
        return new CountList<T>();
    }

    @Override
    public void access(T data) {
        // TODO: Implement the function
        Node<T> temp = head;
        if (temp != null) {
            if (temp.data == data) {
                temp.accessCount++;
                return;
            } else {
                while (temp.next != null) {
                    if (temp.next.data == data) {
                        Node<T> tobeMoved = temp.next;
                        temp.next.accessCount++;
                        temp.next = temp.next.next;
                        if (temp.next != null)
                            temp.next.prev = temp;
                        tobeMoved.next = head;
                        head.prev = tobeMoved;
                        head = tobeMoved;
                        head.prev = null;
                        break;
                    }
                    temp = temp.next;
                }
            }
        }
    }

}
