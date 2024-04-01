public class Main {
    public static void mainTest1() {
        System.out.println();
        System.out.println(
                "[Lvl 2]------>[1]------>[3]->[4]\n[Lvl 1]------>[1]------>[3]->[4]---------------->[8]\n[Lvl 0]->[0]->[1]->[2]->[3]->[4]->[5]->[6]->[7]->[8]->[9]\nSearching for 8	[1][3][4][8]\nSearching for 2	[1][3][3][2]");
        System.out.println();
        System.out.println("\n------- The provided main from the Lecture!! -------");
        SkipList<Integer> myList = new SkipList<>(3);
        for (int i = 0; i < 10; i++) {
            myList.insert(i);
        }
        System.out.println(myList);
        System.out.print("Searching for 8\t");
        myList.printSearchPath(8);
        System.out.print("Searching for 2\t");
        myList.printSearchPath(2);
        System.out.println("\n-------------- ENDS HERE ----------------");
        System.out.println();
    }

    public static void mainTest2() {
        System.out.println("-------- Testing the Constructor -----------");
        SkipList<Integer> skipList = new SkipList<Integer>(4);
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing the Insert -----------");
        skipList.insert(5);
        skipList.insert(3);
        skipList.insert(55);
        skipList.insert(10);
        skipList.insert(19);
        skipList.insert(22);
        skipList.insert(5);
        skipList.insert(3);
        skipList.insert(60);
        skipList.insert(99);
        skipList.insert(29);
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing isEmpty -----------");
        System.out.println(skipList.isEmpty());
        System.out.println("-------- Testing Search -----------");
        if (skipList.search(60) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(60).key);
        }
        if (skipList.search(55) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(55).key);
        }
        if (skipList.search(5) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(5).key);
        }
        if (skipList.search(19) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(19).key);
        }
        if (skipList.search(22) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(22).key);
        }
        if (skipList.search(3) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(3).key);
        }
        if (skipList.search(99) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(99).key);
        }
        if (skipList.search(67) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(67).key);
        }
        if (skipList.search(61) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(61).key);
        }
        if (skipList.search(6) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(6).key);
        }
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList.toString());
        System.out.println("------------ Testing the Delete ---------------\n");
        if (skipList.delete(3) == true) {
            System.out.println("Deleted 3 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList.toString());
        System.out.println("-------------------- DONE ------------------------");
        if (skipList.delete(17) == true) {
            System.out.println("Deleted 17 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList.toString());
        System.out.println("-------------------- DONE ------------------------");
        if (skipList.delete(3) == true) {
            System.out.println("Deleted 3 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing Search -----------");
        if (skipList.search(3) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList.search(3).key);
        }
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing printSearchPath -----------");
        System.out.print("Search Path 55: ");
        skipList.printSearchPath(55);
        System.out.print("Search Path 5: ");
        skipList.printSearchPath(5);
        System.out.print("Search Path 19: ");
        skipList.printSearchPath(19);
        System.out.print("Search Path 22: ");
        skipList.printSearchPath(22);
        System.out.print("Search Path 34: ");
        skipList.printSearchPath(34);
        System.out.println("\n-------------------- DONE ------------------------");
        System.out.println();
    }

    public static void mainTest4() {
        System.out.println("2. SKIPLIST OF MAX-LEVEL 1");
        SkipList<Integer> skipList1 = new SkipList<Integer>(1);
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList1.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing the Insert -----------");
        skipList1.insert(5);
        skipList1.insert(55);
        skipList1.insert(10);
        skipList1.insert(19);
        skipList1.insert(22);
        skipList1.insert(3);
        skipList1.insert(3);
        skipList1.insert(60);
        skipList1.insert(99);
        skipList1.insert(29);
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList1.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing isEmpty -----------");
        System.out.println(skipList1.isEmpty());
        System.out.println("-------- Testing Search -----------");
        if (skipList1.search(60) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(60).key);
        }
        if (skipList1.search(55) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(55).key);
        }
        if (skipList1.search(5) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(5).key);
        }
        if (skipList1.search(19) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(19).key);
        }
        if (skipList1.search(22) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(22).key);
        }
        if (skipList1.search(3) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(3).key);
        }
        if (skipList1.search(99) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(99).key);
        }
        if (skipList1.search(67) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(67).key);
        }
        if (skipList1.search(61) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(61).key);
        }
        if (skipList1.search(6) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(6).key);
        }
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList1.toString());
        System.out.println("------------ Testing the Delete ---------------\n");
        if (skipList1.delete(3) == true) {
            System.out.println("Deleted 3 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        if (skipList1.delete(17) == true) {
            System.out.println("Deleted 17 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        if (skipList1.delete(3) == true) {
            System.out.println("Deleted 3 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing Search -----------");
        if (skipList1.search(3) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList1.search(3).key);
        }
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList1.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing printSearchPath -----------");
        System.out.print("Search Path 55: ");
        skipList1.printSearchPath(55);
        System.out.print("Search Path 5: ");
        skipList1.printSearchPath(5);
        System.out.print("Search Path 19: ");
        skipList1.printSearchPath(19);
        System.out.print("Search Path 22: ");
        skipList1.printSearchPath(22);
        System.out.print("Search Path 34: ");
        skipList1.printSearchPath(34);
        System.out.println("\n-------------------- DONE ------------------------");
        System.out.println();
    }

    public static void mainTest3() {
        System.out.println("----------- NOW STARTING WITH BOUNDS CHECKING -------------------- ");
        System.out.println("1. SKIPLIST OF MAX-LEVEL 0");
        SkipList<Integer> skipList0 = new SkipList<Integer>(0);
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList0.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing the Insert -----------");
        skipList0.insert(5);
        skipList0.insert(55);
        skipList0.insert(10);
        skipList0.insert(19);
        skipList0.insert(22);
        skipList0.insert(3);
        skipList0.insert(3);
        skipList0.insert(60);
        skipList0.insert(99);
        skipList0.insert(29);
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList0.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing isEmpty -----------");
        System.out.println(skipList0.isEmpty());
        System.out.println("-------- Testing Search -----------");
        if (skipList0.search(60) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(60).key);
        }
        if (skipList0.search(55) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(55).key);
        }
        if (skipList0.search(5) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(5).key);
        }
        if (skipList0.search(19) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(19).key);
        }
        if (skipList0.search(22) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(22).key);
        }
        if (skipList0.search(3) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(3).key);
        }
        if (skipList0.search(99) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(99).key);
        }
        if (skipList0.search(67) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(67).key);
        }
        if (skipList0.search(61) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(61).key);
        }
        if (skipList0.search(6) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(6).key);
        }
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList0.toString());
        System.out.println("------------ Testing the Delete ---------------\n");
        if (skipList0.delete(3) == true) {
            System.out.println("Deleted 3 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        if (skipList0.delete(17) == true) {
            System.out.println("Deleted 17 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        if (skipList0.delete(3) == true) {
            System.out.println("Deleted 3 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing Search -----------");
        if (skipList0.search(3) == null) {
            System.out.println("Value doesn't exists!");
        } else {
            System.out.println("Value exists: " + skipList0.search(3).key);
        }
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("------------ Testing the toString Function ---------------\n");
        System.out.println(skipList0.toString());
        System.out.println("-------------------- DONE ------------------------");
        System.out.println("-------- Testing printSearchPath -----------");
        System.out.print("Search Path 55: ");
        skipList0.printSearchPath(55);
        System.out.print("Search Path 5: ");
        skipList0.printSearchPath(5);
        System.out.print("Search Path 19: ");
        skipList0.printSearchPath(19);
        System.out.print("Search Path 22: ");
        skipList0.printSearchPath(22);
        System.out.print("Search Path 34: ");
        skipList0.printSearchPath(34);
        System.out.println("\n-------------------- DONE ------------------------");
        System.out.println();
    }

    public static void mainTest5() {
        SkipList<Integer> skipList = new SkipList<Integer>(4);
        System.out.println("------------ The SkipLIst ---------------\n");
        skipList.insert(5);
        skipList.insert(3);
        skipList.insert(55);
        skipList.insert(10);
        skipList.insert(10);
        skipList.insert(29);
        skipList.insert(29);
        skipList.insert(10);
        skipList.insert(10);
        skipList.insert(19);
        skipList.insert(5);
        skipList.insert(22);
        skipList.insert(99);
        skipList.insert(3);
        skipList.insert(3);
        skipList.insert(3);
        skipList.insert(60);
        skipList.insert(29);
        System.out.println(skipList.toString());
        System.out.println("---------------------------------------");
        System.out.println("------------ Testing search ---------------\n");
        if (skipList.delete(10) == true) {
            System.out.println("Deleted 3 successfully");
        } else {
            System.out.println("Value doesn't exists!");
        }
        // System.out.println("---------------------------------------");
        // if(skipList.delete(10) == true){
        // System.out.println("Deleted 3 successfully");
        // }
        // else{
        // System.out.println("Value doesn't exists!");
        // }
        // System.out.println("--------------- SKIPLIST --------------");
        // System.out.println(skipList.toString());
        // System.out.println("---------------------------------------");
        // if(skipList.delete(5) == true){
        // System.out.println("Deleted 3 successfully");
        // }
        // else{
        // System.out.println("Value doesn't exists!");
        // }
        // System.out.println("--------------- SKIPLIST --------------");
        // System.out.println(skipList.toString());
        // System.out.println("---------------------------------------");
        // if(skipList.delete(55) == true){
        // System.out.println("Deleted 3 successfully");
        // }
        // else{
        // System.out.println("Value doesn't exists!");
        // }
        // System.out.println("--------------- SKIPLIST --------------");
        // System.out.println(skipList.toString());
        // System.out.println("---------------------------------------");
    }

    public static void main(String[] args) {
        mainTest1();
        // mainTest2();
        // mainTest3();
        // mainTest4();
        // mainTest5();
    }
}