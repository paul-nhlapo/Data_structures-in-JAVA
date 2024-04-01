/*
    Name:Paul Nhlapo
    StudentNo: u18108378
*/ 
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
    public int getnumNodes(BSTNode<T> tempRoot){
        if(tempRoot==null)
        {
            return 0;
        }
        return 1 + getnumNodes(tempRoot.left) + getnumNodes(tempRoot.right);
    }

    Object[] myReturnArray=null;
    
    public int numNodes(){
        BSTNode<T> tempRoot = root;
        if(tempRoot!=null){
            return getnumNodes(tempRoot);
        }
        return 0;
    }
    int index=0;
    public Object[] toArray(){
        BSTNode<T> tempRoot = root;
        if(tempRoot!=null){
            myReturnArray =new Object[numNodes()];
            index = 0;
            CtoArray(tempRoot);
            return myReturnArray;
        }
        return null;
    }
//The Helper function for ToArray
public void CtoArray(BSTNode<T> tempRoot){
    if (tempRoot != null){
        CtoArray(tempRoot.left);     
        myReturnArray[index]=tempRoot;
        index+=1;    
        CtoArray(tempRoot.right);   
    }
}

    public boolean contains(T val){
        BSTNode<T> p= root;
        if(!isEmpty()){

            while(p!=null){
                
                if(p.getVal()==val)
                {
                    return true;
                }
                else{
                    return false;
                }
            }
            return false;
        }
        else
        return false;
    }

    public boolean isEmpty(){
        if(root==null){
            return true;
        }
        else{
            return false;
        }
    }

    public BSTNode<T> find(T val){
        BSTNode<T> p=root;
        while(p!=null)
            if(val.equals(p.getVal()))
                return p;
            else if(val.compareTo(p.getVal())<0)
                p=p.left;
            else p=p.right;
        return null; 
    }

    //Helper function to get the height of the tree
    public int getHeight(BSTNode<T> temp)
    {
        if(temp== null)
        { 
            return 0;
        }
        int Left_height=getHeight(temp.left);
        int Right_height=getHeight(temp.right);

        if(Left_height>Right_height)
        return Left_height + 1;
        else{
            return Right_height + 1;
        }
    }

    public int height(){
        BSTNode<T> p =root;
        if(root==null)
        { 
            return -1;
        }
        else{
            return getHeight(p);
        }
    }
    String OutPutStrin="";
    Object [] FinalArry=null;
    //Traversing through the Array
    public void TArrayOrder(BSTNode<T> node, int level){
        int count=0;
        if(node == null){
            return;
        }
        else if(level == 0)
        {
            FinalArry[count]=node;
            count++;
        }
        else
        {
            TArrayOrder(node.left, level-1);
            TArrayOrder(node.right, level-1);
        }
    }
 
   
    public Object[] nodesOnHeight(int h){
        OutPutStrin = "";
        int count = 0;
        BSTNode<T> node = root;
        if(node == null && h<0 && h>height()){
            if(h==0)
            {
                Object FinalArry[] = new Object[1];
                FinalArry[0] = node;
                return FinalArry;
            }
            else
            {
                FinalArry = new Object[numNodes()];
                count = 0;
                TArrayOrder(root, h);
                return FinalArry;
            }
        }
        return null;
    }

    public String inorder(BSTNode<T> node) {
		boolean flag = true;
		if (node != null) 
        {
			String Output = "";
			Output += inorder(node.left);
			if (flag) 
            {
				Output += node.toString() + "\n";
			} 
            else 
            {
				Output += node.getVal().toString() + " ";
			}
			Output += inorder(node.right);
			return Output;
		}
		return "";
	}

    public String DFT(){
       return  inorder(root);
        
    }

    //CurrentLevel
    public void Order_BFT(BSTNode<T> node, int level){
        if(node == null){
            return;
        }
        else if(level == 0)
        {
            OutPutStrin+=node.toString()+";";
        }
        else{
            Order_BFT(node.left, level-1);
            Order_BFT(node.right, level-1);
        }
    }

    public String BFT(){
        if(isEmpty()){
            return "";
        }
        else
        {
            BSTNode<T> node=root;
            OutPutStrin ="";
            int level=getHeight(node);
            for(int p=0 ;p<level; p++)
            {
                Order_BFT(root, p);
            }
            return OutPutStrin.substring(0,OutPutStrin.length()-1);
        }
    }

    public T maxVal(){
        if(root==null){
            return null;
        }
        BSTNode<T> max=root;
        while(max.right!=null)
        {
            max=max.right;
        }
        return max.getVal();

    }

    public T minVal(){
        if(root==null){
            return null;
        }

        BSTNode<T> min=root;
        while(min.left!=null)
        {
            min=min.left;
        }
        return min.getVal();
    }

    //ADD HELPER FUNCTIONS HERE
}
