package com.toDownload;
//class to store edges of the weighted graph
class Edge {
    private String src, dest;
    private int weight;

    Edge(String src, String dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public String getSrc() {return src;}
    public String getDest() {return dest;}
    public int getWt() {return weight;}
}
