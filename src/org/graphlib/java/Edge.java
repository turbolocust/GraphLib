/*
 * The MIT License
 *
 * Copyright 2020 Matthias Fussenegger
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

import java.util.HashMap;
import java.util.Map;

/**
 * An edge links two vertices in one or in both directions.
 *
 * @author Matthias Fussenegger
 * @param <T> type of the identifier.
 * @param <V> type of the weight.
 */
public class Edge<T, V> {

    /**
     * Source and target node that this edge links.
     */
    private T _source, _target;

    /**
     * The weight of this edge.
     */
    private V _weight;

    /**
     * Custom properties (or additional weights) of this edge.
     */
    private final Map<String, Object> _properties;

    /**
     * Initializes a new edge with given source/target.
     *
     * @param vertex1 the source vertex.
     * @param vertex2 the target vertex.
     */
    public Edge(final T vertex1, final T vertex2) {
        _source = vertex1;
        _target = vertex2;
        _properties = new HashMap<String, Object>();
    }

    /**
     * Initializes a new edge with given source/target and weight.
     *
     * @param vertex1 the source vertex.
     * @param vertex2 the target vertex.
     * @param weight the weight of the edge.
     */
    public Edge(final T vertex1, final T vertex2, V weight) {
        _weight = weight;
        _source = vertex1;
        _target = vertex2;
        _properties = new HashMap<String, Object>();
    }

    /**
     * Sets the source vertex of this edge.
     *
     * @param vertex the source vertex to be set.
     * @return false if source is already set.
     */
    public boolean setSource(final T vertex) {
        if (_source != null) {
            _source = vertex;
            return true;
        }
        return false;
    }

    /**
     * Sets the target vertex of this edge.
     *
     * @param vertex the target vertex to be set.
     * @return false if target is already set.
     */
    public boolean setTarget(final T vertex) {
        if (_target != null) {
            _target = vertex;
            return true;
        }
        return false;
    }

    /**
     * Sets a property (or additional weight) of this edge.
     *
     * @param key the key (or name) of the property.
     * @param value the value to be associated with the key.
     */
    public void setProperty(String key, Object value) {
        _properties.put(key, value);
    }

    /**
     * Returns the source vertex of this edge.
     *
     * @return the identifier of the source vertex.
     */
    public T getSource() {
        return _source;
    }

    /**
     * Returns the target vertex of this edge if it does not equal the specified
     * identifier. Otherwise the ID of the source vertex will be returned.
     *
     * @param id the identifier of the vertex to be excluded.
     * @return the identifier of the target/source vertex.
     */
    public T getTarget(T id) {
        if (_target.equals(id)) {
            return _source;
        } else {
            return _target;
        }
    }

    /**
     * Returns the target vertex of this edge.
     *
     * @return the identifier of the target vertex.
     */
    public T getTarget() {
        return _target;
    }

    /**
     * Returns a property (or additional weight) of this edge.
     *
     * @param key the key (or name) of the property.
     * @return the value that is associated with the key.
     */
    public Object getProperty(String key) {
        return _properties.get(key);
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge.
     */
    public V getWeight() {
        return _weight;
    }
}
