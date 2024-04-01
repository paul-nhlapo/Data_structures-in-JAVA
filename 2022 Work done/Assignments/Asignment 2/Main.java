public class Main {

    public static void main(String[] args) {

        // AvlTree<Integer> tree = new AvlTree<>();

        // /* Constructing tree given in the above figure */
        // tree.root = tree.insert(tree.root, 20);
        // tree.root = tree.insert(tree.root, 25);
        // tree.root = tree.insert(tree.root, 35);
        // tree.root = tree.insert(tree.root, 14);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        // tree.root = tree.insert(tree.root, 65);
        // tree.root = tree.insert(tree.root, 80);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        // tree.root = tree.insert(tree.root, 82);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        // tree.root = tree.removeNode(tree.root, 80);

        // System.out.println("Inorder traversal" +
        //         " of constructed tree is : ");
        // tree.print(tree.root);
        // System.out.println("\nTree Height is: " + tree.getHeight(tree.root));

        // //        threaded Avl tree

        // ThreadedAvlTree<Integer> threadedAvlTree = new ThreadedAvlTree<>();
        // threadedAvlTree.convertAVLtoThreaded(tree.root);

        // System.out.println("Inorder traversal" +
        //         " of constructed threaded avl tree is : ");
        // threadedAvlTree.print(threadedAvlTree.root);
        // System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        // threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 82);
        // threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 91);
        // threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 50);

        // System.out.println("Inorder traversal" +
        //         " of constructed threaded avl tree is : ");
        // threadedAvlTree.print(threadedAvlTree.root);
        // System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        // threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 91);

        // System.out.println("Inorder traversal" +
        //         " of constructed threaded avl tree is : ");
        // threadedAvlTree.print(threadedAvlTree.root);
        // System.out.println("\nTree Height is: " + threadedAvlTree.getHeight(threadedAvlTree.root));

        // test1();
        test2();
    }

    public static void test1()
    {
        AvlTree<Character> tree = new AvlTree<>();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 'D');
        tree.root = tree.insert(tree.root, 'B');
        tree.root = tree.insert(tree.root, 'C');
        tree.root = tree.insert(tree.root, 'A');
        tree.root = tree.insert(tree.root, 'E');
        tree.root = tree.insert(tree.root, 'F');
        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("\nTree Height is: " + tree.getHeight(tree.root));
    }

    public static void test2()
    {
        AvlTree<Integer> tree = new AvlTree<>();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 26);
        System.out.println(tree.root.data);
        tree.root = tree.insert(tree.root, 15);
        System.out.println(tree.root.left.data);
        tree.root = tree.insert(tree.root, 30);
        System.out.println(tree.root.right.data);
        System.out.println("******************************************************");
        tree.root = tree.insert(tree.root, 4);
        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println("******************************************************");
        tree.root = tree.insert(tree.root, 20);
        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println(tree.root.left.right.data);
        System.out.println("******************************************************");
        tree.root = tree.insert(tree.root, 21);

        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println(tree.root.right.left.data);
        System.out.println(tree.root.right.right.data);
        System.out.println("******************************************************");

         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));



        tree.root = tree.insert(tree.root, 2);
        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println(tree.root.left.right.data);
        System.out.println(tree.root.right.left.data);
        System.out.println(tree.root.right.right.data);
        System.out.println("******************************************************");

        tree.root = tree.insert(tree.root, 11);
        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println(tree.root.left.right.data);
        System.out.println(tree.root.right.left.data);
        System.out.println(tree.root.right.right.data);
        System.out.println(tree.root.left.right.left.data);
        System.out.println("******************************************************");

         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.insert(tree.root, 3);
        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println(tree.root.left.right.data);
        System.out.println(tree.root.right.left.data);
        System.out.println(tree.root.right.right.data);
        System.out.println(tree.root.left.left.right.data);
        System.out.println(tree.root.left.right.left.data);
        System.out.println("******************************************************");
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.insert(tree.root, 7);
        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println(tree.root.left.right.data);
        System.out.println(tree.root.right.left.data);
        System.out.println(tree.root.right.right.data);
        System.out.println(tree.root.left.left.right.data);
        System.out.println(tree.root.left.right.left.data);
        System.out.println(tree.root.left.right.right.data);
        System.out.println("******************************************************");
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
         tree.root = tree.insert(tree.root, 8);
        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println(tree.root.left.right.data);
        System.out.println(tree.root.right.left.data);
        System.out.println(tree.root.right.right.data);
        System.out.println(tree.root.left.left.right.data);
        System.out.println(tree.root.left.right.right.data);
        System.out.println(tree.root.right.right.left.data);
         System.out.println(tree.root.right.right.right.data);
        System.out.println("******************************************************");
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 8);
        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println(tree.root.left.right.data);
        System.out.println(tree.root.right.left.data);
        System.out.println(tree.root.right.right.data);
        System.out.println(tree.root.left.left.right.data);
        // System.out.println(tree.root.left.right.right.data);
        System.out.println(tree.root.right.right.left.data);
         System.out.println(tree.root.right.right.right.data);
        System.out.println("******************************************************");

         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        tree.root = tree.removeNode(tree.root, 11);
        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.left.left.data);
        System.out.println(tree.root.left.right.data);
        System.out.println(tree.root.right.left.data);
        System.out.println(tree.root.right.right.data);
        // System.out.println(tree.root.left.left.right.data);
        // System.out.println(tree.root.left.right.right.data);
        System.out.println(tree.root.right.right.left.data);
         System.out.println(tree.root.right.right.right.data);
        System.out.println("******************************************************");

         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 26);

         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 20);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 7);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 21);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 4);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 2);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 15);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 30);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.removeNode(tree.root, 3);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));
        preOrder(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        tree.root = tree.insert(tree.root, 26);
        tree.root = tree.insert(tree.root, 15);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 4);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 21);
        tree.root = tree.insert(tree.root, 2);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, 3);
        tree.root = tree.insert(tree.root, 7);
        tree.root = tree.insert(tree.root, 8);
        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        tree.print(tree.root);
        System.out.println("- " + tree.getHeight(tree.root));

        ThreadedAvlTree<Integer> threadedAvlTree = new ThreadedAvlTree<>();
        threadedAvlTree.convertAVLtoThreaded(tree.root);

        System.out.println("Inorder traversal" +
                " of constructed threaded avl tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 8);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 11);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 26);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 20);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 7);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 21);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 4);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 2);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 15);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 30);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.removeNode(threadedAvlTree.root, 3);
        
         System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));

        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 26);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 15);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 30);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 4);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 20);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 21);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 2);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 11);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 3);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 7);
        threadedAvlTree.root = threadedAvlTree.insert(threadedAvlTree.root, 8);
        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        threadedAvlTree.print(threadedAvlTree.root);
        System.out.println("- " + threadedAvlTree.getHeight(threadedAvlTree.root));
    }

    public static void preOrder(Node node)
    {
        if (node == null)
        {
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
}

/*
Expected output
---------------------------
Inorder traversal of constructed tree is :
14 20 25 35
Tree Height is: 2
Inorder traversal of constructed tree is :
14 20 25 35 65 80
Tree Height is: 2
Inorder traversal of constructed tree is :
14 20 25 35 65 80 82
Tree Height is: 3
Inorder traversal of constructed tree is :
14 20 25 35 65 82
Tree Height is: 2

Inorder traversal of constructed threaded avl tree is :
14 20 25 35 65 82
Tree Height is: 2
Inorder traversal of constructed threaded avl tree is :
14 20 25 35 50 65 82 91
Tree Height is: 3
Inorder traversal of constructed threaded avl tree is :
14 20 25 35 50 65 82
Tree Height is: 3


 */
