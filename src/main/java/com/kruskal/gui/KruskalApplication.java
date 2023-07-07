package com.kruskal.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;

import com.kruskal.controlstructures.Mediator;

public class KruskalApplication extends Application {
    private Mediator mediator;
    private ActionController actionController;
    private ShapeController shapeController;
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(KruskalApplication.class.getResource("application-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        actionController = fxmlLoader.getController();
        shapeController = new ShapeController((Pane)root.getChildren().get(1));
        connectMediator();
        Scene scene = new Scene(root);
        stage.setTitle("Kruskal's algorithm");
        stage.setScene(scene);
        stage.show();
    }

    private void connectMediator() {
        mediator = new Mediator();
        mediator.setActionController(actionController);
        mediator.setShapeController(shapeController);
        actionController.setMediator(mediator);
    }
    public static void main(String[] args) {
        launch();
    }
}