/*
 * Copyright (C) 2017 Matthias Fussenegger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graphlib.java;

import org.graphlib.java.component.Edge;
import org.graphlib.java.component.Vertex;
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
        Integer identifier = 1;
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
        Integer v1 = 1;
        Integer v2 = 2;
        float weight = 0f;
        Graph<Integer, Float> instance = _graph;
        boolean expResult = false;
        boolean result = instance.addEdgeDirected(v1, v2, weight);
        assertEquals(expResult, result);
    }

    /**
     * Test of addEdgeUndirected method, of class Graph.
     */
    @Test
    public void testAddEdgeUndirected() {
        System.out.println("addEdgeUndirected");
        Integer v1 = 1;
        Integer v2 = 2;
        float weight = 0f;
        Graph<Integer, Float> instance = _graph;
        boolean expResult = false;
        boolean result = instance.addEdgeUndirected(v1, v2, weight);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdjacentVertices method, of class Graph.
     */
    @Test
    public void testGetAdjacentVertices() {
        System.out.println("getAdjacentVertices");
        Integer identifier = 1;
        Graph<Integer, Float> instance = _graph;
        List<Integer> result = instance.getAdjacentVertices(identifier);
        assertEquals(3, result.size());
    }

    /**
     * Test of getAdjacentEdges method, of class Graph.
     */
    @Test
    public void testGetAdjacentEdges() {
        System.out.println("getAdjacentEdges");
        Integer identifier = 1;
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
        Integer identifier = 2;
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
        Integer id1 = 1;
        Integer id2 = 2;
        Graph<Integer, Float> instance = _graph;
        boolean expResult = true;
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
        Integer id1 = 1;
        Integer id2 = 2;
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
