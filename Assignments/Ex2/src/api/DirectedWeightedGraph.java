package api;
import java.util.Iterator;
/**
 * This interface represents a Directional Weighted Graph,
 * As in: http://math.oxford.emory.edu/site/cs171/directedAndEdgeWeightedGraphs/
 * The interface has a road-system or communication network in mind - 
 * and should support a large number of nodes (over 100,000).
 * The implementation should be based on an efficient compact representation 
 * (should NOT be based on a n*n matrix).
 */

public interface DirectedWeightedGraph {
	/**
	 * returns the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	public NodeData getNode(int key);
	/**
	 * returns the data of the edge (src,dest), null if none.
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return
	 */
	public EdgeData getEdge(int src, int dest);
	/**
	 * adds a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n
	 */
	public void addNode(NodeData n);
/**
 * Connects an edge with weight w between node src to node dest.
 * * Note: this method should run in O(1) time.
 * @param src - the source of the edge.
 * @param dest - the destination of the edge.
 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
 */
	public void connect(int src, int dest, double w);
	/**
	 * This method returns an Iterator for the
	 * collection representing all the nodes in the graph.
	 * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
	 * @return Iterator<node_data>
	 */
	public Iterator<NodeData> nodeIter();
	/**
	 * This method returns an Iterator for all the edges in this graph.
	 * Note: if any of the edges going out of this node were changed since the iterator was constructed - a RuntimeException should be thrown.
	 * @return Iterator<EdgeData>
	 */
	public Iterator<EdgeData> edgeIter();
	/**
	 * This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
	 * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
	 * @return Iterator<EdgeData>
	 */
	public Iterator<EdgeData> edgeIter(int node_id);

	/**
	 * Deletes the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(k), V.degree=k, as all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key
	 */
	public NodeData removeNode(int key);
	/**
	 * Deletes the edge from the graph,
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */
	public EdgeData removeEdge(int src, int dest);
	/** Returns the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time. 
	 * @return
	 */
	public int nodeSize();
	/** 
	 * Returns the number of edges (assume directional graph).
	 * Note: this method should run in O(1) time.
	 * @return
	 */
	public int edgeSize();
/**
 * Returns the Mode Count - for testing changes in the graph.
 * @return
 */
	public int getMC();
}
