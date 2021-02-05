// Romel Pascua 017167304
// CECS 428 Alien Maze
// 2/05/2021

public class Cell {
    int walls;
    Cell parent;
    int t;
    int z;
    int y;
    int x;

    // DEFAULT CONSTRUCTOR
    Cell() {
        walls = 0b00001111;
    }

    // CONSTRUCTOR
    Cell(int walls, Cell parent, int t, int z, int y, int x) {
        this.walls = walls;
        this.parent = parent;
        this.t = t;
        this.z = z;
        this.y = y;
        this.x = x;
    }

    // GETTERS
    public int getWalls() { return walls; }

    public Cell getParent() { return parent; }

    public int getT() { return t; }
    public int getZ() { return z; }
    public int getY() { return y; }
    public int getX() { return x; }

    // SETTERS
    public void setWalls(int walls) { this.walls = walls; }

    public void setParent(Cell parent) { this.parent = parent; }

    public void setT(int t) { this.t = t; }
    public void setZ(int z) { this.z = z; }
    public void setY(int y) { this.y = y; }
    public void setX(int x) { this.x = x; }

    // MISC

    // breaks a wall
    public void changeWall(int change) {
        walls = walls & ~(1 << (change - 1));
    }

    // returns one wall
    public int getWall(int dir) {
        return (walls >> (dir - 1)) & 1;
    }

    // returns coordinate
    public int getExactCoord(int coord) {
        if (coord == 0) { return t; }
        if (coord == 1) { return z; }
        if (coord == 2) { return y; }
        if (coord == 3) { return x; }
        return 0;
    }

    // returns location as string
    public String getLocation() {
        return t + ", " + z + ", " + y + ", " + x;
    }

    // returns neighbors string location
    public String getNeighbor(int direction, int coordinate) {
        switch (coordinate) {
            case 0:
                if (direction % 2 == 0) { return (t - 1) + ", " + z + ", " + y + ", " + x; }
                else { return (t + 1) + ", " + z + ", " + y + ", " + x; }
            case 1:
                if (direction % 2 == 0) { return t + ", " + (z - 1) + ", " + y + ", " + x; }
                else { return t + ", " + (z + 1) + ", " + y + ", " + x; }
            case 2:
                if (direction % 2 == 0) { return t + ", " + z + ", " + (y - 1) + ", " + x; }
                else { return t + ", " + z + ", " + (y + 1) + ", " + x; }
            case 3:
                if (direction % 2 == 0) { return t + ", " + z + ", " + y + ", " + (x - 1); }
                else { return t + ", " + z + ", " + y + ", " + (x + 1); }
            default:
                return "default switch case";
        }
    }
}
