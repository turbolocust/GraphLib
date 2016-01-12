/*
 * Copyright (c) Matthias Fussenegger
 */
package GraphComponents;

import Interfaces.AdjacencyStructure;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter used for identifiers
 */
public class AdjacencyMatrix<T> implements AdjacencyStructure<T> {

    /**
     * The default size of the matrix (n*n) if not specified
     */
    private final int DEFAULT_SIZE = 32;

    /**
     * Constant to define if a value in matrix has not been found
     */
    private final int NOT_FOUND = -1;

    /**
     * The number of vertices stored in matrix
     */
    private int _numVertices;

    /**
     * Array that is used to get the right index of vertex
     */
    private final T[] _vertices;

    /**
     * The adjacency matrix that stores all relationships between vertices
     */
    private final Edge[][] _adjacencyMatrix;

    /**
     * Instantiates a new AdjacencyMatrix
     *
     * @param size The size of the matrix
     */
    public AdjacencyMatrix(int size) {
        if (size > 0) {
            _vertices = (T[]) new Object[size];
            _adjacencyMatrix = new Edge[size][size];
        } else {
            _vertices = (T[]) new Object[DEFAULT_SIZE];
            _adjacencyMatrix = new Edge[DEFAULT_SIZE][DEFAULT_SIZE];
        }
        _numVertices = 0;
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
            if (containsVertex(identifier) != true) {
                _vertices[_numVertices] = identifier;
                ++_numVertices;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addVertex(Vertex<T> v) {
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

    private int getIndex(T identifier) {
        for (int i = 0; i < _numVertices; ++i) {
            if (_vertices[i].equals(identifier)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public Edge<T> addEdgeDirected(T id1, T id2, int weight) {
        if (!containsEdgeDirected(id1, id2)) {
            addVertex(id1);
            addVertex(id2);

            int i = getIndex(id2);
            int j = getIndex(id1);

            if (i != NOT_FOUND && j != NOT_FOUND) {
                Edge<T> e = new Edge(id1, id2, weight);
                _adjacencyMatrix[i][j] = e;
                return e;
            }
        }
        return null;
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
    public Edge<T> addEdgeUndirected(T id1, T id2, int weight) {
        if (!containsEdgeDirected(id1, id2)) {
            addVertex(id1);
            addVertex(id2);

            int i = getIndex(id2);
            int j = getIndex(id1);

            if (i != NOT_FOUND && j != NOT_FOUND) {
                Edge<T> e = new Edge(id1, id2, weight);
                _adjacencyMatrix[i][j] = e;
                _adjacencyMatrix[j][i] = e;
                return e;
            }
        }
        return null;
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
        LinkedList<T> vertices = new LinkedList<>();
        int column = getIndex(identifier);
        for (int i = 0; i < _adjacencyMatrix.length; ++i) {
            if (_adjacencyMatrix[i][column] != null) {
                vertices.add(_vertices[i]);
            }
        }
        return vertices;
    }

    @Override
    public List<Edge> getAdjacentEdges(T identifier) {
        LinkedList<Edge> edges = new LinkedList<>();
        int column = getIndex(identifier);
        for (int i = 0; i < _adjacencyMatrix.length; ++i) {
            if (_adjacencyMatrix[i][column] != null) {
                edges.add(_adjacencyMatrix[i][column]);
            }
        }
        return edges;
    }

    @Override
    public boolean containsEdgeDirected(T id1, T id2) {
        int column = getIndex(id1);
        int row = getIndex(id2);
        return _adjacencyMatrix[row][column] != null;
    }

    @Override
    public boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2) {
        return containsEdgeDirected(v1.getId(), v2.getId());
    }

    @Override
    public boolean containsEdgeUndirected(T id1, T id2) {
        int column = getIndex(id1);
        int row = getIndex(id2);
        if (_adjacencyMatrix[row][column] != null) {
            if (_adjacencyMatrix[column][row] != null) {
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
            for (int i = 0; i < _adjacencyMatrix.length; ++i) {
                output += _vertices[i] + "\t";
                for (int j = 0; j < _adjacencyMatrix[i].length; ++j) {
                    if (_adjacencyMatrix[i][j] != null) {
                        output += _adjacencyMatrix[i][j].getWeight() + "\t";
                    } else {
                        output += 0 + "\t"; //zero as there is no edge connected
                    }
                }
                System.out.println(output);
                output = ""; //reset output
            }
        }
    }
}
