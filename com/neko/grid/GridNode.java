package com.neko.grid;


public class GridNode implements Comparable<GridNode>{
    public String nodeVal;
    private GridNode[] adjacent;


    public enum GridDirection {
        TOP(0),
        BOTTOM(1),
        LEFT(2),
        RIGHT(3),
        ERR(4);

        private int value;

        private GridDirection(int value)
        {
            this.value = value;
        }

        public int getValue(){
            return value;
        }

        public static GridDirection flip (GridDirection gd)
        {
            switch(gd)
            {
                case TOP:
                    return BOTTOM;
                case BOTTOM:
                    return TOP;
                case RIGHT:
                    return LEFT;
                case LEFT:
                    return RIGHT;
                case ERR:
                    return ERR;
            }
            return ERR;
        }
    }




    public GridNode(String nodeVal)
    {
        this.nodeVal = nodeVal;
        adjacent = new GridNode[4];
    }

    public void setSide(GridDirection direction, GridNode node)
    {
        adjacent[direction.getValue()] = node;
    }

    public GridNode getSide(GridDirection direction)
    {
        return adjacent[direction.getValue()];
    }


    @Override
    public int compareTo(GridNode o) {
        return nodeVal.compareTo(o.nodeVal);
    }

    @Override
    public String toString() {
        return "GridNode{" +
                "nodeVal=" + nodeVal+
                '}';
    }
}
