package com.kruskal.gui;

import com.kruskal.controlstructures.Mediator;
import com.kruskal.shapeview.EdgeView;
import com.kruskal.shapeview.NodeView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class ActionController {
    @FXML
    private Button addEdgeButton;
    @FXML
    private Button addNodeButton;
    @FXML
    private Label currentState;
    @FXML
    private Button nextStepButton;
    @FXML
    private Button removeEdgeButton;
    @FXML
    private Button removeNodeButton;
    @FXML
    private Button replaceNodeButton;
    @FXML
    private Button runAlgorithmButton;
    @FXML
    private Button saveGraphButton;
    @FXML
    private Button uploadGraphButton;
    @FXML
    private TextArea messageSender;
    @FXML
    private Pane mainPane;
    private Mediator mediator;
    private NodeView startNode;
    private NodeView endNode;


    @FXML
    protected void onRunAlgorithmButtonClick() {
        currentState.setText("Run Algorithm");
        currentState.setOpacity(1);
        nextStepButton.setDisable(false);
        setDisability(true);
    }

    @FXML
    protected void onUploadGraphButtonClick() {
        currentState.setText("Upload Graph");
        currentState.setOpacity(1);
    }

    @FXML
    protected void onNextStepButtonClick() {
        messageSender.appendText("Next step!\n");
    }

    @FXML
    protected void onRestartButtonClick() {
        setDisability(false);
        nextStepButton.setDisable(true);
        messageSender.clear();
        currentState.setText("Current state");
        currentState.setOpacity(0.5d);
        mainPane.setOnMouseClicked(event -> {});
        mediator.sendRequest(new ActionMessage(State.RESTART, -1, -1, -1, -1, -1));
    }

    private void setDisability(boolean disability) {
        addEdgeButton.setDisable(disability);
        addNodeButton.setDisable(disability);
        removeEdgeButton.setDisable(disability);
        removeNodeButton.setDisable(disability);
        saveGraphButton.setDisable(disability);
        uploadGraphButton.setDisable(disability);
        runAlgorithmButton.setDisable(disability);
        replaceNodeButton.setDisable(disability);
    }

    @FXML
    protected void onAddNodeButtonClick() {
        currentState.setText("Add Node");
        currentState.setOpacity(1);
        mainPane.setOnMouseClicked(event -> {
            mediator.sendRequest(new ActionMessage(State.ADDNODE, event.getX(), event.getY(),
                    -1, -1, -1));
        });
    }

    @FXML
    protected void onAddEdgeButtonClick() {
        currentState.setText("Add Edge");
        currentState.setOpacity(1);
        mainPane.setOnMouseClicked(event -> {
            if (startNode == null) {
                startNode = findNode(event.getX(), event.getY());
            } else {
                endNode = findNode(event.getX(), event.getY());
                if (endNode != null && !endNode.equals(startNode)) {
                    String inputWeight = runAlert();
                    int weight = !inputWeight.equals("") ? Integer.parseInt(inputWeight) : 1;
                    mediator.sendRequest(new ActionMessage(State.ADDEDGE, -1, -1, startNode.getIdNumber(), endNode.getIdNumber(), weight));
                    startNode = null;
                    endNode = null;
                }
            }
        });
    }

    @FXML
    protected void onRemoveNodeButtonClick() {
        currentState.setText("Remove Node");
        currentState.setOpacity(1);
        mainPane.setOnMouseClicked(event -> {
            NodeView removingNode = findNode(event.getX(), event.getY());
            if (removingNode != null) {
                mediator.sendRequest(new ActionMessage(State.REMOVENODE, -1, -1, removingNode.getIdNumber(), -1, -1));
            }
        });
    }

    @FXML
    protected void onRemoveEdgeButtonClicked() {
        currentState.setText("Remove Edge");
        currentState.setOpacity(1);
        mainPane.setOnMouseClicked(event -> {
            for (int i = 0; i < mainPane.getChildren().size(); ++i) {
                if (mainPane.getChildren().get(i) instanceof Line edge) {
                    if (edge.contains(event.getX(), event.getY())) {
                        mediator.sendRequest(new ActionMessage(State.REMOVEEDGE, -1, -1, ((EdgeView)edge).getIdNumber(), -1, -1));
                    }
                }
            }
        });
    }

    @FXML
    protected void onReplaceNodeButtonClicked() {
        currentState.setText("Replace Node");
        currentState.setOpacity(1);
        startNode = null;
        mainPane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) { // Left mouse button
                if (startNode == null) {
                    startNode = findNode(event.getX(), event.getY());
                } else {
                    mediator.sendRequest(new ActionMessage(State.REPLACENODE, event.getX(), event.getY(),
                            startNode.getIdNumber(), -1, 1));
                    startNode = null;
                }
            } else if (event.getButton() == MouseButton.SECONDARY) {// Right mouse button
                startNode = null;
            }
        });
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    private NodeView findNode(double x, double y) {
        for (int i = 0; i < mainPane.getChildren().size(); ++i) {
            if (mainPane.getChildren().get(i) instanceof Circle node) {
                if (node.contains(x, y)) return (NodeView) node;
            }
        }
        return null;
    }

    private String runAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Text field");
        alert.setHeaderText("Введите вес ребра");
        TextField inputField = new TextField();
        inputField.setMaxWidth(Double.MAX_VALUE);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(inputField);
        alert.getDialogPane().setContent(vBox);
        alert.showAndWait();
        return inputField.getText();
    }
}