import api.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) throws IOException, ParseException {
        DirectedWeightedGraph ans = new Graph();

        // ** Add your code here **
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(json_file);
        Object obj = parser.parse(reader);
        JSONObject emp = (JSONObject) obj;



        JSONArray arrayEdges=(JSONArray)emp.get("Edges");
        JSONArray arrayNodes = (JSONArray) emp.get("Nodes");

        for (Object arrayNode : arrayNodes) {
            JSONObject nodes = (JSONObject) arrayNode;
            String pos = (String) nodes.get("pos");
            long id = (long) nodes.get("id");
            String[] posAns = pos.split(",");
            double x = Double.parseDouble(posAns[0]);
            double y = Double.parseDouble(posAns[1]);
            double z = Double.parseDouble(posAns[2]);
            ans.addNode(new Node(x, y, z, (int) id));
        }

        for (Object arrayEdge : arrayEdges) {
            JSONObject edges = (JSONObject) arrayEdge;
            long src = (long) edges.get("src");
            double w = (double) edges.get("w");
            long dest = (long) edges.get("dest");
            ans.connect((int) src, (int) dest, w);

        }


        // ************
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) throws IOException, ParseException {
        DirectedWeightedGraphAlgorithms ans = new GraphAlgo();
        DirectedWeightedGraph asny = new Graph();

        // ** Add your code here **

        ans.init(asny);
        ans.load(json_file);
        // ans.save("G2.json");
        // ************
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) throws IOException, ParseException {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);

        // ** Add your code here **
        //frame
        JFrame frame = new JFrame();
        frame.setTitle("Graphs");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Menu
        JMenu File = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu algorithm = new JMenu("Algorithm");
        JMenuItem drawGraph = new JMenuItem("draw graph");
        JMenuItem save = new JMenuItem("save graph");
        JMenuItem load = new JMenuItem("load graph");
        JMenuItem addNode = new JMenuItem("add a node");
        JMenuItem removeNode = new JMenuItem("remove a node");
        JMenuItem connectNodes= new JMenuItem("connect nodes");
        JMenuItem disconnectNodes = new JMenuItem("disconnect nodes");
        JMenuItem isConnected = new JMenuItem("is connected");
        JMenuItem center = new JMenuItem("center node");
        JMenuItem shortestPath = new JMenuItem("shortest path");

        algorithm.add(shortestPath);
        algorithm.add(center);
        algorithm.add(isConnected);
        edit.add(disconnectNodes);
        edit.add(connectNodes);
        edit.add(removeNode);
        edit.add(addNode);
        File.add(load);
        File.add(drawGraph);
        File.add(save);
        // when shortest path button has been clicked, the shortest path will be visible on the screen by messages
        shortestPath.addActionListener(e -> {
            int i;
            int src = Integer.parseInt(JOptionPane.showInputDialog("enter the src"));
            int dest = Integer.parseInt(JOptionPane.showInputDialog("enter the dest"));
            if (alg.shortestPath(src,dest)==null) JOptionPane.showMessageDialog(null,"there is no path");
            else{
                for (i = 0; i < alg.shortestPath( src,  dest).size() -1; i++){
                    JOptionPane.showMessageDialog(null,alg.shortestPath(src, dest).get(i).getKey()+"->     click ok for the next key");
                }
                JOptionPane.showMessageDialog(null, alg.shortestPath(src, dest).get(i).getKey());
            }});
        // when the center button has been clicked, the center node will be visible on the screen
        center.addActionListener(e -> JOptionPane.showMessageDialog(null, alg.center().getKey()));
        // when the is connected button has been clicked, the boolean value will be visible on the screen
        isConnected.addActionListener(e -> JOptionPane.showMessageDialog(null, alg.isConnected()));
        //when the disconnect nodes button has been clicked, the chosen edge will be removed from the graph
        disconnectNodes.addActionListener(e -> {
            int src = Integer.parseInt(JOptionPane.showInputDialog("int! enter the src:"));
            int dest = Integer.parseInt(JOptionPane.showInputDialog("int! enter the dest"));
            alg.getGraph().removeEdge(src, dest);
        });
        // when the connect nodes button has been clicked, the chosen edge will be added to the graph
        connectNodes.addActionListener(e -> {
            int src = Integer.parseInt(JOptionPane.showInputDialog("int! enter the src:"));
            int dest = Integer.parseInt(JOptionPane.showInputDialog("int! enter the dest:"));
            double w = Double.parseDouble(JOptionPane.showInputDialog("Double! enter the weight:"));
            alg.getGraph().connect(src, dest, w);
        });
        // when the remove node button has been clicked, the chosen node will be removed from the graph
        removeNode.addActionListener(e -> {
            int key = Integer.parseInt(JOptionPane.showInputDialog("int! enter the key of the node:"));
            alg.getGraph().removeNode(key);
        });
        // when the add node button has been clicked, the chosen node will be added to the graph
        addNode.addActionListener(e -> {
            double x = Double.parseDouble(JOptionPane.showInputDialog("Double! x value of the new node:"));
            double y = Double.parseDouble(JOptionPane.showInputDialog("Double! y value of the new node:"));
            alg.getGraph().addNode(new Node(x, y,0, alg.getGraph().nodeSize()));
        });
        // when the load button has been clicked, the chosen graph will be loaded
        load.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog("file name or path, e.g: 'G1.json' or 'src/G1.json");
                alg.load(fileName);

        });
        // when the save button has been clicked, the curren graph will be saved to a chosen json file
        save.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog("file name, eg: 'G1.json' after you close the window the file will appear");
            alg.save(fileName);
        });

        double[] nodesArrX = new double[alg.getGraph().nodeSize()];
        double[] nodesArrY = new double[alg.getGraph().nodeSize()];
        Iterator<NodeData> NodeIterator= alg.getGraph().nodeIter();
        int i = 0;
        while (NodeIterator.hasNext()){
            NodeData node = NodeIterator.next();
            nodesArrX[i] = alg.getGraph().getNode(node.getKey()).getLocation().x();
            nodesArrY[i] = alg.getGraph().getNode(node.getKey()).getLocation().y();
            i++;
        }
        Arrays.sort(nodesArrX);
        Arrays.sort(nodesArrY);


        // when the draw graph button has been clicked, the current graph will be visible on the screen
        drawGraph.addActionListener(e -> new GUI(alg.getGraph(),nodesArrX,nodesArrY));
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(File);
        menuBar.add(edit);
        menuBar.add(algorithm);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);






        // ************
    }


    public static void main(String[] args) throws IOException, ParseException{
        //Graph graph = (Graph) getGrapg("G1.json");


        runGUI(args[0]);
        // getGrapgAlgo("G1.json");


    }
}