package com.kruskal.kruskalalgorithm;

import com.kruskal.shapeview.NodeView;
import com.kruskal.shapeview.ShapeContainer;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


import java.util.ArrayList;
import java.util.List;

public class ShapeController {
    private final ShapeContainer shapeContainer = new ShapeContainer();
    private final List<Circle> circles = new ArrayList<>(); // TODO Create map Text: Circle
    private final List<Line> lines = new ArrayList<>();     // TODO create map Circle: List<Line>
    // TODO replace variables below into ShapeContainerClass
    private Circle startNode;
    private Circle endNode;


    public ShapeController() {
    }

    public void getRequest(Pane pane, State state) {
        pane.setOnMouseClicked(event -> {
            if (state == State.ADDNODE) {
                addNode(pane, event);
            } else if (state == State.ADDEDGE) {
                addEdge(pane, event);
            } else if (state == State.RESTART) {
                circles.clear();
                lines.clear();
                startNode = null;
                endNode = null;
            } else if (state == State.REMOVENODE) {
                removeNode(pane, event);
            } else if (state == State.REMOVEEDGE) {
                removeEdge(pane, event);
            }
        });
    }


    private void removeEdge(Pane pane, MouseEvent event) {
        for (int i = 0; i < pane.getChildren().size(); ++i) {
            if (pane.getChildren().get(i) instanceof Line line) {
                if (line.contains(event.getX(), event.getY())) {
                    pane.getChildren().remove(line); // TODO add removing text
                    circles.remove(line);
                }
            }
        }
    }

    private void removeNode(Pane pane, MouseEvent event) {
        for (int i = 0; i < pane.getChildren().size(); ++i) {
            if (pane.getChildren().get(i) instanceof Circle node) {
                if (node.contains(event.getX(), event.getY())) {
                    pane.getChildren().remove(node); // TODO add removing text
                    circles.remove(node);
                }
            }
        }
    }

    private void addEdge(Pane pane, MouseEvent event) {
        if (startNode == null) {
            startNode = findNode(event.getX(), event.getY(), pane);
        } else {
            endNode = findNode(event.getX(), event.getY(), pane);
            if (endNode != null && startNode != endNode) {
                Line line = new Line();
                line.startXProperty().bind(startNode.centerXProperty());
                line.startYProperty().bind(startNode.centerYProperty());
                line.endXProperty().bind(endNode.centerXProperty());
                line.endYProperty().bind(endNode.centerYProperty());
                line.setStroke(Color.RED);
                line.setStrokeWidth(4d);
                lines.add(line);
                pane.getChildren().add(line);
                startNode = null;
                endNode = null;
            }
        }
    }

    private void addNode(Pane pane, MouseEvent event) {
        Text label = new Text();
        Circle circle = createNode(event.getX(), event.getY());
        label.setText(Integer.toString(circles.indexOf(circle)));
        label.setX(event.getX());
        label.setY(event.getY());
        pane.getChildren().addAll(circle, label);
    }

    private Circle createNode(double x, double y) {
        Circle circle = new Circle(x, y, 30, Color.BLUEVIOLET);
        circles.add(circle);
        return circle;
    }

    private Circle findNode(double x, double y, Pane pane) {
        for (int i = 0; i < pane.getChildren().size(); ++i) {
            if (pane.getChildren().get(i) instanceof Circle node) {
                if (node.contains(x, y)) {
                    return node;
                }
            }
        }
        return null;
    }
}
