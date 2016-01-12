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
public class Vertex<T> {

    /**
     * The identifier of this Vertex
     */
    private final T _identifier;

    /**
     * The color is e.g. used for searching algorithms
     */
    private Color _color;

    /**
     * Initializes a new Vertex with the defined identifier
     *
     * @param identifier The identifier of the new Vertex
     */
    public Vertex(T identifier) {
        _identifier = identifier;
    }

    /**
     * Sets the color of this vertex
     *
     * @param c The color of this vertex
     */
    public void setColor(Color c) {
        _color = c;
    }

    /**
     * Returns the color of this vertex
     *
     * @return The color of this vertex
     */
    public Color getColor() {
        return _color;
    }

    /**
     * Returns the identifier of this vertex
     *
     * @return The identifier of this vertex
     */
    public T getId() {
        return _identifier;
    }
}
