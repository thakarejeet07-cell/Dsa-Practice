package Trees;

import java.util.*;

public class BFS{
    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 0, 0, 0},
            {1, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 1},
            {0, 1, 1, 0, 0, 1},
            {0, 0, 0, 1, 1, 0}
        };

        boolean [] visited = new boolean[6];
        Queue<Integer> q = new LinkedList<>();
        visited[0] = true;
        q.add(0);
        while(!q.isEmpty()){
            int v = q.poll();
            System.out.println(v + " ");
            for(int i=0;i<6;i++){
                if(graph[v][i] == 1 && !visited[i]){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
}

