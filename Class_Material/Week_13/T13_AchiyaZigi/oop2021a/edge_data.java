package oop2021a;

/**
 * This interface represents the set of operations applicable
 * on a directional edge(src,dest)
 */
public interface edge_data {
    /** @return the id of the source node of this edge. */
    public int getSrc();

    /** @return the id of the destination of this edge. */
    public int getDest();

    /** @return the weight of this edge (positive value). */
    public double getWeight();
}