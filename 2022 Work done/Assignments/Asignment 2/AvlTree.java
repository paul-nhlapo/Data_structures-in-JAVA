public class AvlTree<T extends Comparable<T>> {
    public Node<T> root;

    public AvlTree() {
        this.root = null;
    }


    int getHeight(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }

    /*Printing AvlTree in inorder*/
    void print(Node<T> node) {
        if (node == null)
            return;

        print(node.left);

        System.out.print(node.data + " ");

        print(node.right);
    }

    /* Do not edit the code above */

    /**
     * Insert the given data into the tree.
     * Duplicate data is not allowed. just return the node.
     */

    Node<T> insert(Node<T> node, T data) {

        if (!contain(node, data))
        {
            setHeightToOneMore(node);
            node = insertValue(node, data);
            setHeightToOneLess(node);
        }
        return node;
    }

    /**
     * Remove / Delete the node based on the given data
     * Return the node / root if the data do not exist
     */

    Node<T> removeNode(Node<T> root, T data) {
       
        if (contain(root, data) == true)
        {
            setHeightToOneMore(root);
            root = removeNodeValue(root, data);
            setHeightToOneLess(root);
        }
        return root;
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
