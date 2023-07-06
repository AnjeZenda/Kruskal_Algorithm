module com.kruskal.kruskalalgorithm {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
            
    opens com.kruskal.kruskalalgorithm to javafx.fxml;
    exports com.kruskal.kruskalalgorithm;
    exports com.kruskal.shapeview;
    opens com.kruskal.shapeview to javafx.fxml;
}