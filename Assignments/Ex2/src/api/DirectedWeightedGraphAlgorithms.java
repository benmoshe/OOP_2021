package api;
import java.util.List;
/**
 * This interface represents a Directed (positive) Weighted Graph Theory Algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected(); // strongly (all ordered pais connected)
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<NodeData> shortestPath(int src, int dest);
 * 5. NodeData center(); // finds the NodeData which minimizes the max distance to all the other nodes.
 *                       // Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
 * 6. List<NodeData> tsp(List<NodeData> cities); // computes a list of consecutive nodes which go over all the nodes in cities.
 *                                               // See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
 * 7. save(file); // JSON file
 * 8. load(file); // JSON file
 *
 *
 * @author boaz.benmoshe
 *
 */
public interface DirectedWeightedGraphAlgorithms {
    /**
     * Inits the graph on which this set of algorithms operates on.
     * @param g
     */
    public void init(DirectedWeightedGraph g);

    /**
     * Returns the underlying graph of which this class works.
     * @return
     */
    public DirectedWeightedGraph getGraph();
    /**
     * Computes a deep copy of this weighted graph.
     * @return
     */
    public DirectedWeightedGraph copy();
    /**
     * Returns true if and only if (iff) there is a valid path from each node to each
     * other node. NOTE: assume directional graph (all n*(n-1) ordered pairs).
     * @return
     */
    public boolean isConnected();
    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    public double shortestPathDist(int src, int dest);
    /**
     * Computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    public List<NodeData> shortestPath(int src, int dest);

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
     * @return the Node data to which the max shortest path to all the other nodes is minimized.
     */
    public NodeData center();
   /**
    * Computes a list of consecutive nodes which go over all the nodes in cities.
    * the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
    * the lower the better.
    * See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
    */
    List<NodeData> tsp(List<NodeData> cities);
    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    public boolean save(String file);

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    public boolean load(String file);
}
