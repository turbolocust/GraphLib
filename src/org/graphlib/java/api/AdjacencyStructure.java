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
package org.graphlib.java.api;

import org.graphlib.java.Edge;
import org.graphlib.java.Vertex;
import java.util.List;

/**
 * Any implementing class is an adjacency structure (e.g. adjacency list or
 * matrix) that manages relationships between vertices.
 *
 * @author Matthias Fussenegger
 * @param <T> type of the identifier.
 * @param <V> type of the weight.
 */
public interface AdjacencyStructure<T, V> {

    /**
     * Checks if this adjacency structure contains no vertices.
     *
     * @return true if this adjacency structure contains no vertices.
     */
    boolean isEmpty();

    /**
     * Returns the number of vertices in this adjacency structure.
     *
     * @return the number of vertices in adjacency structure.
     */
    int size();

    /**
     * Adds a new vertex with specified value to the adjacency structure.
     *
     * @param id the identifier of the vertex.
     * @return false if vertex already exists in adjacency structure.
     */
    boolean addVertex(T id);

    /**
     * Adds an existing vertex to the adjacency structure.
     *
     * @param v the vertex to be added to the adjacency structure.
     * @return false if vertex already exists in adjacency structure.
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
     * @param v1 the identifier of the first vertex.
     * @param v2 the identifier of the second vertex.
     * @param weight the weight to be set.
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
     * @param v1 the first vertex.
     * @param v2 the second vertex.
     * @param weight the weight to be set.
     * @return {@code null} if edge already exists.
     */
    Edge<T, V> addEdgeUndirected(Vertex<T> v1, Vertex<T> v2, V weight);

    /**
     * Adds a copy of an edge with already specified vertices in both directions
     * to the adjacency structure (which is either a list or a matrix).
     *
     * @param e the edge to be added.
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
     * @param v1 the identifier of the first vertex.
     * @param v2 the identifier of the second vertex.
     * @param weight the weight to be set.
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
     * @param v1 the first vertex.
     * @param v2 the second vertex.
     * @param weight the weight to be set.
     * @return {@code null} if edge already exists.
     */
    Edge<T, V> addEdgeDirected(Vertex<T> v1, Vertex<T> v2, V weight);

    /**
     * Adds a copy of an edge with already specified vertices to the adjacency
     * structure (which is either a list or a matrix).
     *
     * @param e the edge to be added.
     * @return {@code null} if edge already exists.
     */
    Edge<T, V> addEdgeDirected(Edge<T, V> e);

    /**
     * Returns the vertex with the specified identifier if it exits. Otherwise
     * {@code null} is returned.
     *
     * @param id the identifier of the vertex to be returned.
     * @return the vertex with the specified identifier or {@code null} if it
     * does not exist in this adjacency structure.
     */
    Vertex<T> getVertex(T id);

    /**
     * Returns a read-only list with all vertices managed by this adjacency
     * structure.
     *
     * @return all vertices managed by this adjacency structure.
     */
    List<Vertex<T>> getVertices();

    /**
     * Returns a list of all neighbor vertices of the specified vertex.
     *
     * @param id the identifier of the specified vertex.
     * @return a list with all neighbor vertices of vertex.
     */
    List<Vertex<T>> getAdjacentVertices(T id);

    /**
     * Returns a list of all connected edges on the specified vertex.
     *
     * @param id the identifier of the specified vertex.
     * @return a list with all connected edges on the vertex.
     */
    List<Edge<T, V>> getAdjacentEdges(T id);

    /**
     * Checks if the specified vertex exists.
     *
     * @param id the identifier of the vertex.
     * @return true if vertex exists.
     */
    boolean containsVertex(T id);

    /**
     * Checks if the specified vertex exists.
     *
     * @param v1 the vertex to be checked.
     * @return true if vertex exists.
     */
    boolean containsVertex(Vertex<T> v1);

    /**
     * Checks if an edge between two vertices exists in only one direction.
     *
     * @param id1 the identifier of the first vertex.
     * @param id2 the identifier of the second vertex.
     * @return true if edge only exists in one direction.
     */
    boolean containsEdgeDirected(T id1, T id2);

    /**
     * Checks if an edge between two vertices exists in only one direction.
     *
     * @param v1 the first vertex.
     * @param v2 the second vertex.
     * @return true if edge only exists in one direction.
     */
    boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2);

    /**
     * Checks if an edge between two vertices exists in both directions.
     *
     * @param id1 the identifier of the first vertex.
     * @param id2 the identifier of the second vertex.
     * @return true if edge exists in both directions.
     */
    boolean containsEdgeUndirected(T id1, T id2);

    /**
     * Checks if an edge between two vertices exists in both directions.
     *
     * @param v1 the first vertex.
     * @param v2 the second vertex.
     * @return true if edge exists in both directions.
     */
    boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2);

    /**
     * Prints the adjacency structure.
     */
    void print();
}
