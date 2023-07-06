package graph;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class Graph {
    private final List<Node> nodes;

    Graph(){
        nodes = new ArrayList<Node>();
    };

    private Node findNode(int nodeId){
        for(Node currentNode: nodes){
            if(currentNode.getId() == nodeId){
                return currentNode;
            }
        }
        return null;
    }

    boolean deleteNode(int nodeId){
        Node currentNode = findNode(nodeId);
        if(currentNode != null){

            List<Edge> edges = currentNode.getAdjacentEdges();
            while (!edges.isEmpty()) {
                Edge target = edges.get(0);
                currentNode.deleteEdge(target.getId());
            }

            nodes.remove(currentNode);
            return true;
        }
        return false;
    }

    boolean addNode(int newNodeId){
        Node newNode = new Node(newNodeId);
        if(findNode(newNode.getId()) == null){
            nodes.add(newNode);
            return true;
        }
        return false;
    }

    boolean addEdge(int firstNodeId, int secondNodeId, int weight, int edgeId){
        List<Edge> allEdges = getEdgesList();
        for(Edge edge: allEdges){
            if(edge.getId() == edgeId){
                return false;
            }
        }

        Node firstNode = findNode(firstNodeId);
        if(firstNode == null){
            return false;
        }

        Node secondNode = findNode(secondNodeId);
        if(secondNode == null){
            return false;
        }

        Edge newEdge = new Edge(firstNode, secondNode, weight, edgeId);

        if(newEdge.getFirstNode().getAdjacentEdges().contains(newEdge)){
            return false;
        }
        newEdge.getFirstNode().addEdge(newEdge);
        return true;
    }

    boolean deleteEdge(int edgeId){
        List<Edge> allEdges = getEdgesList();
        for(Edge edge: allEdges){
            if(edge.getId() == edgeId){
                edge.getFirstNode().deleteEdge(edgeId);
                return true;
            }
        }
        return false;
    }

    private List<Edge> getEdgesList(){
        List<Edge> allEdges = new ArrayList<Edge>();
        Set<Node> viewedNodes = new HashSet<Node>();
        List<Node> nodesToView = new ArrayList<Node>();

        if(nodes.isEmpty()){
            return allEdges;
        }

        nodesToView.add(nodes.get(0));
        while(!nodesToView.isEmpty()){
            Node currentNode = nodesToView.get(0);
            nodesToView.remove(0);

            if(viewedNodes.contains(currentNode)){
                continue;
            }

            viewedNodes.add(currentNode);

            for (Edge adjacent: currentNode.getAdjacentEdges()){
                if(!allEdges.contains(adjacent)){
                    allEdges.add(adjacent);
                }

                nodesToView.add(adjacent.getAdjacentNode(currentNode));

            }
        }

        return allEdges;
    }

    public void printGraph(){
        for (Node node: nodes) {
            System.out.println(node);
        }
    }

    boolean isValid(){
        Set<Node> viewedNodes = new HashSet<Node>();
        List<Node> nodesToView = new ArrayList<Node>();

        if(nodes.isEmpty()){
            return true;
        }

        nodesToView.add(nodes.get(0));
        while(!nodesToView.isEmpty()){
            Node currentNode = nodesToView.get(0);
            nodesToView.remove(0);

            if(viewedNodes.contains(currentNode)){
                continue;
            }

            viewedNodes.add(currentNode);

            for (Edge adjacent: currentNode.getAdjacentEdges()){
                nodesToView.add(adjacent.getAdjacentNode(currentNode));
            }
        }

        for (Node node : nodes){
            if(!viewedNodes.contains(node)){
                return false;
            }
        }

        return true;
    }

    public Set<Integer> getNodesId(){
        Set<Integer> set = new HashSet<>();
        for (Node node : nodes) {
            set.add(node.getId());
        }
        return set;
    }

    public List<EdgeData> getEdgesData(){
        List<EdgeData> data = new ArrayList<EdgeData>();
        for (Edge edge : getEdgesList()) {
            data.add(new EdgeData(edge));
        }
        return data;
    }
}
