public class Node {
    private final String  annotation;
    private final   ODS< Edge > edges;
    
   

    public void addEdge(Node nextNode, String annotation, int compTime){
        edges.Insert(new Edge(annotation, nextNode, compTime));
    }

    public void addEdge(Edge[] list)
    {
        for(int gg=0; gg<list.length; gg++)
        {
            edges.Insert(list[gg]);
        }
    }

    public Edge EdgeToNode(Node NextNode)
    {
        foo();
        for(int mm=0; mm<edges.toArray().length; mm++)
        {
            //System.out.println("testing");
            foo();
            if(((Edge)edges.toArray()[mm]).getNext().equals(NextNode))
                return (Edge)edges.toArray()[mm];
        }
        return null;
    }

    public void foo()
    {

    }
    public String getAnnotation(){
        return annotation;
    }

    @Override
    public boolean equals(Object nod)
    {
        if(nod==null)
             return false;
        if(this==nod)
            return true;
        if(this!=nod) 
        {
            if(nod.getClass() != this.getClass())
                return false;
        }
        if(annotation.equals( ((Node)nod ).annotation) )
            return true;

        return false;
    }

    public Edge[] getEdges(){

        Edge[] xxx= new Edge[edges.toArray().length];

        for(int xx=0; xx<edges.toArray().length; xx++)
        {
            xxx[xx]=(Edge)edges.toArray()[xx];
        }
        return xxx;
    }

    public Node(String annot){
        edges= new ODS<>();
        annotation= new String(annot);
        //TODO: Implement the function
    }

    public String toString(){
        //Provided function, do not alter!!!
        String res = annotation + ":\n";
        if(edges.toArray().length == 0)
            res += "\t" + "No out going edges" + "\n";
        else
            for(Object e: edges.toArray()){
                res += "\t" + e.toString() + "\n";
            } 
        return res;
    }
}
