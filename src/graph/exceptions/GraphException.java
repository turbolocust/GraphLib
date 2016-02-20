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
package graph.exceptions;

/**
 * Class to handle generic errors that can occur while using graph
 *
 * @author Matthias Fussenegger
 */
public class GraphException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Delegates exception to its super class {@link Exception}
     */
    public GraphException() {
        super();
    }

    /**
     * Delegates error message to its super class {@link Exception}
     *
     * @param errorMessage The specified error message
     */
    public GraphException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Delegates exception cause to its super class {@link Exception}
     *
     * @param cause The cause of this exception
     */
    public GraphException(Throwable cause) {
        super(cause);
    }

    /**
     * Delegates error message and cause to its super class {@link Exception}
     *
     * @param errorMessage The specified error message
     * @param cause The cause of this exception
     */
    public GraphException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
