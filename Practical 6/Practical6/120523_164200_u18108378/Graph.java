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
        // Check for all edge cases and make sure to update the number of vertices
        if (name == null || name.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.numVertices; i++) {
            if (this.vertices[i].equals(name)) {
                return;
            }
        }

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
        // ∗ This function adds an edge to the graph.
        // ∗ If either of the Strings is not found in the graph or the weight is 0, do
        // nothing.
        // ∗ The edge is a directed edge from the Vertix with the name start to the
        // Vertix with
        // the name end. The weight of this edge is the passed-in weight parameter.
        // ∗ If there is already an edge between those nodes change the weight of the
        // edge to the
        // new value

        if (weight == 0)
            return;

        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(start)) {
                startIndex = i;
            }
            if (vertices[i].equals(end)) {
                endIndex = i;
            }
        }

        if (startIndex == -1 || endIndex == -1)
            return;

        adjacencyMatrix[startIndex][endIndex] = weight;

    }

    public void removeVertex(String name) {

        int index = -1;
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(name)) {
                index = i;
            }
        }

        if (index == -1)
            return;

        String[] newVertices = new String[numVertices - 1];
        for (int i = 0; i < index; i++) {
            newVertices[i] = vertices[i];
        }
        for (int i = index; i < numVertices - 1; i++) {
            newVertices[i] = vertices[i + 1];
        }
        vertices = newVertices;

        Integer[][] newAdjacencyMatrix = new Integer[numVertices - 1][numVertices - 1];
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < index; j++) {
                newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        for (int i = index; i < numVertices - 1; i++) {
            for (int j = index; j < numVertices - 1; j++) {
                newAdjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j + 1];
            }
        }
        adjacencyMatrix = newAdjacencyMatrix;
        numVertices--;
    }

    public void removeEdge(String start, String end) {

        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(start)) {
                startIndex = i;
            }
            if (vertices[i].equals(end)) {
                endIndex = i;
            }
        }

        if (startIndex == -1 || endIndex == -1)
            return;

        adjacencyMatrix[startIndex][endIndex] = 0;

    }

    @Override
    public String toString() {
        if (this.numVertices == 0) {
            return "(0,0)";
        }
        String output = "(" + this.numVertices + "," + this.numEdges + ")\t";
        for (int i = 0; i < this.numVertices; i++) {
            output += this.vertices[i] + "\t";
        }
        output += "\n";
        for (int i = 0; i < this.numVertices; i++) {
            output += this.vertices[i] + "\t";
            for (int j = 0; j < this.numVertices; j++) {
                output += this.adjacencyMatrix[i][j] + "\t";
            }
            if (i != this.numVertices - 1) {
                output += "\n";
            }
        }
        return output;
    }

    public String depthFirstTraversal() {
        if (this.numVertices == 0) {
            return "";
        }
        String output = "";
        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }
        int[] stack = new int[this.numVertices];
        int top = -1;
        for (int i = 0; i < this.numVertices; i++) {
            if (!visited[i]) {
                top++;
                stack[top] = i;
                while (top != -1) {
                    int currentVertex = stack[top];
                    top--;
                    if (!visited[currentVertex]) {
                        output += "[" + this.vertices[currentVertex] + "]";
                        visited[currentVertex] = true;
                    }
                    for (int j = this.numVertices - 1; j >= 0; j--) {
                        if (this.adjacencyMatrix[currentVertex][j] != 0 && !visited[j]) {
                            top++;
                            stack[top] = j;
                        }
                    }
                }
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
                queue[rear] = i;
                rear = (rear + 1) % numVertices;

                while (front != rear) {
                    int vertex = queue[front];
                    front = (front + 1) % numVertices;
                    output += "[" + vertices[vertex] + "]";

                    for (int j = 0; j < numVertices; j++) {
                        if (adjacencyMatrix[vertex][j] != 0 && !visited[j]) {
                            visited[j] = true;
                            queue[rear] = j;
                            rear = (rear + 1) % numVertices;
                        }
                    }
                }
            }
        }
        return output;
    }

    public Double[][] shortestPaths() {
        // ∗ This returns the All to All shortest path algorithm result.
        // ∗ Double.POSITIVE_INFINITY should be used for unreachable nodes.
        // ∗ If the graph is empty return an array of size 0x0

        if (numVertices == 0)
            return new Double[0][0];

        Double[][] result = new Double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                result[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        for (int i = 0; i < numVertices; i++) {
            result[i][i] = 0.0;
        }

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    result[i][j] = (double) adjacencyMatrix[i][j];
                }
            }
        }

        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (result[i][k] + result[k][j] < result[i][j]) {
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
            }
        }

        return result;
    }

    public Double shortestPath(String start, String end) {
        // This returns the distance for the shortest path between the start and end
        // vertex.
        // ∗ If either of the nodes is not in the graph, return null.

        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(start)) {
                startIndex = i;
            }
            if (vertices[i].equals(end)) {
                endIndex = i;
            }
        }

        if (startIndex == -1 || endIndex == -1)
            return null;

        Double[][] result = shortestPaths();

        return result[startIndex][endIndex];

    }

    public boolean cycleDetection() {
        if (this.numVertices == 0) {
            return false;
        }

        boolean[] visited = new boolean[this.numVertices];
        int[] stack = new int[this.numVertices];
        int top = -1;
        for (int i = 0; i < this.numVertices; i++) {
            if (!visited[i]) {
                top++;
                stack[top] = i;
                while (top != -1) {
                    int currentVertex = stack[top];
                    top--;
                    if (!visited[currentVertex]) {
                        visited[currentVertex] = true;
                    }
                    for (int j = this.numVertices - 1; j >= 0; j--) {
                        if (this.adjacencyMatrix[currentVertex][j] != 0 && !visited[j]) {
                            top++;
                            stack[top] = j;
                        } else if (this.adjacencyMatrix[currentVertex][j] != 0 && visited[j]) {
                            return true;
                        }
                    }
                }
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
        for (int i = top - 1; i >= 0; i--) {
            int vertex = stack[i];
            if (!visited[vertex]) {
                String[] componentArr = dfs2(vertex, visited, transposeAdjacencyMatrix);
                String component = "";
                for (int j = 0; j < componentArr.length && componentArr[j] != null; j++) {
                    component += "[" + componentArr[j] + "]";
                }
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

    private String[] dfs2(int vertex, boolean[] visited, Integer[][] transposeAdjacencyMatrix) {
        visited[vertex] = true;
        String[] component = new String[this.numVertices];
        int size = 0;
        component[size++] = vertices[vertex];
        for (int j = 0; j < this.numVertices; j++) {
            if (transposeAdjacencyMatrix[vertex][j] != 0 && !visited[j]) {
                String[] subcomponent = dfs2(j, visited, transposeAdjacencyMatrix);
                for (int k = 0; k < subcomponent.length && subcomponent[k] != null; k++) {
                    component[size++] = subcomponent[k];
                }
            }
        }
        sort(component, size);
        return component;
    }

    private void sort(String[] arr, int size) {
        for (int i = 1; i < size; i++) {
            String key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public String[] getVertices() {
        // return the vertices array
        return vertices;
    }

}