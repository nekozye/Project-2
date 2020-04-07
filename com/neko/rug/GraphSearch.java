package com.neko.rug;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphSearch {

    Graph graphToSearch;

    public GraphSearch(Graph graphToSearch)
    {
        switchGraph(graphToSearch);
    }

    public void switchGraph(Graph graphToSearch)
    {
        this.graphToSearch = graphToSearch;
    }
    //3d
    public ArrayList<Node> DFSRec(final Node start, final Node end)
    {
        HashSet<Node> visited = new HashSet<Node>();
        LinkedList<Node> nodelist = DFSRecHelper(start,start,end,visited);
        ArrayList<Node> nds = new ArrayList<Node>();
        for(int i = 0; i < nodelist.size(); i++)
        {
            nds.add(nodelist.get(i));
        }
        return nds;
    }
    //returns null if no destination reach. returns the list including start if exists.
    //makes sure the recursive happens before queueing the next log, so depth is searched first
    public LinkedList<Node> DFSRecHelper(final Node start, final Node current, final Node end, HashSet<Node> visited)
    {
        visited.add(current);
        if(current.equals(end))
        {
            LinkedList<Node> rtlist = new LinkedList<Node>();
            rtlist.addFirst(current);
            return rtlist;
        }

        LinkedList<Node> adjlist = graphToSearch.getAdjList(current);

        boolean deadEnd = true;

        for(int i = 0; i < adjlist.size(); i++)
        {
            if(!visited.contains(adjlist.get(i)))
            {
                deadEnd = false;
                LinkedList<Node> rtf = DFSRecHelper(start,adjlist.get(i),end,visited);
                if(rtf != null)
                {
                    rtf.addFirst(current);
                    return rtf;
                }
            }
        }

        if(deadEnd)
        {
            return null;
        }

        return null;
    }

    //3e
    public ArrayList<Node> DFSIter(final Node start, final Node end)
    {
        LinkedList<Node> workStack = new LinkedList<Node>();
        LinkedList<Node> visited = new LinkedList<Node>();
        LinkedList<Node> aqpath = new LinkedList<Node>();
        Node work = null;
        workStack.addLast(start);
        while(!workStack.isEmpty())
        {
            work = workStack.removeLast();

            if(visited.contains(work))
                continue;

            if(work.equals(end))
            {
                aqpath.addLast(work);
                ArrayList<Node> rtlist = new ArrayList<Node>();
                rtlist.addAll(aqpath);
                return rtlist;
            }

            visited.add(work);
            aqpath.add(work);


            LinkedList<Node> adjList = graphToSearch.getAdjList(work);
            LinkedList<Node> ntvst = new LinkedList<Node>();

            for(Node n : adjList)
            {
                if(!visited.contains(n))
                {
                    ntvst.addLast(n);
                }
            }

            if(ntvst.size() == 0)
            {
                Node nextNodeInQuestion = workStack.getLast();
                boolean rmActive = false;
                LinkedList<Node> newAQList = new LinkedList<Node>();
                for(int i = 0; i < aqpath.size(); i++)
                {
                    if(!rmActive)
                    {
                        newAQList.addLast(aqpath.get(i));
                    }
                    if(aqpath.get(i).equals(nextNodeInQuestion))
                    {
                        rmActive = false;
                    }
                }
                aqpath = newAQList;
                continue;
            }

            for(Node n : ntvst)
            {
                workStack.addLast(n);
            }
        }
        return null;
    }

    //3f
    public ArrayList<Node> BFTRec(final Graph graph)
    {
        LinkedList<Node> queue = null;
        LinkedList<Node> visited = new LinkedList<Node>();
        LinkedList<Node> bflr = new LinkedList<Node>();

        ArrayList<Node> addls = new ArrayList<Node>();


        LinkedList<Node> traverseNeed = graph.getNodeList();

        while(traverseNeed.size() != 0)
        {
            queue = new LinkedList<Node>();
            bflr = new LinkedList<Node>();

            queue.add(traverseNeed.getFirst());

            ArrayList<Node> nodls = BFTRecHelper(graph,queue,visited,bflr);

            traverseNeed.removeAll(nodls);

            addls.addAll(nodls);
        }

        return addls;

    }

    public ArrayList<Node> BFTRecHelper(final Graph graph, LinkedList<Node> queue, LinkedList<Node> visited, LinkedList<Node> bflr)
    {
        if (queue.isEmpty())
        {
            ArrayList<Node> rtbf = new ArrayList<Node>();
            rtbf.addAll(bflr);
            return rtbf;
        }

        Node n = queue.removeFirst();
        visited.add(n);

        bflr.add(n);

        for(Node ne : graph.getAdjList(n))
        {
            if (!visited.contains(ne))
            {
                queue.addLast(ne);
            }
        }

        return BFTRecHelper(graph,queue,visited,bflr);
    }

    //3g
    public ArrayList<Node> BFTIter(final Graph graph)
    {
        ArrayList<Node> visited = new ArrayList<Node>();
        LinkedList<Node> leftover = (LinkedList<Node>)graph.getNodeList().clone();
        LinkedList<Node> queue = new LinkedList<Node>();

        queue.addLast(leftover.removeFirst());

        while((!leftover.isEmpty())||!queue.isEmpty())
        {
            Node next = queue.removeFirst();

            if(visited.contains(next))
                continue;

            visited.add(next);
            leftover.remove(next);

            LinkedList<Node> adj = graph.getAdjList(next);

            boolean hasUnvisited = false;
            for(int i = 0; i < adj.size(); i++)
            {
                if(!visited.contains(adj.get(i)))
                {
                    hasUnvisited = true;
                    queue.addLast(adj.get(i));
                }
            }

            if(!hasUnvisited)
            {
                if(leftover.isEmpty())
                    continue;
                queue.addLast(leftover.removeFirst());
            }
        }

        return visited;
    }



}
