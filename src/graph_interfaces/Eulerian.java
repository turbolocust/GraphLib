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
package graph_interfaces;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public interface Eulerian<T> {

    /**
     * Determines whether this graph contains a Eulerian path or not
     *
     * @return True if this graph contains at least one Eulerian path
     */
    boolean isEulerian();

    /**
     * Determines whether this graph contains a Eulerian circuit or not
     *
     * @return True if this graph contains at least one Eulerian circuit
     */
    boolean isEulerianTrail();

    /**
     * Determines whether this graph contains Eulerian cycle or not
     *
     * @return True if this graph contains at least one Eulerian cycle
     */
    boolean isEulerianCycle();
}
