package Greedy;


import java.util.*;

public class KnapsackOptimizer {
    static class Item{
        int id;
        int weight;
        int profit;
        double ratio;

        public Item(int id, int weight, int profit) {
            this.id = id;
            this.weight = weight;
            this.profit = profit;
            this.ratio = (double) profit / weight;
        }
    }

    public static int solve01Knapsack(int capacity, int[] weights, int[] profits, int n){
        int[][] dp = new int[n + 1][capacity + 1];

for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if(weights[w-1] <= w){
                    dp[i][w] = Math.max(profits[i-1] + dp[i-1][w-weights[i-1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        int res = dp[n][capacity];
        int w = capacity;
        for(int i=n;i>0&&res>0;i--){
        if (res != dp[i - 1][w]) {
                        System.out.printf("Item %d -> Weight: %d kg, Profit: ₹%d\n", i, weights[i - 1], profits[i - 1]);
                        res -= profits[i - 1];
                        w -= weights[i - 1];
                    }
                }
                return dp[n][capacity];
    }
    
    public static double solveFractionalKnapsack(int capacity, Item[] items){
        Arrays.sort(items,(a,b)->Double.compare(b.ratio, a.ratio));
        double totalProfit = 0.0;
        int currentWeight = 0;
        for(Item item : items){
            if(currentWeight + item.weight <= capacity){
                currentWeight += item.weight;
                totalProfit += item.profit;
            } else {
                int remain = capacity - currentWeight;
                double fraction = (double) remain / item.weight;
                double partialProfit = item.profit * fraction;
                totalProfit += partialProfit;
                currentWeight += remain;
                break;
            }
        }
        return totalProfit;
    }
}
