package graph;

import java.util.Arrays;

public class KruskalAlgo {
    static int[] parent;

    // Find operation with Path Compression
    static int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    public static void main(String[] args) {
        int n = 5; // Total 5 vertices: A=0, B=1, C=2, D=3, E=4

        // Graph edges: {src, dest, weight}
        int[][] edges = {
            {0, 1, 2}, // A - B : 2
            {0, 2, 3}, // A - C : 3
            {1, 2, 1}, // B - C : 1
            {1, 3, 4}, // B - D : 4
            {2, 3, 5}, // C - D : 5
            {2, 4, 6}, // C - E : 6
            {3, 4, 2}, // D - E : 2
            {1, 4, 7}  // B - E : 7
        };

        // Step 1: Sort all edges by weight in ascending order
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        // Step 2: Initialize parent array (every node is its own root initially)
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int totalCost = 0;
        int edgeCount = 0;

        System.out.println("Selected MST Edges:");

        // Step 3: Pick the smallest edge and check if it forms a cycle
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            int rootU = find(u);
            int rootV = find(v);

            // If roots are different, joining u and v will not create a cycle
            if (rootU != rootV) {
                parent[rootU] = rootV; // Union operation
                totalCost += weight;
                edgeCount++;

                // Print selected edge in letter format (0 -> 'A', 1 -> 'B', etc.)
                System.out.println((char) ('A' + u) + " - " + (char) ('A' + v) + " : " + weight);

                // Stop early when V - 1 edges are included
                if (edgeCount == n - 1) {
                    break;
                }
            }
        }

        System.out.println("Total Minimum Cost: " + totalCost);
    }
}