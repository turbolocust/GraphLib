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
import java.util.LinkedList;
import java.util.List;

/**
 * Manages relationships between vertices using a 2D matrix.
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter for identifiers
 * @param <V> Generic type parameter for edge weight
 */
public class AdjacencyMatrix<T, V> implements AdjacencyStructure<T, V> {

    /**
     * The default size of the matrix (n*n) if not specified.
     */
    private final int DEFAULT_SIZE = 32;

    /**
     * Constant to define if a value in matrix has not been found.
     */
    private final int NOT_FOUND = -1;

    /**
     * The graph that is aggregated with this class.
     */
    private final Graph<T, V> _graph;

    /**
     * The number of vertices stored in matrix.
     */
    private int _numVertices;

    /**
     * Array used to get the right index of a vertex by its identifier.
     */
    private final T[] _vertices;

    /**
     * The matrix that stores all relationships between vertices.
     */
    private final Edge<T, V>[][] _adjMatrix;

    /**
     * Constructs a new adjacency matrix.
     *
     * @param graph The associated {@link Graph}
     * @param size The size of the matrix (size * size)
     * @throws org.graphlib.java.exception.GraphException If graph is not empty
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public AdjacencyMatrix(Graph<T, V> graph, int size) throws GraphException {
        if (!graph.isEmpty()) {
            throw new GraphException("Graph is not empty");
        }
        if (size > 0) {
            _vertices = (T[]) new Object[size];
            _adjMatrix = new Edge[size][size];
        } else {
            _vertices = (T[]) new Object[DEFAULT_SIZE];
            _adjMatrix = new Edge[DEFAULT_SIZE][DEFAULT_SIZE];
        }
        _graph = graph;
        _numVertices = 0;
    }

    /**
     * Returns the index of the specified identifier.
     *
     * @param identifier The identifier of the vertex.
     * @return The index of the vertex in the two-dimensional array.
     */
    private int getIndex(T identifier) {
        for (int i = 0; i < _numVertices; ++i) {
            if (_vertices[i].equals(identifier)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public boolean isEmpty() {
        return _numVertices == 0;
    }

    @Override
    public int size() {
        return _numVertices;
    }

    @Override
    public boolean addVertex(T identifier) {
        if (_numVertices != _vertices.length) {
            if (!containsVertex(identifier)) {
                _vertices[_numVertices] = identifier;
                _graph.putVertex(identifier);
                ++_numVertices;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addVertex(Vertex<T> v) {
        _graph.putVertex(v);
        return addVertex(v.getId());
    }

    @Override
    public boolean containsVertex(T identifier) {
        for (int i = 0; i < _numVertices; ++i) {
            if (_vertices[i].equals(identifier)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsVertex(Vertex<T> v1) {
        return containsVertex(v1.getId());
    }

    @Override
    public Edge<T, V> addEdgeDirected(T id1, T id2, V weight) {
        if (!containsEdgeDirected(id1, id2)) {
            addVertex(id1);
            addVertex(id2);

            int i = getIndex(id2);
            int j = getIndex(id1);

            if (i != NOT_FOUND && j != NOT_FOUND) {
                Edge<T, V> e = new Edge<>(id1, id2, weight);
                _adjMatrix[i][j] = e;
                return e;
            }
        }
        return null;
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
    public Edge<T, V> addEdgeUndirected(T id1, T id2, V weight) {
        if (!containsEdgeDirected(id1, id2) && !containsEdgeDirected(id2, id1)) {
            addVertex(id1);
            addVertex(id2);

            int i = getIndex(id2);
            int j = getIndex(id1);

            if (i != NOT_FOUND && j != NOT_FOUND) {
                Edge<T, V> e = new Edge<>(id1, id2, weight);
                _adjMatrix[i][j] = e;
                _adjMatrix[j][i] = e;
                return e;
            }
        }
        return null;
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
        int column = getIndex(identifier);
        for (int i = 0; i < _adjMatrix.length; ++i) {
            if (_adjMatrix[i][column] != null) {
                vertices.add(_vertices[i]);
            }
        }
        return vertices;
    }

    @Override
    public List<Edge<T, V>> getAdjacentEdges(T identifier) {
        LinkedList<Edge<T, V>> edges = new LinkedList<>();
        int column = getIndex(identifier);
        for (int i = 0; i < _adjMatrix.length; ++i) {
            if (_adjMatrix[i][column] != null) {
                edges.add(_adjMatrix[i][column]);
            }
        }
        return edges;
    }

    @Override
    public boolean containsEdgeDirected(T id1, T id2) {
        int column = getIndex(id1);
        int row = getIndex(id2);
        return _adjMatrix[row][column] != null;
    }

    @Override
    public boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeDirected(v1.getId(), v2.getId());
    }

    @Override
    public boolean containsEdgeUndirected(T id1, T id2) {
        int column = getIndex(id1);
        int row = getIndex(id2);
        if (_adjMatrix[row][column] != null) {
            if (_adjMatrix[column][row] != null) {
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
        if (_vertices.length > 0) {
            String output = "";
            for (int i = 0; i < _vertices.length; ++i) {
                output += "\t" + _vertices[i];
            }
            System.out.println(output);
            output = ""; //reset output
            for (int i = 0; i < _adjMatrix.length; ++i) {
                output += _vertices[i] + "\t";
                for (int j = 0; j < _adjMatrix[i].length; ++j) {
                    if (_adjMatrix[i][j] != null) {
                        output += _adjMatrix[i][j].getWeight() + "\t";
                    } else {
                        output += "-" + "\t"; //as there is no edge connected
                    }
                }
                System.out.println(output);
                output = ""; //reset output
            }
        }
    }
}
