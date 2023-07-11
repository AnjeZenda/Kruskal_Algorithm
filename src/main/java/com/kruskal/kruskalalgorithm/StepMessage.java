package com.kruskal.kruskalalgorithm;
import com.kruskal.graph.EdgeData;

public class StepMessage {
    private final StepMessageType messageType;
    private final int firstNodeId;
    private final int secondNodeId;
    private final int edgeId;
    private final int weightShift;

    public StepMessage(EdgeData edgeData, StepMessageType messageType, int weightShift){
        this.weightShift = weightShift;
        this.messageType = messageType;
        firstNodeId = edgeData.getFirstNodeId();
        secondNodeId = edgeData.getSecondNodeId();
        edgeId = edgeData.getId();
    }

    public StepMessage(StepMessageType messageType){
        this.messageType = messageType;
        firstNodeId = -1;
        secondNodeId = -1;
        edgeId = -1;
        weightShift = -1;
    }

    public StepMessageType getType(){
        return messageType;
    }

    public int getFirstNodeId(){
        return firstNodeId;
    }

    public int getEdgeId() {
        return edgeId;
    }

    public int getSecondNodeId() {
        return secondNodeId;
    }

    int getWeightShift(){
        return weightShift;
    }
}
