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
package graph;

import graph.components.Edge;
import graph.components.Vertex;
import graph.exceptions.GraphException;
import graph.interfaces.AdjacencyStructure;
import graph.interfaces.Eulerian;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A graph that makes use of an adjacency structure to manage vertices and edges
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
@SuppressWarnings("unchecked")
public class Graph<T> implements Eulerian {

    /**
     * Map that stores the Vertex to its key
     */
    private final Map<T, Vertex<T>> _vertices;

    /**
     * The adjacency structure used by this graph
     */
    private AdjacencyStructure _adjacencyStructure;

    /**
     * Creates a new graph with no arguments. Therefore, this graph will be
     * initialized with an adjacency list by default that offers unbounded size
     */
    public Graph() {
        _vertices = new HashMap<>();
        createGraph(1, 0);
    }

    /**
     * Creates a new graph with the specified size. Therefore, this graph will
     * be initialized with an adjacency matrix using the specified size
     *
     * @param size The size of the matrix
     */
    public Graph(int size) {
        _vertices = new HashMap<>();
        createGraph(2, size);
    }

    /**
     * Creates a new graph with the specified arguments
     *
     * @param ajdStructure 1 for adjacency list, 2 for adjacency matrix
     * @param size The size of the matrix, obsolete if 1 has been selected
     */
    public Graph(int ajdStructure, int size) {
        _vertices = new HashMap<>();
        createGraph(ajdStructure, size);
    }

    /**
     * Initializes a new graph with the specified adjacency structure
     *
     * @param adjStructure 1 for adjacency list, 2 for adjacency matrix
     * @param size The size of the matrix, obsolete if 1 has been selected
     */
    private void createGraph(int adjStructure, int size) {
        try {
            switch (adjStructure) {
                case 1:
                    _adjacencyStructure = new AdjacencyList(this);
                    break;
                case 2:
                    _adjacencyStructure = new AdjacencyMatrix(this, size);
                    break;
                default:
                    throw new IllegalArgumentException("Illegal parameter for adjacency structure");
            }
        } catch (GraphException ex) {
            Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    /**
     * Checks if this graph contains no vertices
     *
     * @return True if this graph contains no vertices
     */
    public boolean isEmpty() {
        return _vertices.isEmpty();
    }

    /**
     * Returns the number of vertices in this graph
     *
     * @return The number of vertices in this graph
     */
    public int size() {
        return _adjacencyStructure.size();
    }

    /**
     * Adds a new vertex with the specified identifier to this graph
     *
     * @param identifier The value to be added to the vertex
     * @return False if vertex already exists in graph
     */
    public boolean addVertex(T identifier) {
        if (!containsVertex(identifier)) {
            _adjacencyStructure.addVertex(identifier);
            _vertices.put(identifier, new Vertex<>(identifier));
            return true;
        }
        return false;
    }

    /**
     * Adds an existing vertex to this graph
     *
     * @param v The vertex to be added to this graph
     * @return False if vertex already exists in graph
     */
    public boolean addVertex(Vertex<T> v) {
        return addVertex(v.getId());
    }

    /**
     * Adds a new edge with two new vertices and their specified identifier if
     * and only if there currently is no edge linked from vertex {@code v1} to
     * vertex {@code v2}.
     *
     * This also means, that if there is an undirected edge connected between
     * these to vertices, no directed edge can be added.
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight of the edge
     * @return False if edge already exists
     */
    public boolean addEdgeDirected(T v1, T v2, float weight) {
        Edge<T> e = _adjacencyStructure.addEdgeDirected(v1, v2, weight);
        return e != null;
    }

    /**
     * Adds a new edge to the specified vertices if and only if there currently
     * is no edge linked from vertex {@code v1} to vertex {@code v2}.
     *
     * This also means, that if there is an undirected edge connected between
     * these to vertices, no directed edge can be added.
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight of the edge
     * @return False if edge already exists or at least one of the vertices to
     * be added is {@code null}
     */
    public boolean addEdgeDirected(Vertex<T> v1, Vertex<T> v2, float weight) {
        Edge<T> e = null;
        if (v1 != null && v2 != null) {
            e = _adjacencyStructure.addEdgeDirected(v1, v2, weight);
        }
        return e != null;
    }

    /**
     * Adds a copy of an edge with already specified vertices to the adjacency
     * structure (which is either a list or a matrix)
     *
     * @param e The edge to be added
     * @return False if edge already exists or is {@code null}
     */
    public boolean addEdgeDirected(Edge<T> e) {
        if (e != null) {
            e = _adjacencyStructure.addEdgeDirected(e);
        }
        return e != null;
    }

    /**
     * Adds a new edge with two new vertices (if they don't exist yet) and their
     * specified identifier in both directions if and only if there currently is
     * no edge linked between these two vertices.
     *
     * So if there is already a directed edge linked, it is not possible to add
     * an undirected edge. You can either have two directed edges on each vertex
     * or an undirected edge for both vertices
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight of the edge
     * @return False if edge already exists
     */
    public boolean addEdgeUndirected(T v1, T v2, float weight) {
        Edge<T> e = _adjacencyStructure.addEdgeUndirected(v1, v2, weight);
        return e != null;
    }

    /**
     * Adds a new edge to the specified vertices in both directions if and only
     * if there currently is no edge linked between these two vertices.
     *
     * So if there is already a directed edge linked, it is not possible to add
     * an undirected edge. You can either have two directed edges on each vertex
     * or an undirected edge for both vertices
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight of the edge
     * @return False if edge already exists or at least one of the vertices to
     * be added is {@code null}
     */
    public boolean addEdgeUndirected(Vertex<T> v1, Vertex<T> v2, float weight) {
        Edge<T> e = null;
        if (v1 != null && v2 != null) {
            e = _adjacencyStructure.addEdgeUndirected(v1, v2, weight);
        }
        return e != null;
    }

    /**
     * Adds a copy of an edge with already specified vertices in both directions
     * to the adjacency structure (which is either a list or a matrix)
     *
     * @param e The edge to be added
     * @return False if edge already exists or is {@code null}
     */
    public boolean addEdgeUndirected(Edge<T> e) {
        if (e != null) {
            e = _adjacencyStructure.addEdgeUndirected(e);
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
     * Returns a list containing neighbor vertices of specified vertex
     *
     * @param identifier The identifier of the specified vertex
     * @return A list with all neighbor vertices of vertex
     */
    public List<Vertex> getAdjacentVertices(T identifier) {
        return _adjacencyStructure.getAdjacentVertices(identifier);
    }

    /**
     * Returns a list of all connected edges on specified vertex
     *
     * @param identifier The identifier of the specified vertex
     * @return A list with all connected edges on vertex
     */
    public List<Edge> getAdjacentEdges(T identifier) {
        return _adjacencyStructure.getAdjacentEdges(identifier);
    }

    /**
     * Checks if this graph contains a vertex with the specified identifier
     *
     * @param identifier The identifier to be checked
     * @return True if graph contains vertex with this identifier
     */
    public boolean containsVertex(T identifier) {
        return _vertices.containsKey(identifier);
    }

    /**
     * Checks if an edge between two vertices exists in only one direction
     *
     * @param id1 The identifier of the first vertex
     * @param id2 The identifier of the second vertex
     * @return True if edge only exists in one direction
     */
    public boolean containsEdgeDirected(T id1, T id2) {
        return _adjacencyStructure.containsEdgeDirected(id1, id2);
    }

    /**
     * Checks if an edge between two vertices exists in only one direction
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if edge only exists in one direction
     */
    public boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2) {
        if (!v1.equals(_vertices.get(v1.getId()))) {
            return false;
        }
        return containsEdgeDirected(v1.getId(), v2.getId());
    }

    /**
     * Checks if an edge between two vertices exists in both directions
     *
     * @param id1 The identifier of the first vertex
     * @param id2 The identifier of the second vertex
     * @return True if edge exists in both directions
     */
    public boolean containsEdgeUndirected(T id1, T id2) {
        return _adjacencyStructure.containsEdgeUndirected(id1, id2);
    }

    /**
     * Checks if an edge between two vertices exists in both directions
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if edge exists in both directions
     */
    public boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2) {
        if (!v1.equals(_vertices.get(v1.getId()))
                && !v2.equals(_vertices.get(v2.getId()))) {
            return false;
        }
        return containsEdgeUndirected(v1.getId(), v2.getId());
    }

    /**
     * Prints the {@code _adjacencyStructure} of this graph to console
     */
    public void print() {
        _adjacencyStructure.print();
    }

    /**
     * Called by classes that implement {@code AdjacencyStructure} to add a
     * vertex to {@code _vertices} map. This ensures that this graph knows all
     * vertices even if a new vertex has been added via an adjacency structure
     *
     * @param identifier The identifier of the vertex
     */
    protected void putVertex(T identifier) {
        if (!_vertices.containsKey(identifier)) {
            _vertices.put(identifier, new Vertex<>(identifier));
        }
    }

    /**
     * Called by classes that implement {@code AdjacencyStructure} to add a
     * vertex to {@code _vertices} map. This ensures that this graph knows all
     * vertices even if a new vertex has been added via an adjacency structure
     *
     * @param v The vertex to be put
     */
    protected void putVertex(Vertex<T> v) {
        if (!_vertices.containsKey(v.getId())) {
            _vertices.put(v.getId(), v);
        }
    }

    @Override
    public boolean isEulerian() {
        int i = 0;
        if (!isEmpty()) {
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
        if (!isEmpty()) {
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
        if (!isEmpty()) {
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
}
