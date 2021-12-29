package oop2020a;

public interface Iterator<T> {
    public boolean hasNext() throws Exception; // return true iff there are still “unvisited” elements of T.
    public T next() throws Exception; // return the “next” element of type T.
    }