package com.neko.dag;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    static final int MAX_WEIGHT = 25560;

    public static void main(String args[])
    {
        DirectedGraph graph = createRandomDAGIter(1000);
        System.out.println(Arrays.deepToString(TopSort.mDFS(graph).toArray()));

        System.out.println(Arrays.deepToString(TopSort.Kahns(graph).toArray()));
    }

    public static HashMap<DirectCostNode, Integer> dijkstras(final DirectCostNode start)
    {
        HashMap<DirectCostNode, Integer> distance = new HashMap<DirectCostNode, Integer>();

        LinkedList<DirectCostNode> workQ = new LinkedList<DirectCostNode>();

        workQ.addLast(start);
        distance.put(start,0);

        while(!workQ.isEmpty())
        {
            DirectCostNode rooters = workQ.removeFirst();
            for(DirectCostNode dest : rooters.edges.keySet())
            {
                if(distance.keySet().contains(dest))
                {
                    int destial = distance.get(dest);
                    int compare = distance.get(rooters).intValue() + rooters.edges.get(dest);

                    distance.put(dest,compare < destial ? compare : destial);
                }
                else
                {
                    int puts = distance.get(rooters).intValue() + rooters.edges.get(dest);

                    distance.put(dest,new Integer(puts));
                }

                workQ.addLast(dest);
            }
        }

        return distance;
    }

    public static WeightedGraph createLinkedList(final int n){
        WeightedGraph linked = new WeightedGraph();
        LinkedList<DirectCostNode> list = new LinkedList<DirectCostNode>();

        for(int i = 0; i < n; i++)
        {
            DirectCostNode nd = new DirectCostNode(String.valueOf(i));
            list.addLast(nd);
            linked.addNode(nd);
        }

        for(int i = 0; i < n-1; i++)
        {
            linked.addDirectedEdge(list.get(i),list.get(i+1));
        }

        return linked;
    }

    public static WeightedGraph createRandomCompleteWeightedGraph(final int n)
    {
        WeightedGraph graphToReturn = new WeightedGraph();
        LinkedList<DirectCostNode> list = new LinkedList<DirectCostNode>();

        for(int i = 0; i < n; i++)
        {
            DirectCostNode nd = new DirectCostNode(String.valueOf(i));
            list.addLast(nd);
            graphToReturn.addNode(nd);
        }

        Random rand = new Random();

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(i != j)
                {
                    graphToReturn.addWeightedEdge(list.get(i),list.get(j),rand.nextInt(MAX_WEIGHT)+1);
                }
            }
        }

        return graphToReturn;
    }

    public static DirectedGraph createRandomDAGIter(final int n){

        DirectedGraph graphToReturn = new DirectedGraph();
        LinkedList<DirectCostNode> nodesToAdd = new LinkedList<DirectCostNode>();

        for(int i = 0; i < n; i++)
        {
            DirectCostNode nd = new DirectCostNode(String.valueOf(i));
            nodesToAdd.addLast(nd);
            graphToReturn.addNode(nd);
        }

        Random rand = new Random();

        LinkedList<DirectCostNode> queue = new LinkedList<DirectCostNode>();
        queue.addLast(nodesToAdd.removeFirst());

        while(!queue.isEmpty())
        {
            int decider = rand.nextInt(3);

            DirectCostNode nodeInQuestion = queue.removeFirst();
            DirectCostNode nodeTemp = null;
            switch(decider)
            {
                case 2:
                    if(nodesToAdd.isEmpty())
                        break;
                    nodeTemp = nodesToAdd.removeFirst();
                    graphToReturn.addDirectedEdge(nodeInQuestion,nodeTemp);
                    queue.addLast(nodeTemp);
                case 1:
                    if(nodesToAdd.isEmpty())
                        break;
                    nodeTemp = nodesToAdd.removeFirst();
                    graphToReturn.addDirectedEdge(nodeInQuestion,nodeTemp);
                    queue.addLast(nodeTemp);
                case 0:
                    break;
            }

            if(queue.isEmpty() && !nodesToAdd.isEmpty())
            {
                queue.addLast(nodesToAdd.removeFirst());
            }
        }

        return graphToReturn;
    }

}
