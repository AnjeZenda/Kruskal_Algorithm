package com.kruskal.shapeview;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ShapeContainer {
    private final List<NodeView> nodeViewList = new ArrayList<>();
    private final List<EdgeView> edgeViewList = new ArrayList<>();
    private Pane pane;
    private int lastNodeId = 0;
    private int lastEdgeId = 0;
    private NodeView startNode;
    private NodeView endNode;


    public ShapeContainer(Pane pane) {
        this.pane = pane;
    }

    public void createNode(double xCoordinate, double yCoordinate, double radius, Color color) {
        NodeView node = new NodeView(xCoordinate, yCoordinate, radius, color, lastNodeId++);
        nodeViewList.add(node);
        pane.getChildren().addAll(node, node.getText());
    }

    public void createEdge(double x, double y) {
        if (startNode == null) {
            startNode = findNode(x, y);
        } else {
            endNode = findNode(x, y);
            if (endNode != null && !startNode.equals(endNode)) {
                EdgeView edge = new EdgeView(startNode.getCenterX(), startNode.getCenterY(),
                        endNode.getCenterX(), endNode.getCenterY(), lastEdgeId++);
                edge.setStroke(Color.BLACK);
                edge.setStrokeWidth(3d);
                edge.setAdjacentNodes(startNode, endNode);
                startNode.addIncidentEdgeId(edge);
                endNode.addIncidentEdgeId(edge);
                edgeViewList.add(edge);
                pane.getChildren().add(edge);
                startNode = null;
                endNode = null;
            }
        }
    }

    public void removeNode(double x, double y) {
        NodeView node = findNode(x, y);
        if (node != null) {
            List<EdgeView> needToBeDeletedEdge = new ArrayList<>();
            for (EdgeView edge: edgeViewList) {
                if (node.hasIncidentEdge(edge)) {
                    if (!node.equals(edge.getStartNode())) {
                        edge.getStartNode().removeEdge(edge);
                    } else {
                        edge.getEndNode().removeEdge(edge);
                    }
                    pane.getChildren().remove(edge);
                    needToBeDeletedEdge.add(edge);
                    node.removeEdge(edge);
                }
            }
            for(EdgeView edge : needToBeDeletedEdge) {
                if (edgeViewList.contains(edge)) {
                    edgeViewList.remove(edge);
                }
            }
            nodeViewList.remove(node);
            pane.getChildren().remove(node.getText());
            pane.getChildren().remove(node);
        }
    }

    public void removeEdge(double x, double y) {
        for (EdgeView edge : edgeViewList) {
            if(edge.contains(x, y)) {
                pane.getChildren().remove(edge);
                edge.getStartNode().removeEdge(edge);
                edge.getEndNode().removeEdge(edge);
                edgeViewList.remove(edge);
                break;
            }
        }
    }
    private NodeView findNode(double x, double y) {
        for (NodeView nodeView : nodeViewList) {
            if (nodeView.contains(x, y)) {
                return nodeView;
            }
        }
        return null;
    }

    public void clear() {
        nodeViewList.clear();
        edgeViewList.clear();
        pane.getChildren().clear();
    }
}
