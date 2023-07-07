package com.kruskal.shapeview;

import com.kruskal.graph.Edge;
import com.kruskal.graph.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ShapeContainer {
    private final List<NodeView> nodeViewList = new ArrayList<>();
    private final List<EdgeView> edgeViewList = new ArrayList<>();
    private final Pane pane;
    private int lastNodeId = 0; // TODO delete this field when synchronizer will be added
    private int lastEdgeId = 0; // TODO delete this field when synchronizer will be added


    public ShapeContainer(Pane pane) {
        this.pane = pane;
    }

    public void createNode(double xCoordinate, double yCoordinate, double radius, Color color, int nodeId) {
        NodeView node = new NodeView(xCoordinate, yCoordinate, radius, color, lastNodeId++); // TODO add nodeId here instead of lastNodeId
        nodeViewList.add(node);
        pane.getChildren().addAll(node, node.getText());
    }

    public void createEdge(int startNodeId, int endNodeId, int weight, int edgeId) {
        EdgeView edgeView = new EdgeView(lastEdgeId++, weight); // TODO add edgeId here instead of lastEdgeId
        NodeView startNode = null, endNode = null;
        for (NodeView node : nodeViewList) {
            if (node.getIdNumber() == startNodeId) {
                startNode = node;
                edgeView.setStartX(node.getCenterX());
                edgeView.setStartY(node.getCenterY());
                node.addIncidentEdgeId(edgeView);
            }else if (node.getIdNumber() == endNodeId) {
                endNode = node;
                edgeView.setEndX(node.getCenterX());
                edgeView.setEndY(node.getCenterY());
                node.addIncidentEdgeId(edgeView);
            }
        }
        edgeView.setAdjacentNodes(startNode, endNode);
        edgeView.setStroke(Color.BLACK);
        edgeView.setStrokeWidth(3d);
        edgeViewList.add(edgeView);
        pane.getChildren().add(edgeView);
    }

    public void removeNode(int nodeId) {
        for (NodeView node: nodeViewList) {
            if (nodeId == node.getIdNumber()) {
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
                break;
            }
        }
    }

    public void removeEdge(int edgeId) {
        for (EdgeView edge: edgeViewList) {
            if (edge.getIdNumber() == edgeId) {
                edge.getStartNode().removeEdge(edge);
                edge.getEndNode().removeEdge(edge);
                edgeViewList.remove(edge);
                pane.getChildren().remove(edge);
                break;
            }
        }
    }

    public void clear() {
        nodeViewList.clear();
        edgeViewList.clear();
        pane.getChildren().clear();
    }

    public void replaceNode(double x, double y, int objectId) {
        for (NodeView node : nodeViewList) {
            if (node.getIdNumber() == objectId) {
                node.setCenterX(x);
                node.setCenterY(y);
                node.getText().setX(x);
                node.getText().setY(y);
                for (EdgeView edge : edgeViewList) {
                    if (node.hasIncidentEdge(edge) && node.isStartVertex(edge)) {
                        edge.setStartX(x);
                        edge.setStartY(y);
                    } else if (node.hasIncidentEdge(edge)) {
                        edge.setEndX(x);
                        edge.setEndY(y);
                    }
                }
                break;
            }
        }
    }
}
