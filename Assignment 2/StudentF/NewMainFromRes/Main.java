

public class Main {

    public static void main(String[] args) {
       task1();
        task2();
        task3();
       //task4();
       task6();
       task7();
       task8();
    }
    public static void task8()
    {
        
  

    }
    public static void task7()
    {
        Treap<Integer> tree= new Treap<Integer>();
        try{
            tree.insert(5);
            tree.insert(52);
            System.out.println(tree.toString());
            tree.insert(15);
            System.out.println(tree.toString());
            tree.insert(552);
            System.out.println(tree.toString());
            tree.insert(58);
            System.out.println(tree.toString());
            tree.insert(572);
            System.out.println(tree.toString());
            tree.insert(59);
            System.out.println(tree.toString());
            
            tree.insert(339);
            System.out.println(tree);
            
        }
        catch(DatabaseException dBeException)
        {
            System.out.println(dBeException.toString());
        }

    }
    public static void task6()
    {
        Treap<Integer> tree= new Treap<Integer>();
        try{
        tree.insert(5);
        tree.insert(6);
        tree.insert(0);
        tree.insert(51);
        tree.insert(7);
        tree.insert(10);
        tree.insert(20);
        tree.insert(535);
        tree.insert(15);
        tree.insert(75);
        System.out.println(tree);
        tree.remove(20);
        tree.remove(6);
        tree.remove(15);
        tree.remove(5);
        tree.remove(0);
       tree.remove(75);
       tree.remove(535);
       tree.remove(7);
       tree.remove(51);
       tree.remove(10);
       tree.insert(71);
       System.out.println(tree);
       tree.insert(66);
       System.out.println(tree);
       tree.remove(66);
       System.out.println(tree);
       
       
        }
        catch(DatabaseException e)
        {
            System.out.println(e.toString());
        }



    }
    public static void task5()
    {
   
    }
    public static void task3()
    {
        try{
        Treap<Integer> tree= new Treap<>();
        System.out.println(tree.toString());
        System.out.println(tree.access(25));
        System.out.println(tree.remove(26));
        tree.insert(25);
        System.out.println(tree.toString());
        for(int c=0; c<10; c++)
        {
            tree.insert(c);
        }
            try{
                tree.insert(5);
            }
            catch(DatabaseException e)
            {
                System.out.println(e);
            }
        System.out.println(tree.toString());
        tree.remove(5);
        System.out.println(tree.toString());





        }
        catch(DatabaseException e)
        {
            System.out.println(e);
        }
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
    }

    public static void task2() {
        /*
         * Note that we also want you to create your own main for this task.
         * 
         * It takes a while to set the DB up, so an example is given below, feel free to
         * change it to test the rest of the functions
         */
        String[] columns = { "Module Code", "Description", "Credits", "Year", "Session" };
        Database db = new Database(columns, 100);

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
            for (String[] mod : modules) {
                db.insert(mod);
            }

            db.generateIndexAll();
        } catch (DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (String[] row : db.database) {
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

        System.out.println(db.indexes[0]);
        System.out.println(db.indexes[1]);
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
/*
 LST110  Language and study skills 110                                              6    1       Sem 1
WTW124  Mathematics 124                                                            16   1       Sem 2
UP0102  Academic orientation 102                                                   0    1       Year
WTW114  Calculus 114                                                               16   1       Sem 1
WTW123  Numerical analysis 123                                                     8    1       Sem 2
PHY114  First course in physics 114                                                16   1       Sem 1
PHY124  First course in physics 124                                                16   1       Sem 2
AIM102  Academic information management 102                                        6    1       Sem 2
COS122  Operating systems 122                                                      16   1       Sem 2
COS132  Imperative programming 132                                                 16   1       Sem 1
COS110  Program design: Introduction 110                                           16   1       Sem 2
COS151  Introduction to computer science 151                                       8    1       Sem 1
COS212  Data structures and algorithms 212                                         16   2       Sem 1
COS226  Concurrent systems 226                                                     16   2       Sem 2
COS284  Computer organisation and architecture 284                                 16   2       Sem 2
COS210  Theoretical computer science 210                                           8    2       Sem 1
WTW248  Vector analysis 248                                                        12   2       Sem 2
PHY255  Waves, thermodynamics and modem physics 255                                24   2       Sem 1
PHY263  General physics 263                                                        24   2       Sem 2
WTW211  Linear algebra 211                                                         12   2       Sem 1
WTW218  Calculus 218                                                               12   2       Sem 1
WTW220  Analysis 220                                                               12   2       Sem 2
COS314  Artificial intelligence 314                                                18   3       Sem 1
COS330  Computer security and ethics 330                                           18   3       Sem 2
COS333  Programming languages 333                                                  18   3       Sem 2
COS344  Computer graphics 344                                                      18   3       Sem 1
PHY310  Particle and astroparticle physics 310                                     18   3       Sem 2
PHY356  Electronics, electromagnetism and quantum mechanics 356                    36   3       Sem 1
PHY364  Statistical mechanics, solid state physics and modelling 364               36   3       Sem 2
COS711  Artificial Intelligence (II) 711                                           15   4       Sem 2
FSK700  Physics 700                                                                135  4       Year
COS344{25}[121]
├(L)─ COS122{8}[95]
|    ├(L)─ AIM102{7}[54]
|    |    └(R)─ COS110{10}[46]
|    └(R)─ COS314{22}[72]
|       ├(L)─ COS284{14}[62]
|       |    └(L)─ COS151{11}[62]
|       |         ├(L)─ COS132{9}[55]
|       |         └(R)─ COS210{15}[61]
|       |            └(R)─ COS212{12}[60]
|       |               └(R)─ COS226{13}[2]
|       └(R)─ COS330{23}[52]
|          └(R)─ COS333{24}[24]
└(R)─ PHY124{6}[114]
   ├(L)─ FSK700{30}[85]
   |    ├(L)─ COS711{29}[34]
   |    └(R)─ LST110{0}[84]
   |       └(R)─ PHY114{5}[27]
   └(R)─ PHY364{28}[109]
      ├(L)─ PHY356{27}[76]
      |    └(L)─ PHY310{26}[63]
      |         └(L)─ PHY263{18}[31]
      |              └(L)─ PHY255{17}[15]
      └(R)─ WTW124{1}[97]
         ├(L)─ UP0102{2}[58]
         |    └(R)─ WTW123{4}[49]
         |       └(L)─ WTW114{3}[32]
         └(R)─ WTW211{19}[81]
            └(R)─ WTW220{21}[64]
               ├(L)─ WTW218{20}[35]
               └(R)─ WTW248{16}[14]

Artificial Intelligence (II) 711{29}[118]
├(L)─ Academic information management 102{7}[65]
|    └(R)─ Academic orientation 102{2}[64]
|       └(R)─ Analysis 220{21}[39]
└(R)─ Particle and astroparticle physics 310{26}[117]
   ├(L)─ Imperative programming 132{9}[113]
   |    ├(L)─ First course in physics 114{5}[112]
   |    |    ├(L)─ Computer organisation and architecture 284{14}[91]
   |    |    |    ├(L)─ Artificial intelligence 314{22}[51]
   |    |    |    |    └(R)─ Computer graphics 344{25}[48]
   |    |    |    |       └(L)─ Calculus 114{3}[29]
   |    |    |    |            └(R)─ Calculus 218{20}[4]
   |    |    |    └(R)─ Data structures and algorithms 212{12}[89]
   |    |    |       ├(L)─ Concurrent systems 226{13}[75]
   |    |    |       |    └(L)─ Computer security and ethics 330{23}[58]
   |    |    |       └(R)─ Electronics, electromagnetism and quantum mechanics 356{27}[54]
   |    |    └(R)─ General physics 263{18}[71]
   |    |       └(L)─ First course in physics 124{6}[15]
   |    └(R)─ Operating systems 122{8}[111]
   |       └(L)─ Introduction to computer science 151{11}[91]
   |            └(R)─ Mathematics 124{1}[76]
   |               ├(L)─ Linear algebra 211{19}[73]
   |               |    └(L)─ Language and study skills 110{0}[61]
   |               └(R)─ Numerical analysis 123{4}[62]
   └(R)─ Statistical mechanics, solid state physics and modelling 364{28}[91]
      ├(L)─ Physics 700{30}[90]
      |    └(R)─ Programming languages 333{24}[57]
      |       └(L)─ Program design: Introduction 110{10}[10]
      └(R)─ Waves, thermodynamics and modem physics 255{17}[91]
         └(L)─ Vector analysis 248{16}[87]
              └(L)─ Theoretical computer science 210{15}[57]


null
null
25[46]

Duplicate insert of:5
3[121]
├(L)─ 2[55]
|    └(L)─ 1[2]
|         └(L)─ 0[1]
└(R)─ 9[113]
   ├(L)─ 8[112]
   |    └(L)─ 4[55]
   |         └(R)─ 5[49]
   |            └(R)─ 7[23]
   |               └(L)─ 6[21]
   └(R)─ 25[46]

3[121]
├(L)─ 2[55]
|    └(L)─ 1[2]
|         └(L)─ 0[1]
└(R)─ 9[113]
   ├(L)─ 8[112]
   |    └(L)─ 4[55]
   |         └(R)─ 7[23]
   |            └(L)─ 6[21]
   └(R)─ 25[46]

51[123]
├(L)─ 7[94]
|    ├(L)─ 5[80]
|    |    ├(L)─ 0[5]
|    |    └(R)─ 6[15]
|    └(R)─ 10[65]
|       └(R)─ 20[54]
|          └(L)─ 15[49]
└(R)─ 535[78]
   └(L)─ 75[77]

71[22]

66[118]
└(R)─ 71[22]

71[22]

52[35]
└(L)─ 5[32]

52[35]
└(L)─ 5[32]
     └(R)─ 15[6]

552[90]
└(L)─ 52[35]
     └(L)─ 5[32]
          └(R)─ 15[6]

552[90]
└(L)─ 52[35]
     ├(L)─ 5[32]
     |    └(R)─ 15[6]
     └(R)─ 58[0]

552[90]
├(L)─ 52[35]
|    ├(L)─ 5[32]
|    |    └(R)─ 15[6]
|    └(R)─ 58[0]
└(R)─ 572[66]

59[115]
├(L)─ 52[35]
|    ├(L)─ 5[32]
|    |    └(R)─ 15[6]
|    └(R)─ 58[0]
└(R)─ 552[90]
   └(R)─ 572[66]

339[120]
├(L)─ 59[115]
|    └(L)─ 52[35]
|         ├(L)─ 5[32]
|         |    └(R)─ 15[6]
|         └(R)─ 58[0]
└(R)─ 552[90]
   └(R)─ 572[66]

 */