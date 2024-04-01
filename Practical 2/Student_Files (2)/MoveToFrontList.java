public class MoveToFrontList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        // TODO: Implement the function
        return new MoveToFrontList<T>();
    }

    @Override
    public void access(T data) {
        // TODO: Implement the function
        Node<T> temp = head;
        if (temp != null) {
            if (temp.data == data) {
                return;
            } else {
                while (temp.next != null) {
                    if (temp.next.data == data) {
                        Node<T> tobeMoved = temp.next;
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
