package Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class NQueens {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter chessboard size (N): ");
        int n = scanner.nextInt();
        scanner.close();

        solveNQueens(n);
    }

    public static void solveNQueens(int n) {
        if (n <= 0) {
            System.out.println("Board size must be greater than 0.");
            return;
        }

        // Initialize empty board with '.'
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // Tracking arrays for O(1) conflict checks
        boolean[] cols = new boolean[n];
        boolean[] mainDiag = new boolean[2 * n - 1]; // Tracks '\' diagonals: row - col + (n - 1)
        boolean[] antiDiag = new boolean[2 * n - 1]; // Tracks '/' diagonals: row + col

        if (placeQueens(0, n, board, cols, mainDiag, antiDiag)) {
            printBoard(board);
        } else {
            System.out.println("\nNo valid arrangement exists for N = " + n);
        }
    }

    private static boolean placeQueens(int row, int n, char[][] board, 
                                      boolean[] cols, boolean[] mainDiag, boolean[] antiDiag) {
        // Base Case: All N queens are successfully placed
        if (row == n) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + (n - 1); // Main diagonal index (\)
            int d2 = row + col;           // Anti-diagonal index (/)

            // Safety check: ensure column and both diagonals are clear
            if (!cols[col] && !mainDiag[d1] && !antiDiag[d2]) {
                // Place Queen
                board[row][col] = 'Q';
                cols[col] = mainDiag[d1] = antiDiag[d2] = true;

                // Recurse to place queen in next row
                if (placeQueens(row + 1, n, board, cols, mainDiag, antiDiag)) {
                    return true;
                }

                // Backtrack: Remove Queen and reset tracking state
                board[row][col] = '.';
                cols[col] = mainDiag[d1] = antiDiag[d2] = false;
            }
        }

        return false; // Trigger backtracking step
    }

    private static void printBoard(char[][] board) {
        System.out.println("\nValid Solution Arrangement:");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}