package oop2020b;

import java.util.Stack;

import oop2020a.edge_data;
import oop2020a.node_data;

// import oop2021a.implementations.Graph;

public class DGraph_Undo extends MyGraph {
    protected Stack<graph> cachedGraphs;

    public DGraph_Undo() {
        super();
        cachedGraphs = new Stack<>();

    }

    @Override
    public void addNode(node_data n) {
        this.cachedGraphs.push(this.copy());
        super.addNode(n);
    }

    @Override
    public node_data removeNode(int key) {
        this.cachedGraphs.push(this.copy());
        return super.removeNode(key);
    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        this.cachedGraphs.push(this.copy());
        return super.removeEdge(src, dest);
    }

    @Override
    public void connect(int src, int dest, double w) {
        this.cachedGraphs.push(this.copy());
        super.connect(src, dest, w);
    }

    public void undo() {
        if (!this.cachedGraphs.empty()) {
            this.init(this.cachedGraphs.pop());
        }
    }
}
