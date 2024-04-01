public class NaturalOrderList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        // TODO: Implement the function
        return new NaturalOrderList<T>();

    }

    @Override
    public void access(T data) {

    }

    @Override
    public void insert(T data) {
        // TODO: Implement the function
        Node<T> newNode = new Node<T>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp != null) {
                if (temp.data.compareTo(data) < 0) {
                    newNode.next = temp;
                    newNode.prev = temp.prev;
                    if (temp.prev != null)
                        temp.prev.next = newNode;
                    temp.prev = newNode;
                    if (temp == head)
                        head = newNode;
                    break;
                }
                temp = temp.next;
            }
            if (temp == null) {
                temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                if (temp.data.compareTo(data) < 0) {
                    newNode.prev = temp;
                    temp.next = newNode;
                } else {
                    newNode.next = temp;
                    newNode.prev = temp.prev;
                    if (temp.prev != null)
                        temp.prev.next = newNode;
                    temp.prev = newNode;
                    if (temp == head)
                        head = newNode;
                }
            }
        }
    }
}
