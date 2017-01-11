package graphs;

import java.util.*;

public class DepthFirstPath<T> implements Paths<T>{

    private Set<T>    marked;
    private Map<T,T>  pathMap;
    private T         source;


    public DepthFirstPath(Graph<T> G, T v){
        marked  = new HashSet<>(G.V());
        pathMap = new HashMap<>(G.V());
        source  = v;
        pathMap.put(source,source);
        dfs(G,v);
    }


    private void dfs(Graph<T> G, T s){
        marked.add(s);
        for(T v: G.adj(s)){
            if(!marked.contains(v)){
                pathMap.put(v,s);
                dfs(G,v);
            }
        }
    }


    public boolean hasPathTo(T s){
        return marked.contains(s);
    }


    public Iterable<T> pathTo(T v){
        Stack<T> stack = new Stack<>();
        for(T u = v; !u.equals(source); u=pathMap.get(u)) stack.push(u);
        stack.push(source);
        return stack;
    }


    public int pathLength(T v){
        int cnt = 1;
        for(T u = v; !u.equals(source); u=pathMap.get(u)) cnt++;
        return cnt;
    }

}
