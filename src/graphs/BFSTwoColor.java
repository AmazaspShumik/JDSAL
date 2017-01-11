package graphs;


import java.util.*;

public class BFSTwoColor<T> implements TwoColor<T>{

    private Set<T>           marked;
    private Map<T,Boolean>   colorMap;
    private boolean          twoColor;


    public BFSTwoColor(Graph<T> G){
        marked   = new HashSet<>();
        colorMap = new HashMap<>();
        for(T u : G.vertices()){
            if(!marked.contains(u)) bfs(G,u);
        }
    }


    private void bfs(Graph<T> G, T u){
        Queue<T> q = new ArrayDeque<>();
        q.add(u); marked.add(u); colorMap.put(u,true);
        while(!q.isEmpty()){
            T v = q.poll();
            if(!marked.contains(v)){
                marked.add(v);
                q.add(v);
                colorMap.put(v,!colorMap.get(u));
            }else{
                if(colorMap.get(v)!=colorMap.get(u)) twoColor = false;
            }
        }
    }


    public boolean isBipartite(){
        return twoColor;
    }
}
