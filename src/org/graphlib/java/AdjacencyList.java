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
