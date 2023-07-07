package com.kruskal.shapeview;

import javafx.scene.shape.Line;
import javafx.util.Pair;

public class EdgeView extends Line {
    private int idNumber;
    private int weight;
    private Pair<NodeView, NodeView> adjacentNodes;

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public EdgeView(double v, double v1, double v2, double v3, int idNumber, int weight) {
        super(v, v1, v2, v3);
        this.idNumber = idNumber;
        this.weight = weight;
    }

    public EdgeView(int idNumber, int weight) {
        super();
        this.idNumber = idNumber;
        this.weight = weight;
    }
    public void setAdjacentNodes(NodeView startNode, NodeView endNode) {
        adjacentNodes = new Pair<>(startNode, endNode);
    }

    public NodeView getStartNode() {
        return adjacentNodes.getKey();
    }
    public NodeView getEndNode() {
        return adjacentNodes.getValue();
    }
}
