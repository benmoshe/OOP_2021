package oop2020b;

import java.util.Collection;

import oop2020a.edge_data;
import oop2020a.node_data;

/** This interface represents a directional weighted graph. */
public interface graph {
    public graph copy();

    public void init(graph g);

    /** return the node_data by the node_id (null - if none) */
    public node_data getNode(int key);

    /** return the data of edge(src,dest), null if none */
    public edge_data getEdge(int src, int dest);

    /** add a new node to the graph with the node_data. */
    public void addNode(node_data n);

    /** Connects an edge with weight w between src->dest. */
    public void connect(int src, int dest, double w);

    /**
     * Return a reference (shallow copy) for the
     * collection representing all the nodes in the graph.
     */
    public Collection<node_data> getV();

    /**
     * Return a reference (shallow copy) for the
     * collection representing all the edges getting out of
     * the given node (src)
     */
    public Collection<edge_data> getE(int src);

    /** Delete the node (with the given ID) from the graph */
    public node_data removeNode(int key);

    /** Delete the edge from the graph */
    public edge_data removeEdge(int src, int dest);

    /** return the number of nodes in the graph. */
    public int nodeSize();

    /** return the number of edges (directional graph). */
    public int edgeSize();

    /** return the Mode Count for testing changes in the graph. */
    public int getMC();
}