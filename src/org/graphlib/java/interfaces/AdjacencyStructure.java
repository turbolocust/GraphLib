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
package org.graphlib.java.interfaces;

import org.graphlib.java.component.Edge;
import org.graphlib.java.component.Vertex;
import java.util.List;

/**
 * Any implementing class is an adjacency structure (e.g. adjacency list or
 * matrix) that manages relationships between vertices.
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter for identifiers
 * @param <V> Generic type parameter for edge weight
 */
public interface AdjacencyStructure<T, V> {

    /**
     * Checks if this adjacency structure contains no vertices.
     *
     * @return True if this adjacency structure contains no vertices.
     */
    boolean isEmpty();

    /**
     * Returns the number of vertices in this adjacency structure.
     *
     * @return The number of vertices in adjacency structure.
     */
    int size();

    /**
     * Adds a new vertex with specified value to the adjacency structure.
     *
     * @param identifier The identifier of the vertex.
     * @return False if vertex already exists in adjacency structure.
     */
    boolean addVertex(T identifier);

    /**
     * Adds an existing vertex to the adjacency structure.
     *
     * @param v The vertex to be added to the adjacency structure.
     * @return False if vertex already exists in adjacency structure.
     */
    boolean addVertex(Vertex<T> v);

    /**
     * Adds a new edge with two new vertices and their specified identifier in
     * both directions if and only if there currently is no edge linked between
     * these two vertices.
     *
     * If there is already a directed edge linked, it is not possible to add an
     * undirected edge. You can either have two directed edges on each vertex or
     * an undirected edge for both vertices.
     *
     * @param v1 The identifier of the first vertex.
     * @param v2 The identifier of the second vertex.
     * @param weight The weight to be set.
     * @return {@code null} if edge already exists.
     */
    Edge<T, V> addEdgeUndirected(T v1, T v2, V weight);

    /**
     * Adds a new edge to the specified vertices in both directions if and only
     * if there currently is no edge linked between these two vertices.
     *
     * If there is already a directed edge linked, it is not possible to add an
     * undirected edge. You can either have two directed edges on each vertex or
     * an undirected edge for both vertices.
     *
     * @param v1 The first vertex.
     * @param v2 The second vertex.
     * @param weight The weight to be set.
     * @return {@code null} if edge already exists.
     */
    Edge<T, V> addEdgeUndirected(Vertex<T> v1, Vertex<T> v2, V weight);

    /**
     * Adds a copy of an edge with already specified vertices in both directions
     * to the adjacency structure (which is either a list or a matrix).
     *
     * @param e The edge to be added.
     * @return {@code null} if edge already exists.
     */
    Edge<T, V> addEdgeUndirected(Edge<T, V> e);

    /**
     * Adds a new edge with two new vertices and their specified identifier if
     * and only if there currently is no edge linked from vertex {@code v1} to
     * vertex {@code v2}.
     *
     * This also means, that if there is an undirected edge connected between
     * these to vertices, no directed edge can be added.
     *
     * @param v1 The identifier of the first vertex.
     * @param v2 The identifier of the second vertex.
     * @param weight The weight to be set.
     * @return {@code null} if edge already exists.
     */
    Edge<T, V> addEdgeDirected(T v1, T v2, V weight);

    /**
     * Adds a new edge to the specified vertices if and only if there currently
     * is no edge linked from vertex {@code v1} to vertex {@code v2}.
     *
     * This also means, that if there is an undirected edge connected between
     * these to vertices, no directed edge can be added.
     *
     * @param v1 The first vertex.
     * @param v2 The second vertex.
     * @param weight The weight to be set.
     * @return {@code null} if edge already exists.
     */
    Edge<T, V> addEdgeDirected(Vertex<T> v1, Vertex<T> v2, V weight);

    /**
     * Adds a copy of an edge with already specified vertices to the adjacency
     * structure (which is either a list or a matrix).
     *
     * @param e The edge to be added.
     * @return {@code null} if edge already exists.
     */
    Edge<T, V> addEdgeDirected(Edge<T, V> e);

    /**
     * Returns a list of all neighbor vertices of the specified vertex.
     *
     * @param identifier The identifier of the specified vertex.
     * @return A list with all neighbor vertices of vertex.
     */
    List<T> getAdjacentVertices(T identifier);

    /**
     * Returns a list of all connected edges on the specified vertex.
     *
     * @param identifier The identifier of the specified vertex.
     * @return A list with all connected edges on the vertex.
     */
    List<Edge<T, V>> getAdjacentEdges(T identifier);

    /**
     * Checks if the specified vertex exists.
     *
     * @param id The identifier of the vertex.
     * @return True if vertex exists.
     */
    boolean containsVertex(T id);

    /**
     * Checks if the specified vertex exists.
     *
     * @param v1 The vertex to be checked.
     * @return True if vertex exists.
     */
    boolean containsVertex(Vertex<T> v1);

    /**
     * Checks if an edge between two vertices exists in only one direction.
     *
     * @param id1 The identifier of the first vertex.
     * @param id2 The identifier of the second vertex.
     * @return True if edge only exists in one direction.
     */
    boolean containsEdgeDirected(T id1, T id2);

    /**
     * Checks if an edge between two vertices exists in only one direction.
     *
     * @param v1 The first vertex.
     * @param v2 The second vertex.
     * @return True if edge only exists in one direction.
     */
    boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2);

    /**
     * Checks if an edge between two vertices exists in both directions.
     *
     * @param id1 The identifier of the first vertex.
     * @param id2 The identifier of the second vertex.
     * @return True if edge exists in both directions.
     */
    boolean containsEdgeUndirected(T id1, T id2);

    /**
     * Checks if an edge between two vertices exists in both directions.
     *
     * @param v1 The first vertex.
     * @param v2 The second vertex.
     * @return True if edge exists in both directions.
     */
    boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2);

    /**
     * Prints the adjacency structure using {@code System.out}.
     */
    void print();

}
