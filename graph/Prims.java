package graph;

import java.util.PriorityQueue;

public class Prims {
    public static void main(String[] args) {
        int[][] graph = {
            {0, 2, 3, 0, 0},
            {2, 0, 1, 3, 7},
            {3, 1, 0, 4, 5},
            {0, 3, 4, 0, 6},
            {0, 7, 5, 6, 0}
        };
        int n = 5;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->  a[0] - b[0]);
        boolean[] visited = new boolean[n];
        pq.add(new int[]{0,0,-1});
        int total = 0;

        System.out.println("MST Edges:");
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int cost = curr[0];
            int u = curr[1];
            int parent = curr[2];
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            total += cost;
            // Print selected edge
            if (parent != -1) {
                System.out.println(
                    (char)('A' + parent) + " - " +
                    (char)('A' + u) + " : " + cost
                );
            }
            for (int v = 0; v < n; v++) {

                if (graph[u][v] != 0 && !visited[v]) {
                    pq.add(new int[]{graph[u][v], v, u});
                }
            }
        }
        System.out.println("Total Minimum Cost: " + total);        
    }
}