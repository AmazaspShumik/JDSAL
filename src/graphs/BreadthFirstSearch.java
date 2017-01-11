package graphs;

import java.util.*;


public class BreadthFirstSearch<T> implements SearchGraph<T>{

    private Set<T> marked;
    private int    count;


    public BreadthFirstSearch(Graph<T> G, T v){
        marked        = new HashSet<>();
        Queue<T> bfs  = new ArrayDeque<>();
        bfs.add(v); count++; marked.add(v);
        while(!bfs.isEmpty()){
            for(T s : G.adj(bfs.poll())){
                if(!marked.contains(s)){
                    marked.add(s);
                    count++;
                    bfs.add(s);
                }
            }
        }
    }


    public int count(){
        return count;
    }

    public boolean marked(T v){
        return marked.contains(v);
    }


}