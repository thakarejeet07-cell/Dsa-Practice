package Dp;

public class RodCutting {

    public static void main(String[] args) {

        int n = 8;

        // Selling prices of lengths 1 to 8
        int[] price = {0, 1, 5, 8, 9, 10, 17, 17, 20};

        // DP array to store maximum profit
        int[] dp = new int[n + 1];

        // Stores the first cut for each length
        int[] firstCut = new int[n + 1];

        // Calculate maximum profit
        for (int i = 1; i <= n; i++) {

            int maxProfit = 0;

            for (int j = 1; j <= i; j++) {

                int currentProfit = price[j] + dp[i - j];

                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                    firstCut[i] = j;
                }
            }

            dp[i] = maxProfit;
        }

        // Display maximum profit
        System.out.println("Maximum Profit: " + dp[n]);

        // Display cuts
        System.out.print("Cuts used: ");

        int length = n;

        while (length > 0) {
            System.out.print(firstCut[length] + " ");
            length = length - firstCut[length];
        }

        System.out.println();

        // Display DP table
        System.out.println("\nDP Table:");
        System.out.println("Length\tMaximum Profit\tFirst Cut");

        for (int i = 0; i <= n; i++) {
            System.out.println(i + "\t" + dp[i] + "\t\t" + firstCut[i]);
        }
    }
}
