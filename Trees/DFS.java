package Trees;

public class DFS {
    public static void dfs(boolean [] visited,int[][]graph,int v){
        visited[v] = true;
        System.out.print(v + " ");

        for(int i=0;i<6;i++){
        if(graph[v][i] == 1 && !visited[i]){
            dfs(visited,graph,i);
        }
    }

    }
    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 0, 0, 0},
            {1, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 1},
            {0, 1, 1, 0, 0, 1},
            {0, 0, 0, 1, 1, 0}
        };
        boolean[] visited = new boolean[6];
        dfs(visited,graph,0);
    }
    
}
