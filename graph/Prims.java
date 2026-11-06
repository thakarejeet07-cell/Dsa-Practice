package graph;

public class Prims {
    public static void main(String[] args) {

        // Adjacency matrix (0 means no connection)
        int[][] graph = {
            {0, 2, 3, 0, 0},
            {2, 0, 1, 3, 7},
            {3, 1, 0, 4, 5},
            {0, 3, 4, 0, 6},
            {0, 7, 5, 6, 0}
        };

        int n = 5;

        // Arrays used in Prim's algorithm
        int[] key = new int[n];       // Stores minimum cost
        boolean[] visited = new boolean[n]; // Tracks visited vertices
        int[] parent = new int[n];    // Stores parent vertex

        // Initially set all costs to infinity
        for (int i = 0; i < n; i++) {
            key[i] = 9999;
        }

        // Start from vertex A (index 0)
        key[0] = 0;
        parent[0] = -1;

        // Repeat for all 5 vertices
        for (int count = 0; count < n; count++) {

            int min = 9999;
            int u = -1;

            // Find the unvisited vertex with minimum cost
            for (int i = 0; i < n; i++) {
                if (!visited[i] && key[i] < min) {
                    min = key[i];
                    u = i;
                }
            }

            // Mark selected vertex as visited
            visited[u] = true;

            // Check all neighbours of selected vertex
            for (int v = 0; v < n; v++) {

                // Update cost if a cheaper connection is found
                if (graph[u][v] != 0 && !visited[v]
                        && graph[u][v] < key[v]) {

                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }


        
        // Print MST edges and calculate total cost
        int total = 0;

        System.out.println("MST Edges:");

        for (int i = 1; i < n; i++) {

            System.out.println(
                (char)('A' + parent[i]) + " - " +
                (char)('A' + i) + " : " + key[i]
            );

            total += key[i];
        }

        System.out.println("Total Minimum Cost: " + total);
    }
}