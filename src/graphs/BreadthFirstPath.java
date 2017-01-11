package graphs;

import java.util.*;


public class BreadthFirstPath<T> implements Paths<T>{

    private Set<T>           marked;
    private Map<T,T>         pathMap;
    private T                source;
    private Map<T,Integer>   dist;


    public BreadthFirstPath(Graph<T> G, T v){
        marked   = new HashSet<>();
        pathMap  = new HashMap<>();
        dist     = new HashMap<>();
        source   = v;
        Queue<T> q = new ArrayDeque<>();
        Queue<Integer> l = new ArrayDeque<>();   // levels queue
        q.add(source); pathMap.put(source,source);
        l.add(0);
        while(!q.isEmpty()){
            T cur = q.poll();
            Integer level = l.poll()+1;
            for(T s: G.adj(cur)){
                if(!marked.contains(s)){
                    marked.add(s);
                    pathMap.put(s,cur);
                    dist.put(s,level);
                    q.add(s);
                    l.add(level);
                }
            }
        }
    }


    public boolean hasPathTo(T v){
        return marked.contains(v);
    }


    public Iterable<T> pathTo(T v){
        Stack<T> stack = new Stack<>();
        for(T u = v; !u.equals(source); u = pathMap.get(u)) stack.push(u);
        stack.push(source);
        return stack;
    }


    public int pathLength(T v){
        int cnt = 1;  // include source
        for(T u = v; !u.equals(source); u = pathMap.get(u)) cnt++;
        return cnt;
    }


    public int distTo( T v){
        return dist.get(v);
    }

}
