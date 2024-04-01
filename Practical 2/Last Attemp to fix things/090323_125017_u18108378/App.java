public class App {

    public static void main(String[] args) throws Exception {
        Test0(); // Basic Traverse checking

        // Test1(); // Natural Order List && basic toString()
        // Test2(); // Same as Test1, but with recursive
        // Test3(); // TransposeList & basic itter contains()
        // Test4(); // MoveToFront checks
        // Test5(); // CountList checksmake

        System.out.println("All tests passed!");
    }

    public static void Test1() throws Exception {
        SelfOrderingList<Integer> ctLis = new NaturalOrderList<>();

        for (int i = 0; i < 10; i++)
            ctLis.insert(i);

        SelfOrderingList<Integer> natLis = new NaturalOrderList<>();

        for (int i = 9; i >= 0; i--)
            natLis.insert(i);

        Traverser<Integer> shaltrav = new IterativeTraverse<>();
        Traverser<Integer> shaltrav2 = new IterativeTraverse<>();
        shaltrav.setList(natLis);
        shaltrav2.setList(ctLis);

        if (!shaltrav.toString().equals(shaltrav2.toString())) {
            throw new RuntimeException(shaltrav2 + " != " + shaltrav);
        }

        Traverser<Integer> trav = new IterativeTraverse(ctLis);

        if (trav.list == null || trav.list == ctLis || trav.list.head == ctLis.head)
            throw new RuntimeException("Incorrect deep copy for IterativeTraverse constructor");

        if (trav.toString()
                .compareTo("->(9[0])->(8[0])->(7[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])") != 0)
            throw new RuntimeException(trav.toString() + "is incorrect");

        for (int i = 1; i < 9; i++) {
            ctLis.remove(i);
        }

        trav.setList(ctLis);

        if (trav.toString()
                .compareTo("->(9[0])->(8[0])->(7[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])") == 0)
            throw new RuntimeException("Iterative traverse setList() function does not function");

        if (trav.toString().compareTo("->(9[0])->(0[0])") != 0)
            throw new RuntimeException("List remove() does not delete properly: " + trav);

        ctLis.remove(9);

        if (trav.toString().compareTo("->(9[0])->(0[0])") == 0)
            throw new RuntimeException(
                    "Iterative traverse setList() shallow copies the head of the paramater list, rather than the entire list. Alternativelely, the remove function of the List, does not remove values from the head");

        if (trav.toString().compareTo("->(0[0])") != 0)
            throw new RuntimeException("List remove() does not delete properly: " + trav);

        ctLis.remove(0);

        if (ctLis.head != null) {
            throw new RuntimeException(
                    "List remove() does not set the head back to null when deleting the only remaining element");
        }

        if (trav.toString().compareTo("") != 0)
            throw new RuntimeException("Incorrect Traverser toString for empty list: " + trav);
    }

    public static void Test2() throws Exception {
        SelfOrderingList<Integer> ctLis = new NaturalOrderList<>();

        for (int i = 0; i < 10; i++)
            ctLis.insert(i);

        Traverser<Integer> trav = new RecursiveTraverse<>(ctLis);

        if (trav.list == null || trav.list == ctLis || trav.list.head == ctLis.head)
            throw new RuntimeException("Incorrect deep copy for Recursive Traverse constructor");

        if (trav.toString()
                .compareTo("->(9[0])->(8[0])->(7[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])") != 0)
            throw new RuntimeException(trav.toString() + "is incorrect");

        for (int i = 1; i < 9; i++) {
            ctLis.remove(i);
        }

        trav.setList(ctLis);

        if (trav.toString()
                .compareTo("->(9[0])->(8[0])->(7[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])") == 0)
            throw new RuntimeException("Recursive traverse setList() function does not function");

        if (trav.toString().compareTo("->(9[0])->(0[0])") != 0)
            throw new RuntimeException("List remove() does not delete properly: " + trav);

        ctLis.remove(9);

        if (trav.toString().compareTo("->(9[0])->(0[0])") == 0)
            throw new RuntimeException(
                    "Recursive traverse setList() shallow copies the head of the paramater list, rather than the entire list. Alternativelely, the remove function of the List, does not remove values from the head");

        if (trav.toString().compareTo("->(0[0])") != 0)
            throw new RuntimeException("List remove() does not delete properly: " + trav);

        ctLis.remove(0);

        if (ctLis.head != null) {
            throw new RuntimeException(
                    "List remove() does not set the head back to null when deleting the only remaining element");
        }

        if (trav.toString().compareTo("") != 0)
            throw new RuntimeException("Incorrect Recursive Traverser toString() for empty list: " + trav);
    }

    public static void Test3() throws Exception {
        SelfOrderingList<Integer> tran = new TransposeList<>();

        for (int i = 0; i < 10; i++)
            tran.insert(i);

        Traverser<Integer> trev = new IterativeTraverse<>();
        trev.setList(tran);

        if (trev.toString()
                .compareTo("->(0[0])->(1[0])->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])") != 0)
            throw new RuntimeException(trev + " is incorrect");

        for (int i = 0; i < 10; i++)
            tran.access(i);

        if (trev.toString()
                .compareTo("->(1[0])->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])->(0[0])") != 0)
            throw new RuntimeException("TransposeList does not swap correctly");

        Traverser<Integer> trevrev = new IterativeTraverse<>();
        trevrev.setList(trev.reverseList());
        if (trevrev.toString()
                .compareTo("->(0[0])->(9[0])->(8[0])->(7[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])") != 0)
            throw new RuntimeException("reverseList() function for Iterative is incorrect: " + trevrev);

        for (int i = 0; i < 10; i++)
            tran.access(i);

        if (trev.toString()
                .compareTo("->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(1[0])->(9[0])->(0[0])") != 0)
            throw new RuntimeException("TransposeList does not swap correctly");

        for (int i = 2; i < 9; i++)
            tran.remove(i);

        tran.access(0);
        tran.access(0);

        if (trev.toString().compareTo("->(0[0])->(1[0])->(9[0])") != 0)
            throw new RuntimeException("Incorrect access/remove function");

        if (trev.contains(-1) || !trev.contains(0) || !trev.contains(1) || !trev.contains(9) || trev.contains(10))
            throw new RuntimeException("Itterative treverse contains() function provides invalid output");

    }

    public static void Test4() throws Exception {
        SelfOrderingList<Integer> tran = new MoveToFrontList<>();
        for (int i = 0; i < 10; i++)
            tran.insert(i);

        Traverser<Integer> trev = new IterativeTraverse<>();
        trev.setList(tran);

        for (int i = 0; i < 10; i++)
            tran.access(i);

        if (trev.toString()
                .compareTo("->(9[0])->(8[0])->(7[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])") != 0)
            throw new RuntimeException("MoveToFrontList does not access correctly " + trev);

        System.out.println("Test4");
        for (int i = 10; i >= 0; i--) {
            tran.access(i);
            System.out.println(Integer.toString(i) + trev);
        }

        System.out.println("");
        // Test4
        // 10->(9[0])->(8[0])->(7[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])
        // 9->(9[0])->(8[0])->(7[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])
        // 8->(8[0])->(9[0])->(7[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])
        // 7->(7[0])->(8[0])->(9[0])->(6[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])
        // 6->(6[0])->(7[0])->(8[0])->(9[0])->(5[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])
        // 5->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])->(4[0])->(3[0])->(2[0])->(1[0])->(0[0])
        // 4->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])->(3[0])->(2[0])->(1[0])->(0[0])
        // 3->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])->(2[0])->(1[0])->(0[0])
        // 2->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])->(1[0])->(0[0])
        // 1->(1[0])->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])->(0[0])
        // 0->(0[0])->(1[0])->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])

        if (trev.toString()
                .compareTo("->(0[0])->(1[0])->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])") != 0)
            throw new RuntimeException("MoveToFrontList does not access correctly " + trev);

        for (int i = 2; i < 9; i++)
            tran.remove(i);

        tran.access(0);
        tran.access(0);

        if (trev.toString().compareTo("->(0[0])->(1[0])->(9[0])") != 0)
            throw new RuntimeException("Incorrect access/remove function " + trev);

    }

    public static void Test5() throws Exception {
        SelfOrderingList<Integer> tran = new CountList<>();
        for (int i = 0; i < 5; i++)
            tran.insert(i);

        Traverser<Integer> trev = new IterativeTraverse<>();
        trev.setList(tran);
        System.out.println("Test5");
        System.out.println(trev);

        tran.access(2);
        System.out.println(trev);
        tran.access(4);
        System.out.println(trev);
        tran.access(2);
        System.out.println(trev);
        tran.access(1);

        // Test5
        // ->(0[0])->(1[0])->(2[0])->(3[0])->(4[0])
        // ->(2[1])->(0[0])->(1[0])->(3[0])->(4[0])
        // ->(4[1])->(2[1])->(0[0])->(1[0])->(3[0])
        // ->(2[2])->(4[1])->(0[0])->(1[0])->(3[0])
        // ->(2[2])->(4[1])->(1[1])->(0[0])->(3[0])

        System.out.println(trev);
        System.out.println("");

        if (trev.toString().compareTo("->(2[2])->(4[1])->(1[1])->(0[0])->(3[0])") != 0)
            throw new RuntimeException(
                    "CountList does not access correctly. Would recommend referring to MoveToFront code, but with an edge-case of inserting after a node, rather than head "
                            + trev);

        for (int i = 5; i >= 0; i--) {
            tran.access(i);
            System.out.println(Integer.toString(i) + trev);
        }

        // 5->(2[2])->(4[1])->(1[1])->(0[0])->(3[0])
        // 4->(2[2])->(4[2])->(1[1])->(0[0])->(3[0])
        // 3->(2[2])->(4[2])->(1[1])->(3[1])->(0[0])
        // 2->(2[3])->(4[2])->(1[1])->(3[1])->(0[0])
        // 1->(2[3])->(4[2])->(1[2])->(3[1])->(0[0])
        // 0->(2[3])->(4[2])->(1[2])->(3[1])->(0[1])

        if (trev.toString().compareTo("->(2[3])->(4[2])->(1[2])->(3[1])->(0[1])") != 0)
            throw new RuntimeException("CountList does not access correctly " + trev);

        for (int i = 2; i < 9; i++)
            tran.remove(i);

        tran.access(0);
        tran.access(0);

        if (trev.toString().compareTo("->(0[3])->(1[2])") != 0)
            throw new RuntimeException("Incorrect access/remove function " + trev);

    }

    public static void Test0() throws Exception {
        Node<Integer> custhead = new Node<>(0);
        Node<Integer> ptr = custhead;
        Node<Integer> prev = null;
        for (int i = 1; i < 4; i++) {
            ptr.prev = prev;
            ptr.next = new Node<Integer>(i);
            prev = ptr;
            ptr = ptr.next;
        }

        SelfOrderingList<Integer> custList = new CountList<>();
        Traverser<Integer> ittr = new IterativeTraverse<>();
        Traverser<Integer> rec = new RecursiveTraverse<>();

        custList.head = custhead;

        ittr.setList(custList);
        rec.setList(custList);

        if (ittr.list.head != custhead)
            throw new RuntimeException("Incorrect shallow copy for IterativeTraverse setList()");

        if (rec.list.head != custhead)
            throw new RuntimeException("Incorrect shallow copy for RecursiveTraverse setList()");

        ittr = new IterativeTraverse<>(custList);
        rec = new RecursiveTraverse<>(custList);

        if (ittr.list == null || ittr.list == custList || ittr.list.head == custhead)
            throw new RuntimeException("Incorrect deep copy for IterativeTraverse constructor");

        if (rec.list == null || rec.list == custList || rec.list.head == custhead)
            throw new RuntimeException("Incorrect deep copy for RecursiveTraverse constructor");

        if (!ittr.toString().equals("->(0[0])->(1[0])->(2[0])->(3[0])"))
            throw new RuntimeException("Incorrect Iterative toString()");

        if (!rec.toString().equals("->(0[0])->(1[0])->(2[0])->(3[0])"))
            throw new RuntimeException("Incorrect Recursive toString()");

        Traverser<Integer> revIttr = new IterativeTraverse<>(ittr.reverseList());
        Traverser<Integer> revRec = new RecursiveTraverse<>(ittr.reverseList());

        Node<Integer> oldprev = prev.next;
        prev = prev.next;
        Node<Integer> testNode = revIttr.list.head;

        while (prev.prev != null) {
            if (!prev.data.equals(testNode.data))
                throw new RuntimeException("Iterative reverseList() data mismatch for .next values");

            prev = prev.prev;
            testNode = testNode.next;
        }

        if (!prev.data.equals(testNode.data))
            throw new RuntimeException("Iterative reverseList() data mismatch for .next values");

        while (testNode.prev != null) {
            if (!prev.data.equals(testNode.data))
                throw new RuntimeException("Iterative reverseList() data mismatch for .prev values");

            prev = prev.next;
            testNode = testNode.prev;
        }

        if (!prev.data.equals(testNode.data))
            throw new RuntimeException("Iterative reverseList() data mismatch for .next values");

        prev = oldprev;
        testNode = revRec.list.head;

        while (prev.prev != null) {
            if (!prev.data.equals(testNode.data))
                throw new RuntimeException("Recursive reverseList() data mismatch for .next values");

            prev = prev.prev;
            testNode = testNode.next;
        }

        if (!prev.data.equals(testNode.data))
            throw new RuntimeException("Recursive reverseList() data mismatch for .next values");

        while (testNode.prev != null) {
            if (!prev.data.equals(testNode.data))
                throw new RuntimeException("Recursive reverseList() data mismatch for .prev values");

            prev = prev.next;
            testNode = testNode.prev;
        }

        if (!prev.data.equals(testNode.data))
            throw new RuntimeException("Recursive reverseList() data mismatch for .next values");

    }
}
