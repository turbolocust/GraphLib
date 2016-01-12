/*
 * Copyright (c) Matthias Fussenegger
 */
package GraphComponents;

import java.awt.Color;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter used for identifiers
 */
public class Edge<T> {

    /**
     * Source and target node that this edge links
     */
    private T _source, _target; //the nodes that are linked by this edge

    /**
     * The weight of this edge
     */
    private int _weight;

    /**
     * The color is e.g. used for searching algorithms
     */
    private Color _color;

    /**
     * Initializes a new edge with given source/target
     *
     * @param vertex1 The source vertex
     * @param vertex2 The target vertex
     */
    public Edge(T vertex1, T vertex2) {
        _source = vertex1;
        _target = vertex2;
        _color = Color.WHITE;
    }

    /**
     * Initializes a new edge with given source/target and weight
     *
     * @param vertex1 The source vertex
     * @param vertex2 The target vertex
     * @param weight The weight of the edge
     */
    public Edge(T vertex1, T vertex2, int weight) {
        _weight = weight;
        _source = vertex1;
        _target = vertex2;
        _color = Color.WHITE;
    }

    /**
     * Sets the source vertex of this edge
     *
     * @param vertex The source vertex to be set
     * @return False if source is already set
     */
    public boolean setSource(T vertex) {
        if (_source != null) {
            _source = vertex;
            return true;
        }
        return false;
    }

    /**
     * Sets the target vertex of this edge
     *
     * @param vertex The target vertex to be set
     * @return False if target is already set
     */
    public boolean setTarget(T vertex) {
        if (_target != null) {
            _target = vertex;
            return true;
        }
        return false;
    }

    /**
     * Sets the color of this edge
     *
     * @param c The color of this edge
     */
    public void setColor(Color c) {
        _color = c;
    }

    /**
     * Returns the source vertex of this edge
     *
     * @return The identifier of the source vertex
     */
    public T getSource() {
        return _source;
    }

    /**
     * Returns the target vertex of this edge if it does not equal the specified
     * identifier. Otherwise the ID of the source vertex will be returned.
     *
     * @param id The identifier of the vertex to be excluded
     * @return The identifier of the target/source vertex
     */
    public T getTarget(T id) {
        if (_target.equals(id)) {
            return _source;
        } else {
            return _target;
        }
    }

    /**
     * Returns the target vertex of this edge
     *
     * @return The identifier of the target vertex
     */
    public T getTarget() {
        return _target;
    }

    /**
     * Returns the color of this edge
     *
     * @return The color of this edge
     */
    public Color getColor() {
        return _color;
    }

    /**
     * Returns the weight of this edge
     *
     * @return The weight of this edge
     *
     */
    public int getWeight() {
        return _weight;
    }
}
