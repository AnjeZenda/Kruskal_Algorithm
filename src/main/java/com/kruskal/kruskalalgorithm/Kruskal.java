package com.kruskal.kruskalalgorithm;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kruskal.graph.EdgeData;
import com.kruskal.graph.Graph;

public class Kruskal {
    private final List<EdgeData> edges;
    private final List<Set<Integer>> connectedNodes;
    private int edgeNumber;
    private int edgesWeightSum;

    public Kruskal(Graph graph){
        Set<Integer> nodes = graph.getNodesId();
        edges = graph.getEdgesData();

        Comparator<EdgeData> comparator = new Comparator<EdgeData>() {
            public int compare(EdgeData first, EdgeData second){
                return Integer.valueOf(first.getWeight()).compareTo(Integer.valueOf(second.getWeight()));
            }
        };
        edges.sort(comparator);

        connectedNodes = new ArrayList<Set<Integer>>();
        for (Integer node: nodes) {
            Set<Integer> newSet = new HashSet<Integer>();
            newSet.add(node);
            connectedNodes.add(newSet);
        }

        edgeNumber = 0;
        edgesWeightSum = 0;
    }

    public StepMessage makeStep(){
        System.out.println(connectedNodes);
        if(connectedNodes.size() == 1){
            return new StepMessage(StepMessageType.ALGORITHMENDED);
        }

        EdgeData currentEdge = edges.get(edgeNumber);
        edgeNumber++;

        Set<Integer> firstNodeSet = null;
        Set<Integer> secondNodeSet = null;

        for (Set<Integer> set : connectedNodes) {
            if(firstNodeSet == null){
                if(set.contains(currentEdge.getFirstNodeId())){
                    firstNodeSet = set;
                }
            }   
            
            if(secondNodeSet == null){
                if(set.contains(currentEdge.getSecondNodeId())){
                    secondNodeSet = set;
                }
            }
        }

        if(firstNodeSet.equals(secondNodeSet)){
            return new StepMessage(currentEdge, StepMessageType.EDGEDECLINED);
        }

        firstNodeSet.addAll(secondNodeSet);
        connectedNodes.remove(secondNodeSet);
        edgesWeightSum += currentEdge.getWeight();
        return new StepMessage(currentEdge, StepMessageType.EDGEADDED);
    }

    public int getCurrentTreeWeight(){
        return edgesWeightSum;
    }
    

}
