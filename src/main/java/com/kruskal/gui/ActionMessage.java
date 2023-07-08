package com.kruskal.gui;


public class ActionMessage {
    private State state;
    private double x;
    private double y;
    private int objectId;
    private int secondObjectId;
    private int weight;

    public String getFileName() {
        return fileName;
    }

    private String fileName;

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public State getState() {
        return state;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getObjectId() {
        return objectId;
    }

    public int getSecondObjectId() {
        return secondObjectId;
    }

    public int getWeight() {
        return weight;
    }

    public ActionMessage(State state, double x, double y, int objectId, int secondObjectId, int weight, String fileName) {
        this.state = state;
        this.x = x;
        this.y = y;
        this.objectId = objectId;
        this.secondObjectId = secondObjectId;
        this.weight = weight;
        this.fileName = fileName;
    }
    public ActionMessage(State state, double x, double y) {
        this(state, x, y, -1, -1, -1, null);
    }

    public ActionMessage(State state) {
        this(state, -1, -1, -1, -1, -1, null);
    }

    public ActionMessage(State state, int objectId, int secondObjectId, int weight) {
        this(state, -1, -1, objectId, secondObjectId, weight, null);
    }

    public ActionMessage(State state, int objectId) {
        this(state, -1, -1, objectId, -1, -1, null);
    }

    public ActionMessage(State state, double x, double y, int objectId) {
        this(state, x, y, objectId, -1, -1, null);
    }

    public ActionMessage(State state, String fileName) {
        this(state, -1, -1, -1, -1, -1, fileName);
    }
}
