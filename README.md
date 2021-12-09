# Assignment2_OOP
Assignment2 in course OOP, programming language : JAVA

Name: David Ehevich, ID:212757405
Name: Liel Zilberman, ID: 212480974

Sources which helped us understand the alogrithms which we used in this assignment:

1.Dijkstra algorithm for shortest path: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

2.Center of graph: https://www.youtube.com/watch?v=szGXkfx9nC8

3.Checking if a graph is strongly connected: https://en.wikipedia.org/wiki/Strongly_connected_component

4.tsp(Travelling salesman problem): https://en.wikipedia.org/wiki/Travelling_salesman_problem

Explaining the assignment: 

The subject of the assignment is graphs. We were given interfaces which we need to implement.//complete here

NodeData interface: gives us information about the node(vertex), ID , location, etc...

EdgeData interface: gives us information about the edge, since it's a directed graph , this edge has source node and destination node. We can also get the weight of this node.

GeoLocation interface: gives us information about the location of the node in the graph, (x,y,z).

DirectedWeightedGraph interface: this interface allows us to add new nodes to the graph with existed edges, we can also add new edges to the graph if we want. Just like adding we can also remove edges and nodes, we can get nodes by their ID's also get edges by entering their source node and destination node. You can algo get an iterator for all of the edges of the graph, all of the nodes of the graph, or all of the edges of a specific node. We have a variable called MC which keeps track with changes made to the graph , so when creating the iterator a change in the graph has occured, a RunTime error will be thrown. You can also get the amount of nodes and edges in the graph.

Note: Getting a edge, adding a node, adding or removing a edge from the graph, are done in O(1) complexity, removing a node from the graph is done in O(K) complexity , when 
V.degree=k.

DirectedWeightedGraphsAlgorithms: this is the most important interface, in here we implement all of the algroithms which are being used in the graph.
this interface will allow you to get the shortest path distance between 2 nodes, and get the routh which it takes to get to the node.
We can also check if the current graph is strongly connected, get it's center and do TSP on a given list of citites.
This interface will also allow you to load graph data from a JSON file, or if you create a graph your self , you will be able to save this graph into a JSON file as well.

Note: I added my own class which is called Pair which hold  the values,(index,distance) index of the node we currently travel with , and the distance we made with it so far.
I created this class to use it in our priority queue which is being used in the Dijkstra algorithm, we pull items form the priority queue by the smallest distance available in the queue, and this being done by the comparator, in the Pair class.

Explaining the algorithms:

Center:					 	
since my ShortestPathDist function creates a hashtable which stores the shortest distance between the src node to each node in the graph, we will only take the biggest shortest distance in that list. we do this to every node in a given graph , and then taking the smallest biggest shortest distance out of all them.

ShortestPathDist:  
this function returns the shortest path between 2 nodes, in here we used the Dijkstra algorithm.

ShortestPath: 
Works exactly the same as ShortestPathDist, but we always keep the parent node, so we can track and save the route.

isConnected:
We initialize a hashtable of visited nodes, saved by keys(ID's) of the node and the values are boolean.
We run a DFS on the graph , and if all of the nodes are not visited , then the graph is not strongly connected. then we reset the hashtable of the visited nodes,
then we reverse all of the edges of the graph and create a new graph , we run DFS on the newly created graph, and if all of the edges are not visited then the graph is not strongly connected, if both DFS's pass then the graph is strongly connected.

TSP(Travelling salesman problem):
We are given a list of cities which we need to visit, between every 2 pairs of cities , I run the function ShortestPath and since it returnes the shortest route between those 2 cities, I add it to the list which I need to return at the end.

Time Results:(Everything tested in Junit5)

Loading from json:

1000 nodes 9000 edges: 125 ms

10000 nodes 90000 edges: 410 ms

100000 nodes 900000 edges: 4 sec 954 ms


Checking if a graph is connected:

1000 nodes 9000 edges: 157 ms

10000 nodes 90000 edges: 517 ms

100000 nodes 900000 edges: 6 sec 551 ms

Checking center of graph:

1000 nodes 9000 edges: 1 sec 748 ms

10000 nodes 90000 edges: 3 minutes 37 sec

100000 nodes 900000 edges: timeout











