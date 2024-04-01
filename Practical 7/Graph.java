import java.nio.channels.AcceptPendingException;

public class Graph {
    public Vertex[] vertices = new Vertex[0];

    public Edge[] edges = new Edge[0];

    public void addVertex(String v) {
        // Check if the vertex already exists in the graph.
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].name.equals(v)) {
                return;
            }
        }
        // Create a new vertex array with increased size
        Vertex[] newVertices = new Vertex[vertices.length + 1];
        for (int i = 0; i < vertices.length; i++) {
            newVertices[i] = vertices[i];
        }
        newVertices[newVertices.length - 1] = new Vertex(v);
        vertices = newVertices;

        // Sort the vertices array alphabetically by the vertex name.
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length - i - 1; j++) {
                if (vertices[j].name.compareTo(vertices[j + 1].name) > 0) {
                    Vertex temp = vertices[j];
                    vertices[j] = vertices[j + 1];
                    vertices[j + 1] = temp;
                }
            }
        }
    }

    public void removeVertex(String v) {
        // Find the index of the vertex to be removed
        int indexToRemove = -1;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].name.equals(v)) {
                indexToRemove = i;
                break;
            }
        }

        // If the vertex is not found, do nothing
        if (indexToRemove == -1) {
            return;
        }

        // Create a new vertices array with reduced size
        Vertex[] newVertices = new Vertex[vertices.length - 1];
        int newIndex = 0;

        // Copy vertices except for the one being removed
        for (int i = 0; i < vertices.length; i++) {
            if (i != indexToRemove) {
                newVertices[newIndex] = vertices[i];
                newIndex++;
            }
        }

        // Update the vertices array
        vertices = newVertices;

        // Remove any edges connected to the removed vertex
        removeEdgesWithVertex(v);
    }

    private void removeEdgesWithVertex(String v) {
        // Create a new edges array with reduced size
        Edge[] newEdges = new Edge[edges.length];
        int newIndex = 0;

        // Copy edges except for the ones connected to the removed vertex
        for (int i = 0; i < edges.length; i++) {
            Edge edge = edges[i];
            if (!(edge.vertexA.name.equals(v) || edge.vertexB.name.equals(v))) {
                newEdges[newIndex] = edge;
                newIndex++;
            }
        }

        // Update the edges array
        edges = new Edge[newIndex];
        for (int i = 0; i < newIndex; i++) {
            edges[i] = newEdges[i];
        }
    }

    public void addEdge(String a, String b, int w) {
        // Check if the vertices exist in the graph
        Vertex vertexA = null;
        Vertex vertexB = null;
        for (Vertex vertex : vertices) {
            if (vertex.name.equals(a)) {
                vertexA = vertex;
            }
            if (vertex.name.equals(b)) {
                vertexB = vertex;
            }
        }

        // If either vertex is missing, do nothing
        if (vertexA == null || vertexB == null) {
            return;
        }

        // Check if the edge already exists in the graph
        for (Edge edge : edges) {
            if ((edge.vertexA == vertexA && edge.vertexB == vertexB) ||
                    (edge.vertexA == vertexB && edge.vertexB == vertexA)) {
                return;
            }
        }

        // Create the new edge and update the edges array
        Edge newEdge = new Edge(vertexA, vertexB, w);
        Edge[] newEdges = new Edge[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            newEdges[i] = edges[i];
        }
        newEdges[newEdges.length - 1] = newEdge;

        // Sort the newEdges array using bubble sort
        for (int i = 0; i < newEdges.length - 1; i++) {
            for (int j = 0; j < newEdges.length - i - 1; j++) {
                if (newEdges[j].compareTo(newEdges[j + 1]) > 0) {
                    // Swap the elements if they are out of order
                    Edge temp = newEdges[j];
                    newEdges[j] = newEdges[j + 1];
                    newEdges[j + 1] = temp;
                }
            }
        }

        edges = newEdges;
    }

    public void removeEdge(String a, String b) {
        // Check if the vertices exist in the graph
        Vertex vertexA = null;
        Vertex vertexB = null;
        for (Vertex vertex : vertices) {
            if (vertex.name.equals(a)) {
                vertexA = vertex;
            }
            if (vertex.name.equals(b)) {
                vertexB = vertex;
            }
        }

        // If either vertex is missing, do nothing
        if (vertexA == null || vertexB == null) {
            return;
        }

        // Find the edge to remove and update the edges array
        boolean edgeRemoved = false;
        Edge[] newEdges = new Edge[edges.length - 1];
        int newIndex = 0;

        for (Edge edge : edges) {
            if ((edge.vertexA == vertexA && edge.vertexB == vertexB) ||
                    (edge.vertexA == vertexB && edge.vertexB == vertexA)) {
                edgeRemoved = true;
            } else {
                newEdges[newIndex] = edge;
                newIndex++;
            }
        }

        // If the edge was removed, update the edges array
        if (edgeRemoved) {
            edges = newEdges;
        }
    }

    public int[][] unionFind() {
        int[][] result = new int[4][vertices.length];
        int[] root = result[0];
        int[] next = result[1];
        int[] length = result[2];
        int[] cycleDetected = result[3];

        for (int i = 0; i < vertices.length; i++) {
            root[i] = i;
            next[i] = i;
            length[i] = 1;
            cycleDetected[i] = 0;
        }

        for (Edge edge : edges) {
            int v = getPosition(edge.vertexA, vertices);
            int u = getPosition(edge.vertexB, vertices);

            int rootU = findRoot(u, root);
            int rootV = findRoot(v, root);

            if (rootU == rootV) {
                // A cycle was found
                cycleDetected[rootU] = 1;
            } else if (length[rootV] < length[rootU]) {
                root[rootV] = rootU;
                length[rootU] += length[rootV];
                int temp = next[rootV];
                next[rootV] = next[rootU];
                next[rootU] = temp;
            } else {
                root[rootU] = rootV;
                length[rootV] += length[rootU];
                int temp = next[rootU];
                next[rootU] = next[rootV];
                next[rootV] = temp;
            }
        }

        // Update cycleDetected array based on root values
        for (int i = 0; i < vertices.length; i++) {
            int rootI = findRoot(i, root);
            if (cycleDetected[rootI] == 1) {
                cycleDetected[i] = 1;
            }
        }

        return result;
    }

    private int findRoot(int vertex, int[] root) {
        if (vertex != root[vertex]) {
            root[vertex] = findRoot(root[vertex], root);
        }
        return root[vertex];
    }

    // Helper method to get the position of a vertex in the vertices array
    private int getPosition(Vertex vertex, Vertex[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == vertex) {
                return i;
            }
        }
        return -1; // Vertex not found
    }

    private int findRoot(int[] root, Vertex vertex) {
        int vertexIndex = -1;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].compareTo(vertex) == 0) {
                vertexIndex = i;
                break;
            }
        }
        if (vertexIndex == -1) {
            return -1;
        }

        while (vertexIndex != root[vertexIndex]) {
            vertexIndex = root[vertexIndex];
        }

        return vertexIndex;
    }

    public boolean cycle() {
        int[] root = new int[vertices.length];
        int[] next = new int[vertices.length];
        int[] length = new int[vertices.length];

        // Initialize root, next, and length arrays
        for (int i = 0; i < vertices.length; i++) {
            root[i] = i;
            next[i] = i;
            length[i] = 1;
        }

        // Perform union-find cycle detection algorithm
        for (Edge edge : edges) {
            int rootA = findRoot(root, edge.vertexA);
            int rootB = findRoot(root, edge.vertexB);

            if (rootA == rootB) {
                // Cycle detected
                return true;
            } else {
                if (length[rootA] >= length[rootB]) {
                    // Merge rootB into rootA
                    next[rootB] = rootA;
                    length[rootA] += length[rootB];
                    root[rootB] = rootA;
                } else {
                    // Merge rootA into rootB
                    next[rootA] = rootB;
                    length[rootB] += length[rootA];
                    root[rootA] = rootB;
                }
            }
        }

        // No cycle detected
        return false;
    }

    public Graph minSpanningTree() {

        // Create a new graph to store the minimum spanning tree
        Graph minSpanningTree = new Graph();

        // Create a new array to store the root of each vertex
        int[] root = new int[vertices.length];

        // Initialize root array
        for (int i = 0; i < vertices.length; i++) {
            root[i] = i;
        }

        // Sort the edges array by weight
        Edge[] sortedEdges = new Edge[edges.length];
        for (int i = 0; i < edges.length; i++) {
            sortedEdges[i] = edges[i];
        }
        for (int i = 0; i < sortedEdges.length - 1; i++) {
            for (int j = 0; j < sortedEdges.length - i - 1; j++) {
                if (sortedEdges[j].weight > sortedEdges[j + 1].weight) {
                    Edge temp = sortedEdges[j];
                    sortedEdges[j] = sortedEdges[j + 1];
                    sortedEdges[j + 1] = temp;
                }
            }
        }

        // Perform Kruskal's algorithm
        for (Edge edge : sortedEdges) {
            int rootA = findRoot(root, edge.vertexA);
            int rootB = findRoot(root, edge.vertexB);

            if (rootA != rootB) {
                // Add the edge to the minimum spanning tree
                minSpanningTree.addVertex(edge.vertexA.name);
                minSpanningTree.addVertex(edge.vertexB.name);
                minSpanningTree.addEdge(edge.vertexA.name, edge.vertexB.name, edge.weight);

                // Merge rootB into rootA
                root[rootB] = rootA;
            }
        }

        return minSpanningTree;
    }

    public Vertex[][] brelazColouring() {

        // Create a new array to store the colours
        Vertex[][] colours = new Vertex[vertices.length][vertices.length];

        // Create a new array to store the colour of each vertex
        int[] vertexColours = new int[vertices.length];

        // Initialize vertexColours array
        for (int i = 0; i < vertices.length; i++) {
            vertexColours[i] = -1;
        }

        // Sort the vertices array by degree
        Vertex[] sortedVertices = new Vertex[vertices.length];
        // Create a new array to store the degree of each vertex
        int[] vertexDegrees = new int[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            sortedVertices[i] = vertices[i];
            vertexDegrees[i] = getDegree(vertices[i]);
        }
        for (int i = 0; i < sortedVertices.length - 1; i++) {
            for (int j = 0; j < sortedVertices.length - i - 1; j++) {
                if (vertexDegrees[j] < vertexDegrees[j + 1]) {
                    Vertex temp = sortedVertices[j];
                    sortedVertices[j] = sortedVertices[j + 1];
                    sortedVertices[j + 1] = temp;

                    int tempDegree = vertexDegrees[j];
                    vertexDegrees[j] = vertexDegrees[j + 1];
                    vertexDegrees[j + 1] = tempDegree;
                }
            }
        }

        // Perform Brelaz's algorithm
        for (Vertex vertex : sortedVertices) {
            // Find the smallest colour that can be assigned to the vertex
            int smallestColour = 0;
            boolean colourFound = false;
            while (!colourFound) {
                boolean colourAvailable = true;
                for (Edge edge : edges) {
                    if (edge.vertexA == vertex
                            && vertexColours[getPosition(edge.vertexB, vertices)] == smallestColour) {
                        colourAvailable = false;
                        break;
                    }
                    if (edge.vertexB == vertex
                            && vertexColours[getPosition(edge.vertexA, vertices)] == smallestColour) {
                        colourAvailable = false;
                        break;
                    }
                }
                if (colourAvailable) {
                    colourFound = true;
                } else {
                    smallestColour++;
                }
            }

            // Assign the smallest colour to the vertex
            vertexColours[getPosition(vertex, vertices)] = smallestColour;
        }

        // Sort the vertices array by colour
        Vertex[] sortedVerticesByColour = new Vertex[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            sortedVerticesByColour[i] = vertices[i];
        }
        for (int i = 0; i < sortedVerticesByColour.length - 1; i++) {
            for (int j = 0; j < sortedVerticesByColour.length - i - 1; j++) {
                if (vertexColours[j] > vertexColours[j + 1]) {
                    Vertex temp = sortedVerticesByColour[j];
                    sortedVerticesByColour[j] = sortedVerticesByColour[j + 1];
                    sortedVerticesByColour[j + 1] = temp;

                    int tempColour = vertexColours[j];
                    vertexColours[j] = vertexColours[j + 1];
                    vertexColours[j + 1] = tempColour;
                }
            }
        }

        // Sort the vertices in each colour by name
        for (int i = 0; i < sortedVerticesByColour.length; i++) {
            // Find the number of vertices with the current colour
            int numVertices = 0;
            for (int j = 0; j < sortedVerticesByColour.length; j++) {
                if (vertexColours[j] == i) {
                    numVertices++;
                }
            }

            // Create a new array to store the vertices with the current colour
            Vertex[] verticesWithColour = new Vertex[numVertices];
            int index = 0;
            for (int j = 0; j < sortedVerticesByColour.length; j++) {
                if (vertexColours[j] == i) {
                    verticesWithColour[index] = sortedVerticesByColour[j];
                    index++;
                }
            }

            // Sort the vertices with the current colour by name
            for (int j = 0; j < verticesWithColour.length - 1; j++) {
                for (int k = 0; k < verticesWithColour.length - j - 1; k++) {
                    if (verticesWithColour[k].name.compareTo(verticesWithColour[k + 1].name) > 0) {
                        Vertex temp = verticesWithColour[k];
                        verticesWithColour[k] = verticesWithColour[k + 1];
                        verticesWithColour[k + 1] = temp;
                    }
                }
            }

            // Add the vertices with the current colour to the colours array
            colours[i] = verticesWithColour;
        }

        return colours;
    }

    private int getDegree(Vertex vertex) {
        int degree = 0;
        for (Edge edge : edges) {
            if (edge.vertexA == vertex || edge.vertexB == vertex) {
                degree++;
            }
        }
        return degree - 1; // Subtract 1 to exclude self-links
    }

    @Override
    public String toString() {
        // If the graph is empty, return an empty string
        if (vertices.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        // Append the vertex names as the first row with tabs
        sb.append("\t");
        for (Vertex v : vertices) {
            sb.append(v.name).append("\t");
        }
        sb.append("\n");

        // Append the adjacency matrix rows
        for (Vertex v1 : vertices) {
            sb.append(v1.name).append("\t");
            for (Vertex v2 : vertices) {
                boolean foundEdge = false;
                for (Edge e : edges) {
                    if ((e.vertexA == v1 && e.vertexB == v2) || (e.vertexA == v2 && e.vertexB == v1)) {
                        sb.append(e.weight).append("\t");
                        foundEdge = true;
                        break;
                    }
                }
                if (!foundEdge) {
                    sb.append("0\t");
                }
            }
            sb.setLength(sb.length() - 1); // Remove the trailing tab
            sb.append("\n");
        }

        return sb.toString();
    }

    // stronglyConnectedComponentSearch()
    // for all vertices v
    // num(v) =0;
    // i=1;
    // while there is a vertex v such that num(v) == 0
    // strongDFS(v);

    // strongDFS(v)
    // pre(v) = num(v) = i++;
    // push(v);
    // for all vertices u adjacent to v
    // if num(u) == 0
    // strongDFS(u);
    // pred(v) = min(pred(v), pred(u));
    // else if num(u) < num(v) and u is on the stack
    // pred(v)= min(pred(v), num(u));
    // if pred(v) == num(v)
    // w =pop();
    // while w != v
    // output w;
    // w = pop();
    // output w;

    // //The topological sort algorithm
    // topologicalSort(digraph)
    // for all vertices v
    // num(v)=TSNum(v)=0;
    // i=1;
    // j=|v|;
    // while there is a vertex v such that num(v) == 0
    // TS(v);

    // output vertices according to their TSNum's;

    // TS(v)
    // num(v) = i++;
    // for all vertices u adjacent to v
    // if num(u) == 0
    // TS(u);
    // else if TSNum(u) == 0
    // error;
    // TSNum(v)= j--;


    //label correcting algorithm
    labelCorrectingAlgorithm(weighted simple digraph, vertex first)
    for all vertices v
    currDist(v) = infinit;
    currDist(first)=0;
    toBeChecked = {first};
    while toBeChecked is not empty
    v = a vertex in toBeChecked;
    remove v from toBeChecked;
    for all vertices u adjacent to v
    if currDist(u)> currDist(v)+ weight(edge(vu))
    currDist(u) = currDist(v)+ weight(edge(vu));
    predessor(u) = v;
    add u to toBeChecked if it is not there already;
}
