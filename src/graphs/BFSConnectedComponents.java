package graphs;


import java.util.*;

public class BFSConnectedComponents<T> implements ConnectedComponents<T>{

    private Set<T>          marked;
    private Map<T,Integer>  id;
    private int             count;


    public BFSConnectedComponents(Graph<T> G){
        marked = new HashSet<>();
        id     = new HashMap<>();
        for(T v: G.vertices()){
            if(!id.containsKey(v)){
                bfs(G,v);
                count++;
            }
        }
    }


    private void bfs(Graph<T> G, T v){
        Queue<T> q = new ArrayDeque<>();
        q.add(v); marked.add(v);
        while(!q.isEmpty()){
            T u = q.poll();
            for(T s: G.adj(u)){
                if(!marked.contains(s)){
                    marked.add(s);
                    id.put(s,count);
                    q.add(s);
                }
            }
        }
    }


    public boolean connected(T u , T v){
        return id.get(u)==id.get(v);
    }


    public int count(){
        return count;
    }


    public int id(T s){
        return id.get(s);
    }
}
