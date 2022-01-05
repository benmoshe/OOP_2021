package oop2021a.implementations;

import java.util.Collection;

import oop2021a.GraphInterface;
import oop2021a.edge_data;

public class Graph implements GraphInterface {
    int edges = 0;

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
        this.edges += 1;

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
        return this.edges;
    }

    @Override
    public int getMC() {
        // TODO Auto-generated method stub
        return 0;
    }

}