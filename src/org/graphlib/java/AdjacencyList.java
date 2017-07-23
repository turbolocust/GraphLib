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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages relationships between vertices using a map of lists.
 *
 * @author Matthias Fussenegger
 * @param <T> type of the identifier.
 * @param <V> type of the weight.
 */
public final class AdjacencyList<T, V> extends Graph<T, V> {

    /**
     * Static reference to logger of this class.
     */
    private static final Logger LOG = Logger.getLogger(AdjacencyList.class.getName());

    /**
     * Map that stores the Vertex to its key.
     */
    private final Map<T, Vertex<T>> _vertices;

    /**
     * A map that stores the adjacent edges of each vertex.
     */
    private final Map<T, LinkedList<Edge<T, V>>> _adjEdges;

    /**
     * Constructs a new adjacency list.
     */
    public AdjacencyList() {
        _vertices = new HashMap<T, Vertex<T>>();
        _adjEdges = new HashMap<T, LinkedList<Edge<T, V>>>();
    }

    /**
     * Returns the edge that connects the vertices with the specified
     * identifiers. If there is no edge, {@code null} will be returned.
     *
     * @param id1 identifier of the first edge.
     * @param id2 identifier of the second edge.
     * @return the edge that connects the two vertices or {@code null}.
     */
    private Edge<T, V> getEdge(T id1, T id2) {
        LinkedList<Edge<T, V>> list = _adjEdges.get(id1);
        Edge<T, V> edge = null;
        if (list != null) {
            final Iterator<Edge<T, V>> iter = list.iterator();
            while (iter.hasNext()) { // loop to check first identifier
                Edge<T, V> nextEdge = iter.next();
                if (nextEdge.getTarget(id1).equals(id2)) {
                    edge = nextEdge;
                    break;
                }
            }
        }
        return edge;
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
    public boolean addVertex(T id) {
        if (!containsVertex(id)) {
            _vertices.put(id, new Vertex<T>(id));
            return true;
        }
        return false;
    }

    @Override
    public boolean addVertex(Vertex<T> v) {
        return addVertex(v.getId());
    }

    @Override
    public boolean containsVertex(T id) {
        return _vertices.containsKey(id);
    }

    @Override
    public boolean containsVertex(Vertex<T> v1) {
        return containsVertex(v1.getId());
    }

    @Override
    public Edge<T, V> addEdgeDirected(T v1, T v2, V weight) {
        if (getVertex(v1) == null || getVertex(v2) == null
                || containsEdgeDirected(v1, v2)) {
            return null;
        }
        // get list from edges and update neighbours
        LinkedList<Edge<T, V>> list1 = _adjEdges.get(v1);
        // instantiate new edge and add it to the map
        Edge<T, V> e = new Edge<T, V>(v1, v2, weight);
        if (list1 == null) {
            list1 = new LinkedList<Edge<T, V>>();
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
        if (getVertex(v1) == null || getVertex(v2) == null
                || containsEdgeDirected(v1, v2) || containsEdgeDirected(v2, v1)) {
            return null;
        }
        // get lists from edges and update neighbours
        LinkedList<Edge<T, V>> list1 = _adjEdges.get(v1);
        LinkedList<Edge<T, V>> list2 = _adjEdges.get(v2);
        // instantiate new edge and add it to the map
        Edge<T, V> e = new Edge<T, V>(v1, v2, weight);
        if (list1 == null) {
            list1 = new LinkedList<Edge<T, V>>();
            _adjEdges.put(v1, list1);
        }
        if (list2 == null) {
            list2 = new LinkedList<Edge<T, V>>();
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
    public Vertex<T> getVertex(T id) {
        return _vertices.get(id);
    }

    @Override
    public List<Vertex<T>> getVertices() {
        List<Vertex<T>> vertices = new ArrayList<Vertex<T>>(_vertices.size());
        for (Entry<T, Vertex<T>> entry : _vertices.entrySet()) {
            vertices.add(entry.getValue());
        }
        return Collections.unmodifiableList(vertices);
    }

    @Override
    public List<Vertex<T>> getAdjacentVertices(T id) {
        LinkedList<Vertex<T>> vertices = new LinkedList<Vertex<T>>();
        LinkedList<Edge<T, V>> adjEdges = _adjEdges.get(id);
        for (Edge<T, V> edge : adjEdges) {
            vertices.add(_vertices.get(edge.getTarget(id)));
        }
        return vertices;
    }

    @Override
    public List<Edge<T, V>> getAdjacentEdges(T id) {
        return _adjEdges.get(id);
    }

    @Override
    public boolean containsEdgeDirected(T id1, T id2) {
        Edge<T, V> e1 = getEdge(id1, id2);
        Edge<T, V> e2 = getEdge(id2, id1);
        return e1 != null && (e2 == null || !e1.equals(e2));
    }

    @Override
    public boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeDirected(v1.getId(), v2.getId());
    }

    @Override
    public boolean containsEdgeUndirected(T id1, T id2) {
        Edge<T, V> e1 = getEdge(id1, id2);
        Edge<T, V> e2 = getEdge(id2, id1);
        return e1 != null && e2 != null && e1.equals(e2);
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
            StringBuilder builder = new StringBuilder("\n");
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

    @Override
    public boolean isEulerian() {
        int i = 0;
        if (!isEmpty()) {
            Set<T> set = _vertices.keySet();
            Iterator<T> iter = set.iterator();
            while (iter.hasNext()) {
                T next = iter.next();
                List<Edge<T, V>> edges = getAdjacentEdges(next);
                if ((edges.size() % 2) != 0) { // odd
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
                List<Edge<T, V>> edges = getAdjacentEdges(next);
                if ((edges.size() % 2) != 0) { // odd
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
                List<Edge<T, V>> edges = getAdjacentEdges(next);
                if ((edges.size() % 2) != 0) { // odd
                    ++i;
                }
            }
        }
        return i == 0;
    }
}
