package oop2021b;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import oop2020a.node_data;
import oop2021a.GraphInterface;
import oop2021a.edge_data;

public class Graph implements GraphInterface {

    @Override
    public boolean hasNode(int key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addNode(int key) {
        // TODO Auto-generated method stub

    }

    @Override
    public void connect(edge_data e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void connect(int src, int dest, double w) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<Integer> getV() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeNode(int key) {
        // TODO Auto-generated method stub

    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int nodeSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int edgeSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMC() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean isSimetric() {
        return allNoneSymetric().isEmpty();
    }

    private ArrayList<edge_data> allNoneSymetric() {
        // Set<edge_data> noneSymetric = new HashSet<>();
        ArrayList<edge_data> noneSymetric = new ArrayList<>();
        for (int nkey : this.getV()) {
            var out_edges = this.getE(nkey);
            for (edge_data out_edge : out_edges) {
                int dest = out_edge.getDest();
                edge_data in_edge = this.getEdge(dest, nkey);
                if (in_edge == null || in_edge.getWeight() != out_edge.getWeight()) {
                    noneSymetric.add(out_edge);
                }
            }
        }
        return noneSymetric;
    }

}
