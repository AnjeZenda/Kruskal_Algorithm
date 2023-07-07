package com.kruskal.gui;

import com.kruskal.shapeview.ShapeContainer;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class ShapeController {
    private final ShapeContainer shapeContainer;
    private final Pane pane;


    public ShapeController(Pane pane) {
        this.shapeContainer = new ShapeContainer(pane);
        this.pane = pane;
    }

    public void getRequest(State state) {
        if (state == State.RESTART) {
            clear();
            pane.setOnMouseClicked(event -> {});
            return;
        }
        pane.setOnMouseClicked(event -> {
            if (state == State.ADDNODE) {
                addNode(event);
            } else if (state == State.ADDEDGE) {
                addEdge(event);
            } else if (state == State.REMOVENODE) {
                removeNode(event);
            } else if (state == State.REMOVEEDGE) {
                removeEdge(event);
            }
        });
    }


    private void clear() {
        shapeContainer.clear();
    }


    private void removeEdge(MouseEvent event) {
        shapeContainer.removeEdge(event.getX(), event.getY());
    }

    private void removeNode(MouseEvent event) {
        shapeContainer.removeNode(event.getX(), event.getY());
    }

    private void addEdge(MouseEvent event) {
        shapeContainer.createEdge(event.getX(), event.getY());
    }

    private void addNode(MouseEvent event) {
        shapeContainer.createNode(event.getX(), event.getY(),
                30, Color.rgb(161, 227, 255));
    }
}
