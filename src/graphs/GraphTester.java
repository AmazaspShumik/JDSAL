package graphs;

/**
 * Testing Graph algorithm on small , but tricky graph
 */
public class GraphTester {


    public static<T> void checkSearchGraph(Graph<T> G, T source, T dest){
        SearchGraph<T> bfs = new BreadthFirstSearch<>(G,source);
        SearchGraph<T> dfs = new DepthFirstSearch<>(G,source);

        System.out.println("BFS");
        System.out.println(bfs.count());
        System.out.println(bfs.marked(dest));

        System.out.println("DFS");
        System.out.println(dfs.count());
        System.out.println(bfs.marked(dest));
    }


    public static void main(String[] args){
        Graph<Integer> G = new GraphMap<>(12);
        for(int j=0; j<13; j++) G.addVertex(j);

        // =========================== Initialise Graph ========================================

        // edges for 0 element
        G.addEdge(0,1); G.addEdge(0,2); G.addEdge(0,5); G.addEdge(0,6);

        G.addEdge(3,4);

        // edges for 5 element
        G.addEdge(5,3); G.addEdge(5,4);

        // add edges for 6
        G.addEdge(6,4);

        // add edges for 9 && 11
        G.addEdge(9,10); G.addEdge(9,11); G.addEdge(9,12); G.addEdge(11,12);

        G.addEdge(7,8);

        // print to check correct initialization
        //System.out.println(G.toString());

        //System.out.println();


        // ========================== Check Search Interface ==================================

        //checkSearchGraph(G,0,4);

        // ========================== Check Paths Interface ===================================




        // =========================== Connected Components ==================================

        BFSConnectedComponents<Integer> dfscc = new BFSConnectedComponents<>(G);
        //System.out.println(dfscc.count());
        //System.out.println(dfscc.connected(0,6));
        //System.out.println(dfscc.id(0));
        //System.out.println(dfscc.id(7));
        //System.out.println(dfscc.id(11));



        // ========================== Graph Cycle ============================================

        BFSCycle<Integer> bfsc = new BFSCycle<>(G);
        System.out.println(bfsc.hasCycle());

        // ========================== Graph Colorablity ======================================

        TwoColor<Integer> bp = new BFSTwoColor<>(G);
        System.out.println(bp.isBipartite());










    }


}
