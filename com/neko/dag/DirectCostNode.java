package com.neko.dag;

import java.util.HashMap;

public class DirectCostNode implements Comparable<DirectCostNode>{


    public String nodeVal;

    HashMap<DirectCostNode, Integer> edges;

    public DirectCostNode(String nodeVal)
    {
        this.nodeVal = nodeVal;
        this.edges = new HashMap<DirectCostNode, Integer>();
    }

    public void addDirectedEdge(final DirectCostNode destination, int cost)
    {
        this.edges.put(destination,new Integer(cost));
    }

    public int getCost(final DirectCostNode destination)
    {
        if(this.hasEdgeTo(destination))
        {
            return this.edges.get(destination).intValue();
        }
        else
        {
            throw new NullPointerException();
        }
    }

    public boolean hasEdgeTo(final DirectCostNode destination)
    {
        return edges.containsKey(destination);
    }

    @Override
    public int compareTo(DirectCostNode o) {
        return nodeVal.compareTo(o.nodeVal);
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeVal=" + nodeVal+
                '}';
    }
}
