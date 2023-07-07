package com.kruskal.shapeview;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class NodeView extends Circle {
    private int idNumber;
    private final Text text;
    private final List<EdgeView> incidentEdgeIdList = new ArrayList<>();

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public NodeView(double x, double y, double radius, Paint paint, int idNumber) {
        super(x, y, radius, paint);
        this.idNumber = idNumber;
        this.text = new Text(x, y, Integer.toString(idNumber));
    }

    public Text getText() {
        return text;
    }

    public void addIncidentEdgeId(EdgeView edge) {
        incidentEdgeIdList.add(edge);
    }

    public boolean hasIncidentEdge(EdgeView edge) {
        return incidentEdgeIdList.contains(edge);
    }

    public void removeEdge(EdgeView edge) {
        incidentEdgeIdList.remove(edge);
    }
}
