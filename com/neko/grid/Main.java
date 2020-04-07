package com.neko.grid;

import java.util.HashMap;
import java.util.Random;

public class Main {

    public static GridGraph createRandomGridGraph (int n){
        GridGraph graph = new GridGraph(n+1,n+1);

        for(int i = 0 ; i < n+1; i++)
        {
            for(int j = 0 ; j < n+1; j++)
            {
                graph.addGridNode(i,j,Integer.toString(i*(n+1)+j));
            }
        }

        Random rand = new Random();

        for(int i = 0 ; i < n+1; i++)
        {
            for(int j = 0 ; j < n+1; j++)
            {
                HashMap<GridNode.GridDirection, GridNode> sideMap = graph.getSides(graph.grid[i][j]);
                for(GridNode.GridDirection gd : sideMap.keySet())
                {
                    if(graph.grid[i][j].getSide(gd) != null) {
                        GridNode nodeOnSide = sideMap.get(gd);
                        if (rand.nextBoolean()) {
                            graph.addUndirectedEdge(graph.grid[i][j],nodeOnSide);
                        }
                    }
                }
            }
        }

        return graph;
    }
}
