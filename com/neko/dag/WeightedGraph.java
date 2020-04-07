package com.neko.dag;

import java.util.HashSet;

public class WeightedGraph extends DirectedGraph{
    HashSet<DirectCostNode> nodes = new HashSet<DirectCostNode>();

    public void addWeightedEdge (final DirectCostNode first, final DirectCostNode second, int cost) {
        if(this.nodes.contains(first) && this.nodes.contains(second))
        {
            first.edges.put(second,cost);
        }
    }

}
