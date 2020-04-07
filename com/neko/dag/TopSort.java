package com.neko.dag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class TopSort {
    public static ArrayList<DirectCostNode> Kahns(final DirectedGraph graph){


        HashMap<DirectCostNode, Integer> indegree = new HashMap<DirectCostNode, Integer>();
        ArrayList<DirectCostNode> results = new ArrayList<DirectCostNode>();

        //indegree calculation preprocessing
        for(final DirectCostNode node : graph.nodes)
        {
            indegree.put(node,new Integer(0));
        }

        for(final DirectCostNode node : graph.nodes)
        {
            for(final DirectCostNode dest : node.edges.keySet())
            {
                int idg = indegree.get(dest)+1;
                indegree.put(dest,idg);
            }
        }

        //kahns
        LinkedList<DirectCostNode> workQ = graph.getStarts();
        int countVisitedNode = 0;

        while(!workQ.isEmpty())
        {
            DirectCostNode node = workQ.removeFirst();
            results.add(node);
            countVisitedNode++;
            for(final DirectCostNode dest : node.edges.keySet())
            {
                int idsdst = indegree.get(dest) - 1;
                indegree.put(dest,idsdst);

                if(idsdst == 0)
                    workQ.addLast(dest);
            }
        }

        if(countVisitedNode != graph.nodes.size())
            return null;
        else
        {
            return results;
        }
    }

    public static ArrayList<DirectCostNode> mDFS(final DirectedGraph graph){
        LinkedList<DirectCostNode> visited = new LinkedList<DirectCostNode>();
        LinkedList<DirectCostNode> workStack = new LinkedList<DirectCostNode>();
        LinkedList<DirectCostNode> resultStack = new LinkedList<DirectCostNode>();
        HashSet<DirectCostNode> allset = graph.getAllNodes();

        for(DirectCostNode node : allset)
        {
            if(visited.contains(node))
            {
                continue;
            }

            workStack.addFirst(node);


            while(!workStack.isEmpty())
            {
                DirectCostNode nudge = workStack.removeFirst();
                visited.addLast(nudge);
                resultStack.addFirst(nudge);
                for(DirectCostNode dest : nudge.edges.keySet())
                {
                    if(!visited.contains(dest))
                        workStack.addFirst(dest);
                }
            }
        }

        ArrayList<DirectCostNode> result = new ArrayList<DirectCostNode>();
        while(!resultStack.isEmpty())
        {
            result.add(resultStack.removeFirst());
        }

        return result;

    }
}
