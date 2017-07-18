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

import org.graphlib.java.component.Edge;
import org.graphlib.java.component.Vertex;
import org.graphlib.java.exception.GraphException;
import org.graphlib.java.interfaces.AdjacencyStructure;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages relationships between vertices using a map of lists.
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter for identifier
 * @param <V> Generic type parameter for edge weight
 */
public class AdjacencyList<T, V> implements AdjacencyStructure<T, V> {

    /**
     * Static reference to logger of this class.
     */
    private static final Logger LOG = Logger.getLogger(AdjacencyList.class.getName());

    /**
     * A set that stores the identifiers of each vertex.
     */
    private final Set<T> _vertices;

    /**
     * A map that stores the adjacent edges of each vertex.
     */
    private final Map<T, LinkedList<Edge<T, V>>> _adjEdges;

    /**
     * The graph that is associated with this class.
     */
    private final Graph<T, V> _graph;

    /**
     * Constructs a new adjacency list.
     *
     * @param graph The associated {@link Graph}.
     * @throws org.graphlib.java.exception.GraphException If graph is not empty.
     */
    public AdjacencyList(Graph<T, V> graph) throws GraphException {
        if (!graph.isEmpty()) {
            throw new GraphException("Graph is not empty");
        }
        _vertices = new HashSet<>();
        _adjEdges = new HashMap<>();
        _graph = graph;
    }

    @Override
    public boolean isEmpty() {
        return _vertices.isEmpty();
    }

    @Override
    public int size() {
        return _vertices.size();
    }

    @Override
    public boolean addVertex(T identifier) {
        if (!containsVertex(identifier)) {
            _vertices.add(identifier);
            _graph.putVertex(identifier);
            return true;
        }
        return false;
    }

    @Override
    public boolean addVertex(Vertex<T> v) {
        _graph.putVertex(v);
        return addVertex(v.getId());
    }

    @Override
    public boolean containsVertex(T id) {
        return _vertices.contains(id);
    }

    @Override
    public boolean containsVertex(Vertex<T> v1) {
        return containsVertex(v1.getId());
    }

    @Override
    public Edge<T, V> addEdgeDirected(T v1, T v2, V weight) {
        if (containsEdgeDirected(v1, v2)) {
            return null;
        }
        addVertex(v1);
        addVertex(v2);
        /*get list from edges and update neighbours*/
        LinkedList<Edge<T, V>> list1 = _adjEdges.get(v1);
        /*instantiate new edge and add it to the map*/
        Edge<T, V> e = new Edge<>(v1, v2, weight);
        if (list1 == null) {
            list1 = new LinkedList<>();
            _adjEdges.put(v1, list1);
        }
        list1.add(e);
        return e;
    }

    @Override
    public Edge<T, V> addEdgeDirected(Vertex<T> v1, Vertex<T> v2, V weight) {
        return addEdgeDirected(v1.getId(), v2.getId(), weight);
    }

    @Override
    public Edge<T, V> addEdgeDirected(Edge<T, V> e) {
        return addEdgeDirected(e.getSource(), e.getTarget(), e.getWeight());
    }

    @Override
    public Edge<T, V> addEdgeUndirected(T v1, T v2, V weight) {
        if (containsEdgeDirected(v1, v2) || containsEdgeDirected(v2, v1)) {
            return null;
        }
        addVertex(v1);
        addVertex(v2);
        /*get lists from edges and update neighbours*/
        LinkedList<Edge<T, V>> list1 = _adjEdges.get(v1);
        LinkedList<Edge<T, V>> list2 = _adjEdges.get(v2);
        /*instantiate new edge and add it to the map*/
        Edge<T, V> e = new Edge<>(v1, v2, weight);
        if (list1 == null) {
            list1 = new LinkedList<>();
            _adjEdges.put(v1, list1);
        }
        if (list2 == null) {
            list2 = new LinkedList<>();
            _adjEdges.put(v2, list2);
        }
        list1.add(e);
        list2.add(e);
        return e;
    }

    @Override
    public Edge<T, V> addEdgeUndirected(Vertex<T> v1, Vertex<T> v2, V weight) {
        return addEdgeUndirected(v1.getId(), v2.getId(), weight);
    }

    @Override
    public Edge<T, V> addEdgeUndirected(Edge<T, V> e) {
        return addEdgeUndirected(e.getSource(), e.getTarget(), e.getWeight());
    }

    @Override
    public List<T> getAdjacentVertices(T identifier) {
        LinkedList<T> vertices = new LinkedList<>();
        LinkedList<Edge<T, V>> adjEdges = _adjEdges.get(identifier);

        adjEdges.forEach((e) -> {
            vertices.add(e.getTarget(identifier));
        });
        return vertices;
    }

    @Override
    public List<Edge<T, V>> getAdjacentEdges(T identifier) {
        return _adjEdges.get(identifier);
    }

    @Override
    public boolean containsEdgeDirected(T id1, T id2) {
        LinkedList<Edge<T, V>> list = _adjEdges.get(id1);
        T temp; //temporary identifier of vertex

        /*loop to check first identifier*/
        for (int i = 0; list != null && i < list.size(); ++i) {
            temp = list.get(i).getTarget(id1);
            if (temp.equals(id2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeDirected(v1.getId(), v2.getId());
    }

    @Override
    public boolean containsEdgeUndirected(T id1, T id2) {
        return containsEdgeDirected(id1, id2) && containsEdgeDirected(id2, id1);
    }

    @Override
    public boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeUndirected(v1.getId(), v2.getId());
    }

    @Override
    public void print() {
        if (!_adjEdges.isEmpty()) {
            Set<T> set = _adjEdges.keySet();
            Iterator<T> iter = set.iterator();
            List<Edge<T, V>> edges;
            StringBuilder output = new StringBuilder();
            StringBuilder builder = new StringBuilder();
            while (iter.hasNext()) {
                T next = iter.next();
                edges = _adjEdges.get(next);
                builder.append(next).append(" -> ");
                for (Edge<T, V> e : edges) {
                    builder.append("[").append(e.getTarget(next));
                    builder.append(", ").append(e.getWeight()).append("] ");
                }
                output.append(builder.toString()).append("\n");
                builder.setLength(0); // reset
            }
            LOG.log(Level.INFO, output.toString());
        }
    }
}
