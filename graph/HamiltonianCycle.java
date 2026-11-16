package graph;
import java.util.Arrays;


public class HamiltonianCycle {

    static int V = 5; // Total number of cities (0, 1, 2, 3, 4)

    // Rule checker: Can we move to city 'v' at step 'pos'?
    static boolean isSafe(int v, int[][] graph, int[] path, int pos) {
        int previousCity = path[pos - 1];

        // Rule 1: There must be a road between previousCity and v
        if (graph[previousCity][v] == 0) {
            return false;
        }

        // Rule 2: City 'v' must NOT already be in our path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true; // Safe to visit!
    }

    // Backtracking function to build the round-trip step by step
    static boolean solve(int[][] graph, int[] path, int pos) {
        // Base Case: All 5 cities are placed in path[]
        if (pos == V) {
            int lastCity = path[V - 1];
            int startCity = path[0];

            // Check if there is a road from the last city back to 0
            return graph[lastCity][startCity] == 1;
        }

        // Try visiting cities 1, 2, 3, 4 one by one
        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v; // Step forward into city v

                // Try to finish the rest of the trip from here
                if (solve(graph, path, pos + 1)) {
                    return true;
                }

                // Dead end! Undo the move (Backtrack) and try the next city
                path[pos] = -1;
            }
        }

        return false; // No city worked at this position
    }

    // Helper to connect two cities with a two-way road
    static void addRoad(int[][] graph, int u, int v) {
        graph[u][v] = 1;
        graph[v][u] = 1;
    }

    public static void main(String[] args) {
        int[][] graph = new int[V][V];

        // Add all given roads
        addRoad(graph, 0, 1);
        addRoad(graph, 0, 3);
        addRoad(graph, 1, 2);
        addRoad(graph, 1, 3);
        addRoad(graph, 1, 4);
        addRoad(graph, 2, 3);
        addRoad(graph, 2, 4);
        addRoad(graph, 3, 4);

        int[] path = new int[V];
        Arrays.fill(path, -1); // -1 means "empty slot"
        path[0] = 0;           // Start at city 0

        if (solve(graph, path, 1)) {
            System.out.print("Hamiltonian Cycle found: ");
            for (int city : path) {
                System.out.print(city + " -> ");
            }
            System.out.println(path[0]); // Return to start (0)
        } else {
            System.out.println("No Hamiltonian Cycle exists.");
        }
    }
}