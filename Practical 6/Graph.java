import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    private String[] vertices;
    private Integer[][] adjacencyMatrix;
    private int numVertices;
    private int numEdges;

    public Graph(String fileName) {
        if (fileName.isEmpty()) {
            // Initialize an empty graph
            vertices = new String[0];
            adjacencyMatrix = new Integer[0][0];
            numVertices = 0;
            numEdges = 0;
        } else {
            // Initialize the graph from the file
            try (Scanner scanner = new Scanner(new File(fileName))) {
                // Read the first line to get the number of vertices
                numVertices = scanner.nextInt();
                scanner.nextLine();

                // Read the second line to get the vertex names
                String line = scanner.nextLine();
                vertices = line.split(" ");

                // Initialize the adjacency matrix
                adjacencyMatrix = new Integer[numVertices][numVertices];

                // Read the remaining lines to fill in the adjacency matrix
                for (int i = 0; i < numVertices; i++) {
                    for (int j = 0; j < numVertices; j++) {
                        adjacencyMatrix[i][j] = scanner.nextInt();
                    }
                    // scanner.nextLine(); //This line is causing the error

                }

                // Calculate the number of edges
                numEdges = 0;
                for (int i = 0; i < numVertices; i++) {
                    for (int j = 0; j < numVertices; j++) {
                        if (adjacencyMatrix[i][j] != 0) {
                            numEdges++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertVertex(String name) {
        // This function adds a vertex to the graph.
        // The vertex should be added to the end of the vertices array.
        // The adjacency matrix should be updated, assuming there are no edges from this
        // vertex to the other vertices.
        // You may assume that duplicates won’t be inserted

        String[] newVertices = new String[this.numVertices + 1];
        Integer[][] newAdjacencyMatrix = new Integer[this.numVertices + 1][this.numVertices + 1];
        for (int i = 0; i < this.numVertices; i++) {
            newVertices[i] = this.vertices[i];
            for (int j = 0; j < this.numVertices; j++) {
                newAdjacencyMatrix[i][j] = this.adjacencyMatrix[i][j];
            }
        }
        newVertices[this.numVertices] = name;
        for (int i = 0; i < this.numVertices + 1; i++) {
            newAdjacencyMatrix[this.numVertices][i] = 0;
            newAdjacencyMatrix[i][this.numVertices] = 0;
        }

        this.vertices = newVertices;
        this.adjacencyMatrix = newAdjacencyMatrix;
        this.numVertices++;

    }

    public void insertEdge(String start, String end, int weight) {
        // This function adds an edge to the graph.
        // If either of the Strings is not found in the graph or the weight is 0, do
        // nothing.
        // The edge is a directed edge from the Vertix with the name start to the Vertix
        // with
        // the name end. The weight of this edge is the passed-in weight parameter.
        // If there is already an edge between those nodes change the weight of the edge
        // to the
        // new value.

        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < this.numVertices; i++) {
            if (this.vertices[i] == start) {
                startIndex = i;
            }
            if (this.vertices[i] == end) {
                endIndex = i;
            }
        }
        if (startIndex == -1 || endIndex == -1 || weight == 0) {
            return;
        }
        this.adjacencyMatrix[startIndex][endIndex] = weight;

    }

    public void removeVertex(String name) {
        // This function removes a Vertex from the graph.
        // If the Vertex is not found, do nothing.
        // The vertices and adjacencyMatrix arrays should be updated to reflect this.

        int index = -1;
        for (int i = 0; i < this.numVertices; i++) {
            if (this.vertices[i] == name) {
                index = i;
            }
        }
        if (index == -1) {
            return;
        }
        String[] newVertices = new String[this.numVertices - 1];
        Integer[][] newAdjacencyMatrix = new Integer[this.numVertices - 1][this.numVertices - 1];
        for (int i = 0; i < this.numVertices; i++) {
            if (i < index) {
                newVertices[i] = this.vertices[i];
            } else if (i > index) {
                newVertices[i - 1] = this.vertices[i];
            }
            for (int j = 0; j < this.numVertices; j++) {
                if (i < index && j < index) {
                    newAdjacencyMatrix[i][j] = this.adjacencyMatrix[i][j];
                } else if (i < index && j > index) {
                    newAdjacencyMatrix[i][j - 1] = this.adjacencyMatrix[i][j];
                } else if (i > index && j < index) {
                    newAdjacencyMatrix[i - 1][j] = this.adjacencyMatrix[i][j];
                } else if (i > index && j > index) {
                    newAdjacencyMatrix[i - 1][j - 1] = this.adjacencyMatrix[i][j];
                }
            }
        }
        this.vertices = newVertices;
        this.adjacencyMatrix = newAdjacencyMatrix;
        this.numVertices--;

    }

    public void removeEdge(String start, String end) {
        // If either start or end is not found inside the graph, do nothing.
        // Set the weight from Vertex start to Vertex end to 0.

        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < this.numVertices; i++) {
            if (this.vertices[i] == start) {
                startIndex = i;
            }
            if (this.vertices[i] == end) {
                endIndex = i;
            }
        }
        if (startIndex == -1 || endIndex == -1) {
            return;
        }

        this.adjacencyMatrix[startIndex][endIndex] = 0;

    }

    @Override
    public String toString() {
        if (numVertices == 0) {
            return "(0,0)";
        }

        String result = "(" + numVertices + "," + numEdges + ")\t";
        for (String vertex : vertices) {
            result += vertex + "\t";
        }
        result += "\n";

        for (int i = 0; i < numVertices; i++) {
            result += vertices[i] + "\t";
            for (int j = 0; j < numVertices; j++) {
                result += adjacencyMatrix[i][j] + "\t";
            }
            result += "\n";
        }

        return result;
    }

    // This returns a depth-first traversal of the graph.
    // The Vertex’s name should be used and not the index in the array.
    // Every name should be between square brackets.
    // These square brackets are then appended to each other and then returned
    // (without
    // a new line)
    // If the graph is empty, return an empty string.
    public String depthFirstTraversal() {
        if (this.numVertices == 0) {
            return "";
        }
        String output = "";
        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < this.numVertices; i++) {
            if (!visited[i]) {
                output += "[" + this.vertices[i] + "]";
                visited[i] = true;
                output += depthFirstTraversalHelper(i, visited);
            }
        }
        return output;
    }

    private String depthFirstTraversalHelper(int i, boolean[] visited) {
        String output = "";
        for (int j = 0; j < this.numVertices; j++) {
            if (this.adjacencyMatrix[i][j] != 0 && !visited[j]) {
                output += "[" + this.vertices[j] + "]";
                visited[j] = true;
                output += depthFirstTraversalHelper(j, visited);
            }
        }
        return output;
    }

    public String breadthFirstTraversal() {
        if (numVertices == 0) {
            return "";
        }
        String output = "";
        boolean[] visited = new boolean[numVertices];
        int[] queue = new int[numVertices];
        int front = 0;
        int rear = 0;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                visited[i] = true;
                queue[rear++] = i;

                while (front != rear) {
                    int vertex = queue[front++];
                    output += "[" + vertices[vertex] + "]";

                    for (int j = 0; j < numVertices; j++) {
                        if (adjacencyMatrix[vertex][j] != 0 && !visited[j]) {
                            visited[j] = true;
                            queue[rear++] = j;
                        }
                    }
                }
            }
        }
        return output;
    }

    public Double[][] shortestPaths() {
        // This returns the All to All shortest path algorithm result.
        // Double.POSITIVE_INFINITY should be used for unreachable nodes.
        // If the graph is empty return an array of size 0x0.

        if (this.numVertices == 0) {
            return new Double[0][0];
        }

        Double[][] output = new Double[this.numVertices][this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                if (i == j) {
                    output[i][j] = 0.0;
                } else if (this.adjacencyMatrix[i][j] != 0) {
                    output[i][j] = (double) this.adjacencyMatrix[i][j];
                } else {
                    output[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        for (int k = 0; k < this.numVertices; k++) {
            for (int i = 0; i < this.numVertices; i++) {
                for (int j = 0; j < this.numVertices; j++) {
                    if (output[i][k] + output[k][j] < output[i][j]) {
                        output[i][j] = output[i][k] + output[k][j];
                    }
                }
            }
        }
        return output;
    }

    public Double shortestPath(String start, String end) {
        // This returns the distance for the shortest path between the start and end
        // vertex.
        // If either of the nodes is not in the graph, return null.

        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < this.numVertices; i++) {
            if (this.vertices[i] == start) {
                startIndex = i;
            }
            if (this.vertices[i] == end) {
                endIndex = i;
            }
        }
        if (startIndex == -1 || endIndex == -1) {
            return null;
        }

        Double[][] shortestPaths = shortestPaths();
        return shortestPaths[startIndex][endIndex];

    }

    public boolean cycleDetection() {
        // Return true if there is a cycle in the graph.
        // Return false if there are no cycles in the graph.

        if (this.numVertices == 0) {
            return false;
        }

        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < this.numVertices; i++) {
            if (!visited[i]) {
                if (cycleDetectionHelper(i, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean cycleDetectionHelper(int i, boolean[] visited) {

        for (int j = 0; j < this.numVertices; j++) {
            if (this.adjacencyMatrix[i][j] != 0 && !visited[j]) {
                visited[j] = true;
                if (cycleDetectionHelper(j, visited)) {
                    return true;
                }
            } else if (this.adjacencyMatrix[i][j] != 0 && visited[j]) {
                return true;
            }
        }
        return false;
    }

    public String stronglyConnectedComponents() {
        if (this.numVertices == 0) {
            return "";
        }
        String output = "";
        boolean[] visited = new boolean[this.numVertices];
        int[] stack = new int[this.numVertices];
        int top = 0;
        for (int i = 0; i < this.numVertices; i++) {
            if (!visited[i]) {
                top = dfs(i, visited, stack, top);
            }
        }
        Integer[][] transposeAdjacencyMatrix = new Integer[this.numVertices][this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                transposeAdjacencyMatrix[i][j] = this.adjacencyMatrix[j][i];
            }
        }
        visited = new boolean[this.numVertices];
        while (top != 0) {
            int vertex = stack[--top];
            if (!visited[vertex]) {
                String component = dfs2(vertex, visited, transposeAdjacencyMatrix);
                output = component + "\n" + output;
            }
        }
        return output;
    }

    private int dfs(int vertex, boolean[] visited, int[] stack, int top) {
        visited[vertex] = true;
        for (int j = 0; j < this.numVertices; j++) {
            if (adjacencyMatrix[vertex][j] != 0 && !visited[j]) {
                top = dfs(j, visited, stack, top);
            }
        }
        stack[top++] = vertex;
        return top;
    }

    private String dfs2(int vertex, boolean[] visited, Integer[][] transposeAdjacencyMatrix) {
        visited[vertex] = true;
        String component = "[" + vertices[vertex] + "]";
        for (int j = 0; j < this.numVertices; j++) {
            if (transposeAdjacencyMatrix[vertex][j] != 0 && !visited[j]) {
                component += dfs2(j, visited, transposeAdjacencyMatrix);
            }
        }
        return component;
    }
}