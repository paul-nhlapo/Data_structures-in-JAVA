public class List<T> {
    public int length;
    public Node<T> head;

    public List() {
        length = 0;
        head = null;
    }

    public String toString() {
        String res = "";
        Node<T> ptr = head;
        while (ptr != null) {
            res += ptr.data + ",";
            ptr = ptr.next;
        }
        if (res.length() > 0) {
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    public void append(T val) {
        Node<T> newNode = new Node<T>(val);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> ptr = head;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = newNode;
        }
        length++;
    }

    public boolean remove(T val) {
        Node<T> ptr = head;
        if (ptr != null) {
            if (ptr.data.equals(val)) {
                head = ptr.next;
                length--;
                return true;
            } else {
                while (ptr.next != null) {
                    if (ptr.next.data.equals(val)) {
                        ptr.next = ptr.next.next;
                        length--;
                        return true;
                    }
                    ptr = ptr.next;
                }

            }

        }
        return false;
    }

    public boolean remove(List<T> val) {

        Node<T> ptr = val.head;
        boolean removed = false;
        while (ptr != null) {
            if (remove(ptr.data)) {
                removed = true;
            }
            ptr = ptr.next;
        }
        return removed;

    }

    public boolean contains(T search) {
        Node<T> ptr = head;
        if (ptr == null) {
            return false;
        }
        while (ptr != null) {
            if (ptr.data.equals(search)) {
                return true;
            }
            ptr = ptr.next;
        }
        return false;
    }

    public boolean equals(List<T> other) {
        if (other == null) {
            return false;
        }
        if (length != other.length) {
            return false;
        }
        Node<T> ptr = head;
        Node<T> ptr2 = other.head;
        while (ptr != null && ptr2 != null) {
            if (!ptr.data.equals(ptr2.data)) {
                return false;
            }
            ptr = ptr.next;
            ptr2 = ptr2.next;
        }
        return ptr == null && ptr2 == null;
    }
}
