package graph_interview;


import java.util.*;

public class Maze {




    public static boolean pathTo(boolean[][] matrix, int[] source, int[] exit){
        boolean[][] marked = new boolean[matrix.length][matrix[0].length];
        dfs(marked,source,exit);
        System.out.println(marked[0].length);
        if(marked[exit[0]][exit[1]]) return true;
        return false;
    }


    public static int[] pathMoves(boolean[][] matrix, int[] source, int[] exit){

    }



    public static void dfs(boolean[][] marked, int[] source, int[] exit){
        int i = source[0], j = source[1];
        marked[i][j] = true;
        if(i==exit[0] && j==exit[1])  return;

        if( i-1 >= 0 && !marked[i-1][j]){
            source[0] = i-1;
            dfs(marked,source,exit);
        }

        if( i+1 < marked.length && !marked[i+1][j]){
            source[0] = i+1;
            dfs(marked,source,exit);
        }


        if( j-1 >= 0  && !marked[i][j-1]){
            source[1] = j-1;
            dfs(marked,source,exit);
        }

        if( j+1 < marked[i].length && !marked[i][j+1]){
            source[1] = j+1;
            dfs(marked,source,exit);
        }
    }


    public static void main(String[] args){
        boolean[][] marked = {{false, true, true,  true, true,  true, false,   false, true, true},
                              {true,  true, false, true, true,  true,   true,  true, true,  true},
                              {false, true, false, true, true,  false,  false, true, false, false},
                              {true,  true, true, false, false, false, true,   true, false, true}};
        System.out.println(pathTo(marked,new int[]{3,0}, new int[]{0,9}));
    }
}
