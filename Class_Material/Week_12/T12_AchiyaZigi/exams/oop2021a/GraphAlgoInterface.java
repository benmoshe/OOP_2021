package oop2021a;

public interface GraphAlgoInterface {
    public void init(GraphAlgoInterface g);
    public GraphInterface getGraph();
    public boolean isConnected();
    public double shortestPath(int src, int dest);
    
}
