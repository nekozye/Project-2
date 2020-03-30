package com.neko;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Graph {

    LinkedList<Node> nodes;
    TreeMap<Node,LinkedList<Node>> edges;

    public Graph()
    {
        this.nodes = new LinkedList<Node>();
        this.edges = new TreeMap<Node,LinkedList<Node>>();
    }

    //3a, i~iv

    void addNode(final String nodeVal)
    {
        Node newnode = new Node(nodeVal);
        nodes.add(newnode);
        edges.put(newnode,new LinkedList<Node>());
    }

    void addUndirectedEdge(final Node first, final Node second)
    {

        if(nodes.contains(first) && nodes.contains(second))
        {
            LinkedList<Node> adjlist1 = edges.get(first);
            LinkedList<Node> adjlist2 = edges.get(second);

            if(adjlist1.contains(second) || adjlist2.contains(first))
                return;

            adjlist1.add(second);
            adjlist2.add(first);

            edges.put(first,adjlist1);
            edges.put(second,adjlist2);
        }
    }

    void removeUndirectedEdge(final Node first, final Node second)
    {
        if(nodes.contains(first) && nodes.contains(second))
        {
            LinkedList<Node> adjlist1 = edges.get(first);
            LinkedList<Node> adjlist2 = edges.get(second);

            if(!adjlist1.contains(second) || !adjlist2.contains(first))
                return;

            adjlist1.remove(second);
            adjlist2.remove(first);

            edges.put(first,adjlist1);
            edges.put(second,adjlist2);
        }
    }

    boolean hasEdge(final Node first, final Node second)
    {
        LinkedList<Node> adjlist1 = edges.get(first);
        LinkedList<Node> adjlist2 = edges.get(second);
        return adjlist1.contains(second) || adjlist2.contains(first);
    }

    LinkedList<Node> getAdjList(final Node node)
    {
        return edges.get(node);
    }


    HashSet<Node> getAllNodes()
    {
        HashSet<Node> set = new HashSet<Node>();
        set.addAll(this.nodes);
        return set;
    }

    LinkedList<Node> getNodeList()
    {
        return this.nodes;
    }

}
