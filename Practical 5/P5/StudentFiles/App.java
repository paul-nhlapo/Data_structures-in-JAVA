public class Main {

	public static void main(String[] args) {

		// create a new B-Tree with degree 3
		BTree<Integer> bTree = new BTree<Integer>(3);

		// insert some values into the tree
		bTree.insert(10);
		bTree.insert(20);
		bTree.insert(5);
		bTree.insert(6);
		bTree.insert(12);
		bTree.insert(30);
		bTree.insert(7);
		bTree.insert(17);

		// print the tree
		System.out.println("B-Tree:");
		bTree.print();
		System.out.println();

		// traverse the tree
		System.out.println("Traversing the B-Tree:");
		bTree.traverse();
		System.out.println();

		// find some values in the tree
		System.out.println("Finding values in the B-Tree:");
		System.out.println("Find 10: " + bTree.find(10));
		System.out.println("Find 20: " + bTree.find(20));
		System.out.println("Find 5: " + bTree.find(5));
		System.out.println("Find 6: " + bTree.find(6));
		System.out.println("Find 12: " + bTree.find(12));
		System.out.println("Find 30: " + bTree.find(30));
		System.out.println("Find 7: " + bTree.find(7));
		System.out.println("Find 17: " + bTree.find(17));
		System.out.println("Find 100: " + bTree.find(100));
		System.out.println();

		// get the nodes of the tree
		System.out.println("Getting the nodes of the B-Tree:");
		Node<Integer>[] nodes = bTree.nodes();
		if (nodes != null) {
			System.out.println("Nodes of the B-Tree:");
			for (int i = 0; i < nodes.length; i++) {
				System.out.println(nodes[i]);
			}
		}
		System.out.println();

		// count the number of keys and nodes in the tree
		System.out.println("Number of keys in the B-Tree: " + bTree.numKeys());
		System.out.println("Number of nodes in the B-Tree: " + bTree.countNumNodes());
	}

}
