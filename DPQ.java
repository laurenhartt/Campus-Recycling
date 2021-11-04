package com.toDownload;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DPQ {
    public int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Graph.Node> pq;
    private int V; // Number of vertices
    List<List<Graph.Node>> adj;
    public DPQ(Graph graph) {

        V = graph.adj_list.size();
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Graph.Node>(V, new Graph.Node("", 0));
    }

    // Function for Dijkstra's Algorithm
    public void dijkstra(Graph graph, String src) {
        int index = 0;
        int c = 0;
        for (Graph.VertexNode vn : graph.adj_list) {
            if (vn.getName().equals(src)) {
                index = c;
            }
            c++;
        }
        List<List<Graph.Node>> adj = graph.adj_list.get(index).getList();

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Graph.Node(src, 0));

        // Distance to the source is 0
        dist[index] = 0;
        while (settled.size() != V) {

            // remove the minimum distance node
            // from the priority queue
            String name = pq.remove().getValue();
            int u = 0;
            int count = 0;
            for (Graph.VertexNode vn : graph.adj_list) {
                if (vn.getName().equals(name)) {
                    u = count;
                }
                count++;
            }
            // adding the node whose distance is
            // finalized
            settled.add(u);


            e_Neighbours(u, graph);
        }
    }

    // Function to process all the neighbours
    // of the passed node
    private void e_Neighbours(int u, Graph graph) {
        int edgeDistance = -1;
        int newDistance = -1;

        adj = graph.adj_list.get(u).getList();
        // All the neighbors of v
        for (int i = 0; i < adj.size(); i++) {
            Graph.Node v = (Graph.Node) adj.get(i);
            int c = 0;
            int index = 0;
            for (Graph.VertexNode vn : graph.adj_list) {
                if (vn.getName().equals(v.getValue())) {
                    index = c;
                }
                c++;
            }

            // If current node hasn't already been processed
            if (!settled.contains(index)) {
                edgeDistance = v.getWeight();
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[index])
                    dist[index] = newDistance;

                // Add the current node to the queue
                pq.add(new Graph.Node(v.getValue(), dist[index]));
            }
        }
    }
}
