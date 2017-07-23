/*
 * The MIT License
 *
 * Copyright 2017 Matthias Fussenegger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.graphlib.java;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias Fussenegger
 */
public class GraphTestMatrix extends GraphTest {

    public GraphTestMatrix() {
        super(AdjacencyStructureType.MATRIX);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of print method, of class Graph.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        Graph<Integer, Float> instance = _graph;
        instance.print();
    }

    /**
     * Test of addVertex method, of class Graph.
     */
    @Test
    public void testAddVertex_GenericType() {
        System.out.println("addVertex");
        int identifier = 1;
        Graph<Integer, Float> instance = _graph;
        boolean expResult = false;
        boolean result = instance.addVertex(identifier);
        assertEquals(expResult, result);
    }

    /**
     * Test of addVertex method, of class Graph.
     */
    @Test
    public void testAddVertex_Vertex() {
        System.out.println("addVertex");
        Graph<Integer, Float> instance = _graph;
        boolean expResult = true;
        boolean result = instance.addVertex(new Vertex<>(6));
        assertEquals(expResult, result);
    }

    /**
     * Test of addEdgeDirected method, of class Graph.
     */
    @Test
    public void testAddEdgeDirected() {
        System.out.println("addEdgeDirected");
        int v1 = 1;
        int v2 = 2;
        float weight = 0f;
        Graph<Integer, Float> instance = _graph;
        assertNull(instance.addEdgeDirected(v1, v2, weight));
    }

    /**
     * Test of addEdgeUndirected method, of class Graph.
     */
    @Test
    public void testAddEdgeUndirected() {
        System.out.println("addEdgeUndirected");
        int v1 = 1;
        int v2 = 2;
        float weight = 0f;
        Graph<Integer, Float> instance = _graph;
        assertNull(instance.addEdgeUndirected(v1, v2, weight));
    }

    /**
     * Test of getAdjacentVertices method, of class Graph.
     */
    @Test
    public void testGetAdjacentVertices() {
        System.out.println("getAdjacentVertices");
        int identifier = 1;
        Graph<Integer, Float> instance = _graph;
        List<Vertex<Integer>> result = instance.getAdjacentVertices(identifier);
        assertEquals(3, result.size());
    }

    /**
     * Test of getAdjacentEdges method, of class Graph.
     */
    @Test
    public void testGetAdjacentEdges() {
        System.out.println("getAdjacentEdges");
        int identifier = 1;
        Graph<Integer, Float> instance = _graph;
        List<Edge<Integer, Float>> result = instance.getAdjacentEdges(identifier);
        assertEquals(3, result.size());
    }

    /**
     * Test of containsVertex method, of class Graph.
     */
    @Test
    public void testContainsVertex() {
        System.out.println("containsVertex");
        int identifier = 2;
        Graph<Integer, Float> instance = _graph;
        boolean expResult = true;
        boolean result = instance.containsVertex(identifier);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsEdgeDirected method, of class Graph.
     */
    @Test
    public void testContainsEdgeDirected_GenericType_GenericType() {
        System.out.println("containsEdgeDirected");
        int id1 = 1;
        int id2 = 2;
        Graph<Integer, Float> instance = _graph;
        boolean expResult = false;
        boolean result = instance.containsEdgeDirected(id1, id2);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsEdgeDirected method, of class Graph.
     */
    @Test
    public void testContainsEdgeDirected_Vertex_Vertex() {
        System.out.println("containsEdgeDirected");
        Graph<Integer, Float> instance = _graph;
        boolean expResult = false;
        boolean result = instance.containsEdgeDirected(new Vertex<>(1), new Vertex<>(2));
        assertEquals(expResult, result);
    }

    /**
     * Test of containsEdgeUndirected method, of class Graph.
     */
    @Test
    public void testContainsEdgeUndirected_GenericType_GenericType() {
        System.out.println("containsEdgeUndirected");
        int id1 = 1;
        int id2 = 2;
        Graph<Integer, Float> instance = _graph;
        boolean expResult = true;
        boolean result = instance.containsEdgeUndirected(id1, id2);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEulerian method, of class Graph.
     */
    @Test
    public void testIsEulerian() {
        System.out.println("isEulerian");
        Graph<Integer, Float> instance = _graph;
        boolean expResult = true;
        boolean result = instance.isEulerian();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEulerianTrail method, of class Graph.
     */
    @Test
    public void testIsEulerianTrail() {
        System.out.println("isEulerianTrail");
        Graph<Integer, Float> instance = _graph;
        boolean expResult = true;
        boolean result = instance.isEulerianTrail();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEulerianCycle method, of class Graph.
     */
    @Test
    public void testIsEulerianCycle() {
        System.out.println("isEulerianCycle");
        Graph<Integer, Float> instance = _graph;
        boolean expResult = false;
        boolean result = instance.isEulerianCycle();
        assertEquals(expResult, result);
    }
}
