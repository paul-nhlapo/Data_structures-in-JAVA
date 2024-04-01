public class Vertex implements Comparable<Vertex> {
    public String name;
    public int colour;

    public Vertex(String n) {
        name = n;
    }

    public Vertex(String name2, int i) {
        name = name2;


    }

    @Override
    public int compareTo(Vertex o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }

}
