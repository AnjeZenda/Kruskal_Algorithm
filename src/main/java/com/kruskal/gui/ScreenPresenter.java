package com.kruskal.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    private Mediator mediator;
    private Controller controller;
    private ShapeController shapeController;
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("application-view.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        shapeController = new ShapeController();
        connectMediator();
        Scene scene = new Scene(root);
        stage.setTitle("Kruskal's algorithm");
        stage.setScene(scene);
        stage.show();
    }

    private void connectMediator() {
        mediator = new Mediator();
        mediator.setController(controller);
        mediator.setGraphEditor(shapeController);
        controller.setMediator(mediator);
    }
    public static void main(String[] args) {
        launch();
    }
}