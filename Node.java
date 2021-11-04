/**package com.toDownload;

import java.util.*;

public class Node {

    public String data; // data element
    public boolean visited=false; // flag to track the already visited node
    public List<Node> adjacentNodes = new LinkedList<Node>(); // adjacency list
    //      public List adjacentNodes = new LinkedList(); // adjacency list
    public Node rootNode;

    public Node(String data){
        this.data = data;
    }

    public void addAdjacentNode(final Node node){
        adjacentNodes.add(node);
        node.adjacentNodes.add(this);
//          adjacentNodes.add(rootNode);
//          node.adjacentNodes.add(this)
    }

}*/
