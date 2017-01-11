package graphs;


public interface Graph<T> {

    /**
     * Computes number of vertices in Graph
     * @return number of vertices
     */
    int V();

    /**
     * Computes number of edges in Graph
     * @return total number of edges in graph
     */
    int E();

    /**
     * Add edge v-w to graph
     * @param v vertex that should be connected with another one by new edge
     * @param w vertex that should be connected with another one by new edge
     */
    void addEdge(T v, T w);

    /**
     * Add vertex v
     * @param v adds vertex v to Graph.
     */
    void addVertex(T v);

    /**
     * Returns iterator for vertices adjacent to v
     * @return Iterator<T> for vertices adjacent to v
     */
    Iterable<T> adj(T v);


    /**
     * Returns Iterable for vertices of Graph
     * @return Iterable object
     */
    Iterable<T> vertices();


    /**
     * String representaton of Graph
     */
    String toString();

}
