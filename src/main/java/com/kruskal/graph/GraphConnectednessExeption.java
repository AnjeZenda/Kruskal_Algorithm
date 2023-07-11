package com.kruskal.graph;

public class GraphConnectednessExeption extends RuntimeException{
    public GraphConnectednessExeption(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "FileFormatException{" + getMessage() + "}";
    }
}
