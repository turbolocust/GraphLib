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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages relationships between vertices using an {@code n*n} matrix.
 *
 * @author Matthias Fussenegger
 * @param <T> type of the identifier.
 * @param <V> type of the weight.
 */
public final class AdjacencyMatrix<T, V> extends Graph<T, V> {

    /**
     * Static reference to logger of this class.
     */
    private static final Logger LOG = Logger.getLogger(AdjacencyMatrix.class.getName());

    /**
     * The default size of the matrix (n*n) if not specified.
     */
    private final int DEFAULT_SIZE = 1 << 5;

    /**
     * Constant to define if a value in matrix has not been found.
     */
    private final int NOT_FOUND = -1;

    /**
     * The number of vertices stored in matrix.
     */
    private int _numVertices;

    /**
     * Array used to get the right index of a vertex by its identifier.
     */
    private Vertex<T>[] _vertices;

    /**
     * The matrix that stores all relationships between vertices.
     */
    private Edge<T, V>[][] _adjMatrix;

    /**
     * Constructs a new adjacency matrix.
     */
    public AdjacencyMatrix() {
        init(DEFAULT_SIZE);
    }

    /**
     * Constructs a new adjacency matrix with the specified size.
     *
     * @param size the size of the matrix (size * size)
     */
    public AdjacencyMatrix(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must not be zero or negative.");
        }
        init(size);
    }

    /**
     * Initializes the matrix with the specified size.
     *
     * @param size the size of the matrix.
     */
    @SuppressWarnings("unchecked")
    private void init(int size) {
        _vertices = (Vertex<T>[]) Array.newInstance(Vertex.class, size);
        _adjMatrix = (Edge<T, V>[][]) Array.newInstance(Edge.class, size, size);
        _numVertices = 0;
    }

    /**
     * Returns the index of the specified identifier.
     *
     * @param id the identifier of the vertex.
     * @return the index of the vertex in the one-dimensional array.
     */
    private int getIndex(final T id) {
        for (int i = 0; i < _numVertices; ++i) {
            Vertex<T> vertex = _vertices[i];
            if (vertex != null && vertex.getId().equals(id)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Resizes the adjacency matrix by roughly double the size.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = _vertices.length * 2 + 1;
        _vertices = Arrays.copyOf(_vertices, newSize);
        Edge<T, V>[][] matrix = (Edge<T, V>[][]) Array.newInstance(
                Edge.class, newSize, newSize);
        for (int i = 0; i < _adjMatrix.length; ++i) {
            for (int j = 0; j < _adjMatrix.length; ++j) {
                matrix[i][j] = _adjMatrix[i][j];
            }
        }
        _adjMatrix = matrix;
    }

    @Override
    public final boolean isEmpty() {
        return _numVertices == 0;
    }

    @Override
    public final int size() {
        return _numVertices;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final boolean addVertex(final T id) {
        if (_numVertices == _vertices.length) {
            resize();
        }
        if (!containsVertex(id)) {
            _vertices[_numVertices] = new Vertex<T>(id);
            ++_numVertices;
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
        return getIndex(id) != NOT_FOUND;
    }

    @Override
    public final boolean containsVertex(Vertex<T> v1) {
        return containsVertex(v1.getId());
    }

    @Override
    public final Edge<T, V> addEdgeDirected(final T id1, final T id2, V weight) {
        int i = getIndex(id2);
        int j = getIndex(id1);
        if (i != NOT_FOUND && j != NOT_FOUND && _adjMatrix[i][j] == null) {
            Edge<T, V> e = new Edge<T, V>(id1, id2, weight);
            _adjMatrix[i][j] = e;
            return e;
        }
        return null;
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
    public final Edge<T, V> addEdgeUndirected(final T id1, final T id2, V weight) {
        if (!containsEdgeDirected(id1, id2) && !containsEdgeDirected(id2, id1)) {
            int i = getIndex(id2);
            int j = getIndex(id1);
            if (i != NOT_FOUND && j != NOT_FOUND) {
                if (_adjMatrix[i][j] == null && _adjMatrix[j][i] == null) {
                    Edge<T, V> e = new Edge<T, V>(id1, id2, weight);
                    _adjMatrix[i][j] = e;
                    _adjMatrix[j][i] = e;
                    return e;
                }
            }
        }
        return null;
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
        final int i = getIndex(id);
        return i != NOT_FOUND ? _vertices[i] : null;
    }

    @Override
    public final List<Vertex<T>> getVertices() {
        return Collections.unmodifiableList(Arrays.asList(_vertices));
    }

    @Override
    public final List<Vertex<T>> getAdjacentVertices(final T id) {
        LinkedList<Vertex<T>> vertices = new LinkedList<Vertex<T>>();
        int column = getIndex(id);
        for (int i = 0; i < _adjMatrix.length; ++i) {
            if (_adjMatrix[i][column] != null) {
                vertices.add(_vertices[i]);
            }
        }
        return vertices;
    }

    @Override
    public final List<Edge<T, V>> getAdjacentEdges(final T id) {
        LinkedList<Edge<T, V>> edges = new LinkedList<Edge<T, V>>();
        int column = getIndex(id);
        for (int i = 0; i < _adjMatrix.length; ++i) {
            if (_adjMatrix[i][column] != null) {
                edges.add(_adjMatrix[i][column]);
            }
        }
        return edges;
    }

    @Override
    public final boolean containsEdgeDirected(final T id1, final T id2) {
        int i = getIndex(id1);
        int j = getIndex(id2);
        if (i == NOT_FOUND || j == NOT_FOUND) {
            return false;
        }
        Edge<T, V> e1 = _adjMatrix[i][j];
        Edge<T, V> e2 = _adjMatrix[j][i];
        return e1 != null && (e2 == null || !e1.equals(e2));
    }

    @Override
    public final boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeDirected(v1.getId(), v2.getId());
    }

    @Override
    public final boolean containsEdgeUndirected(final T id1, final T id2) {
        int i = getIndex(id1);
        int j = getIndex(id2);
        if (i == NOT_FOUND && j == NOT_FOUND) {
            return false;
        }
        Edge<T, V> e1 = _adjMatrix[i][j];
        Edge<T, V> e2 = _adjMatrix[j][i];
        return e1 != null && e2 != null && e1.equals(e2);
    }

    @Override
    public final boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2) {
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

    @Override
    public final boolean isEulerian() {
        int c = 0;
        if (!isEmpty()) {
            for (int i = 0; i < _vertices.length; ++i) {
                Vertex<T> next = _vertices[i];
                if (next != null) {
                    List<Edge<T, V>> edges = getAdjacentEdges(next.getId());
                    if ((edges.size() % 2) != 0) { // odd
                        ++i;
                    }
                }
            }
        }
        return c == 0 || c == 2;
    }

    @Override
    public final boolean isEulerianTrail() {
        int c = 0;
        if (!isEmpty()) {
            for (int i = 0; i < _vertices.length; ++i) {
                Vertex<T> next = _vertices[i];
                if (next != null) {
                    List<Edge<T, V>> edges = getAdjacentEdges(next.getId());
                    if ((edges.size() % 2) != 0) { // odd
                        ++c;
                    }
                }
            }
        }
        return c == 2;
    }

    @Override
    public final boolean isEulerianCycle() {
        int c = 0;
        if (!isEmpty()) {
            for (int i = 0; i < _vertices.length; ++i) {
                Vertex<T> next = _vertices[i];
                if (next != null) {
                    List<Edge<T, V>> edges = getAdjacentEdges(next.getId());
                    if ((edges.size() % 2) != 0) { // odd
                        ++c;
                    }
                }
            }
        }
        return c == 0;
    }
}
