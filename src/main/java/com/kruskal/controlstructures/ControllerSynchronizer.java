package com.kruskal.controlstructures;

import com.kruskal.gui.ShapeController;
import com.kruskal.graph.Graph;
import com.kruskal.graph.GraphBuilder;

public class ControllerSynchronizer {
    private final ShapeController shapeController;
    private final GraphBuilder graphBuilder;
    private int freeNodeId;
    private int freeEdgeId;

    public ControllerSynchronizer(ShapeController shapeController, GraphBuilder graphBuilder){
        this.graphBuilder = graphBuilder;
        this.shapeController = shapeController;
        freeNodeId = 1;
        freeEdgeId = 1;
    }

    public boolean addNode(double xCoordinate, double yCoordinate){
        if(graphBuilder.addNode(freeNodeId)){
            shapeController.addNode(xCoordinate, yCoordinate, freeNodeId);
            freeNodeId++;
            return true;
        }
        return false;
    }

    public boolean addEdge(int firstNodeId, int secondNodeId, int weight){
        if(graphBuilder.addEdge(firstNodeId, secondNodeId, weight, freeEdgeId)){
            shapeController.addEdge(firstNodeId, secondNodeId, weight, freeEdgeId);
            freeEdgeId++;
            return true;
        }
        return false;
    }

    public boolean deleteNode(int nodeId){
        if(graphBuilder.deleteNode(nodeId)){
            shapeController.removeNode(nodeId);
            return true;
        }
        return false;
    }

    public boolean deleteEdge(int edgeId){
        if(graphBuilder.deleteEdge(edgeId)){
            shapeController.removeEdge(edgeId);
            return true;
        }
        return false;
    }

    public void eraseGraph(){
        freeEdgeId = 1;
        freeNodeId = 1;
        graphBuilder.resetGraph();
        shapeController.clear();
    }

    public Graph getGraph(){
        return graphBuilder.getGraph();
    }
}
