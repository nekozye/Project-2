package com.neko;

public class Node implements Comparable<Node>{
    public String nodeVal;


    public Node(String nodeVal)
    {
        this.nodeVal = nodeVal;
    }

    @Override
    public int compareTo(Node o) {
        return nodeVal.compareTo(o.nodeVal);
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeVal=" + nodeVal+
                '}';
    }
}
