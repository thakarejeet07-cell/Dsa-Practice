package graph;

public class GraphColoring {
    static int V = 5;
    static int m = 3;
    static int [] color = new int [V];
    static int [] names = {'A', 'B', 'C', 'D', 'E'};

    static int[][] graph = {
        {0, 1, 1, 0, 0},   // A: B, C
        {1, 0, 1, 1, 0},   // B: A, C, D
        {1, 1, 0, 1, 1},   // C: A, B, D, E
        {0, 1, 1, 0, 1},   // D: B, C, E
        {0, 0, 1, 1, 0}    // E: C, D
    };

    public static boolean isSafe(int v,int c){
        for(int i=0;i<v;i++){
            if(graph[v][i] == 1 && color[i] == c){
                return false;
            }
        }
        return true;
    }

    public static boolean solve(int v){
        if(v == V){
            return true;
        }
        for (int c = 1; c <= m; c++) {
            if(isSafe(v, c)){
                color[v]=c;
                if(solve(v+1)){
                    return true;
                }
                color[v] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if(solve(0)){
            System.out.println("Graph can be colored with " + m + " colors.");
            for (int i = 0; i < V; i++) {
                System.out.println("Vertex " + names[i] + " -> Color " + color[i]);
            }
        } else {
            System.out.println("Graph cannot be colored with " + m + " colors.");
        }
    }
}
