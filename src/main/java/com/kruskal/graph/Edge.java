package com.kruskal.graph;

public class Edge {
    private final Node firstNode;
    private final Node secondNode;
    private final int weight;
    private final int id;

    Edge(Node first, Node second, int weight, int id){
        this.firstNode = first;
        this.secondNode = second;
        this.weight = weight;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj instanceof Edge){
            if(this.firstNode.equals(((Edge)obj).firstNode)){
                if(this.secondNode.equals(((Edge)obj).secondNode)){
                    return true;
                }
            }

            if(this.firstNode.equals(((Edge)obj).secondNode)){
                if(this.secondNode.equals(((Edge)obj).firstNode)){
                    return true;
                }
            }
        }
        return false;
    }    

    int getWeight() {
        return weight;
    }

    Node getFirstNode() {
        return firstNode;
    }

    Node getSecondNode() {
        return secondNode;
    }

    int getId() {
        return id;
    }

    Node getAdjacentNode(Node start){
        if(firstNode.equals(start)){
            return secondNode;
        }
        return firstNode;    
    }

    @Override
    public String toString(){
        return "(EdgeId:"+ id +" FirstNodeId:"+ firstNode.getId()+" SecondNodeId:"+secondNode.getId() +" Weight"+ weight + ")";
    }
}
