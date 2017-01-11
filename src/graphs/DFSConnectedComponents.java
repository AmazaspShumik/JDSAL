package graphs;

import java.util.*;


public class DFSConnectedComponents<T> implements ConnectedComponents<T>{

    private Set<T>          marked;
    private int             count;
    private Map<T,Integer>  id;


    public DFSConnectedComponents(Graph<T> G){
        marked = new HashSet<>();
        id     = new HashMap<>();
        for(T v: G.vertices()) {
            if (!id.containsKey(v)) {
                dfs(G, v);
                count++;
            }
        }
    }


    private void dfs(Graph<T> G, T v){
        marked.add(v);
        id.put(v,count);
        for( T u : G.adj(v) ){
            if(!marked.contains(u)){
                dfs(G,u);
            }
        }
    }


    public boolean connected(T u, T v){
        return id.get(u)==id.get(v);
    }


    public int count(){
        return count;
    }


    public int id(T v){
        return id.get(v);
    }

}
