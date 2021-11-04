package com.toDownload;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.*;

// Graph class
class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // define edges of the graph
        Scanner fileScan = new Scanner(new File("C:\\Users\\Lauren\\Desktop\\CampusRecycling2\\src\\com\\toDownload\\RecyclingData.txt"));
        int lineCount = 0;
        while (fileScan.hasNextLine()) {
            fileScan.nextLine();
            lineCount++;
        }
        Edge[] allEdges = new Edge[lineCount - 1];

        fileScan = new Scanner(new File("C:\\Users\\Lauren\\Desktop\\CampusRecycling2\\src\\com\\toDownload\\RecyclingData.txt"));
        fileScan.nextLine();
        int n = 0;
        while (fileScan.hasNextLine()) {
            String edgeLine = fileScan.nextLine();
            Scanner lineScan = new Scanner(edgeLine);
            lineScan.useDelimiter(",");
            String src = lineScan.next().replaceAll("\\s+", "");
            String dest = lineScan.next().replaceAll("\\s+", "");
            int weight = Integer.parseInt(lineScan.next().replaceAll("\\s+", ""));
            allEdges[n] = new Edge(src, dest, weight);
            n++;
        }

        List<Edge> edges = Arrays.asList(allEdges);

        // call graph class Constructor to construct a graph
        Graph graph = new Graph(edges);
        Scanner myObj = new Scanner(System.in);
        System.out.println("WELCOME TO LA TECH RECYCLING!\n---------------------------------\nTo view the adjacency list, type'1'\nTo do a breadth-first search, type'2'\n" +
                "To do a depth-first search, type '3'\nTo do a Dijkstra's algorithm search-aka shortest paths-, type'4'\nTo find the longest paths, type '5'\n" +
                "To run Prim-Jarnik's algorithm, Type '6'\nTo exit, type '7'\n ");
        String UserChoice = myObj.nextLine();
        switch(UserChoice){
            case "1":
                System.out.println(graph.adj_list.get(0).getList().get(0).toString());
                Graph.printGraph(graph);
                break;
            case "2":
                Scanner Choice2 = new Scanner(System.in);
                System.out.println("Please type a building to begin from! (See RecyclingData.txt to see how to type it)\n");
                String BFSChoice = Choice2.nextLine();
                Graph.BFS(BFSChoice, graph);
                break;
            case "3":
                Scanner Choice3 = new Scanner(System.in);
                System.out.println("Please type a building to begin from! (See RecyclingData.txt to see how to type it)\n");
                String DFSChoice = Choice3.nextLine();
                Graph.DFS2(DFSChoice, graph);
                break;
            case "4":
                Scanner Choice4 = new Scanner(System.in);
                System.out.println("Please type a building to begin from! (See RecyclingData.txt to see how to type it)\n");
                String DPQChoice = Choice4.nextLine();
                long startTimeD = System.currentTimeMillis();
                DPQ dpq = new DPQ(graph);
                dpq.dijkstra(graph, DPQChoice);
                System.out.println("The shortest path from node:");
                for (int i = 0; i < dpq.dist.length; i++) {
                    System.out.println(DPQChoice + " to " + graph.adj_list.get(i).getName() + " is " + dpq.dist[i]);
                    long endTimeD = System.currentTimeMillis();
                    System.out.println("That took " + (endTimeD - startTimeD) + " milliseconds");
                }
                break;
            case "5":
                Scanner Choice5 = new Scanner(System.in);
                System.out.println("Please type a building to begin from! (See RecyclingData.txt to see how to type it)\n");
                String LongestPathChoice = Choice5.nextLine();
                graph.longestPath(LongestPathChoice, graph);
                break;
            case "6":
                Scanner Choice6 = new Scanner(System.in);
                System.out.println("Please type a building to begin from! (See RecyclingData.txt to see how to type it)\n");
                String PrimsChoice = Choice6.nextLine();
                long startTimeP = System.currentTimeMillis();
                Graph.Prims(graph, PrimsChoice);
                long endTimeP = System.currentTimeMillis();
                System.out.println("That took " + (endTimeP - startTimeP) + " milliseconds");

                break;
            case "7":
                System.out.println("Goodbye!");
                break;
        }

    }

}


