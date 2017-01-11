package graphs;


public interface SearchGraph<T> {

    /**
     * Checks whether vertex v was connected to s
     * @return true if vertex v is marked, false otherwise
     */
    boolean marked( T v);

    /**
     * Number of vertices connected to s
     * @return number of vertices connected to s
     */
    int count();

}
