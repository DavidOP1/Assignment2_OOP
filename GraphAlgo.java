package api;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import java.util.*;
import java.util.Hashtable;
import java.util.List;
public class GraphAlgo implements DirectedWeightedGraphAlgorithms{
    DirectedWeightedGraph graph;
    @Override
    public void init(DirectedWeightedGraph g) {
      this.graph= new Graph(g);
    }
    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return new Graph(this.graph);
    }

    @Override
    public boolean isConnected() {
        // do for every vertex
        Set<Integer> nodes = ((Graph)this.graph).getNodeTable().keySet();
        Hashtable<Integer,Boolean> visited=new Hashtable<>();
        //System.out.println("key = " +((Graph)this.graph).getNodeTable().get(0).getKey());
        for (int key: nodes)
        {
            //System.out.println("key = " +key);
            // to keep track of whether a vertex is visited or not
            Iterator<NodeData> data=this.graph.nodeIter();
            //Initilize each node as not visited
            for(int visitKey: nodes){
                visited.put(visitKey,false);
            }
            // start DFS from the first vertex in hastable
            DFS(key, visited);

            // If DFS  doesn't visit all nodes,
            // then the graph is not  connected
            Set<Integer> visitCheck = visited.keySet();
            for (int check:visitCheck)
            {
                if (!visited.get(check)) {
                    return false;
                }
            }
        }

        return true;
    }
    private void DFS(int v, Hashtable<Integer,Boolean> visited){
       Stack<Integer> nodeStack = new Stack<>(); // this stack will store ID's of nodes
        ArrayList<Integer> reverseKeys=new ArrayList<>();
        nodeStack.push(v);
        while(!nodeStack.empty()){
            v= nodeStack.pop(); //popping a node from the stack
            if(visited.get(v)){//If we visited the node already skip it
                continue;
            }
            visited.put(v,true);
            //We do this for all the edges exiting v node but from the end of
            Set<Integer> nodeEdge = ((Node)((Graph)this.graph).getNodeTable().get(v)).Node.keySet();
            for (int key:nodeEdge){
                reverseKeys.add(key);
            }
            for(int i=reverseKeys.size()-1;i>=0;i--){
                int nextNode= reverseKeys.get(i);
                if(!visited.get(nextNode)){
                    nodeStack.push(nextNode);
                }
            }
        }
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        double minDist=0,newDist=0;
        int index=0;
        Set<Integer> nodes = ((Graph)this.graph).getNodeTable().keySet();
        Hashtable<Integer,Boolean> visited=new Hashtable<>();
        Hashtable<Integer,Double> dist=new Hashtable<>();
        Pair<Integer,Double> temp;
        PriorityQueue<Pair<Integer,Double>> distQ = new PriorityQueue<Pair<Integer,Double>>();//Pair(id,distance)
            for(int visitKey: nodes){
                visited.put(visitKey,false);
                dist.put(visitKey, Double.MAX_VALUE);
                System.out.println(visitKey);
            }dist.put(src,0.0);
            distQ.add(new Pair<Integer,Double>(src,0.0));
            while(distQ.size()!=0){
                temp=distQ.poll(); index=temp.id; minDist=temp.weight;// minDist is used in the comparator of the priority queue
                visited.put(index,true);
                Set<Integer> edgeNode = ((Node)this.graph.getNode(index)).Node.keySet();
                if(dist.get(index)<minDist) continue;
                for(int edgeKey: edgeNode){
                   if (visited.get(edgeKey)) continue;
                   newDist=dist.get(index)+((Node)this.graph.getNode(index)).Node.get(edgeKey).getWeight();
                   if(newDist<dist.get(edgeKey)){
                       dist.put(edgeKey,newDist);
                       distQ.add(new Pair<Integer,Double>(edgeKey,newDist));
                   }
                }
            }
            if(dist.get(dest)==Double.MAX_VALUE){
                return -1;
            }
        return dist.get(dest);
    }
    public ArrayList<Integer> findPath(ArrayList<HashMap> ret,int src,int dest){
        //ret at index 0 is the array of the dist
        //ret at index 1 is the array of previous nodes
        System.out.println("distance= " +ret.get(0).get(dest));
        ArrayList<Integer> route= new ArrayList<>();
        if((double)ret.get(0).get(dest)==Double.MAX_VALUE) return null;
        Set<Integer> nodes =  ret.get(1).keySet();
        for (int key:nodes){
            System.out.println("key= "+key +" "+"Value= "+ret.get(1).get(key));
        }
        for (Integer prev=dest;prev!=null;prev=(Integer) ret.get(1).get(prev)) {//problem, what if src=0 is not at the begining of a route but at the middle it will exit in the middle.
            System.out.println("asd");
            route.add(prev);
        }

            Collections.reverse(route);
          return route;
    }
  public ArrayList<Integer> assistShortest(int src,int dest){
      double minDist=0,newDist=0;
      int index=0;
      Set<Integer> nodes = ((Graph)this.graph).getNodeTable().keySet();
      ArrayList<HashMap> ret=new ArrayList<>();
      Hashtable<Integer,Boolean> visited=new Hashtable<>();
      HashMap<Integer,Double> dist=new HashMap<>();
      HashMap<Integer,Integer> previousNode=new HashMap<>();
      Pair<Integer,Double> temp;
      PriorityQueue<Pair<Integer,Double>> distQ = new PriorityQueue<Pair<Integer,Double>>();//Pair(id,distance)
      for(int visitKey: nodes){
          visited.put(visitKey,false);
          dist.put(visitKey, Double.MAX_VALUE);
          previousNode.put(visitKey,null);
          System.out.println(visitKey);
      }dist.put(src,0.0);
      distQ.add(new Pair<Integer,Double>(src,0.0));
      while(distQ.size()!=0){
          temp=distQ.poll(); index=temp.id; minDist=temp.weight;// minDist is used in the comparator of the priority queue
          visited.put(index,true);
          Set<Integer> edgeNode = ((Node)this.graph.getNode(index)).Node.keySet();
          if(dist.get(index)<minDist) continue;
          for(int edgeKey: edgeNode){
              if (visited.get(edgeKey)) continue;
              newDist=dist.get(index)+((Node)this.graph.getNode(index)).Node.get(edgeKey).getWeight();
              if(newDist<dist.get(edgeKey)){
                  previousNode.put(edgeKey,index);
                  dist.put(edgeKey,newDist);
                  distQ.add(new Pair<Integer,Double>(edgeKey,newDist));
              }
          }
      }
      ret.add(dist);
      ret.add(previousNode);
       return findPath(ret,src,dest);
  }
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        ArrayList<Integer> path=assistShortest(src,dest);
        List<NodeData> route = new ArrayList<NodeData>();
        if (path==null) return null;
        for(int index:path){
            System.out.println("index= "+index);
            route.add(((Graph)this.graph).getNodeTable().get(index));
        }
return route;
    }

    @Override
    public NodeData center() {
        //For each node find the biggest shortest distance of node.
        double tempDistance = 0, maxDistance = 0;
        ArrayList<Double> dist = new ArrayList<>();//to keep the distances
        ArrayList<Integer> nodeID = new ArrayList<>();//to keep the id for each nodeID
        Set<Integer> nodes = ((Graph) this.graph).getNodeTable().keySet();
        System.out.println("answer- " + this.isConnected());
        if (this.isConnected()) {
            //PERFORM CODE
            System.out.println("SHALOM");
            for (int i : nodes) {
                maxDistance = 0;
                tempDistance = 0;
                for (int j : nodes) {
                    //On each node calculate shortest dist with each other node.
                    maxDistance = this.shortestPathDist(i, j);
                    if (tempDistance < maxDistance) {
                        tempDistance = maxDistance;
                    }
                }
                dist.add(maxDistance);
                nodeID.add(i);
            }
            return  ((Graph) this.graph).getNodeTable().get(nodeID.indexOf(dist.indexOf(Collections.min(dist))));//the node;
        }
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
   public boolean load(String file) {

        return false;
   }
}
