package api;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Graph implements DirectedWeightedGraph{
    private int id;
    private int nodeCount;
    private int edgeCount;
    private int mc;
    private Hashtable<Integer,NodeData> graph;//Integer is id of node.
    private Hashtable<Integer,Hashtable<Integer,EdgeData>> edges;//first integer is src, second integer is dest
    public Graph(DirectedWeightedGraph g){
        this.graph=((Graph)g).graph;
        this.edges=((Graph)g).edges;
        this.nodeCount=g.nodeSize();
        this.edgeCount=g.edgeSize();
        this.mc=g.getMC();
    }
    public Graph(){
        this.graph=new Hashtable<Integer,NodeData>();
        this.edges=new Hashtable<Integer,Hashtable<Integer,EdgeData>>();
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
        graph.put(n.getKey(),n);
        nodeCount+=1;
        this.mc+=1;
    }

    @Override
    public void connect(int src, int dest, double w) {
        Node node =(Node) graph.get(src);
        node.Node.put(dest,new Edge(src,dest,w));
        edgeCount+=1;
        this.mc+=1;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        int tempMC=this.mc;
        Set graphSet=graph.entrySet();
        Iterator<NodeData> graphIterator= graphSet.iterator();
        if (this.mc!=tempMC) throw new RuntimeException();
        return graphIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        int tempMC=this.mc;
        Set edgeSet=edges.entrySet();
        Iterator<EdgeData> edgeIterator=edgeSet.iterator();
        if (this.mc!=tempMC) throw new RuntimeException();
        return edgeIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        int tempMC=this.mc;
        Set edgeIDSet= ((Node)graph.get(node_id)).Node.entrySet();
        Iterator<EdgeData> edgeID= edgeIDSet.iterator();
        if (this.mc!=tempMC) throw new RuntimeException();
        return edgeID;
    }

    @Override
    public NodeData removeNode(int key) {
        if(graph.get(key)==null) return null;
        NodeData temp=graph.get(key);
        Node node =(Node) graph.get(key);
        node.Node.clear();
        graph.remove(key);
        this.nodeCount-=1;
        this.edgeCount-=node.Node.size();
        this.mc+=1;
        return temp;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Node node =(Node) graph.get(src);
        EdgeData temp=node.Node.get(dest);
        node.Node.remove(dest);
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
