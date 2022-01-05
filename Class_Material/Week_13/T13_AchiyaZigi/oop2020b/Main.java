package oop2020b;

import oop2020a.edge_data;
import oop2020a.graph;
import oop2020a.node_data;

public class Main {
    public static boolean contains(graph g1, graph g2) {
        for (node_data n : g2.getV()) {
            if (g1.getNode(n.getKey()) == null) {
                return false;
            }
            for (edge_data e2 : g2.getE(n.getKey())) {
                int src = e2.getSrc();
                int dest = e2.getDest();
                double w = e2.getWeight();
                var e1 = g1.getEdge(src, dest);
                if (e1 == null || e1.getWeight() != w) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int relation(graph g1, graph g2) {
        if (contains(g1, g2)) {
            if (contains(g2, g1)) {
                return 0;
            }
            return 1;
        }
        if (contains(g2, g1)) {
            return 2;
        }
        return -1;
    }
}
