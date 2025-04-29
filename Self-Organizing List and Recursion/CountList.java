public class CountList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        // TODO: Implement the function
        return new CountList<T>();
    }

    @Override
    public void access(T data) {
        // TODO: Implement the function
        Node<T> curr = head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                curr.accessCount++;
                break;
            }
            curr = curr.next;
        }
        if (curr != null) {
            Node<T> temp = curr;
            while (temp.prev != null && temp.prev.accessCount < temp.accessCount) {
                T tempData = temp.data;
                temp.data = temp.prev.data;
                temp.prev.data = tempData;
                int tempCount = temp.accessCount;
                temp.accessCount = temp.prev.accessCount;
                temp.prev.accessCount = tempCount;
                temp = temp.prev;
            }
        }

    }

}
