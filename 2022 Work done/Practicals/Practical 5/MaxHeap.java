
@SuppressWarnings("unchecked")
public class MaxHeap<T extends Comparable<T>> extends Heap<T> {

    public MaxHeap(int capacity) {
	super(capacity);
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line ////// 

    @Override
    public void insert(T elem) {

        //Your code goes here
    }

    public T removeMax() {

        //Your code goes here
    }

    public void delete(T elem) {

	//Your code goes here
    int k_iterator2;
    for(k_iterator2 = 0;k_iterator2<=this.getCapacity();k_iterator2++){
        if(mH[k_iterator2]==elem)break;
    }
    if(isEmpty() )
        return;
    // T key = (T)mH[k_iterator];
    mH[k_iterator2] = mH[currentSize-1];
    currentSize--;
    swimNode(k_iterator2);
    }


     //Helper functions
     private void bubbleNode(int i) {
        Comparable<T> temp = mH[i];
        while(i>0 && CompareStuff(temp, mH[parentNode(i)])){
            mH[i] = mH[parentNode(i)];
            i = parentNode(i);
        }
        mH[i] = temp;
    }
    private void swimNode(int i){
        int child;
        Comparable<T> temp = ConvertToTypeT(mH[i]);
        while(k_thChildNode(i, 1) < currentSize){
            child = maximumChildNode(i);
            if(!CompareStuff(temp,mH[child])){
                mH[i] = mH[child];
            }else break;
            i = child;
        } 
        mH[i] = temp;
    }
    private int maximumChildNode(int i){
        int leftChild = k_thChildNode(i, 1);
        int rightChild = k_thChildNode(i, 2);
        if(CompareStuff(mH[leftChild],mH[rightChild]))return leftChild;
        else return rightChild;
    }
    public T findMaximumNode(){
        if(isEmpty())
            return null;
        return ConvertToTypeT(mH[1]);
    }
    private int parentNode(int i){
        return (i-1)/d;
    }
     
    private int k_thChildNode(int i,int k){
        return d*i  +k;
    }
    public boolean isFull(){
        return currentSize == capacity;
    }
    public boolean CompareStuff(Comparable<T> first, Comparable<T> Second){
        //Your code goes here
        Comparable k = first;
        Comparable t = Second;
        if(k.compareTo(t)>0){
            return true;
        }
        return false;
    }
    public T ConvertToTypeT(Comparable<T> first){
        //Your code goes here
        T konstant = (T)first;
        return konstant;
    }


}