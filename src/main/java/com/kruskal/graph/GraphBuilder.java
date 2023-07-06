package com.kruskal.graph;

import java.util.List;


public class GraphBuilder {

    private Graph graph;
    public GraphBuilder(){
            graph = new Graph();
    };

    public boolean deleteNode(int nodeId){
        return graph.deleteNode(nodeId);
    }

    public boolean deleteEdge(int edgeId){
        return graph.deleteEdge(edgeId);
    }

    public boolean addNode(int newNodeId){
        return graph.addNode(newNodeId);
    }
    public boolean addEdge(int firstNodeId, int secondNodeId, int weight, int edgeId){
        return graph.addEdge(firstNodeId, secondNodeId, weight, edgeId);
    }

    public boolean isValid(){
        return graph.isValid();
    }

    public void printGraph(){
        graph.printGraph();
    }

    public List<EdgeData> getEdgesData(){
        return graph.getEdgesData();
    }

    public void resetGraph(){
        graph = new Graph();
    }

    public Graph getGraph() {
        return graph;
    }
}
