public class App {
    public static void main(String[] args) throws Exception {
        //TODO: Implement an extensive testing main!!!
        //Note: the print function tests both next and previous links
        //comment part of the code you will not be testing
        System.out.println("Testing countlist fucntions.................................");
        CountList <Integer> countList = new CountList<>();
        for(int i = 0; i < 10; i++)
        {
            countList.insert(i);
        }
        System.out.println("Testing insert function with both next and prev links.......:");
        print(countList);

        System.out.println("Testing the remove function in all cases......................:");
        countList.remove(0);
        countList.remove(9);
        countList.remove(5);
        countList.remove(0);
        countList.remove(9);
        countList.remove(5);
        print(countList);
        System.out.println("Testing the access method of countlist...........................");
        countList.access(4);
        System.out.print("Accesing 4: ");
        print(countList);
        countList.access(8);
        System.out.print("Accesing 8: ");
        print(countList);
        countList.access(4);
        System.out.print("Accesing 4: ");
        print(countList);
        countList.access(1);
        System.out.print("Accesing 1: ");
        print(countList);
        countList.access(1);
        System.out.print("Accesing 1: ");
        print(countList);
        countList.access(5);
        System.out.print("Accesing 5: ");
        print(countList);

        System.out.println("Testing Move to front functions.................................");
        MoveToFrontList<Integer> list = new MoveToFrontList<>();
        for(int i = 1; i < 11;i++)
        {
            list.insert(i);
        }
        System.out.println("Testing access..................................................");
        System.out.print("Before: ");
        print(list);
        System.out.println("Acessing 5: ");
        list.access(5);
        print(list);
        System.out.println("Acessing 5: ");
        list.access(5);
        print(list);
        System.out.println("Acessing 10: ");
        list.access(10);
        print(list);
        System.out.println("Acessing 6: ");
        list.access(6);
        print(list);
        for(int i = 1; i < 11;i++)
        {
            list.remove(i);
        }
        System.out.println("Acessing 10: ");
        list.access(10);
        print(list);
        
        System.out.println("Testing natural order functions..........................");
        NaturalOrderList<Integer> orderList = new NaturalOrderList<>();
        for(int i = 0; i < 10; i++)
        {
            orderList.insert(i);
        }
        print(orderList);
        for(int i = 0; i < 5;i++)
        {
            orderList.remove(i);
        }
        print(orderList);

        System.out.println("Testing transpose function......................................");
        TransposeList<Integer> transposeList = new TransposeList<>();
        for(int i = 0; i < 10; i++)
        {
            transposeList.insert(i);
        }
        System.out.print("Before trasposing stuff: ");
        print(transposeList);
        System.out.println("Transposing 2:");
        transposeList.access(2);
        print(transposeList);
        System.out.println("Transposing 2:");
        transposeList.access(2);
        print(transposeList);
        System.out.println("Transposing 9:");
        transposeList.access(9);
        print(transposeList);
        System.out.println("Transposing 5:");
        transposeList.access(5);
        print(transposeList);

        System.out.println("Testing Iterative traverse functions...........................");
        IterativeTraverse<Integer> iterative = new IterativeTraverse<>();
        CountList<Integer> iterativList = new CountList<>();
        for(int i = 0; i < 10;i++)
        {
            iterativList.insert(i);
        }
        iterative.setList(iterativList);
        System.out.println("Testing toString ...............................................");
        System.out.println(iterative);
        System.out.println();
        System.out.println("Testing contains .............................................. ");
        if(iterative.contains(0)) System.out.println("list contains 0 ? yes");
        if(iterative.contains(17) == false) System.out.println("list contains 17 ? no");
        System.out.println();
        System.out.println("Testing size ...................................................");
        System.out.println("Size of list is: "+ iterative.size());
        System.out.println();
        System.out.println("Testing find .................................................... ");
        Node <Integer> node = iterative.find(2);
        System.out.println("find 2 in iterative -> "+node.toString());
        System.out.println();
        System.out.println("Testing get .............................................. ");
        node = iterative.get(3);
        System.out.println("Get node in position 3 ->"+node.toString());
        System.out.println();
        System.out.println("Testing reverse list .............................................. ");
        SelfOrderingList<Integer> reverse = new CountList<>();
        reverse = iterative.reverseList();
        System.out.println("List reversed:");
        print(reverse);
        System.out.println();
        System.out.println("Testing clone .............................................. ");
        reverse = iterative.clone(reverse);
        System.out.println("List cloned:");
        print(reverse);
        System.out.println();
        System.out.println("Testing ToString .............................................. ");
        System.out.println(iterative);

        System.out.println("Testing Recursive traverse functions...........................");
        RecursiveTraverse<Integer> recursive = new RecursiveTraverse<>();
        CountList<Integer> recursiveList = new CountList<>();
        for(int i = 0; i < 10;i++)
        {
            recursiveList.insert(i);
        }
        recursive.setList(recursiveList);
        System.out.println("Testing toString ...............................................");
        System.out.println(recursive);
        System.out.println();
        System.out.println("Testing contains .............................................. ");
        if(recursive.contains(0)) System.out.println("list contains 0 ? yes");
        if(recursive.contains(17) == false) System.out.println("list contains 17 ? no");
        System.out.println();
        System.out.println("Testing size ...................................................");
        System.out.println("Size of list is: "+ recursive.size());
        System.out.println();
        System.out.println("Testing find .................................................... ");
        Node <Integer> noder = recursive.find(2);
        System.out.println("find 2 in recursive -> "+noder.toString());
        System.out.println();
        System.out.println("Testing get .............................................. ");
        noder = recursive.get(3);
        System.out.println("Get node in position 3 ->"+noder.toString());
        System.out.println();
        System.out.println("Testing reverse list .............................................. ");
        SelfOrderingList<Integer> reverser = new CountList<>();
        reverser = recursive.reverseList();
        System.out.println("List reversed:");
        print(reverser);
        System.out.println();
        System.out.println("Testing clone .............................................. ");
        reverser = recursive.clone(reverser);
        System.out.println("List cloned:");
        print(reverser);
        System.out.println();
        System.out.println("Testing ToString .............................................. ");
        System.out.println(recursive);
    }
    public static void print(SelfOrderingList<Integer> list)
    {
        Node<Integer> node = list.head;
        while(node!= null)
        {
            System.out.print(node.toString());
            node = node.next;
        }
        System.out.println();

        node = list.tail;
        while(node!= null)
        {
            System.out.print(node.toString());
            node = node.prev;
        }
        System.out.println();
    }

}
//Expected output:
/*
 * Testing countlist fucntions.................................
Testing insert function with both next and prev links.......:
(0[0])(1[0])(2[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])
(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(2[0])(1[0])(0[0])
Testing the remove function in all cases......................:
(1[0])(2[0])(3[0])(4[0])(6[0])(7[0])(8[0])
(8[0])(7[0])(6[0])(4[0])(3[0])(2[0])(1[0])
Testing the access method of countlist...........................
Accesing 4: (4[1])(1[0])(2[0])(3[0])(6[0])(7[0])(8[0])
(8[0])(7[0])(6[0])(3[0])(2[0])(1[0])(4[1])
Accesing 8: (4[1])(8[1])(1[0])(2[0])(3[0])(6[0])(7[0])
(7[0])(6[0])(3[0])(2[0])(1[0])(8[1])(4[1])
Accesing 4: (4[2])(8[1])(1[0])(2[0])(3[0])(6[0])(7[0])
(7[0])(6[0])(3[0])(2[0])(1[0])(8[1])(4[2])
Accesing 1: (4[2])(8[1])(1[1])(2[0])(3[0])(6[0])(7[0])
(7[0])(6[0])(3[0])(2[0])(1[1])(8[1])(4[2])
Accesing 1: (4[2])(1[2])(8[1])(2[0])(3[0])(6[0])(7[0])
(7[0])(6[0])(3[0])(2[0])(8[1])(1[2])(4[2])
Accesing 5: (4[2])(1[2])(8[1])(2[0])(3[0])(6[0])(7[0])
(7[0])(6[0])(3[0])(2[0])(8[1])(1[2])(4[2])
Testing Move to front functions.................................
Testing access..................................................
Before: (1[0])(2[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])(10[0])
(10[0])(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(2[0])(1[0])
Acessing 5: 
(5[0])(1[0])(2[0])(3[0])(4[0])(6[0])(7[0])(8[0])(9[0])(10[0])
(10[0])(9[0])(8[0])(7[0])(6[0])(4[0])(3[0])(2[0])(1[0])(5[0])
Acessing 5: 
(5[0])(1[0])(2[0])(3[0])(4[0])(6[0])(7[0])(8[0])(9[0])(10[0])
(10[0])(9[0])(8[0])(7[0])(6[0])(4[0])(3[0])(2[0])(1[0])(5[0])
Acessing 10: 
(10[0])(5[0])(1[0])(2[0])(3[0])(4[0])(6[0])(7[0])(8[0])(9[0])
(9[0])(8[0])(7[0])(6[0])(4[0])(3[0])(2[0])(1[0])(5[0])(10[0])
Acessing 6: 
(6[0])(10[0])(5[0])(1[0])(2[0])(3[0])(4[0])(7[0])(8[0])(9[0])
(9[0])(8[0])(7[0])(4[0])(3[0])(2[0])(1[0])(5[0])(10[0])(6[0])
Acessing 10: 


Testing natural order functions..........................
(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(2[0])(1[0])(0[0])
(0[0])(1[0])(2[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])
(9[0])(8[0])(7[0])(6[0])(5[0])
(5[0])(6[0])(7[0])(8[0])(9[0])
Testing transpose function......................................
Before trasposing stuff: (0[0])(1[0])(2[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])
(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(2[0])(1[0])(0[0])
Transposing 2:
(0[0])(2[0])(1[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])
(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(1[0])(2[0])(0[0])
Transposing 2:
(2[0])(0[0])(1[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])
(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(1[0])(0[0])(2[0])
Transposing 9:
(2[0])(0[0])(1[0])(3[0])(4[0])(5[0])(6[0])(7[0])(9[0])(8[0])
(8[0])(9[0])(7[0])(6[0])(5[0])(4[0])(3[0])(1[0])(0[0])(2[0])
Transposing 5:
(2[0])(0[0])(1[0])(3[0])(5[0])(4[0])(6[0])(7[0])(9[0])(8[0])
(8[0])(9[0])(7[0])(6[0])(4[0])(5[0])(3[0])(1[0])(0[0])(2[0])
Testing Iterative traverse functions...........................
Testing toString ...............................................
->(0[0])->(1[0])->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])

Testing contains .............................................. 
list contains 0 ? yes
list contains 17 ? no

Testing size ...................................................
Size of list is: 10

Testing find .................................................... 
find 2 in iterative -> (2[0])

Testing get .............................................. 
Get node in position 3 ->(3[0])

Testing reverse list .............................................. 
List reversed:
(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(2[0])(1[0])(0[0])
(0[0])(1[0])(2[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])

Testing clone .............................................. 
List cloned:
(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(2[0])(1[0])(0[0])
(0[0])(1[0])(2[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])

Testing ToString .............................................. 
->(0[0])->(1[0])->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])
Testing Recursive traverse functions...........................
Testing toString ...............................................
->(0[0])->(1[0])->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])

Testing contains .............................................. 
list contains 0 ? yes
list contains 17 ? no

Testing size ...................................................
Size of list is: 10

Testing find .................................................... 
find 2 in recursive -> (2[0])

Testing get .............................................. 
Get node in position 3 ->(3[0])

Testing reverse list .............................................. 
List reversed:
(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(2[0])(1[0])(0[0])
(0[0])(1[0])(2[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])

Testing clone .............................................. 
List cloned:
(9[0])(8[0])(7[0])(6[0])(5[0])(4[0])(3[0])(2[0])(1[0])(0[0])
(0[0])(1[0])(2[0])(3[0])(4[0])(5[0])(6[0])(7[0])(8[0])(9[0])

Testing ToString .............................................. 
->(0[0])->(1[0])->(2[0])->(3[0])->(4[0])->(5[0])->(6[0])->(7[0])->(8[0])->(9[0])
 */