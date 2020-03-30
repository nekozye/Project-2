package com.neko;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Graph eug = createExampleUnweightedGraphIter();
        GraphSearch searcher = new GraphSearch(eug);

        LinkedList<Node> elist = eug.getNodeList();
        Node[] earr = new Node[elist.size()];
        earr = elist.toArray(earr);

        System.out.println(BFTRecLinkedList(eug));
    }

    //tester
    public static Graph createExampleUnweightedGraphIter()
    {
        Graph eug = new Graph();

        for(int i = 0; i < 25; i++)
        {
            eug.addNode(String.valueOf(i+1));
        }

        LinkedList<Node> elist = eug.getNodeList();
        Node[] earr = new Node[elist.size()];
        earr = elist.toArray(earr);

        eug.addUndirectedEdge(earr[0],earr[1]);
        eug.addUndirectedEdge(earr[0],earr[3]);
        eug.addUndirectedEdge(earr[0],earr[16]);

        eug.addUndirectedEdge(earr[1],earr[2]);

        eug.addUndirectedEdge(earr[3],earr[4]);

        eug.addUndirectedEdge(earr[4],earr[5]);

        eug.addUndirectedEdge(earr[5],earr[6]);

        eug.addUndirectedEdge(earr[6],earr[7]);

        eug.addUndirectedEdge(earr[7],earr[8]);
        eug.addUndirectedEdge(earr[7],earr[15]);

        eug.addUndirectedEdge(earr[8],earr[12]);
        eug.addUndirectedEdge(earr[8],earr[9]);

        eug.addUndirectedEdge(earr[9],earr[10]);

        eug.addUndirectedEdge(earr[10],earr[11]);

        eug.addUndirectedEdge(earr[12],earr[13]);

        eug.addUndirectedEdge(earr[13],earr[14]);

        eug.addUndirectedEdge(earr[16],earr[17]);

        eug.addUndirectedEdge(earr[17],earr[18]);

        eug.addUndirectedEdge(earr[18],earr[19]);

        eug.addUndirectedEdge(earr[19],earr[20]);

        eug.addUndirectedEdge(earr[20],earr[21]);
        eug.addUndirectedEdge(earr[20],earr[22]);

        eug.addUndirectedEdge(earr[22],earr[23]);

        eug.addUndirectedEdge(earr[23],earr[24]);

        return eug;
    }

    //3b
    public static Graph createRandomUnweightedGraphIter(int n)
    {
        Graph rug = new Graph();

        for(int i = 0; i < n; i++)
        {
            rug.addNode(String.valueOf(i+1));

        }

        LinkedList<Node> listnodes = rug.getNodeList();
        Node[] nodearr = new Node[listnodes.size()];
        nodearr = listnodes.toArray(nodearr);

        for(Node node : listnodes)
        {
            Random random = new Random();
            int type = random.nextInt(4);
            int select = 0;
            Node nd = null;
            int selector = rug.getAdjList(node).size();
            selector = Math.min(selector, 4);
            type = (type + 1) - selector;
            type = type - 1;
            switch(type)
            {
                case 3:
                    select = random.nextInt(listnodes.size());
                    nd = nodearr[select];
                    rug.addUndirectedEdge(node,nd);
                case 2:
                    select = random.nextInt(listnodes.size());
                    nd = nodearr[select];
                    rug.addUndirectedEdge(node,nd);
                case 1:
                    select = random.nextInt(listnodes.size());
                    nd = nodearr[select];
                    rug.addUndirectedEdge(node,nd);
                case 0:
                    select = random.nextInt(listnodes.size());
                    nd = nodearr[select];
                    rug.addUndirectedEdge(node,nd);
                    break;
            }
        }

        return rug;
    }

    //3c
    public static Graph createLinkedList(int n)
    {
        Graph rug = new Graph();
        for(int i = 0; i < n; i++)
        {
            rug.addNode(String.valueOf(i+1));
        }
        LinkedList<Node> listnodes = rug.getNodeList();
        Node[] nodearr = new Node[listnodes.size()];
        nodearr = listnodes.toArray(nodearr);

        for(int i = 1; i < n; i++)
        {
            rug.addUndirectedEdge(nodearr[i],nodearr[i-1]);
        }
        return rug;
    }

    //3h
    public static ArrayList<Node> BFTRecLinkedList(final Graph graph)
    {
        Graph glr = createLinkedList(10000);
        GraphSearch gsearch = new GraphSearch(glr);

        return gsearch.BFTRec(glr);
    }
    /*
        This ran into issue, spitting out stack overflow error. Since each BFT Recursive tree level requires one "stack slot",
        Basically n = 10000 requires 10000 stack, which is way out of over of my machine setting (that has been deliberatly toned down
    */


    //3i
    public static ArrayList<Node> BFTIterLinkedList(final Graph graph)
    {
        Graph glr = createLinkedList(10000);
        GraphSearch gsearch = new GraphSearch(glr);

        return gsearch.BFTIter(glr);
    }

    /*
        This did not ran into issue, unlike the previous function.
        Instead of the whole program flow stack, it only uses one queue of linked list of relatively small dataset (one node per one entry in queue)
        This makes less physical space needed for same task, therefore it does not run into issue unlike recursive list.

    */



}
