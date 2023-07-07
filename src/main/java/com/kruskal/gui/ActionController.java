package com.kruskal.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

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
    private Mediator mediator;


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
        mediator.sendRequest(State.RESTART);
    }

    protected void setDisability(boolean disability) {
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
        mediator.sendRequest(State.ADDNODE);
    }
    @FXML
    protected void onAddEdgeButtonClick() {
        currentState.setText("Add Edge");
        currentState.setOpacity(1);
        mediator.sendRequest(State.ADDEDGE);
    }
    @FXML
    protected void onRemoveNodeButtonClick() {
        currentState.setText("Remove Node");
        currentState.setOpacity(1);
        mediator.sendRequest(State.REMOVENODE);
    }

    @FXML
    protected void onRemoveEdgeButtonClicked() {
        currentState.setText("Remove Edge");
        currentState.setOpacity(1);
        mediator.sendRequest(State.REMOVEEDGE);
    }
    @FXML
    protected void onReplaceNodeButtonClicked() {
        currentState.setText("Replace Node");
        currentState.setOpacity(1);
        mediator.sendRequest(State.REPLACENODE);
    }

    protected void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}