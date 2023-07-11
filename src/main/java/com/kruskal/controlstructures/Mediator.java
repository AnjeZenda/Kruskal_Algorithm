package com.kruskal.controlstructures;

import com.kruskal.fileparser.FileReader;
import com.kruskal.fileparser.FileWriter;
import com.kruskal.graph.GraphBuilder;
import com.kruskal.gui.ActionController;
import com.kruskal.gui.ActionMessage;
import com.kruskal.gui.ShapeController;
import com.kruskal.kruskalalgorithm.Kruskal;
import com.kruskal.kruskalalgorithm.StepMessage;

public class Mediator {
    private ActionController actionController;
    private ControllerSynchronizer controllerSynchronizer;
    private ShapeController shapeController;
    private Kruskal algorithm;
    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public Mediator() {
        fileWriter = new FileWriter();
        fileReader = new FileReader();
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
            case UPLOADGRAPH -> {
                controllerSynchronizer.eraseGraph();
                fileReader.read(actionMessage.getFileName(), actionMessage.getX(), actionMessage.getY(), this);
            }
            case SAVEGRAPH -> fileWriter.write(actionMessage.getFileName(), controllerSynchronizer.getGraph().getEdgesData(), controllerSynchronizer.getGraph().getNodesId());
            case RUNALGORITHM -> algorithm = new Kruskal(controllerSynchronizer.getGraph());
            case NEXTSTEP -> {
                StepMessage stepMessage = algorithm.makeStep();
                actionController.printMessage(stepMessage);
                shapeController.paintEdge(stepMessage);
            }
            case PREVIOUSSTEP -> {
                StepMessage stepMessage = algorithm.stepBack();
                if (stepMessage != null) {
                    actionController.deleteLastMessage();
                    shapeController.paintEdgeDefault(stepMessage);
                } else {
                    actionController.blockPreviousStepButton();
                    actionController.deleteLastMessage();
                }
            }
        }
    }
}
