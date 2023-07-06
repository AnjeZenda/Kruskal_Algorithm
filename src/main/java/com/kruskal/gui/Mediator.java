package com.kruskal.gui;


import javafx.scene.layout.Pane;

public class Mediator {
    private Controller controller;
    private ShapeController shapeController;
    public Mediator() {}

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setGraphEditor(ShapeController shapeController) {
        this.shapeController = shapeController;
    }

    public void sendRequest(Pane pane, State state) {
        shapeController.getRequest(pane, state);
    }
    public void sendAnswer() {

    }

}
