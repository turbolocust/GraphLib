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

import java.awt.Color;
import java.util.Objects;

/**
 * A vertex is a node of the graph and can be linked with other ones by edges.
 *
 * @author Matthias Fussenegger
 * @param <T> type of the identifier.
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
     * @param id the identifier of the new vertex.
     */
    public Vertex(final T id) {
        _identifier = id;
    }

    /**
     * Sets the color of this vertex.
     *
     * @param c the color of this vertex.
     */
    public void setColor(Color c) {
        _color = c;
    }

    /**
     * Returns the color of this vertex.
     *
     * @return the color of this vertex.
     */
    public Color getColor() {
        return _color;
    }

    /**
     * Returns the identifier of this vertex.
     *
     * @return the identifier of this vertex.
     */
    public T getId() {
        return _identifier;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(_identifier);
        hash = 89 * hash + Objects.hashCode(_color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex<?> other = (Vertex<?>) obj;
        if (!Objects.equals(_identifier, other._identifier)) {
            return false;
        }
        return Objects.equals(_color, other._color);
    }

    @Override
    public String toString() {
        return _identifier.toString();
    }
}
