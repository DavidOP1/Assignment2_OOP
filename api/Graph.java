package api;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Iterator;

public class Graph implements DirectedWeightedGraph{
    //private int id;
    private int nodeCount;
    private int edgeCount;
    private int mc;
    private Hashtable<Integer,NodeData> graph;//Integer is id of node.
    //private Hashtable<Integer,NodeData> edges;//first integer is src, second integer is dest
    public Graph(DirectedWeightedGraph g){
        this.graph=((Graph)g).graph;
        //this.edges=((Graph)g).edges;
        this.nodeCount=g.nodeSize();
        this.edgeCount=g.edgeSize();
        this.mc=g.getMC();
    }
    public Graph(){
        this.graph=new Hashtable<Integer,NodeData>();
        //this.edges=new  Hashtable<Integer,NodeData>();
        this.nodeCount=0;
        this.edgeCount=0;
        this.mc=0;
    }
    public Hashtable<Integer,NodeData> getNodeTable(){
        return this.graph;
    }
    @Override
    public NodeData getNode(int key) {
        return graph.get(key);
    }
    @Override
    public EdgeData getEdge(int src, int dest) {
        Node node =(Node) graph.get(src);
       return node.Node.get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        this.graph.put(n.getKey(),new Node(n.getLocation().x(),n.getLocation().y(),n.getLocation().z(),n.getKey()));
        //initilize an empty edge list too
        if(((Node)n).Node!=null){
            this.edgeCount+=((Node)n).Node.size();
        }
        this.nodeCount+=1;
        this.mc+=1;
    }

    @Override
    public void connect(int src, int dest, double w) {
        Node node =(Node) graph.get(src);
        node.Node.put(dest,new Edge(src,dest,w));
        //System.out.println("edge size= "+this.edges.size());
        edgeCount+=1;
        this.mc+=1;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        int tempMC=this.mc;
      //  Set graphSet=graph.entrySet();
        if (this.graph.size()==0) return null;
        Iterator<NodeData> graphIterator= this.graph.values().iterator();
        if (this.mc!=tempMC) throw new RuntimeException();
        return graphIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        int tempMC=this.mc;
        if (this.graph.size()==0) return null;
       // Iterator<EdgeData> edgeIterator=edges.values().iterator();
        ArrayList<EdgeData> edgeList=new ArrayList<>();
        Set<Integer> nodeEdge =this.graph.keySet();
        for(int key: nodeEdge){
            Set<Integer> edgeKey =((Node)this.graph.get(key)).Node.keySet();
            for (int edgeK:edgeKey){
                edgeList.add(((Node)this.graph.get(key)).Node.get(edgeK));
            }
        }
        if (edgeList.size()==0) return null;
        Iterator<EdgeData> edgeIterator= edgeList.iterator();
        if (this.mc!=tempMC) throw new RuntimeException();
        return edgeIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (this.graph.size()==0||((Node)this.graph.get(node_id)).Node==null) return null;
        int tempMC=this.mc;
        ArrayList<EdgeData> edgeList=new ArrayList<>();
        Set<Integer> nodeEdge =((Node)this.graph.get(node_id)).Node.keySet();
        for (int edgeK:nodeEdge){
            edgeList.add(((Node)this.graph.get(node_id)).Node.get(edgeK));
        }
        Iterator<EdgeData> edgeID= edgeList.iterator();
        return edgeID;
    }

    @Override
    public NodeData removeNode(int key) {
        if(graph.get(key)==null) return null;
        NodeData temp=graph.get(key);
        Node node =(Node) graph.get(key);
        this.edgeCount-=node.Node.size();
        Set<Integer> nodes =this.graph.keySet();
        for (int edgeKey: nodes){
            ((Node)this.graph.get(edgeKey)).Node.remove(key);
        }
        //IMPORTANT AF REMOVE ALL EDGES IN HERE FROM THE EDGE LIST
        node.Node.clear();
        graph.remove(key);
        this.nodeCount-=1;
        this.mc+=1;
        return temp;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Node node =(Node) graph.get(src);
        if(node.Node.get(dest)==null) return null;
        EdgeData temp=node.Node.get(dest);
        node.Node.remove(dest); // check here if this already removed above
        this.edgeCount-=1;
        this.mc+=1;
        return temp;
    }

    @Override
    public int nodeSize() {
        return this.nodeCount;
    }

    @Override
    public int edgeSize() {
        return this.edgeCount;
    }

    @Override
    public int getMC() {
        return this.mc;
    }
}
