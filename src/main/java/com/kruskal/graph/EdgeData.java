package graph;

public class EdgeData {
    private final int id;
    private final int weight;
    private final int firstNodeId;
    private final int secondNodeId;

    EdgeData(Edge edge){
        this.id = edge.getId();
        this.weight = edge.getWeight();
        this.firstNodeId = edge.getFirstNode().getId();
        this.secondNodeId = edge.getSecondNode().getId();
    }

    public int getId(){
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public int getFirstNodeId() {
        return firstNodeId;
    }

    public int getSecondNodeId() {
        return secondNodeId;
    }
}
