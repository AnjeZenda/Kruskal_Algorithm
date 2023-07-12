package com.kruskal.kruskalalgorithm;

import org.junit.jupiter.api.Test;

import com.kruskal.graph.GraphBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class KruskalTest {
    Kruskal algorithm;

    @BeforeEach
    void preparation(){
        GraphBuilder builder = new GraphBuilder();
        builder.addNode(1);
        builder.addNode(2);
        builder.addNode(3);
        builder.addNode(4);
        builder.addEdge(1, 2, 1, 1);
        builder.addEdge(2, 3, 2, 2);
        builder.addEdge(1, 3, 3, 3);
        builder.addEdge(3, 4, 4, 4);
        builder.addEdge(2, 4, 5, 5);

        algorithm = new Kruskal(builder.getGraph());
    }

    @Test
    void testGetCurrentTreeWeight() {
        assertEquals(0, algorithm.getCurrentTreeWeight());
        algorithm.makeStep();
        assertEquals(1, algorithm.getCurrentTreeWeight());
        algorithm.makeStep();
        assertEquals(3, algorithm.getCurrentTreeWeight());
        algorithm.makeStep();
        assertEquals(3, algorithm.getCurrentTreeWeight());
        algorithm.makeStep();
        assertEquals(7, algorithm.getCurrentTreeWeight());
        algorithm.makeStep();
        assertEquals(7, algorithm.getCurrentTreeWeight());
    }

    @Test
    void testMakeStep() {
        StepMessage message = algorithm.makeStep();
        assertEquals(1,message.getEdgeId());
        assertEquals(StepMessageType.EDGEADDED,message.getType());
        message = algorithm.makeStep();
        assertEquals(2,message.getEdgeId());
        assertEquals(StepMessageType.EDGEADDED,message.getType());
        message = algorithm.makeStep();
        assertEquals(3,message.getEdgeId());
        assertEquals(StepMessageType.EDGEDECLINED,message.getType());
        message = algorithm.makeStep();
        assertEquals(4,message.getEdgeId());
        assertEquals(StepMessageType.EDGEADDED,message.getType());
        message = algorithm.makeStep();
        assertEquals(-1,message.getEdgeId());
        assertEquals(StepMessageType.ALGORITHMENDED,message.getType());
    }

    @Test
    void testStepBack() {
        algorithm.makeStep();
        algorithm.makeStep();
        algorithm.makeStep();
        algorithm.makeStep();
        algorithm.makeStep();

        StepMessage message = algorithm.stepBack();
        assertEquals(4,message.getEdgeId());
        message = algorithm.stepBack();
        assertEquals(3,message.getEdgeId());
        message = algorithm.stepBack();
        assertEquals(2,message.getEdgeId());
        message = algorithm.stepBack();
        assertEquals(1,message.getEdgeId());
        message = algorithm.stepBack();
        assertEquals(null,message);
    }
}
