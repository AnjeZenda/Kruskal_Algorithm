package com.kruskal.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class GraphTest {
    GraphBuilder builder;

    @BeforeEach
    void prepareBulilder(){
        builder = new GraphBuilder();
    }

    @Test
    void testAddUniqueNode(){
        assertEquals(true,builder.addNode(1));
        assertEquals(true,builder.addNode(2));
        assertEquals(true,builder.addNode(3));
    }

    @Test
    void testAddExistingNode(){
        assertEquals(true,builder.addNode(1));
        assertEquals(false,builder.addNode(1));
    }

    @Test
    void testAddUniqueEdge(){
        assertEquals(true,builder.addNode(1));
        assertEquals(true,builder.addNode(2));
        assertEquals(true,builder.addNode(3));
        assertEquals(true,builder.addEdge(1, 2, 2, 1));
    }

    @Test
    void testAddExistingEdge(){
        assertEquals(true,builder.addNode(1));
        assertEquals(true,builder.addNode(2));
        assertEquals(true,builder.addNode(3));
        assertEquals(true,builder.addEdge(1, 2, 2, 1));
        assertEquals(false,builder.addEdge(1, 2, 5, 2));
        assertEquals(false,builder.addEdge(1, 3, 2, 1));
    }

    @Test
    void testAddNegativeEdge(){
        assertEquals(true,builder.addNode(1));
        assertEquals(true,builder.addNode(2));
        assertEquals(false,builder.addEdge(1, 2, -2, 1));
    }

    @Test
    void testDeleteExistingNode(){
        assertEquals(true,builder.addNode(1));
        assertEquals(true,builder.addNode(2));
        assertEquals(true,builder.deleteNode(1));
        assertEquals(true,builder.deleteNode(2));
    }

    @Test
    void testDeleteNonExistingNode(){
        assertEquals(true,builder.addNode(1));
        assertEquals(true,builder.addNode(2));
        assertEquals(false,builder.deleteNode(3));
        assertEquals(false,builder.deleteNode(4));
    }

    @Test
    void testDeleteExistingEdge(){
        assertEquals(true,builder.addNode(1));
        assertEquals(true,builder.addNode(2));
        assertEquals(true,builder.addNode(3));
        assertEquals(true,builder.addEdge(1, 2, 2, 1));
        assertEquals(true,builder.addEdge(1, 3, 2, 2));
        assertEquals(true,builder.deleteEdge(2));
        assertEquals(true,builder.deleteEdge(1));
    }

    @Test
    void testDeleteNonExistingEdge(){
        assertEquals(true,builder.addNode(1));
        assertEquals(true,builder.addNode(2));
        assertEquals(true,builder.addNode(3));
        assertEquals(true,builder.addEdge(1, 2, 2, 1));
        assertEquals(true,builder.addEdge(1, 3, 2, 2));
        assertEquals(false,builder.deleteEdge(3));
        assertEquals(false,builder.deleteEdge(4));
    }

    @Test
    void testIsGraphValid(){
        assertEquals(true, builder.isValid());
        assertEquals(true,builder.addNode(1));
        assertEquals(true,builder.addNode(2));
        assertEquals(false, builder.isValid());
        assertEquals(true,builder.addEdge(1, 2, 2, 1));
        assertEquals(true, builder.isValid());
    }
}