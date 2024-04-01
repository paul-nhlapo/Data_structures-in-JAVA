public class Path {
    private final  Node  startNode;
    private  Node  endNode;
    private final  ODS<Node>  nodes;
    private final  ODS<Edge>  edges;

    

    public Path(Path other){
        startNode=other.startNode;
        endNode=other.endNode;
        edges= new ODS<>();
        Object[] Edges=other.edges.toArray();
        for(int nn=0; nn<Edges.length; nn++)
        {
            edges.Insert((Edge)Edges[nn]);
        }
        Edges=other.nodes.toArray();
        nodes= new ODS<>();
        for(int nn=0; nn<Edges.length; nn++)
        {
            nodes.Insert((Node)Edges[nn]);
        }
    }

    public int computationalCostOfPath(){
        int tt=-1;

        for(int gg=0; gg<edges.toArray().length; gg++)
        {
            tt+=((Edge)edges.toArray()[gg]).getCompTime();
        }

        return tt+1;
    }

    @SuppressWarnings("unchecked")
    public void appendToPath(Path p){//will do it later
        //TODO: Implement the function
        endNode=p.endNode;
        addEdges(p.edges.toArray());
        addNodes(p.nodes.toArray());
    }

    public void addEdges(Object [] df)
    {
        for(int  v=0; v<df.length; v++)
        {
            edges.Insert((Edge)df[v]);
        }
    }

    public void addNodes(Object [] df)
    {
        for(int  v=0; v<df.length; v++)
        {
            nodes.Insert((Node)df[v]);
        }
    }

    public int NumNodes()
    {
        return nodes.toArray().length;
    }

    public Node endNode()
    {
        return endNode;
    }
    public Edge[] EdgeList()
    {
        Edge[] inj= new Edge[edges.toArray().length];
        for(int v=0; v<inj.length; v++)
        {
            inj[v] = (Edge)edges.toArray()[v];
        }
        return inj;
    }

    public boolean validPath(){
        //TODO: Implement the function
        for(int nn=0; nn<nodes.toArray().length-1; nn++)
        {
            if(  !((Edge)edges.toArray()[nn]).getNext().equals(nodes.toArray()[nn+1])   )
                return false;
        }

        int last=edges.toArray().length-1;

        if(((Edge)edges.toArray()[last]).getNext().equals(endNode))
            return true;

        return false;
    }

    
    public boolean ValidSimplePath()
    {
        for(int c=0; c<edges.toArray().length; c++)
        {

            for(int g=0; g<edges.toArray().length; g++)
            {
                if(g!=c)
                {
                    if(((Edge)edges.toArray()[c]).getNext(). equals(((Edge)edges.toArray()[g]).getNext() ))
                    {
                        return false;
                    }
                }
            }
        }

        return true;

    }
    public Path(Node startNode, Node endNode, Node[] nodes, Edge[] edges){
        if(nodes==null)
        {
            this.nodes = new ODS();  
            this.nodes.Insert(startNode);
            this.nodes.Insert(endNode);
        }
        else{
            this.nodes= new ODS<>(nodes);
        }
        if(edges==null)
        {
            this.edges= new ODS<>();
    
        }
        else{
            this.edges= new ODS<>(edges);
        }
        this.startNode=startNode;
        this.endNode=endNode;
    }
    public String toString(){
        //Provided function, do not alter!!!
        String str = "";
        str += ((Node)nodes.toArray()[0]).getAnnotation();
        for(Object e: edges.toArray()){
            str += e.toString();
        }
        return str;
    }
    
}
