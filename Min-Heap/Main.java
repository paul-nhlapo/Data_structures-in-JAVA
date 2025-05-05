public class Main {
    // Summary: This is a test class for the minDHeap class. It creates a minDHeap
    // with a degree of 3, inserts elements into it, changes the degree to 2, and
    // then removes some elements. It also prints the heap at various stages and
    // tests the min and max functions.
    // It also prints the path to the root for a specific index.
    // The minDHeap class is a custom implementation of a min-heap data structure
    // with a specified degree (number of children per node).
    // The class provides methods for inserting elements, removing elements,
    // changing the degree of the heap, and finding the minimum and maximum values
    // in the heap.
    // The class also includes a method for finding the path to the root of the heap
    // from a given index.
    public static void main(String[] args) {
        minDHeap<Integer> heap = new minDHeap<>(3);

        for (int i = 10; i >= 0; i--) {
            heap.insert(i);
        }
        System.out.println(heap);
        heap.changeD(2);
        System.out.println(heap);

        System.out.println(heap.pathToRoot(5));
        for (int i = 0; i <= 5; i++) {
            heap.remove(i);
        }
        System.out.println(heap);
        // Test both my min and max funtionality
        System.out.println(heap.min(0));
        System.out.println(heap.max(0));

    }
}