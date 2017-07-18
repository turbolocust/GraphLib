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
package org.graphlib.java.component;

import java.awt.Color;

/**
 * A vertex is a node of the graph and can be linked with other ones by edges.
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter that is used for identifiers
 */
public class Vertex<T> {

    /**
     * The identifier of this vertex.
     */
    private final T _identifier;

    /**
     * The color is e.g. used for searching algorithms.
     */
    private Color _color;

    /**
     * Initializes a new vertex with the defined identifier.
     *
     * @param identifier The identifier of the new vertex.
     */
    public Vertex(T identifier) {
        _identifier = identifier;
    }

    /**
     * Sets the color of this vertex.
     *
     * @param c The color of this vertex.
     */
    public void setColor(Color c) {
        _color = c;
    }

    /**
     * Returns the color of this vertex.
     *
     * @return The color of this vertex.
     */
    public Color getColor() {
        return _color;
    }

    /**
     * Returns the identifier of this vertex.
     *
     * @return The identifier of this vertex.
     */
    public T getId() {
        return _identifier;
    }
}
