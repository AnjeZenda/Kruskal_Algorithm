package com.kruskal.gui;


import javafx.scene.layout.Pane;

public class Mediator {
    private ActionController controller;
    private ShapeController shapeController;
    public Mediator() {}

    public void setController(ActionController controller) {
        this.controller = controller;
    }

    public void setGraphEditor(ShapeController shapeController) {
        this.shapeController = shapeController;
    }

    public void sendRequest(State state) {
        shapeController.getRequest(state);
    }
    public void sendAnswer() {

    }

}
