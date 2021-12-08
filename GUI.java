package api;

import javax.swing.*;
import java.awt.*;



public class GUI extends JFrame {
    private double scale(double currNode, double minNode, double maxNode) {
        return ((currNode - minNode) / (maxNode - minNode)) * 600;
    }

    private DirectedWeightedGraph graph;
    private double[]x;
    private double[]y;


    public GUI(DirectedWeightedGraph graph, double[]x,double[]y) {
        this.graph = graph;
        this.x = x;
        this.y = y;
        setTitle("Graphs");
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
   

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.GREEN);

        Graphics2D g2d = (Graphics2D) g;


        for (int i =0; i < graph.nodeSize(); i++) {
           double tempX = graph.getNode(i).getLocation().x();
           double tempY = graph.getNode(i).getLocation().y();
           g2d.fillOval((int) scale(tempX, x[0],x[x.length-1]), (int)scale(tempY,y[0],y[y.length-1]),12, 12);
        }
        for (int i = 0; i < graph.edgeSize(); i++){
            //double srcX =graph.getEdge();
        }
    }
}

