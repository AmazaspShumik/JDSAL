package graphs;

/**
 * Created by amazaspshaumyan on 1/9/17.
 */
public interface Paths<T> {

    /**
     * Checks existence of path from source to given vertex
     *
     * @param v : vertex for which we check existence of path
     * @return true if path exists, false otherwise
     */
    boolean hasPathTo(T v);

    /**
     * Returns Iterable Object which contains vrtices on path from source to given vertex
     * @param v :
     * @return Iterable Object with vertics on path
     */
    Iterable<T> pathTo(T v);

    /**
     * Computes path from source to vertex v.
     * @param v : vertex v
     * @return  : length of path from source to vertex v
     */
    int pathLength(T v);
}
