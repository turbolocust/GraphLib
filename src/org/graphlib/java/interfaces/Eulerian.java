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
package org.graphlib.java.interfaces;

/**
 * Any implementing class offers functionality to test graph for Eulerian
 * characteristics, e.g. if graph contains a Eulerian trail or cycle.
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public interface Eulerian<T> {

    /**
     * Determines whether this graph contains a Eulerian path or not.
     *
     * @return True if this graph contains at least one Eulerian path.
     */
    boolean isEulerian();

    /**
     * Determines whether this graph contains a Eulerian circuit or not.
     *
     * @return True if this graph contains at least one Eulerian circuit.
     */
    boolean isEulerianTrail();

    /**
     * Determines whether this graph contains Eulerian cycle or not.
     *
     * @return True if this graph contains at least one Eulerian cycle.
     */
    boolean isEulerianCycle();

}
