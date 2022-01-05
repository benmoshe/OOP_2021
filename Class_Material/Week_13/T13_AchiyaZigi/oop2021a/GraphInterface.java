package oop2021a;

import java.util.Collection;

import oop2020a.node_data;

public interface GraphInterface {
    public boolean hasNode(int key);

    public edge_data getEdge(int src, int dest);

    public void addNode(int key);

    public void connect(edge_data e);

    public void connect(int src, int dest, double w);

    public Collection<Integer> getV();

    public Collection<edge_data> getE(int node_id);

    public void removeNode(int key);

    public edge_data removeEdge(int src, int dest);

    public int nodeSize();

    public int edgeSize();

    public int getMC();
}
