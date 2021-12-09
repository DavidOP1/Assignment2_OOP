package api;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void getNode() {
    }

    @Test
    void getEdge() {
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
        assertEquals(11,graph.getEdge(3,5).getWeight());
    }

    @Test
    void addNode() {
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
        assertEquals(0,graph.getNode(0).getKey());
        assertEquals(1,graph.getNode(1).getKey());
        assertEquals(2,graph.getNode(2).getKey());
        assertEquals(3,graph.getNode(3).getKey());
        assertEquals(4,graph.getNode(4).getKey());
        assertEquals(5,graph.getNode(5).getKey());
    }

    @Test
    void connect() {
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
        assertEquals(11,graph.getEdge(3,5).getWeight());
        assertEquals(3,graph.getEdge(3,5).getSrc());
        assertEquals(5,graph.getEdge(3,5).getDest());
    }

    @Test
    void nodeIter() {
        DirectedWeightedGraph graph=new Graph();
        assertEquals(null,graph.nodeIter());
        Node v0=new Node(1,2,3,0);
        Node v1=new Node(4,5,6,1);
        Node v2=new Node(7,8,9,2);
        Node v3=new Node(10,11,12,3);
        Node v4=new Node(13,14,15,4);
        Node v5=new Node(16,17,18,5);
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
    }

    @Test
    void edgeIter() {
        DirectedWeightedGraph graph=new Graph();
        assertEquals(null,graph.edgeIter());
        Node v0=new Node(1,2,3,0);
        Node v1=new Node(4,5,6,1);
        Node v2=new Node(7,8,9,2);
        Node v3=new Node(10,11,12,3);
        Node v4=new Node(13,14,15,4);
        Node v5=new Node(16,17,18,5);
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
    }

    @Test
    void testEdgeIter() {
        DirectedWeightedGraph graph=new Graph();
        assertEquals(null,graph.edgeIter(2));
        Node v0=new Node(1,2,3,0);
        Node v1=new Node(4,5,6,1);
        Node v2=new Node(7,8,9,2);
        Node v3=new Node(10,11,12,3);
        Node v4=new Node(13,14,15,4);
        Node v5=new Node(16,17,18,5);
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
    }

    @Test
    void removeNode() {
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
        graph.removeNode(0);
        assertEquals(null,graphAlgo.getGraph().getNode(0));
        graph.addNode(v0);
        assertEquals(0,graphAlgo.getGraph().getNode(0).getKey());
    }

    @Test
    void removeEdge() {
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
        graph.removeEdge(0,1);
        assertEquals(null,graphAlgo.getGraph().getEdge(0,1));
    }

    @Test
    void nodeSize() {
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
    }

    @Test
    void edgeSize() {
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
        assertEquals(7,graphAlgo.getGraph().edgeSize());
    }

    @Test
    void getMC() {
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
        DirectedWeightedGraphAlgorithms graphAlgo=new GraphAlgo();
        graphAlgo.init(graph);//We can see initialize works
        assertEquals(13,graphAlgo.getGraph().getMC());
    }
}