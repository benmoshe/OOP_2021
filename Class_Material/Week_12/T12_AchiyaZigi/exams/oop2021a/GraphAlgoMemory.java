package oop2021a;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import oop2021a.implementations.GraphAlgo;

public class GraphAlgoMemory extends GraphAlgo implements GraphAlgoInterface{
    private class Tuple<T>{
        T[] args;
        @SafeVarargs
        public Tuple(T... args){
            this.args = Arrays.copyOf(args, args.length);
        }

        @Override
        public int hashCode() {
            return Objects.hash(args);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj){
                return true;
            }
            if(obj == null || obj.getClass() == this.getClass()){
                return false;
            }
            Tuple<T> other = (Tuple<T>)obj;
            if(other.args.length != this.args.length){
                return false;
            }
            for (int i = 0; i < args.length; i++) {
                if(this.args[i] != other.args[i]){
                    return false;
                }
            }
            return true;
            
        }
    }


    int lastMC;
    boolean isConnected;
    Map<Tuple<Integer>, Double> shortestPath;
    @Override
    public void init(GraphAlgoInterface ga) {
        super.init(ga);
        this.lastMC = -1;
        
    }

    @Override
    public boolean isConnected() {
        if(this.lastMC == this.getGraph().getMC()){
            return this.isConnected;
        }
        this.lastMC = this.getGraph().getMC();
        this.isConnected = super.isConnected();
        return this.isConnected;
    }

    @Override
    public double shortestPath(int src, int dest) {
        Tuple<Integer> key = new Tuple<>(src, dest);
        if(this.lastMC == this.getGraph().getMC()){

            if(this.shortestPath.containsKey(key)){
                return this.shortestPath.get(key);
            }
        }
        
        this.lastMC = this.getGraph().getMC();
        this.shortestPath.put(key, super.shortestPath(src, dest));
        return this.shortestPath.get(key);
    }
    
}
