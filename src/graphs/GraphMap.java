package graphs;

import java.util.*;

/**
 * Adjacency Map represenatation of graph
 */
public class GraphMap<T> implements Graph<T>{

    private Map<T,Set<T>> map;

    GraphMap(int V){
        map = new HashMap<>(V);
    }


    public int V(){
        return map.size();
    }


    public int E(){
        int cnt = 0;
        for(T v: map.keySet()) cnt+= map.get(v).size();
        return cnt/2;
    }


    public void addEdge(T v, T w){
        if(!map.containsKey(v) || !map.containsKey(w)) throw new NoSuchElementException(" Verices are not in Graph");
        map.get(v).add(w);
        map.get(w).add(v);
    }


    public void addVertex(T v){
        if(!map.containsKey(v)) map.put(v,new HashSet<>());
    }


    public Iterable<T> adj(T v){
        return map.get(v);
    }


    public Iterable<T> vertices() { return map.keySet(); }


    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Graph with "+((Integer)V()).toString()+" vertices and "+ ((Integer)E()).toString() + " edges");
        str.append("\n");
        for(T v: map.keySet()){
            str.append("   Vertex" + v.toString()+": ");
            for(T s: map.get(v)) str.append(s.toString()+", ");
            str.append("\n");
        }
        return str.toString();
    }
}
