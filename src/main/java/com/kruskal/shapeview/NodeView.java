package com.kruskal.shapeview;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class NodeView extends Circle {
    private int idNumber;
    private final List<Integer> incidentEdgeIdList = new ArrayList<>();

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public NodeView(double v, int idNumber) {
        super(v);
        this.idNumber = idNumber;
    }

    public NodeView(double v, Paint paint, int idNumber) {
        super(v, paint);
        this.idNumber = idNumber;
    }

    public NodeView(int idNumber) {
        this.idNumber = idNumber;
    }

    public NodeView(double v, double v1, double v2, int idNumber) {
        super(v, v1, v2);
        this.idNumber = idNumber;
    }

    public NodeView(double v, double v1, double v2, Paint paint, int idNumber) {
        super(v, v1, v2, paint);
        this.idNumber = idNumber;
    }
}
