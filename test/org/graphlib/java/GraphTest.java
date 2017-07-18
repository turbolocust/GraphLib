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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Matthias Fussenegger
 */
public class GraphTest {

    /**
     * The graph to be tested.
     */
    protected final Graph<Integer, Float> _graph;

    public GraphTest() {
        this(AdjacencyStructureType.LIST);
    }

    protected GraphTest(AdjacencyStructureType type) {
        _graph = new Graph<>(type, 0);

        _graph.addVertex(1);
        _graph.addVertex(2);
        _graph.addVertex(3);
        _graph.addVertex(4);
        _graph.addVertex(5);

        _graph.addEdgeUndirected(1, 2, 0f);
        _graph.addEdgeUndirected(1, 3, 0f);
        _graph.addEdgeUndirected(1, 4, 0f);
        _graph.addEdgeUndirected(2, 4, 0f);
        _graph.addEdgeUndirected(2, 3, 0f);
        _graph.addEdgeUndirected(3, 4, 0f);
        _graph.addEdgeUndirected(3, 5, 0f);
        _graph.addEdgeUndirected(4, 5, 0f);
    }

    @Test
    public void testSize() {
        assertEquals(_graph.size(), 5);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(!_graph.isEmpty());
    }
}
