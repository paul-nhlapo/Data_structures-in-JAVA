// /**
//  * Name:Paul Nhlapo
//  * Student Number: u18108378
//  */



// public class OrganisingList {

//     public ListNode root = null;

//     public OrganisingList() {

//     }
    
//     /**
//      * Calculate the sum of keys recursively, starting with the given node until the end of the list
//      * @return The sum of keys from the current node to the last node in the list
//      * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
//      */
//     public static int sumRec(ListNode node) {
//         // Your code here...
//         if(node.next==null)
//         {
//             return node.key;
//         }
//         else{

//             return node.key + sumRec(node.next);
//         }
//     }

//     /**
//      * Calculate and set the data for the given node.
//      * The calculated data for the given node
//      * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
//      */
//     public static int dataRec(ListNode node) {
//         // Your code here...
//         if(node.next==null)
//         {
//             return node.key;
//         }
//         else{
//             node.data= (node.key * sumRec(node)) - dataRec(node.next);
//             return node.data;
//         }
       
//     }

//     /**
//      * Calculate the data field of all nodes in the list using the recursive functions.
//      * DO NOT MODIFY!
//      */
//     public void calculateData() {
//         if (root != null) {
//             dataRec(root);
//         }
//     }

//     /**
//      * Retrieve the data for the node with the specified key and swap the
//      * accessed node with its predecessor, then recalculate data fields.
//      *  The data of the node before it has been moved,
//      * otherwise 'null' if the key does not exist.
//      */
//     public Integer getData(Integer key) {
//         // Your code here...
//        ListNode DataNode=root;
//        Integer tempData=DataNode.data;
//        while(DataNode!=null)
//        {
//            if(DataNode.data==key && DataNode.data!=null){
//                DataNode.data=DataNode.key;
//                calculateData();
//            }
//            else {
//                return null;
//            }
//        }
//         return tempData;  
//     }

//     /**
//      * Delete the node with the given key.
//      * calculateData() should be called after deletion.
//      * If the key does not exist, do nothing.
//      */
//     public void delete(Integer key) {
//         // Your code here...
//         ListNode nodeToDelete = root;
//         // ListNode TempData = null;
//         if(contains(key))
//         {
//             while(nodeToDelete!=null)
//             {
//                 if(nodeToDelete.next.key==key){

//                    if(nodeToDelete!=null){
//                        nodeToDelete = nodeToDelete.next;
//                        calculateData();
//                    }
//                    break;
//                 }
//                 else{
//                     // TempData = nodeToDelete;
//                     nodeToDelete = nodeToDelete.next;
//                 }

//             }
//         }
//     }
// abstract class OrganisingList {

//     public ListNode root = null;

//     public OrganisingList() {

//     }
    
//     /**
//      * Calculate the data field of all nodes in the list using the recursive functions.
//      */
//     public void calculateData() {
//         if (root != null) {
//             dataRec(root);
//         }
//     }

//     ////// You may not change any code above this line //////

//     ////// Implement the functions below this line //////

    
//     //Recursive functions

//     /**
//      * Calculate the sum of keys recursively, starting with the given node until the end of the list
//      * @return The sum of keys from the current node to the last node in the list
//      * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
//      */
//     public static int sumRec(ListNode node) {

//         //Your code goes here
//         if(node.next==null){
//             return node.key;
//         }else{
//             return sumRec(node.next)+node.key;
//         }

//     } //TODO:imp sumRec

//     /**
//      * Calculate and set the data for the given node.
//      * @return The calculated data for the given node
//      * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
//      */
//     public static int dataRec(ListNode node) {

//         //Your code goes here
//         if(node.next==null){
//             node.data= node.key;
//             return node.key;
//         }else{
//             node.data = (node.key* sumRec(node))-dataRec(node.next);
//             return (node.key* sumRec(node))-dataRec(node.next);
//         }

//     } //TODO:imp dataRec


//     //Organising List functions

//     /**
//      * Retrieve the node with the specified key, move the node as required and recalculate all data fields.
//      * @return The node with its data value before it has been moved, otherwise 'null' if the key does not exist.
//      * Implement only in specific subclass!
//      */
//     public abstract ListNode searchNode(Integer key);

//     /**
//      * Check if a key is contained in the list
//      * @return true if the key is in the list, otherwise false
//      */
//     public boolean contains(Integer key) {
//         ListNode curr;
//         //Your code goes here
//         if(root==null){ //if the list is empty
//             return false;
//         }else if(root.key==key){
//             return true;
//         }else{
//             curr=root;
//             while(curr.next!=null && curr.key!=key){
//                 curr=curr.next;
//             }
//             if(curr.key==key){
//                 return true;
//             }
//         }
//         return false;
//     }


//     /**
//      * Insert a new key into the linked list.
//      * New nodes should be inserted at the back.
//      * calculateData() should be called after insertion.
//      * Duplicate keys should not be added.
//      */
//     public void insert(Integer key) {

//         //Your code goes here
//         ListNode curr;
//         ListNode newNode = new ListNode(key);
//         if(root==null){ //make newNode the root when the list does not yet exist and or is empty
//             root = newNode;
//             calculateData();
//         }else if(contains(key)){   //if key is already present then do nothing
//             return;
//         }else{
//             curr=root;
//             while(curr.next!=null){
//                 curr=curr.next;
//             }
//             curr.next=newNode;
//             calculateData();
//         }
//     }
	
//     /**
//      * Delete the node with the given key.
//      * calculateData() should be called after deletion.
//      * If the key does not exist, do nothing.
//      */
//     public void delete(Integer key) {

//         //Your code goes here
//         if(root==null){ //if list is empty
//             return;
//         }else if(!contains(key)){     //key is not in list
//             return;
//         }else if(root.key==key){ //root is being deleted
//             root=root.next;
//             calculateData();
//         }else{  //key is present and list does exist
//             ListNode prev,curr;
//             prev=curr=root;

//             while(curr.next !=null && curr.key!=key){   //loop through list till you find the desired node
//                 prev=curr;
//                 curr=curr.next;
//             }
//             prev.next = curr.next;  //uncouple curr node from list
//             curr = null;
//            calculateData();
//         }
//     }


//     /**
//      * Insert a new key into the linked list.
//      * 
//      * New nodes should be inserted to the back
//      * Duplicate keys should not be added.
//      */
//     public void insert(Integer key) {
//         // Your code here...    
//         ListNode TrasVerse= root;
        
//         if(TrasVerse== null)
//         {
//             ListNode newData= new ListNode(key);
//             newData.next = null;
//             TrasVerse=newData;
//             calculateData();
//         }
//         else{
//             ListNode newData= new ListNode(key);
//             while(TrasVerse.next!=null)
//             {
//                 if(TrasVerse.key==key)
//                 {
//                     return;
//                 }
//                 else{
//                     newData=TrasVerse.next;
//                 }
//             }
//             TrasVerse.next=newData;
//             calculateData();
//         }
//     }

//     /**
//      * Check if a key is contained in the list
//      * true if the key is in the list, otherwise false
//      */
//     public Boolean contains(Integer key) {
//         // Your code here...
//         ListNode current=root;
//         while(current!=null){
//             if(current.key!=key)
//             {
//                 current=current.next;
//                 calculateData();
//             }
//             else{
//                return true;
//             }
//         }
//         calculateData();
//         return false;
//     }


//     /**
//      * Return a string representation of the Linked List.
//      * DO NOT MODIFY!
//      */
//     public String toString() {
//         if (root == null) {
//             return "List is empty";
//         }

//         String result = "";
//         for (ListNode node = root; node != null; node = node.next) {
//             result = result.concat("[K: " + node.key + ", D: " + node.data + "]");

//             if (node.next != null) {
//                 result = result.concat(" ");
//             }
//         }

//         return result;
//     }

//     /**
//      * Return a string representation of the linked list, showing only the keys of nodes.
//      * DO NOT MODIFY!
//      */
//     public String toStringKeysOnly() {

//         if (root == null) {
//             return "List is empty";
//         }

//         String result = "";
//         for (ListNode node = root; node != null; node = node.next) {
//             result = result + node.key;

//             if (node.next != null) {
//                 result = result.concat(", ");
//             }
//         }

//         return result;
//     }

    
// }
abstract class OrganisingList {

    public ListNode root = null;

    public OrganisingList() {

    }
    
    /**
     * Calculate the data field of all nodes in the list using the recursive functions.
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    
    //Recursive functions

    /**
     * Calculate the sum of keys recursively, starting with the given node until the end of the list
     * @return The sum of keys from the current node to the last node in the list
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {

        //Your code goes here
        if(node.next==null){
            return node.key;
        }else{
            return sumRec(node.next)+node.key;
        }

    } //TODO:imp sumRec

    /**
     * Calculate and set the data for the given node.
     * @return The calculated data for the given node
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {

        //Your code goes here
        if(node.next==null){
            node.data= node.key;
            return node.key;
        }else{
            node.data = (node.key* sumRec(node))-dataRec(node.next);
            return (node.key* sumRec(node))-dataRec(node.next);
        }

    } //TODO:imp dataRec


    //Organising List functions

    /**
     * Retrieve the node with the specified key, move the node as required and recalculate all data fields.
     * @return The node with its data value before it has been moved, otherwise 'null' if the key does not exist.
     * Implement only in specific subclass!
     */
    public abstract ListNode searchNode(Integer key);

    /**
     * Check if a key is contained in the list
     * @return true if the key is in the list, otherwise false
     */
    public boolean contains(Integer key) {
        ListNode curr;
        //Your code goes here
        if(root==null){ //if the list is empty
            return false;
        }else if(root.key==key){
            return true;
        }else{
            curr=root;
            while(curr.next!=null && curr.key!=key){
                curr=curr.next;
            }
            if(curr.key==key){
                return true;
            }
        }
        return false;
    }


    /**
     * Insert a new key into the linked list.
     * New nodes should be inserted at the back.
     * calculateData() should be called after insertion.
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {

        //Your code goes here
        ListNode curr;
        ListNode newNode = new ListNode(key);
        if(root==null){ //make newNode the root when the list does not yet exist and or is empty
            root = newNode;
            calculateData();
        }else if(contains(key)){   //if key is already present then do nothing
            return;
        }else{
            curr=root;
            while(curr.next!=null){
                curr=curr.next;
            }
            curr.next=newNode;
            calculateData();
        }
    }
	
    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {

        //Your code goes here
        if(root==null){ //if list is empty
            return;
        }else if(!contains(key)){     //key is not in list
            return;
        }else if(root.key==key){ //root is being deleted
            root=root.next;
            calculateData();
        }else{  //key is present and list does exist
            ListNode prev,curr;
            prev=curr=root;

            while(curr.next !=null && curr.key!=key){   //loop through list till you find the desired node
                prev=curr;
                curr=curr.next;
            }
            prev.next = curr.next;  //uncouple curr node from list
            curr = null;
           calculateData();
        }
    }
