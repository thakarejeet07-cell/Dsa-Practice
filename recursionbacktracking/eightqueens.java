package recursionbacktracking;

public class eightqueens {
    static int count = 0;
    static int N = 8;
    static boolean[] columns = new boolean[N];
    static boolean[] diag1 = new boolean[2 * N - 1];
    static boolean[] diag2 = new boolean[2 * N - 1];
    static char[][] board = new char[N][N];

    public static void solve(int row){
        if(row == N){
            count++;
            return;
        }
        for(int col = 0;col<N;col++){
            int d1 = row - col + N - 1;
            int d2 = row + col;

            if(!columns[col] && !diag1[d1] && !diag2[d2]){
                board[row][col] = 'Q';

                columns[col] = true;
                diag1[d1] = true;
                diag2[d2] = true;
                solve(row+1);
                board[row][col] = '.';
                                                    

                columns[col] = false;
                diag1[d1] = false;
                diag2[d2] = false;

            }


        }

    }
    public static void main(String[] args) {
        for(int i = 0; i < N; i++){
    for(int j = 0; j < N; j++){
        board[i][j] = '.';
    }
}
        solve(0);
        System.out.println(count);
    }

    
}
