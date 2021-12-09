package api;
import jdk.swing.interop.SwingInterOpUtils;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Hashtable;
import java.util.List;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms{
    DirectedWeightedGraph graph;
    public GraphAlgo(){
        this.graph=new Graph();
    }
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
        int vert=0;//here choose random verted id
        //System.out.println("key = " +((Graph)this.graph).getNodeTable().get(0).getKey());
        for(int visitKey: nodes){
            vert=visitKey;
            break;
        }
        for(int visitKey: nodes){
            visited.put(visitKey,false);
        }
        DFS(this.getGraph(),vert,visited);
        for(int key:nodes){
            if (!visited.get(key)) return false;
        }
        for(int visitKey: nodes){
            visited.put(visitKey,false);
        }
        //create new graphs with reversed edges:
        DirectedWeightedGraph temp=new Graph();
        for (int key:nodes){
            temp.addNode(this.graph.getNode(key));
        }
        for (int key:nodes) {
            Set<Integer> edgeNode = ((Node) this.graph.getNode(key)).Node.keySet();

            for (int index : edgeNode) {
                temp.connect(index, key, ((Node) this.graph.getNode(key)).Node.get(index).getWeight());
            }
        }
        //System.out.println( "fuck= "+this.graph.nodeSize());
        DFS(temp,vert,visited);
        System.out.println("asd");
        for(int key:nodes){
            if (!visited.get(key)) return false;
        }
        return true;
    }
    private void DFS(DirectedWeightedGraph graph,int v, Hashtable<Integer,Boolean> visited){
       Stack<Integer> nodeStack = new Stack<>(); // this stack will store ID's of nodes
        nodeStack.push(v);
        while(!nodeStack.empty()){
            v= nodeStack.pop(); //popping a node from the stack
            if(visited.get(v)){//If we visited the node already skip it
                continue;
            }
            visited.put(v,true);
            //We do this for all the edges exiting v node but from the end of
            Set<Integer> nodeEdge = ((Node)((Graph)graph).getNodeTable().get(v)).Node.keySet();
            for (int key:nodeEdge){
                if(!visited.get(key)){
                    nodeStack.push(key);
                }
            }
            }
        }

    @Override
    public double shortestPathDist(int src, int dest) {
        //Check if node
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
                //System.out.println(visitKey);
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
       // System.out.println("distance= " +ret.get(0).get(dest));
        ArrayList<Integer> route= new ArrayList<>();
        if((double)ret.get(0).get(dest)==Double.MAX_VALUE) return null;
        Set<Integer> nodes =  ret.get(1).keySet();

        for (Integer prev=dest;prev!=null;prev=(Integer) ret.get(1).get(prev)) {//problem, what if src=0 is not at the begining of a route but at the middle it will exit in the middle.
            //System.out.println("asd");
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
          //System.out.println(visitKey);
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
        if (this.graph.getNode(src)==null||this.graph.getNode(dest)==null) return null;
        ArrayList<Integer> path=assistShortest(src,dest);
        List<NodeData> route = new ArrayList<NodeData>();
        if (path==null) return null;
        for(int index:path){
           // System.out.println("index= "+index);
            route.add(((Graph)this.graph).getNodeTable().get(index));
        }
return route;
    }
    public double maxDistNode(int src) {//same as shortest path dist but we return a list of all distances between node src to every node in graph
        //Check if node
        double minDist=0,newDist=0,tempDistance=0,maxDistance=0,save=0;
        int index=0;
        Set<Integer> nodes = ((Graph)this.graph).getNodeTable().keySet();
        Hashtable<Integer,Boolean> visited=new Hashtable<>();
        Hashtable<Integer,Double> dist=new Hashtable<>();
        Pair<Integer,Double> temp;
        PriorityQueue<Pair<Integer,Double>> distQ = new PriorityQueue<Pair<Integer,Double>>();//Pair(id,distance)
        for(int visitKey: nodes){
            visited.put(visitKey,false);
            dist.put(visitKey, Double.MAX_VALUE);
            //System.out.println(visitKey);
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
        Set<Integer> distIndex= dist.keySet();
        for (int in:distIndex){
            tempDistance=dist.get(in);
            if (tempDistance>maxDistance){
                maxDistance=tempDistance;
            }
        }
        return maxDistance;
    }
    @Override
    public NodeData center() {
        //For each node find the biggest shortest distance of node.
        double  maxDistance = 0;
        ArrayList<Double> dist = new ArrayList<>();//to keep the distances
        ArrayList<Integer> nodeID = new ArrayList<>();//to keep the id for each nodeID
        Set<Integer> nodes = ((Graph) this.graph).getNodeTable().keySet();
        //System.out.println("answer- " + this.isConnected());
        if (this.isConnected()) {
            //PERFORM CODE
            //System.out.println("SHALOM");
            for (int i : nodes) {
                maxDistance = maxDistNode(i);
                    //On each node calculate shortest dist with each other node
                //if (i==639)System.out.println("Distance" +maxDistance);
                dist.add(maxDistance);
                nodeID.add(i);
            }
            System.out.println("Index= " +Collections.min(dist));
            return  ((Graph) this.graph).getNodeTable().get(nodeID.get(dist.indexOf(Collections.min(dist))));//the node;
        }
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> cityRoute=new ArrayList<>();
        List<List<NodeData>> cityRouteList=new ArrayList<>();
        List<NodeData> routeTemp=new ArrayList<>();
        for(int i=0;i<cities.size()-1;i++){
            //do route with j=(i+1)%mod citis.size() to close full circle route with cities
            routeTemp=this.shortestPath(cities.get(i).getKey(),cities.get((i+1)%(cities.size())).getKey());
            if (routeTemp==null) return null;
            cityRouteList.add(routeTemp);
        }
        for (int i=0;i<cityRouteList.size();i++){
            if (i!=0){
                for (int j=1;j<cityRouteList.get(i).size();j++){
                    cityRoute.add(cityRouteList.get(i).get(j));
                }}else{
                for (int j=0;j<cityRouteList.get(i).size();j++){
                    cityRoute.add(cityRouteList.get(i).get(j));
                }
                }
            }
        return cityRoute;
        }



    @Override
    public boolean save(String file) {

        JSONArray jsonArrayEdges = new JSONArray();
        JSONArray jsonArrayNodes = new JSONArray();
        JSONObject jsonObject = new JSONObject();



        Iterator<EdgeData> EdgesIterator= this.graph.edgeIter();
        while(EdgesIterator.hasNext()){
            JSONObject obj = new JSONObject();
            EdgeData edge = EdgesIterator.next();
            obj.put("src", edge.getSrc());
            obj.put("w", edge.getWeight());
            obj.put("dest",edge.getDest());
            jsonArrayEdges.add(obj);
            jsonObject.put("Edges", jsonArrayEdges);
        }
        Iterator<NodeData> NodeIterator= this.graph.nodeIter();
        while (NodeIterator.hasNext()){
            JSONObject temp = new JSONObject();
            NodeData node = NodeIterator.next();
            temp.put("pos", this.graph.getNode(node.getKey()).getLocation().x()+","+this.graph.getNode(node.getKey()).getLocation().y()+","+this.graph.getNode(node.getKey()).getLocation().z());
            temp.put("id", node.getKey());
            jsonArrayNodes.add(temp);
            jsonObject.put("Nodes", jsonArrayNodes);
        }


        try (FileWriter jsonFile = new FileWriter(file)) {
            jsonFile.write(jsonObject.toJSONString());
            // jsonFile.write(jsonObject.toJSONString());
            jsonFile.flush();

        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean load(String file) {
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(file)) {
            Object obj = parser.parse(reader);
            JSONObject emp = (JSONObject) obj;


            JSONArray arrayEdges = (JSONArray) emp.get("Edges");
            JSONArray arrayNodes = (JSONArray) emp.get("Nodes");

            for (Object arrayNode : arrayNodes) {
                JSONObject nodes = (JSONObject) arrayNode;
                String pos = (String) nodes.get("pos");
                long id = (long) nodes.get("id");
                String[] posAns = pos.split(",");
                double x = Double.parseDouble(posAns[0]);
                double y = Double.parseDouble(posAns[1]);
                double z = Double.parseDouble(posAns[2]);
                this.graph.addNode(new Node(x, y, z, (int) id));
                //System.out.println("Node size= "+graph.nodeSize());
            }

            for (Object arrayEdge : arrayEdges) {
                JSONObject edges = (JSONObject) arrayEdge;
                long src = (long) edges.get("src");
                double w = (double) edges.get("w");
                long dest = (long) edges.get("dest");
                this.graph.connect((int) src, (int) dest, w);
                //System.out.println("edge size= "+graph.edgeSize());
            }
        } catch (IOException e){
            return false;
        } catch (ParseException p){
            return false;
        }
        return true;
    }
}
