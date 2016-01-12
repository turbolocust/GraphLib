/*
 * Copyright (c) Matthias Fussenegger
 */
package Interfaces;

import java.util.List;

/**
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter used for identifiers
 */
public interface Eulerian<T> {

    /**
     * Depth-first-search on Graph to find all paths
     *
     * @param root The identifier of the start vertex
     * @return A List with all paths containing the identifiers of vertices
     */
    List<String> findAllPaths(T root);

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
