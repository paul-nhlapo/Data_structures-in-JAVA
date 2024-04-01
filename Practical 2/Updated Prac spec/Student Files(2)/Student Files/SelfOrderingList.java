abstract class SelfOrderingList<T extends Comparable<T>> {
    public Node<T> head = null;

    public void insert(T data) {
        // TODO: Implement the function

        Node<T> newNode = new Node<T>(null);
        newNode.data = data;
        newNode.next = null;
        newNode.prev = null;
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = new Node<T>(null);
            temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
    }

    public void remove(T data) {
        // TODO: Implement the function
        Node<T> temp = head;
        if (temp != null) {
            if (temp.data == data) {
                Node<T> tobeDeleted = head;
                head = head.next;
                tobeDeleted = null;
                if (head != null)
                    head.prev = null;
            } else {
                while (temp.next != null) {
                    if (temp.next.data == data) {
                        Node<T> tobeDeleted = temp.next;
                        temp.next = temp.next.next;
                        if (temp.next != null)
                            temp.next.prev = temp;
                        tobeDeleted = null;
                        break;
                    }
                    temp = temp.next;
                }
            }
        }

    }

    public abstract void access(T data);

    public abstract SelfOrderingList<T> getBlankList();
}
