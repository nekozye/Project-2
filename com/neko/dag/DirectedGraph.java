package com.neko.dag;

import java.util.HashSet;
import java.util.LinkedList;

public class DirectedGraph {

    HashSet<DirectCostNode> nodes = new HashSet<DirectCostNode>();



    public void addNode (final String nodeVal) {
        DirectCostNode newNode = new DirectCostNode(nodeVal);
        nodes.add(newNode);
    }

    public void addNode (final DirectCostNode node){
        nodes.add(node);
    }

    public DirectCostNode searchNode (final String key){
        for(DirectCostNode nd : this.nodes)
        {
            if(nd.nodeVal.equals(key))
                return nd;
        }
        return null;
    }

    public LinkedList<DirectCostNode> getStarts (){
        LinkedList<DirectCostNode> investigate = new LinkedList<DirectCostNode>();
        LinkedList<DirectCostNode> hasSource = new LinkedList<DirectCostNode>();


        for(final DirectCostNode nd : this.nodes)
        {
            investigate.addLast(nd);
        }

        for(final DirectCostNode nd : investigate)
        {
            for(final DirectCostNode dest : nd.edges.keySet())
            {
                if(!hasSource.contains(dest))
                    hasSource.addLast(dest);
            }
        }

        investigate.removeAll(hasSource);

        return investigate;
    }


    public void addDirectedEdge (final DirectCostNode first, final DirectCostNode second) {
        if(this.nodes.contains(first) && this.nodes.contains(second))
        {
            first.edges.put(second,1);
        }
    }

    public void removeDirectedEdge (final DirectCostNode first, final DirectCostNode second) {
        if(this.nodes.contains(first) && this.nodes.contains(second))
        {
            first.edges.remove(first);
        }
    }

    public HashSet<DirectCostNode> getAllNodes(){
        return nodes;
    }
}
