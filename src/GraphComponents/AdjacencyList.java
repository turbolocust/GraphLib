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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter used for identifiers
 */
@SuppressWarnings("unchecked")
public class AdjacencyList<T> implements AdjacencyStructure<T> {

    /**
     * A map that stores all relationships between vertices
     */
    private final HashMap<T, LinkedList<T>> _adjacentVertices;

    /**
     * A map that stores the edge degree of each vertex
     */
    private final HashMap<T, LinkedList<Edge>> _adjacentEdges;

    /**
     * Instantiates a new AdjacencyList
     */
    public AdjacencyList() {
        _adjacentVertices = new HashMap<>();
        _adjacentEdges = new HashMap<>();
    }

    @Override
    public boolean isEmpty() {
        return _adjacentVertices.isEmpty();
    }

    @Override
    public int size() {
        return _adjacentVertices.size();
    }

    @Override
    public boolean addVertex(T identifier) {
        if (containsVertex(identifier)) {
            return false;
        } else {
            _adjacentVertices.put(identifier, new LinkedList<>());
        }
        return true;
    }

    @Override
    public boolean addVertex(Vertex<T> v) {
        return addVertex(v.getId());
    }

    @Override
    public Edge<T> addEdgeDirected(T v1, T v2, int weight) {
        if (containsEdgeDirected(v1, v2)) {
            return null;
        }
        addVertex(v1);
        addVertex(v2);
        /*get list from edges and update neighbours*/
        LinkedList<Edge> list1 = _adjacentEdges.get(v1);
        /*get list from vertices and update neighbours*/
        LinkedList<T> vlist1 = _adjacentVertices.get(v1);
        /*instantiate new edge and add it to map*/
        Edge<T> e = new Edge<>(v1, v2, weight);
        if (list1 == null) {
            list1 = new LinkedList<>();
            _adjacentEdges.put(v1, list1);
        }
        list1.add(e);
        vlist1.add(v2);
        return e;
    }

    @Override
    public Edge<T> addEdgeDirected(Vertex<T> v1, Vertex<T> v2, int weight) {
        return addEdgeDirected(v1.getId(), v2.getId(), weight);
    }

    @Override
    public Edge<T> addEdgeDirected(Edge<T> e) {
        return addEdgeDirected(e.getSource(), e.getTarget(), e.getWeight());
    }

    @Override
    public Edge<T> addEdgeUndirected(T v1, T v2, int weight) {
        if (containsEdgeUndirected(v1, v2)) {
            return null;
        }
        addVertex(v1);
        addVertex(v2);
        /*get lists from edges and update neighbours*/
        LinkedList<Edge> list1 = _adjacentEdges.get(v1);
        LinkedList<Edge> list2 = _adjacentEdges.get(v2);
        /*get lists from vertices and update neighbours*/
        LinkedList<T> vlist1 = _adjacentVertices.get(v1);
        LinkedList<T> vlist2 = _adjacentVertices.get(v2);
        /*instantiate new edge and add it to map*/
        Edge<T> e = new Edge<>(v1, v2, weight);
        if (list1 == null) {
            list1 = new LinkedList<>();
            _adjacentEdges.put(v1, list1);
        }
        if (list2 == null) {
            list2 = new LinkedList<>();
            _adjacentEdges.put(v2, list2);
        }
        list1.add(e);
        list2.add(e);
        vlist1.add(v2);
        vlist2.add(v1);
        return e;
    }

    @Override
    public Edge<T> addEdgeUndirected(Vertex<T> v1, Vertex<T> v2, int weight) {
        return addEdgeUndirected(v1.getId(), v2.getId(), weight);
    }

    @Override
    public Edge<T> addEdgeUndirected(Edge<T> e) {
        return addEdgeUndirected(e.getSource(), e.getTarget(), e.getWeight());
    }

    @Override
    public List<T> getAdjacentVertices(T identifier) {
        return _adjacentVertices.get(identifier);
    }

    @Override
    public List<Edge> getAdjacentEdges(T identifier) {
        return _adjacentEdges.get(identifier);
    }

    @Override
    public boolean containsVertex(T id) {
        return _adjacentVertices.get(id) != null;
    }

    @Override
    public boolean containsVertex(Vertex<T> v1) {
        return containsVertex(v1.getId());
    }

    @Override
    public boolean containsEdgeDirected(T id1, T id2) {
        LinkedList<Edge> list = _adjacentEdges.get(id1);
        T temp;
        /*loop to check first identifier*/
        for (int i = 0; list != null && i < list.size(); ++i) {
            temp = (T) list.get(i).getTarget();
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
        LinkedList<Edge> list = _adjacentEdges.get(id1);
        T temp;
        /*loop to check first identifier*/
        for (int i = 0; list != null && i < list.size(); ++i) {
            temp = (T) list.get(i).getTarget();
            if (temp.equals(id2)) {
                return true;
            }
        }
        list = _adjacentEdges.get(id2);
        /*loop to check second identifier*/
        for (int i = 0; list != null && i < list.size(); ++i) {
            temp = (T) list.get(i).getTarget();
            if (temp.equals(id1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeUndirected(v1.getId(), v2.getId());
    }

    @Override
    public void print() {
        if (!_adjacentEdges.isEmpty()) {
            Set<T> set = _adjacentEdges.keySet();
            Iterator<T> iter = set.iterator();
            LinkedList<Edge> edges;
            String output = "";
            while (iter.hasNext()) {
                T next = iter.next();
                edges = _adjacentEdges.get(next);
                for (Edge e : edges) {
                    output += "[" + e.getTarget(next);
                    output += ", " + e.getWeight() + "] ";
                }
                System.out.println(next + " -> " + output);
                output = ""; //reset output
            }
        }
    }
}
