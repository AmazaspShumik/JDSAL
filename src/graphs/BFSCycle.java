package graphs;

import java.util.*;


public class BFSCycle<T>  {

    private Set<T>  marked;
    private boolean hasCycle;

    public BFSCycle(Graph<T> G){
        marked = new HashSet<>();
        // there can be separate connected components;
        for(T u: G.vertices()){
            if(!marked.contains(u)) bfs(G,u);
        }
    }


    private void bfs(Graph<T> G, T u){
        Queue<T> q = new ArrayDeque<>();
        marked.add(u); q.add(u);
        while(!q.isEmpty()){
            T v = q.poll();
            for(T s: G.adj(v)){
                if(!marked.contains(s)){
                    marked.add(s);
                    q.add(s);
                }else{
                    hasCycle = true;
                }
            }
        }
    }


    public boolean hasCycle(){
        return hasCycle;
    }
}
