/*
 * Copyright (C) 2016 Matthias
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
package GraphComponents;

import graph.Graph;
import graph.Vertex;
import graph.Edge;
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
public class GraphTest {

    /**
     * The graph to be tested
     */
    private final Graph<Integer> _graph;

    public GraphTest() {
        _graph = new Graph<>(1, 5);

        _graph.addVertex(1);
        _graph.addVertex(2);
        _graph.addVertex(3);
        _graph.addVertex(4);
        _graph.addVertex(5);

        _graph.addEdgeUndirected(1, 1, 2, 0);
        _graph.addEdgeUndirected(2, 1, 3, 0);
        _graph.addEdgeUndirected(3, 1, 4, 0);
        _graph.addEdgeUndirected(4, 2, 4, 0);
        _graph.addEdgeUndirected(5, 2, 3, 0);
        _graph.addEdgeUndirected(6, 3, 4, 0);
        _graph.addEdgeUndirected(7, 3, 5, 0);
        _graph.addEdgeUndirected(8, 4, 5, 0);
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
        Graph<Integer> instance = _graph;
        instance.print();
    }

    /**
     * Test of addVertex method, of class Graph.
     */
    @Test
    public void testAddVertex_GenericType() {
        System.out.println("addVertex");
        Integer identifier = 1;
        Graph<Integer> instance = _graph;
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
        Graph<Integer> instance = _graph;
        boolean expResult = false;
        boolean result = instance.addVertex(new Vertex<>(1));
        assertEquals(expResult, result);
    }

    /**
     * Test of addEdgeDirected method, of class Graph.
     */
    @Test
    public void testAddEdgeDirected() {
        System.out.println("addEdgeDirected");
        Integer id = 9;
        Integer v1 = 1;
        Integer v2 = 2;
        int weight = 0;
        Graph<Integer> instance = _graph;
        boolean expResult = false;
        boolean result = instance.addEdgeDirected(id, v1, v2, weight);
        assertEquals(expResult, result);
    }

    /**
     * Test of addEdgeUndirected method, of class Graph.
     */
    @Test
    public void testAddEdgeUndirected() {
        System.out.println("addEdgeUndirected");
        Integer id = 9;
        Integer v1 = 1;
        Integer v2 = 2;
        int weight = 0;
        Graph<Integer> instance = _graph;
        boolean expResult = false;
        boolean result = instance.addEdgeUndirected(id, v1, v2, weight);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdjacentVertices method, of class Graph.
     */
    @Test
    public void testGetAdjacentVertices() {
        System.out.println("getAdjacentVertices");
        Integer identifier = 1;
        Graph<Integer> instance = _graph;
        List<Vertex> result = instance.getAdjacentVertices(identifier);
        assertEquals(3, result.size());
    }

    /**
     * Test of getAdjacentEdges method, of class Graph.
     */
    @Test
    public void testGetAdjacentEdges() {
        System.out.println("getAdjacentEdges");
        Integer identifier = 1;
        Graph<Integer> instance = _graph;
        List<Edge> result = instance.getAdjacentEdges(identifier);
        assertEquals(3, result.size());
    }

    /**
     * Test of containsVertex method, of class Graph.
     */
    @Test
    public void testContainsVertex() {
        System.out.println("containsVertex");
        Integer identifier = 2;
        Graph<Integer> instance = _graph;
        boolean expResult = true;
        boolean result = instance.containsVertex(identifier);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsEdge method, of class Graph.
     */
    @Test
    public void testContainsEdge() {
        System.out.println("containsEdge");
        Integer identifier = 2;
        Graph<Integer> instance = _graph;
        boolean expResult = true;
        boolean result = instance.containsEdge(identifier);
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
        Graph<Integer> instance = _graph;
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
        Graph<Integer> instance = _graph;
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
        Graph<Integer> instance = _graph;
        boolean expResult = true;
        boolean result = instance.containsEdgeUndirected(id1, id2);
        assertEquals(expResult, result);
    }

    /**
     * Test of numberOfNodes method, of class Graph.
     */
    @Test
    public void testNumberOfNodes() {
        System.out.println("numberOfNodes");
        Graph<Integer> instance = _graph;
        int expResult = 5;
        int result = instance.numberOfNodes();
        assertEquals(expResult, result);
    }

    /**
     * Test of numberOfEdges method, of class Graph.
     */
    @Test
    public void testNumberOfEdges() {
        System.out.println("numberOfEdges");
        Graph<Integer> instance = _graph;
        int expResult = 8;
        int result = instance.numberOfEdges();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAllPaths method, of class Graph.
     */
    @Test
    public void testFindAllPaths() {
        System.out.println("findAllPaths");
        Integer root = 1;
        int expResult = 44;
        Graph<Integer> instance = _graph;
        List<String> result = instance.findAllPaths(root);
        assertEquals(result.size(), expResult);
    }

    /**
     * Test of isEulerian method, of class Graph.
     */
    @Test
    public void testIsEulerian() {
        System.out.println("isEulerian");
        Graph<Integer> instance = _graph;
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
        Graph<Integer> instance = _graph;
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
        Graph<Integer> instance = _graph;
        boolean expResult = false;
        boolean result = instance.isEulerianCycle();
        assertEquals(expResult, result);
    }
}
