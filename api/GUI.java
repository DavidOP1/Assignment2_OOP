package api;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;


public class GUI extends JFrame {
    // the scale function helps to scale the nodes location for more bright GUI
    private double scale(double currNode, double minNode, double maxNode) {
        return ((currNode - minNode) / (maxNode - minNode)) * (600+this.graph.nodeSize());
    }

    private DirectedWeightedGraph graph;
    private double[]x ;
    private double[]y;



    public GUI(DirectedWeightedGraph graph, double[]x, double[]y) {
        this.graph = graph;


        this.x = x;
        this.y = y;

        setTitle("Graphs");
        setSize(600, 600);
        setVisible(true);



    }


    @Override
    public void paint(Graphics g) {

        g.setColor(Color.GREEN);

        Graphics2D g2d = (Graphics2D) g;

        double tempX, tempY;
        Iterator<NodeData> NodeIterator= this.graph.nodeIter();
        while (NodeIterator.hasNext()){
            NodeData node = NodeIterator.next();
            tempX = graph.getNode(node.getKey()).getLocation().x();
            tempY = graph.getNode(node.getKey()).getLocation().y();
            g2d.fillOval((int) scale(tempX, x[0],x[x.length-1]), (int)scale(tempY,y[0],y[y.length-1]),12, 12);
        }
        Iterator<EdgeData> EdgesIterator= this.graph.edgeIter();
        g.setColor(Color.BLACK);
        while(EdgesIterator.hasNext()){
            EdgeData edge = EdgesIterator.next();
            double srcX = this.graph.getNode(edge.getSrc()).getLocation().x();
            double srcY = this.graph.getNode(edge.getSrc()).getLocation().y();
            double destX = this.graph.getNode(edge.getDest()).getLocation().x();
            double destY = this.graph.getNode(edge.getDest()).getLocation().y();
            g.drawLine((int) scale(srcX,x[0],x[x.length-1]), (int) scale(srcY,y[0],y[y.length-1]),(int) scale(destX,x[0],x[x.length-1]),(int) scale(destY,y[0],y[y.length-1]));
        }

    }
}