/*
 * Copyright (C) 2016 Matthias Fussenegger
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

import Interfaces.AdjacencyStructure;
import Interfaces.Eulerian;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Matthias Fussenegger
 * @version 2016-01-15
 * @param <T> Generic type parameter used for identifiers
 */
@SuppressWarnings("unchecked")
public class Graph<T> implements Eulerian {

    /**
     * Map that stores the Vertex to its key
     */
    private final Map<T, Vertex<T>> _vertices;

    /**
     * Map that stores the Edge to its key
     */
    private final Map<T, Edge<T>> _edges;

    /**
     * The adjacency structure used by this graph
     */
    private AdjacencyStructure _adjacencyStructure;

    /**
     * Constructor that initializes Graph
     *
     * @param ajdStructure 1 for adjacency list, 2 for adjacency matrix
     * @param size The size of the matrix, obsolete if 1 has been selected
     */
    public Graph(int ajdStructure, int size) {
        _vertices = new HashMap<>();
        _edges = new HashMap<>();
        createGraph(ajdStructure, size);
    }

    /**
     * Initializes Graph with specified adjacency structure
     *
     * @param adjStructure 1 for adjacency list, 2 for adjacency matrix
     * @param size The size of the matrix, obsolete if 1 has been selected
     */
    private void createGraph(int adjStructure, int size) {
        switch (adjStructure) {
            case 1:
                _adjacencyStructure = new AdjacencyList();
                break;
            case 2:
                _adjacencyStructure = new AdjacencyMatrix(size);
                break;
            default:
                throw new IllegalArgumentException("Illegal parameter for adjacency structure!");
        }
    }

    /**
     * Prints the AdjacencyStructure of this Graph
     */
    public void print() {
        _adjacencyStructure.print();
    }

    /**
     * Adds a new Vertex with defined identifier to the Graph
     *
     * @param identifier The value to be added to the Vertex
     * @return False if Vertex already exists in Graph
     */
    public boolean addVertex(T identifier) {
        if (containsVertex(identifier)) {
            return false;
        } else {
            _adjacencyStructure.addVertex(identifier);
            _vertices.put(identifier, new Vertex<>(identifier));
        }
        return true;
    }

    /**
     * Adds an existing Vertex to the Graph
     *
     * @param v The Vertex to be added to the Graph
     * @return False if Vertex already exists in Graph
     */
    public boolean addVertex(Vertex<T> v) {
        return addVertex(v.getId());
    }

    /**
     * Adds a new edge with two new vertices and their defined identifier in one
     * direction (directed)
     *
     * @param id The identifier of the edge
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight of the edge
     * @return False if edge already exists
     */
    public boolean addEdgeDirected(T id, T v1, T v2, int weight) {
        Edge<T> e = null;
        if (_adjacencyStructure != null) {
            e = _adjacencyStructure.addEdgeDirected(v1, v2, weight);
            if (e != null) {
                _edges.put(id, e);
            }
        }
        return e != null;
    }

    /**
     * Adds a new edge with two new vertices and their defined identifier in one
     * direction (directed)
     *
     * @param id The identifier of the edge
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight of the edge
     * @return False if edge already exists
     */
    public boolean addEdgeUndirected(T id, T v1, T v2, int weight) {
        Edge<T> e = null;
        if (_adjacencyStructure != null) {
            e = _adjacencyStructure.addEdgeUndirected(v1, v2, weight);
            if (e != null) {
                _edges.put(id, e);
            }
        }
        return e != null;
    }

    /**
     * Returns the vertex with specified identifier
     *
     * @param identifier The identifier of the vertex to be returned
     * @return The vertex with the specified identifier
     */
    public Vertex<T> getVertex(T identifier) {
        return _vertices.get(identifier);
    }

    /**
     * Returns the edge with specified identifier
     *
     * @param identifier The identifier of the edge to be returned
     * @return The edge with the specified identifier
     */
    public Edge<T> getEdge(T identifier) {
        return _edges.get(identifier);
    }

    /**
     * Returns a list containing children of specified vertex
     *
     * @param identifier The identifier of the specified vertex
     * @return A list with all children of vertex
     */
    public List<Vertex> getAdjacentVertices(T identifier) {
        return _adjacencyStructure.getAdjacentVertices(identifier);
    }

    /**
     * Returns a list of connected edges of specified vertex
     *
     * @param identifier The identifier of the specified vertex
     * @return A list with all connected edges of vertex
     */
    public List<Edge> getAdjacentEdges(T identifier) {
        return _adjacencyStructure.getAdjacentEdges(identifier);
    }

    /**
     * Checks if Graph's map already contains Vertex with defined identifier
     *
     * @param identifier The identifier to be checked
     * @return True if Graph's map contains Vertex with this identifier
     */
    public boolean containsVertex(T identifier) {
        return _vertices.containsKey(identifier);
    }

    /**
     * Checks if Graph's map already contains Edge with defined identifier
     *
     * @param identifier The identifier of the Edge
     * @return True if Graph's map contains Edge with this identifier
     */
    public boolean containsEdge(T identifier) {
        return _edges.containsKey(identifier);
    }

    /**
     * Checks if the edge between id1 and id2 already exists
     *
     * @param id1 The identifier of the first vertex
     * @param id2 The identifier of the second vertex
     * @return True if edge between id1 and id2 already exists
     */
    public boolean containsEdgeDirected(T id1, T id2) {
        return _adjacencyStructure.containsEdgeDirected(id1, id2);
    }

    /**
     * Checks if the edge between vertex 1 and vertex 2 already exists
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if edge between vertex 1 and vertex 2 already exists
     */
    public boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeDirected(v1.getId(), v2.getId());
    }

    /**
     * Checks if the edge between id1 and id2 already exists
     *
     * @param id1 The identifier of the first vertex
     * @param id2 The identifier of the second vertex
     * @return True if edge between id1 and id2 already exists
     */
    public boolean containsEdgeUndirected(T id1, T id2) {
        return _adjacencyStructure.containsEdgeUndirected(id1, id2);
    }

    /**
     * Checks if the edge between vertex 1 and vertex 2 already exists
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if edge between vertex 1 and vertex 2 already exists
     */
    public boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeUndirected(v1.getId(), v2.getId());
    }

    /**
     * Returns the number of vertices in the Graph
     *
     * @return The number of vertices in the Graph
     */
    public int numberOfNodes() {
        return _vertices.size();
    }

    /**
     * Returns the number of edges in the Graph
     *
     * @return The number of edges in the Graph
     */
    public int numberOfEdges() {
        return _edges.size();
    }

    @Override
    public List<String> findAllPaths(Object root) {
        _visited.clear();
        _visitedQ.clear();
        _path.clear();

        Set<T> set = _edges.keySet();
        Iterator<T> iter = set.iterator();
        while (iter.hasNext()) {
            Edge<T> e = _edges.get(iter.next());
            e.setColor(Color.WHITE);
        }

        DFS(root);
        return getAllEulerianPaths();
    }

    /**
     * Depth-first-search for finding all paths
     *
     * @param root The vertex to start with
     */
    private void DFS(Object root) {
        Vertex<T> v = getVertex((T) root);

        _path.push(v);

        if (_visitedQ.size() == numberOfEdges()) {
            String path = "";
            for (int i = _path.size() - 1; i >= 0; --i) {
                path += "->" + _path.get(i).getId();
            }
            _visited.add(path);
        }
        for (Edge e : getAdjacentEdges((T) root)) {
            if (e.getColor().equals(Color.WHITE)) {
                e.setColor(Color.GRAY);
                _visitedQ.add(e);
                v = getVertex((T) e.getTarget(root));
                DFS(v.getId());
                _visitedQ.remove(e);
                e.setColor(Color.WHITE);
            }
        }
        _path.pop();
    }

    @Override
    public boolean isEulerian() {
        int i = 0;
        if (numberOfEdges() > 0) {
            Set<T> set = _vertices.keySet();
            Iterator<T> iter = set.iterator();
            while (iter.hasNext()) {
                T next = iter.next();
                List<Edge> edges = getAdjacentEdges(next);
                if ((edges.size() % 2) != 0) { //odd
                    ++i;
                }
            }
        }
        return i == 0 || i == 2;
    }

    @Override
    public boolean isEulerianTrail() {
        int i = 0;
        if (numberOfEdges() > 0) {
            Set<T> set = _vertices.keySet();
            Iterator<T> iter = set.iterator();
            while (iter.hasNext()) {
                T next = iter.next();
                List<Edge> edges = getAdjacentEdges(next);
                if ((edges.size() % 2) != 0) { //odd
                    ++i;
                }
            }
        }
        return i == 2;
    }

    @Override
    public boolean isEulerianCycle() {
        int i = 0;
        if (numberOfEdges() > 0) {
            Set<T> set = _vertices.keySet();
            Iterator<T> iter = set.iterator();
            while (iter.hasNext()) {
                T next = iter.next();
                List<Edge> edges = getAdjacentEdges(next);
                if ((edges.size() % 2) != 0) { //odd
                    ++i;
                }
            }
        }
        return i == 0;
    }

    private List<String> getAllEulerianPaths() {
        return _visited;
    }

    /**
     * Contains all paths
     */
    private final LinkedList<String> _visited = new LinkedList<>();
    /**
     * Temporarily holds the edges of the path
     */
    private final LinkedList<Edge> _visitedQ = new LinkedList<>();
    /**
     * Temporarily holds the vertices of the path
     */
    private final LinkedList<Vertex> _path = new LinkedList<>();
}
