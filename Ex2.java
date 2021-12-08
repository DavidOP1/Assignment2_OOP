import api.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


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

        // ****** Add your code here ******
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
            double x = Double.valueOf(posAns[0]);
            double y = Double.valueOf(posAns[1]);
            double z = Double.valueOf(posAns[2]);
            ans.addNode(new Node(x, y, z, (int) id));
        }

        for (Object arrayEdge : arrayEdges) {
            JSONObject edges = (JSONObject) arrayEdge;
            long src = (long) edges.get("src");
            double w = (double) edges.get("w");
            long dest = (long) edges.get("dest");
            ans.connect((int) src, (int) dest, w);

        }


        // ********************************
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
        // ****** Add your code here ******
        ans.init(getGrapg(json_file));
        // ********************************
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) throws IOException, ParseException {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        DirectedWeightedGraph graph = getGrapg(json_file);
        // ****** Add your code here ******
        double[] nodesArrX = new double[graph.nodeSize()];
        double[] nodesArrY = new double[graph.nodeSize()];
        for (int i =0; i < graph.nodeSize(); i++){
           nodesArrX[i] = graph.getNode(i).getLocation().x();
           nodesArrY[i] = graph.getNode(i).getLocation().y();
        }
        Arrays.sort(nodesArrX);
        Arrays.sort(nodesArrY);


        new GUI(graph, nodesArrX, nodesArrY);



        // ********************************
    }


    public static void main(String[] args) throws IOException, ParseException{
//       Graph graph = (Graph) getGrapg("src/api/G1.json");

        runGUI("src/api/G1.json");

    }
}


