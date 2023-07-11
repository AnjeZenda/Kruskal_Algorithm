package com.kruskal.gui;

import com.kruskal.controlstructures.Mediator;
import com.kruskal.fileparser.FileFormatException;
import com.kruskal.kruskalalgorithm.StepMessage;
import com.kruskal.shapeview.EdgeView;
import com.kruskal.shapeview.NodeView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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
    private Button previousStepButton;
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
    @FXML
    private Label treeWeightInfo;
    private Mediator mediator;
    private NodeView startNode;
    private NodeView endNode;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void onRunAlgorithmButtonClick() {
        currentState.setText("Run Algorithm");
        currentState.setOpacity(1);
        nextStepButton.setDisable(false);
        setDisability(true);
        try {
            mediator.sendRequest(new ActionMessage(State.RUNALGORITHM));
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    protected void onUploadGraphButtonClick() {
        currentState.setText("Current state");
        currentState.setOpacity(0.5d);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                mediator.sendRequest(new ActionMessage(State.UPLOADGRAPH, mainPane.getWidth(), mainPane.getHeight(), file.getAbsolutePath()));
                messageSender.appendText("Graph has been uploaded\n");
            } catch (FileFormatException exception) {
                createErrorAlertMessage(exception.toString());
            } catch (Exception exception) {
                createErrorAlertMessage(exception.getMessage());
            }
        } else {
            createErrorAlertMessage("Не удалось открыть файл");
        }
    }

    @FXML
    protected void onSaveGraphButtonClicked() {
        currentState.setText("Current state");
        currentState.setOpacity(0.5d);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                mediator.sendRequest(new ActionMessage(State.SAVEGRAPH, file.getAbsolutePath()));
                messageSender.appendText("Graph has been saved\n");
            } catch (FileFormatException exception) {
                createErrorAlertMessage(exception.toString());
            }
        } else {
            createErrorAlertMessage("Не удалось открыть файл");
        }
    }

    @FXML
    protected void onNextStepButtonClick() {
        if (previousStepButton.isDisable()) {
            previousStepButton.setDisable(false);
        }
        try {
            mediator.sendRequest(new ActionMessage(State.NEXTSTEP));
        } catch (Exception exception) {
            createErrorAlertMessage(exception.getMessage());
        }
    }

    @FXML
    protected void onPreviousStepButtonClick() {
        if (nextStepButton.isDisable()) {
            nextStepButton.setDisable(false);
        }
        try {
            mediator.sendRequest(new ActionMessage(State.PREVIOUSSTEP));
        } catch (Exception exception) {
            createErrorAlertMessage(exception.getMessage());
        }
    }

    @FXML
    protected void onRestartButtonClick() {
        setDisability(false);
        treeWeightInfo.setText("0");
        nextStepButton.setDisable(true);
        previousStepButton.setDisable(true);
        messageSender.clear();
        currentState.setText("Current state");
        currentState.setOpacity(0.5d);
        mainPane.setOnMouseClicked(event -> {});
        mediator.sendRequest(new ActionMessage(State.RESTART));
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
        try {
            mainPane.setOnMouseClicked(event -> {
                mediator.sendRequest(new ActionMessage(State.ADDNODE, event.getX(), event.getY()));
            });
        } catch (Exception exception) {
            createErrorAlertMessage(exception.getMessage());
        }
    }

    @FXML
    protected void onAddEdgeButtonClick() {
        currentState.setText("Add Edge");
        currentState.setOpacity(1);
        try {
            mainPane.setOnMouseClicked(event -> {
                if (startNode == null) {
                    startNode = findNode(event.getX(), event.getY());
                } else {
                    endNode = findNode(event.getX(), event.getY());
                    if (endNode != null && !endNode.equals(startNode)) {
                        String inputWeight = runAlert();
                        int weight = !inputWeight.equals("") ? Integer.parseInt(inputWeight) : 1;
                        mediator.sendRequest(new ActionMessage(State.ADDEDGE, startNode.getIdNumber(), endNode.getIdNumber(), weight));
                        startNode = null;
                        endNode = null;
                    }
                }
            });
        }
        catch (Exception exception) {
            createErrorAlertMessage(exception.getMessage());
        }
    }

    @FXML
    protected void onRemoveNodeButtonClick() {
        currentState.setText("Remove Node");
        currentState.setOpacity(1);
        try {
            mainPane.setOnMouseClicked(event -> {
                NodeView removingNode = findNode(event.getX(), event.getY());
                if (removingNode != null) {
                    mediator.sendRequest(new ActionMessage(State.REMOVENODE, removingNode.getIdNumber()));
                }
            });
        } catch (Exception exception) {
            createErrorAlertMessage(exception.getMessage());
        }
    }

    @FXML
    protected void onRemoveEdgeButtonClicked() {
        currentState.setText("Remove Edge");
        currentState.setOpacity(1);
        try {
            mainPane.setOnMouseClicked(event -> {
                for (int i = 0; i < mainPane.getChildren().size(); ++i) {
                    if (mainPane.getChildren().get(i) instanceof Line edge) {
                        if (edge.contains(event.getX(), event.getY())) {
                            mediator.sendRequest(new ActionMessage(State.REMOVEEDGE, ((EdgeView) edge).getIdNumber()));
                        }
                    }
                }
            });
        } catch (Exception exception) {
            createErrorAlertMessage(exception.getMessage());
        }
    }

    @FXML
    protected void onReplaceNodeButtonClicked() {
        currentState.setText("Replace Node");
        currentState.setOpacity(1);
        startNode = null;
        try {
            mainPane.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (startNode == null) {
                        startNode = findNode(event.getX(), event.getY());
                    } else {
                        mediator.sendRequest(new ActionMessage(State.REPLACENODE, event.getX(), event.getY(), startNode.getIdNumber()));
                        startNode = null;
                    }
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    startNode = null;
                }
            });
        } catch (Exception exception) {
            createErrorAlertMessage(exception.getMessage());
        }
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
        inputField.setPrefWidth(Integer.toString(Integer.MAX_VALUE).length());
        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.startsWith("0") || !newValue.matches("\\d*") || newValue.equals("") ||
                    Integer.toString(Integer.MAX_VALUE).length() < newValue.length() ||
                    Double.parseDouble(newValue) > Integer.MAX_VALUE) {
                inputField.setStyle("-fx-border-color: red");
                Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                okButton.setDisable(true);
            } else {
                inputField.setStyle("-fx-border-color: blue");
                Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                okButton.setDisable(false);
            }
        });
        VBox vBox = new VBox();
        vBox.getChildren().addAll(inputField);
        alert.getDialogPane().setContent(vBox);
        alert.showAndWait();
        return inputField.getText();
    }

    public void printMessage(StepMessage message) {
        switch (message.getType()) {
            case EDGEADDED -> messageSender.appendText("Edge between " + message.getFirstNodeId()
                    + " and " + message.getSecondNodeId() + " were added\n");
            case EDGEDECLINED -> messageSender.appendText("Edge between " + message.getFirstNodeId()
                    + " and " + message.getSecondNodeId() + " were declined\n");
            case ALGORITHMENDED -> {
                messageSender.appendText("Algorithm ended\n");
                nextStepButton.setDisable(true);
            }
        }
    }

    public void deleteLastMessage() {
        String[] sentences = messageSender.getText().split("\n");
        if (sentences.length > 0 && sentences[sentences.length - 1].contains("Algorithm ended")) {
            sentences[sentences.length - 1] = "";
            sentences[sentences.length - 2] = "";
        } else if (sentences.length > 0 && !sentences[sentences.length - 1].contains("Graph has been")) {
            sentences[sentences.length - 1] = "";
        }
        messageSender.setText(String.join("\n", sentences));
    }

    public void blockPreviousStepButton() {
        previousStepButton.setDisable(true);
    }

    public void printTreeWeight(int treeWeight) {
        treeWeightInfo.setText(Integer.toString(treeWeight));
    }

    private void createErrorAlertMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        alert.showAndWait();
        mediator.sendRequest(new ActionMessage(State.RESTART));
    }
}