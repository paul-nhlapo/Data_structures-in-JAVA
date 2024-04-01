/**
 * Name:Paul Nhlapo
 * Student Number:u18108378
 */

public class OrganisingList {

    public ListNode root = null;

    public OrganisingList() {

    }

    /**
     * Calculate the sum of keys recursively, starting with the given node until the
     * end of the list
     * 
     * @return The sum of keys from the current node to the last node in the list
     *         NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {
        // Your code here...
        if (node.next == null) {
            return node.key;

        } else {
            return sumRec(node.next) + node.key;

        }
    }

    /**
     * Calculate and set the data for the given node.
     * 
     * @return The calculated data for the given node
     *         NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {
        // Your code here...
        if (node.next == null) {
            node.data = node.key;
            return node.key;
        } else {
            node.data = (node.key * sumRec(node)) - dataRec(node.next);
            return (node.key * sumRec(node)) - dataRec(node.next);
        }
    }

    /**
     * Calculate the data field of all nodes in the list using the recursive
     * functions.
     * DO NOT MODIFY!
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    /**
     * Retrieve the data for the node with the specified key and swap the
     * accessed node with its predecessor, then recalculate data fields.
     * 
     * @return The data of the node before it has been moved,
     *         otherwise 'null' if the key does not exist.
     */
    public Integer getData(Integer key) {
        // Your code here...
        ListNode DataNode = root;
        Integer tempData = DataNode.data;
        while (DataNode != null) {
            if (DataNode.data == key && DataNode.data != null) {
                DataNode.data = DataNode.key;
                calculateData();
            } else {
                return null;
            }
        }
        return tempData;
    }

    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {
        // Your code here...
        ListNode prevNhh = root;
        ListNode previousNode;
        ListNode currentNode;
        if (!(prevNhh == null || !contains(key))) {
            // return;
            if (prevNhh.key == key) {
                root = root.next;
                calculateData();
            } else {
                previousNode = currentNode = root;
                while (currentNode.next != null && currentNode.key != key) {
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }
                previousNode.next = currentNode.next;
                currentNode = null;
                calculateData();
            }
        }
        return;
    }

    /**
     * Insert a new key into the linked list.
     * 
     * New nodes should be inserted to the back
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {
        // Your code here...
        ListNode newNode = new ListNode(key);
        if (root == null) {
            root = newNode;
            calculateData();
        } else if (!contains(key)) {
            ListNode Node;
            Node = root;
            while (Node.next != null) {
                Node = Node.next;
            }
            Node.next = newNode;
            calculateData();
        } else {
            return;
        }
    }

    /**
     * Check if a key is contained in the list
     * 
     * @return true if the key is in the list, otherwise false
     */
    public Boolean contains(Integer key) {
        // Your code here...
        if (root == null) {
            return false;
        } else if (root.key == key) {
            return true;
        } else {
            while (root != null) {
                root = root.next;
            }
        }
        return false;
    }

    /**
     * Return a string representation of the Linked List.
     * DO NOT MODIFY!
     */
    public String toString() {
        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result.concat("[K: " + node.key + ", D: " + node.data + "]");

            if (node.next != null) {
                result = result.concat(" ");
            }
        }

        return result;
    }

    /**
     * Return a string representation of the linked list, showing only the keys of
     * nodes.
     * DO NOT MODIFY!
     */
    public String toStringKeysOnly() {

        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result + node.key;

            if (node.next != null) {
                result = result.concat(", ");
            }
        }

        return result;
    }

}