public class Graph {
    public Vertex[] vertices = new Vertex[0];
    // This array will contain all the vertices in the graph.
    // The array will be resized as needed to accommodate new vertices and no empty
    // spaces
    // spaces will be allowed.
    // The array will always be sorted alphabetically by the vertex name.
    public Edge[] edges = new Edge[0];
    // This array will contain all the edges in the graph.
    // The array will be resized as needed to accommodate new edges and no empty
    // spaces
    // spaces will be allowed.
    // The array will always be sorted using Edge.compareTo().

    public void addVertex(String v) {

        // Check if the vertex already exists in the graph.
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].name.equals(v)) {
                return;
            }
        }

        if (vertices.length == 0) {
            vertices = new Vertex[1];
            vertices[0] = new Vertex(v);
        } else {
            Vertex[] temp = new Vertex[vertices.length + 1];
            for (int i = 0; i < vertices.length; i++) {
                temp[i] = vertices[i];
            }
            temp[temp.length - 1] = new Vertex(v);
            vertices = temp;
        }

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

        // Check if the vertex exists in the graph.
        boolean vertexExists = false;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].name.equals(v)) {
                vertexExists = true;
                break;
            }
        }
        if (!vertexExists) {
            return;
        }

        // Remove the vertex from the vertices array.
        Vertex[] temp = new Vertex[vertices.length - 1];
        int index = 0;
        for (int i = 0; i < vertices.length; i++) {
            if (!vertices[i].name.equals(v)) {
                temp[index] = vertices[i];
                index++;
            }
        }
        vertices = temp;

        // Sort the vertices array alphabetically by the vertex name.
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length - i - 1; j++) {
                if (vertices[j].name.compareTo(vertices[j + 1].name) > 0) {
                    Vertex temp2 = vertices[j];
                    vertices[j] = vertices[j + 1];
                    vertices[j + 1] = temp2;
                }
            }
        }

    }

    public void addEdge(String a, String b, int w) {

        // Check if the edge already exists in the graph.
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].vertexA.name.equals(a) && edges[i].vertexB.name.equals(b)) {
                return;
            }
        }

        // Check if both vertices exist in the graph.
        boolean vertexAExists = false;
        boolean vertexBExists = false;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].name.equals(a)) {
                vertexAExists = true;
            }
            if (vertices[i].name.equals(b)) {
                vertexBExists = true;
            }
        }
        if (!vertexAExists || !vertexBExists) {
            return;
        }

        // Add the edge to the edges array.
        if (edges.length == 0) {
            edges = new Edge[1];
            edges[0] = new Edge(new Vertex(a), new Vertex(b), w);
        } else {
            Edge[] temp = new Edge[edges.length + 1];
            for (int i = 0; i < edges.length; i++) {
                temp[i] = edges[i];
            }
            temp[temp.length - 1] = new Edge(new Vertex(a), new Vertex(b), w);
            edges = temp;
        }

        // Sort the edges array using Edge.compareTo().
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].compareTo(edges[j + 1]) > 0) {
                    Edge temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }

    }

    public void removeEdge(String a, String b) {

        // Check if the edge exists in the graph.
        boolean edgeExists = false;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].vertexA.name.equals(a) && edges[i].vertexB.name.equals(b)) {
                edgeExists = true;
                break;
            }
        }
        if (!edgeExists) {
            return;
        }

        // Remove the edge from the edges array.
        Edge[] temp = new Edge[edges.length - 1];
        int index = 0;
        for (int i = 0; i < edges.length; i++) {
            if (!(edges[i].vertexA.name.equals(a) && edges[i].vertexB.name.equals(b))) {
                temp[index] = edges[i];
                index++;
            }
        }
        edges = temp;

        // Sort the edges array using Edge.compareTo().
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].compareTo(edges[j + 1]) > 0) {
                    Edge temp2 = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp2;
                }
            }
        }

    }

    public int[][] unionFind() {

        int root[] = new int[vertices.length];
        int next[] = new int[vertices.length];
        int length[] = new int[vertices.length];
        int result[] = new int[vertices.length];

        // Iterate through the vertices.
        for (int i = 0; i < vertices.length - 1; i++) {
            // Set the root of each vertex to itself.
            root[i] = i;
            // Set the next of each vertex to itself.
            next[i] = i;
            // Set the length of each vertex to 1.
            length[i] = 1;
            // Set the result of each vertex to 0.
            result[i] = 0;
        }

        // Iterate through the edges.
        for (int i = 0; i < edges.length; i++) {
            // Get the index of the first vertex.
            int indexA = getIndex(edges[i].vertexA.name);
            // Get the index of the second vertex.
            int indexB = getIndex(edges[i].vertexB.name);

            // Check if the vertices are in the same set.
            if (root[indexA] == root[indexB]) {
                // If they are, set the result of the second vertex to 1.
                result[indexB] = 1;
            } else {
                // If they are not, check if the length of the first vertex is greater than the
                // length of the second vertex.
                if (length[indexA] > length[indexB]) {
                    // If it is, set the root of the second vertex to the root of the first vertex.
                    root[indexB] = root[indexA];
                    // Set the next of the second vertex to the first vertex.
                    next[indexB] = indexA;
                    // Set the length of the second vertex to the length of the first vertex plus
                    // one.
                    length[indexB] = length[indexA] + 1;
                } else {
                    // If it is not, set the root of the first vertex to the root of the second
                    // vertex.
                    root[indexA] = root[indexB];
                    // Set the next of the first vertex to the second vertex.
                    next[indexA] = indexB;
                    // Set the length of the first vertex to the length of the second vertex plus
                    // one.
                    length[indexA] = length[indexB] + 1;
                }
            }
        }

        // Iterate through the vertices.
        for (int i = 0; i < vertices.length; i++) {
            // Check if the result of the vertex is 1.
            if (result[i] == 1) {
                // If it is, set the result of the vertex to 1.
                result[i] = 1;
                // Set the result of the next vertex to 1.
                result[next[i]] = 1;
            }
        }

        // Create a new 2D array.
        int[][] res = new int[4][vertices.length];

        // Iterate through the vertices.
        for (int i = 0; i < vertices.length; i++) {
            // Add the root to the first row.
            res[0][i] = root[i];
            // Add the next to the second row.
            res[1][i] = next[i];
            // Add the length to the third row.
            res[2][i] = length[i];
            // Add the result to the fourth row.
            res[3][i] = result[i];
        }

        // Return the 2D array.
        return res;

    }

    private int getIndex(String name) {
        // This function will return the index of the vertex with the given name.
        // If the vertex does not exist, -1 will be returned.

        // Iterate through the vertices.
        for (int i = 0; i < vertices.length; i++) {
            // Check if the vertex name matches the given name.
            if (vertices[i].name.equals(name)) {
                // If it does, return the index.
                return i;
            }
        }

        // If the vertex does not exist, return -1.
        return -1;
    }

    public boolean cycle() {

        // Call unionFind().
        int[][] res = unionFind();

        // Iterate through the result array.
        for (int i = 0; i < res[3].length; i++) {
            // Check if the result is 1.
            if (res[3][i] == 1) {
                // If it is, return true.
                return true;
            }
        }

        // If it is not, return false.

        return false;
    }

    public Graph minSpanningTree() {
        // Create a new graph.
        Graph res = new Graph();

        // Iterate through the edges.
        for (int i = 0; i < edges.length; i++) {
            // Check if the result is 0.
            if (unionFind()[3][getIndex(edges[i].vertexA.name)] == 0) {
                // If it is, add the edge to the new graph.
                res.addVertex(edges[i].vertexA.name);
                res.addVertex(edges[i].vertexB.name);
                res.addEdge(edges[i].vertexA.name, edges[i].vertexB.name, edges[i].weight);
            }
        }

        // Return the new graph.
        return res;
    }

    public Vertex[][] brelazColouring() {
        // Create a new 2D array.
        Vertex[][] res = new Vertex[2][vertices.length];

        // Iterate through the vertices.
        for (int i = 0; i < vertices.length; i++) {
            // Add the vertex to the first row.
            res[0][i] = vertices[i];
            // Add the colour to the second row.
            res[1][i] = new Vertex(vertices[i].name, 0);
        }

        // Sort the vertices array alphabetically by the vertex name.
        for (int i = 0; i < res[0].length; i++) {
            for (int j = 0; j < res[0].length - i - 1; j++) {
                if (res[0][j].name.compareTo(res[0][j + 1].name) > 0) {
                    Vertex temp = res[0][j];
                    res[0][j] = res[0][j + 1];
                    res[0][j + 1] = temp;
                }
            }
        }

        // Iterate through the vertices.
        for (int i = 0; i < res[0].length; i++) {
            // Check if the vertex is the first vertex.
            if (i == 0) {
                // If it is, set the colour to 1.
                res[1][i].colour = 1;
            } else {
                // If it is not, create a new array to store the colours of the neighbours.
                int[] colours = new int[i];
                // Iterate through the neighbours.
                for (int j = 0; j < i; j++) {
                    // Check if the vertices are neighbours.
                    if (unionFind()[3][getIndex(res[0][i].name)] == 1
                            && unionFind()[3][getIndex(res[0][j].name)] == 1) {
                        // If they are, add the colour to the array.
                        colours[j] = res[1][j].colour;
                    }
                }
                // Sort the array numerically.
                for (int j = 0; j < colours.length; j++) {
                    for (int k = 0; k < colours.length - j - 1; k++) {
                        if (colours[k] > colours[k + 1]) {
                            int temp = colours[k];
                            colours[k] = colours[k + 1];
                            colours[k + 1] = temp;
                        }
                    }
                }
                // Iterate through the colours.
                for (int j = 0; j < colours.length; j++) {
                    // Check if the colour is equal to the current colour.
                    if (colours[j] == res[1][i].colour) {
                        // If it is, increment the colour.
                        res[1][i].colour++;
                    }
                }
            }
        }

        // Return the 2D array.
        return res;
    }

    @Override
    public String toString() {

        // Create a string builder to build the string.
        StringBuilder sb = new StringBuilder();

        // Add the vertices to the first line.
        for (int i = 0; i < vertices.length; i++) {
            sb.append(vertices[i].name + "\t");
        }

        // Add a new line.
        sb.append("\n");

        // Add the remaining rows.
        for (int i = 0; i < vertices.length; i++) {
            // Add the vertex name.
            sb.append(vertices[i].name + "\t");

            // Add the weights.
            for (int j = 0; j < vertices.length; j++) {
                // Check if the edge exists.
                boolean edgeExists = false;
                for (int k = 0; k < edges.length; k++) {
                    if (edges[k].vertexA.name.equals(vertices[i].name)
                            && edges[k].vertexB.name.equals(vertices[j].name)) {
                        sb.append(edges[k].weight + "\t");
                        edgeExists = true;
                        break;
                    }
                }

                // If the edge does not exist, add 0.
                if (!edgeExists) {
                    sb.append("0\t");
                }
            }

            // Add a new line.
            sb.append("\n");
        }

        // Return the string.
        return sb.toString();

    }
}
