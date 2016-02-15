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
package graph.interfaces;

import graph.components.Edge;
import graph.components.Vertex;
import java.util.List;

/**
 * Any implementing class is an adjacency structure (e.g. adjacency list or
 * matrix) for managing relationships between vertices
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public interface AdjacencyStructure<T> {

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
     * Adds a new vertex with specified value to the adjacency structure
     *
     * @param identifier The identifier of the vertex
     * @return False if vertex already exists in adjacency structure
     */
    boolean addVertex(T identifier);

    /**
     * Adds an existing vertex to the adjacency structure
     *
     * @param v The vertex to be added to the adjacency structure
     * @return False if vertex already exists in adjacency structure
     */
    boolean addVertex(Vertex<T> v);

    /**
     * Adds a new edge with two new vertices (if they don't exist yet) and their
     * specified identifier in both directions if and only if there currently is
     * no edge linked between these two vertices.
     *
     * So if there is already a directed edge linked, it is not possible to add
     * an undirected edge. You can either have two directed edges on each vertex
     * or an undirected edge for both vertices
     *
     * @param v1 The identifier of the first vertex
     * @param v2 The identifier of the second vertex
     * @param weight The weight to be set
     * @return {@code null} if edge already exists
     */
    Edge<T> addEdgeUndirected(T v1, T v2, float weight);

    /**
     * Adds a new edge to the specified vertices in both directions if and only
     * if there currently is no edge linked between these two vertices.
     *
     * So if there is already a directed edge linked, it is not possible to add
     * an undirected edge. You can either have two directed edges on each vertex
     * or an undirected edge for both vertices
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight to be set
     * @return {@code null} if edge already exists
     */
    Edge<T> addEdgeUndirected(Vertex<T> v1, Vertex<T> v2, float weight);

    /**
     * Adds a copy of an edge with already specified vertices in both directions
     * to the adjacency structure (which is either a list or a matrix)
     *
     * @param e The edge to be added
     * @return {@code null} if edge already exists
     */
    Edge<T> addEdgeUndirected(Edge<T> e);

    /**
     * Adds a new edge with two new vertices and their specified identifier if
     * and only if there currently is no edge linked from vertex {@code v1} to
     * vertex {@code v2}.
     *
     * This also means, that if there is an undirected edge connected between
     * these to vertices, no directed edge can be added.
     *
     * @param v1 The identifier of the first vertex
     * @param v2 The identifier of the second vertex
     * @param weight The weight to be set
     * @return {@code null} if edge already exists
     */
    Edge<T> addEdgeDirected(T v1, T v2, float weight);

    /**
     * Adds a new edge to the specified vertices if and only if there currently
     * is no edge linked from vertex {@code v1} to vertex {@code v2}.
     *
     * This also means, that if there is an undirected edge connected between
     * these to vertices, no directed edge can be added.
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param weight The weight to be set
     * @return {@code null} if edge already exists
     */
    Edge<T> addEdgeDirected(Vertex<T> v1, Vertex<T> v2, float weight);

    /**
     * Adds a copy of an edge with already specified vertices to the adjacency
     * structure (which is either a list or a matrix)
     *
     * @param e The edge to be added
     * @return {@code null} if edge already exists
     */
    Edge<T> addEdgeDirected(Edge<T> e);

    /**
     * Returns a list of all neighbor vertices of the specified vertex
     *
     * @param identifier The identifier of the specified vertex
     * @return A list with all neighbor vertices of vertex
     */
    List<T> getAdjacentVertices(T identifier);

    /**
     * Returns a list of all connected edges on the specified vertex
     *
     * @param identifier The identifier of the specified vertex
     * @return A list with all connected edges on vertex
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
     * @param v1 The vertex to be checked
     * @return True if vertex exists
     */
    boolean containsVertex(Vertex<T> v1);

    /**
     * Checks if an edge between two vertices exists in only one direction
     *
     * @param id1 The identifier of the first vertex
     * @param id2 The identifier of the second vertex
     * @return True if edge only exists in one direction
     */
    boolean containsEdgeDirected(T id1, T id2);

    /**
     * Checks if an edge between two vertices exists in only one direction
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if edge only exists in one direction
     */
    boolean containsEdgeDirected(Vertex<T> v1, Vertex<T> v2);

    /**
     * Checks if an edge between two vertices exists in both directions
     *
     * @param id1 The identifier of the first vertex
     * @param id2 The identifier of the second vertex
     * @return True if edge exists in both directions
     */
    boolean containsEdgeUndirected(T id1, T id2);

    /**
     * Checks if an edge between two vertices exists in both directions
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @return True if edge exists in both directions
     */
    boolean containsEdgeUndirected(Vertex<T> v1, Vertex<T> v2);

    /**
     * Prints the adjacency structure to console
     */
    void print();
}
