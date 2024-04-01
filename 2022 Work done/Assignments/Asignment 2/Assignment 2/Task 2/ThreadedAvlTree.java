@SuppressWarnings({"unchecked","rawtypes"})
public class ThreadedAvlTree<T extends Comparable<T>> {
    public Node<T> root;
    public Node<T> tmp;

    public ThreadedAvlTree() {
        this.root = null;
    }


    int getHeight(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }

    static Node getLeftMost(Node node) {
        while (node != null && node.left != null)
            node = node.left;
        return node;
    }

    // Inorder traversal of a threaded avl tree
    void print(Node<T> node) {
        if (node == null)
            return;

        Node<T> cur = getLeftMost(node);

        while (cur != null) {
            System.out.print(" " + cur.data + " ");


            if (cur.rightThread == true)
                cur = cur.right;
            else 
                cur = getLeftMost(cur.right);
        }
    }

    /* Do not edit the code above */


    void convertAVLtoThreaded(Node<T> node) {
        tmp = null;
        insert(node);
        root = node;
        makeThread(root, tmp);
    }

    /**
     * Insert the given data into the tree.
     * Duplicate data is not allowed. just return the node.
     */


    Node<T> insert(Node<T> node, T data) {
        removeThread(node);
        if (!contain(node, data))
        {
            setHeightToOneMore(node);
            node = insertValue(node, data);
            setHeightToOneLess(node);
        }
        tmp = null;
        insert(node);
        makeThread(node, tmp);
        this.root = node;
        return node;
    }

    /**
     * Delete the given element \texttt{data} from the tree.  Re-balance the tree after deletion.
     * If the data is not in the tree, return the given node / root.
     */
    Node<T> removeNode(Node<T> root, T data) {
        removeThread(root); 
        if (contain(root, data) == true)
        {
            setHeightToOneMore(root);
            root = removeNodeValue(root, data);
            setHeightToOneLess(root);
        }
        tmp = null;
        insert(root);
        makeThread(root, tmp);
        this.root = root;
        return root;
    }

    private void insert(T data)
    {
        if (tmp == null)
        {
            tmp = new Node<T>(data);
        }
        else
        {
            Node<T> curr = tmp;

            while (curr != null)
            {
                if (data.compareTo(curr.data) < 0)
                {
                    if (curr.left == null)
                    {
                        curr.left = new Node<T>(data);
                        curr.left.rightThread = true;
                        curr.left.right = curr;
                        break;
                    }
                    else
                    {
                        curr = curr.left;
                    }
                }
                else
                {
                    if (curr.right == null)
                    {
                        curr.right = new Node<T>(data);
                        break;
                    }
                    else if (curr.rightThread == true)
                    {
                        Node<T> node = new Node<T>(data);
                        node.right = curr.right;
                        node.rightThread = true;
                        curr.rightThread = false;
                        curr.right = node;
                        break;
                    }
                    else
                    {
                        curr = curr.right;
                    }
                }
            }
        }
    }

    private void insert(Node<T> node)
    {
        if (node == null)
        {
            return;
        }
        insert(node.data);
        insert(node.left);
        insert(node.right);
    }


    private void makeThread(Node<T> root ,Node<T> node)
    {
        if (node == null)
        {
            return;
        }

        if (node.rightThread == true)
        {
            Node<T> curr = getNode(root,node.data);
            Node<T> parent = getNode(root,node.right.data);
            curr.rightThread = true;
            curr.right = parent;
        }

        makeThread(root, node.left);
        if (!node.rightThread)
        {
            makeThread(root, node.right);
        }
    }

    private Node<T> getNode(Node<T> curr,T data)
    {
        while (curr != null)
        {
            if (data.equals(curr.data))
            {
                return curr;
            }
            else if (data.compareTo(curr.data) < 0)
            {
                curr = curr.left;
            }
            else
            {
                curr = curr.right;
            }
        }
        return null;
    }

    private void removeThread(Node<T> node)
    {
        if (node == null)
        {
            return;
        }

        if (node.rightThread == true)
        {
            node.right = null;
            node.rightThread = false;
        }
        removeThread(node.left);
        removeThread(node.right);
    }


    private int getMax(int left, int right)
    {
        if (left > right)
        {
            return left;
        }
        return right;
    }

    private Node<T> rightR(Node<T> node)
    {
        Node<T> parent = node.left;
        node.left = parent.right;
        parent.right = node;
        setHeight(node, parent);
        return parent;
    }

    private Node<T> leftR(Node<T> node)
    {
        Node<T> parent = node.right;
        node.right = parent.left;
        parent.left = node;
        setHeight(node, parent);
        return parent;
    }

    private void setHeight(Node<T> node, Node<T> parent)
    {
        node.height = 1 + getMax(getHeight(node.left), getHeight(node.right));
        parent.height = 1 + getMax(getHeight(parent.left), getHeight(parent.right));
    }

    private Boolean contain(Node<T> node, T data) {

        while (node != null)
        {
            if (data.equals(node.data))
            {
                return true;
            }
            else if (data.compareTo(node.data)  < 0)
            {
                node = node.left;
            }
            else
            {
                node = node.right;
            }
        }
        return false;
    }

    private Node<T> insertValue(Node<T> node, T data)
    {
        if (node == null)
        {
            node = new Node<T>(data);
            node.height = 1;
            return node;
        }
        
        else if (data.compareTo(node.data) < 0)
        {
            node.left = insertValue(node.left, data);
        }
        else
        {
            node.right = insertValue(node.right, data);
        }
        
        node.height = 1 + getMax(getHeight(node.left), getHeight(node.right));
        int balanceFactor = getHeight(node.right) - getHeight(node.left); 

        if (balanceFactor < -1)
        {
            if (data.compareTo(node.left.data) > 0)
            {
                node.left = leftR(node.left); 
            }
            return rightR(node);
        }
        else if (balanceFactor > 1)
        {
            if (data.compareTo(node.right.data) < 0)
            {
                node.right = rightR(node.right);
            }
            return leftR(node);
        }
        return node;
    }

    private void setHeightToOneLess(Node<T> node)
    {
        if (node == null)
        {
            return;
        }

        node.height = node.height - 1;
        setHeightToOneLess(node.left);
        setHeightToOneLess(node.right);
    }

    private void setHeightToOneMore(Node<T> node)
    {
        if (node == null)
        {
            return;
        }

        node.height = node.height + 1;
        setHeightToOneMore(node.left);
        setHeightToOneMore(node.right);
    }


    private Node<T> removeNodeValue(Node<T> root, T data) {
        if (root == null)
        {
            return null;
        }

        if (data.compareTo(root.data) < 0)
        {
            root.left = removeNodeValue(root.left, data);
        }

        else if(data.compareTo(root.data) > 0)
        {
            root.right = removeNodeValue(root.right, data);
        }

        else
        {
            if (root.right == null)
            {
                root = root.left;
            }
            else if (root.left == null) 
            {
                root = root.right;
            }
            else
            {
                Node<T> curr = root.left;
                for (; curr.right != null; curr = curr.right);
                root.data = curr.data;
                root.left = removeNodeValue(root.left, curr.data);
            }
        }

        if (root == null)
        {
            return root;
        }

        root.height = 1 + getMax(getHeight(root.left), getHeight(root.right));
        int balanceFactor = getHeight(root.right) - getHeight(root.left);

        if (balanceFactor < -1)
        {
            if (getHeight(root.right) < getHeight(root.left))
            {
                root.left = leftR(root.left);
            }
            return rightR(root);
        }
        else if ( balanceFactor > 1)
        {
            if (getHeight(root.right) < getHeight(root.left))
            {
                root.right = rightR(root.right);
            }
            return leftR(root);
        }
        return root;
    }
}
