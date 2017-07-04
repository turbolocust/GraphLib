/*
 * Copyright (C) 2017 Matthias Fussenegger
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
