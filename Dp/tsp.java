package Dp;

import java.util.Arrays;

public class tsp {

    static final int N = 4; // 0: Depot, 1..3: Clinics
    static final int ALL_DONE = (1 << N) - 1; // 1111 in binary (15)
    static final int INF = 1_000_000_000;

    // Directed travel energy cost matrix: cost[from][to]
    static int[][] cost = {
        { 0, 10, 15, 20 }, // From Depot 0
        { 5,  0,  9, 10 }, // From Clinic 1
        { 6, 13,  0, 12 }, // From Clinic 2
        { 8,  8,  9,  0 }  // From Clinic 3
    };

    static int[][] dp = new int[1 << N][N];         // Memoization table
    static int[][] nextClinic = new int[1 << N][N]; // Tracks best next clinic for tour

    // Top-Down Dynamic Programming with Bitmasking
    static int solve(int mask, int pos) {
        // Base Case: All 4 locations visited -> return to Depot 0
        if (mask == ALL_DONE) {
            return dp[mask][pos] = cost[pos][0];
        }

        // Return saved answer if already computed
        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int minCost = INF;
        int bestNext = -1;

        for (int i = 0; i < N; i++) {
            int spotlight = (1 << i); // Isolate bit i

            // Check if location i is still unvisited (bit i is 0)
            if ((mask & spotlight) == 0) {
                int newMask = mask | spotlight; // Mark location i as visited (flip bit i to 1)
                int currentCost = cost[pos][i] + solve(newMask, i);

                if (currentCost < minCost) {
                    minCost = currentCost;
                    bestNext = i;
                }
            }
        }

        nextClinic[mask][pos] = bestNext;
        return dp[mask][pos] = minCost;
    }

    // Reconstruct and print the optimal tour sequence
    static void printOptimalTour() {
        int mask = 1; // 0001: start with Depot 0 visited
        int pos = 0;  // Start at Depot 0

        System.out.print("Optimal Tour Sequence: 0");
        while (mask != ALL_DONE) {
            int next = nextClinic[mask][pos];
            System.out.print(" -> " + next);
            mask = mask | (1 << next);
            pos = next;
        }
        System.out.println(" -> 0");
    }

    // Display the DP state / memoization table
    static void printDpTable() {
        System.out.println("\nDP Memoization Table (dp[mask][pos]):");
        System.out.println("Mask (Dec | Bin)  | Pos 0 | Pos 1 | Pos 2 | Pos 3");
        System.out.println("------------------+-------+-------+-------+-------");

        for (int mask = 0; mask < (1 << N); mask++) {
            String bin = String.format("%4s", Integer.toBinaryString(mask)).replace(' ', '0');
            System.out.printf("   %2d     | %s  |", mask, bin);

            for (int pos = 0; pos < N; pos++) {
                if (dp[mask][pos] == -1) {
                    System.out.print("   -   |");
                } else {
                    System.out.printf("  %3d  |", dp[mask][pos]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        for (int[] row : dp) Arrays.fill(row, -1);
        for (int[] row : nextClinic) Arrays.fill(row, -1);

        // Start at Depot 0 (pos = 0) with only Depot 0 visited (mask = 0001 = 1)
        int minEnergy = solve(1, 0);

        System.out.println("Minimum Energy Travel Cost: " + minEnergy);
        printOptimalTour();
        printDpTable();
    }
}
