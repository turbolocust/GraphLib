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
package GraphComponents;

import java.awt.Color;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter used for identifiers
 */
public class Vertex<T> {

    /**
     * The identifier of this vertex
     */
    private final T _identifier;

    /**
     * The color is e.g. used for searching algorithms
     */
    private Color _color;

    /**
     * Initializes a new vertex with the defined identifier
     *
     * @param identifier The identifier of the new vertex
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
