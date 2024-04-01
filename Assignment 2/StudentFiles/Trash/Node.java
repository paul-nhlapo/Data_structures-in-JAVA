import java.util.Random;

public class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;
    public int priority = getPriority();
    private static Random random = new Random(123456789);

    private static int getPriority() {
        return random.nextInt(128);
    }

    @Override
    public String toString() {
        return data.toString() + "[" + priority + "]";
    }

    /*
     * Don't change anything above this line
     */

    public Node(T data) {
        this.data = data;
        this.priority = getPriority();
        this.left = null;
        this.right = null;
    }

    public T getData() {
        return data;
    }
}
