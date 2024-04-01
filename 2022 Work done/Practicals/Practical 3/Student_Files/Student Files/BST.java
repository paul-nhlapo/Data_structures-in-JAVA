public class BST<T extends Comparable<T>> {
    protected BSTNode<T> root;

    public BST(){};

    public boolean insert(T val){
        if(root == null){
            root = new BSTNode<>(val);
            return true;
        } else {
            return root.recInsert(val);
        }
    }

    //Place code below

    public int numNodes(){
    }

    public Object[] toArray(){
    }

    public boolean contains(T val){
    }

    public boolean isEmpty(){
    }

    public BSTNode<T> find(T val){
    }

    public int height(){
    }

    public Object[] nodesOnHeight(int h){
    }

    public String DFT(){
    }

    public String BFT(){
    }

    public T maxVal(){
    }

    public T minVal(){
    }

    //ADD HELPER FUNCTIONS HERE
}
