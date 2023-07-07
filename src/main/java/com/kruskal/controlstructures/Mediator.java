package com.kruskal.controlstructures;

import com.kruskal.graph.GraphBuilder;
import com.kruskal.gui.ActionController;
import com.kruskal.gui.ActionMessage;
import com.kruskal.gui.ShapeController;
import com.kruskal.kruskalalgorithm.Kruskal;

public class Mediator {
    private ActionController actionController;
    private ControllerSynchronizer controllerSynchronizer;
    private ShapeController shapeController;
    private Kruskal algorithm;

    public Mediator() {
    }

    public void setActionController(ActionController controller) {
        this.actionController = controller;
    }

    public void setShapeController(ShapeController shapeController){
        this.shapeController = shapeController;
        this.controllerSynchronizer = new ControllerSynchronizer(shapeController, new GraphBuilder());
    }

    public void sendRequest(ActionMessage actionMessage) {
        switch (actionMessage.getState()) {
            case ADDNODE -> controllerSynchronizer.addNode(actionMessage.getX(), actionMessage.getY());
            case ADDEDGE -> controllerSynchronizer.addEdge(actionMessage.getObjectId(), actionMessage.getSecondObjectId(), actionMessage.getWeight());
            case REMOVEEDGE -> controllerSynchronizer.deleteEdge(actionMessage.getObjectId());
            case REMOVENODE -> controllerSynchronizer.deleteNode(actionMessage.getObjectId());
            case RESTART -> controllerSynchronizer.eraseGraph();
            case REPLACENODE -> shapeController.replaceNode(actionMessage.getX(), actionMessage.getY(), actionMessage.getObjectId());
        }
    }
}
