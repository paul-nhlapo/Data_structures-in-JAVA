public class Main {
    public static Database db;
    public static String[] columns;

    public static void main(String[] args) {
        // task1();
        task2();
        // * DO NOT TEST THEM ALL AT ONCE!!!
        // System.out.println("---------- NOW TESTING THE DATABASE QUERIES
        // -----------");
        // System.out.println("-------------------------------------------------------");
        CountMethod();
        FindMethod();
        UpdateMethod();
        RemoveMethod();
        TestingThe_AllWhere_Methods();
    }

    public static void task1() {
        /*
         * You are not given a Main for this task, because we want you to figure out how
         * to do it for yourself.
         * 
         * You are provided with a validTreap() function which will print out valid or
         * invalid for a passed in Treap.
         * 
         * Use this function to make sure that your heaps follow the rules set by the
         * Assignment.
         * 
         * Tip : Create a Main that inserts / deletes a lot of elements and call
         * validTreap after every step
         */
        // Here test the treap class
        Treap<Integer> treap = new Treap<>();
        try {
            treap.insert(3);
            treap.insert(6);
            treap.insert(1);
            treap.insert(8);
            treap.insert(2);
            treap.insert(9);
            treap.insert(4);
            treap.insert(7);
            treap.insert(5);
            treap.insert(10);
            // treap.insert(5);
            treap.insert(10);
            // for(int i = 1; i <= 15; i++) treap.insert(i);
        } catch (DatabaseException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }

        System.out.println(treap + "\n");
        String flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        System.out.println("----------------Test the remove function of the treap----------------" + "\n");
        Node<Integer> nodeToRemove = treap.access(8);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(7);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(6);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(4);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(3);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        System.out.println(
                "----------------Test the remove function of the treap with non-existant node----------------" + "\n");
        nodeToRemove = treap.access(200);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(110);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(190);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(2112);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(1199);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(1897);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        System.out.println("----------------Test the access function of the treap----------------" + "\n");
        Node<Integer> nodeToAccess = treap.access(10);
        if (nodeToAccess != null) {
            Node<Integer> accessedNode = treap.access(nodeToAccess.getData());
            System.out.println("Accessed node: " + accessedNode.getData());
        }
        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        System.out.println("Accessed node: " + treap.access(15));

        System.out.println(
                "----------------Test the access function of the treap with non-existant node----------------" + "\n");

        System.out.println("Accessed node: " + treap.access(200));
        System.out.println("Accessed node: " + treap.access(110));
        System.out.println("Accessed node: " + treap.access(190));
        System.out.println("Accessed node: " + treap.access(2112));
        System.out.println("Accessed node: " + treap.access(1199));

        System.out.println("----------------Test the remove function of the treap----------------" + "\n");
        nodeToRemove = treap.access(5);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(10);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(9);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(2);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(1);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");

        nodeToRemove = treap.access(7);
        if (nodeToRemove != null) {
            treap.remove(nodeToRemove.getData());
        }

        System.out.println(treap + "\n");
        flag = validTreap(treap);
        System.out.println("Is the treap Valid or not: " + flag + "\n");
    }

    public static <T extends Comparable<T>> void FindMethod() {
        Database db_back = db;
        // PrintDB(db.database);
        // System.out.println(db.indexes[0]);
        // System.out.println(db.indexes[1]);
        System.out.println("--------- Testing findFirstWhere(col, data) -----------");
        try {
            PrintDB(db.findFirstWhere("Descriptio", "Calculus 114"));
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.findFirstWhere("Description", "Calculus 114"));
            // System.out.println(db.indexes[1]);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.findFirstWhere("Module Code", "COS212"));
            // System.out.println(db.indexes[1]);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.findFirstWhere("Module Code", "COS212@"));
            // System.out.println(db.indexes[1]);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        // PrintDB(db.database);
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static <T extends Comparable<T>> void UpdateMethod() {
        Database db_back = db;
        System.out.println("------------ Printing the Indexes Arrays --------------");
        System.out.println("-- Module Codes Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[0]);
        System.out.println("-- Description Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[1]);
        System.out.println("-------------------------------------------------------");
        System.out.println("--------------- Printing The Database -----------------");
        PrintDB(db.database);
        System.out.println("-------------------------------------------------------\n\n");
        System.out.println("--- Testing updateFirstWhere(col, updateCon, data) ----");
        try {
            PrintDB(db.updateFirstWhere("Descriptio", "Calculus 114", "Khalkhulas 114"));
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.updateFirstWhere("Description", "Calculus 114", "Differentiation"));
            PrintDB(db.updateFirstWhere("Description", "Artificial Intelligence (II) 711", "GPT-4 Open AI"));
            PrintDB(db.updateFirstWhere("Description", "Imperative programming 132", "Procedural Programming"));
            PrintDB(db.updateFirstWhere("Description", "Operating systems 122", "Windows For Example"));
            PrintDB(db.updateFirstWhere("Module Code", "COS212", "POS800"));
            // System.out.println(db.indexes[1]);
            // PrintDB(db.database);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.updateFirstWhere("Credits", "16", "50"));
            // System.out.println(db.indexes[0]);
            // PrintDB(db.database);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.updateFirstWhere("Module Code", "COS212-2", "Assignment is Not Allowed"));
            // System.out.println(db.indexes[0]);
            // PrintDB(db.database);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("\n\n------------ Printing the Indexes Arrays --------------");
        System.out.println("-- Module Codes Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[0]);
        System.out.println("-- Description Treap<Cell>: --");
        System.out.println("-------------------------------------------------------");
        System.out.println(db.indexes[1]);
        System.out.println("-------------------------------------------------------");
        System.out.println("--------------- Printing The Database -----------------");
        PrintDB(db.database);
        System.out.println("-------------------------------------------------------");
        // System.out.println(db.indexes[1].contains(new Cell(2, "Calculus 114")));
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static <T extends Comparable<T>> void CountMethod() {
        Database db_back = db;
        System.out.println("--------- Testing countOccurrences(col, data) ---------");
        try {
            System.out.println("Count 'Calculus 114': " + db.countOccurences("Dscription", "Calculus 114"));
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            System.out.println("Count 'Calculus 114': " + db.countOccurences("Description", "Calculus 114"));
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            System.out.println("Count 'Mathematics 124': " + db.countOccurences("Description", "Mathematics 124"));
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            System.out.println("Count 'COS212': " + db.countOccurences("Module Code", "COS212"));
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            System.out.println("Count 'COS132': " + db.countOccurences("Module Code", "COS132"));
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        PrintDB(db.database);
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static <T extends Comparable<T>> void TestingThe_AllWhere_Methods() {
        System.out.println("------- Testing TestingThe_AllWhere_Methods(col, data) -----------");
        Database db_back = db;
        // System.out.println("--------- Testing removeAllWhere(col, data) ---------");
        // try{
        // PrintDB(db.removeAllWhere("Credits", "16"));
        // // System.out.println(db.indexes[1]);
        // }
        // catch(DatabaseException e){
        // System.out.println(e);
        // }
        // db = db_back;
        System.out.println("--- Testing updateAllWhere(col, updateCon, data) ----");
        try {
            PrintDB(db.updateFirstWhere("Description", "Mathematics 124", "Khalkhulas 124"));
            System.out.println("--------------------- The Indexes Array ---------------------");
            System.out.println(db.indexes[1]);
        } catch (DatabaseException e) {
            System.out.println(e);
        }

        // System.out.println("--- Testing updateAllWhere(col, updateCon, data) ----");
        // try{
        // PrintDB(db.updateAllWhere("Credits", "16", "27"));
        // System.out.println("--------------------- The Indexes Array
        // ---------------------");
        // System.out.println(db.indexes[1]);
        // }
        // catch(DatabaseException e){
        // System.out.println(e);
        // }
        // db = db_back;

        // db = db_back;
        // System.out.println("--- Testing findAllWhere(col, updateCon, data) ----");
        // try{
        // PrintDB(db.findAllWhere("Description", "Mathematics 124"));
        // }
        // catch(DatabaseException e){
        // System.out.println(e);
        // }
        // db = db_back;
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static <T extends Comparable<T>> void RemoveMethod() {
        Database db_back = db;
        System.out.println("--------- Testing removeFirstWhere(col, data) ---------");
        try {
            PrintDB(db.removeFirstWhere("Descriptio", "Calculus 114"));
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.removeFirstWhere("Description", "Calculus 114"));
            // System.out.println(db.indexes[1]);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.removeFirstWhere("Module Code", "COS212"));
            // System.out.println(db.indexes[1]);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.removeFirstWhere("Credits", "16"));
            // System.out.println(db.indexes[1]);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        try {
            PrintDB(db.removeFirstWhere("Module Code", "COS212@"));
            // System.out.println(db.indexes[1]);
        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------");
        PrintDB(db.database);
        System.out.println("-------------------- D O N E --------------------------");
        db = db_back;
    }

    public static void PrintDB(String[][] database) {
        if (database.length == 0) {
            System.out.println("Empty Database");
        } else {
            for (String[] row : database) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void PrintDB(String[] database) {
        if (database.length == 0) {
            System.out.println("Empty Database");
        } else {
            int c = 0;
            for (String s : database) {
                if (c++ == 1) {
                    System.out.print(String.format("%1$-75s", s));
                } else {
                    System.out.print(s + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void task2() {
        /*
         * Note that we also want you to create your own main for this task.
         * 
         * It takes a while to set the DB up, so an example is given below, feel free to
         * change it to test the rest of the functions
         */
        String[] columns = { "Module Code", "Description", "Credits", "Year", "Session" };
        System.out.println("----------- Testing The Database Constructor ----------");
        db = new Database(columns, 100);
        System.out.println("---------------------- D O N E ------------------------");
        String[][] modules = {
                { "LST110", "Language and study skills 110", "6", "1", "Sem 1" },
                { "WTW124", "Mathematics 124", "16", "1", "Sem 2" },
                { "UP0102", "Academic orientation 102", "0", "1", "Year" },
                { "WTW114", "Calculus 114", "16", "1", "Sem 1" },
                { "WTW123", "Numerical analysis 123", "8", "1", "Sem 2" },
                { "PHY114", "First course in physics 114", "16", "1", "Sem 1" },
                { "PHY124", "First course in physics 124", "16", "1", "Sem 2" },
                { "AIM102", "Academic information management 102", "6", "1", "Sem 2" },
                { "COS122", "Operating systems 122", "16", "1", "Sem 2" },
                { "COS132", "Imperative programming 132", "16", "1", "Sem 1" },
                { "COS110", "Program design: Introduction 110", "16", "1", "Sem 2" },
                { "COS151", "Introduction to computer science 151", "8", "1", "Sem 1" },
                { "COS212", "Data structures and algorithms 212", "16", "2", "Sem 1" },
                { "COS226", "Concurrent systems 226", "16", "2", "Sem 2" },
                { "COS284", "Computer organisation and architecture 284", "16", "2", "Sem 2" },
                { "COS210", "Theoretical computer science 210", "8", "2", "Sem 1" },
                { "WTW248", "Vector analysis 248", "12", "2", "Sem 2" },
                { "PHY255", "Waves, thermodynamics and modem physics 255", "24", "2", "Sem 1" },
                { "PHY263", "General physics 263", "24", "2", "Sem 2" },
                { "WTW211", "Linear algebra 211", "12", "2", "Sem 1" },
                { "WTW218", "Calculus 218", "12", "2", "Sem 1" },
                { "WTW220", "Analysis 220", "12", "2", "Sem 2" },
                { "COS314", "Artificial intelligence 314", "18", "3", "Sem 1" },
                { "COS330", "Computer security and ethics 330", "18", "3", "Sem 2" },
                { "COS333", "Programming languages 333", "18", "3", "Sem 2" },
                { "COS344", "Computer graphics 344", "18", "3", "Sem 1" },
                { "PHY310", "Particle and astroparticle physics 310", "18", "3", "Sem 2" },
                { "PHY356", "Electronics, electromagnetism and quantum mechanics 356", "36", "3", "Sem 1" },
                { "PHY364", "Statistical mechanics, solid state physics and modelling 364", "36", "3", "Sem 2" },
                { "COS711", "Artificial Intelligence (II) 711", "15", "4", "Sem 2" },
                { "FSK700", "Physics 700", "135", "4", "Year" }
        };

        try {
            System.out.println("------------ Testing The Insert Function --------------");
            for (String[] mod : modules) {
                db.insert(mod);
            }
            System.out.println("---------------------- D O N E ------------------------");
            // System.out.println("-- Testing generateIndexAll() & generateIndexOn(String
            // col) --");
            db.generateIndexAll();
            // System.out.println("---------------------- D O N E
            // ------------------------");
        } catch (DatabaseException e) {
            System.out.println("Error: " + e);
        }
        // System.out.println("------------- Now Printing The Database
        // ---------------");
        // PrintDB(db.database);
        // System.out.println("---------------------- D O N E
        // ------------------------");
        // System.out.println("------------ Printing the Indexes Arrays
        // --------------");
        // System.out.println("-- Module Codes Treap<Cell>: --");
        // System.out.println("-------------------------------------------------------");
        // System.out.println(db.indexes[0]);
        // System.out.println("-- Description Treap<Cell>: --");
        // System.out.println("-------------------------------------------------------");
        // System.out.println(db.indexes[1]);
        // System.out.println("---------------------- D O N E
        // ------------------------");
        // System.out.println("-------------------------------------------------------");
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static <T extends Comparable<T>> String validTreap(Treap<T> t) {
        return (validTreap(t.root) ? ANSI_GREEN + "Valid\n" + ANSI_RESET : ANSI_RED + "Invalid\n" + ANSI_RESET);
    }

    public static <T extends Comparable<T>> boolean validTreap(Node<T> n) {
        if (n == null) {
            return true;
        }

        if (n.left != null && (n.left.priority > n.priority || n.getData().compareTo(n.left.getData()) < 0)) {
            return false;
        }

        if (n.right != null && (n.right.priority > n.priority || n.getData().compareTo(n.right.getData()) > 0)) {
            return false;
        }

        if (!validTreap(n.left)) {
            return false;
        }

        if (!validTreap(n.right)) {
            return false;
        }

        return true;
    }
}