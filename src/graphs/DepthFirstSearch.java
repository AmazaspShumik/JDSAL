package graphs;

import java.util.*;


public class DepthFirstSearch<T> implements SearchGraph<T>{

    private Set<T> marked;
    private int count;


    public DepthFirstSearch(Graph<T> G, T s){
        marked = new HashSet<>(G.V());
        dfs(G,s);
    }


    private void dfs(Graph<T> G, T s){
        marked.add(s); count++;
        for(T v: G.adj(s)) if(!marked.contains(v)) dfs(G,v);
    }


    public boolean marked(T v){
        return marked.contains(v);
    }

    public int count(){
        return count;
    }
}
