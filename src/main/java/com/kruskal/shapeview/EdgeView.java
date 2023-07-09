package com.kruskal.shapeview;

import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Pair;

public class EdgeView extends Line {
    private int idNumber;
    private final int weight;
    private Text weightText;
    private final TextFlow textFlow = new TextFlow();

    public TextFlow getTextFlow() {
        return textFlow;
    }

    private Pair<NodeView, NodeView> adjacentNodes;

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public Text getWeightText() {
        return weightText;
    }

    public EdgeView(double v, double v1, double v2, double v3, int idNumber, int weight) {
        super(v, v1, v2, v3);
        this.idNumber = idNumber;
        this.weight = weight;
        this.weightText = new Text(Integer.toString(weight));
    }

    public EdgeView(int idNumber, int weight) {
        super();
        this.idNumber = idNumber;
        this.weight = weight;
        this.weightText = new Text(Integer.toString(weight));
        this.weightText.setStyle("-fx-fill: black; -fx-font-weight: bold");
        this.textFlow.setStyle("-fx-background-color: white");
        this.textFlow.setPrefWidth(20);
        this.textFlow.setPrefHeight(20);
        this.textFlow.setTextAlignment(TextAlignment.CENTER);
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
