public class Edge {
    private final String annotation;
    private final Node nextNode;
    private final int computationalTime;

    public Edge(String annot, Node nextNode, int compTime){
        annotation= new String(annot);
        this.nextNode=nextNode;
        this.computationalTime=compTime;
    }

    public Node getNext(){
        return nextNode;
    }

    public String getAnnotation(){
        return annotation;
    }

    public int getCompTime(){
        return computationalTime;
    }

    @Override
    public boolean equals(Object eg)
    {
        if(eg==null)
             return false;
        if(this!=eg) 
        {
            if(eg.getClass() != this.getClass())
                return false;
        }
        if(!annotation.equals( ((Edge)eg ).annotation) )
            return false;

        return true;
    }

    public String toString(){
        //Provided function, do not alter!!!
        String res = "-> " + annotation + " -[" + computationalTime + "]-"; 
        
        if(nextNode == null)
            res += "> NULL";
        else
            res += "> " + nextNode.getAnnotation();

        return res;
    }
}
