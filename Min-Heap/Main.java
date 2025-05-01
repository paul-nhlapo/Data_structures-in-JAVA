public class Main {
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