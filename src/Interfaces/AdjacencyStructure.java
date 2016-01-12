/*
 * Copyright (c) Matthias Fussenegger
 */
package Interfaces;

import GraphComponents.Edge;
import GraphComponents.Vertex;
import java.util.List;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter used for identifiers
 */
public interface AdjacencyStructure<T> {

    /**
     * Prints the adjacency structure to console
     */
    void print();

    /**
     * Checks if this adjacency structure contains no vertices
     *
     * @return True if this adjacency structure contains no vertices
     */
    boolean isEmpty();

    /**
     * Returns the number of vertices in this adjacency structure
     *
     * @return The number of vertices in adjacency structure
     */
    int size();

    /**
     * Adds a new Vertex with defined value to the adjacency structure
     *
     * @param identifier The identifier of the Vertex
     * @return Null if Vertex already exists in adjacency structure
     */
    boolean addVertex(T identifier);

    /**
     * Adds an existing Vertex to the adjacency structure
     *
     * @param v The Vertex to be added to the adjacency structure
     * @return Null if Vertex already exists in adjacency structure
     */
    boolean addVertex(Vertex<T> v);

    /**
     * Adds a new edge with two new vertices and their defined identifier in
     * both directions
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight to be set
     * @return Null if edge already exists
     */
    Edge<T> addEdgeUndirected(T v1, T v2, int weight);

    /**
     * Adds a new edge to the defined vertices in both directions (undirected)
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight to be set
     * @return Null if edge already exists
     */
    Edge<T> addEdgeUndirected(Vertex<T> v1, Vertex<T> v2, int weight);

    /**
     * Adds a new edge with already defined vertices in both directions
     *
     * @param e The edge to be added
     * @return Null if edge already exists
     */
    Edge<T> addEdgeUndirected(Edge<T> e);

    /**
     * Adds a new edge with two new vertices and their defined identifier
     *
     * @param v1 The identifier of the first vertex
     * @param v2 The identifier of the second vertex
     * @param weight The weight to be set
     * @return Null if edge already exists
     */
    Edge<T> addEdgeDirected(T v1, T v2, int weight);

    /**
     * Adds a new edge to the defined vertices
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight to be set
     * @return Null if edge already exists
     */
    Edge<T> addEdgeDirected(Vertex<T> v1, Vertex<T> v2, int weight);

    /**
     * Adds a new edge with already defined vertices
     *
     * @param e The edge to be added
     * @return Null if edge already exists
     */
    Edge<T> addEdgeDirected(Edge<T> e);

    /**
     * Returns a list of children of specified vertex
     *
     * @param identifier The identifier of the specified vertex
     * @return A list with all children of vertex
     */
    List<T> getAdjacentVertices(T identifier);

    /**
     * Returns a list of connected edges of specified vertex
     *
     * @param identifier The identifier of the specified vertex
     * @return A list with all connected edges of vertex
     */
    List<Edge> getAdjacentEdges(T identifier);

    /**
     * Checks if the specified vertex exists
     *
     * @param id The identifier of the vertex
     * @return True if vertex exists
     */
    boolean containsVertex(T id);

    /**
     * Checks if the specified vertex exists
     *
     * @param v1 The vertex
     * @return True if vertex exists
     */
    boolean containsVertex(Vertex<T> v1);

    /**
     * Checks if the edge between id1 and id2 already exists
     *
     * @param id1 The identifier of the first vertex
     * @param id2 The identifier of the second vertex
     * @return True if edge between id1 and id2 already exists
     */
    boolean containsEdgeDirected(T id1, T id2);

    /**
     * Checks if the edge between vertex 1 and vertex 2 already exists
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if edge between vertex 1 and vertex 2 already exists
     */
    boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2);

    /**
     * Checks if the edge between id1 and id2 already exists in both directions
     *
     * @param id1 The identifier of the first vertex
     * @param id2 The identifier of the second vertex
     * @return True if edge between id1 and id2 already exists
     */
    boolean containsEdgeUndirected(T id1, T id2);

    /**
     * Checks if the edge between vertex 1 and vertex 2 already exists in both
     * directions
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if edge between vertex 1 and vertex 2 already exists
     */
    boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2);
}
