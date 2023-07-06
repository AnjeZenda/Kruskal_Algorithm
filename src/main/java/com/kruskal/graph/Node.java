package com.kruskal.graph;

import java.util.ArrayList;
import java.util.List;


public class Node {
    private final int id;
    private final List<Edge> adjacentEdges;
    Node(int id){
        this.id = id;
        adjacentEdges = new ArrayList<Edge>();
    };

    public int getId() {
        return id;
    }

    void addEdge(Edge newEdge){
        adjacentEdges.add(newEdge);
        newEdge.getAdjacentNode(this).adjacentEdges.add(newEdge);
    }

    private Edge findEdge(int edgeId){
        for(Edge currentEdge: adjacentEdges){
            if(currentEdge.getId() == edgeId){
                return currentEdge;
            }
        }
        return null;
    }

    void deleteEdge(int edgeId){
        Edge currentEdge = findEdge(edgeId);
        if(currentEdge != null){
            currentEdge.getFirstNode().adjacentEdges.remove(currentEdge);
            currentEdge.getSecondNode().adjacentEdges.remove(currentEdge);
        }
    }

    List<Edge> getAdjacentEdges() {
        return adjacentEdges;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj instanceof Node){
            if (this.id == ((Node)obj).id){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("NodeId:" + id + ",  Edges:\n");
        for (Edge edge : adjacentEdges) {
            builder.append("\t" + edge + "\n");
        }
        return builder.toString();
    }
}
