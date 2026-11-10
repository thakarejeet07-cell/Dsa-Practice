package Dp;

import java.util.*;

public class CoinChangeOptimizer {

    public static void minCoinsDP(int[] coins, int target) {
        int[] dp = new int[target + 1];
        int[] coinUsed = new int[target + 1];

        // Fill DP array with a large value representing infinity
        Arrays.fill(dp, target + 1);
        dp[0] = 0; // Base case: 0 amount needs 0 coins

        // Build DP table bottom-up
        for (int a = 1; a <= target; a++) {
            for (int coin : coins) {
                if (a - coin >= 0 && dp[a - coin] + 1 < dp[a]) {
                    dp[a] = dp[a - coin] + 1;
                    coinUsed[a] = coin; // Track coin used for traceback
                }
            }
        }

        // Task 3: Check if target amount is impossible
        if (dp[target] > target) {
            System.out.println("Target amount ₹" + target + " cannot be formed with the given coin denominations.");
            return;
        }

        // Task 1 & 2: Display min coins and selected quantities
        System.out.println("Minimum number of coins: " + dp[target]);

        Map<Integer, Integer> coinCounts = new HashMap<>();
        int curr = target;
        while (curr > 0) {
            int coin = coinUsed[curr];
            coinCounts.put(coin, coinCounts.getOrDefault(coin, 0) + 1);
            curr -= coin;
        }

        System.out.print("Coins selected: ");
        for (Map.Entry<Integer, Integer> entry : coinCounts.entrySet()) {
            System.out.print("Coin " + entry.getKey() + " (x" + entry.getValue() + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] coins = {1, 3, 4, 6};
        int target = 8;

        System.out.println("=== Dynamic Programming Approach ===");
        minCoinsDP(coins, target);
    }
}