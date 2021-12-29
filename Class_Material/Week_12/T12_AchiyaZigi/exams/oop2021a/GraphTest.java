package oop2021a;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;

import oop2021a.implementations.Graph;

public class GraphTest {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void buildRandom(){
        int nodes = 1000;
        int edges = 10000;
        GraphInterface g = new Graph();
        for (int i = 0; i < nodes; i++) {
            g.addNode(i);
        }
        while(g.edgeSize() < edges){
            int src = (int)(Math.random() * 1000);
            int dest = (int)(Math.random() * 1000);
            g.connect(src, dest, 1);
        }

    }
}
