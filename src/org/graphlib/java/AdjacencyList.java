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
     * Stores all vertices.
     */
    private final List<Vertex<T>> _vertices;

    /**
     * A map that stores the adjacent edges of each vertex.
     */
    private final Map<T, List<Edge<T, V>>> _adjEdges;

    /**
     * Constructs a new adjacency list.
     */
    @SuppressWarnings("unchecked")
    public AdjacencyList() {
        _vertices = new ArrayList<Vertex<T>>();
        _adjEdges = new HashMap<T, List<Edge<T, V>>>();
    }

    /**
     * Returns the edge that connects the vertices with the specified
     * identifiers. If there is no edge, {@code null} will be returned.
     *
     * @param id1 identifier of the first edge.
     * @param id2 identifier of the second edge.
     * @return the edge that connects the two vertices or {@code null}.
     */
    private Edge<T, V> getEdge(final T id1, final T id2) {
        List<Edge<T, V>> list = _adjEdges.get(id1);
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
    public final boolean isEmpty() {
        return _vertices.isEmpty();
    }

    @Override
    public final int size() {
        return _vertices.size();
    }

    @Override
    public final boolean addVertex(final T id) {
        if (!containsVertex(id)) {
            _vertices.add(new Vertex<T>(id));
            return true;
        }
        return false;
    }

    @Override
    public final boolean addVertex(Vertex<T> v) {
        return addVertex(v.getId());
    }

    @Override
    public final boolean containsVertex(final T id) {
        return getVertex(id) != null;
    }

    @Override
    public final boolean containsVertex(Vertex<T> v1) {
        return _vertices.indexOf(v1) > -1;
    }

    @Override
    public final Edge<T, V> addEdgeDirected(final T v1, final T v2, V weight) {
        if (getVertex(v1) == null || getVertex(v2) == null
                || containsEdgeDirected(v1, v2)) {
            return null;
        }
        // get list from edges and update neighbours
        List<Edge<T, V>> list1 = _adjEdges.get(v1);
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
    public final Edge<T, V> addEdgeDirected(Vertex<T> v1, Vertex<T> v2, V weight) {
        return addEdgeDirected(v1.getId(), v2.getId(), weight);
    }

    @Override
    public final Edge<T, V> addEdgeDirected(Edge<T, V> e) {
        return addEdgeDirected(e.getSource(), e.getTarget(), e.getWeight());
    }

    @Override
    public final Edge<T, V> addEdgeUndirected(final T v1, final T v2, V weight) {
        if (getVertex(v1) == null || getVertex(v2) == null
                || containsEdgeDirected(v1, v2) || containsEdgeDirected(v2, v1)) {
            return null;
        }
        // get lists from edges and update neighbours
        List<Edge<T, V>> list1 = _adjEdges.get(v1);
        List<Edge<T, V>> list2 = _adjEdges.get(v2);
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
    public final Edge<T, V> addEdgeUndirected(Vertex<T> v1, Vertex<T> v2, V weight) {
        return addEdgeUndirected(v1.getId(), v2.getId(), weight);
    }

    @Override
    public final Edge<T, V> addEdgeUndirected(Edge<T, V> e) {
        return addEdgeUndirected(e.getSource(), e.getTarget(), e.getWeight());
    }

    @Override
    public final Vertex<T> getVertex(final T id) {
        final Vertex<T> vertex = new Vertex<T>(id);
        final int index = _vertices.indexOf(vertex);
        return index > -1 ? _vertices.get(index) : null;
    }

    @Override
    public final List<Vertex<T>> getVertices() {
        return Collections.unmodifiableList(_vertices);
    }

    @Override
    public final List<Vertex<T>> getAdjacentVertices(final T id) {
        List<Vertex<T>> vertices = new LinkedList<Vertex<T>>();
        List<Edge<T, V>> adjEdges = _adjEdges.get(id);
        for (Edge<T, V> edge : adjEdges) {
            vertices.add(getVertex(edge.getTarget(id)));
        }
        return vertices;
    }

    @Override
    public final List<Edge<T, V>> getAdjacentEdges(final T id) {
        return _adjEdges.get(id);
    }

    @Override
    public final boolean containsEdgeDirected(final T id1, final T id2) {
        Edge<T, V> e1 = getEdge(id1, id2);
        Edge<T, V> e2 = getEdge(id2, id1);
        return e1 != null && (e2 == null || !e1.equals(e2));
    }

    @Override
    public final boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeDirected(v1.getId(), v2.getId());
    }

    @Override
    public final boolean containsEdgeUndirected(final T id1, final T id2) {
        Edge<T, V> e1 = getEdge(id1, id2);
        Edge<T, V> e2 = getEdge(id2, id1);
        return e1 != null && e2 != null && e1.equals(e2);
    }

    @Override
    public final boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2) {
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
    public final boolean isEulerian() {
        int i = 0;
        if (!isEmpty()) {
            final Iterator<Vertex<T>> iter = _vertices.iterator();
            while (iter.hasNext()) {
                T next = iter.next().getId();
                List<Edge<T, V>> edges = getAdjacentEdges(next);
                if ((edges.size() % 2) != 0) { // odd
                    ++i;
                }
            }
        }
        return i == 0 || i == 2;
    }

    @Override
    public final boolean isEulerianTrail() {
        int i = 0;
        if (!isEmpty()) {
            final Iterator<Vertex<T>> iter = _vertices.iterator();
            while (iter.hasNext()) {
                T next = iter.next().getId();
                List<Edge<T, V>> edges = getAdjacentEdges(next);
                if ((edges.size() % 2) != 0) { // odd
                    ++i;
                }
            }
        }
        return i == 2;
    }

    @Override
    public final boolean isEulerianCycle() {
        int i = 0;
        if (!isEmpty()) {
            final Iterator<Vertex<T>> iter = _vertices.iterator();
            while (iter.hasNext()) {
                T next = iter.next().getId();
                List<Edge<T, V>> edges = getAdjacentEdges(next);
                if ((edges.size() % 2) != 0) { // odd
                    ++i;
                }
            }
        }
        return i == 0;
    }
}
