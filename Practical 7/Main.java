/* Hi everyone this is the main for Prac 7
 * 
 * This one might be my best one yet as I have included a way to output the graph for two different visualizer sites.
 * If you want to use it on a graph of you own making just make sure to follow the method that I made the edges 2D array
 * 
 * If you don't want to see the stuff for the visualizer change the visualizer boolean to false
 * 
 * Be aware some of the visualizers don't work for negative weights so just change the negatives boolean to false, also that only works for the graphs I made
 * Negatives is off by default
 * 
 * This main first outputs the original main and then adds on my own tests which all of their own description
 * 
 * You don't need to add anything to any of your other files for this to work
 * 
 * As usual these mains take extremely long to make so any support would be very welcome
 * 
 * My paypal is: 
 * https://www.paypal.me/ChuufMaster
 * 
 * 
 */

import java.util.Random;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static boolean visualizer = true;
    public static boolean negatives = false;

    public static void main(String[] args) {

        og_test();

        CYAN();
        print("\n\nTest 1: Testing for proper edge case handling for empty graph: ____________________________________________________________________________________________________________________________________________________________________________________________\n");
        RESET();
        test_1();

        CYAN();
        print("\n\nTest 2: Testing if your graph works on a fully connected graph: ____________________________________________________________________________________________________________________________________________________________________________________________\n");
        RESET();
        test_2();

        CYAN();
        print("\n\nTest 3: Testing for if your graph works for a disconnected graph: ____________________________________________________________________________________________________________________________________________________________________________________________\n");
        RESET();
        test_3();

        CYAN();
        print("\n\nTest 4: Testing for if your graph works for a larger graph: ____________________________________________________________________________________________________________________________________________________________________________________________\n");
        RESET();
        test_4();

        CYAN();
        print("\n\nTest 5: Testing your remove Vertex and Remove Edge : ____________________________________________________________________________________________________________________________________________________________________________________________\n");
        RESET();
        test_5();

        CYAN();
        print("\n\nTest 6: Testing for if your graph works for a fully disconnected graph : ____________________________________________________________________________________________________________________________________________________________________________________________\n");
        RESET();
        test_6();

        CYAN();
        print("\n\nTest 7: Testing for if your graph works for a fully connected graph with negative weights : ____________________________________________________________________________________________________________________________________________________________________________________________\n");
        RESET();
        test_7();

        RNJesus();
    }

    private static void og_test() {
        Graph g = new Graph();

        String[] verts = { "a", "b", "c", "d", "e", "f", "g", "h" };
        String[][] edges = { { "a", "e", "1" },
                { "a", "f", "-1" },
                { "a", "g", "2" },
                { "b", "e", "-2" },
                { "b", "c", "3" },
                { "b", "h", "-3" },
                { "c", "g", "4" },
                { "d", "f", "-4" },
                { "d", "g", "5" },
                { "f", "g", "-5" },
                { "f", "h", "6" },
                { "g", "h", "-6" } };

        for (String s : verts) {
            g.addVertex(s);
        }

        for (String[] e : edges) {
            g.addEdge(e[0], e[1], Integer.valueOf(e[2]));
        }

        System.out.println("---\ntoString\n---\n" + g);

        System.out.println("---\nunionFind\n---");
        int[][] unionFindRes = g.unionFind();
        for (int r = 0; r < unionFindRes.length; r++) {
            for (int c = 0; c < unionFindRes[r].length; c++) {
                System.out.print(unionFindRes[r][c] + "\t");
            }
            System.out.println();
        }
        System.out.println("---\nminSpanningTree\n---");
        System.out.println(g.minSpanningTree());
        System.out.println("---\ncolouring\n---");
        Vertex[][] colourRes = g.brelazColouring();
        print("");
        for (int r = 0; r < colourRes.length; r++) {
            for (int c = 0; c < colourRes[r].length; c++) {
                System.out.print(colourRes[r][c] + "\t");
            }
            System.out.println();
        }
    }

    private static void test_1() {
        Graph test = new Graph();
        test.removeEdge("a", "b");
        test.removeVertex("a");
        tests(test, new String[][] {}, new String[] {});
    }

    private static void test_2() {
        String[] verts = new String[] { "a", "b", "c", "d", "e" };
        String[][] edges = new String[][] {
                { "a", "b", "1" },
                { "a", "c", "2" },
                { "a", "d", "3" },
                { "a", "e", "4" },

                { "b", "c", "5" },
                { "b", "d", "6" },
                { "b", "e", "7" },

                { "c", "d", "8" },
                { "c", "e", "9" },

                { "d", "e", "10" }
        };

        Graph test = make_graph(verts, edges);
        tests(test, edges, verts);
        //print_matrix_2D(matrix(test));

    }

    private static void test_3() {
        String[] verts = new String[] {
                "a", "b", "c", "d", "e", "f"
        };

        String[][] edges = new String[][] {
                { "a", "b", "1" },
                { "b", "c", "2" },
                { "d", "e", "3" },
                { "e", "f", "4" },
                //{ "c", "d", "5" },
        };

        Graph test = make_graph(verts, edges);
        tests(test, edges, verts);
    }

    private static void test_4() {
        String[] verts = new String[20];
        String[][] edges = new String[30][3];
        for (int index = 1; index <= 20; index++) {
            if ((index + "").length() == 1)
                verts[index - 1] = "0" + index + "";
            else
                verts[index - 1] = index + "";
        }

        for (int row = 0; row < 30; row++) {
            if (row < 18) {
                edges[row][0] = ((row + 1) + "").length() == 1 ? "0" + (row + 1) + "" : (row + 1) + "";
                edges[row][1] = ((row + 2) + "").length() == 1 ? "0" + (row + 2) + "" : (row + 2) + "";
            } else {
                edges[row][0] = (((row - 18) + 1) + "").length() == 1 ? "0" + ((row - 18) + 1) + ""
                        : ((row - 18) + 1) + "";
                edges[row][1] = (((row - 18) + 9) + "").length() == 1 ? "0" + ((row - 18) + 9) + ""
                        : ((row - 18) + 9) + "";
            }
            edges[row][2] = (negatives ? (((row + 1) % 2) == 0 ? 1 : -1) : 1) * (row + 1) + "";
        }

        Graph test = make_graph(verts, edges);
        tests(test, edges, verts);

    }

    private static void test_5() {
        String[] verts = new String[20];
        String[][] edges = new String[30][3];
        for (int index = 1; index <= 20; index++) {
            if ((index + "").length() == 1)
                verts[index - 1] = "0" + index + "";
            else
                verts[index - 1] = index + "";
        }

        for (int row = 0; row < 30; row++) {
            if (row < 18) {
                edges[row][0] = ((row + 1) + "").length() == 1 ? "0" + (row + 1) + "" : (row + 1) + "";
                edges[row][1] = ((row + 2) + "").length() == 1 ? "0" + (row + 2) + "" : (row + 2) + "";
            } else {
                edges[row][0] = (((row - 18) + 1) + "").length() == 1 ? "0" + ((row - 18) + 1) + ""
                        : ((row - 18) + 1) + "";
                edges[row][1] = (((row - 18) + 9) + "").length() == 1 ? "0" + ((row - 18) + 9) + ""
                        : ((row - 18) + 9) + "";
            }
            edges[row][2] = (negatives ? (((row + 1) % 2) == 0 ? 1 : -1) : 1) * (row + 1) + "";
        }

        Graph test = make_graph(verts, edges);

        Graph vert_first = delete_graph_vertex_first(verts, edges);
        Graph edge_first = delete_graph_edge_first(verts, edges);
        print(test);
        print(vert_first);
        print(edge_first);
    }

    private static void test_6() {
        String[] verts = new String[] {
                "a", "b", "c", "d", "e", "f", "g", "e"
        };
        String[][] edges = new String[0][0];

        Graph test = make_graph(verts, edges);
        tests(test, edges, verts);
    }

    private static void test_7() {

        RED();
        print("This graph cannot switch between negative and not negative");
        RESET();

        String[] verts = new String[] { "a", "b", "c", "d", "e" };
        String[][] edges = new String[][] {
                { "a", "b", "-1" },
                { "a", "c", "-2" },
                { "a", "d", "-3" },
                { "a", "e", "-4" },

                { "b", "c", "-5" },
                { "b", "d", "-6" },
                { "b", "e", "-7" },

                { "c", "d", "-8" },
                { "c", "e", "-9" },

                { "d", "e", "-10" }
        };

        Graph test = make_graph(verts, edges);
        tests(test, edges, verts);
        //print_matrix_2D(matrix(test));

    }

    private static Graph make_graph(String[] verts, String[][] edges) {
        Graph graph = new Graph();
        for (String s : verts) {
            graph.addVertex(s);
        }

        for (String[] e : edges) {
            graph.addEdge(e[0], e[1], Integer.valueOf(e[2]));
        }
        return graph;
    }

    private static Graph delete_graph_vertex_first(String[] verts, String[][] edges) {
        Graph graph = make_graph(verts, edges);
        for (String s : verts) {
            graph.removeVertex(s);
        }

        for (String[] e : edges) {
            graph.removeEdge(e[0], e[1]);
        }
        return graph;
    }

    private static Graph delete_graph_edge_first(String[] verts, String[][] edges) {
        Graph graph = make_graph(verts, edges);

        for (String[] e : edges) {
            graph.removeEdge(e[0], e[1]);
        }
        for (String s : verts) {
            graph.removeVertex(s);
        }
        return graph;
    }

    private static <T> void print(T in) {
        System.out.println(in.toString());
    }

    private static <T> void print_1(T in) {
        System.out.print(in.toString());
    }

    private static <T> void print(T[] in) {
        for (T t : in) {
            print_1(t + " ");
        }
        print("");
    }

    private static <T> void print_matrix(T[] in) {
        int count = 0;
        for (T t : in) {
            print_1(t);
            count++;
            if (count < in.length)
                print_1(",");
        }
        print("");
    }

    private static <T> void print_2D(T[][] in) {
        for (T[] ts : in) {
            print(ts);
        }
    }

    private static <T> void print_2D_edges(T[][] in) {
        for (T[] ts : in) {
            print_edges(ts);
        }
    }

    private static <T> void print_edges(T[] in) {
        for (T t : in) {
            print_1(t + " ");
        }
        print("");
    }

    private static <T> void print_matrix_2D(T[][] in) {
        for (T[] ts : in) {
            print_matrix(ts);
        }
    }

    private static Integer[][] to_Integer(int[][] array) {
        Integer[][] out = new Integer[array.length][array[0] != null ? array[0].length : 0];
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                out[row][column] = array[row][column];
            }
        }
        return out;
    }

    private static String[][] matrix(Graph graph) {
        int vert_num = graph.vertices.length;
        String[][] matrix = new String[vert_num][vert_num];
        for (int row = 0; row < vert_num; row++) {
            for (int column = 0; column < vert_num; column++)
                matrix[row][column] = "0";
        }

        for (int index = 0; index < graph.edges.length; index++) {
            int vertex_A = find_pos(graph.vertices, graph.edges[index].vertexA);
            int vertex_B = find_pos(graph.vertices, graph.edges[index].vertexB);
            matrix[vertex_A][vertex_B] = graph.edges[index].weight + "";
            matrix[vertex_B][vertex_A] = graph.edges[index].weight + "";
        }

        return matrix;
    }

    private static <T extends Comparable<T>> int find_pos(T[] array, T in) {
        for (int index = 0; index < array.length; index++) {
            if (array[index].compareTo(in) == 0)
                return index;
        }

        return -1;
    }

    private static void tests(Graph test, String[][] edges, String[] verts) {
        print("Graph: \n");
        print(test);

        YELLOW();
        print("\nUnion Find: \n");
        print_2D(to_Integer(test.unionFind()));

        RED();
        print("\nCycle: \n");
        print(test.cycle());

        PURPLE();
        print("\nMin-SpanningTree: \n");
        print(test.minSpanningTree());

        GREEN();
        print("\nBrelaz Colouring: \n");
        print_2D(test.brelazColouring());

        if (visualizer)
            graph_visualizer(test, edges, verts);
    }

    private static void graph_visualizer(Graph test, String[][] edges, String[] verts) {

        BLUE();
        print("\nInput for: https://csacademy.com/app/graph_editor/ ");
        print("To use this one follow the link and copy the lines below and replace the text on the of the page with the copied text \n");
        for (String vert : verts) {
            print(vert);
        }
        print_2D_edges(edges);

        print("\nInput for: https://graphonline.ru/en/create_graph_by_matrix ");
        print("To use this one follow the link and click on the enter as text option, replace the text in the text box with the lines below and then click 'Plot Graph'\n");
        print("Be aware this one doesn't work for negatives so turn off negatives as explained at the top\n");
        print_matrix_2D(matrix(test));

        print("\nInput for: https://visualgo.net/en ");
        print("This one is really hard to use and Im not sure how to use it myself but if you can figure it out it does have visualizers for different algorithms\n");
        print_2D(matrix(test));
        RESET();
    }

    private static void RESET() {
        System.out.print(ANSI_RESET);
    }

    private static void BLACK() {
        System.out.print(ANSI_BLACK);
    }

    private static void RED() {
        System.out.print(ANSI_RED);
    }

    private static void GREEN() {
        System.out.print(ANSI_GREEN);
    }

    private static void YELLOW() {
        System.out.print(ANSI_YELLOW);
    }

    private static void BLUE() {
        System.out.print(ANSI_BLUE);
    }

    private static void PURPLE() {
        System.out.print(ANSI_PURPLE);
    }

    private static void CYAN() {
        System.out.print(ANSI_CYAN);
    }

    private static void WHITE() {
        System.out.print(ANSI_WHITE);
    }

    private static void RNJesus() {
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        if (randomNumber <= 5) {
            RED();
            print("________________________________________________________________________________________________________________________________________________________________________________________");
            BLUE();
            print("\n\n\nRNJesus has chosen you to make a donation: \n https://www.paypal.me/ChuufMaster\n\n");
            RED();
            print("________________________________________________________________________________________________________________________________________________________________________________________");
            RESET();
        }

    }
}