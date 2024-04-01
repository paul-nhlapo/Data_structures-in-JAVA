public class CFG {
    private Node startNode;
    private ODS<Node> nodes;
    private ODS<Edge> edges;
    private ODS<Node> exitNodes;

    public CFG(){
        nodes= new ODS<Node>();
        edges= new ODS<Edge>();
        exitNodes= new ODS<>();
        startNode=null;
        //TODO: Implement the function
    }

    public CFG(Node[] nodes, Edge[] edges, Node sNode, Node[] exitNodes){
        
        this.nodes=new ODS<Node>(nodes);
        this.edges= new ODS<Edge>(edges);
        startNode=sNode;
        this.exitNodes= new ODS<Node>(exitNodes);

        //TODO: Implement the function
    }

    public CFG(CFG other){
        //TODO: Implement the function
        startNode= new Node(other.startNode.getAnnotation());
        Edge[] ed= other.startNode.getEdges();
        startNode.addEdge(ed);
        //////////////////////////////////
        Object[] arr;
        nodes= new ODS<>();
        Node n;
        Edge[] arr2;
        arr= other.nodes.toArray();
        for(int mm=0; mm<arr.length; mm++)
        {
            n= new Node( ((Node)arr[mm]).getAnnotation()  );
            arr2=((Node)arr[mm]).getEdges();
            n.addEdge(arr2);
            nodes.Insert(( n )); 
        }
        ////////////////////////////////////
        edges= new ODS<>();
        Edge e;
        arr=other.edges.toArray();
        for(int c=0; c<arr.length; c++)
        {
            e=(Edge)arr[c];
            edges.Insert( new Edge(e.getAnnotation(), e.getNext(), e.getCompTime()));
        }
        /////////////////////////////////
        exitNodes= new ODS<>();
        arr=other.exitNodes.toArray();
        for(int j=0; j<arr.length; j++)
        {
            n = new Node( ((Node)arr[j]).getAnnotation()  );
            arr2=((Node)arr[j]).getEdges();
            n.addEdge(arr2);
            exitNodes.Insert(n);
        }
    }

    public boolean isValid(){
        if(startNode==null)
            return false;
        //
        Object[] ar= exitNodes.toArray();
        if(ar.length<=0)
            return false;
        
        ar=nodes.toArray();
        for(int bb=0; bb<ar.length; bb++)
        {
            if(NotENode((Node) ar[bb]))
            {
                if(!RENode((Node) ar[bb]))
                    return false;

                //System.out.println("Testing");
            }
        }
        return true;
    }

    public boolean NotENode(Node node)
    {
        if(!exitNodes.Contains(node))
            return true;
        
        return false;
    }

    public boolean RENode(Node node)
    {
        Object[] ar= exitNodes.toArray();
        for(int c=0; c<ar.length; c++)
        {
            if(isReachable(node, (Node)ar[c]))
                return true;
        }
        return false;
    }

    public boolean isSESE(){

        if(isValid() && exitNodes.toArray().length==1)
            return true;

        return false;
        //TODO: Implement the function
    }

    public CFG[] splitGraph(){
        if(isSESE())
        {
            CFG[] gh= new CFG[1];
            gh[0]=this;
            return gh;
        }
        else if(isValid())
        {
            CFG[] cfgs= new CFG[exitNodes.toArray().length];
            for(int cfg=0; cfg<cfgs.length; cfg++)
            {
                cfgs[cfg]= new CFG();
                cfgs[cfg].addStartNode(new Node(startNode.getAnnotation()));
                cfgs[cfg].addExitNode(new Node  (  ((Node)exitNodes.toArray()[cfg]).getAnnotation()  )    );

            }

            for(int v=0; v<nodes.toArray().length; v++)
            {
                for(int e=0; e<exitNodes.toArray().length; e++)
                {
                    if(isReachable((Node)(nodes.toArray()[v]), (Node)(exitNodes.toArray()[e])))
                    {
                        if(cfgs[e].nodes.toArray().length>=2)
                        {
                            Node add1=( (Node)(nodes.toArray()[v])) ;
                            cfgs[e].addNode(( (Node)(nodes.toArray()[v])).getAnnotation(), isSESE());
                            //Node add2=(Node)(nodes.toArray()[nodes.toArray().length-1]);

                        }
                        else{
                            cfgs[e].addNode(  ( (Node)(nodes.toArray()[v])).getAnnotation()  );
                        }
                        
                    }
                }
            }

            for(int cf=0; cf<exitNodes.toArray().length; cf++ )//cfg
            {
                Object[] nod=cfgs[cf].nodes.toArray();
                for(int bn=0; bn<nod.length-1; bn++)//node
                {
                    Node n=(Node)nod[bn];
                    Node nn=(Node)exitNodes.toArray()[cf];
                    Node s= getNode(n.getAnnotation());
                    Node s1=getNode(nn.getAnnotation());
                 
                    if(isReachable(s, s1))
                    {   
                        /*if(bn !=0)]
                        {
                            Node vn1=(Node)nod[bn-1];
                            Node vn2=(Node)nod[bn];
                           // System.out.println(vn1);
                            //System.out.println(vn2);
                            if(isReachable(vn1, vn2))
                            {
                                Path shortedPaths=shortestCompTimePath(vn1, vn2);
                                Edge[] e=shortedPaths.EdgeList();
                                for(int c=0; c<e.length; c++)
                                {
                                    n=NodeWithEdge(e[c]);
                                    if(n != null){
                                        
                                        n=(Node)cfgs[cf].nodes.Search(n);
                                        n.addEdge(e[c].getNext(), e[c].getAnnotation(), e[c].getCompTime());
                                    }
                                    cfgs[cf].edges.Insert(e[c]);
                                }

                            }
                        } */                 
                        Path shortedPaths=shortestCompTimePath(s, s1);
                        Edge[] e=shortedPaths.EdgeList();
    
                        for(int c=0; c<e.length; c++)
                        {
                            n=NodeWithEdge(e[c]);
                            if(n != null){
                                
                                n=(Node)cfgs[cf].nodes.Search(n);
                            
                                n.addEdge(e[c].getNext(), e[c].getAnnotation(), e[c].getCompTime());
                            }
                            cfgs[cf].edges.Insert(e[c]);
                        }
                       
                        Node n1=(Node)nod[bn];
                        Node nn1=(Node)nod[bn+1] ;
                        Node ss1= getNode(n1.getAnnotation());
                        Node s11=getNode(nn1.getAnnotation());
                        if(isReachable(ss1, s11))
                        {
                            Path shortedPaths1=shortestCompTimePath(ss1, s11);
                            Edge[] e1=shortedPaths1.EdgeList();
        
                            for(int c=0; c<e1.length; c++)
                            {
                                n1=NodeWithEdge(e1[c]);
                                if(n1 != null){
                                    
                                    n1=(Node)cfgs[cf].nodes.Search(n1);
                                
                                    n1.addEdge(e1[c].getNext(), e1[c].getAnnotation(), e1[c].getCompTime());
                                }
                                cfgs[cf].edges.Insert(e1[c]);
                            }

                        }
                    
                    }

                    /*if(isReachable(n1, nn1))
                    {
                        Path shortedPaths=shortestCompTimePath(n1, nn1); 
                        Edge[] e=shortedPaths.EdgeList();
                        for(int c=0; c<e.length; c++)
                        {
                            n=NodeWithEdge(e[c]);

                        }
                    }*/
                }
            }     
            return cfgs;
        }
        else 
            return new CFG[0];
    }

    public boolean isReachable(Node startNode, Node goalNode){
        int[] arr= FindReachablableNode(startNode);

        if(arr[IndexOf(goalNode)] != Integer.MIN_VALUE)
            return true;

        return false;
    }

    public int compTimeRequired(Path p){
        return p.computationalCostOfPath();
    }

   // public Edge[] listOfEdges(Node )

    public Path shortestCompTimePath(Node sN, Node gN){

        int[] pre =FindReachablableNode(sN);
        int startIndex=IndexOf(gN);
        ODS<Node> nn= new ODS<>();
        Node node=null;;
        do{

            node=(Node)nodes.toArray()[startIndex];
            nn.Insert(node);
            startIndex=pre[startIndex];
            //System.out.println("testing " + node);
        }
        while(node !=  sN);
        ////////////////////////
        Object[] n=nn.toArray();
        Node[] nod= new Node[n.length];
        int index=0;
        for(int c=n.length-1; c>=0; c-- )
        {
            nod[index++]=(Node)n[c];
        }
        /////////////////////
        ODS<Edge> ee= new ODS<>();
        for(int c=0; c<nod.length-1; c++)
        {
            ee.Insert(nod[c].EdgeToNode(nod[c+1]));
        }
        //////////////////
        n=ee.toArray();
        Edge[] edf= new Edge[n.length];
        int jhg=0;
        for(int c=0; c<n.length; c++)
        {
            edf[c]=(Edge)n[c];
            jhg++;
        }
        startIndex=jhg;
        return new Path(sN, gN, nod, edf);

    }

    public int[] FindReachablableNode(Node sN)
    {
        int[][] pre=setUp();
        ODS<Node> nn= new ODS<>();
        pre[0][IndexOf(sN)]=0;//distance

        nn.Insert(sN);
        Node curr;
        while(nn.toArray().length>0)
        {
            curr= (Node)nn.toArray()[0];
            if(nn.Remove(curr))
            {
                Edge[] dd=curr.getEdges();
                for(int vv=0; vv<dd.length; vv++)
                {
                   // System.ou
                    if(pre[0][IndexOf(curr)] + dd[vv].getCompTime() <  pre[0][IndexOf(dd[vv].getNext())] )
                    {
                        pre[0][IndexOf(dd[vv].getNext())]=pre[0][IndexOf(curr)] + dd[vv].getCompTime();
                        pre[1][IndexOf(dd[vv].getNext())]=IndexOf(curr);

                        Node v=(Node)nodes.toArray()[IndexOf(dd[vv].getNext())];
                        if(!nn.Contains(v))
                            nn.Insert(v);

                    }
                }
            }
        }
        /*for(int ii=0; ii<5; ii++)
        {
            System.out.println(pre[0][ii] +  "  " + pre[1][ii]);
        }*/
        return CutIt(pre);

    }

    public int [] CutIt(int [][] arr)
    {
        int[] arr2 = new int[arr[0].length];

        for(int cc=0; cc<arr[0].length; cc++)
        {
            arr2[cc]=arr[1][cc];
        }
        return arr2;
    }

    public int IndexOf(Node sN)
    {
        Object[] arr = nodes.toArray();
        for(int cv=0; cv<arr.length; cv++)
        {
            if( ((Node)arr[cv]).equals(sN) )
                return cv;
        }
        return Integer.MIN_VALUE;
    }

    public int[][] setUp()
    {
        int size=nodes.toArray().length;
        int [][] ar = new int[2][nodes.toArray().length];
        for(int b=0; b<size; b++)
        {
            ar[0][b]=Integer.MAX_VALUE;
            ar[1][b]=Integer.MIN_VALUE;
        }

        return ar;
    }

    public Path[] getPrimePaths(){
        //TODO: Implement the function
        Path [] simple=getSimplePaths();
        String [] path= new String[simple.length];

        for(int n=0; n<simple.length; n++)
        {
            path[n]=simple[n].toString();
        }
        //count
        int c=0;
        for(int n=0; n<simple.length; n++)
        {
            if(!subp(path, path[n]))
                c++;
        }

        Path[] mk= new Path[c];
        c=0;
        for(int n=0; n<simple.length; n++)
        {
            if(!subp(path, path[n])){
                mk[c]=simple[n];
                c++;
            }
        }
        return mk;

    }

    public boolean subp(String[] AllPaths, String single)
    {
        for(int vv=0; vv<AllPaths.length; vv++)
        {
            if(AllPaths[vv] != single)
            {
                if( AllPaths[vv].contains(single))
                    return true;
            }
        }
        return false;
    }


    public Path[] getSimplePaths()
    {

        return SimplePaths();
    }

    public Path[] SimplePaths()
    {
        int MaxSimplePath=nodes.toArray().length -1;
        ODS<Path> simple= new ODS<>();
        for(int gg=0; gg<=MaxSimplePath; gg++)
        {
            Node[] m = {(Node)nodes.toArray()[gg]};
            simple.Insert( new Path((Node)nodes.toArray()[gg], (Node)nodes.toArray()[gg], m, null)  );

        }
        int currentLength=1;

        for(;currentLength<=MaxSimplePath;)
        {
            int numOfSimple=simple.toArray().length;
            for(int ll=0; ll<numOfSimple; ll++)
            {
                if(((Path)simple.toArray()[ll]).NumNodes()==currentLength)
                {
                    Node last=((Path)simple.toArray()[ll]).endNode();
                    Edge [] list= last.getEdges();

                    for(int b=0; b<list.length; b++)
                    {
                        Node sN=last;
                        Node eN=list[b].getNext();
                        Node [] addN={sN, eN};
                        Edge[ ] addE={list[b]};

                        Path copy= new Path(((Path)simple.toArray()[ll]));
                        Path add= new Path(sN, eN, addN, addE);
                        copy.appendToPath(add);

                        if(copy.ValidSimplePath())
                            simple.Insert(copy);
                    }
                }
            }
            currentLength++;
        }

        return Convert(simple);
    }

   // public 

    public Path[] Convert(ODS<Path> simple)
    {
        Object[] path= simple.toArray();
        Path[] rg= new Path[path.length];
        for(int vv=0; vv<path.length; vv++)
        {
            rg[vv]= new Path((Path)path[vv]);
        }
        return rg;
    }

    public void addNode(String annotation){
        Node vb= new Node(annotation);
        nodes.Insert(vb);
    }
    public void addNode(String annotation, boolean en){
        Node vb= new Node(annotation);
        Node last= nodes.Search(  (Node) nodes.toArray()[nodes.toArray().length-1]  );
        nodes.Remove(last);
        nodes.Insert(vb);
        nodes.Insert(last);
    }

    public void addNode(Node node){
        nodes.Insert(node);
    }

    public void addEdge(String annotation, Node fromNode, Node toNode, int computationalTime){
        if(computationalTime>=0){
        Edge vb= new Edge(annotation, toNode, computationalTime);
        edges.Insert(vb);
        fromNode.addEdge(toNode, annotation, computationalTime);
        }
        //TODO: Implement the function
    }

    public void addExitNode(Node n){
        if(n!= null){
        exitNodes.Insert(n);
        nodes.Insert(n);
        }
    }

    public void addStartNode(Node n){
        //TODO: Implement the function
        if(startNode==null)
            startNode=n;

        nodes.Insert(n);
    }

    public Node NodeWithEdge(Edge e)
    {
        for(Object f: nodes.toArray())
        {
            for(Edge e1: ((Node)f).getEdges()){
                if(e1.equals(e))
                {
                    return (Node)f;
                }
            }
        }

        return null;
    }

    public String toString() {
        //Provided function, do not alter!!!
        String res = "";
        for(Object n: nodes.toArray()){
            res += n.toString();
        }
        return res;
    }

    public Node getNode(String annotation){
       Node n= new Node(annotation);

       return nodes.Search(n);
    }
}
