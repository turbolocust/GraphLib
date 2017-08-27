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

import java.util.Objects;
import org.graphlib.java.api.AdjacencyStructure;
import org.graphlib.java.api.Eulerian;

/**
 * A graph that makes use of an {@link AdjacencyStructure} to manage
 * relationships between vertices and edges. The graph itself manages the actual
 * instances of {@link Vertex}.
 *
 * @author Matthias Fussenegger
 * @param <T> type of the identifier.
 * @param <V> type of the weight.
 */
public abstract class Graph<T, V> implements AdjacencyStructure<T, V>, Eulerian {

    protected Graph() {
    }

    /**
     * Initializes a new graph with the specified adjacency structure.
     *
     * @param <T> type of the identifier.
     * @param <V> type of the weight.
     * @param type graph will be backed by this adjacency structure. Must not be
     * {@code null}.
     * @return a new instance of {@link Graph} which is backed by the specified
     * type of adjacency structure.
     */
    public static <T, V> Graph<T, V> createGraph(AdjacencyStructureType type) {
        Objects.requireNonNull(type);
        Graph<T, V> structure;
        switch (type) {
            case LIST:
                structure = new AdjacencyList<T, V>();
                break;
            case MATRIX:
                structure = new AdjacencyMatrix<T, V>();
                break;
            default:
                throw new IllegalArgumentException(type.name());
        }
        return structure;
    }
}
