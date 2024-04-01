public class Vertex implements Comparable<Vertex> {
    public String name;

    public Vertex(String n) {
        name = n;
    }

    @Override
    public int compareTo(Vertex o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    // Create a variable to store index of vertex
    public int index;

    // getter for index
    public int getIndex() {
        return index;
    }

}
