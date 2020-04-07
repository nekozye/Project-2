package com.neko.grid;


import java.util.*;

public class GridGraph {

    public GridNode[][] grid;


    //max_x and max_y are exclusive.
    public GridGraph (final int max_x, final int max_y)
    {
        grid = new GridNode[max_x][max_y];
    }

    public void addGridNode(final int x, final int y, final String nodeVal){
        if(x < grid.length && y < grid[0].length)
        {
            grid[x][y] = new GridNode(nodeVal);
        }
    }

    public void addUndirectedEdge(final GridNode first, final GridNode second)
    {
        boolean nextTo = isNextTo(first,second);
        if(nextTo)
        {
            GridNode.GridDirection gd = getSide(first,second);
            if(gd != GridNode.GridDirection.ERR)
            {
                first.setSide(gd,second);
                second.setSide(GridNode.GridDirection.flip(gd),first);
            }
        }
    }

    public void removeUndirectedEdge(final GridNode first, final GridNode second)
    {
        boolean nextTo = isNextTo(first,second);
        if(nextTo)
        {
            GridNode.GridDirection gd = getSide(first,second);
            if(gd != GridNode.GridDirection.ERR)
            {
                first.setSide(gd,null);
                second.setSide(GridNode.GridDirection.flip(gd),null);
            }
        }
    }

    public HashSet<GridNode> getAllNodes()
    {
        HashSet<GridNode> allNodes = new HashSet<GridNode>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j] != null)
                    allNodes.add(grid[i][j]);
            }
        }
        return allNodes;
    }


    private boolean isNextTo(final GridNode first, final GridNode second) {
        List<GridNode> nodeToSearch = new LinkedList<GridNode>();
        nodeToSearch.add(first);
        nodeToSearch.add(second);

        HashMap<GridNode,Coords> grid = getCoords(nodeToSearch);

        if(grid.keySet().contains(first)&&grid.keySet().contains(second) && (Math.abs(grid.get(first).x-grid.get(second).x) + Math.abs(grid.get(first).y-grid.get(second).y)==1))
        {
            return true;
        }
        return false;
    }

    private GridNode.GridDirection getSide(final GridNode first, final GridNode second) {
        List<GridNode> nodeToSearch = new LinkedList<GridNode>();
        nodeToSearch.add(first);
        nodeToSearch.add(second);

        HashMap<GridNode,Coords> grid = getCoords(nodeToSearch);

        if(grid.keySet().contains(first)&&grid.keySet().contains(second))
        {
            int xCoordDiff = grid.get(first).x-grid.get(second).x;
            int yCoordDiff = grid.get(first).y - grid.get(second).y;
            if(xCoordDiff == 1 && yCoordDiff == 0)
            {
                return GridNode.GridDirection.RIGHT;
            }
            else if(xCoordDiff == 0 && yCoordDiff == 1)
            {
                return GridNode.GridDirection.BOTTOM;
            }
            else if(xCoordDiff == -1 && yCoordDiff == 0)
            {
                return GridNode.GridDirection.LEFT;
            }
            else if(xCoordDiff == 0 && yCoordDiff == -1)
            {
                return GridNode.GridDirection.TOP;
            }
        }
        return GridNode.GridDirection.ERR;
    }

    public HashMap<GridNode.GridDirection, GridNode> getSides(final GridNode first) {
        HashMap<GridNode.GridDirection, GridNode> sideMap = new HashMap<GridNode.GridDirection, GridNode>();


        Coords gridcoords = getCoord(first);
        if(gridcoords.x-1 >= 0)
        {
            sideMap.put(GridNode.GridDirection.LEFT,grid[gridcoords.x-1][gridcoords.y]);
        }
        if(gridcoords.x+1 < this.grid.length )
        {
            sideMap.put(GridNode.GridDirection.RIGHT,grid[gridcoords.x+1][gridcoords.y]);
        }
        if(gridcoords.y-1 >= 0)
        {
            sideMap.put(GridNode.GridDirection.TOP,grid[gridcoords.x][gridcoords.y-1]);
        }
        if(gridcoords.y+1 < this.grid[gridcoords.x].length)
        {
            sideMap.put(GridNode.GridDirection.BOTTOM,grid[gridcoords.x][gridcoords.y+1]);
        }

        return sideMap;
    }


    private Coords getCoord(final GridNode gnd)
    {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j].equals (gnd))
                {
                    return new Coords(i,j);
                }
            }
        }
        return null;
    }

    private HashMap<GridNode,Coords> getCoords(List<GridNode> gnd)
    {
        HashMap<GridNode,Coords> returns = new HashMap<GridNode,Coords>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++)
            {
                for(GridNode nd : gnd)
                {
                    if(grid[i][j].equals(nd))
                        returns.put(nd,new Coords(i,j));
                }
            }
        }
        return returns;
    }
}
