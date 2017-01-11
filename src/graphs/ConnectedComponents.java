package graphs;

/**
 * Created by amazaspshaumyan on 1/10/17.
 */
public interface ConnectedComponents<T> {

    /**
     * Checks whether two vertices are connected or not
     *
     * @param v : Vertex of Graph
     * @param u : Vertex of Graph
     * @return true if vertices of Graph are connected, false otherwise
     */
    boolean connected(T u, T v);

    /**
     * Computes number of connected components
     *
     * @return number of components
     */
    int count();

    /**
     * Component identifier
     *
     * @return id of components
     */
    int id(T v);
}
