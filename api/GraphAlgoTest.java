package api;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgoTest {

    @org.junit.jupiter.api.Test
    void init() {
        Node v0=new Node(1,2,3,0);
        Node v1=new Node(4,5,6,1);
        Node v2=new Node(7,8,9,2);
        Node v3=new Node(10,11,12,3);
        Node v4=new Node(13,14,15,4);
        Node v5=new Node(16,17,18,5);
        DirectedWeightedGraph graph=new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.addNode(v5);
        assertEquals(6,graph.nodeSize());
        graph.connect(0,1,4);
        graph.connect(0,2,11);
        graph.connect(1,2,5);
        graph.connect(1,3,13);
        graph.connect(2,4,3);
        graph.connect(4,3,11);
        graph.connect(3,5,11);
        assertEquals(7,graph.edgeSize());
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);//We can see initialize works
        assertEquals(9,graphAlgo.shortestPathDist(0,2));
        assertEquals(-1,graphAlgo.shortestPathDist(2,1));
        assertEquals(0,graphAlgo.shortestPathDist(1,1));
        assertEquals(25,graphAlgo.shortestPathDist(2,5));
        assertEquals(17,graphAlgo.shortestPathDist(0,3));
    }

    @org.junit.jupiter.api.Test
    void getGraph() {
        Node v0=new Node(1,2,3,0);
        Node v1=new Node(4,5,6,1);
        Node v2=new Node(7,8,9,2);
        Node v3=new Node(10,11,12,3);
        Node v4=new Node(13,14,15,4);
        Node v5=new Node(16,17,18,5);
        DirectedWeightedGraph graph=new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.addNode(v5);
        assertEquals(6,graph.nodeSize());
        graph.connect(0,1,4);
        graph.connect(0,2,11);
        graph.connect(1,2,5);
        graph.connect(1,3,13);
        graph.connect(2,4,3);
        graph.connect(4,3,11);
        graph.connect(3,5,11);
        assertEquals(7,graph.edgeSize());
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);//We can see initialize works
        assertEquals(6,graphAlgo.getGraph().nodeSize());
        assertEquals(7,graphAlgo.getGraph().edgeSize());
    }

    @org.junit.jupiter.api.Test
    void copy() {
        Node v0=new Node(1,2,3,0);
        Node v1=new Node(4,5,6,1);
        Node v2=new Node(7,8,9,2);
        Node v3=new Node(10,11,12,3);
        Node v4=new Node(13,14,15,4);
        Node v5=new Node(16,17,18,5);
        DirectedWeightedGraph graph=new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.addNode(v5);
        assertEquals(6,graph.nodeSize());
        graph.connect(0,1,4);
        graph.connect(0,2,11);
        graph.connect(1,2,5);
        graph.connect(1,3,13);
        graph.connect(2,4,3);
        graph.connect(4,3,11);
        graph.connect(3,5,11);
        assertEquals(7,graph.edgeSize());
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);//We can see initialize works
        assertEquals(9,graphAlgo.shortestPathDist(0,2));
        assertEquals(-1,graphAlgo.shortestPathDist(2,1));
        assertEquals(0,graphAlgo.shortestPathDist(1,1));
        assertEquals(25,graphAlgo.shortestPathDist(2,5));
        assertEquals(17,graphAlgo.shortestPathDist(0,3));
        DirectedWeightedGraph graphCopy=graphAlgo.copy();
        DirectedWeightedGraphAlgorithms graphAlgoCopy=new GraphAlgo();
        graphAlgoCopy.init(graphCopy);//We can see initialize works
        assertEquals(9,graphAlgoCopy.shortestPathDist(0,2));
        assertEquals(-1,graphAlgoCopy.shortestPathDist(2,1));
        assertEquals(0,graphAlgoCopy.shortestPathDist(1,1));
        assertEquals(25,graphAlgoCopy.shortestPathDist(2,5));
        assertEquals(17,graphAlgoCopy.shortestPathDist(0,3));
    }

    @org.junit.jupiter.api.Test
    void isConnected() {
        Node v0=new Node(1,2,3,0);
        Node v1=new Node(4,5,6,1);
        Node v2=new Node(7,8,9,2);
        Node v3=new Node(10,11,12,3);
        Node v4=new Node(13,14,15,4);
        Node v5=new Node(16,17,18,5);
        DirectedWeightedGraph graph=new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.addNode(v5);
        assertEquals(6,graph.nodeSize());
        graph.connect(0,1,4);
        graph.connect(0,2,11);
        graph.connect(1,2,5);
        graph.connect(1,3,13);
        graph.connect(2,4,3);
        graph.connect(4,3,11);
        graph.connect(3,5,11);
        assertEquals(7,graph.edgeSize());
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);
        assertEquals(false,graphAlgo.isConnected());
        Node v01=new Node(13,14,15,0);
        Node v02=new Node(16,17,18,1);
        DirectedWeightedGraph graphNew=new Graph();
        graphNew.addNode(v01);
        graphNew.addNode(v02);
        graphNew.connect(0,1,4);
        graphNew.connect(1,0,6);
        DirectedWeightedGraphAlgorithms graphAlgoNew=new GraphAlgo();
        graphAlgoNew.init(graphNew);
        assertEquals(true,graphAlgoNew.isConnected());
//        DirectedWeightedGraphAlgorithms graphAlgo1=new GraphAlgo();
//        DirectedWeightedGraphAlgorithms graphAlgo2=new GraphAlgo();
//        DirectedWeightedGraphAlgorithms graphAlgo3=new GraphAlgo();
//        graphAlgo3.load("src/api/G6.json");
//        assertEquals(true,graphAlgo3.isConnected());
////        graphAlgo2.load("src/api/G5.json");
////        assertEquals(true,graphAlgo2.isConnected());
////        graphAlgo1.load("src/api/G4.json");
////        assertEquals(true,graphAlgo1.isConnected());
    }

    @org.junit.jupiter.api.Test
    void shortestPathDist() {
        Node v0=new Node(1,2,3,0);
        Node v1=new Node(4,5,6,1);
        Node v2=new Node(7,8,9,2);
        Node v3=new Node(10,11,12,3);
        Node v4=new Node(13,14,15,4);
        Node v5=new Node(16,17,18,5);
        DirectedWeightedGraph graph=new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.addNode(v5);
        assertEquals(6,graph.nodeSize());
        graph.connect(0,1,4);
        graph.connect(0,2,11);
        graph.connect(1,2,5);
        graph.connect(1,3,13);
        graph.connect(2,4,3);
        graph.connect(4,3,11);
        graph.connect(3,5,11);
        assertEquals(7,graph.edgeSize());
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);
        assertEquals(9,graphAlgo.shortestPathDist(0,2));
        assertEquals(-1,graphAlgo.shortestPathDist(2,1));
        assertEquals(0,graphAlgo.shortestPathDist(1,1));
        assertEquals(25,graphAlgo.shortestPathDist(2,5));
        assertEquals(17,graphAlgo.shortestPathDist(0,3));
    }

    @org.junit.jupiter.api.Test
    void shortestPath() {
        Node v0=new Node(1,2,3,0);
        Node v1=new Node(4,5,6,1);
        Node v2=new Node(7,8,9,2);
        Node v3=new Node(10,11,12,3);
        Node v4=new Node(13,14,15,4);
        Node v5=new Node(16,17,18,5);
        DirectedWeightedGraph graph=new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.addNode(v5);
        assertEquals(6,graph.nodeSize());
        graph.connect(0,1,4);
        graph.connect(0,2,11);
        graph.connect(1,2,5);
        graph.connect(1,3,13);
        graph.connect(2,4,3);
        graph.connect(4,3,11);
        graph.connect(3,5,11);
        assertEquals(7,graph.edgeSize());
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);
        List<NodeData> route= graphAlgo.shortestPath(0,4);
        assertEquals(0,route.get(0).getKey());
        assertEquals(1,route.get(1).getKey());
        assertEquals(2,route.get(2).getKey());
        assertEquals(4,route.get(3).getKey());
    }

    @org.junit.jupiter.api.Test
    void center() {
        Node v0 = new Node(1, 2, 3, 0);
        Node v1 = new Node(4, 5, 6, 1);
        Node v2 = new Node(7, 8, 9, 2);
        Node v3 = new Node(1, 2, 3, 3);
        Node v4 = new Node(4, 5, 6, 4);
        DirectedWeightedGraph graph = new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.connect(0, 1, 1);
        graph.connect(1, 2, 2);
        graph.connect(2, 3, 10);
        graph.connect(2, 4, 4);
        graph.connect(3, 4, 4);
        graph.connect(4, 3, 2);
        graph.connect(4, 0, 5);
        graph.connect(4, 2, 5);
        graph.connect(3, 2, 1);
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);
        assertEquals(4,graphAlgo.center().getKey());
    }

    @org.junit.jupiter.api.Test
    void tsp() {
        Node v0 = new Node(1, 2, 3, 0);
        Node v1 = new Node(4, 5, 6, 1);
        Node v2 = new Node(7, 8, 9, 2);
        Node v3 = new Node(1, 2, 3, 3);
        Node v4 = new Node(4, 5, 6, 4);
        DirectedWeightedGraph graph = new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.connect(0, 1, 1);
        graph.connect(1, 2, 2);
        graph.connect(2, 3, 10);
        graph.connect(2, 4, 4);
        graph.connect(3, 4, 4);
        graph.connect(4, 3, 2);
        graph.connect(4, 0, 5);
        graph.connect(4, 2, 5);
        graph.connect(3, 2, 1);
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);
        List<NodeData> route = new ArrayList<NodeData>();
        List<NodeData> tsp = new ArrayList<NodeData>();
//        Route= 2
//        Route= 4
//        Route= 3
//        Route= 4
//        Route= 3
//        Route= 2
        tsp.add(v2);
        tsp.add(v3);
        tsp.add(v4);
        route = graphAlgo.tsp(tsp);
        assertEquals(2,route.get(0).getKey());
        assertEquals(4,route.get(1).getKey());
        assertEquals(3,route.get(2).getKey());
        assertEquals(4,route.get(3).getKey());
    }

    @org.junit.jupiter.api.Test
    void save() {
        Node v0 = new Node(1, 2, 3, 0);
        Node v1 = new Node(4, 5, 6, 1);
        Node v2 = new Node(7, 8, 9, 2);
        Node v3 = new Node(1, 2, 3, 3);
        Node v4 = new Node(4, 5, 6, 4);
        DirectedWeightedGraph graph = new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.connect(0, 1, 1);
        graph.connect(1, 2, 2);
        graph.connect(2, 3, 10);
        graph.connect(2, 4, 4);
        graph.connect(3, 4, 4);
        graph.connect(4, 3, 2);
        graph.connect(4, 0, 5);
        graph.connect(4, 2, 5);
        graph.connect(3, 2, 1);
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);
       assertEquals(true,graphAlgo.save("Shalom.json"));
    }

    @org.junit.jupiter.api.Test
    void load() {
        DirectedWeightedGraphAlgorithms graphAlgo1=new GraphAlgo();
        DirectedWeightedGraphAlgorithms graphAlgo2=new GraphAlgo();
        DirectedWeightedGraphAlgorithms graphAlgo3=new GraphAlgo();
        //Note the tests here work I just put it under commecnt because the path of the file might be different on the tester computer.
       // graphAlgo.init(graphAlgo);
        //assertEquals(true,graphAlgo1.load("src/api/G4.json"));//enter here file , whatever is called on your pc
        //assertEquals(true,graphAlgo2.load("src/api/G5.json"));//enter here file , whatever is called on your pc
        //assertEquals(true,graphAlgo3.load("src/api/G6.json"));//enter here file , whatever is called on your pc


    }
}