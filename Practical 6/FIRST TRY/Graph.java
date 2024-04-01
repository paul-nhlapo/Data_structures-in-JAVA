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
            if (this.vertices[i].equals(start)) {
                startIndex = i;
            }
            if (this.vertices[i].equals(end)) {
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
            if (this.vertices[i].equals(name)) {
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
            if (this.vertices[i].equals(start)) {
                startIndex = i;
            }
            if (this.vertices[i].equals(end)) {
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

        String result = String.format("(%d,%d)\t", numVertices, numEdges);
        for (String vertex : vertices) {
            result += String.format("%-8s", vertex);
        }
        result += "\n";

        for (int i = 0; i < numVertices; i++) {
            result += String.format("%-8s", vertices[i]);
            for (int j = 0; j < numVertices; j++) {
                result += String.format("%-8d", adjacencyMatrix[i][j]);
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
            if (this.vertices[i].equals(start)) {
                startIndex = i;
            }
            if (this.vertices[i].equals(end)) {
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

    // public String stronglyConnectedComponents() {
    // if (numVertices == 0) {
    // return "";
    // }

    // // Step 1: Perform a depth-first search on the graph and record the finish
    // times
    // // of each vertex
    // boolean[] visited = new boolean[numVertices];
    // int[] stack = new int[numVertices];
    // int top = -1;
    // int[] finishTimes = new int[numVertices];
    // int time = 0;

    // for (int i = 0; i < numVertices; i++) {
    // if (!visited[i]) {
    // top++;
    // stack[top] = i;
    // while (top != -1) {
    // int currentVertex = stack[top];
    // top--;
    // if (!visited[currentVertex]) {
    // visited[currentVertex] = true;
    // }
    // boolean allNeighborsVisited = true;
    // for (int j = numVertices - 1; j >= 0; j--) {
    // if (adjacencyMatrix[currentVertex][j] != 0 && !visited[j]) {
    // top++;
    // stack[top] = j;
    // allNeighborsVisited = false;
    // }
    // }
    // if (allNeighborsVisited) {
    // finishTimes[time] = currentVertex;
    // time++;
    // }
    // }
    // }
    // }

    // // Step 2: Transpose the graph by reversing the direction of all edges
    // Integer[][] transposedAdjacencyMatrix = new
    // Integer[numVertices][numVertices];
    // for (int i = 0; i < numVertices; i++) {
    // for (int j = 0; j < numVertices; j++) {
    // transposedAdjacencyMatrix[i][j] = adjacencyMatrix[j][i];
    // }
    // }

    // // Step 3: Perform a depth-first search on the transposed graph in decreasing
    // // order of finish times
    // visited = new boolean[numVertices];
    // String output = "";
    // for (int i = numVertices - 1; i >= 0; i--) {
    // int currentVertex = finishTimes[i];
    // if (!visited[currentVertex]) {
    // top++;
    // stack[top] = currentVertex;
    // String componentOutput = "";
    // while (top != -1) {
    // currentVertex = stack[top];
    // top--;
    // if (!visited[currentVertex]) {
    // visited[currentVertex] = true;
    // componentOutput += "[" + vertices[currentVertex] + "]";
    // }
    // for (int j = numVertices - 1; j >= 0; j--) {
    // if (transposedAdjacencyMatrix[currentVertex][j] != 0 && !visited[j]) {
    // top++;
    // stack[top] = j;
    // }
    // }
    // }
    // output += componentOutput + "\n";
    // }
    // }

    // return output;
    // }
    public String stronglyConnectedComponents() {
        if (numVertices == 0) {
            return "";
        }

        // Step 1: Perform a depth-first search on the graph and record the finish times
        // of each vertex
        boolean[] visited = new boolean[numVertices];
        int[] stack = new int[numVertices];
        int top = -1;
        int[] finishTimes = new int[numVertices];
        int time = 0;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                top++;
                stack[top] = i;
                while (top != -1) {
                    int currentVertex = stack[top];
                    top--;
                    if (!visited[currentVertex]) {
                        visited[currentVertex] = true;
                    }
                    boolean allNeighborsVisited = true;
                    for (int j = numVertices - 1; j >= 0; j--) {
                        if (adjacencyMatrix[currentVertex][j] != 0 && !visited[j]) {
                            top++;
                            stack[top] = j;
                            allNeighborsVisited = false;
                        }
                    }
                    if (allNeighborsVisited) {
                        finishTimes[time] = currentVertex;
                        time++;
                    }
                }
            }
        }

        // Step 2: Transpose the graph by reversing the direction of all edges
        Integer[][] transposedAdjacencyMatrix = new Integer[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                transposedAdjacencyMatrix[i][j] = adjacencyMatrix[j][i];
            }
        }

        // Step 3: Perform a depth-first search on the transposed graph in decreasing
        // order of finish times
        visited = new boolean[numVertices];
        String[][] components = new String[numVertices][numVertices];
        int[] componentSizes = new int[numVertices];
        int numComponents = 0;
        for (int i = numVertices - 1; i >= 0; i--) {
            int currentVertex = finishTimes[i];
            if (!visited[currentVertex]) {
                top++;
                stack[top] = currentVertex;
                while (top != -1) {
                    currentVertex = stack[top];
                    top--;
                    if (!visited[currentVertex]) {
                        visited[currentVertex] = true;
                        components[numComponents][componentSizes[numComponents]] = vertices[currentVertex];
                        componentSizes[numComponents]++;
                    }
                    for (int j = numVertices - 1; j >= 0; j--) {
                        if (transposedAdjacencyMatrix[currentVertex][j] != 0 && !visited[j]) {
                            top++;
                            stack[top] = j;
                        }
                    }
                }
                numComponents++;
            }
        }

        // Step 4: Sort the components and the vertices within each component
        for (int i = 0; i < numComponents; i++) {
            for (int j = i + 1; j < numComponents; j++) {
                if (componentSizes[j] > componentSizes[i]) {
                    String[] tempComponent = components[i];
                    components[i] = components[j];
                    components[j] = tempComponent;

                    int tempSize = componentSizes[i];
                    componentSizes[i] = componentSizes[j];
                    componentSizes[j] = tempSize;
                }
            }
            for (int k = 0; k < componentSizes[i]; k++) {
                for (int l = k + 1; l < componentSizes[i]; l++) {
                    if (components[i][l].compareTo(components[i][k]) < 0) {
                        String tempVertex = components[i][k];
                        components[i][k] = components[i][l];
                        components[i][l] = tempVertex;
                    }
                }
            }
        }

        // Step 5: Construct the output string
        String output = "";
        for (int i = 0; i < numComponents; i++) {
            for (int j = 0; j < componentSizes[i]; j++) {
                output += "[" + components[i][j] + "]";
            }
            output += "\n";
        }

        return output;
    }

    // Create a function to get vertices
    public String[] getVertices() {
        return this.vertices;
    }

}