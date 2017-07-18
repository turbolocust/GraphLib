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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages relationships between vertices using a 2D matrix.
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter for identifier
 * @param <V> Generic type parameter for edge weight
 */
public class AdjacencyMatrix<T, V> implements AdjacencyStructure<T, V> {

    /**
     * Static reference to logger of this class.
     */
    private static final Logger LOG = Logger.getLogger(AdjacencyMatrix.class.getName());

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
            StringBuilder output = new StringBuilder();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < _vertices.length; ++i) {
                builder.append("\t");
                builder.append(_vertices[i] != null ? _vertices[i] : "*");
            }
            output.append(builder.toString()).append("\n");
            builder.setLength(0); // reset
            for (int i = 0; i < _adjMatrix.length; ++i) {
                builder.append(_vertices[i] != null ? _vertices[i] : "*");
                builder.append("\t");
                for (int j = 0; j < _adjMatrix[i].length; ++j) {
                    if (_adjMatrix[i][j] != null) {
                        builder.append(_adjMatrix[i][j].getWeight()).append("\t");
                    } else {
                        builder.append("-\t"); // since there is no edge connected
                    }
                }
                output.append(builder.toString()).append("\n");
                builder.setLength(0); // reset
            }
            LOG.log(Level.INFO, output.toString());
        }
    }
}
