package oop2020a;


public class EdgeIterator implements Iterator<edge_data> {

    private graph g;
    private edge_data[] edges;
    private int idx;
    private int mc;

    public EdgeIterator(graph g){
        this.edges = new edge_data[g.edgeSize()];
        this.idx = 0;
        int i = 0;
        for (node_data n : g.getV()) {
            for (edge_data e : g.getE(n.getKey())) {
                this.edges[i++] = e;
            }
        }
        this.g = g;
        this.mc = g.getMC();
    }

    @Override
    public boolean hasNext() throws Exception {
        if(this.g.getMC() != this.mc){
            throw new Exception("cant iterate over changable graph");
        }
        return this.idx < this.edges.length;
    }

    @Override
    public edge_data next() throws Exception {
        if(this.g.getMC() != this.mc){
            throw new Exception("cant iterate over changable graph");
        }
        if(this.hasNext()){
            return this.edges[idx++];
        }
        return null;
    }
    
}
