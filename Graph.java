package com.toDownload;

import java.util.*;

class Graph {
    // node of adjacency list
    static class Node implements Comparator<Node> {
        private String value;
        private int weight;

        Node(String s, int wt) {
            value = s;
            weight = wt;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if (node1.weight < node2.weight)
                return -1;
            if (node1.weight > node2.weight)
                return 1;
            return 0;
        }

        public String getValue() {
            return value;
        }


        public int getWeight() {
            return weight;
        }

        public void flipWeight() {
            weight = (weight * -1);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;
            return value.equals(node.value);
        }

        @Override
        public String toString() {
            return "Dest: " + this.value + " Wt: " + this.weight;
        }
    }

    class VertexNode {
        private String name;
        private List<List<Node>> myEdges;

        VertexNode(String n) {

            name = n;
            myEdges = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List getList() {
            return myEdges;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            VertexNode vertexnode = (VertexNode) o;
            return name.equals(vertexnode.name);
        }
    }

// define adjacency list

    List<VertexNode> adj_list = new ArrayList<>();

    //Graph Constructor
    public Graph(List<Edge> edges) {
        // adjacency list memory allocation
        for (Edge e1 : edges) {
            boolean exists = false;
            for (int y = 0; y < adj_list.size(); y++) {
                if (e1.getSrc().equals(adj_list.get(y).name)) {
                    exists = true;
                }
            }
            if (exists == false) {
                adj_list.add(new VertexNode(e1.getSrc()));
            }
            System.out.println();
        }

        // add edges to the graph
        for (Edge e : edges) {
            // allocate new node in adjacency List from src to dest
            int c = 0;
            boolean flag = false;
            while (c < edges.size() && flag == false) {
                if (e.getSrc().equals(adj_list.get(c).name)) {
                    flag = true;
                    adj_list.get(c).getList().add(new Node(e.getDest(), e.getWt()));
                    c = 0;
                } else {
                    c++;
                }
            }
        }
    }

    // print adjacency list for the graph
    public static void printGraph(Graph graph) {

        System.out.println("The contents of the graph:");

        for (int src_vertex = 0; src_vertex < graph.adj_list.size(); src_vertex++) {
            for (int c = 0; c < graph.adj_list.get(src_vertex).getList().size(); c++) {
                Node n = (Node) graph.adj_list.get(src_vertex).getList().get(c);
                System.out.println("Vertex: " + graph.adj_list.get(src_vertex).name + "Destination: " + n.value + "Weight: " + n.weight);
            }
            System.out.println();
        }
    }

    //recursive part of dfs.
    public static void DFS2Util(int s, ArrayList<Boolean> visitedDFS, Graph graph) {
        visitedDFS.set(s, true);
        System.out.println(s + " " + graph.adj_list.get(s).getName());

        // Recur for all the vertices adjacent to this
        // vertex
        for (int i = 0; i < graph.adj_list.get(s).getList().size(); i++) {

            Node n = (Node) graph.adj_list.get(s).getList().get(i);
            int index = 0;

            for (int q = 0; q < graph.adj_list.size(); q++) {
                if (n.getValue().equals(graph.adj_list.get(q).name)) {
                    index = q;

                }
            }
            if (index > -1 && !visitedDFS.get(index)) {
                DFS2Util(index, visitedDFS, graph);
            }
        }
    }
    public static void DFS2(String VertexNode, Graph graph) {
        System.out.println("Starting DFS: \n---------------------");
        ArrayList<Boolean> visited = new ArrayList<Boolean>();
        int index = 0;

        for (int i = 0; i < graph.adj_list.size(); i++) {
            if (VertexNode.equals(graph.adj_list.get(i).name)) {
                index = i;

            }
        }
        //Marking each node as unvisited
        for (int i = 0; i < graph.adj_list.size(); i++) {
            visited.add(i, false);

        }
        DFS2Util(index, visited, graph);
    }

    //bfs
    public static void BFS(String VertexNode, Graph graph) {
        System.out.println("Starting BFS:\n------------------");
        ArrayList<Boolean> visited = new ArrayList<Boolean>();
        int index = 0;


        //Marking each node as unvisited
        for (int i = 0; i < graph.adj_list.size(); i++) {
            visited.add(i, false);

        }
        for (int i = 0; i < graph.adj_list.size(); i++) {
            if (VertexNode.equals(graph.adj_list.get(i).name)) {
                index = i;
            }
        }
        Queue<Integer> queue = new LinkedList<>();

        visited.set(index, true);
        queue.add(index);
        System.out.println(index + " " + VertexNode);
        while (!queue.isEmpty()) {
            index = queue.remove();
            for (int k = 0; k < graph.adj_list.get(index).getList().size(); k++) {
                Node n = (Node) graph.adj_list.get(index).getList().get(k);
                for (int l = 0; l < graph.adj_list.size(); l++) {
                    if (n.value.equals(graph.adj_list.get(l).name) && visited.get(l) == false) {
                        queue.add(l);
                        visited.set(l, true);
                        System.out.println(l + " " + n.value);
                    }
                }

            }
        }
    }

    public static int Prims(Graph g, String VertexNode){
        System.out.println("Starting Prims:");
        ArrayList<Boolean> visited=new ArrayList<Boolean>();
        int index = 0;
        int totalCost = 0;
        ArrayList<Integer> cost=new ArrayList<Integer>();

        for(int i=0;i<g.adj_list.size();i++)
        {
            visited.add(i, false);
        }
        for(int i=0;i<g.adj_list.size();i++)
        {
            cost.add(i, Integer.MAX_VALUE);
        }
        for(int i=0;i<g.adj_list.size();i++){
            if(VertexNode.equals(g.adj_list.get(i).name)){
                index = i;

            }
        }

        Queue<Integer> queue = new LinkedList<>();
        cost.set(index, 0);
        visited.set(index, true);
        queue.add(index);
        System.out.println(index + " " + VertexNode);
        while(!queue.isEmpty()) {
            index = queue.remove();
            int lowcost = 9999;
            for(int i = 0; i < g.adj_list.get(index).getList().size(); i++){
                Node n = (Node) g.adj_list.get(index).getList().get(i);
                if(cost.get(findIndex(n.value, g)) > n.getWeight() && visited.get(findIndex(n.value, g)) == false){
                    cost.set(findIndex(n.value, g), n.getWeight());
                }
            }
            for(int i = 0; i < cost.size(); i++){
                if(cost.get(i) != 0 && lowcost > cost.get(i) && visited.get(i) == false){
                    lowcost = cost.get(i);
                }
            }
            boolean visit = false;
            while(!visit) {

                for (int i = 0; i < g.adj_list.get(index).getList().size(); i++) {
                    Node n = (Node) g.adj_list.get(index).getList().get(i);
                    if (lowcost == n.weight && visited.get(findIndex(n.value, g)) == false) {
                        queue.add(findIndex(n.value, g));
                        System.out.println("added " + n.value + " to queue");
                        visited.set(findIndex(n.value, g), true);
                        System.out.println("added " + lowcost + "to totalcost");
                        totalCost += lowcost;
                        visit = true;
                    }
                }
                if(!visit){
                    for(int i = 0; i < cost.size(); i++){
                        if(cost.get(i) == lowcost && !visited.get(i)){
                            queue.add(i);
                            System.out.println("added " + g.adj_list.get(i).getName() + " to queue");
                            visited.set(i, true);
                            System.out.println("added " + lowcost + " to totalcost");
                            totalCost += lowcost;
                            visit = true;
                            break;
                        }
                    }
                }
                break;
            }
        }
        System.out.println(totalCost);
        return totalCost;
    }
    public static int findIndex(String buildingName, Graph graph){
        int index = 0;
        for(int i=0;i<graph.adj_list.size();i++){
            if(buildingName.equals(graph.adj_list.get(i).name)){
                index = i;
            }
        }
        return index;
    }

    void topologicalSortUtil(int v, boolean visited[], Graph graph,
                             Stack<Integer> stack) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < graph.adj_list.get(v).getList().size(); i++) {

            if (!visited[i])
                topologicalSortUtil(i, visited, graph, stack);
        }

        // Push current vertex to stack which stores topological
        // sort
        stack.push(v);
    }
    // The function to find longest distances from a given vertex.
    // It uses recursive topologicalSortUtil() to get topological
    // sorting.
    void longestPath(String name, Graph graph)
    {
        System.out.println("Longest Path Started");
        int s= 0;
        for(int h = 0; h<graph.adj_list.size(); h++){
            if(name.equals(graph.adj_list.get(h).name)) {
                s=h;
            }
        }
        int V = graph.adj_list.size();
        Stack<Integer> stack = new Stack<Integer>();
        int dist[] = new int[V];

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Call the recursive helper function to store Topological
        // Sort starting from all vertices one by one
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, graph, stack);

        // Initialize distances to all vertices as 0 and
        // distance to source as 0
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MIN_VALUE;
            for (int n = 0; n < graph.adj_list.get(s).getList().size(); n++) {
                Node newnode = (Node) graph.adj_list.get(s).getList().get(n);
                if (graph.adj_list.get(i).name.equals(newnode.value)) {
                    dist[i] = newnode.weight;
                }

            }
        }

        dist[s] = 0;

        // Process vertices in topological order
        while (stack.isEmpty() == false)
        {

            // Get the next vertex from topological order
            int u = stack.peek();
            stack.pop();

            // Update distances of all adjacent vertices ;
            if (dist[u] != Integer.MIN_VALUE)
            {
                for (int i = 0; i<graph.adj_list.get(u).getList().size(); i++)
                {
                    Node node = (Node) graph.adj_list.get(u).getList().get(i);
                    if (dist[i] < dist[u] + node.getWeight())
                        dist[i] = dist[u] + node.getWeight();
                }
            }
        }

        // Print the calculated longest distances
        for (int i = 0; i < V; i++)
            if(dist[i] == Integer.MIN_VALUE)
                System.out.println(graph.adj_list.get(i).name + " INF " + dist[i]);
            else
                System.out.println(graph.adj_list.get(i).name + " " + dist[i] + " ");
    }
}



