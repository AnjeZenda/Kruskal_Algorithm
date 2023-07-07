package com.kruskal.gui;


public class ActionMessage {
    private State state;
    private double x;
    private double y;
    private int objectId;
    private int secondObjectId;
    private int weight;

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

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

    public ActionMessage(State state, double x, double y, int objectId, int secondObjectId, int weight) {
        this.state = state;
        this.x = x;
        this.y = y;
        this.objectId = objectId;
        this.secondObjectId = secondObjectId;
        this.weight = weight;
    }
}
