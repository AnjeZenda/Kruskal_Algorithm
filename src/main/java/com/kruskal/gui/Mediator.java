package com.kruskal.gui;


public class Mediator {
    private ActionController controller;
    private ShapeController shapeController;

    public Mediator() {
    }

    public void setController(ActionController controller) {
        this.controller = controller;
    }

    public void setShapeController(ShapeController shapeController) {
        this.shapeController = shapeController;
    }

    public void sendRequest(ActionMessage actionMessage) {
        switch (actionMessage.getState()) {
            case ADDNODE -> shapeController.addNode(actionMessage.getX(), actionMessage.getY(), 0);
            case ADDEDGE -> shapeController.addEdge(actionMessage.getObjectId(), actionMessage.getSecondObjectId(), actionMessage.getWeight(), 0);
            case REMOVEEDGE -> shapeController.removeEdge(actionMessage.getObjectId());
            case REMOVENODE -> shapeController.removeNode(actionMessage.getObjectId());
            case RESTART -> shapeController.clear();
            case REPLACENODE -> shapeController.replaceNode(actionMessage.getX(), actionMessage.getY(), actionMessage.getObjectId());
        }
    }
}
