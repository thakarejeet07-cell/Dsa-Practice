package graph;
import java.util.*;

public class Dijkstra {
    static class Edge {
        static int node;
        static int weight;
        Edge(int node,int weight){
            this.node = node;
            this.weight = weight;
        }    
    }
    static int[] ddijkstra(ArrayList<ArrayList<Edge>> graph,int src){
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        dist[src] = 0;
        pq.add(new Edge(src, 0));
        while(!pq.isEmpty()){
            Edge current = pq.poll();
            int u = current.node;
            int d = current.weight;

            if(d != dist[u]){
                continue;
            }

            for(Edge e : graph.get(u)){
                int v = e.node;
                int w = e.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new Edge(v, dist[v]));
                } 
            }
        }
        return dist;
    }

    static void addEdge(ArrayList<ArrayList<Edge>> graph, int u, int v, int w){
        graph.get(u).add(new Edge(v, w));
        graph.get(v).add(new Edge(u, w));
    }

    public static void main(String[] args) {
        int n = 5;
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 0, 1, 4);
        addEdge(graph, 0, 2, 8);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 1, 4, 6);
        addEdge(graph, 2, 3, 2);
        addEdge(graph, 3, 4, 10);

        int[] shortest = ddijkstra(graph, 0);
        System.out.println(Arrays.toString(shortest));
    }
    
}
