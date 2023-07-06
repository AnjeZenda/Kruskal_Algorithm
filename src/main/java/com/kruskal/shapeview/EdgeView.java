package com.kruskal.shapeview;

import javafx.scene.shape.Line;

public class EdgeView extends Line {
    private int idNumber;


    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public EdgeView(double v, double v1, double v2, double v3, int idNumber) {
        super(v, v1, v2, v3);
        this.idNumber = idNumber;
    }

    public EdgeView(int idNumber) {
        super();
        this.idNumber = idNumber;
    }
}
